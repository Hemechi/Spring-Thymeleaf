package thymeleaf.model;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String description;
    // getters and setters
}

