/*
 * KevtrisConstants.java
 *
 * Created on 26 November 2007, 22:00
 *
 */

package kevtris;

/**
 * This class contains the constants used by the Kevtris
 * applet
 *
 * @author Kevin Gordon
 * @version 1.0
 */
public class KevtrisConstants {

    /** The screen length in pixels is 15 * 30 = 450 */
    final static public int SCREEN_LENGTH = 225; // 15 * 30
    /** The screen width in pixels is 15 * 15 = 225 */
    final static public int SCREEN_WIDTH = 450; // 15 * 15
    /** The gap between each column and row is 15 */
    final static public int GAP = 15;
    /** The number of screen rows = 30 */
    final static public int SCREEN_ROWS = SCREEN_LENGTH / GAP; // 30
    /** The number of screen columns = 15 */
    final static public int SCREEN_COLS = SCREEN_WIDTH / GAP; // 15
    
    /** Movement is 0 - down */
    final static public int MOVE_DOWN = 0;
    /** Movement is 1 - left */
    final static public int MOVE_LEFT = 1;
    /** Movement is 2 - right */
    final static public int MOVE_RIGHT = 2;
    
    
    // Related to the rotation delta multidimensional array
    /** The index [][thisone][] in the rotational delta that represents x values */
    final static public int X_VALUES = 0;
    /** The index [][thisone][] in the rotational delta that represents y values */
    final static public int Y_VALUES = 1;
    /** The index [][thisone][] in the rotational delta that represents extremity values */    
    final static public int EXTREMITY_INDEXES = 2;
    /** The index [][][thisone] in the rotational delta that is the left most */
    final static public int LEFT_MOST = 0;
    /** The index [][][thisone] in the rotational delta that is the right most */
    final static public int RIGHT_MOST = 1;
    /** The index [][][thisone] in the rotational delta that is the bottom most */
    final static public int BOTTOM_MOST = 2;
    /** The index [][][thisone] in the rotational delta that is the top most */
    final static public int TOP_MOST = 3;
    
    /** Creates a new instance of KevtrisConstants */
    public KevtrisConstants() {
    }
    
}
