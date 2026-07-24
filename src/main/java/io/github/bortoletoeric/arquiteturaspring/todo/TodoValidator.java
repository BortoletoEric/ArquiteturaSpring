package io.github.bortoletoeric.arquiteturaspring.todo;

import org.springframework.stereotype.Component;

/**
 * Validador de regras de negócio para tarefas.
 *
 * <p>Este componente encapsula as validações específicas do domínio de tarefas (To Dos),
 * garantindo que as regras de negócio sejam respeitadas antes de persistir alterações.
 *
 * <p><b>Validações realizadas:</b>
 * <ul>
 *     <li>Verifica se já existe tarefa com a mesma descrição</li>
 * </ul>
 *
 * <p><b>Exceções lançadas:</b>
 * <ul>
 *     <li>{@link IllegalAccessException} - Quando a tarefa viola uma regra de negócio</li>
 * </ul>
 *
 * @author Eric Bortoleto
 * @version 1.0
 * @see TodoService
 * @see TodoEntity
 * @see TodoRepository
 */
@Component
public class TodoValidator {
    
    private TodoRepository todoRepository;

    /**
     * Construtor com injeção de dependência.
     *
     * @param todoRepository repositório para consultar tarefas existentes
     */
    public TodoValidator(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    /**
     * Valida se a tarefa atende às regras de negócio.
     *
     * <p>Atualmente valida que não existe outra tarefa com a mesma descrição.
     *
     * @param todoEntity a tarefa a ser validada
     * @throws IllegalAccessException se a tarefa viola uma regra de negócio
     */
    public void validar(TodoEntity todoEntity) throws IllegalAccessException {
        if (existeTodoComEssaDescricao(todoEntity.getDescription())) {
            throw new IllegalAccessException("Já existe um TODO com essa descrição");
        }
    }

    /**
     * Verifica se existe uma tarefa com a descrição fornecida.
     *
     * @param descricao a descrição a verificar
     * @return {@code true} se existe tarefa com essa descrição, {@code false} caso contrário
     */
    private Boolean existeTodoComEssaDescricao(String descricao) {
        return todoRepository.existsByDescription(descricao);
    }
}
