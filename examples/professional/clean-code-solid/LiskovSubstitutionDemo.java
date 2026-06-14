import java.util.List;

public class LiskovSubstitutionDemo {
    public static void main(String[] args) {
        List<ReadableDocument> documents = List.of(new PdfDocument("Guide"), new WebPage("Docs"));

        for (ReadableDocument document : documents) {
            System.out.println(document.title());
        }
    }
}

interface ReadableDocument {
    String title();
}

record PdfDocument(String title) implements ReadableDocument {}

record WebPage(String title) implements ReadableDocument {}
