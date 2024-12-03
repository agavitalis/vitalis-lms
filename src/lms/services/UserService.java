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
	
	public List<User> getUsers() throws IOException {
		 ObjectMapper mapper = new ObjectMapper();
         List<User> users = mapper.readValue(new File(FILE_PATH), new TypeReference<List<User>>() {});
         return users;
	}
	    
    public void saveUser(String firstName, String lastName, String email, String password, String role) throws IOException {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("Error: Email already registered.");
                return;
            }
        }

        User newUser = new User(firstName, lastName, email, password,role);
        users.add(newUser);
      
        File file = new File("FILE_PATH");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
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
