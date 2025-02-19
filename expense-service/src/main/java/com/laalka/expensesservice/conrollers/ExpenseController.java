package com.laalka.expensesservice.conrollers;

import com.laalka.expensesservice.dto.ExpenseRequest;
import com.laalka.expensesservice.dto.ExpenseResponse;
import com.laalka.expensesservice.maps.ExpenseMapper;
import com.laalka.expensesservice.models.ExpenseEntity;
import com.laalka.expensesservice.services.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exp")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseMapper expenseMapper;

    public ExpenseController(ExpenseService expenseService, ExpenseMapper expenseMapper) {
        this.expenseService = expenseService;
        this.expenseMapper = expenseMapper;
    }

    /**
     * Получение всех сущностей трат по userId
     * @param userId
     * @return
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExpenseResponse>> getExpensesByUser(@PathVariable Long userId) {
        List<ExpenseEntity> entities = expenseService.expensesList(userId);

        List<ExpenseResponse> responseList = entities.stream()
                .map(expenseMapper::fromEntityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    /**
     * Создание новой сущности траты
     * @param request
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody @Valid ExpenseRequest request) {
        ExpenseEntity createdEntity;

        if (request.getCategory() == null || request.getCategory().isBlank()) {
            createdEntity = expenseService.createExpense(
                    request.getUserId(),
                    request.getReceiverId(),
                    request.getAmount()
            );
        } else {
            createdEntity = expenseService.createExpense(
                    request.getUserId(),
                    request.getReceiverId(),
                    request.getAmount(),
                    request.getCategory()
            );
        }

        ExpenseResponse response = expenseMapper.fromEntityToResponse(createdEntity);
        return ResponseEntity.ok(response);
    }

    /**
     * Обновление текущей сущности траты
     * @param expenseId
     * @param request
     * @return
     */
    @PutMapping("/update/{expenseId}")
    public ResponseEntity<ExpenseResponse> updateExpense(@PathVariable Long expenseId,
                                                         @RequestBody @Valid ExpenseRequest request) {
        ExpenseEntity updatedEntity = expenseService.updateExpense(
                expenseId,
                request.getUserId(),
                request.getReceiverId(),
                request.getAmount(),
                request.getCategory()
        );
        ExpenseResponse response = expenseMapper.fromEntityToResponse(updatedEntity);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{expenseId}")
    public ResponseEntity<ExpenseResponse> deleteExpense(@PathVariable Long expenseId) {
        expenseService.deleteExpense(expenseId);
        return ResponseEntity.noContent().build();
    }


}
