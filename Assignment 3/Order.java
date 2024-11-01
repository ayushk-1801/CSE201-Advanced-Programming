import java.util.ArrayList;

public class Order {
    private static int orderID = 0;
    private int OrderID;
    private ArrayList<Pair<Item, Integer>> item;
    private Status status;
    private String specialRequest;
    private String review;

    public Order(ArrayList<Pair<Item, Integer>> item, Status status, String specialRequest) {
        this.item = item;
        this.status = status;
        this.specialRequest = specialRequest;
        this.review = "";
        this.OrderID = orderID;
        orderID++;
    }

    public static int getOrderID() {
        return orderID;
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