import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Gym Management System ===");

        System.out.print("Enter username: ");
        String user = scanner.nextLine();

        System.out.print("Enter password: ");
        String pass = scanner.nextLine();

        Admin admin = new Admin();
        boolean isLoggedIn = admin.login(user, pass);

        if (isLoggedIn) {
            boolean keepRunning = true;

            while (keepRunning) {
                System.out.println("\n--- Add New Customer Purchase ---");

                System.out.print("Enter Customer ID: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                System.out.print("Enter Customer Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter Gym Plan (Basic / Premium / Pro): ");
                String planName = scanner.nextLine();

                double price = 0;
                int duration = 0;

                switch (planName.toLowerCase()) {
                    case "basic":
                        price = 30.0;
                        duration = 1;
                        break;
                    case "premium":
                        price = 80.0;
                        duration = 3;
                        break;
                    case "pro":
                        price = 150.0;
                        duration = 6;
                        break;
                    default:
                        System.out.println("Invalid plan. Defaulting to Basic.");
                        planName = "Basic";
                        price = 30.0;
                        duration = 1;
                }

                Plan plan = new Plan(0, planName, duration, price); // Use 0 or remove planId

                Customer customer = new Customer(id, name, plan.getPlanName());
                customer.displayDetails();
                plan.displayPlan();

                System.out.print("Enter Payment Method (Cash / Card / Online): ");
                String method = scanner.nextLine();

                Payment payment = new Payment(id, plan.getPrice(), method);
                payment.displayPaymentDetails();

                try (Connection conn = DBConnection.getConnection()) {

                    // 🔄 Insert or Update Plan
                    String insertPlan = "INSERT INTO plans (plan_name, duration_months, price) VALUES (?, ?, ?) " +
                                        "ON DUPLICATE KEY UPDATE duration_months = VALUES(duration_months), price = VALUES(price)";
                    PreparedStatement planStmt = conn.prepareStatement(insertPlan);
                    planStmt.setString(1, plan.getPlanName());
                    planStmt.setInt(2, plan.getDurationMonths());
                    planStmt.setDouble(3, plan.getPrice());
                    planStmt.executeUpdate();

                    // 💾 Insert Customer
                    String insertCustomer = "INSERT INTO customers (id, name, plan_name) VALUES (?, ?, ?)";
                    PreparedStatement custStmt = conn.prepareStatement(insertCustomer);
                    custStmt.setInt(1, customer.getId());
                    custStmt.setString(2, customer.getName());
                    custStmt.setString(3, customer.getPlanName());
                    custStmt.executeUpdate();

                    // 💰 Insert Payment
                    String insertPayment = "INSERT INTO payments (customer_id, amount, method, payment_date) VALUES (?, ?, ?, ?)";
                    PreparedStatement payStmt = conn.prepareStatement(insertPayment);
                    payStmt.setInt(1, payment.getCustomerId());
                    payStmt.setDouble(2, payment.getAmount());
                    payStmt.setString(3, payment.getMethod());
                    payStmt.setString(4, payment.getPaymentDate());
                    payStmt.executeUpdate();

                    System.out.println("✅ Customer, Plan, and Payment saved successfully!");
                } catch (SQLIntegrityConstraintViolationException e) {
                    System.out.println("❌ Error: Duplicate Customer ID. Try a different ID.");
                } catch (SQLException e) {
                    System.out.println("❌ Database error: " + e.getMessage());
                }

                System.out.print("\nDo you want to add another customer? (yes/no): ");
                String choice = scanner.nextLine().toLowerCase();
                if (!choice.equals("yes")) {
                    keepRunning = false;
                    System.out.println("👋 Exiting Gym Management System. Goodbye!");
                }
            }
        } else {
            System.out.println("🔒 Access denied. Try again.");
        }

        scanner.close();
    }
}
