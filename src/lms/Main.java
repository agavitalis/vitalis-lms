package lms;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Vitalis Digital Library, We are pleased to see you.\n"
				+ "Pls select an option from this menu to continue:\n" + "1. Login\n" + "2. Register as User\n"
				+ "3. Browse Catalog.\n" + "4. Contact Support.\n" + "5. Logout and Close.");

		int userChoice = -1;
		while (userChoice < 1 || userChoice > 5) {
			System.out.print("Please select an option (1-5): ");

			// Check if the user input is an integer
			if (scanner.hasNextInt()) {
				userChoice = scanner.nextInt();
				if (userChoice < 1 || userChoice > 5) {
					System.out.println("Invalid option! Please select a number between 1 and 4.");
				}
			} else {
				System.out.println("Invalid input! Please enter a valid number.");
				scanner.next(); // Clear the invalid input
			}

			if (userChoice == 5) {
				System.out.println("Exiting the program.");
				scanner.close();
				System.exit(0); // terminate the program
			}
		}

		switch (userChoice) {
		case 1:
			System.out.println("You selected Login.");
			break;
		case 2:
			System.out.println("You selected Register as User.");
			break;
		case 3:
			System.out.println("You selected Browse Catalog.");
			break;
		case 4:
			System.out.println("You selected Contact support.");
			break;
		}

	}

}
