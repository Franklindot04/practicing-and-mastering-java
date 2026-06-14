import java.util.List;

public class MeaningfulNamingDemo {
    public static void main(String[] args) {
        List<OrderLine> orderLines = List.of(
                new OrderLine("Notebook", 3),
                new OrderLine("Pen", 10));

        int totalItems = countItems(orderLines);
        System.out.println("Items ordered: " + totalItems);
    }

    private static int countItems(List<OrderLine> orderLines) {
        int totalItems = 0;
        for (OrderLine orderLine : orderLines) {
            totalItems += orderLine.quantity();
        }
        return totalItems;
    }
}

record OrderLine(String productName, int quantity) {}
