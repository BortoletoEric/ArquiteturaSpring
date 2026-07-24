package io.github.bortoletoeric.arquiteturaspring.todo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para operações de persistência de tarefas.
 *
 * <p>Esta interface estende {@link JpaRepository} e fornece operações CRUD (Create, Read, Update, Delete)
 * para a entidade {@link TodoEntity}, bem como queries customizadas específicas do domínio.
 *
 * <p>Spring Data JPA gera automaticamente a implementação dessa interface em tempo de execução,
 * convertendo os métodos em consultas SQL apropriadas.
 *
 * <p><b>Queries customizadas:</b>
 * <ul>
 *     <li>{@link #existsByDescription(String)} - Verifica se existe tarefa com a descrição fornecida</li>
 * </ul>
 *
 * @author Eric Bortoleto
 * @version 1.0
 * @see TodoEntity
 * @see TodoService
 */
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
    
    /**
     * Verifica se existe uma tarefa com a descrição fornecida.
     *
     * <p>Este método é usado para validar se a descrição de uma nova tarefa
     * já existe no banco de dados, impedindo duplicações.
     *
     * @param description a descrição a ser verificada
     * @return {@code true} se existe tarefa com essa descrição, {@code false} caso contrário
     */
    boolean existsByDescription(String description);
}
