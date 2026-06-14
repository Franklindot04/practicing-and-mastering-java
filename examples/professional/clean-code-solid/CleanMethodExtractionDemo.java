public class CleanMethodExtractionDemo {
    public static void main(String[] args) {
        Invoice invoice = new Invoice("A-100", 120.00, 0.10);
        System.out.println(formatReceipt(invoice));
    }

    private static String formatReceipt(Invoice invoice) {
        double tax = calculateTax(invoice);
        double total = invoice.subtotal() + tax;
        return "Invoice " + invoice.number() + " total: $" + total;
    }

    private static double calculateTax(Invoice invoice) {
        return invoice.subtotal() * invoice.taxRate();
    }
}

record Invoice(String number, double subtotal, double taxRate) {}
