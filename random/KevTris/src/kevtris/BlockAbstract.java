/*
 * BlockAbstract.java
 *
 * Created on 27 November 2007, 20:38
 *
 */

package kevtris;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.Stack;

/**
 * The abstract block class that is the parent to all the blocks
 * this class contains all the methods that manipulate the blocks
 * whereas the children actually define the configuration of the
 * building blocks within the block for each of the different
 * block shapes.
 *
 * @author Kevin Gordon
 * @version 1.0
 */
public abstract class BlockAbstract {
    
    /** The array of building blocks that make up the block */
    protected BuildingBlock[] blocks = new BuildingBlock[4];
    /** 
     * The reference to the block stack which contains references
     * to all the blocks that have either been fixed in place
     * or the one that is falling.
     */
    protected Stack blockStack = new Stack();

    /** 
     * The references to the array indexes of the blocks
     * that fall in the particular position. These
     * need to be overridden for the particular type of block
     */
    protected int leftMost, rightMost, bottomMost, topMost;

    /**
     * The rotational position, 4 posible values
     */
    protected int position = 0;
    
    /**
     * RotationalMovementsDelta gives the amount each x or y
     * value should be changed to on rotating to the particular
     * position
     *
     * [current position][x or y values or extremity value][block index]
     *
     */
    protected int[][][] rotationalMovementDelta = new int[4][3][4];
    

    
    /** Creates a new instance of BlockAbstract */
    public BlockAbstract() {
    }
    
    /**
     * @return BuildingBlock[] Returns the array of building blocks that
     * makes up the block
     */
    public BuildingBlock[] getBuildingBlocks() {
        return blocks;
    }
    
    /**
     * @return int The index of the left most block
     */
    public int getLeftMost() {
        return leftMost;
    }
    
    /**
     * @return int The index of the right most block
     */
    public int getRightMost() {
        return rightMost;
    }
    
    /**
     * @return int The index of the bottom most block
     */
    public int getBottomMost() {
        return bottomMost;
    }
    
    /**
     * @return int The index of the top most block
     */
    public int getTopMost() {
        return topMost;
    }
    
        
    /**
     * Set the blockstack reference within the block
     * this should be done initially when the class is created.
     * However I've allowed the class to be created without
     * the blockstack array as I may be showing blocks that are
     * coming next, and I may be showing blocks dashed to show
     * where they will fall
     *
     * @param blockStack Stack blockStack see above for details
     */
    public void setBlockStack(Stack blockStack) {
        this.blockStack = blockStack;
    }
    
    /**
     * Removes the building block with a specific index
     *
     * @param index int The index of the building block to be removed i.e. set
     * to null
     */
    public void removeBuildingBlock(int index) {
        blocks[index] = null;
    }
    
    /**
     * This method moves the block down.
     * 
     * @param spaces int The number of spaces to move the block down.
     */
    public void moveDown(int spaces) {
        int newYLocation;
        
        int maxYLocation = blocks[bottomMost].getYLocation() + KevtrisConstants.GAP * spaces;
        
        
        if(canMove(spaces, KevtrisConstants.MOVE_DOWN) &&
                maxYLocation <= KevtrisConstants.SCREEN_LENGTH - KevtrisConstants.GAP) {
            for(int i=0; i<blocks.length; i++) {
                newYLocation = blocks[i].getYLocation() + KevtrisConstants.GAP * spaces;
                blocks[i].setYLocation(newYLocation);
            }
        }
    }
    
    /**
     * This method moves the block right.
     * 
     * @param spaces int The number of spaces to move the block to the right.
     */
    public void moveRight(int spaces) {
        int newXLocation;
        
        int maxXLocation = blocks[rightMost].getXLocation() + KevtrisConstants.GAP * spaces;
        
        if(canMove(spaces, KevtrisConstants.MOVE_RIGHT) &&
                maxXLocation < KevtrisConstants.SCREEN_WIDTH) {
            
            for(int i=0; i<blocks.length; i++) {
                newXLocation = blocks[i].getXLocation() + KevtrisConstants.GAP * spaces;
                
                blocks[i].setXLocation(newXLocation);
                
            }
            
        }
    }
    
