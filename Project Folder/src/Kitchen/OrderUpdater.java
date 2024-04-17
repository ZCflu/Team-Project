package Kitchen;

/**
 * Implements the interface OrderUpdateListener. Contains body of methods to update an order.
 */
public class OrderUpdater implements OrderUpdateListener {
    /**
     * Method prints a statement confirming an Order for a Table is complete.
     * @param TableID Unique identifier for a Table.
     * @param OrderID Unique identifier for an Order.
     */
    @Override
    public void onOrderUpdate(int TableID, int OrderID) {
        // Perform certain actions when an order update occurs
        System.out.println("Order no: " + OrderID + ", for Table no : " + TableID + "is ready.");
        // Add more actions as needed

    }
}
