package thymeleaf.model;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Integer id;
    private String description;
    // getters and setters
}

