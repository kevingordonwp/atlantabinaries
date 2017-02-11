/*
 * KevTris.java
 *
 * Created on 26 November 2007, 21:15
 *
 */

package kevtris;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Stack;
import javax.swing.Timer;


/**
 * This is the applet class that should be run to view
 * the KevTris applet.
 *
 * @author Kevin Gordon
 * @version 1.0
 */
public class KevTris extends Applet implements KeyListener {
    
    private String debugMessages = "";
    private String debugMessages2 = "";
    private Stack blockStack;
    /** The array of building blocks referenced by [row] and [col]
     * 29 -
     * 28 -
     * 27 -
     * .
     * .
     * 0  -
     *    0  1  2  ..  13 14
     */
    private BuildingBlock[][] placedBuildingBlocks;
    /** The count of number of building blocks per row */
    private int[] buildingBlockCountPerRow = new int[KevtrisConstants.SCREEN_ROWS];
    
    /** Determines whether to output debug messages */
    private boolean debugFlag = false;
    
    /** Flag indicates whether it is game over */
    private boolean gameOverFlag = false;
    
    /**
     * Creates a new instance of KevTris
     */
    public KevTris() {
    }
    
    /**
     * Initialise, set up objects or values at this point.
     */
    public void init() {
        
        addKeyListener(this);
        
        blockStack = new Stack();
        placedBuildingBlocks = new BuildingBlock[KevtrisConstants.SCREEN_ROWS][KevtrisConstants.SCREEN_COLS];
        generateBlock();
        
        int delay = 1000; //milliseconds
        ActionListener refreshBlocks = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(!gameOverFlag) {
                    try {
                        
                        // Redraw the block on the screen
                        BlockAbstract tempBlock = (BlockAbstract) blockStack.peek();
                        if(tempBlock.canMove(1, KevtrisConstants.MOVE_DOWN) && !tempBlock.isAtBottom()) {
                            tempBlock.moveDown(1);
                        } else { // Create a new Block
                            debugMessages2 = "At bottom!";
                            addToPlacedBuildingBlocks(tempBlock);
                            removeAndMoveDownBuildingBlocks();
                            generateBlock();
                        }
                        
                        repaint((long) 0);
                        
                    } catch(AlreadyContainsBlockException e) {
                        System.out.println("Ending because already contains block exception thrown");
                    }
                } else {
                    
                }
            }
        };
        new Timer(delay, refreshBlocks).start();
        // use .stop to stop the timer and clear down
    }
    
    
    /**
     * If can, move the building blocks down above the row
     * which contains building blocks in all 15 positions
     */
    public void removeAndMoveDownBuildingBlocks() {
        for(int i=0; i<KevtrisConstants.SCREEN_ROWS; i++) {
            if(buildingBlockCountPerRow[i] >= KevtrisConstants.SCREEN_COLS) {
                removeRow(i);
                moveRowsAboveDownOne(i);
                i--; // Make sure the moved down row is checked
            } else {
                // The row isn't full so no need to move down
            }
        }
        
        if(debugFlag) {
            String countsString = "{";
            for(int i=0; i<KevtrisConstants.SCREEN_ROWS; i++) {
                countsString += buildingBlockCountPerRow[i] + ",";
            }
            countsString += "}";
            System.out.println(countsString);
        }
    }
    
    /**
     * Removes a row of building blocks for a particular row
     *
     * @param row int The row number to be removed (or index within
     * the placed building blocks.
     */
    public void removeRow(int row) {
        for(int k=0; k<placedBuildingBlocks[row].length; k++) {
            
            if(placedBuildingBlocks[row][k] != null) {
                int buildingBlockIndex = placedBuildingBlocks[row][k].getIndex();
                placedBuildingBlocks[row][k].getParentBlock().removeBuildingBlock(buildingBlockIndex);
                placedBuildingBlocks[row][k] = null;
            }
        }
        buildingBlockCountPerRow[row] = 0;
    }
    
    /**
     * Move all the rows above down 1, removing the row that is
     * moved each time.
     *
     * @param row int The row to which all rows above should be moved down 1
     */
    public void moveRowsAboveDownOne(int row) {
        for(int i=row+1; i<placedBuildingBlocks.length; i++) {
            // for now mark each block that can be moved down
            for(int j=0; j<placedBuildingBlocks[i].length; j++) {
                
                if(placedBuildingBlocks[i][j] != null) {
                    
                    placedBuildingBlocks[i-1][j] = placedBuildingBlocks[i][j];
                    placedBuildingBlocks[i-1][j].moveDown(1);
                    placedBuildingBlocks[i][j] = null;
                }
                
            }
            
            buildingBlockCountPerRow[i-1] = buildingBlockCountPerRow[i];
            removeRow(i); // KG | 20080314 | Bug that some blocks weren't being removed
            
        }
        if(debugFlag) System.out.println("Moved those above down one");
    }
    
    /**
     * When the building block is placed, then add it to the
     * placed building blocks array.
     * @param blockToPlace BlockAbstract The block to add to the placedBuildingBlocks array
     * @throws AlreadyContainsBlockException The exception that is used to indicate
     * that building blocks have been added over the top of existing building
     * blocks.
     */
    public void addToPlacedBuildingBlocks(BlockAbstract blockToPlace) throws AlreadyContainsBlockException {
        BuildingBlock[] buildingBlocksToPlace = blockToPlace.getBuildingBlocks();
        for(int i=0; i<buildingBlocksToPlace.length; i++) {
            BuildingBlock b = buildingBlocksToPlace[i];
            //System.out.println("Y = " + b.getYLocation()/KevtrisConstants.GAP + " X = " + b.getXLocation()/KevtrisConstants.GAP);
            //Transform so 0 is the bottom most row
            int row = KevtrisConstants.SCREEN_ROWS - b.getYLocation()/KevtrisConstants.GAP - 1;
            int col = b.getXLocation()/KevtrisConstants.GAP;
            if(placedBuildingBlocks[row][col] == null) {
                placedBuildingBlocks[row][col] = b;
            } else {
                throw(new AlreadyContainsBlockException("placedBuildingBlocks[" + row + "][" + col + "] already contains a block"));
            }
            buildingBlockCountPerRow[row]++;
        }
    }
    
    /**
     * Generate one block, randomly from the selection of seven
     * possible blocks
     */
    public void generateBlock() {
        
            
            double blockNoDouble = Math.random() * 7;
            //System.out.println("blockNoDouble: " + blockNoDouble);
            int blockNo = (int) blockNoDouble;
            //System.out.println("blockNo: " + blockNo);
            
            if(blockNo == 0) {
                BlockA newBlock = new BlockA();
                newBlock.setBlockStack(blockStack);
                if(!checkForGameOver(newBlock)) blockStack.add(newBlock);
            } else if(blockNo == 1) {
                BlockB newBlock = new BlockB();
                newBlock.setBlockStack(blockStack);
                if(!checkForGameOver(newBlock)) blockStack.add(newBlock);
            } else if(blockNo == 2) {
                BlockC newBlock = new BlockC();
                newBlock.setBlockStack(blockStack);
                if(!checkForGameOver(newBlock)) blockStack.add(newBlock);
            } else if(blockNo == 3) {
                BlockD newBlock = new BlockD();
                newBlock.setBlockStack(blockStack);
                if(!checkForGameOver(newBlock)) blockStack.add(newBlock);
            } else if(blockNo == 4) {
                BlockE newBlock = new BlockE();
                newBlock.setBlockStack(blockStack);
                if(!checkForGameOver(newBlock)) blockStack.add(newBlock);
            } else if(blockNo == 5) {
                BlockF newBlock = new BlockF();
                newBlock.setBlockStack(blockStack);
                if(!checkForGameOver(newBlock)) blockStack.add(newBlock);
            } else if(blockNo == 6) {
                BlockG newBlock = new BlockG();
                newBlock.setBlockStack(blockStack);
                if(!checkForGameOver(newBlock)) blockStack.add(newBlock);
            } else {
                System.out.println("bad number choice = " + blockNo);
            }
        
    }
    
    /**
     * Checks to see if positions to put the block in already
     * contain a block. If they do then it is game over and set
     * the game over flag to true.
     *
     * @param tempBlock BlockAbstract The block which is being generated and 
     * to be seen whether it overlap any other blocks if added
     * @return boolean Returns true if game over or false if block
     * can be added without any problems.
     */
    public boolean checkForGameOver(BlockAbstract tempBlock) {
        int topIndex = placedBuildingBlocks.length - 1;

        BuildingBlock[] tempBuildingBlocks = tempBlock.getBuildingBlocks();
        for(int i=0; i<tempBuildingBlocks.length; i++) {
            BuildingBlock b = tempBuildingBlocks[i];
            int row = KevtrisConstants.SCREEN_ROWS - b.getYLocation()/KevtrisConstants.GAP - 1;
            int col = b.getXLocation() / KevtrisConstants.GAP;
            if(placedBuildingBlocks[row][col] != null) {
                gameOverFlag = true;
                if(debugFlag) System.out.println("Its game over [" + row + "][" + col + "] contains a block");
                break;
            }
        }
        
        return gameOverFlag;
    }
    
    /**
     * The paint method will draw the screen
     *
     * @param g Graphics The graphics object to use to generate the graphics
     */
    public void paint(Graphics g) {
        if(debugFlag) g.drawString("build 0.32", 10 , 10);
        
        if(!gameOverFlag) {
            
            // Draw all the blocks
            Iterator iter = blockStack.iterator();
            
            while(iter.hasNext()) {
                BlockAbstract tempBlock = (BlockAbstract) iter.next();
                tempBlock.drawBlock(g);
            }
            
            // Draw the active block
            BlockAbstract activeBlock = (BlockAbstract) blockStack.peek();
            
            
            if(activeBlock.isAtBottom() && debugFlag) {
                g.drawString("Its at bottom!", 100 , 25); // Create the next block at this point
            }
            
        } else {
            g.drawString("Game Over!", 100, 25);    
        }
        
        g.setColor(new Color(0, 0, 0));
        g.drawLine(0, KevtrisConstants.SCREEN_LENGTH, KevtrisConstants.SCREEN_WIDTH, KevtrisConstants.SCREEN_LENGTH);
        g.drawLine(KevtrisConstants.SCREEN_WIDTH, 0, KevtrisConstants.SCREEN_WIDTH, KevtrisConstants.SCREEN_LENGTH);
        if(debugFlag) {
            g.drawString(debugMessages, 100 , 50);
            g.drawString(debugMessages2, 100 , 70);
            for(int i=0; i<buildingBlockCountPerRow.length; i++) {
                g.drawString(":" + buildingBlockCountPerRow[i], KevtrisConstants.SCREEN_WIDTH, (buildingBlockCountPerRow.length-i)*KevtrisConstants.GAP);
            }
        }
        //BlockAbstract tempBlock = (BlockAbstract) blockStack.peek();
        
    }
    
    /**
     * The action taken on pressing one off the keyboard keys
     * i.e. looks for the up, down, left and right keys.
     *
     * @param e KeyEvent The keyevent object that has been fired
     */
    public void keyPressed(KeyEvent e) {
        
        BlockAbstract tempBlock = (BlockAbstract) blockStack.peek();
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            tempBlock.moveLeft(1);
            debugMessages = "went left";
            repaint((long) 0);
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            tempBlock.moveRight(1);
            debugMessages = "went right";
            repaint((long) 0);
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            tempBlock.moveDown(1);
            debugMessages = "went down";
            repaint((long) 0);
        } else if(e.getKeyCode() == KeyEvent.VK_UP) {
            tempBlock.rotateRight();
            debugMessages = "rotated right";
            repaint((long) 0);
        } else {
            debugMessages = "didn't recognise: " + e.getKeyCode();
            repaint((long) 0);
        }
    }
    
    /**
     * Method fired on releasing a key - required to be specified
     * as per the extension of the Applet class.
     *
     * @param e KeyEvent The key event that was fired.
     */
    public void keyReleased(KeyEvent e) {
        
    }
    
    /**
     * Method fired on typing a key this is one of the main letter
     * keys. This method is required to be specified as per the
     * extension of the Applet class
     *
     * @param e KeyEvent The key event that was fired.
     */
    public void keyTyped(KeyEvent e) {
        
    }
    
}