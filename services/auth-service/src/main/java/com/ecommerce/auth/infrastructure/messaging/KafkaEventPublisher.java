package com.ecommerce.auth.infrastructure.messaging;

import com.ecommerce.auth.application.port.out.EventPublisher;
import com.ecommerce.auth.domain.model.UserId;

/**
 * Kafka adapter placeholder. Topic names, schemas, and delivery guarantees are not defined yet.
 */
public final class KafkaEventPublisher implements EventPublisher {

    @Override
    public void publish(String eventType, UserId aggregateId) {
        throw new UnsupportedOperationException("Kafka event publishing is not implemented");
    }
}
