package entity;

import enums.CatColor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cats")
@NoArgsConstructor
@AllArgsConstructor
public class CatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "breed")
    private String breed;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private CatColor color;

    @Column
    private byte[] image;

    @JoinColumn(name = "owner_id")
    private Long ownerId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cat_friends", joinColumns = @JoinColumn(name = "cat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private List<CatEntity> friends = null;

    public CatEntity(String name, LocalDate birthDate, String breed, CatColor color, byte[] image, Long ownerId,
                     List<CatEntity> friends) {
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.color = color;
        this.image = image;
        this.ownerId = ownerId;
        this.friends = friends;
    }
}
