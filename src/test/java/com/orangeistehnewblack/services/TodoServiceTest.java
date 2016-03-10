package com.orangeistehnewblack.services;

import com.orangeistehnewblack.models.Todo;
import com.orangeistehnewblack.models.User;
import com.orangeistehnewblack.repository.TodoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {
    @Mock private TodoRepository mockTodoRepository;
    @InjectMocks private TodoService todoService = new TodoService();

    @Test
    public void testGetTodoList() throws Exception {
        User currentUser = new User();
        List<Todo> pendingTodos = mock(List.class);
        when(mockTodoRepository.findAllByUserIdAndDoneOrderByIdAsc(currentUser.getId(), false)).thenReturn(pendingTodos);
        List<Todo> completedTodos = mock(List.class);
        when(mockTodoRepository.findAllByUserIdAndDoneOrderByIdAsc(currentUser.getId(), true)).thenReturn(completedTodos);
        List<Todo> allTodos = mock(List.class);
        when(mockTodoRepository.findAllByUserIdOrderByIdAsc(currentUser.getId())).thenReturn(allTodos);

        assertThat(todoService.getTodoList(currentUser, "pending"), is(pendingTodos));
        assertThat(todoService.getTodoList(currentUser, "completed"), is(completedTodos));
        assertThat(todoService.getTodoList(currentUser, "all"), is(allTodos));
    }

    @Test
    public void testAddTodo() throws Exception {
        Todo newTodo = new Todo();
        todoService.addTodo(newTodo);
        verify(mockTodoRepository).save(newTodo);
    }

    @Test
    public void testSetDone() throws Exception {
        long todo_id = 1234567890L;
        Todo currentTodo = new Todo();
        currentTodo.setDone(false);

        when(mockTodoRepository.findOne(todo_id)).thenReturn(currentTodo);

        todoService.setDone(todo_id, true);

        assertThat(currentTodo.getDone(), is(true));

        verify(mockTodoRepository).findOne(todo_id);
        verify(mockTodoRepository).save(currentTodo);
    }

    @Test
    public void testDeleteToDo() throws Exception {
        long todo_id = 1234567890L;

        todoService.deleteTodo(todo_id);

        verify(mockTodoRepository).delete(todo_id);
    }

    @Test
    public void testDeleteCompleted() throws Exception {
        List<Todo> completedTodos = mock(List.class);
        when(mockTodoRepository.findAllByDone(true)).thenReturn(completedTodos);

        todoService.deleteCompleted();

        verify(mockTodoRepository).deleteInBatch(completedTodos);
    }
}
