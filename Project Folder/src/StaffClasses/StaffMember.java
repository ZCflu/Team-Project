package StaffClasses;

import java.util.Random;
import java.util.Vector;

public class StaffMember {
    private final char IDType = 'S';
    private int ID;
    private String name;
    private String surname;
    private String  position;
    private String email;
    private loginCredentials loginCredentials;
    private Vector<StaffAviliability> staffAviliability;


    public StaffMember() {
        this.ID = new Random().nextInt(1000);
        this.staffAviliability = new Vector<>();
        this.loginCredentials = null;
    }    // No Complex data structures constructor
    public StaffMember(int ID, String name, String surname, String position, String email) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.email = email;
        this.staffAviliability = new Vector<>();
        this.loginCredentials = null;
    }

    // Full constructor
    public StaffMember(int ID, String name, String surname, String position, String email, loginCredentials loginCredentials,
                       Vector<StaffAviliability> staffAviliability) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.email = email;
        this.loginCredentials = loginCredentials;
        this.staffAviliability = staffAviliability;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public loginCredentials getLoginCredentials() {
        return loginCredentials;
    }

    public void setLoginCredentials(loginCredentials loginCredentials) {
        this.loginCredentials = loginCredentials;
    }

    public Vector<StaffAviliability> getStaffAviliability() {
        return staffAviliability;
    }

    public void setStaffAviliability(Vector<StaffAviliability> staffAviliability) {
        this.staffAviliability = staffAviliability;
    }
    public void addStaffAviliability(StaffAviliability staffAviliability) {
        this.staffAviliability.add(staffAviliability);
    }
    public void removeStaffAviliability(StaffAviliability staffAviliability) {
        this.staffAviliability.remove(staffAviliability);
    }
    public void removeStaffAviliabilityByDate(int date) {
        this.staffAviliability.removeIf(staffAviliability -> staffAviliability.getDate() == date);
    }
}
