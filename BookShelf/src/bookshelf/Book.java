package bookshelf;

import java.io.Serializable;
import java.util.*;
import java.lang.Comparable;

public class Book implements Serializable, Comparable<Book> {

    private String title, ISBN;
    private int edition;
    private double price;
    private ArrayList<Author> authorList;
    private Author author;

    public Book(String title, String ISBN, int edition, double price) {
        authorList = new ArrayList();
        this.ISBN = ISBN;
        this.title = title;
        this.edition = edition;
        this.price = price;
    }

    public Book() {
        authorList = new ArrayList();
        ISBN = "";
        title = "";
        edition = 0;
        price = 0.0;
    }

    public void addAuthor(String author) {
        boolean flag = false;
        for (Author element : authorList) {
            flag = element.getName().equalsIgnoreCase(author);
            if (flag == true) {
                break;
            }
        }
        if (flag == false) {

            this.author = new Author(author);
            authorList.add(this.author);
        }
    }

    public ArrayList<Author> getAuthors() {
        return (ArrayList<Author>) authorList.clone();
    }

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getEdition() {
        return edition;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        String info = "Title: " + title + " | ISBN: " + ISBN + " | Edition: " + edition + " | Price: " + price + "\nAuthor List: ";
        int count=0;
        for(count=0; count<authorList.size()-1;count++)
        {
            info += authorList.get(count).getName() + ", ";
        }
        info += authorList.get(count).getName();
        return info;
    }

    @Override
    public int compareTo(Book b) {
        return this.title.compareToIgnoreCase(b.getTitle());
    }

}
