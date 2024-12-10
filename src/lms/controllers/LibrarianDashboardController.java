package lms.controllers;

import java.util.Scanner;

public class LibrarianDashboardController {
	 public static void menu() {

	        Scanner scanner = new Scanner(System.in);
	        int userChoice = -1;

	        while (userChoice != 5) { // Keep showing the menu until the user chooses to exit
	            System.out.println("\n------Welcome to Digital Library Dashboad.--------\n"
	                    + "What would you like to do:\n"
	                    + "1. Create Book\n"
	                    + "2. Update Book \n"
	                    + "3. Delete Book .\n"
	                    + "4. View Library Catalog.\n"
	                    + "4. View Library Users.\n"
	                    + "5. Logout and Close.");
	           

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
	                	BookController.createBook(scanner);
	                    break;
	                case 2:
	                	BookController.updateBook(scanner);
	                    break;
	                case 3:
	                	BookController.deleteBook(scanner);
	                    break;
	                case 4:
	                	BookController.getBooksCatalog();
	                    break;
	                case 5:
	                	UserController.getUsers();
	                    break;
	                case 6:
	                    System.out.println("Thank you for using Vitalis Digital Library. Goodbye!");
	                    scanner.close();
	                    System.exit(0); // Terminate the program
	                    break; // Technically unnecessary because of System.exit
	            }
	        }
	    }
}
