package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import java.awt.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Library library = new Library("City Library", "123 Main St");

        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "Fiction");
        Book book3 = new Book("Becoming", "Michelle Obama", "Biography");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Check out a book
        library.checkOutBook(book1);

        // List available fiction books
        System.out.println("Available Fiction Books:");
        library.listAvailableBooksInGenre("Fiction").forEach(System.out::println);

        // Returning a book
        library.returnBook(book1);

        // List again after returning
        System.out.println("\nAvailable Fiction Books after returning one:");
        library.listAvailableBooksInGenre("Fiction").forEach(System.out::println);
        library.displayBooksInJFrame();
    }
}

class Book {
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = true; // Initially, the book is available
    }

    public void markOnLoan() {
        this.isAvailable = false;
    }

    public void returnBook() {
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getGenre() {
        return genre;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

class Library {
    private String name;
    private String address;
    private ArrayList<Book> books; //

    public Library(String name, String address) {
        this.name = name;
        this.address = address;
        this.books = new ArrayList<>(); //
    }

    public void addBook(Book book) {
        this.books.add(book); //
    }

    public void checkOutBook(Book book) {
        if (book.isAvailable()) {
            book.markOnLoan();
        }
    }

    public void returnBook(Book book) {
        book.returnBook();
    }

    public List<Book> listAvailableBooksInGenre(String genre) {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : this.books) {
            if (book.getGenre().equalsIgnoreCase(genre) && book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public void displayBooksInJFrame() {
        JFrame frame = new JFrame(name + " - Book List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (Book book : books) {
            JLabel bookLabel = new JLabel(book.toString());
            panel.add(bookLabel);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}

