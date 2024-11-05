import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Customer implements CustomerInterface {
    private String username;
    private String password;
    private String name;
    private boolean isVIP;
    private ArrayList<Pair<Item, Integer>> cart;
    private ArrayList<Pair<Order, Date>> orderHistory;

    public Customer(String username, String password, String name, boolean isVIP) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.isVIP = isVIP;
        this.cart = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVIP(boolean isVIP) {
        this.isVIP = isVIP;
    }

    public void browseItems() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Main.header("Browse Items");
            System.out.println("1. View all items");
            System.out.println("2. Search item");
            System.out.println("3. Filter by category");
            System.out.println("4. Sort by price");
            System.out.println("0. Go back");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewAllItems();
                    break;
                case 2:
                    searchItem();
                    break;
                case 3:
                    filterByCategory();
                    break;
                case 4:
                    sortByPrice();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void viewAllItems() {
        System.out.println(String.format("%-20s %-10s %-15s", "Item Name", "Price", "Availability"));
        System.out.println("-------------------- ---------- ---------------");
        for (Item item : DATABASE.menu) {
            System.out.println(String.format("%-20s ₹%-10.2f %-15s", item.getName(), item.getPrice(), item.getIsAvailable() ? "Available" : "Not Available"));
        }
    }

    public void searchItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item name:");
        String name = scanner.nextLine();
        Item item = null;
        for (Item i : DATABASE.menu) {
            if (i.getName().equals(name)) {
                item = i;
                break;
            }
        }
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.println(String.format("%-20s %-10s %-15s", "Item Name", "Price", "Availability"));
        System.out.println("-------------------- ---------- ---------------");
        System.out.println(String.format("%-20s ₹%-10.2f %-15s", item.getName(), item.getPrice(), item.getIsAvailable() ? "Available" : "Not Available"));
    }

    public void filterByCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select category:");
        Category[] categories = Category.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]);
        }
        int categoryIndex = scanner.nextInt() - 1;
        if (categoryIndex < 0 || categoryIndex >= categories.length) {
            System.out.println("Invalid category.");
            return;
        }
        Category selectedCategory = categories[categoryIndex];
        System.out.println(String.format("%-20s %-10s %-15s", "Item Name", "Price", "Availability"));
        System.out.println("-------------------- ---------- ---------------");
        for (Item item : DATABASE.menu) {
            if (item.getCategory().equals(selectedCategory)) {
                System.out.println(String.format("%-20s ₹%-10.2f %-15s", item.getName(), item.getPrice(), item.getIsAvailable() ? "Available" : "Not Available"));
            }
        }
    }

    public void sortByPrice() {
        System.out.println("Items sorted by price:");
        DATABASE.menu.sort((i1, i2) -> Double.compare(i1.getPrice(), i2.getPrice()));
        System.out.println(String.format("%-20s %-10s %-15s", "Item Name", "Price", "Availability"));
        System.out.println("-------------------- ---------- ---------------");
        for (Item item : DATABASE.menu) {
            System.out.println(String.format("%-20s ₹%-10.2f %-15s", item.getName(), item.getPrice(), item.getIsAvailable() ? "Available" : "Not Available"));
        }
    }

    public void cartOperations() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Main.header("Cart Operations");
            System.out.println("1. Add to cart");
            System.out.println("2. Modify quantities");
            System.out.println("3. Remove from cart");
            System.out.println("4. View total");
            System.out.println("5. Checkout");
            System.out.println("0. Go back");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addToCart();
                    break;
                case 2:
                    modifyQuantities();
                    break;
                case 3:
                    removeFromCart();
                    break;
                case 4:
                    viewTotal();
                    break;
                case 5:
                    checkout();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void addToCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item name:");
        String name = scanner.nextLine();
        Item item = null;
        for (Item i : DATABASE.menu) {
            if (i.getName().equals(name)) {
                item = i;
                break;
            }
        }
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        if (!item.getIsAvailable()) {
            System.out.println("Item is not available.");
            return;
        }
        System.out.println("Enter quantity:");
        int quantity = scanner.nextInt();
        cart.add(new Pair<>(item, quantity));
        System.out.println("Item added to cart.");
    }

    public void modifyQuantities() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item name:");
        String name = scanner.nextLine();
        Pair<Item, Integer> itemPair = null;
        for (Pair<Item, Integer> pair : cart) {
            if (pair.getFirst().getName().equals(name)) {
                itemPair = pair;
                break;
            }
        }
        if (itemPair == null) {
            System.out.println("Item not found in cart.");
            return;
        }
        System.out.println("Enter new quantity:");
        int quantity = scanner.nextInt();
        cart.remove(itemPair);
        cart.add(new Pair<>(itemPair.getFirst(), quantity));
        System.out.println("Quantity updated.");
    }

    public void removeFromCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item name:");
        String name = scanner.nextLine();
        Pair<Item, Integer> itemPair = null;
        for (Pair<Item, Integer> pair : cart) {
            if (pair.getFirst().getName().equals(name)) {
                itemPair = pair;
                break;
            }
        }
        if (itemPair == null) {
            System.out.println("Item not found in cart.");
            return;
        }
        cart.remove(itemPair);
        System.out.println("Item removed from cart.");
    }

    public void viewTotal() {
        double total = 0;
        System.out.println(String.format("%-20s %-10s %-10s", "Item Name", "Quantity", "Price"));
        System.out.println("-------------------- ---------- ----------");
        for (Pair<Item, Integer> pair : cart) {
            double itemTotal = pair.getFirst().getPrice() * pair.getSecond();
            total += itemTotal;
            System.out.println(String.format("%-20s %-10d ₹%-10.2f", pair.getFirst().getName(), pair.getSecond(), itemTotal));
        }
        System.out.println("-------------------- ---------- ----------");
        System.out.println(String.format("%-20s %-10s ₹%-10.2f", "Total", "", total));
    }

    public void checkout() {
        double total = 0;
        for (Pair<Item, Integer> pair : cart) {
            total += pair.getFirst().getPrice() * pair.getSecond();
        }
        System.out.println("Total: ₹" + total);
        System.out.println("Enter any special requests:");
        Scanner scanner = new Scanner(System.in);
        String specialRequest = scanner.nextLine();
        System.out.println("Enter delivery details:");
        String deliveryDetails = scanner.nextLine();
        ArrayList<Pair<Item, Integer>> items = new ArrayList<>(cart);
        Order newOrder = new Order(items, Status.PENDING, specialRequest);
        newOrder.setDeliveryDetails(deliveryDetails);
        System.out.println("Checkout successful! Your order ID is " + newOrder.getOrderID());
        if (isVIP) {
            DATABASE.orderQueue.addFirst(newOrder);
        } else {
            DATABASE.orderQueue.add(newOrder);
        }
        orderHistory.add(new Pair<>(newOrder, new Date()));
        cart.clear();
    }

    public void orderTracking() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Main.header("Order Tracking");
            System.out.println("1. View order status");
            System.out.println("2. Cancel order");
            System.out.println("3. Order history");
            System.out.println("0. Go back");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewOrderStatus();
                    break;
                case 2:
                    cancelOrder();
                    break;
                case 3:
                    viewOrderHistory();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void viewOrderStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order ID:");
        int orderId = scanner.nextInt();
        Order order = null;
        for (Order o : DATABASE.orderQueue) {
            if (o.getOrderID() == orderId) {
                order = o;
                break;
            }
        }
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        order.printOrder();
    }

    public void cancelOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order ID:");
        int orderId = scanner.nextInt();
        Pair<Order, Date> orderPair = null;
        for (Order o : DATABASE.orderQueue) {
            if (o.getOrderID() == orderId) {
                orderPair = new Pair<>(o, new Date());
                break;
            }
        }
        if (orderPair == null) {
            System.out.println("Order not found.");
            return;
        }
        if (orderPair.getFirst().getStatus() != Status.PENDING) {
            System.out.println("Only pending orders can be cancelled.");
            return;
        }
        DATABASE.orderQueue.remove(orderPair);
        orderPair.getFirst().setStatus(Status.CANCELLED);
        System.out.println("Order cancelled.");
    }

    public void viewOrderHistory() {
        System.out.println("Order history:");
        for (Pair<Order, Date> pair : orderHistory) {
            System.out.println("Order placed on " + pair.getSecond() + ":");
            pair.getFirst().printOrder();
        }

        System.out.println("Reorder items?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 2) {
            return;
        }

        System.out.println("Enter order ID:");
        int orderId = scanner.nextInt();
        Pair<Order, Date> orderPair = null;
        for (Pair<Order, Date> pair : orderHistory) {
            if (pair.getFirst().getOrderID() == orderId) {
                orderPair = pair;
                break;
            }
        }
        if (orderPair == null) {
            System.out.println("Order not found.");
            return;
        }
        Order newOrder = new Order(orderPair.getFirst());
        if (isVIP) {
            DATABASE.orderQueue.addFirst(newOrder);
        } else {
            DATABASE.orderQueue.add(newOrder);
        }
        orderHistory.add(new Pair<>(newOrder, new Date()));
        System.out.println("Order placed successfully! Your order ID is " + newOrder.getOrderID());
    }

    public void itemReviews() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item name:");
        String name = scanner.nextLine();
        Item item = null;
        for (Item i : DATABASE.menu) {
            if (i.getName().equals(name)) {
                item = i;
                break;
            }
        }
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.println("Enter your review:");
        String review = scanner.nextLine();
        System.out.println("Review submitted.");
    }
}