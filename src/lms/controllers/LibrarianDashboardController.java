package lms.controllers;

import java.util.Scanner;

public class LibrarianDashboardController extends DashboardController {

    public void menu() {
    	System.out.println("------ Welcome to VDL Dashboard ------");
        Scanner scanner = new Scanner(System.in);
        int userChoice = -1;

        while (userChoice != 7) { // Keep showing the menu until the user chooses to exit
            displayMenu();
            userChoice = getUserChoice(scanner);

            switch (userChoice) {
                case 1 -> BookController.createBook(scanner);
                case 2 -> BookController.updateBook(scanner);
                case 3 -> BookController.deleteBook(scanner);
                case 4 -> BookController.browseCatalog();
                case 5 -> BookController.checkBookAvaliability(scanner);
                case 6 -> UserController.getUsers();
                case 7 -> exitApplication(scanner);
                default -> System.out.println("Invalid option! Please select a valid number.");
            }
        }
    }

    private static void displayMenu() {
    	System.out.println("\n--------------------------------");
        System.out.println("What would you like to do:");
        System.out.println("1. Create Book");
        System.out.println("2. Update Book");
        System.out.println("3. Delete Book");
        System.out.println("4. View Library Catalog");
        System.out.println("5. Check Book Avaliability");
        System.out.println("6. View Library Users");
        System.out.println("7. Logout and Close");
    }

    private static int getUserChoice(Scanner scanner) {
        int userChoice = -1;
        while (userChoice < 1 || userChoice > 7) {
            System.out.print("Please select an option (1-7): ");

            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (userChoice < 1 || userChoice > 7) {
                    System.out.println("Invalid option! Please select a number between 1 and 7.");
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