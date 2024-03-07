package my.projects.recipebook.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Collection;
import java.util.List;
/**
 * Domain class (entity) to represent a Recipe.
 * Includes an auto generated key and some validation.
 * Relationships are configured as default.
 * Is using lombok for getters and setters.
 */
@Entity
@Getter
@Setter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 350)
    private String image;
    @Column(length = 50)
    private String category;
}
