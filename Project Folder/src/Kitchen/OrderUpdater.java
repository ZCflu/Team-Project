package Kitchen;

public class OrderUpdater implements OrderUpdateListener {

    @Override
    public void onOrderUpdate(int TableID, int OrderID) {
        // Perform certain actions when an order update occurs
        System.out.println("Order no: " + OrderID + ", for Table no : " + TableID + "is ready.");
        // Add more actions as needed

    }
}
