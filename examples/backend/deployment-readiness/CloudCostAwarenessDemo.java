public class CloudCostAwarenessDemo {
    public static void main(String[] args) {
        MonthlyCostEstimate estimate = new MonthlyCostEstimate(12.00, 7.50, 3.25);
        System.out.println("Estimated monthly demo cost: $" + estimate.total());
    }

    record MonthlyCostEstimate(double compute, double database, double logsAndStorage) {
        double total() {
            return compute + database + logsAndStorage;
        }
    }
}
