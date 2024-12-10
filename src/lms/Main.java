package lms;

import java.util.Scanner;

import lms.controllers.UserController;
import lms.controllers.BookController;
import lms.controllers.SupportController;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int userChoice = -1;

        while (userChoice != 5) { // Keep showing the menu until the user chooses to exit
            System.out.println("Welcome to Vitalis Digital Library, We are pleased to see you.\n"
                    + "Pls select an option from this menu to continue:\n"
                    + "1. Login\n"
                    + "2. Register as User\n"
                    + "3. Browse Catalog.\n"
                    + "4. Contact Support.\n"
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
                    UserController.login(scanner);
                    break;
                case 2:
                    UserController.register(scanner);
                    break;
                case 3:
                    BookController.getBooksCatalog();
                    break;
                case 4:
                    SupportController.getSupportDetails();
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
