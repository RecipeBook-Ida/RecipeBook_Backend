package my.projects.recipebook.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDate;
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
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 500, nullable = false)
    private String description;
    @Column(length = 5)
    private Integer cooktime;
    @Column(length = 350)
    private String image;
    @Column(length = 50)
    private String cuisine;
    @Column(length = 50)
    private String type;
    private Integer portion;

    @OneToMany
    @JoinColumn(name = "recipe_id")
    private List<SubRecipe> subRecipes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;
}
