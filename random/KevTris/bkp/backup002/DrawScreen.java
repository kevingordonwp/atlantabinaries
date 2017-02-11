/*
 * DrawScreen.java
 *
 * Created on 26 November 2007, 21:15
 *
 * This is the applet class that should be run to view
 * the KevTris applet.
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
 *
 * @author Kevin Gordon
 */
public class DrawScreen extends Applet implements KeyListener {
    
    private String debugMessages = "";
    private String debugMessages2 = "";
    private Stack blockStack;
    // The array of building blocks referenced by [row] and [col]
    // 29 -
    // 28 -
    // 27 -
    // .
    // .
    // 0  -
    //    0  1  2  ..  13 14
    private BuildingBlock[][] placedBuildingBlocks;
    // The count of number of building blocks per row
    private int[] buildingBlockCountPerRow = new int[KevtrisConstants.SCREEN_ROWS];
    
    /** Creates a new instance of DrawScreen */
    public DrawScreen() {
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
            /*
                // Remove the rows above row i
                System.out.println("row complete, moving down");
                for(int j=i+1; j<KevtrisConstants.SCREEN_ROWS; j++) {
             
                    // Move every building block down 1
                    for(int k=0; k<KevtrisConstants.SCREEN_COLS; k++) {
                        BuildingBlock buildingBlockToMove = placedBuildingBlocks[j][k];
                        placedBuildingBlocks[j][k] = null;
                        if(buildingBlockToMove != null) {
                            buildingBlockToMove.moveDown(1); // TODO: This working?
                            placedBuildingBlocks[j-1][k] = buildingBlockToMove;
                        } else {
                            if(placedBuildingBlocks[j-1][k] != null) {
                                int buildingBlockIndex = placedBuildingBlocks[j-1][k].getIndex();
                                System.out.println("building block index = " + buildingBlockIndex);
                                System.out.println("parent block = " + placedBuildingBlocks[j-1][k].getParentBlock());
                                placedBuildingBlocks[j-1][k].getParentBlock().removeBuildingBlock(buildingBlockIndex);
                            } else {
                                // Block remains as null
                            }
                        }
             
                    }
                    buildingBlockCountPerRow[j-1] = buildingBlockCountPerRow[j];
                    buildingBlockCountPerRow[j] = 0;
                }
             */
            } else {
                // The row isn't full so no need to move down
            }
        }

        String countsString = "{";
        for(int i=0; i<KevtrisConstants.SCREEN_ROWS; i++) {
            countsString += buildingBlockCountPerRow[i] + ",";
        }
        countsString += "}";
        System.out.println(countsString);
    }
    
    /**
     * Removes a row of building blocks for a particular row
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
    
    public void moveRowsAboveDownOne(int row) {
        for(int i=row+1; i<placedBuildingBlocks.length; i++) {
            // for now mark each block that can be moved down
            for(int j=0; j<placedBuildingBlocks[i].length; j++) {
                
                if(placedBuildingBlocks[i][j] != null) {
                    /*
                    int[] colors = placedBuildingBlocks[i][j].getColors();
                    int red = colors[0] - 30;
                    int blue = colors[1] - 30;
                    int green = colors[2] - 30;
                    placedBuildingBlocks[i][j].setColors(red, blue, green);
                     */
                    placedBuildingBlocks[i-1][j] = placedBuildingBlocks[i][j];
                    placedBuildingBlocks[i-1][j].moveDown(1);
                    placedBuildingBlocks[i][j] = null;
                }
                
            }

            buildingBlockCountPerRow[i-1] = buildingBlockCountPerRow[i];            
            removeRow(i); // KG | 20080314 | Bug that some blocks weren't being removed
            
        }
        System.out.println("Moved those above down one");
    }
    
    /**
     * When the building block is placed, then add it to the
     * placed building blocks array.
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
    
    public void generateBlock() {
        double blockNoDouble = Math.random() * 2;
        //System.out.println("blockNoDouble: " + blockNoDouble);
        int blockNo = (int) blockNoDouble;
        //System.out.println("blockNo: " + blockNo);
        
        if(blockNo == 0) {
            BlockA newBlock = new BlockA();
            newBlock.setBlockStack(blockStack);
            blockStack.add(newBlock);
        } else if(blockNo == 1) {
            BlockB newBlock = new BlockB();
            newBlock.setBlockStack(blockStack);
            blockStack.add(newBlock);
        } else {
            System.out.println("back number choice = " + blockNo);
        }
    }
    
    /**
     * The paint method will draw the screen
     */
    public void paint(Graphics g) {
        g.drawString("build 0.26", 10 , 10);
        
        // Draw all the blocks
        Iterator iter = blockStack.iterator();
        
        while(iter.hasNext()) {
            BlockAbstract tempBlock = (BlockAbstract) iter.next();
            tempBlock.drawBlock(g);
        }
        
        // Draw the active block
        BlockAbstract activeBlock = (BlockAbstract) blockStack.peek();
        if(activeBlock.isAtBottom()) {
            g.drawString("Its at bottom!", 100 , 25); // Create the next block at this point
        }
        g.setColor(new Color(0, 0, 0));
        g.drawLine(0, KevtrisConstants.SCREEN_LENGTH, KevtrisConstants.SCREEN_WIDTH, KevtrisConstants.SCREEN_LENGTH);
        g.drawLine(KevtrisConstants.SCREEN_WIDTH, 0, KevtrisConstants.SCREEN_WIDTH, KevtrisConstants.SCREEN_LENGTH);
        g.drawString(debugMessages, 100 , 50);
        g.drawString(debugMessages2, 100 , 70);
        for(int i=0; i<buildingBlockCountPerRow.length; i++) {
            g.drawString(":" + buildingBlockCountPerRow[i], KevtrisConstants.SCREEN_WIDTH, (buildingBlockCountPerRow.length-i)*KevtrisConstants.GAP);
        }
        BlockAbstract tempBlock = (BlockAbstract) blockStack.peek();
        
    }
    
    /**
     * The action taken on pressing one off the keyboard keys
     * i.e. looks for the up, down, left and right keys.
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
     */
    public void keyReleased(KeyEvent e) {
        
    }
    
    /**
     * Method fired on typing a key this is one of the main letter
     * keys. This method is required to be specified as per the
     * extension of the Applet class
     */
    public void keyTyped(KeyEvent e) {
        
    }
    
}