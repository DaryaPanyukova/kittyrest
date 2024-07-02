package dto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public record CatDto(Long id, String name, LocalDate birthDate, String breed, String color, byte[] image, Long ownerId,
                     List<CatDto> friends) {
    public CatDto {
        if (friends == null) {
            friends = Collections.emptyList();
        }
    }

    public CatDto(Long id) {
        this(id, null, null, null, null, null, null, Collections.emptyList());
    }
}