    /**
     * This method moves the block left.
     *
     * @param spaces int The number of spaces to move the block to the left.
     */
    public void moveLeft(int spaces) {
        int newXLocation;
        
        int minXLocation = blocks[leftMost].getXLocation() - KevtrisConstants.GAP * spaces;
        
        if(canMove(spaces, KevtrisConstants.MOVE_LEFT) &&
                minXLocation >= 0) {
            for(int i=0; i<blocks.length; i++) {
                newXLocation = blocks[i].getXLocation() - KevtrisConstants.GAP * spaces;
                
                blocks[i].setXLocation(newXLocation);
                
            }
        }
    }
    
    /**
     * Movement is 0 - down, 1 - left and 2 - right
     * This method will determine whether the block can move in the
     * direction defined by the parameter "movement" this is an
     * int value as shown above, each number representing a different
     * direction.
     *
     * @param spaces int - The number of spaces that it is to be moved
     * @param movement int - The direction to be moved
     * @return boolean Test whether it is possible to move the block based
     * on the number of spaces and the value of the movement variable.
     */
    public boolean canMove(int spaces, int movement) {
        
        boolean canMoveDown = true;
        
        // Iterate through each block and make sure none of the x, y pairs match
        int[] proposedX = new int[4];
        int[] proposedY = new int[4];
        
        int movement_down = 0;
        int movement_left_right = 0;
        
        if(movement == KevtrisConstants.MOVE_DOWN) {
            movement_down = KevtrisConstants.GAP * spaces;
        }
        
        if(movement == KevtrisConstants.MOVE_LEFT) {
            movement_left_right = KevtrisConstants.GAP * spaces * -1;
        }
        
        if(movement == KevtrisConstants.MOVE_RIGHT) {
            movement_left_right = KevtrisConstants.GAP * spaces;
        }
        
        
        for(int i=0; i<blocks.length; i++) {
            
            proposedX[i] = blocks[i].getXLocation() + movement_left_right;
            proposedY[i] = blocks[i].getYLocation() + movement_down;
            
        }
        
        canMoveDown = canMoveCheckValues(proposedX, proposedY);
        
        return canMoveDown;
    }
    
    /**
     * Checks the proposed position of the blocks against all the
     * other blocks in the stack and if none overlap then
     * canMoveDown is kept as true. Any overlaps and canMoveDown
     * is changed to false.
     *
     * @param proposedX int[] The proposed set of x positions
     * @param proposedY int[] The proposed set of y positions
     * @return boolean True whether the new x and y positions can be used
     */
    public boolean canMoveCheckValues(int[] proposedX, int[] proposedY) {
        
        boolean canMoveDown = true;
        Iterator iter = blockStack.iterator();
        BlockAbstract tempBlock;
        BuildingBlock[] tempBuildingBlocks;
        while(iter.hasNext()) {
            tempBlock = (BlockAbstract) iter.next();
            if(!this.equals(tempBlock)) {
                tempBuildingBlocks = tempBlock.getBuildingBlocks();
                for(int i=0; i<tempBuildingBlocks.length; i++) {
                    for(int j=0; j<blocks.length; j++) {
                        if(tempBuildingBlocks[i] != null) {
                            if(tempBuildingBlocks[i].getXLocation() == proposedX[j] &&
                               tempBuildingBlocks[i].getYLocation() == proposedY[j]) {                             canMoveDown = false;
                                break;
                            }
                        }
                    } // end for - iterate the blocks
                    
                } // end for - iterate tempBuildingBlocks
                if(canMoveDown == false) {
                    break;
                }
            } // else { System.out.println("blocks match"); }
        }
        return canMoveDown;
    }    
    
