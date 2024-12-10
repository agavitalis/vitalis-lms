package lms.controllers;

import java.io.IOException;
import java.util.Scanner;

public class UserDashboardController {
	 public static void menu() throws IOException {

	        Scanner scanner = new Scanner(System.in);
	        int userChoice = -1;

	        while (userChoice != 5) { // Keep showing the menu until the user chooses to exit
	        	System.out.println("\n------Welcome to VDL Dashboard.--------\n");
	        	System.out.println("What would you like to do:");
	        	System.out.println("1. Search books by ISBN");
	        	System.out.println("2. Borrow a Book");
	        	System.out.println("3. Return a Book.");
	        	System.out.println("4. View the Library Catalog.");
	        	System.out.println("5. Logout and Close.");


	            userChoice = -1; // Reset user choice for each loop
	            while (userChoice < 1 || userChoice > 5) {
	                System.out.print("Please select an option (1-5): ");

	                // Check if the user input is an integer
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

	            switch (userChoice) {
	                case 1:
	                	BookController.searchBook(scanner);
	                    break;
	                case 2:
	                	BookController.borrowBook(scanner);
	                    break;
	                case 3:
	                	BookController.returnBook(scanner);
	                    break;
	                case 4:
	                	BookController.getBooksCatalog();
	                    break;
	                case 5:
	                    System.out.println("Thank you for using Vitalis Digital Library. Goodbye!");
	                    scanner.close();
	                    System.exit(0); // Terminate the program
	                    break; // Technically unnecessary because of System.exit
	            }
	        }
	    }
}
