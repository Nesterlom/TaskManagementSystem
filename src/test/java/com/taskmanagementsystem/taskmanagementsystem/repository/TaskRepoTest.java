package com.taskmanagementsystem.taskmanagementsystem.repository;

import com.taskmanagementsystem.taskmanagementsystem.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaskRepoTest {

    @Autowired
    private TaskRepo taskRepo;

    @Test
    public void findByIdTest(){
        Task task = Task
                .builder()
                .title("Task")
                .description("Test")
                .author("email@gmail.com")
                .build();
        taskRepo.save(task);

        Optional<Task> returnedTask = taskRepo.findById(1L);

        assertThat(returnedTask).isNotNull();
        assertThat(returnedTask.get().getTitle()).isEqualTo("Task");
    }

    //todo test with pageable
    @Test
    public void findAllTest(){
        Task task = Task
                .builder()
                .title("Task")
                .description("Test")
                .author("email@gmail.com")
                .build();

        Task task2 = Task
                .builder()
                .title("Task")
                .description("Test")
                .author("email@gmail.com")
                .build();

        taskRepo.save(task);
        taskRepo.save(task2);

        List<Task> tasks = taskRepo.findAll();

        assertThat(tasks).isNotNull();
        assertThat(tasks.size()).isEqualTo(2);
    }

    @Test
    public void saveTest(){
        Task task = Task
                .builder()
                .title("Task")
                .description("Test")
                .author("email@gmail.com")
                .build();

        Task savedTask = taskRepo.save(task);

        assertThat(savedTask).isNotNull();
    }
}