    /**
     * This determines whether the block can be rotated, the
     * values passed in are the x and y values that will be
     * the actual values if the block is rotated.
     *
     * @param xValues int[] The x value positions proposed to position the block
     * after its rotated.
     * @param yValues int[] The y value positions proposed to position the block
     * after its rotated.
     * @return boolean True if the block can take on the proposed x and
     * y values, False if it can't.
     */
    public boolean canRotate(int[] xValues, int[] yValues) {
        boolean canRotate = true;
        int xLocation, yLocation;
        
        if(canMoveCheckValues(xValues, yValues)) {
            
            for(int i=0; i<xValues.length; i++) {
                
                xLocation = xValues[i];
                yLocation = yValues[i];
                
                
                if(xLocation < 0 || xLocation >= KevtrisConstants.SCREEN_WIDTH) {
                    canRotate = false;
                    break;
                }
                
                if(yLocation > KevtrisConstants.SCREEN_LENGTH - KevtrisConstants.GAP) {
                    canRotate = false;
                    break;
                }
                
            }
        } // end if canMoveCheckValues
        
        else {
            canRotate = false;
        }
        
        return canRotate;
    }
    
    /**
     * This method will rotate the block to the right. Please note
     * that the block will only be rotated in one direction. If you're
     * at the side and need to rotate, then the block will need to be
     * moved to the right by one space and then rotated.
     */
    public void rotateRight() {
        int[] xValues = new int[4];
        int[] yValues = new int[4];
        int[] xProposed = new int[4];
        int[] yProposed = new int[4];
        
        for(int i=0; i<blocks.length; i++) {
            xValues[i] = blocks[i].getXLocation();
            yValues[i] = blocks[i].getYLocation();
        }
        
        
        for(int i=0; i<blocks.length; i++) {
            xProposed[i] = xValues[i] +
                    rotationalMovementDelta[position][KevtrisConstants.X_VALUES][i];
            yProposed[i] = yValues[i] +
                    rotationalMovementDelta[position][KevtrisConstants.Y_VALUES][i];
        }
        
        if(canRotate(xProposed, yProposed)) {
            
            for(int i=0; i<blocks.length; i++) {
                blocks[i].setXLocation(xProposed[i]);
                blocks[i].setYLocation(yProposed[i]);
            }
            
            leftMost = rotationalMovementDelta[position][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.LEFT_MOST];
            rightMost = rotationalMovementDelta[position][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.RIGHT_MOST];
            bottomMost = rotationalMovementDelta[position][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.BOTTOM_MOST];
            topMost = rotationalMovementDelta[position][KevtrisConstants.EXTREMITY_INDEXES][KevtrisConstants.TOP_MOST];
            position = (position + 1) % 4;
            
        } 
        
    }
    
    /**
     * Test to determine whether the block is at the bottom.
     *
     * @return boolean Returns true if the block is at the bottom or false
     * if its not at the bottom.
     */
    public boolean isAtBottom() {
        boolean isAtBottom = false;
        if(!canMove(1, KevtrisConstants.MOVE_DOWN) || blocks[bottomMost].getYLocation() >= KevtrisConstants.SCREEN_LENGTH - KevtrisConstants.GAP) {
            isAtBottom = true;
        }
        //System.out.println("At bottom? " + isAtBottom + 
        //               ", bottom block " + bottomMost + "  y location: " + blocks[bottomMost].getYLocation() +
        //               " >= to? " + (KevtrisConstants.SCREEN_LENGTH - KevtrisConstants.GAP));
        return isAtBottom;
    }
    
    /**
     * This method calls the drawBuildingBlock method for each block
     * in order to draw each of the building blocks that make up the
     * block.
     *
     * @param g Graphics The graphics object used for drawing to the screen
     */
    public void drawBlock(Graphics g) {
        for(int i=0; i<blocks.length; i++) {
            if(blocks[i] != null) {
                //System.out.println("blocks[" + i + "] not null so attempting to draw");
                blocks[i].drawBuildingBlock(g);
            }
        }
    }
}
