import java.util.ArrayList;

public class Order {
    private static int orderID = 0;
    private int OrderID;
    private ArrayList<Pair<Item, Integer>> item;
    private Status status;
    private String specialRequest;
    private String deliveryDetails = "";

    public Order(Order order) {
        orderID++;
        this.OrderID = orderID;
        this.item = new ArrayList<>(order.item);
        this.status = Status.PENDING;
        this.specialRequest = order.specialRequest;
    }

    public Order(ArrayList<Pair<Item, Integer>> item, Status status, String specialRequest) {
        this.item = item;
        this.status = status;
        this.specialRequest = specialRequest;
        orderID++;
        this.OrderID = orderID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public ArrayList<Pair<Item, Integer>> getItem() {
        return item;
    }

    public Status getStatus() {
        return status;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public String getDeliveryDetails() {
        return deliveryDetails;
    }

    public int setOrderID(int orderID) {
        return orderID;
    }

    public void setItem(ArrayList<Pair<Item, Integer>> item) {
        this.item = item;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public void setDeliveryDetails(String deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }

    public void printOrder() {
    System.out.println(String.format("%-20s %-10s", "Order ID:", OrderID));
    System.out.println(String.format("%-20s %-10s", "Item", "Quantity"));
    System.out.println("-------------------- ----------");
    for (Pair<Item, Integer> pair : item) {
        System.out.println(String.format("%-20s %-10d", pair.getFirst().getName(), pair.getSecond()));
    }
    System.out.println("-------------------- ----------");
    System.out.println(String.format("%-20s %-10s", "Special Request:", specialRequest));
    System.out.println(String.format("%-20s %-10s", "Status:", status));
    System.out.println(String.format("%-20s %-10s", "Delivery Details:", deliveryDetails));
}

    @Override
    public String toString() {
        return "Order ID: " + OrderID + "\n" +
                "Item: " + item.getFirst() + "\n" +
                "Status: " + status + "\n" +
                "Special Request: " + specialRequest + "\n";
    }
}