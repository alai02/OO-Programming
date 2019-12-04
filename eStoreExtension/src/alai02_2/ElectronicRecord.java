/*
 * Author: Alex L
 * Date: Wed, November 9th, 2016
 * This class extends ProductRecord but this child class has members
 * author and price. 
 */
package alai02_2;

import java.util.Objects;

public class ElectronicRecord extends ProductRecord {//extends parent class

    private String maker; //class member

    public ElectronicRecord(String maker, int price, String productID, String name, int year) {//constructor
        super(price, productID, name, year);//parent class inits the other members
        this.maker = maker;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    @Override
    public String toString() { //new toString overrides parent to string
        return "Electronic Record: " + " Type = " + this.getType() + ", Product ID = " + this.getProductID() + ", Name = " + this.getName() + ", Maker = " + this.getMaker();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.maker);
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
        final ElectronicRecord other = (ElectronicRecord) obj;
        if (!Objects.equals(this.maker, other.maker)) {
            return false;
        }
        return true;
    }

}
