package io.github.bortoletoeric.arquiteturaspring.todo;

import org.springframework.web.bind.annotation.*;

/**
 * REST Controller para operações CRUD de tarefas.
 *
 * <p>Este controlador expõe endpoints HTTP para gerenciar tarefas (To Dos) através
 * de requisições REST. Todos os endpoints trabalham com representação JSON.
 *
 * <p><b>Endpoint base:</b> {@code /todos}
 *
 * <p><b>Operações suportadas:</b>
 * <ul>
 *     <li>POST /todos - Criar nova tarefa</li>
 *     <li>GET /todos/{id} - Buscar tarefa por ID</li>
 *     <li>PUT /todos/{id} - Atualizar status da tarefa</li>
 * </ul>
 *
 * @author Eric Bortoleto
 * @version 1.0
 * @see TodoService
 * @see TodoEntity
 */
@RestController
@RequestMapping("todos")
public class TodoController {

    private TodoService todoService;

    /**
     * Construtor com injeção de dependência.
     *
     * @param todoService o serviço de tarefas
     */
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Cria uma nova tarefa.
     *
     * <p><b>Exemplo de requisição:</b>
     * <pre>
     * POST /todos
     * Content-Type: application/json
     *
     * {
     *   "description": "Estudar Spring Boot",
     *   "completed": false
     * }
     * </pre>
     *
     * @param todo os dados da tarefa a ser criada
     * @return a tarefa criada com seu ID preenchido
     * @throws org.springframework.web.server.ResponseStatusException com status 409 se descrição já existe
     */
    @PostMapping
    public TodoEntity salvar(@RequestBody TodoEntity todo) {
        return this.todoService.salvar(todo);
    }

    /**
     * Atualiza o status (concluída/não concluída) de uma tarefa existente.
     *
     * <p><b>Exemplo de requisição:</b>
     * <pre>
     * PUT /todos/1
     * Content-Type: application/json
     *
     * {
     *   "description": "Estudar Spring Boot",
     *   "completed": true
     * }
     * </pre>
     *
     * <p>Uma notificação por email será enviada informando a mudança de status.
     *
     * @param id o identificador da tarefa a atualizar
     * @param todo os dados da tarefa com o novo status
     */
    @PutMapping("{id}")
    public void atualizarStatus(
            @PathVariable Integer id,
            @RequestBody TodoEntity todo
    ) {
        todo.setId(id);
        todoService.atualizarStatus(todo);
    }

    /**
     * Busca uma tarefa pelo seu identificador.
     *
     * <p><b>Exemplo de requisição:</b>
     * <pre>
     * GET /todos/1
     * </pre>
     *
     * @param id o identificador da tarefa
     * @return a tarefa encontrada, ou {@code null} se não existe
     */
    @GetMapping("{id}")
    public TodoEntity buscar(@PathVariable Integer id) {
        return todoService.buscarPorId(id);
    }
}
