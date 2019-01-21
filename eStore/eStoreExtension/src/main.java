/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A2;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author alexlai
 */
public class main {

    public static Scanner scanner = new Scanner(System.in);

    /**
     * This is a main class thats sole purpose is to ask for user to enter a file path and open a new instance of the ESTore
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Enter a file path(Enter to leave blank)");
        String path = scanner.nextLine();

        try {
            EStore myStore = new EStore();
            String cmdLine;
            cmdLine = Arrays.toString(args);
            myStore.inputFile(myStore.getProductList(), path);
            myStore.menu(myStore, args);
            myStore.outputToFile(myStore.getProductList(), path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
