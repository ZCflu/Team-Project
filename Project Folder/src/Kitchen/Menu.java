package Kitchen;

import java.util.List;

/**
 * The Kitchen.Menu class represents a menu in the restaurant.
 */
public class Menu {
    private int menuID; // Unique identifier for the menu
    private int creationDate; // Date when the menu was created
    private int chefApprovalDate; // Date when the menu was approved by the chef
    private boolean headChefApproval; // Indicates approval status by the head chef
    private boolean sousChefApproval; // Indicates approval status by the sous chef
    private boolean chefApproval; // Indicates overall approval status by the chef
    private List<dish> dishes; // Array of Kitchen.Dish objects representing the dishes on the menu

    /**
     * Constructor to initialize a Kitchen.Menu object with provided details.
     *
     * @param menuID Unique identifier for the menu.
     * @param creationDate Date when the menu was created.
     * @param dishes Array of Kitchen.Dish objects representing the dishes on the menu.
     */
    public Menu(int menuID, int creationDate, List<dish> dishes) {
        this.menuID = menuID;
        this.creationDate = creationDate;
        this.dishes = dishes;
    }

    /**
     * Getter method to retrieve the ID of the menu.
     *
     * @return Unique identifier for the menu.
     */
    public int getMenuID() {
        return this.menuID;
    }

    /**
     * Method to check if the menu is approved by the chef.
     *
     * @return true if the menu is approved by the chef, false otherwise.
     */
    public boolean checkApproval() {
        return this.chefApproval;
    }

    /**
     * Getter method to retrieve the array of dishes on the menu.
     *
     * @return Array of Kitchen.Dish objects representing the dishes on the menu.
     */
    public List<dish> getDishes() {
        return this.dishes;
    }

    /**
     * Getter method to retrieve the approval date by the chef.
     *
     * @return Date when the menu was approved by the chef.
     */
    public int getChefApprovalDate() {
        return this.chefApprovalDate;
    }

    /**
     * Getter method to retrieve the creation date of the menu.
     *
     * @return Date when the menu was created.
     */
    public int getCreationDate() {
        return this.creationDate;
    }
}
