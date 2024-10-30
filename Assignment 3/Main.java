import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Byte Me! Canteen System");
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter admin username:");
        String username = scanner.nextLine();
        System.out.println("Enter admin password:");
        String password = scanner.nextLine();

        if (Database.admin.getUsername().equals(username) && Database.admin.getPassword().equals(password)) {
            adminMenu();
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private void customerLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        Customer customer = Database.customers.stream()
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
        Database.customers.add(customer);
        System.out.println("Customer registered successfully.");
    }

    private void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Admin Menu");
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
                    Database.admin.addItem();
                    break;
                case 2:
                    Database.admin.removeItem();
                    break;
                case 3:
                    Database.admin.updateItem();
                    break;
                case 4:
                    Database.admin.orderManagement();
                    break;
                case 5:
                    Database.admin.reportGeneration();
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
            System.out.println("Welcome, " + customer.getName());
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
}