package io.github.bortoletoeric.arquiteturaspring.todo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TodoService {

    private TodoRepository repository;
    private TodoValidator validator;
    public MailSender mailSender;

    public TodoService(TodoRepository todoRepository,
                       TodoValidator todoValidator,
                       MailSender mailSender) {
        this.repository = todoRepository;
        this.validator = todoValidator;
        this.mailSender = mailSender;
    }

    public TodoEntity salvar(TodoEntity novoTodo) {
        try {
            validator.validar(novoTodo);
        } catch (IllegalAccessException e) {
            System.out.println("Erro ao validar o TodoEntity: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return repository.save(novoTodo);
    }

    public void atualizarStatus(TodoEntity todo) {
        repository.save(todo);
        String status = todo.getCompleted() == Boolean.TRUE ? "Concluido" : "Não concluido";
        mailSender.sendMail("Todo: " + todo.getDescription() + " foi atualizado para " + status);
    }

    public TodoEntity buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
