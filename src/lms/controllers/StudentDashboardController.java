package lms.controllers;

import java.io.IOException;
import java.util.Scanner;

public class StudentDashboardController {

    public static void menu() throws IOException {
    	System.out.println("------ Welcome to VDL Dashboard ------");
        Scanner scanner = new Scanner(System.in);
        int userChoice = -1;

        while (userChoice != 5) { // Keep showing the menu until the user chooses to exit
        	displayMenu();
            userChoice = getUserChoice(scanner);
            
            switch (userChoice) {
                case 1 -> BookController.searchBook(scanner);
                case 2 -> BookController.borrowBook(scanner);
                case 3 -> BookController.returnBook(scanner);
                case 4 -> BookController.browseCatalog();
                case 5 -> exitApplication(scanner);
                default -> System.out.println("Invalid option! Please select a valid number.");
            }
        }
    }
    
    private static void displayMenu() {
    	 System.out.println("What would you like to do:");
         System.out.println("1. Search books by ISBN");
         System.out.println("2. Borrow a Book");
         System.out.println("3. Return a Book");
         System.out.println("4. View the Library Catalog");
         System.out.println("5. Logout and Close");
    }

    private static int getUserChoice(Scanner scanner) {
        int userChoice = -1;
        while (userChoice < 1 || userChoice > 5) {
            System.out.print("Please select an option (1-5): ");

            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (userChoice < 1 || userChoice > 5) {
                    System.out.println("Invalid option! Please select a number between 1 and 5.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
        return userChoice;
    }

    private static void exitApplication(Scanner scanner) {
        System.out.println("Thank you for using Vitalis Digital Library. Goodbye!");
        scanner.close();
        System.exit(0); // Terminate the program
    }
}
