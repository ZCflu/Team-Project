package KitchenInterfaceClasses;
import MenuClasses.*;
import StaffClasses.*;

import java.util.Vector;
//Done
public interface kitchenInterface {
    /**
     * @return return fresh stock ingredients details (received from the supplier) as the vector of ingredients
     */
    Vector<Ingredient> getFreshStock();
    /**
     * IMPLEMENTED
     * @return latest food menu
     */
    Menu getLatestFoodMenu();
    /**
     * UNDER CONSIDERATION
     * @return latest drink menu
     */
    Menu getLatestDrinkMenu();
    /**
     * IMPLEMENTED
     * @param dishID numeric dish ID
     * @return return dish details by dish ID
     */
    Dish getDishDetailsByDishID(int dishID);
    /**
     * UNDER DEVELOPMENT
     * @param startDate start date in format DDMMYY
     * @param endDate end date in format DDMMYY
     * @return return most popular dish withing date range, null if no data available
     */
    Dish getMostPopularDishWithingDateRange(int startDate, int endDate);
    /**
     * IMPLEMENTED
     * @param date date in format DDMMYY
     * @return return list of staff members working on this date (credetial field will be null),
     * null if no staff members are working on this date
     */
    Vector<StaffMember> getStaffMembersByDate(int date);

    /**
     * IMPLEMENTED
     * @param position position of the staff member
     * @return return list of staff members with indicated position (credetial field will be null),
     * null if no staff members with indicated position
     */
    Vector<StaffMember> getStaffMembersByPosition(String position);
    /**
     * IMPLEMENTED
     * @param login login of the staff member
     * @param password password of the staff member
     * @return return staff member with indicated credentials, null if credentials are incorrect
     */
    StaffMember getStaffMemberByCredentials(String login, String password);
    /**
     * IMPLEMENTED
     * @param ID numeric ID of the staff member
     * @return return staff member with indicated ID (credetial field will be null),
     * null if no staff member with indicated ID
     */
    StaffMember getStaffMemberByID(int ID);

}
