package thymeleaf.controller;

import org.springframework.ui.Model;
import thymeleaf.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        // Initialize some static data
        tasks.add(new Task(1, "Bamboo"));
        tasks.add(new Task(2, "Chicken"));
        tasks.add(new Task(3, "Bok Choy"));
        tasks.add(new Task(4, "Adios"));
        tasks.add(new Task(5, "Deja Vu"));
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping("/add")
    public String addTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "add";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task) {
        tasks.add(task);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable Integer id, Model model) {
        Task task = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (task == null) {
            // Handle case where task with given ID is not found
            return "redirect:/";
        }
        model.addAttribute("task", task);
        return "edit";
    }

    @PostMapping("/edit")
    public String editTask(@ModelAttribute Task updatedTask) {
        Integer id = updatedTask.getId(); // Retrieve the ID from the updatedTask object
        Task task = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (task == null) {
            return "redirect:/";
        }
        // Update task description
        task.setDescription(updatedTask.getDescription());
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Integer id) {
        Optional<Task> taskToRemove = tasks.stream().filter(task -> task.getId().equals(id)).findFirst();
        if (taskToRemove.isPresent()) {
            tasks.remove(taskToRemove.get());
        }
        return "redirect:/";
    }

}

