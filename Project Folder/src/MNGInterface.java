import java.util.List;

/**
 * The MNGInterface represents an interface for the management team to communicate with the kitchen system.
 */
public interface MNGInterface {

    /**
     * Retrieves menu details by ID.
     * 
     * @param menuID The ID of the menu.
     * @return The Menu object representing the menu details.
     */
    public Menu getMenu(int menuID);

    /**
     * Retrieves menu details by approval date.
     * 
     * @param approvalDate The date when the menu was approved.
     * @return The Menu object representing the menu details.
     */
    public Menu getMenuByDate(int approvalDate);

    /**
     * Retrieves a list of menus approved within a date range.
     * 
     * @param initialDate The initial date of the range.
     * @param endDate The end date of the range.
     * @return A list of Menu objects representing the menus approved within the specified date range.
     */
    public List<Menu> getMenuRange(int initialDate, int endDate);

    /**
     * Retrieves dish details by ID.
     * 
     * @param dishID The ID of the dish.
     * @return The Dish object representing the dish details.
     */
    public Dish getDish(int dishID);

    /**
     * Retrieves order details by ID.
     * 
     * @param orderID The ID of the order.
     * @return The Order object representing the order details.
     */
    public Order getOrder(int orderID);

    /**
     * Retrieves order details by order date.
     * 
     * @param orderDate The date when the order was created.
     * @return The Order object representing the order details.
     */
    public Order getOrderByDate(int orderDate);

    /**
     * Retrieves a list of orders created within a date range.
     * 
     * @param initialDate The initial date of the range.
     * @param endDate The end date of the range.
     * @return A list of Order objects representing the orders created within the specified date range.
     */
    public List<Order> getOrderRange(int initialDate, int endDate);
}
