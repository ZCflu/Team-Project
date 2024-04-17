package Kitchen;
//Done
/**
 * The Kitchen.Ingredient class represents an ingredient used in recipes.
 */
public class Ingredient {
    private int ingredientID; // Unique identifier for the ingredient
    private char IDType; // Type identifier ('I' for ingredient)
    private String ingredientName; // Name of the ingredient
    private double quantity; // Quantity of the ingredient

    /**
     * Constructor to initialize an Kitchen.Ingredient object with provided details.
     * 
     * @param ingredientID Unique identifier for the ingredient.
     * @param ingredientName Name of the ingredient.
     * @param quantity Quantity of the ingredient.
     */
    public Ingredient(int ingredientID, String ingredientName, double quantity) {
        this.ingredientID = ingredientID;
        this.IDType = 'I'; // Setting IDType to 'I' for ingredient
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    /**
     * Getter method to retrieve the name of the ingredient.
     * 
     * @return Name of the ingredient.
     */
    public String getIngredientName() {
        return this.ingredientName;
    }

    /**
     * Getter method to retrieve the quantity of the ingredient.
     * 
     * @return Quantity of the ingredient.
     */
    public double getQuantity() {
        return this.quantity;
    }

    /**
     * Getter method to retrieve the ID of the ingredient.
     * 
     * @return Unique identifier for the ingredient.
     */
    public int getIngredientID() {
        return this.ingredientID;
    }

    /**
     * Getter method to retrieve the type identifier of the ingredient.
     * 
     * @return Type identifier ('I' for ingredient).
     */
    public char getIDType() {
        return IDType;
    }
}
