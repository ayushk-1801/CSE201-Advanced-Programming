import java.util.ArrayList;

public class Order {
    private static int orderID = 0;
    private int OrderID;
    private ArrayList<Pair<Item, Integer>> item;
    private Status status;
    private String specialRequest;
    private String review;

    public Order(Order order) {
        orderID++;
        this.OrderID = orderID;
        this.item = new ArrayList<>(order.item);
        this.status = Status.PENDING;
        this.specialRequest = order.specialRequest;
        this.review = order.review;
    }

    public Order(ArrayList<Pair<Item, Integer>> item, Status status, String specialRequest) {
        this.item = item;
        this.status = status;
        this.specialRequest = specialRequest;
        this.review = "";
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

    public String getReview() {
        return review;
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

    public void setReview(String review) {
        this.review = review;
    }
}