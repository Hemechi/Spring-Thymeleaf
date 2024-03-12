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
    public String editTaskForm(@PathVariable Long id, Model model) {
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
        Long id = updatedTask.getId(); // Retrieve the ID from the updatedTask object
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
    public String deleteTask(@PathVariable Long id) {
        Optional<Task> taskToRemove = tasks.stream().filter(task -> task.getId().equals(id)).findFirst();
        if (taskToRemove.isPresent()) {
            tasks.remove(taskToRemove.get());
        }
        return "redirect:/";
    }

}

