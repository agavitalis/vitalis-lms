package lms.utils;

public class LmsUtil {

	public static boolean isValidEmail(String email) {
		return email.contains("@") && email.contains(".");
	}
}
