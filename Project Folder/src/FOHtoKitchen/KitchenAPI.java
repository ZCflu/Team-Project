package FOHtoKitchen;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API from the front of house. Allows connection to their database to retrieve data on Orders.
 */

public class KitchenAPI implements kitchenInterface{

    /**
     * Getter for a list of orders that are currently pending in the database.
     * @return A list of Orders objects that represent current orders.
     */
    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT Orders.*, Order_details.quantity, Dishes.* " +
                "FROM Orders " +
                "JOIN Order_details ON Orders.orderID = Order_details.orderID " +
                "JOIN Dishes ON Order_details.dishID = Dishes.dishID " +
                "WHERE Orders.orderStatus = 'Pending' AND Dishes.dishType <> 4";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            Map<Integer, Order> orderMap = new HashMap<>();

            while (rs.next()) {
                int orderId = rs.getInt("orderId");
                Order order = orderMap.getOrDefault(orderId, new Order(0,"", 0));
                order.setOrderID(orderId);
                order.setTableNo(rs.getInt("tableNumber"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setAdditionalInformation(rs.getString("additionalInformation"));

                Dish dish = new Dish("", 0, 0);
                dish.setName(rs.getString("dishName"));
                // Set other dish properties
                int quantity = rs.getInt("quantity");

                Map<Dish, Integer> dishes = order.getItems();
                if (dishes == null) {
                    dishes = new HashMap<>();
                    order.setItems(dishes);
                }
                dishes.put(dish, quantity);

                orderMap.put(orderId, order);
            }

            orders.addAll(orderMap.values());
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        return orders;
    }

    /**
     * Creates a connection to the Front of House database.
     * @return A connection object.
     * @throws SQLException
     */

    private Connection getConnection() throws SQLException {
        // Database credentials
        String url = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t16";
        String user = "in2033t16_d";
        String password = "X4wgPBbSY_c";

        // Establish and return a connection to the database
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Updates the status of an Order on the database.
     * (Not implemented).
     * @return Currently returns null. When implemented, returns an Order object.
     */
    @Override
    public Order updateBookingStatus() {
        return null;
    }
}
