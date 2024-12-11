package lms.interfaces;

import java.io.IOException;
import java.util.List;

import lms.entities.User;
import lms.enums.UserRole;

public interface IUserService {
    
	void createUser(User newUser) throws IOException;
   
    User authenticateUser(String email, String password) throws IOException;
    
    User getUser(String email)throws IOException;

    List<User> getUsers() throws IOException;
    
    User getLoggedInUser() throws IOException;
}
