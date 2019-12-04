

package alai02_2;

import java.util.Objects;

/*
 * This is a ProductRecord class
 * Product Records have a type, price, productID, name and a year
 * This class has methods such as toString, equals and hashCode
 */
/**
 * @author alex l
 */
public class ProductRecord {

    public static final int PRODUCT_ID_SIZE = 6;

    private String type; //class members
    private int price;
    private String productID;
    private String name;
    private int year;

    public ProductRecord(){ //empty constructor
        
    }
    
    public ProductRecord(int price, String productID, String name, int year) { //constructor
        this.price = price; //using this to differentiate between class members and parameters
        this.productID = productID;
        this.name = name;
        this.year = year;
    }

    public String getType() { //getters and setters
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int hashCode() { //hash code which takes the name as the key
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) { //equals method
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductRecord other = (ProductRecord) obj;
        if (this.price != other.price) {
            return false;
        }
        if (!Objects.equals(this.productID, other.productID)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        return true;
    }

    @Override //toString method
    public String toString() {
        return "ProductRecord: " + " Price = " + price + ", Product ID = " + productID + ", Name = " + name + ", Year = " + year ;
    }
}
