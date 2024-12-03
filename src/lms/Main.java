package lms;

import java.util.List;
import lms.entities.User;
import lms.services.UserService;


public class Main {

	public static void main(String[] args) {
		 // Create ObjectMapper instance
		try {
            // Create ObjectMapper instance
            UserService _userService = new UserService();

            // Read JSON file and map it to List<User>
            List<User> users = _userService.getUsers();

            // Print each user
            for (User user : users) {
                System.out.println(user.getFirstName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

       

	}

}
