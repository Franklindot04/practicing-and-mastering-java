public class SingleResponsibilityDemo {
    public static void main(String[] args) {
        SalesReport report = new SalesReport(15, 780.50);
        ReportFormatter formatter = new ReportFormatter();
        ConsoleReportPrinter printer = new ConsoleReportPrinter();

        printer.print(formatter.format(report));
    }
}

record SalesReport(int orders, double revenue) {}

class ReportFormatter {
    String format(SalesReport report) {
        return "Orders: " + report.orders() + ", revenue: $" + report.revenue();
    }
}

class ConsoleReportPrinter {
    void print(String report) {
        System.out.println(report);
    }
}
