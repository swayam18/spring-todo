package com.orangeistehnewblack.repository;

import com.orangeistehnewblack.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    public List<Todo> findAllByOrderByIdAsc();
    public List<Todo> findAllByDone(boolean done);
    List<Todo> findAllByDoneOrderByIdAsc(boolean done);

    List<Todo> findAllByUserIdAndDoneOrderByIdAsc(long userId, boolean done);
    public List<Todo> findAllByUserIdOrderByIdAsc(long userId);
}
