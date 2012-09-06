package sk.mka.books.patterns.singleton;

/**
 * The SingletonPattern2 class in an example of the Singleton Pattern implemented in Java. The
 * Singleton Pattern is implemented by statically initializing the "SINGLETON_INSTANCE" variable.
 *
 * @author	Andre Mare
 */
public class SingletonPattern2 {

    /**
     * The String variable, message, stores a reference to a String object that contains the message
     * to display.
     */
    private String message = "";
    /**
     * The SINGLETON_INSTANCE varibale stores a reference to a SingletonPattern2 object that is
     * initialized during the loading of the class.
     */
    private static SingletonPattern2 SINGLETON_INSTANCE = new SingletonPattern2();

    /**
     * The no-arg constructor creates a newly allocated SingletonPattern2 object. The constructor
     * further initializes the "message" member attribute.
     */
    private SingletonPattern2() {
        message = "Hello Singleton !!!";
    } // no-arg constructor

    /**
     * The getInstance method will retrieve the single instance of the class SingletonPattern2. The
     * method forms part of the "Singleton" pattern. The method will always return the only single
     * instance of the class SingletonPattern2. This instance is assigned to a member attribute.
     *
     * @return 	SingletonPattern2 The getInstance returns an instance of the class.
     */
    public static SingletonPattern2 getInstance() {
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
} // class SingletonPattern2

