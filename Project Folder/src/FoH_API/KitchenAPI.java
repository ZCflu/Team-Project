package FoH_API;

import FoH_API.Dish;
import FoH_API.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KitchenAPI implements kitchenInterface{

    // implemented
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

    private Connection getConnection() throws SQLException {
        // Database credentials
        String url = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2033t16";
        String user = "in2033t16_d";
        String password = "X4wgPBbSY_c";

        // Establish and return a connection to the database
        return DriverManager.getConnection(url, user, password);
    }

    // not implemented
    @Override
    public Order updateBookingStatus() {
        return null;
    }
}
