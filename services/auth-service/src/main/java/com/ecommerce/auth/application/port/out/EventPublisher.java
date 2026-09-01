package com.ecommerce.auth.application.port.out;

import com.ecommerce.auth.domain.model.UserId;

public interface EventPublisher {

    void publish(String eventType, UserId aggregateId);
}
