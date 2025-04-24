public class Payment {
    private int customerId;
    private double amount;
    private String method;
    private String paymentDate;

    public Payment(int customerId, double amount, String method) {
        this.customerId = customerId;
        this.amount = amount;
        this.method = method;
        this.paymentDate = java.time.LocalDate.now().toString();
    }

    public void displayPaymentDetails() {
        System.out.println("\n--- Payment Details ---");
        System.out.println("Customer ID    : " + customerId);
        System.out.println("Amount Paid    : $" + amount);
        System.out.println("Method         : " + method);
        System.out.println("Payment Date   : " + paymentDate);
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public String getPaymentDate() {
        return paymentDate;
    }
}
