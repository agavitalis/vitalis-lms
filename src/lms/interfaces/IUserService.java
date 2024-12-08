package lms.interfaces;

import java.io.IOException;
import java.util.List;

import lms.entities.User;

public interface IUserService {
    /**
     * Save a new user to the database.
     *
     * @param email     The email of the user to save.
     * @param password  The password of the user to save.
     * @param role  The type of the user (e.g., admin, regular user).
     * @throws IOException If there is an issue accessing or modifying the data file.
     */
	void saveUser(String firstName, String lastName, String email, String password, String role) throws IOException;
   
    /**
     * Authenticate a user based on email and password.
     *
     * @param email     The email of the user attempting to authenticate.
     * @param password  The password of the user attempting to authenticate.
     * @return          True if authentication is successful, false otherwise.
     * @throws IOException If there is an issue accessing the data file.
     */
    User getUser(String email, String password) throws IOException;

    /**
     * Retrieve all users from the database.
     *
     * @return          A list of all users.
     * @throws IOException If there is an issue accessing the data file.
     */
    List<User> getUsers() throws IOException;
}