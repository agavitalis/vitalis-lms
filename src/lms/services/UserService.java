package lms.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lms.entities.*;
import lms.enums.UserRole;
import lms.interfaces.IUserService;
public class UserService implements IUserService {
	
	private static final String FILE_PATH = "data/users.json";
	
	public List<User> getUsers() throws IOException {
		 ObjectMapper mapper = new ObjectMapper();
         List<User> users = mapper.readValue(new File(FILE_PATH), new TypeReference<List<User>>() {});
         return users;
	}
	    
    public void saveUser(String firstName, String lastName, String email, String password, UserRole role) throws IOException {
        List<User> users = getUsers();
        
        for (User user : users) {
        	 System.out.println(user.getFirstName());
            if (user.getEmail().equals(email)) {
                System.out.println("Error: Email already registered.");
                return;
            }
        }

        User newUser = new User(firstName, lastName, email, password,role.toString());
        users.add(newUser);
        
        // Specify the correct file path
        File file = new File(FILE_PATH);
        
        // Create ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            // Write the users list to the file with pretty printing
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();  
        }
        
        System.out.println(String.format(
			    "\nRegistration Successful!\n" +
			    "First Name: %s\n" +
			    "Last Name: %s\n" +
			    "Email: %s", 
			    firstName, lastName, email));
    }
    
    public User getUser(String email, String password)throws IOException {
    	List<User> users = getUsers();
        for (User user : users) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                return user; 
            }
        }
        return null; 
    }
    
}
