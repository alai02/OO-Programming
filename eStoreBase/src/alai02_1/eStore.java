/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alai02_1;

import static alai02_1.BookRecord.addBook;
import static alai02_1.ElectronicRecord.addElectronic;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class eStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Welcome, this is a store for books and electronics!");
        menu();

    }

    /**
     * @param args the command line arguments
     */
    public static void searchFunction(ArrayList electronicList, ArrayList bookList) {

        int startingYear = 0;
        int endingYear = 0;
        int singleYear = 0;
        boolean isSingle = true;
        String yearRange;
        String productID;
        String wordOrPhrase;
        String yearString = "";
        String idString = "";
        String wordString = "";

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the year you would like to search: (can leave blank)");
        yearRange = sc.nextLine();
        System.out.println("Enter the product ID: (can leave blank)");
        productID = sc.nextLine();
        System.out.println("Enter a word or multiple to search lists: (can leave blank)");
        wordOrPhrase = sc.nextLine();

        //search for the year
        if (yearRange.length() > 0) {
            if (yearRange.contains("-")) {
                startingYear = Integer.valueOf(yearRange.split("-")[0]);
                endingYear = Integer.valueOf(yearRange.split("-")[1]);
                isSingle = false;
            } else {
                try {
                    singleYear = Integer.valueOf(yearRange);
                    isSingle = true;
                } catch (NumberFormatException e) {

                }
            }
        }
        String[] words = wordOrPhrase.split("\\s+");
        if (wordOrPhrase.length() > 0) {
            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].replaceAll("[^\\w]", "");
            }
        }
        wordString = searchWords(electronicList, bookList, words);
        yearString = searchYear(electronicList, bookList, isSingle, singleYear, startingYear, endingYear);
        idString = searchID(electronicList, bookList, productID);

        if (yearRange.length() > 0) {
            if (productID.length() > 0) {
                if (wordOrPhrase.length() > 0) {
                    try {
                        if (wordString.equals(yearString) && wordString.equals(idString)) {
                            System.out.println(wordString);
                        }
                    } catch (Exception e) {

                    }
                } else if (yearString.equals(idString)) {
                    System.out.println(yearString);
                } else {
                    System.out.println("Item not found");
                }
            } else if (wordOrPhrase.length() > 0) {
                if (wordOrPhrase.equals(yearString)) {
                    System.out.println(yearString);
                } else {
                    System.out.println("Item not found");
                }
            }
        }
        try {
            if (idString.equals(wordString)) {
                System.out.println(wordString);
            }
        } catch (Exception e) {

        }

    }

    public static String searchYear(ArrayList electronicList, ArrayList bookList, boolean isSingle, int singleYear, int start, int end) {

        if (isSingle == true) {
            int i;
            for (i = 0; i < bookList.size(); i++) {
                BookRecord test = new BookRecord(0, null, 0);
                test = (BookRecord) bookList.get(i);
                if (test.getYear() == singleYear) {
                    return test.toString();
                }
            }
        } else {
            int i;
            for (i = 0; i < bookList.size(); i++) {
                BookRecord test = new BookRecord(0, null, 0);
                test = (BookRecord) bookList.get(i);
                if (test.getYear() > start && test.getYear() < end) {
                    return test.toString();
                }
            }
        }
        //check the electronic list
        if (isSingle == true) {
            int i;
            for (i = 0; i < electronicList.size(); i++) {
                ElectronicRecord test = new ElectronicRecord(0, null, 0);
                test = (ElectronicRecord) electronicList.get(i);
                if (test.getYear() == singleYear) {
                    return test.toString();
                }
            }
        } else {
            int i;
            for (i = 0; i < electronicList.size(); i++) {
                ElectronicRecord test = new ElectronicRecord(0, null, 0);
                test = (ElectronicRecord) electronicList.get(i);
                if (test.getYear() > start && test.getYear() < end) {
                    return test.toString();
                }
            }
        }
        return null;
    }

    public static String searchWords(ArrayList electronicList, ArrayList bookList, String[] words) {

        int i;

        for (i = 0; i < bookList.size(); i++) {
            BookRecord test = new BookRecord(0, null, 0);
            test = (BookRecord) bookList.get(i);
            if (test.getName().contains(words[i])) {
                return test.toString();
            }
        }

        for (i = 0; i < electronicList.size(); i++) {
            ElectronicRecord test = new ElectronicRecord(0, null, 0);
            test = (ElectronicRecord) electronicList.get(i);
            if (test.getName().contains(words[i])) {
                return test.toString();
            }
        }
        return null;

    }

    public static String searchID(ArrayList electronicList, ArrayList bookList, String productID) {
        int i;
        int sucessfulPrint = 0;

        for (i = 0; i < bookList.size(); i++) {
            BookRecord test = new BookRecord(0, null, 0);
            test = (BookRecord) bookList.get(i);
            try {
                if (Integer.valueOf(productID) == test.getProductID()) {
                    return test.toString();
                }
            } catch (Exception e) {

            }
        }

        for (i = 0; i < electronicList.size(); i++) {
            ElectronicRecord test = new ElectronicRecord(0, null, 0);
            test = (ElectronicRecord) electronicList.get(i);
            if (Integer.valueOf(productID) == test.getProductID()) {
                return test.toString();
            }
        }
        return null;
    }

    public static int idCheck(ArrayList electronicList, ArrayList bookList, int newID) {

        int i, j;
        int length = String.valueOf(newID).length();
        for (i = 0; i < electronicList.size(); i++) {
            ElectronicRecord electronicCompare = new ElectronicRecord(0, null, 0);
            electronicCompare = (ElectronicRecord) electronicList.get(i);
            if (electronicCompare.getProductID() == newID) {
                return 1;
            }
        }
        for (j = 0; j < bookList.size(); j++) {
            BookRecord bookCompare = new BookRecord(0, null, 0);
            bookCompare = (BookRecord) bookList.get(j);
            if (bookCompare.getProductID() == newID) {
                return 1;
            }
        }
        if (length != 6) {
            System.out.println("ERROR: The product ID needs to be 6 consecutive integers");
            return 0;
        }
        return 0;
    }

    public static void menu() {

        ArrayList<ElectronicRecord> electronicList = new ArrayList<>();
        ArrayList<BookRecord> bookList = new ArrayList<>();

        BookRecord AbsoluteJava = new BookRecord(100000, 123456, "Absolute Java", 2015, "Walter Savitch, Kendrik Mock", "Person");
        bookList.add(AbsoluteJava);

        int newID = 0;
        int isBook = 0;
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice != 3) {
            choice = exceptionHandlerInt("Enter 1 to add, 2 to search and 3 to quit: ", 1, 3);
            switch (choice) {
                case 1:
                    System.out.println("Press 1 to add a book and 2 to add an electronic");
                    isBook = sc.nextInt();
                    System.out.println("Enter the product ID: ");
                    newID = sc.nextInt();
                    if (idCheck(electronicList, bookList, newID) == 0) {
                        if (isBook == 1) {
                            addBook(bookList, newID);
                        } else if (isBook == 2) {
                            addElectronic(electronicList, newID);
                        } else {
                            System.out.println("You can only enter a number 1 or 2 ");
                        }
                    }
                    break;
                case 2:
                    searchFunction(electronicList, bookList);
                    break;
                case 3:
                    System.out.println("Program ended."); //implement other ways to quit ex. q,  quit, exit, Quit
                    break;
                default:
                    System.out.println("Please enter a number again");
                    break;
            }
        }
    }
    
    public static int exceptionHandlerInt(String question, int low, int high) {
        Scanner sc = new Scanner(System.in);
        boolean noError = true;
        int answer = -1;
        while (noError) {
            System.out.println(question);

            try {
                answer = Integer.parseInt((sc.nextLine()));
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
}
