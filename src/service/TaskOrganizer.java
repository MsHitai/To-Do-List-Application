package src.service;

import src.interfaces.Organizable;
import src.model.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TaskOrganizer implements Organizable {

    private int id;

    protected final Map<Integer, Task> tasks = new HashMap<>();

    private int createId() {
        return ++id;
    }


    @Override
    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }
    @Override
    public Task createTask(String description, String date) {
        Task task = new Task();
        if (tasks.isEmpty()) {
            task.setId(createId());
        } else {
            id = tasks.size();
            task.setId(createId());
        }
        task.setDescription(description);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd. MM. yyyy");
        try {
            task.setDueDate(LocalDate.parse(date, dtf));
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
        task.setDone(false);
        return task;
    }

    public List<Task> getTasks() {
        List<Task> tasksToReturn = new ArrayList<>();
        for (Integer id : tasks.keySet()) {
            tasksToReturn.add(tasks.get(id));
        }
        return tasksToReturn;
    }

    @Override
    public void assignDeadLine(int id, String dueDate) {
        if (tasks.containsKey(id)) {
            Task task = tasks.get(id);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd. MM. yyyy");
            try {
                task.setDueDate(LocalDate.parse(dueDate, dtf));
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Под таким номером нет задачи.");
        }
    }

    @Override
    public void markAsDone(int id) {
        if (tasks.containsKey(id)) {
            Task task = tasks.get(id);
            task.setDone(true);
        } else {
            System.out.println("Под таким номером нет задачи.");
        }
    }

    @Override
    public void removeTask(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else {
            System.out.println("Под таким номером нет задачи.");
        }
    }

    @Override
    public void changeTask(int id, String description) {
        if (tasks.containsKey(id)) {
            Task task = tasks.get(id);
            task.setDescription(description);
        } else {
            System.out.println("Под таким номером нет задачи.");
        }
    }
}
