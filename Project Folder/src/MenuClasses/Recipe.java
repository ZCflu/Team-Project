package MenuClasses;

import java.util.HashMap;
import java.util.Random;

public class Recipe {
    private final char IDType = 'R';
    private int ID;
    /**
     * Name of the dish author.
     */
    private String createdBy;
    /**
     * HashMap of ingredients and their quantities.
     */
    private HashMap<Ingredient, Double> ingredients;

    public Recipe(String createdBy, HashMap<Ingredient, Double> ingredients) {
        this.createdBy = createdBy;
        this.ingredients = ingredients;
    }
    public Recipe(int ID, String createdBy) {
        this.createdBy = createdBy;
        this.ID = ID;
        this.ingredients = new HashMap<>();
    }
    public Recipe(String createdBy) {
        this.createdBy = createdBy;
        this.ID = new Random().nextInt(1000);
        this.ingredients = new HashMap<>();
    }

    public void addIngredient(Ingredient ingredient, double quantity){
        ingredients.put(ingredient, quantity);
    }

    public void removeIngredient(Ingredient ingredient){
        ingredients.remove(ingredient);
    }

    public void removeIngredientByName(String ingredientName){
        this.ingredients.keySet().removeIf(ingredient -> ingredient.getName().equals(ingredientName));
    }
    public void removeIngredientByID(int ingredientID){
        this.ingredients.keySet().removeIf(ingredient -> ingredient.getID() == ingredientID);
    }

    public HashMap<Ingredient, Double> getIngredients(){
        return ingredients;
    }

    public void setIngredients(HashMap<Ingredient, Double> ingredients){
        this.ingredients = ingredients;
    }
    public void clearIngredients(){
        this.ingredients.clear();
    }
    public char getIDType() {
        return IDType;
    }

    public int getID() {
        return ID;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public String setCreatedBy(String createdBy) {
        return this.createdBy = createdBy;
    }
}
