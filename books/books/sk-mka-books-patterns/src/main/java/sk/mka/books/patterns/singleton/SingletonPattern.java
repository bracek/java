package sk.mka.books.patterns.singleton;


/**
 * The SingletonPattern1 class in an example of the Singleton Pattern implemented in Java. The 
 * Singleton Pattern is implemented by adding the synchronized keyword to the getInstance method to
 * ensure that only 1 thread may access the method. If the instance is not initialized, the method 
 * will create a new instance of the class and assign it to the "SINGLETON_INSTANCE" member 
 * attribute.
 * 
 * @author	Andre Mare
 */
public class SingletonPattern {

	/**
	 * The String variable, message, stores a reference to a String object that contains the message
	 * to display.
	 */
	private String message = ""; 
	
	/**
	 * The SINGLETON_INSTANCE varibale stores a reference to a SingletonPattern1 object.
	 */
	private static SingletonPattern SINGLETON_INSTANCE = null;
	
	/**
	 * The no-arg constructor creates a newly allocated SingletonPattern1 object. The constructor
	 * further initializes the "message" member attribute.
	 */
	private SingletonPattern() {
		message = "Hello Singleton !!!"; 
	} // no-arg constructor
	
	
	/**
	 * The getInstance method will retrieve the single instance of the class SingletonPattern1. The 
	 * method forms part of the "Singleton" pattern. The method will always return the only single 
	 * instance of the class SingletonPattern1. 
	 * 
	 * @return 	SingletonPattern1 The getInstance returns an instance of the class. 			
	 */
	public static synchronized SingletonPattern getInstance() {
		if (SINGLETON_INSTANCE == null) {
			SINGLETON_INSTANCE = new SingletonPattern();
		} // if
		
		return SINGLETON_INSTANCE;
	} // method getInstance
	
	
	/**
	 * The accessor method of the String property message.
	 * 
	 * @return	String The current value of the String property message.
	 */
	public String getMessage() {
		return message;
	} // method 
	
	
} // class SingletonPattern1
