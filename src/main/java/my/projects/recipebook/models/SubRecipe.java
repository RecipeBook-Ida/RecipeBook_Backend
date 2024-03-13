package my.projects.recipebook.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Collection;
import java.util.List;

/**
 * Domain class (entity) to represent a sub Recipe.
 * Includes an auto generated key and some validation.
 * Relationships are configured as default.
 * Is using lombok for getters and setters.
 */
@Entity
@Getter
@Setter
public class SubRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 1000)
    private String instructions;

    @ManyToMany
    @JoinTable(name = "recipe_ingredients",
            joinColumns = @JoinColumn(name = "sub_recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_quantity_id"))
    private Collection<IngredientQuantity> ingredients;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "main_recipe_id")
    private Recipe mainRecipe;

}
