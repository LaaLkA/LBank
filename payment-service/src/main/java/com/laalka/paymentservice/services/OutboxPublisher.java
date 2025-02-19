package com.laalka.paymentservice.services;

import com.laalka.paymentservice.models.OutboxEvent;
import com.laalka.paymentservice.repositories.OutboxEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OutboxPublisher {
    @Autowired
    private OutboxEventRepository outboxEventRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC_PAYMENT = "paymentCompletedTopic";

    @Scheduled(fixedDelay = 5000)
    public void publishOutboxEvents() {

        List<OutboxEvent> events = outboxEventRepository.findByProcessedFalse();
        if (events.isEmpty()) {
            return;
        }
        for (OutboxEvent outbox : events) {
            try {
                kafkaTemplate.send(TOPIC_PAYMENT,outbox.getPayload());

                outbox.setProcessed(true);
                outbox.setProcessedAt(LocalDateTime.now());
                outboxEventRepository.save(outbox);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
