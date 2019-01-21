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
public class ElectronicRecord {

    private int price;
    private int productID;
    private String name;
    private int year;
    private String maker;

    public ElectronicRecord(int productID, String name, int year) {
        this.productID = productID;
        this.name = name;
        this.year = year;
    }

    public ElectronicRecord(int price, int productID, String name, int year, String maker) {
        this.price = price;
        this.productID = productID;
        this.name = name;
        this.year = year;
        this.maker = maker;
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

    public String getMaker() {
        return maker;
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
        this.name = name;
    }

    public void setYear(int year) {
        if (year > 1000 && year < 9999) {
            this.year = year;
        } else {
            System.out.println("Error creating year");
        }
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    @Override
    public String toString() {
        return "electronicRecord{" + "price=" + price + ", productID=" + productID + ", name=" + name + ", year=" + year + ", maker=" + maker + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ElectronicRecord other = (ElectronicRecord) obj;
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
        if (!Objects.equals(this.maker, other.maker)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    public static ArrayList addElectronic(ArrayList electronicList, int iD) {

        Scanner input = new Scanner(System.in);
        int price = 0;
        int productID = iD;
        String name = "TBD";
        int year = 0;
        String maker = "TBD";


        System.out.println("Enter the price of the product: ");
        price = input.nextInt();
        input.nextLine();
        System.out.println("Enter the name of the product: ");
        name = input.nextLine();
        System.out.println("Enter the year the product was made: ");
        year = input.nextInt();
        input.nextLine();
        System.out.println("Enter the maker of the product: ");
        maker = input.nextLine();
        
        System.out.println("The new electronic was added to the list");

        ElectronicRecord newElectronic = new ElectronicRecord(price, productID, name, year, maker);
        electronicList.add(newElectronic);
        return electronicList;
    }

}
