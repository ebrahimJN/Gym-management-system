public class Plan {
    private int planId;
    private String planName;
    private int durationMonths;
    private double price;

    // Constructor with optional planId (use 0 when unknown)
    public Plan(int planId, String planName, int durationMonths, double price) {
        this.planId = planId;
        this.planName = planName;
        this.durationMonths = durationMonths;
        this.price = price;
    }

    // Getters
    public int getPlanId() {
        return planId;
    }

    public String getPlanName() {
        return planName;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public double getPrice() {
        return price;
    }

    // Setter for planId (used after DB auto-increment fetch)
    public void setPlanId(int planId) {
        this.planId = planId;
    }

    // Display plan details
    public void displayPlan() {
        System.out.println("\n--- Plan Details ---");
        System.out.println("Plan ID      : " + planId);
        System.out.println("Plan Name    : " + planName);
        System.out.println("Duration     : " + durationMonths + " months");
        System.out.println("Price        : $" + price);
    }
}
