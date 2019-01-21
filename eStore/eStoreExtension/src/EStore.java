package A2; //a2 package

import java.io.File; //imports
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Date: November 9th 2015 Student #: 0920158 Assignment #: 2 Description: This
 * class is an EStore which has members map and productList that store products.
 * This Class can add new members to the list or search for specific elements in
 * the list by using the name of the product as the key This EStore class that
 * has an Arrray List and a Hash Map which can perform functions utilizing the
 * ProductRecord, BookRecord and Electronic Record Classes. To use the store I
 * recomend opeinging a new store and calling the menu
 *
 * @author alexlai
 */
public class EStore { //eStore class

    private ArrayList<ProductRecord> productList; //class members of the EStore
    private HashMap<String, ProductRecord> map;
    public static Scanner scanner = new Scanner(System.in);

    public EStore() throws Exception { //constructor for the EStore that creates instances of the product list and hash map
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
/**
 * parameters: This function takes in a productList and a command line arguement that holds the file path
 * If the file path is shorter than 5 (just the value i chose because no path is less than length 5 /C:/home/ minimum)
 * This function prints out to an output file weather its provided or not in the correct format that allows the information
 * to be loaded back into the EStore when it is reopened
 * This void function has no return. 
 * 
     * @param productList
 */
    public void outputToFile(ArrayList productList, String cmdLine) { //function that prints all products to a file
        try { //try to open the file
            if (cmdLine.length() < 5) { //check if user actually entered a file path
                cmdLine = "productsOutput.txt"; //set default file path
            }
            PrintWriter fileWriter = new PrintWriter(cmdLine, "UTF-8"); //create a file path
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
/**
 * parameters: This function takes in an ArrayList of type ProductRecord
 * This method asks the user to input all nessisary information to create a new product and add it to the list and map
 * This void function returns the new updated arraylist
 * 
 */
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
        String priceS;
        boolean found = false;

        do {
            productID = exceptionHandlerString("Enter the products ID (Must be unique)", 7);
            found = false;
            for (i = 0; i < productList.size(); i++) {//loop list
                ProductRecord pTemp = (ProductRecord) productList.get(i);
                if (pTemp.getProductID().contentEquals(productID)) {
                    found = true;//loop agian
                }
            }
        } while (found == true);

        System.out.println();
        bookOrElectronic = exceptionHandlerInt("Enter 1 to add an electronic and 2 for a book", 1, 2);

        System.out.println();
        price = exceptionHandlerInt("Enter the price of the product:", 0, 2000000);

        System.out.println();
        year = exceptionHandlerInt("Enter the year the product was made: ", 0, 2020);

        System.out.println();
        name = exceptionHandlerString("Enter the name of the product: ", 50);

        if (bookOrElectronic == 1) {
            System.out.println();
            maker = exceptionHandlerString("Enter the maker of the electronic: ", 50);
            ElectronicRecord newElectronic = new ElectronicRecord(maker, price, productID, name, year);
            newElectronic.setType("electronics");
            productList.add(newElectronic);
            name = name.toLowerCase();
            map.put(name, newElectronic);
            System.out.println("Added: " + newElectronic.toString());
        } else {
            authors = exceptionHandlerString("Enter the author of the book: ", 50);
            publisher = exceptionHandlerString("Enter the books publisher: ", 50);
            System.out.println(name + " was added to the list.");
            BookRecord newBook = new BookRecord(authors, publisher, price, productID, name, year);
            newBook.setType("book");
            productList.add(newBook);
            name = name.toLowerCase();
            map.put(name, newBook);
            System.out.println("Added: " + newBook.toString());
        }
        return productList;
    }
/**
 * parameters: This function takes in a word or multiple and searches the map to see if the key is in the list
 * To properly use this function you should enter a name of a product and it will search for that product in the list
 * This void function has no return. This function prints out product not in the list if the key is not found
 * 
 */
    public void searchList(String keyWords) {
        int i;
        ProductRecord temp;
        keyWords = keyWords.toLowerCase();
        if (keyWords.contains(" ")) {
            temp = map.get(keyWords);
                if (temp != null) {
                    System.out.println(temp.toString());
                } else {
                    System.out.println(keyWords + " does not exist in the list");
                }
        } 
        String[] keys = keyWords.split("\\s+");
            for (i = 0; i < keys.length; i++) {
                if (map.containsKey(keys[i])) {
                    temp = map.get(keys[i]);
                    System.out.println(temp.toString());
                } else {
                    System.out.println(keys[i] + " does not exist in the list");
                }
            }
    }
/**
 * parameters: Takes in the product list and the command line file path
 * This function is of type void and has no return it just updates the product list with new members that are in the input file 
 * This function is called in the main method before the menu function so the EStore has some intial products
 * This void function has no return. 
 * 
 */
    public void inputFile(ArrayList productList, String cmdLine) {

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

        String filepath = cmdLine;
        if (filepath.length() > 6) {

            try {
                File f = new File(filepath); //open the file
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) { //get the line
                    String theData = sc.nextLine();
                    if (theData.contains("type")) {
                        type = parseData(theData);//get the data from the line
                        eDataCollected++; //incremets so you can create multiple objects in the file
                        bDataCollected++;
                    } else if (theData.contains("productID")) {//check if line contains the data
                        productID = parseData(theData);
                        eDataCollected++;
                        bDataCollected++;
                    } else if (theData.contains("name")) {
                        name = parseData(theData);
                        eDataCollected++;
                        bDataCollected++;
                    } else if (theData.contains("price")) {
                        priceS = parseData(theData);
                        eDataCollected++;
                        bDataCollected++;
                    } else if (theData.contains("year")) {
                        yearS = parseData(theData);
                        eDataCollected++;
                        bDataCollected++;
                    } else if (theData.contains("maker")) {
                        maker = parseData(theData);
                        eDataCollected++;
                    } else if (theData.contains("authors")) {
                        authors = parseData(theData);
                        bDataCollected++;
                    } else if (theData.contains("publisher")) {
                        publisher = parseData(theData);
                        bDataCollected++;
                    }
                    if (eDataCollected == 6) {
                        try {
                            price = Integer.parseInt(priceS);
                            year = Integer.parseInt(yearS);
                        } catch (NumberFormatException ei) {
                            price = 0;
                            year = 0;
                        }
                        ElectronicRecord newElectronic = new ElectronicRecord(maker, price, productID, name, year);//create the producte of type electronic
                        newElectronic.setType("electronics");//set type for later use
                        productList.add(newElectronic);//add to the product list
                        name = name.toLowerCase();
                        map.put(name, newElectronic); //hash the name and put the object in the map
                        System.out.println("File created: " + newElectronic.toString());
                        eDataCollected = 0;
                    }
                    if (bDataCollected == 7) {//make sure all vals are parsed
                        try {
                            price = Integer.parseInt(priceS);
                            year = Integer.parseInt(yearS);
                        } catch (NumberFormatException ei) {
                            price = 0;
                            year = 0;
                        }
                        BookRecord newBook = new BookRecord(authors, publisher, price, productID, name, year);
                        newBook.setType("book");
                        productList.add(newBook);//add book to list
                        name = name.toLowerCase();
                        map.put(name, newBook);//use hasmap to hash the nan=me
                        System.out.println("File created: " + newBook.toString());
                        bDataCollected = 0;
                    }
                }
                sc.close(); //stop scanning the file
            } catch (Exception e) {//catch any file exception
                System.out.println("Incorrect file path");
            }
        }
        System.out.println("Welcome to the E Store");
    }

    public String parseData(String s) {//take in tbe line
        int count = 0;//find the first quote
        int i;
        for (i = 0; i < s.length(); i++) { //loop until found
            if (s.charAt(i) == '\"' && s.charAt(i - 1) == ' ') {
                count = i;
            }
        }
        String str1;
        String str2 = "";
        try {
            str1 = s.substring(count + 1); //take substring starting after the first quote
            str2 = str1.substring(0, str1.length() - 1); //remove the ending quote of the new string

        } catch (Exception e) {
            System.out.println("Input not parsed properly(format should be type = \"book\")");
        }
        return str2; //return new string
    }
/**
 * parameters: This function takes in a question that is of type string and also the 
 * min and max values of the int value should be
 * This method loops asking the user to enter an integer in the range
 * This function returns an integer in the range 
 * 
 */
    public int exceptionHandlerInt(String question, int low, int high) {//range of input
        boolean noError = true;
        int answer = -1;//init answer
        while (noError) {
            System.out.print(question);//prints question passed in from parameter
            System.out.println();
            try { //try to parse the string to int 
                answer = Integer.parseInt((scanner.nextLine()));//scan for user input
                if (answer >= low && answer <= high) { //range of input
                    noError = false;//set error
                } else {
                    System.out.println("Integer not within range. Lowest # can be " + low + " and highest is " + high);//print error message
                }
            } catch (NumberFormatException e) { //catch exeception if user inputs something other than an int
            }

        }
        return answer;
    }
/**
 * parameters: takes in a question of type string and the max length the string should be
 * This method loops asking the user to input a correct string until condition is satisfied
 * This function returns a string with no possible errors. 
 * 
 */
    public String exceptionHandlerString(String question, int length) {//take in parameters 
        String answer = "";
        String myInput = "g";
        boolean error = true;
        System.out.print(question);
        while (error) { //loop until correct input
            myInput = scanner.nextLine().trim(); //remove possible whiete space
            if (myInput.length() <= length && myInput.length() > 0) {
                answer = myInput;
                error = false;
            } else {
                System.out.print("\n" + question);
            }

        }
        return answer; //returns a error free string
    }

/**
 * parameters.com: takes in a EStore instance and a string that holds a file path
 * This method loops through 3 options asking the user to create a new product,
 * Or search the product list and finally quit the program.
 * This void function has no return. 
 * 
 */
    public static void menu(EStore myStore, String[] s) { //take in a new EStore instance and command line input. 

        int choice = 0;
        int exit = 0;
        int newID = 0;
        int isBook = 0;
        String cmdLine;

        cmdLine = Arrays.toString(s);
        String userKeyInput;
        String input;
        String[] quittingOptions = {"q", "Q", "quit", "Quit", "QUIT"};

        while (exit != 1) {
            System.out.println("Enter 1 to add, 2 to search or Quit: ");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Creating product...");
                    myStore.addProduct(myStore.getProductList());
                    break;
                case "2":
                    System.out.println();
                    userKeyInput = myStore.exceptionHandlerString("Enter key words to use to search list: ", 50);
                    myStore.searchList(userKeyInput);
                    break;
                case "3":
                    System.out.println("Program ended.");
                    exit = 1;
                    break;
                default:
                    for (String quittingOption : quittingOptions) {
                        if (input.equals(quittingOption)) {
                            exit = 1;
                        }
                    }
                    if (exit != 1) {
                        System.out.println("Please enter a number again");
                    }
                    break;
            }
        }
    }
}
