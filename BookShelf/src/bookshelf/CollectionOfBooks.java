package bookshelf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

public class CollectionOfBooks {

    private ArrayList<Book> bookList;

    public CollectionOfBooks() {
        bookList = new ArrayList();
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("BookList.bin"));
            bookList = (ArrayList<Book>) is.readObject();
            is.close();
        } catch (FileNotFoundException ex) {
            System.out.println("BookList.bin not found.\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void addBook(Book b) {
        bookList.add(b);
    }

    public void removeBook(Book b) {
        bookList.remove(b);
    }

    public ArrayList<Book> getBooksByTitle(String title) {
        ArrayList<Book> bookSearchResult = new ArrayList();
        for (Book element : bookList) {
            if (element.getTitle().toLowerCase().contains(title.toLowerCase())) {
                bookSearchResult.add(element);
            }
        }
        Collections.sort(bookSearchResult);
        return (ArrayList<Book>) bookSearchResult.clone();
    }

    public ArrayList<Book> getBooksByAuthor(String author) {
        ArrayList<Book> bookSearchResult = new ArrayList();
        for (Book element : bookList) {
            for (Author elementAuthor : element.getAuthors()) {
                if (elementAuthor.getName().toLowerCase().contains(author.toLowerCase())) {
                    bookSearchResult.add(element);
                }
            }
        }
        Collections.sort(bookSearchResult);
        return (ArrayList<Book>) bookSearchResult.clone();
    }

    public ArrayList<Book> getBooksByISBN(String ISBN) {
        ArrayList<Book> bookSearchResult = new ArrayList();
        for (Book element : bookList) {
            if (element.getISBN().toLowerCase().contains(ISBN.toLowerCase())) {
                bookSearchResult.add(element);
            }
        }
        Collections.sort(bookSearchResult);
        return (ArrayList<Book>) bookSearchResult.clone();
    }

    public ArrayList<Book> getBookList() {
        Collections.sort(bookList);
        return (ArrayList<Book>) bookList.clone();
    }

}
