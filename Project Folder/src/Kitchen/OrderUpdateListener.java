package Kitchen;

/**
 * Interface for updating an order.
 * @see OrderUpdater
 */
public interface OrderUpdateListener {
    /**
     * Interface method that updates an order using TableID and OrderID.
     * @param TableID Unique identifier for a Table.
     * @param OrderID Unique identifier for an Order.
     */
    void onOrderUpdate(int TableID, int OrderID); // Add more parameters as needed
}