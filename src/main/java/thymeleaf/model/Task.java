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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

