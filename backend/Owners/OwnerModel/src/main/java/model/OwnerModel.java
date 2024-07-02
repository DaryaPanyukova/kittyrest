package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OwnerModel {
    private Long id;
    private String name;
    private LocalDate birthDate;

    public OwnerModel(Long id) {
        this.id = id;
    }
}
