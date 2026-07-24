package io.github.bortoletoeric.arquiteturaspring.todo;

import jakarta.persistence.*;

/**
 * Entidade JPA que representa uma tarefa (To Do) no sistema.
 *
 * <p>Esta classe mapeia a tabela {@code tb_todo} do banco de dados e encapsula
 * os dados de uma tarefa, incluindo identificação, descrição e status de conclusão.
 * É gerenciada pelo contexto de persistência do Spring Data JPA.
 *
 * <p><b>Exemplo de uso:</b>
 * <pre>
 *     TodoEntity todo = new TodoEntity();
 *     todo.setDescription("Estudar Spring Boot");
 *     todo.setCompleted(false);
 *     TodoEntity saved = todoRepository.save(todo);
 * </pre>
 *
 * @author Eric Bortoleto
 * @version 1.0
 * @see TodoService
 * @see TodoRepository
 */
@Entity
@Table(name = "tb_todo")
public class TodoEntity {

    /**
     * Identificador único da tarefa.
     * Gerado automaticamente pelo banco de dados com estratégia de identity.
     */
    @Id()
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Descrição da tarefa.
     */
    @Column(name = "description")
    private String description;

    /**
     * Flag indicando se a tarefa foi concluída.
     */
    @Column(name = "fl_completed")
    private Boolean completed;

    /**
     * Obtém o identificador único da tarefa.
     *
     * @return o ID da tarefa, ou {@code null} se não foi persistida
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador único da tarefa.
     *
     * @param id o novo ID da tarefa
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtém a descrição da tarefa.
     *
     * @return a descrição da tarefa
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define a descrição da tarefa.
     *
     * @param description a nova descrição da tarefa
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtém o status de conclusão da tarefa.
     *
     * @return {@code true} se a tarefa foi concluída, {@code false} caso contrário
     */
    public Boolean getCompleted() {
        return completed;
    }

    /**
     * Define o status de conclusão da tarefa.
     *
     * @param completed {@code true} para marcar como concluída, {@code false} caso contrário
     */
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
