package dev.franklindot04.projects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.List;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class IntermediateLibraryCatalogTest {
    @TempDir
    Path tempDir;

    @Test
    void addsSearchesBorrowsAndReturnsBooks() throws Exception {
        ClassLoader classLoader = compileProject();
        Class<?> catalogClass = classLoader.loadClass("LibraryCatalog");
        Object catalog = catalogClass.getConstructor().newInstance();

        Method addBook = catalogClass.getMethod("addBook", String.class, String.class);
        Method allBooks = catalogClass.getMethod("allBooks");
        Method search = catalogClass.getMethod("search", String.class);
        Method borrowBook = catalogClass.getMethod("borrowBook", int.class);
        Method returnBook = catalogClass.getMethod("returnBook", int.class);
        Method findById = catalogClass.getMethod("findById", int.class);

        addBook.invoke(catalog, "Effective Java", "Joshua Bloch");
        addBook.invoke(catalog, "Clean Code", "Robert Martin");

        assertEquals(2, ((List<?>) allBooks.invoke(catalog)).size());
        assertEquals(1, ((List<?>) search.invoke(catalog, "bloch")).size());

        Object firstBook = findById.invoke(catalog, 1);
        Method isAvailable = firstBook.getClass().getMethod("isAvailable");
        assertTrue((boolean) isAvailable.invoke(firstBook));

        borrowBook.invoke(catalog, 1);
        assertFalse((boolean) isAvailable.invoke(firstBook));

        returnBook.invoke(catalog, 1);
        assertTrue((boolean) isAvailable.invoke(firstBook));
    }

    @Test
    void preventsBorrowingUnavailableBooks() throws Exception {
        ClassLoader classLoader = compileProject();
        Class<?> catalogClass = classLoader.loadClass("LibraryCatalog");
        Object catalog = catalogClass.getConstructor().newInstance();

        Method addBook = catalogClass.getMethod("addBook", String.class, String.class);
        Method borrowBook = catalogClass.getMethod("borrowBook", int.class);

        addBook.invoke(catalog, "Effective Java", "Joshua Bloch");
        borrowBook.invoke(catalog, 1);

        InvocationTargetException error = assertThrows(
                InvocationTargetException.class,
                () -> borrowBook.invoke(catalog, 1));

        assertInstanceOf(IllegalStateException.class, error.getCause());
        assertEquals("Book is already borrowed.", error.getCause().getMessage());
    }

    private ClassLoader compileProject() throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        Path outputDirectory = tempDir.resolve("library-management-system");
        outputDirectory.toFile().mkdirs();

        int result = compiler.run(
                null,
                null,
                null,
                "-d",
                outputDirectory.toString(),
                "projects/intermediate/library-management-system/BookStatus.java",
                "projects/intermediate/library-management-system/Book.java",
                "projects/intermediate/library-management-system/LibraryCatalog.java",
                "projects/intermediate/library-management-system/LibraryApp.java");
        assertEquals(0, result, "Library project should compile before tests run.");

        return new URLClassLoader(new URL[] {outputDirectory.toUri().toURL()});
    }
}
