/*
 * AlreadyContainsBlockException.java
 *
 * Created on March 14, 2008, 7:37 PM
 *
 * Exception thrown when a block already exists in that position
 */

package kevtris;

/**
 *
 * @author kevingordon
 */
public class AlreadyContainsBlockException extends Exception {
    
    /** Creates a new instance of AlreadyContainsBlockException */
    public AlreadyContainsBlockException() {
    }
    
    public AlreadyContainsBlockException(String msg) {
        super(msg);
        System.out.println(msg);
        printStackTrace();
    }
}
