package my.projects.recipebook.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import my.projects.recipebook.models.dto.ingredient.IngredientQuantityDTO;
import net.minidev.json.annotate.JsonIgnore;

import java.util.*;

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

    @ManyToMany
    @JoinTable(name = "groceryList",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_quantity_id"))
    private List<IngredientQuantity> groceryList;

    public Map<Ingredient, List<IngredientQuantity>> sortGroceryList(List<IngredientQuantity> groceryList){
        List<IngredientQuantity> sortedGroceryList = new ArrayList<>(groceryList);
        Collections.sort(sortedGroceryList);

        Map<Ingredient, List<IngredientQuantity>> groupedGroceries = new HashMap<>();
        for (IngredientQuantity ingredientQuantity : sortedGroceryList) {
            Ingredient ingredient = ingredientQuantity.getIngredient();
            if (!groupedGroceries.containsKey(ingredient)) {
                groupedGroceries.put(ingredient, new ArrayList<>());
            }
            groupedGroceries.get(ingredient).add(ingredientQuantity);
        }

        return groupedGroceries;

    }
}
