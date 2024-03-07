package my.projects.recipebook.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Set;

/**
 * Domain class (entity) to represent a User.
 * Includes an auto generated key and some validation.
 * Relationships are configured as default.
 * Is using lombok for getters and setters.
 */
@Entity
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String username;
    @Column(length = 50)
    private String firstname;
    @Column(length = 50)
    private String lastname;

    @JsonIgnore
    @OneToMany(mappedBy = "appUser")
    private Set<Recipe> recipes;
}
