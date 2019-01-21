/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alai_A1;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class BookRecord {

    //GLOBALS
    public static final int PRODUCT_ID_SIZE = 6;

    private int price = 0;
    private int productID = 0;
    private String name = "TBD";
    private int year = 0;
    private String authors = "TBD";
    private String publisher = "TBD";

    public static ArrayList addBook(ArrayList bookList, int iD) {

        Scanner input = new Scanner(System.in);
        int price = 0;
        int productID = iD;
        String name;
        int year = 0;
        String authors = "TBD";
        String publisher = "TBD";

        System.out.println("Enter the price of the product (optional): )");
        price = input.nextInt();
        input.nextLine();
        System.out.println("Enter the name of the product: (manditory) ");
        name = input.nextLine();
        System.out.println("Enter the year the product was made: (manditory) ");
        year = input.nextInt();
        input.nextLine();
        System.out.println("Enter the autor(s) of the book: (optional) ");
        authors = input.nextLine();
        System.out.println("Enter the publishers name: (optional)");
        publisher = input.nextLine();
        
        System.out.println("The new book was added to the list! ");

        BookRecord newBook = new BookRecord(price, productID, name, year, authors, publisher);
        bookList.add(newBook);
        return bookList;
    }

    public BookRecord(int productID, String name, int year) {
        this.productID = productID;
        this.name = name;
        this.year = year;
    }
    
    public BookRecord(int price, int productID, String name, int year, String authors, String publisher) {
        this.price = price;
        this.productID = productID;
        this.name = name;
        this.year = year;
        this.authors = authors;
        this.publisher = publisher;
    }

    public static int getPRODUCT_ID_SIZE() {
        return PRODUCT_ID_SIZE;
    }

    public int getPrice() {
        return price;
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPrice(int price) {
        if (price > 0 && price < Integer.MAX_VALUE) {
            this.price = price;
        } else {
            System.out.println("Error creating price");
        }
    }

    public void setProductID(int productID) {
        if (productID == 6) {
            this.productID = productID;
        } else {
            System.out.println("Error creating price");
        }
    }

    public void setName(String name) {
        if (authors != null) {
            StringBuilder temp = new StringBuilder(authors.trim());
            temp.setLength(80);
            this.authors = temp.toString();
        } else {
            System.out.println("ERROR CREATING NAME");
        }
    }

    public void setYear(int year) {
        if (year > 1000 && year < 9999) {
            this.year = year;
        } else {
            System.out.println("Error creating year");
        }
    }

    public void setAuthors(String authors) {

        if (authors != null) {
            StringBuilder temp = new StringBuilder(authors.trim());
            temp.setLength(50);
            this.authors = temp.toString();
        } else {
            this.authors = "TBD";
        }
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "BookRecord{" + "price=" + price + ", productID=" + productID + ", name=" + name + ", year=" + year + ", Authors=" + authors + ", Publisher=" + publisher + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookRecord other = (BookRecord) obj;
        if (this.price != other.price) {
            return false;
        }
        if (this.productID != other.productID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.authors, other.authors)) {
            return false;
        }
        if (!Objects.equals(this.publisher, other.publisher)) {
            return false;
        }
        return true;
    }

}
