package stibrik.springapp.util;

/**
 * The Class StringUtil.
 * 
 * @author Ondrej Stibrik
 */
public class StringUtil {

	// -------------------------------- ATTRS ----------------------------------

	// ----------------------------- CONSTRUCTORS ------------------------------

	// -------------------------------- METHODS --------------------------------
	/**
	 * Checks if is empty.
	 * 
	 * @param str the str
	 * 
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String str) {
		if (str == null)
			return true;
		
		if (str.equals(""))
			return true;
		
		return false;
	}
	

	/**
	 * Checks if is not empty.
	 * 
	 * @param str the str
	 * 
	 * @return true, if is not empty
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	/**
	 * Method wraps given string by <i>redirect:</i> and <i>.do</i>.
	 * 
	 * @param viewName the view name
	 * 
	 * @return the string
	 */
	public static String doRedirect(String viewName) {
		return "redirect:"+viewName+".do";
	}
}
