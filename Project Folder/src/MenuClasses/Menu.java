package MenuClasses;

import java.util.Vector;

public class Menu {
    private final char IDType = 'M';
    private int ID;
    private Boolean isApproved;
    private Vector<Dish> dishes;
    /**
     * Date in format DDMMYY
     */
    private int creationDate;
    private int chefApprovalDate;

    public Menu(int ID, int creationDate, int chefApprovalDate) {
        this.ID = ID;
        this.creationDate = creationDate;
        this.chefApprovalDate = chefApprovalDate;
        this.dishes = new Vector<>();
        this.isApproved = false;
    }
    public char getIDType() {
        return IDType;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }
    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public String getIDAsString(){
        return this.IDType + "-" + Integer.toString(this.ID);
    }

    public Vector<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Vector<Dish> dishes) {
        this.dishes = dishes;
    }

    public void addDish(Dish dish){
        this.dishes.add(dish);
    }
    public void removeDish(Dish dish){
        this.dishes.remove(dish);
    }
    public void removeDishByName(String dishName){
        dishes.removeIf(dish -> dish.getName().equals(dishName));
    }
    public void removeDishByID(int dishID){
        dishes.removeIf(dish -> dish.getID() == dishID);
    }

    public int getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    public int getChefApprovalDate() {
        return chefApprovalDate;
    }

    public void setChefApprovalDate(int chefApprovalDate) {
        this.chefApprovalDate = chefApprovalDate;
    }

    public String getChefApprovalDateAsString(){
        return this.chefApprovalDate % 100 + "/" +
                (this.chefApprovalDate / 100) % 100 + "/" +
                this.chefApprovalDate / 10000;
    }

    public String getCreationDateAsString(){
        return this.creationDate % 100 + "/" +
                (this.creationDate / 100) % 100 + "/" +
                this.creationDate / 10000;
    }

    public void calculatePrices(double multiplier){
        for (Dish dish : dishes){
            dish.calculatePrice(multiplier);
        }
    }
}
