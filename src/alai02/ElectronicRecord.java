/*
 * This class extends ProductRecord but this child class has members
 * author and price. 
 */
package alai02;

import java.util.Objects;

/**
 *
 * @author alex
 */
public class ElectronicRecord extends ProductRecord {

    private String maker;

    public ElectronicRecord(String maker, int price, String productID, String name, int year) {
        super(price, productID, name, year);
        this.maker = maker;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    @Override
    public String toString() {
        return "Electronic Record: " + " Type = " + this.getType() + " Product ID = " + this.getProductID() + " Name = " + this.getName() + " Maker = " + this.getMaker();
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
