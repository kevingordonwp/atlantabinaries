/*
 * BlockB.java
 *
 * Created on December 22, 2007, 7:37 PM
 *
 */

package kevtris;


/**
 * This class defines the block B. Block B extends the
 * abstract block, in order to inherit the necessary
 * methods for a block. But this class has the configuration
 * for the block which looks like:
 *   _ _
 * 0|_|_|1
 * 2|_|
 * 3|_|
 *
 * @author Kevin Gordon
 * @version 1.0
 */
public class BlockB extends BlockAbstract {

    
    /** Creates a new instance of BlockB */
    public BlockB() {

        blocks[0] = new BuildingBlock(this, 0, 0, 0);
        blocks[1] = new BuildingBlock(this, 1, KevtrisConstants.GAP, 0);
        blocks[2] = new BuildingBlock(this, 2, 0, KevtrisConstants.GAP);
        blocks[3] = new BuildingBlock(this, 3, 0, KevtrisConstants.GAP * 2);

        
        int tempPosition;
        // 0 -> 1
        tempPosition = 0;
        rotationalMovementDelta[tempPosition][KevtrisConstants.X_VALUES][0] = KevtrisConstants.GAP * 2;
        rotationalMovementDelta[tempPosition][KevtrisConstants.X_VALUES][1] = KevtrisConstants.GAP;
        rotationalMovementDelta[tempPosition][KevtrisConstants.X_VALUES][2] = KevtrisConstants.GAP;
        rotationalMovementDelta[tempPosition][KevtrisConstants.X_VALUES][3] = 0;
        rotationalMovementDelta[tempPosition][KevtrisConstants.Y_VALUES][0] = 0;
        rotationalMovementDelta[tempPosition][KevtrisConstants.Y_VALUES][1] = KevtrisConstants.GAP;
        rotationalMovementDelta[tempPosition][KevtrisConstants.Y_VALUES][2] = KevtrisConstants.GAP * -1;
        rotationalMovementDelta[tempPosition][KevtrisConstants.Y_VALUES][3] = KevtrisConstants.GAP * -2;
        rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.LEFT_MOST] = 3;
        rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.RIGHT_MOST] = 1;
        rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.BOTTOM_MOST] = 1;
        rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.TOP_MOST] = 3;
        
        // 1 -> 2
        tempPosition = 1;
        rotationalMovementDelta[tempPosition][KevtrisConstants.X_VALUES][0] = KevtrisConstants.GAP * -1;
        rotationalMovementDelta[tempPosition][KevtrisConstants.X_VALUES][1] = KevtrisConstants.GAP * -2;
        rotationalMovementDelta[tempPosition][KevtrisConstants.X_VALUES][2] = 0;
        rotationalMovementDelta[tempPosition][KevtrisConstants.X_VALUES][3] = KevtrisConstants.GAP;
        rotationalMovementDelta[tempPosition][KevtrisConstants.Y_VALUES][0] = KevtrisConstants.GAP * 2;
        rotationalMovementDelta[tempPosition][KevtrisConstants.Y_VALUES][1] = KevtrisConstants.GAP;
        rotationalMovementDelta[tempPosition][KevtrisConstants.Y_VALUES][2] = KevtrisConstants.GAP * 1;
        rotationalMovementDelta[tempPosition][KevtrisConstants.Y_VALUES][3] = 0;
        rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.LEFT_MOST] = 1;
        rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.RIGHT_MOST] = 3;
        rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.BOTTOM_MOST] = 1;
        rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.TOP_MOST] = 3;
        
        // 2 -> 3
        tempPosition = 2;
        rotationalMovementDelta[tempPosition][KevtrisConstants.X_VALUES][0] = KevtrisConstants.GAP * -1;
        rotationalMovementDelta[tempPosition][KevtrisConstants.X_VALUES][1] = 0;
        rotationalMovementDelta[tempPosition][KevtrisConstants.X_VALUES][2] = 0;
        rotationalMovementDelta[tempPosition][KevtrisConstants.X_VALUES][3] = KevtrisConstants.GAP;
        rotationalMovementDelta[tempPosition][KevtrisConstants.Y_VALUES][0] = KevtrisConstants.GAP * -1;
        rotationalMovementDelta[tempPosition][KevtrisConstants.Y_VALUES][1] = KevtrisConstants.GAP * -2;
        rotationalMovementDelta[tempPosition][KevtrisConstants.Y_VALUES][2] = 0;
        rotationalMovementDelta[tempPosition][KevtrisConstants.Y_VALUES][3] = KevtrisConstants.GAP;
        rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.LEFT_MOST] = 1;
        rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.RIGHT_MOST] = 3;
        rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.BOTTOM_MOST] = 3;
        rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.TOP_MOST] = 1;
        
        // 3 -> 0
        tempPosition = 3;
        rotationalMovementDelta[tempPosition][KevtrisConstants.X_VALUES][0] = 0;
        rotationalMovementDelta[tempPosition][KevtrisConstants.X_VALUES][1] = KevtrisConstants.GAP;
        rotationalMovementDelta[tempPosition][KevtrisConstants.X_VALUES][2] = KevtrisConstants.GAP * -1;
        rotationalMovementDelta[tempPosition][KevtrisConstants.X_VALUES][3] = KevtrisConstants.GAP * -2;
        rotationalMovementDelta[tempPosition][KevtrisConstants.Y_VALUES][0] = KevtrisConstants.GAP * -1;
        rotationalMovementDelta[tempPosition][KevtrisConstants.Y_VALUES][1] = 0;
        rotationalMovementDelta[tempPosition][KevtrisConstants.Y_VALUES][2] = 0;
        rotationalMovementDelta[tempPosition][KevtrisConstants.Y_VALUES][3] = KevtrisConstants.GAP;
        rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.LEFT_MOST] = 3;
        rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.RIGHT_MOST] = 1;
        rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.BOTTOM_MOST] = 3;
        rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.TOP_MOST] = 1;
        
        for(int i=0; i<blocks.length; i++) {
            blocks[i].setColors(100, 255, 125);
        }
        
        /**
         * The references to the array indexes of the blocks
         * that fall in the particular position
         *
         * These are for BlockA in position zero.
         */
        tempPosition = 3;
        leftMost = rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.LEFT_MOST];
        rightMost = rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.RIGHT_MOST];
        bottomMost = rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.BOTTOM_MOST];
        topMost = rotationalMovementDelta[tempPosition][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.TOP_MOST];
        position = 0;
        
    }

}