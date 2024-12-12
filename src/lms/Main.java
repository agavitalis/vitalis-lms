package lms;

import java.util.Scanner;

import lms.controllers.UserController;
import lms.controllers.BookController;
import lms.controllers.SupportController;

public class Main {

    public static void main(String[] args) {
    	System.out.println("Welcome to Vitalis Digital Library (VDL). We are pleased to see you.");
    	Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        
        while (isRunning) {
            displayMenu();

            try {
                System.out.print("Enter your choice: ");
                int userChoice = Integer.parseInt(scanner.nextLine());

                switch (userChoice) {
                    case 1 -> UserController.authenticate(scanner);
                    case 2 -> UserController.createUser(scanner);
                    case 3 -> BookController.browseCatalog();
                    case 4 -> SupportController.getSupportDetails();
                    case 5 -> {
                        System.out.println("Thank you for using Vitalis Digital Library. Goodbye!");
                        isRunning = false;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }
        }
        scanner.close();
        
    }
    
    private static void displayMenu() {
        System.out.println("Please select an option from the menu to continue:");
        System.out.println("1. Login");
        System.out.println("2. Register as Student");
        System.out.println("3. Browse Catalog");
        System.out.println("4. Contact Support");
        System.out.println("5. Logout and Close");
    }
}
