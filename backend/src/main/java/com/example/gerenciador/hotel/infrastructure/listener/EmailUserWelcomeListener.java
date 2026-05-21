package com.example.gerenciador.hotel.infrastructure.listener;


import com.example.gerenciador.hotel.domain.user.event.UserCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailUserWelcomeListener {

    @EventListener
    public void handleUserCreated(UserCreatedEvent event) {
        String email = event.getEmail();
        String name = event.getName();
        //System.out.println("📧 Enviando boas-vindas para " + name + " no e-mail " + email);
    }
}
