package Kitchen;

import DatabaseConnection.databaseAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


import FoH_API.KitchenAPI;
import FoH_API.Order;
import KitchenGUI.OrderMenu;
import KitchenGUI.Ticket;

public class OrderManagement {

    KitchenAPI kp = new KitchenAPI();

    private Connection con;
    private databaseAdmin database;
    private void closeConnection() throws SQLException {
        database.endConnection(con);
    }
    private void startConnection() throws SQLException {
        database = new databaseAdmin();
        con = new databaseAdmin().returnConnection();
    }



    public void getOrder()
    {
        kp.getOrders();

    }

    public void addOrder() throws SQLException
    {

        startConnection();

        List<Order> l = kp.getOrders();

        String insertQuery = "INSERT IGNORE INTO Orders (ID) VALUES (?)";
        String insertQuery2 = "INSERT IGNORE INTO TableOrders (TableID, OrderID, ReadyToServe) VALUES (?, ?, 0)";

        try (PreparedStatement insertStmt = con.prepareStatement(insertQuery2))
        {
            for(Order order : l) {
               // Ticket ticket = new Ticket(order.getOrderID(),order.getItems(), "Hi", order,new OrderMenu());
                insertStmt.setInt(1, order.getTableNo());
                insertStmt.setInt(2, order.getOrderID());


                int rowsAffected = insertStmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Order added successfully: " + order.getOrderID()+ "Table No" + order.getTableNo());
                } else {
                    System.out.println("Failed to add order: " + order.getOrderID() + ". It may already exist." + "Table No" + order.getTableNo());
                }

            }
        }catch (SQLException e) {
            System.err.println("SQL Error while adding Order: " );
            e.printStackTrace();
        }


        try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
            for(Order order : l) {
                insertStmt.setInt(1, order.getOrderID());


                int rowsAffected = insertStmt.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Order added successfully: " + order.getOrderID() );
                } else {
                    System.out.println("Failed to add order: " + order.getOrderID() + ". It may already exist.");
                }
            }


        }catch (SQLException e) {
        System.err.println("SQL Error while adding Order: " );
        e.printStackTrace();
    } finally {
        closeConnection();
    }

    }

    public  void updateOrder(int orderID) throws SQLException
    {
        startConnection();
        String insertQuery = "UPDATE TableOrders SET ReadyToServe = 1  WHERE OrderID = ?";

        try (PreparedStatement insertStmt = con.prepareStatement(insertQuery))
        {
            insertStmt.setInt(1,orderID);
            insertStmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("SQL Error while adding Order: " );
            e.printStackTrace();
        } finally {
            closeConnection();
        }

    }
}
