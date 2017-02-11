/*
 * KevtrisConstants.java
 *
 * Created on 26 November 2007, 22:00
 *
 * This class contains the constants used by the Kevtris
 * applet
 */

package kevtris;

/**
 *
 * @author Kevin Gordon
 */
public class KevtrisConstants {

    final static public int SCREEN_LENGTH = 450; // 15 * 30
    final static public int SCREEN_WIDTH = 225; // 15 * 15
    final static public int GAP = 15;
    
    //Movement is 0 - down, 1 - left and 2 - right
    final static public int MOVE_DOWN = 0;
    final static public int MOVE_LEFT = 1;
    final static public int MOVE_RIGHT = 2;
    
    final static public int X_VALUES = 0;
    final static public int Y_VALUES = 1;
    final static public int EXTREMITY_INDEXES = 2;
    final static public int LEFT_MOST = 0;
    final static public int RIGHT_MOST = 1;
    final static public int BOTTOM_MOST = 2;
    final static public int TOP_MOST = 3;
    
    /** Creates a new instance of KevtrisConstants */
    public KevtrisConstants() {
    }
    
}
