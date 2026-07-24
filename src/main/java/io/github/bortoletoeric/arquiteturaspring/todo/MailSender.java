package io.github.bortoletoeric.arquiteturaspring.todo;

import org.springframework.stereotype.Component;

@Component
public class MailSender {

    public void sendMail(String mensagem) {
        System.out.println("Enviando email: " + mensagem);
    }
}
