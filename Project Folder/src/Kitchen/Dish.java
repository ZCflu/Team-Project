package Kitchen;

/**
 * The Kitchen.Dish class represents a dish in the restaurant menu.
 * It contains information about the dish ID, availability status, name, and associated recipe.
 */
public class Dish {
    private int dishID; // Unique identifier for the dish
    private int dishAvailability_status_id; // Availability status of the dish
    private String dishName; // Name of the dish

    private String dishType;
    private int recipeID; // Kitchen.Recipe object representing the ingredients and preparation instructions for the dish

    /**
     * Constructor to initialize a Kitchen.Dish object with provided dish ID, name, and recipe.
     * 
     * @param dishID Unique identifier for the dish.
     * @param dishName Name of the dish.
     */
    public Dish(int dishID, String dishName, String dishType, int recipieID) {
        this.dishID = dishID;
        this.dishName = dishName;
        this.dishType = dishType;
        this.recipeID = recipieID;

    }

    /**
     * Getter method to retrieve the recipe associated with the dish.
     * 
     * @return Kitchen.Recipe object representing the ingredients and preparation instructions for the dish.
     */


    public String getDishType() {
        return this.dishType;
    }

    public int getRecipeID(){return this.recipeID;}

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

    public void deductAvailability(int sub){

        this.dishAvailability_status_id = this.dishAvailability_status_id - sub;
    }
}

