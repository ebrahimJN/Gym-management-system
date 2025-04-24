public class Customer {
    private int id;
    private String name;
    private String planName;

    public Customer(int id, String name, String planName) {
        this.id = id;
        this.name = name;
        this.planName = planName;
    }

    public void displayDetails() {
        System.out.println("\n--- Customer Details ---");
        System.out.println("Customer ID   : " + id);
        System.out.println("Customer Name : " + name);
        System.out.println("Plan Chosen   : " + planName);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlanName() {
        return planName;
    }
}
