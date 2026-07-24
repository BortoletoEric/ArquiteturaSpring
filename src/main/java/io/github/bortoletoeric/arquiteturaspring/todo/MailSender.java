package io.github.bortoletoeric.arquiteturaspring.todo;

import org.springframework.stereotype.Component;

/**
 * Componente responsável por envio de notificações por email.
 *
 * <p>Este componente encapsula a lógica de envio de emails para notificações
 * do sistema de tarefas. Atualmente, implementa um comportamento de log para fins
 * educacionais, mas pode ser facilmente expandido para usar provedores de email reais.
 *
 * <p><b>Casos de uso:</b>
 * <ul>
 *     <li>Notificação quando o status de uma tarefa é alterado</li>
 *     <li>Confirmação de criação de nova tarefa (futuro)</li>
 *     <li>Lembretes de tarefas pendentes (futuro)</li>
 * </ul>
 *
 * <p><b>Exemplo de uso:</b>
 * <pre>
 *     mailSender.sendMail("Tarefa foi marcada como concluída");
 * </pre>
 *
 * @author Eric Bortoleto
 * @version 1.0
 * @see TodoService
 */
@Component
public class MailSender {

    /**
     * Envia uma mensagem de email (notificação).
     *
     * <p>Atualmente, registra a mensagem no console (System.out). Esta implementação
     * pode ser estendida para integrar com provedores de email como SendGrid, AWS SES, etc.
     *
     * @param mensagem o conteúdo da mensagem a ser enviada
     */
    public void sendMail(String mensagem) {
        System.out.println("Enviando email: " + mensagem);
    }
}
