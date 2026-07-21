package io.github.bortoletoeric.arquiteturaspring.todo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public TodoEntity salvar(@RequestBody TodoEntity todo) {
        return this.todoService.salvar(todo);
    }

    @PutMapping("{id}")
    public void atualizarStatus(
            @PathVariable Integer id,
            @RequestBody TodoEntity todo
    ) {
        todo.setId(id);
        todoService.atualizarStatus(todo);
    }

    @GetMapping("{id}")
    public TodoEntity buscar(@PathVariable Integer id) {
        return todoService.buscarPorId(id);
    }

}
