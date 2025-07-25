package ru.kpfu.itis.cmsforblogs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.cmsforblogs.dto.KafkaNotificationMessage;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    private final static String TOPIC_NAME = "message-topic";

    private final KafkaTemplate<String, KafkaNotificationMessage> kafkaTemplate;

    public void sendMessage(KafkaNotificationMessage message) {

        Message<KafkaNotificationMessage> kafkaMessage = MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, TOPIC_NAME)
                .setHeader(KafkaHeaders.KEY, message.getClass().getSimpleName())
                .build();

        CompletableFuture<SendResult<String, KafkaNotificationMessage>> future = kafkaTemplate.send(kafkaMessage);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message: {}", result.getProducerRecord().value());
            } else {
                log.error("Failed to send message", ex);
            }
        });

    }
}

