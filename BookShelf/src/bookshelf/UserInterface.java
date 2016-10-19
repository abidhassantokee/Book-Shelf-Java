package bookshelf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class UserInterface {

    private Scanner inString;
    private Scanner inInt;
    private Scanner inDouble;
    private Book book;
    private CollectionOfBooks cob;

    public UserInterface() {
        inString = new Scanner(System.in);
        inInt = new Scanner(System.in);
        inDouble = new Scanner(System.in);
        cob = new CollectionOfBooks();
    }

    public void menu() {
        int choice = 0;
        System.out.println("**Welcome to Book Shelf**");
        while (choice != 5) {
            System.out.println("\n\n**Main menu**");
            System.out.println("1. Add a book\n2. Remove a book\n3. Search book by title, author or ISBN\n4. Get list of all books\n5. Exit and save");
            System.out.print("Select your choice by entering a number: ");
            choice = inInt.nextInt();
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    System.out.println("\n1. Search by title\n2. Search by author\n3. Search by ISBN");
                    System.out.print("Select your choice by entering a number: ");
                    int searchChoice = inInt.nextInt();
                    switch (searchChoice) {
                        case 1:
                            searchBookByTitle();
                            break;
                        case 2:
                            searchBookByAuthor();
                            break;
                        case 3:
                            searchBookByISBN();
                            break;
                        default:
                            System.out.println("Wrong input");
                            break;
                    }
                    break;
                case 4:
                    getBookList();
                    break;
                case 5:
                    saveToFile();
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    public void addBook() {
        boolean ISBNfound = false, invalidISBN = false;
        String ISBN = "";
        ArrayList<Book> bookList = cob.getBookList();
        System.out.print("\nEnter book title: ");
        String title = inString.nextLine();
        do {
            System.out.print("Enter book ISBN: ");
            ISBN = null;
            ISBN = inString.nextLine();
            if (!ISBN.contains(" ") && !ISBN.isEmpty()) {
                invalidISBN = false;
                for (Book element : bookList) {
                    ISBNfound = element.getISBN().equalsIgnoreCase(ISBN);
                    if (ISBNfound == true) {
                        System.out.println("A book is already registered with this ISBN");
                        break;
                    }
                }
            } else {
                invalidISBN = true;
                System.out.println("ISBN can not be empty or can not contain any space");
            }
        } while (ISBNfound == true || invalidISBN == true);
        System.out.print("Enter book edition: ");
        int edition = inInt.nextInt();
        System.out.print("Enter book price: ");
        double price = inDouble.nextDouble();
        book = new Book(title, ISBN, edition, price);
        cob.addBook(book);
        String input = "";
        do {
            System.out.print("Enter book author: ");
            String author = inString.nextLine();
            book.addAuthor(author);
            do {
                System.out.print("Do you want to enter more book author? (y/n): ");
                input = inString.nextLine();
                if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
                    System.out.println("Wrong input");
                }
            } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));
        } while (input.equalsIgnoreCase("y"));
        System.out.println("\nBook added successfully");
    }

    public void removeBook() {
        ArrayList<Book> bookList = cob.getBookList();
        System.out.print("\nEnter book ISBN: ");
        String ISBN = inString.nextLine();
        for (Book element : bookList) {
            if (element.getISBN().equalsIgnoreCase(ISBN)) {
                cob.removeBook(element);
                break;
            }
        }
        System.out.println("Book removed successfully");
    }

    public void searchBookByTitle() {
        System.out.print("\nEnter book title: ");
        String title = inString.nextLine();
        ArrayList<Book> bookList = cob.getBooksByTitle(title);
        int count = 1;
        System.out.println("\nSearch result:");
        for (Book element : bookList) {
            System.out.println("\n" + count + ". " + element);
            count++;
        }
    }

    public void searchBookByAuthor() {
        System.out.print("\nEnter book author: ");
        String author = inString.nextLine();
        ArrayList<Book> bookList = cob.getBooksByAuthor(author);
        int count = 1;
        System.out.println("\nSearch result:");
        for (Book element : bookList) {
            System.out.println("\n" + count + ". " + element);
            count++;
        }
    }

    public void searchBookByISBN() {
        System.out.print("\nEnter book ISBN: ");
        String ISBN = inString.nextLine();
        ArrayList<Book> bookList = cob.getBooksByISBN(ISBN);
        int count = 1;
        System.out.println("\nSearch result:");
        for (Book element : bookList) {
            System.out.println("\n" + count + ". " + element);
            count++;
        }
    }

    public void getBookList() {
        ArrayList<Book> bookList = cob.getBookList();
        int count = 1;
        System.out.println("\nBook list:");
        for (Book element : bookList) {
            System.out.println("\n" + count + ". " + element);
            count++;
        }
    }

    public void saveToFile() {
        ArrayList<Book> bookList = cob.getBookList();
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("BookList.bin"));
            os.writeObject(bookList);
            os.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
