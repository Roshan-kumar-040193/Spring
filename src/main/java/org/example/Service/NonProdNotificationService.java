package org.example.Service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class NonProdNotificationService implements NotificationService{
    @Override
    public void notifyUser(String messgae) {
        System.out.println("Non-Prod implementation "+messgae);
    }
}
