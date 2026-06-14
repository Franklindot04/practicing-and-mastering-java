# Library Management System

A command-line library catalog that lets a learner add books, list books, search by title or author, borrow books, and return books.

## Concepts Practiced

- Classes and encapsulation
- Collections with `ArrayList` and `List`
- Service-style business logic
- Enums for availability status
- Searching text with `contains`
- Validation and simple exceptions
- Console input with `Scanner`

## Files

```text
BookStatus.java
Book.java
LibraryCatalog.java
LibraryApp.java
```

## Compile

From the repository root:

```bash
javac projects/intermediate/library-management-system/BookStatus.java projects/intermediate/library-management-system/Book.java projects/intermediate/library-management-system/LibraryCatalog.java projects/intermediate/library-management-system/LibraryApp.java
```

## Run

```bash
java -cp projects/intermediate/library-management-system LibraryApp
```

## Example Usage

```text
1. Add book
2. List books
3. Search books
4. Borrow book
5. Return book
6. Exit
Enter choice: 1
Title: Effective Java
Author: Joshua Bloch
Book added with id 1.
```

## Possible Improvements

- Register members and track who borrowed each book.
- Add due dates and overdue status.
- Save and load the catalog from a file.
- Support deleting books.
- Add sorting by title or author.
