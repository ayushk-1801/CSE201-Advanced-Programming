import java.util.List;
import java.util.Scanner;

public class Customer {
    private String username;
    private String password;
    private String name;
    private boolean isVIP;
    private List<Item> cart;

    public Customer(String username, String password, String name, boolean isVIP) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.isVIP = isVIP;
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
        System.out.println("All items:");
        for (Item item : Database.items) {
            System.out.println(item.getName() + " - " + item.getPrice());
        }
    }

    public void searchItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter item name:");
        String name = scanner.nextLine();
        Item item = null;
        for (Item i : Database.items) {
            if (i.getName().equals(name)) {
                item = i;
                break;
            }
        }
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.println("Item found:");
        System.out.println(item.getName() + " - " + item.getPrice());
    }

    public void filterByCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter category:");
        String category = scanner.nextLine();
        System.out.println("Items in category " + category + ":");
        for (Item item : Database.items) {
            if (item.getCategory().equals(category)) {
                System.out.println(item.getName() + " - " + item.getPrice());
            }
        }
    }

    public void sortByPrice() {
        System.out.println("Items sorted by price:");
        Database.items.sort((i1, i2) -> Double.compare(i1.getPrice(), i2.getPrice()));
        for (Item item : Database.items) {
            System.out.println(item.getName() + " - " + item.getPrice());
        }
    }

    public void cartOperations() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
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
        // TODO
    }

    public void modifyQuantities() {
        // TODO
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     }

    public void removeFromCart() {
        // TODO
    }

    public void viewTotal() {
        // TODO
    }

    public void checkout() {
        // TODO
    }
}