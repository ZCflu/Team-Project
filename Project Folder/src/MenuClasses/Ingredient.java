package MenuClasses;

/**
 * Class that represents an ingredient.
 * Wines also represent an ingredient, they do not have independent representation, as they
 * can be ordered from the same supplier as the rest of the ingredients.
 */
public class Ingredient {

    private final char IDType = 'I';
    private int ID;
    private String name;
    private double price;

    public Ingredient(int ID, String name, double price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
