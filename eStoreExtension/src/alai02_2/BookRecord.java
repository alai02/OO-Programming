/*
 * Author: Alex L 
 * Date: Wed, November 9th, 2016
 * This class extends ProductRecord but this child class has members
 * author and price. 
 * 
 */
package alai02_2;

import java.util.Objects;

public class BookRecord extends ProductRecord { //extends parent class ProductRecord but adds new functionality of the BookRecord

    private String authors; //class variables
    private String publisher;

    public BookRecord(String authors, String publisher, int price, String productID, String name, int year) { //constructor
        super(price, productID, name, year);
        this.authors = authors;
        this.publisher = publisher;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
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
        if (!Objects.equals(this.authors, other.authors)) {
            return false;
        }
        if (!Objects.equals(this.publisher, other.publisher)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "BookRecord: " + " Name: " + this.getName() + ", Authors = " + this.authors + " , Publisher = " + this.publisher + ", product ID: " + this.getProductID();
    }
}
