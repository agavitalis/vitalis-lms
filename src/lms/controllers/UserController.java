package lms.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import lms.entities.User;
import lms.enums.UserRole;
import lms.services.UserService;
import lms.utils.*;

public class UserController {
	
	public static void createUser(Scanner scanner) {

		String firstName = "", lastName = "", email = "", password = "";

		while (firstName.isEmpty()) {
			System.out.print("Enter your first name: ");
			firstName = scanner.nextLine();
			if (firstName.isEmpty()) {
				System.out.println("First name cannot be empty. Please enter a valid first name.");
			}
		}

		while (lastName.isEmpty()) {
			System.out.print("Enter your last name: ");
			lastName = scanner.nextLine(); // Read last name
			if (lastName.isEmpty()) {
				System.out.println("Last name cannot be empty. Please enter a valid last name.");
			}
		}

		while (email.isEmpty() || !LmsUtil.isValidEmail(email)) {
			System.out.print("Enter your email: ");
			email = scanner.nextLine(); // Read email
			if (email.isEmpty()) {
				System.out.println("Email cannot be empty. Please enter a valid email.");
			} else if (!LmsUtil.isValidEmail(email)) {
				System.out.println("Invalid email format. Please enter a valid email.");
			}
		}

		while (password.isEmpty()) {
			System.out.print("Enter your password: ");
			password = scanner.nextLine(); // Read password
			if (password.isEmpty()) {
				System.out.println("Password cannot be empty. Please enter a valid password.");
			}
		}

		// send to user service
		UserService userService = new UserService();
		try {
			User newUser = new User(firstName, lastName, email, password,UserRole.STUDENT.toString());
			userService.createUser(newUser);
			System.out.println("Student registration successful.");
		} catch (IOException e) {
			System.out.println("Internal server error: " + e.getMessage());
		}
	}

	public static User authenticate(Scanner scanner) {

		String email = "", password = "";

		while (email.isEmpty() || !LmsUtil.isValidEmail(email)) {
			System.out.print("Enter your email: ");
			email = scanner.nextLine(); // Read email
			if (email.isEmpty()) {
				System.out.println("Email cannot be empty. Please enter a valid email.");
				
			} else if (!LmsUtil.isValidEmail(email)) {
				System.out.println("Invalid email format. Please enter a valid email.");
			}
		}

		while (password.isEmpty()) {
			System.out.print("Enter your password: ");
			password = scanner.nextLine(); // Read password
			if (password.isEmpty()) {
				System.out.println("Password cannot be empty. Please enter a valid password.");
			}
		}

		// send to user service
		UserService userService = new UserService();
		try {
			User user = userService.authenticateUser(email, password);
			
			if(user.getRole() == UserRole.STUDENT.toString()) {
				UserDashboardController.menu();
			}else {
				LibrarianDashboardController.menu();
			}
			
		} catch (IOException e) {
			System.out.println("Internal server error: " + e.getMessage());
		}
		return null;
	}

	public static List<User> getUsers() {

		UserService userService = new UserService();
		try {

			System.out.println("Here is a list of our users\n:");

			for (User user : userService.getUsers()) {
				System.out.println("-----------------------------------");
				System.out.println("FirstName: " + user.getFirstName());
				System.out.println("LastName: " + user.getLastName());
				System.out.println("Email: " + user.getEmail());
				System.out.println("Role: " + user.getRole());
			}

		} catch (IOException e) {
			System.out.println("Internal server error: " + e.getMessage());
		}

		return null;
	}

	

}
