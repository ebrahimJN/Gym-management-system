public class Admin {
    private String username = "admin";
    private String password = "password123";

    public boolean login(String user, String pass) {
        if (username.equals(user) && password.equals(pass)) {
            System.out.println("✅ Login successful!");
            return true;
        } else {
            System.out.println("❌ Invalid credentials. Access denied.");
            return false;
        }
    }
}
