/*
 * BuildingBlock.java
 *
 * Created on 26 November 2007, 21:09
 *
 * This class is the building block that makes up the blocks
 */

package kevtris;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Kevin Gordon
 */
public class BuildingBlock {
    
    private int xLocation = 0;
    private int yLocation = 0;
    final private int width = 10;
    final private int height = 10;
    private int red = 100;
    private int blue = 255;
    private int green = 125;
    BlockAbstract parentBlock;
    /**
     * Index of the building block in the building block index
     */
    protected int index;
    
    /** 
     * Creates a new instance of BuildingBlock 
     * Reference to the parent block object is passed to
     * on creation of the block.
     */
    public BuildingBlock(BlockAbstract parentBlock) {
        this.parentBlock = parentBlock;
    }

    /** 
     * Creates a new instance of BuildingBlock 
     * Reference to the parent block object is passed to
     * on creation of the block. And setting the x
     * and y locations
     */
    public BuildingBlock(BlockAbstract parentBlock, int index, int xLocation, int yLocation) {
        this.parentBlock = parentBlock;
        this.index = index;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }
    
    public void setParentBlock(BlockAbstract parentBlock) {
        this.parentBlock = parentBlock;
    }
    
    public BlockAbstract getParentBlock() {
        return parentBlock;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return index;
    }
    
    public void setColors(int red, int blue, int green) {
        this.red = red;
        this.blue = blue;
        this.green = green;
    }
    
    public int[] getColors() {
        int[] colors = new int[3];
        colors[0] = red;
        colors[1] = blue;
        colors[2] = green;
        return colors;
    }
    
    public int getXLocation() {
        return xLocation;
    }
    
    public void setXLocation(int xLocation) {
        this.xLocation = xLocation;
    }
    
    public int getYLocation() {
        return yLocation;
    }
    
    public void setYLocation(int yLocation) {
        this.yLocation = yLocation;
    }
    
    public void moveDown(int spaces) {
        int newYLocation = this.yLocation + spaces * KevtrisConstants.GAP;
        // TODO: is 0 at the top or bottom? So am I moving this correctly?
        this.yLocation = newYLocation;
    }
    
    public void drawBuildingBlock(Graphics g) {
        g.setColor(new Color(red, green, blue));
        g.fillRect(xLocation, yLocation, width, height);
    }
    
}
