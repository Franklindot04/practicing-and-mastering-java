public class Book {
    private final int id;
    private final String title;
    private final String author;
    private BookStatus status;

    public Book(int id, String title, String author) {
        if (id <= 0) {
            throw new IllegalArgumentException("Book id must be positive.");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank.");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be blank.");
        }

        this.id = id;
        this.title = title.trim();
        this.author = author.trim();
        this.status = BookStatus.AVAILABLE;
    }

    public int id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public BookStatus status() {
        return status;
    }

    public boolean isAvailable() {
        return status == BookStatus.AVAILABLE;
    }

    public void borrow() {
        if (!isAvailable()) {
            throw new IllegalStateException("Book is already borrowed.");
        }
        status = BookStatus.BORROWED;
    }

    public void returnBook() {
        if (isAvailable()) {
            throw new IllegalStateException("Book is already available.");
        }
        status = BookStatus.AVAILABLE;
    }

    public String displayText() {
        return id + " | " + title + " by " + author + " | " + status;
    }
}
