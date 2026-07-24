package io.github.bortoletoeric.arquiteturaspring.todo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Serviço que implementa a lógica de negócio para gerenciamento de tarefas.
 *
 * <p>Este serviço é responsável por orquestrar as operações de CRUD (Create, Read, Update, Delete)
 * de tarefas (To Dos), aplicando validações de negócio, persistência de dados e notificações
 * por email. Utiliza injeção de dependência via construtor para integrar seus componentes.
 *
 * <p><b>Componentes utilizados:</b>
 * <ul>
 *     <li>{@link TodoRepository} - Acesso aos dados</li>
 *     <li>{@link TodoValidator} - Validação de regras de negócio</li>
 *     <li>{@link MailSender} - Notificações por email</li>
 * </ul>
 *
 * <p><b>Fluxo de operação:</b>
 * <ol>
 *     <li>Ao salvar, valida se já existe tarefa com mesma descrição</li>
 *     <li>Se válida, persiste no banco de dados</li>
 *     <li>Ao atualizar status, notifica via email sobre a mudança</li>
 * </ol>
 *
 * @author Eric Bortoleto
 * @version 1.0
 * @see TodoRepository
 * @see TodoValidator
 * @see MailSender
 * @see TodoEntity
 */
@Service
public class TodoService {

    private TodoRepository repository;
    private TodoValidator validator;
    public MailSender mailSender;

    /**
     * Construtor com injeção de dependências.
     *
     * @param todoRepository repositório para operações de persistência
     * @param todoValidator  validador de regras de negócio
     * @param mailSender     componente para envio de emails
     */
    public TodoService(TodoRepository todoRepository,
                       TodoValidator todoValidator,
                       MailSender mailSender) {
        this.repository = todoRepository;
        this.validator = todoValidator;
        this.mailSender = mailSender;
    }

    /**
     * Salva uma nova tarefa após validação.
     *
     * <p>Valida se já existe uma tarefa com a mesma descrição. Se a validação
     * falhar, lança uma exceção HTTP 409 (Conflict).
     *
     * @param novoTodo a tarefa a ser salva
     * @return a tarefa salva com seu identificador preenchido
     * @throws ResponseStatusException com status 409 se a descrição já existe
     * @see TodoValidator#validar(TodoEntity)
     */
    public TodoEntity salvar(TodoEntity novoTodo) {
        try {
            validator.validar(novoTodo);
        } catch (IllegalAccessException e) {
            System.out.println("Erro ao validar o TodoEntity: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return repository.save(novoTodo);
    }

    /**
     * Atualiza o status de uma tarefa e envia notificação por email.
     *
     * <p>Persiste a alteração do status no banco de dados e envia uma notificação
     * por email informando se a tarefa foi marcada como concluída ou não.
     *
     * @param todo a tarefa com o novo status
     * @see MailSender#sendMail(String)
     */
    public void atualizarStatus(TodoEntity todo) {
        repository.save(todo);
        String status = todo.getCompleted() == Boolean.TRUE ? "Concluido" : "Não concluido";
        mailSender.sendMail("Todo: " + todo.getDescription() + " foi atualizado para " + status);
    }

    /**
     * Busca uma tarefa pelo seu identificador.
     *
     * @param id o identificador da tarefa
     * @return a tarefa se encontrada, ou {@code null} caso contrário
     */
    public TodoEntity buscarPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
