package sk.mka.codes;

/**
*
* @author bracek
*/

/**
 *	PLEASE COMMIT CURRENT VERSION ALSO TO ANDROID CLIENT PROJECT !!! 
 */
public class ErrorCodes {
	// GENERAL
	public final static String GENERAL_OK = "OK";
	public final static String GENERAL_ERROR = "ERROR";
	public final static String GENERAL_WRONG_ARGUMENT = "WRONG_ARGUMENT";	
	
	// CODE TABLE
	public final static String CODE_TABLE_CODE_NOT_FOUND = "CODE_NOT_FOUND";
	public final static String CODE_TABLE_CODE_NOT_UNIQUE = "CODE_NOT_UNIQUE";
	
	// USER
	public final static String USER_USER_NOT_FOUND = "USER_NOT_FOUND";
	public final static String USER_USER_NOT_UNIQUE = "USER_NOT_UNIQUE";
	
	// AUTHORIZATION
	public final static String AUTHORIZATION_KEY_MISSING = "AUTHORIZATION_KEY_MISSING";
	public final static String AUTHORIZATION_KEY_INVALID = "AUTHORIZATION_KEY_INVALID";

	// AUTHENTICATION
	public final static String AUTH_ACCOUNT_OR_PASSWORD_IS_NULL = "ACCOUNT_OR_PASSWORD_IS_NULL";
	public final static String AUTH_ACCOUNT_TYPE_NOT_DEFINED = "ACCOUNT_TYPE_NOT_DEFINED";
	public final static String AUTH_BAD_USERNAME_OR_PASSWORD = "BAD_USERNAME_OR_PASSWORD";
	public final static String AUTH_CONNECTION_ERROR = "CONNECTION_ERROR";
	
	// PROFILE
	public final static String PROFILE_NOT_UNIQUE = "PROFILE_NOT_UNIQUE";
	public final static String PROFILE_NOT_FOUND = "PROFILE_NOT_FOUND";
}
