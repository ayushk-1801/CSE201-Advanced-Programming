import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.init();
        main.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            header("Welcome to Byte Me! Canteen System");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Customer");
            System.out.println("3. Register as Customer");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    customerLogin();
                    break;
                case 3:
                    registerCustomer();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void adminLogin() {
        header("Admin Login");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter admin username:");
        String username = scanner.nextLine();
        System.out.println("Enter admin password:");
        String password = scanner.nextLine();

        if (DATABASE.admin.getUsername().equals(username) && DATABASE.admin.getPassword().equals(password)) {
            adminMenu();
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private void customerLogin() {
        header("Customer Login");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        Customer customer = DATABASE.customers.stream()
                .filter(c -> c.getUsername().equals(username) && c.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (customer != null) {
            customerMenu(customer);
        } else {
            System.out.println("Invalid customer credentials.");
        }
    }

    private void registerCustomer() {
        header("Customer Registration");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        System.out.println("Enter your name:");
        String name = scanner.nextLine();
        System.out.println("Are you a VIP customer? (yes/no):");
        boolean isVIP = scanner.nextBoolean();

        Customer customer = new Customer(username, password, name, isVIP);
        DATABASE.customers.add(customer);
        System.out.println("Customer registered successfully.");
    }

    private void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            header("Admin Menu");
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. Update item");
            System.out.println("4. Order Management");
            System.out.println("5. Report Generation");
            System.out.println("0. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    DATABASE.admin.addItem();
                    break;
                case 2:
                    DATABASE.admin.removeItem();
                    break;
                case 3:
                    DATABASE.admin.updateItem();
                    break;
                case 4:
                    DATABASE.admin.orderManagement();
                    break;
                case 5:
                    DATABASE.admin.reportGeneration();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void customerMenu(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            header("Welcome, " + customer.getName());
            System.out.println("1. Browse items");
            System.out.println("2. Cart Operations");
            System.out.println("3. Order Tracking");
            System.out.println("4. Item Reviews");
            System.out.println("0. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    customer.browseItems();
                    break;
                case 2:
                    customer.cartOperations();
                    break;
                case 3:
                    customer.orderTracking();
                    break;
                case 4:
                    customer.itemReviews();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void init() {
        DATABASE.admin = new Admin();
        DATABASE.customers = new ArrayList<>();
        DATABASE.menu = new ArrayList<>();
        DATABASE.orderQueue = new LinkedList<>();
        DATABASE.orderHistory = new ArrayList<>();

        DATABASE.menu.add(new Item("Chicken Biryani", 100, Category.NON_VEG));
        DATABASE.menu.add(new Item("Chicken Roll", 80, Category.ROLLS));
        DATABASE.menu.add(new Item("Veg Roll", 40, Category.ROLLS));
        DATABASE.menu.add(new Item("Tea", 10, Category.DRINK));

        DATABASE.customers.add(new Customer("dev", "123", "Dev", false));
        DATABASE.customers.add(new Customer("anant", "123", "Anant", false));
        DATABASE.customers.add(new Customer("arhan", "123", "Arhan", true));
    }

    public static void header(String title) {
        System.out.println(
                "============================================================================================================");
        System.out.println("                                                " + title);
        System.out.println(
                "============================================================================================================");
    }
}