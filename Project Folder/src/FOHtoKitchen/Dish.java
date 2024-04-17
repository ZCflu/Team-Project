package FOHtoKitchen;

public class Dish {
    private String name;
    private int quantity;
    private int price;

    /**
     * Front of House Dish class constructor. Takes in the name, quantity and price of the dish.
     * @see KitchenAPI,kitchenInterface,Order,KitchenGUI.Ticket
     * @param name
     * @param quantity
     * @param price
     */
    public Dish(String name, int quantity, int price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Getter for the name of a Dish.
     * @return A string (dish name).
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of a dish.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the quantity of a dish
     * @return An int (quantity).
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter for the quantity of a dish.
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter for the price of a dish.
     * @return An int (price).
     */
    public int getPrice(){
        return price;
    }

    /**
     * Setter for the price of a dish.
     * @param price
     */
    public void setPrice(int price){
        this.price = price;
    }
}
