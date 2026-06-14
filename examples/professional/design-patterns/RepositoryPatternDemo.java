import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositoryPatternDemo {
    public static void main(String[] args) {
        CustomerRepository repository = new InMemoryCustomerRepository();
        repository.save(new Customer(1, "Ada"));

        repository.findById(1).ifPresent(customer -> System.out.println(customer.name()));
    }
}

record Customer(int id, String name) {}

interface CustomerRepository {
    void save(Customer customer);

    Optional<Customer> findById(int id);
}

class InMemoryCustomerRepository implements CustomerRepository {
    private final List<Customer> customers = new ArrayList<>();

    public void save(Customer customer) {
        customers.add(customer);
    }

    public Optional<Customer> findById(int id) {
        return customers.stream()
                .filter(customer -> customer.id() == id)
                .findFirst();
    }
}
