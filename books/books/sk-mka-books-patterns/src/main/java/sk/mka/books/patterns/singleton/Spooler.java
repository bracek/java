package sk.mka.books.patterns.singleton;

/**
 *
 * @author bracek
 * @date Dec 22, 2009
 */

/*
The Design Patterns Java Companion

Copyright (C) 1998, by James W. Cooper

IBM Thomas J. Watson Research Center

 */
class Spooler {
    //this is a prototype for a printer-spooler class
    //such that only one instance can ever exist

    static boolean instance_flag = false; //true if 1 instance

    public Spooler() throws SingletonException {
        if (instance_flag) {
            throw new SingletonException("Only one printer allowed");
        } else {
            instance_flag = true; //set flag for 1 instance
        }
        System.out.println("printer opened");
    }

    @Override
    public void finalize() {
        instance_flag = false;
    }
}

class SingletonException extends RuntimeException {
    //new exception type for singleton classes

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SingletonException() {
        super();
    }

    public SingletonException(String s) {
        super(s);
    }
}

