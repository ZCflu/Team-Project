package Management;

import Kitchen.Dish;
import Kitchen.Menu;
import Kitchen.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * The Management.MNGInterface represents an interface for the management team to communicate with the kitchen system.
 */
public interface MNGInterface {

    /**
     * Retrieves menu details by ID.
     * 
     * @param menuID The ID of the menu.
     * @return The Kitchen.Menu object representing the menu details.
     */
    public Menu getMenu(int menuID) throws SQLException;

    /**
     * Retrieves menu details by approval date.
     * 
     * @param approvalDate The date when the menu was approved.
     * @return The Kitchen.Menu object representing the menu details.
     */
    public Menu getMenuByDate(int approvalDate) throws SQLException;

    /**
     * Retrieves a list of menus approved within a date range.
     * 
     * @param initialDate The initial date of the range.
     * @param endDate The end date of the range.
     * @return A list of Kitchen.Menu objects representing the menus approved within the specified date range.
     */
    public List<Menu> getMenuRange(int initialDate, int endDate) throws SQLException;

    /**
     * Retrieves dish details by ID.
     * 
     * @param dishID The ID of the dish.
     * @return The Kitchen.Dish object representing the dish details.
     */
    public Dish getDish(int dishID) throws SQLException;

    /**
     * Retrieves order details by ID.
     * 
     * @param orderID The ID of the order.
     * @return The Kitchen.Order object representing the order details.
     */
    public Order getOrder(int orderID) throws SQLException;

    /**
     * Retrieves order details by order date.
     * 
     * @param orderDate The date when the order was created.
     * @return The Kitchen.Order object representing the order details.
     */
    public Order getOrderByDate(int orderDate) throws SQLException;

    /**
     * Retrieves a list of orders created within a date range.
     * 
     * @param initialDate The initial date of the range.
     * @param endDate The end date of the range.
     * @return A list of Kitchen.Order objects representing the orders created within the specified date range.
     */
    public List<Order> getOrderRange(int initialDate, int endDate) throws SQLException;
}
