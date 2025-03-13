package com.laalka.expensesservice.conrollers;

import com.laalka.expensesservice.dto.ExpenseRequest;
import com.laalka.expensesservice.dto.ExpenseResponse;
import com.laalka.expensesservice.maps.ExpenseMapper;
import com.laalka.expensesservice.models.ExpenseEntity;
import com.laalka.expensesservice.services.ExpenseService;
import com.laalka.expensesservice.services.impl.ExpenseServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class ExpenseController {

    private final ExpenseServiceImpl expenseServiceImpl;
    private final ExpenseMapper expenseMapper;

    public ExpenseController(ExpenseServiceImpl expenseServiceImpl, ExpenseMapper expenseMapper) {
        this.expenseServiceImpl = expenseServiceImpl;
        this.expenseMapper = expenseMapper;
    }

    /**
     * Получение всех сущностей трат по userId
     * @param sender
     * @return
     */
    @GetMapping("/user/{sender}")
    public ResponseEntity<List<ExpenseResponse>> getExpensesByUser(@PathVariable String sender) {
        List<ExpenseEntity> entities = expenseServiceImpl.expensesList(sender);

        List<ExpenseResponse> responseList = entities.stream()
                .map(expenseMapper::fromEntityToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/userIncome/{receiver}")
    public ResponseEntity<List<ExpenseResponse>> getExpensesByReceiver(@PathVariable String receiver) {
        List<ExpenseEntity> entities = expenseServiceImpl.incomesList(receiver);

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
    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody ExpenseRequest request) {

        ExpenseEntity createdEntity = new ExpenseEntity();
        createdEntity.setSender(request.getSender());
        createdEntity.setReceiver(request.getReceiver());
        createdEntity.setAmount(request.getAmount());

        expenseServiceImpl.createExpense(createdEntity);
        return ResponseEntity.ok(expenseMapper.fromEntityToResponse(createdEntity));
    }



}
