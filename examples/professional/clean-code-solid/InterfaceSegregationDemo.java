public class InterfaceSegregationDemo {
    public static void main(String[] args) {
        Printable receipt = new Receipt("Paid");
        Scannable contract = new Contract("Signed");

        receipt.print();
        contract.scan();
    }
}

interface Printable {
    void print();
}

interface Scannable {
    void scan();
}

record Receipt(String text) implements Printable {
    public void print() {
        System.out.println("Printing receipt: " + text);
    }
}

record Contract(String text) implements Scannable {
    public void scan() {
        System.out.println("Scanning contract: " + text);
    }
}
