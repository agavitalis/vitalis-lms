package lms.interfaces;

import java.io.IOException;
import java.util.List;

import lms.entities.User;
import lms.enums.UserRole;

public interface IUserService {
    /**
     * Save a new user to the database.
     *
     * @param email     The email of the user to save.
     * @param password  The password of the user to save.
     * @param role  The type of the user (e.g., admin, regular user, librarian ).
     * @throws IOException If there is an issue accessing or modifying the data file.
     */
	void createUser(User newUser) throws IOException;
   
    /**
     * Authenticate a user based on email and password.
     *
     * @param email     The email of the user attempting to authenticate.
     * @param password  The password of the user attempting to authenticate.
     * @return          True if authentication is successful, false otherwise.
     * @throws IOException If there is an issue accessing the data file.
     */
    User authenticateUser(String email, String password) throws IOException;
    
    /**
     * Authenticate a user based on email and password.
     *
     * @param email     The email of the user attempting to authenticate.
     
     * @return          User if a user with the email is found.
     * @throws IOException If there is an issue accessing the data file.
     */
     User getUser(String email)throws IOException;

    /**
     * Retrieve all users from the database.
     *
     * @return          A list of all users.
     * @throws IOException If there is an issue accessing the data file.
     */
    List<User> getUsers() throws IOException;
}
