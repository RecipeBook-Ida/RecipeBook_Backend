package my.projects.recipebook.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Collection;

@Entity
@Getter
@Setter
public class IngredientQuantity implements Comparable<IngredientQuantity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 5)
    private Integer quantity;
    private String unit;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @JsonIgnore
    @ManyToMany(mappedBy = "groceryList")
    private Collection<AppUser> groceryList;


    @Override
    public int compareTo(IngredientQuantity other) {
        return this.getIngredient().getName().compareTo(other.getIngredient().getName());
    }
}
