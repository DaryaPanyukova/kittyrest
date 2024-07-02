package model;

import enums.CatColor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class CatModel {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String breed;
    private CatColor color;
    private byte[] image;
    private Long ownerId;
    private List<CatModel> friends;

    public CatModel(Long id) {
        this.id = id;
    }
}