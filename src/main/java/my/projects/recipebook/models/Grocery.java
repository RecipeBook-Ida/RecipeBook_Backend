package my.projects.recipebook.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Collection;

@Entity
@Getter
@Setter
public class Grocery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToMany
    @JoinTable(name = "quantities",
            joinColumns = @JoinColumn(name = "grocery_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_quantity_id"))
    private Collection<IngredientQuantity> quantities;

    @JsonIgnore
    @ManyToMany(mappedBy = "groceryList")
    private Collection<AppUser> groceryList;

}

