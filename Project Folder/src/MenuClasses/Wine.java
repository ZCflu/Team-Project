package MenuClasses;

public class Wine {
    private final char IDType = 'W';
    private int ID;
    private String name;
    private String type;
    private int productionYear;
    private double price;

    public Wine(int ID) {
        this.ID = ID;
    }


    public Wine(int ID, String name, String type, int productionYear, double price) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.productionYear = productionYear;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
