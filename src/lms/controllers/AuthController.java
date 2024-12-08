package lms.controllers;

import java.io.IOException;
import java.util.Scanner;

import lms.enums.UserRole;
import lms.services.UserService;

public class AuthController {
	 public static void register(Scanner scanner) {

	        String firstName = "", lastName = "", email = "", password  = "";
	        
	        while (firstName.isEmpty()) {
	            System.out.print("Enter your first name: ");
	            firstName = scanner.nextLine(); 
	            if (firstName.isEmpty()) {
	                System.out.println("First name cannot be empty. Please enter a valid first name.");
	            }
	        }

	        while (lastName.isEmpty()) {
	            System.out.print("Enter your last name: ");
	            lastName = scanner.nextLine();  // Read last name
	            if (lastName.isEmpty()) {
	                System.out.println("Last name cannot be empty. Please enter a valid last name.");
	            }
	        }

	        while (email.isEmpty() || !isValidEmail(email)) {
	            System.out.print("Enter your email: ");
	            email = scanner.nextLine();  // Read email
	            if (email.isEmpty()) {
	                System.out.println("Email cannot be empty. Please enter a valid email.");
	            } else if (!isValidEmail(email)) {
	                System.out.println("Invalid email format. Please enter a valid email.");
	            }
	        }

	        while (password.isEmpty()) {
	            System.out.print("Enter your password: ");
	            password = scanner.nextLine();  // Read password
	            if (password.isEmpty()) {
	                System.out.println("Password cannot be empty. Please enter a valid password.");
	            }
	        }

	        //send to user service
	        UserService userService = new UserService();
	        try {
				userService.saveUser(firstName, lastName, email, password, UserRole.USER);
			} catch (IOException e) {
				System.out.println("Internal server error: " + e.getMessage());
			}
	    }

	    // Simple email validation function
	    public static boolean isValidEmail(String email) {
	        return email.contains("@") && email.contains(".");
	    }
}