package MenuClasses;

import java.util.HashSet;
import java.util.Set;

public class Dish {
    private final char IDType = 'D';
    private int ID;
    private String name;
    private Recipe recipe;
    private double price;
    /**
     * AllergenInfo represents the allergens that the dish contains. (Gluten, lactose, nuts, etc.)
     */
    private Set<String> AllergenInfo;
    private Wine wineParing;
    /**
     * type reprsents to which section of the menu the dish belongs to. (First course, starters
     * appetizers, main course, desserts, etc.)
     */
    private String type;

    public Dish(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.AllergenInfo = new HashSet<>();
    }
    public Dish(int ID){
        this.ID = ID;
        this.AllergenInfo = new HashSet<>();
    }

    public Dish(String name, Recipe recipe, Wine wineParing, String type) {
        this.name = name;
        this.recipe = recipe;
        this.AllergenInfo = new HashSet<>();
        this.wineParing = wineParing;
        this.type = type;
    }
    public Dish(String name, Recipe recipe, HashSet<String> allergenInfo,
                Wine wineParing, String type) {
        this.name = name;
        this.recipe = recipe;
        this.AllergenInfo = allergenInfo;
        this.wineParing = wineParing;
        this.type = type;
    }
    public char getIDType() {
        return IDType;
    }

    public int getID() {
        return ID;
    }

    public String getIDAsString(){
        return this.IDType + "-" + Integer.toString(this.ID);
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void calculatePrice(double multiplier){
        double TmpPrice = 0;
        for (Ingredient ingredient : recipe.getIngredients().keySet()){
            TmpPrice += ingredient.getPrice() * recipe.getIngredients().get(ingredient);
        }
        this.price = TmpPrice * multiplier;
    }
    public Set<String> getAllergenInfo() {
        return AllergenInfo;
    }

    public String getAllergenInfoAsString(){
        StringBuilder allergenInfo = new StringBuilder();
        for (String allergen : AllergenInfo){
            allergenInfo.append(allergen).append(" |");
        }
        return allergenInfo.toString();
    }
    public void addAllergenInfo(String allergen){
        this.AllergenInfo.add(allergen);
    }

    public void clearAllergenInfo(){
        this.AllergenInfo.clear();
    }
    public void setAllergenInfo(HashSet<String> allergenInfo) {
        AllergenInfo = allergenInfo;
    }

    public Wine getWineParing() {
        return wineParing;
    }

    public void setWineParing(Wine wineParing) {
        this.wineParing = wineParing;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
