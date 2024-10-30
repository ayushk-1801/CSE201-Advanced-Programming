public class Order {
    private static int orderID = 1;
    private Pair<Item, Integer> item;
    private Status status;
    private String specialRequest;

    public Order(Item item, int quantity, String specialRequest) {
        this.item = new Pair<>(item, quantity);
        this.status = Status.PENDING;
        this.specialRequest = specialRequest;
        orderID++;
    }

    public Pair<Item, Integer> getItem() {
        return item;
    }

    public int getQuantity() {
        return item.getSecond();
    }

    public Status getStatus() {
        return status;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setItem(Item item, int quantity) {
        this.item = new Pair<>(item, quantity);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }
}