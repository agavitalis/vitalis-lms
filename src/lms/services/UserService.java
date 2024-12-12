package lms.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lms.entities.*;
import lms.interfaces.IUserService;

public class UserService implements IUserService {

	private static final String FILE_PATH = "data/users.json";
	private static final String FILE_PATH_LOGIN = "data/logged_user.json";

	public void createUser(User newUser) throws IOException {
		List<User> users = getUsers();

		for (User user : users) {
			if (user.getEmail().equals(newUser.getEmail())) {
				System.out.println("Error: Email already registered.");
				return;
			}
		}

		users.add(newUser);
		saveUsersToFile(users);

	}

	public List<User> getUsers() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<User> users = mapper.readValue(new File(FILE_PATH), new TypeReference<List<User>>() {
		});
		return users;
	}

	public User getUser(String email) throws IOException {
		List<User> users = getUsers();
		for (User user : users) {
			if (email.equals(user.getEmail())) {
				return user;
			}
		}
		return null;
	}

	public User authenticateUser(String email, String password) throws IOException {
		List<User> users = getUsers();
		for (User user : users) {
			if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
				System.out.println("\nLogin Successful!" );
				saveLoggedInUserToFile(user);
				return user;
			}
		}
		return null;
	}

	public User getLoggedInUser() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(new File(FILE_PATH_LOGIN), new TypeReference<User>() {
		});
		
		if(user == null) {
			System.out.println("You are not logged in");
			return null;
		}
		return user;
	}

	private void saveUsersToFile(List<User> users) throws IOException {
		File file = new File(FILE_PATH);
		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
		} catch (IOException e) {
			System.out.println("An error occurred while saving the books.");
			e.printStackTrace();
		}
	}

	private void saveLoggedInUserToFile(User user) throws IOException {
		File file = new File(FILE_PATH_LOGIN);
		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(file, user);
		} catch (IOException e) {
			System.out.println("An error occurred while saving the books.");
			e.printStackTrace();
		}
	}

}
