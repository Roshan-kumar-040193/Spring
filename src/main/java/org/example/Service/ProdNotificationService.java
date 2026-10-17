package org.example.Service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdNotificationService implements NotificationService{
    @Override
    public void notifyUser(String messgae) {
        System.out.println("Prod implementation "+messgae);
    }
}
