/**
 * The Recipe class represents a recipe for a dish, consisting of ingredients.
 */
public class Recipe {
    private int recipeID; // Unique identifier for the recipe
    private String recipeName; // Name of the recipe
    private Ingredient[] ingredients; // Array of Ingredient objects representing the ingredients in the recipe

    /**
     * Constructor to initialize a Recipe object with provided details.
     * 
     * @param recipeID Unique identifier for the recipe.
     * @param recipeName Name of the recipe.
     * @param ingredients Array of Ingredient objects representing the ingredients in the recipe.
     */
    public Recipe(int recipeID, String recipeName, Ingredient[] ingredients) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
    }

    /**
     * Getter method to retrieve the ID of the recipe.
     * 
     * @return Unique identifier for the recipe.
     */
    public int getRecipeID() {
        return this.recipeID;
    }

    /**
     * Getter method to retrieve the name of the recipe.
     * 
     * @return Name of the recipe.
     */
    public String getRecipeName() {
        return this.recipeName;
    }

    /**
     * Getter method to retrieve the array of ingredients in the recipe.
     * 
     * @return Array of Ingredient objects representing the ingredients in the recipe.
     */
    public Ingredient[] getIngredients() {
        return this.ingredients;
    }
}
