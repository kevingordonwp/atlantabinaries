/*
 * AlreadyContainsBlockException.java
 *
 * Created on March 14, 2008, 7:37 PM
 *
 */

package kevtris;

/**
 * Exception thrown when a block already exists in that position
 *
 * @author kevingordon
 * @version 1.0
 */
public class AlreadyContainsBlockException extends Exception {
    
    /** Creates a new instance of AlreadyContainsBlockException */
    public AlreadyContainsBlockException() {
    }
    
    /**
     * Constructor for the Already Contains Block exception
     * this will store the message in the super constructor
     * and output the message to standard output and print
     * the stack trace
     *
     * @param msg String The message given to the exception when it is thrown
     */
    public AlreadyContainsBlockException(String msg) {
        super(msg);
        System.out.println(msg);
        printStackTrace();
    }
}
