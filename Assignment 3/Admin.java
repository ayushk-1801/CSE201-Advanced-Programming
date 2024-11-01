import java.util.Scanner;

public class Admin {
    private String username = "admin";
    private String password = "password";

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item name:");
        String name = scanner.nextLine();
        System.out.println("Enter item price:");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter item category:");
        String categoryInput = scanner.nextLine();
        Category category;
        try {
            category = Category.valueOf(categoryInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category. Please enter either FOOD or DRINK.");
            return;
        }
        Item item = new Item(name, price, category);
        DATABASE.menu.add(item);
        System.out.println("Item added successfully!");
    }

    public void removeItem() {
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
        DATABASE.menu.remove(item);
        for (Order order : DATABASE.orderQueue) {
            for (Pair<Item, Integer> pair : order.getItem()) {
                if (pair.getFirst().equals(item)) {
                    order.setStatus(Status.DENIED);
                }
            }
        }
        System.out.println("Item removed successfully!");
    }

    public void updateItem() {
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
        System.out.println("Enter new item name:");
        String newName = scanner.nextLine();
        System.out.println("Enter new item price:");
        double newPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new item category:");
        String categoryInput = scanner.nextLine();
        Category newCategory;
        try {
            newCategory = Category.valueOf(categoryInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category. Please enter either FOOD or DRINK.");
            return;
        }
        item.setName(newName);
        item.setPrice(newPrice);
        item.setCategory(newCategory);
        System.out.println("Item updated successfully!");
    }

    public void orderManagement() {
        Scanner scanner = new Scanner(System.in);
        Main.header("Order Management");
        System.out.println("1. View pending orders");
        System.out.println("2. Update order status");
        System.out.println("3. Process refund");
        System.out.println("4. Handle special requests");
        System.out.println("0. Go back");
        System.out.println("Enter your choice:");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                viewPendingOrders();
                break;
            case 2:
                updateOrderStatus();
                break;
            case 3:
                processRefund();
                break;
            case 4:
                handleSpecialRequests();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void viewPendingOrders() {
        System.out.println("Pending Orders:");
        for (Order order : DATABASE.orderQueue) {
            if (order.getStatus() == Status.PENDING) {
                System.out.println("Order ID: " + order.getOrderID());
                for (Pair<Item, Integer> pair : order.getItem()) {
                    System.out.println("Item: " + pair.getFirst().getName());
                    System.out.println("Quantity: " + pair.getSecond());
                }
                System.out.println("Special Request: " + order.getSpecialRequest());
                System.out.println();
            }
        }
    }

    public void updateOrderStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order ID:");
        int orderID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Order order = null;
        for (Order o : DATABASE.orderQueue) {
            if (o.getOrderID() == orderID) {
                order = o;
                break;
            }
        }
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        System.out.println("Enter new status (PENDING, PREPARED, DELIVERED, CANCELLED, DENIED):");
        String statusInput = scanner.nextLine();
        Status newStatus;
        try {
            newStatus = Status.valueOf(statusInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status.");
            return;
        }
        order.setStatus(newStatus);
        if (newStatus == Status.DELIVERED) {
            DATABASE.orderQueue.remove(order);
            DATABASE.orderHistory.add(order);
        }
        System.out.println("Order status updated successfully!");
    }

    public void processRefund() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order ID:");
        int orderID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Order order = null;
        for (Order o : DATABASE.orderQueue) {
            if (o.getOrderID() == orderID) {
                order = o;
                break;
            }
        }
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        if (order.getStatus() == Status.DELIVERED) {
            DATABASE.orderQueue.remove(order);
            System.out.println("Refund processed successfully!");
        } else {
            System.out.println("Refund can only be processed for delivered orders.");
        }
    }

    public void handleSpecialRequests() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order ID:");
        int orderID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Order order = null;
        for (Order o : DATABASE.orderQueue) {
            if (o.getOrderID() == orderID) {
                order = o;
                break;
            }
        }
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        System.out.println("Enter special request:");
        String specialRequest = scanner.nextLine();
        order.setSpecialRequest(specialRequest);
        System.out.println("Special request handled successfully!");
    }

    public void reportGeneration() {
        // TODO
    }
}