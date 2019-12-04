/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alai02;

import java.io.File;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Date: November 9th, 2015
 *
 * @author alex l 
 * Description: This is a EStoreFunctionality class that has an Arrray List
 * and a Hash Map which can perform functions utilizing the ProductRecord,
 * BookRecord and Electronic Record Classes.
 *
 */
public class EStoreFunctionality {

    private ArrayList<ProductRecord> productList; //class members of the EStoreFunctionality
    private HashMap<String, ProductRecord> map;
    public static Scanner scanner = new Scanner(System.in);

    public EStoreFunctionality() throws Exception { //constructor for the EStoreFunctionality that creates instances of the product list and hash map
        this.productList = new ArrayList<>();
        this.map = new HashMap<>();
    }

    //getters and setters for the class variables
    public ArrayList<ProductRecord> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<ProductRecord> productList) {
        this.productList = productList;
    }

    public HashMap<String, ProductRecord> getMap() {
        return map;
    }

    public void setMap(HashMap<String, ProductRecord> map) {
        this.map = map;
    }

    public void outputToFile(ArrayList productList) { //function that prints all products to a file
        try { //try to open the file
            PrintWriter fileWriter = new PrintWriter("/home/alexlai/Desktop/CS/products.txt", "UTF-8"); //create a file path
            int i;

            for (i = 0; i < productList.size(); i++) { //loop through all the productsin the list
                ProductRecord pTemp; //create a temp product
                pTemp = (ProductRecord) productList.get(i); //case because of warnings

                switch (pTemp.getType()) {   //switch the product type
                    case "electronics":
                        ElectronicRecord eTemp = (ElectronicRecord) pTemp; //create the corresponding product type
                        fileWriter.println("type = \"" + eTemp.getType() + "\""); //write to the output file
                        fileWriter.println("productID = \"" + eTemp.getProductID() + "\"");
                        fileWriter.println("name = \"" + eTemp.getName() + "\"");
                        fileWriter.println("price = \"" + eTemp.getPrice() + "\"");
                        fileWriter.println("year = \"" + eTemp.getYear() + "\"");
                        fileWriter.println("maker = \"" + eTemp.getMaker() + "\"");
                        fileWriter.println(); //next line should be whitespace
                        break;
                    case "book":
                        BookRecord bTemp = (BookRecord) pTemp;
                        fileWriter.println("type = \"" + bTemp.getType() + "\"");
                        fileWriter.println("productID = \"" + bTemp.getProductID() + "\"");
                        fileWriter.println("name = \"" + bTemp.getName() + "\"");
                        fileWriter.println("price = \"" + bTemp.getPrice() + "\"");
                        fileWriter.println("year = \"" + bTemp.getYear() + "\"");
                        fileWriter.println("authors = \"" + bTemp.getAuthors() + "\"");
                        fileWriter.println("publisher = \"" + bTemp.getPublisher() + "\"");
                        fileWriter.println();
                        break;
                }

            }
            fileWriter.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println("Failed to write to output file");
        }
    }

    public ArrayList addProduct(ArrayList productList) {

        int set = 0;
        int i;
        int price;
        int year;
        boolean exit = false;
        int bookOrElectronic;
        String productID = "";
        String name;
        String maker; //specific to electronics
        String authors; //specific to books
        String publisher;
        boolean found = false;
        
        do {
            System.out.println("Enter the products ID (Must be unique)");
            productID = scanner.nextLine();
            found = false;
            for(i = 0; i < productList.size(); i++){
                ProductRecord pTemp = (ProductRecord) productList.get(i);
                if(pTemp.getProductID().contentEquals(productID)){
                    found = true;
                }
            }
        } while (found == true);

        System.out.println();
        bookOrElectronic = exceptionHandlerInt("Enter 1 to add an electronic and 2 for a book", 1, 2);
        
        price = exceptionHandlerInt("Enter the price of the product: (Just press enter to leave blank, default is 100)", 0, 1000000);
        scanner.nextLine();

        System.out.println("Enter the year the product was made: ");
        year = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the name of the product: ");
        name = scanner.nextLine();

        if (bookOrElectronic == 1) {
            System.out.println("Enter the maker of the electronic: ");
            maker = scanner.nextLine();

            ElectronicRecord newElectronic = new ElectronicRecord(maker, price, productID, name, year);
            newElectronic.setType("electronics");
            productList.add(newElectronic);
            map.put(name, newElectronic);
            System.out.println("Added: " + newElectronic.toString());
        } else {
            System.out.println("Enter the author of the book: ");
            authors = scanner.nextLine();

            System.out.println("Enter the books publisher: ");
            publisher = scanner.nextLine();

            System.out.println(name + " was added to the list.");

            BookRecord newBook = new BookRecord(authors, publisher, price, productID, name, year);
            newBook.setType("book");
            productList.add(newBook);
            map.put(name, newBook);
            System.out.println("Added: " + newBook.toString());
        }
        return productList;
    }

    public void searchList(String keyWords) {

        String[] keys = keyWords.split("\\s+");
        int i;
        ProductRecord temp;
        for (i = 0; i < keys.length; i++) {
            if (map.containsKey(keys[i])) {
                temp = map.get(keyWords);
                System.out.println(temp.toString());
            } else {
                System.out.println("Product does not exist in the list");
            }
        }
    }

    public void inputFile(ArrayList productList) {

        int price = 0;  //intialize the variables nessisary for the object
        int year = 0;
        String type = "";
        String name = "";
        String authors = "";
        String publisher = "";
        String maker = "";
        String productID = "";
        String priceS = "";
        String yearS = "";
        int eDataCollected = 0;
        int bDataCollected = 0;

        String filepath = "/home/alexlai/Desktop/products.txt";
        try {
            File f = new File(filepath);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String theData = sc.nextLine();
                if (theData.contains("type")) {
                    type = parseData(theData);
                    eDataCollected++;
                    bDataCollected++;
                    System.out.println("The type: " + type + " E count: " + eDataCollected + " B count: " + bDataCollected);
                } else if (theData.contains("productID")) {
                    productID = parseData(theData);
                    eDataCollected++;
                    bDataCollected++;
                    System.out.println("The product id: " + productID + " E count: " + eDataCollected + " B count: " + bDataCollected);
                } else if (theData.contains("name")) {
                    name = parseData(theData);
                    eDataCollected++;
                    bDataCollected++;
                    System.out.println("The name is: " + name + " E count: " + eDataCollected + " B count: " + bDataCollected);
                } else if (theData.contains("price")) {
                    priceS = parseData(theData);
                    eDataCollected++;
                    bDataCollected++;
                    System.out.println("The price: " + priceS + " E count: " + eDataCollected + " B count: " + bDataCollected);
                } else if (theData.contains("year")) {
                    yearS = parseData(theData);
                    eDataCollected++;
                    bDataCollected++;
                    System.out.println("The year: " + yearS + " E count: " + eDataCollected + " B count: " + bDataCollected);
                } else if (theData.contains("maker")) {
                    maker = parseData(theData);
                    eDataCollected++;
                    System.out.println("The maker: " + maker + " E count: " + eDataCollected + " B count: " + bDataCollected);
                } else if (theData.contains("authors")) {
                    authors = parseData(theData);
                    bDataCollected++;
                    System.out.println("The authors: " + authors + " E count: " + eDataCollected + " B count: " + bDataCollected);
                } else if (theData.contains("publisher")) {
                    publisher = parseData(theData);
                    bDataCollected++;
                    System.out.println("The publisher: " + publisher + " E count: " + eDataCollected + " B count: " + bDataCollected);
                }
                if (eDataCollected == 6) {
                    try {
                        price = Integer.parseInt(priceS);
                        year = Integer.parseInt(yearS);
                    } catch (NumberFormatException ei) {
                        price = 0;
                        year = 0;
                    }
                    ElectronicRecord newElectronic = new ElectronicRecord(maker, price, productID, name, year);
                    newElectronic.setType("electronics");
                    productList.add(newElectronic);
                    map.put(name, newElectronic);
                    System.out.println("File created: " + newElectronic.toString());
                    eDataCollected = 0;
                }
                if (bDataCollected == 7) {
                    try {
                        price = Integer.parseInt(priceS);
                        year = Integer.parseInt(yearS);
                    } catch (NumberFormatException ei) {
                        price = 0;
                        year = 0;
                    }
                    BookRecord newBook = new BookRecord(authors, publisher, price, productID, name, year);
                    newBook.setType("book");
                    productList.add(newBook);
                    map.put(name, newBook);
                    System.out.println("File created: " + newBook.toString());
                    bDataCollected = 0;
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String parseData(String s) {
        int count = 0;
        int i;
        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\"' && s.charAt(i - 1) == ' ') {
                count = i;
            }
        }
        String str1 = s.substring(count + 1);
        String str2 = str1.substring(0, str1.length() - 1);
        return str2;
    }
    
    public int exceptionHandlerInt(String question, int low, int high) {
        boolean noError = true;
        int answer = -1;
        while (noError) {
            System.out.print(question);
            try {
                answer = Integer.parseInt((scanner.nextLine()));
                if (answer >= low && answer <= high) {
                    noError = false;
                } else {
                    System.out.println("Integer not within range. Lowest # can be " + low + " and highest is " + high);
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter an integer, not letters or decimals. please");
            }

        }
        return answer;
    }

    public String exceptionHandlerString(String question, int length) {
        String answer = "";
        String myInput = "g";
        boolean error = true;
        System.out.print(question);
        while (error) {
            myInput = scanner.nextLine().trim();
            if (myInput.length() <= length && myInput.length() > 0) {
                answer = myInput;
                error = false;
            } else {
                System.out.print("\n" + question);
            }

        }
        return answer;
    }
}

