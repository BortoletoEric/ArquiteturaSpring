package io.github.bortoletoeric.arquiteturaspring.todo;

import org.springframework.stereotype.Component;

@Component
public class TodoValidator {
    private TodoRepository todoRepository;

    public TodoValidator(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void validar(TodoEntity todoEntity) throws IllegalAccessException {
        if (existeTodoComEssaDescricao(todoEntity.getDescription())) {
            throw new IllegalAccessException("Já existe um TODO com essa descrição");
        }
    }

    private Boolean existeTodoComEssaDescricao(String descricao) {
        return todoRepository.existsByDescription(descricao);
    }
}
