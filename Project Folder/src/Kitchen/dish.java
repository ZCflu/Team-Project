package Kitchen;

/**
 * The Kitchen.Dish class represents a dish in the restaurant menu.
 * It contains information about the dish ID, availability status, name, and associated recipe.
 */
public class dish {
    private int dishID; // Unique identifier for the dish
    private int dishAvailability_status_id; // Availability status of the dish
    private String dishName; // Name of the dish
    private Recipe recipe; // Kitchen.Recipe object representing the ingredients and preparation instructions for the dish

    /**
     * Constructor to initialize a Kitchen.Dish object with provided dish ID, name, and recipe.
     * 
     * @param dishID Unique identifier for the dish.
     * @param dishName Name of the dish.
     * @param recipe Kitchen.Recipe object representing the ingredients and preparation instructions for the dish.
     */
    public dish(int dishID, String dishName, Recipe recipe) {
        this.dishID = dishID;
        this.dishName = dishName;
        this.recipe = recipe;
    }

    /**
     * Getter method to retrieve the recipe associated with the dish.
     * 
     * @return Kitchen.Recipe object representing the ingredients and preparation instructions for the dish.
     */
    public Recipe getRecipe() {
        return this.recipe;
    }

    /**
     * Getter method to retrieve the name of the dish.
     * 
     * @return Name of the dish.
     */
    public String getDishName() {
        return this.dishName;
    }

    /**
     * Getter method to retrieve the ID of the dish.
     * 
     * @return Unique identifier for the dish.
     */
    public int getDishID() {
        return this.dishID;
    }

    /**
     * Setter method to set the availability status of the dish.
     * 
     * @param availability Availability status of the dish.
     */
    public void setDishAvailability(int availability) {
        this.dishAvailability_status_id = availability;
    }

    /**
     * Getter method to retrieve the availability status of the dish.
     * 
     * @return Availability status of the dish.
     */
    public int getDishAvailability() {
        return this.dishAvailability_status_id;
    }
}
