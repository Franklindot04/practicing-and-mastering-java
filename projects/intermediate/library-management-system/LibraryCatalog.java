import java.util.ArrayList;
import java.util.List;

public class LibraryCatalog {
    private final List<Book> books = new ArrayList<>();
    private int nextId = 1;

    public Book addBook(String title, String author) {
        Book book = new Book(nextId, title, author);
        books.add(book);
        nextId++;
        return book;
    }

    public List<Book> allBooks() {
        return List.copyOf(books);
    }

    public List<Book> search(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Search text cannot be blank.");
        }

        String searchText = text.trim().toLowerCase();
        List<Book> matches = new ArrayList<>();
        for (Book book : books) {
            if (book.title().toLowerCase().contains(searchText)
                    || book.author().toLowerCase().contains(searchText)) {
                matches.add(book);
            }
        }
        return matches;
    }

    public void borrowBook(int id) {
        findById(id).borrow();
    }

    public void returnBook(int id) {
        findById(id).returnBook();
    }

    public Book findById(int id) {
        for (Book book : books) {
            if (book.id() == id) {
                return book;
            }
        }
        throw new IllegalArgumentException("Book id does not exist.");
    }
}
