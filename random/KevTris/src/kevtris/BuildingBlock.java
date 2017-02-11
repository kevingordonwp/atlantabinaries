/*
 * BuildingBlock.java
 *
 * Created on 26 November 2007, 21:09
 *
 */

package kevtris;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This class is the building block that makes up the blocks
 *
 * @author Kevin Gordon
 * @version 1.0
 */
public class BuildingBlock {
    
    /**
     * The xLocation of the building block in pixels initially set to zero
     *
     * @see #getXLocation()
     * @see #setXLocation()
     */
    private int xLocation = 0;

    /**
     * The yLocation of the building block in pixels initially set to zero
     *
     * @see #getYLocation()
     * @see #setYLocation()
     */
    private int yLocation = 0;
    
    /**
     * The width of the building block in pixels
     */
    final private int width = 10;
    
    /**
     * The height of the building block in pixels
     */
    final private int height = 10;
    
    /**
     * The red integer value
     *
     * @see #getColors()
     * @see #setColors()
     */
    private int red = 100;

    /**
     * The blue integer value
     *
     * @see #getColors()
     * @see #setColors()
     */    
    private int blue = 255;

    /**
     * The green integer value
     *
     * @see #getColors()
     * @see #setColors()
     */    
    private int green = 125;
    
    /**
     * Reference to the parent block that the building block is made
     * up of
     *
     * @see #getParentBlock()
     * @see #setParentBlock()
     */
    BlockAbstract parentBlock;

    /**
     * Index of the building block in the building block index
     *
     * @see #getIndex()
     * @see #setIndex(int index)
     */
    protected int index;
    
    /**
     * Creates a new instance of BuildingBlock
     * Reference to the parent block object is passed to
     * on creation of the block.
     *
     * @param parentBlock BlockAbstract The block that the building block is forming
     * part of, this will give a reference to this block
     */
    public BuildingBlock(BlockAbstract parentBlock) {
        this.parentBlock = parentBlock;
    }
    
    /**
     * Creates a new instance of BuildingBlock
     * Reference to the parent block object is passed to
     * on creation of the block. And setting the x
     * and y locations
     *
     * @param parentBlock BlockAbstract The parent block to create a reference to
     * @param index int The index of the building block in the buildingBlocks array
     * @param xLocation int The xLocation the building block to be initially set to
     * @param yLocation int The yLocation the building block to be initially set to
     */
    public BuildingBlock(BlockAbstract parentBlock, int index, int xLocation, int yLocation) {
        this.parentBlock = parentBlock;
        this.index = index;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }
    
    /**
     * @param parentBlock BlockAbstract Sets the reference to the parent block in the
     * building block
     */
    public void setParentBlock(BlockAbstract parentBlock) {
        this.parentBlock = parentBlock;
    }
    
    /** @return BlockAbstract the reference to the building blocks parent block */
    public BlockAbstract getParentBlock() {
        return parentBlock;
    }
    
    /**
     * @param index int The index of the block in the buildingBlocks array
     * in the parent block.
     */
    public void setIndex(int index) {
        this.index = index;
    }
    
    /**
     * @return int The index of the building block in the parent block
     * buildingBlocks array
     */
    public int getIndex() {
        return index;
    }
    
    /**
     * Sets the colour of the block
     *
     * @param red int The integer value of the red channel
     * @param blue int The integer value of the blue channel
     * @param green int The integer value of the green channel
     */
    public void setColors(int red, int blue, int green) {
        this.red = red;
        this.blue = blue;
        this.green = green;
    }
    
    /**
     * Gets an array containing each of the integer values for
     * each colour.
     *
     * @return int[] An array containing each of the colours 0 = red
     * 1 = blue and 2 = green
     */
    public int[] getColors() {
        int[] colors = new int[3];
        colors[0] = red;
        colors[1] = blue;
        colors[2] = green;
        return colors;
    }
    
    /**
     * @return int The x location of the building block
     */
    public int getXLocation() {
        return xLocation;
    }
    
    /**
     * @param xLocation int The x location to set the x location to of the building block
     */
    public void setXLocation(int xLocation) {
        this.xLocation = xLocation;
    }
    
    /**
     * @return int The y location of the building block
     */
    public int getYLocation() {
        return yLocation;
    }
    
    /**
     * @param yLocation int The y location to set the y location to of the building block
     */    
    public void setYLocation(int yLocation) {
        this.yLocation = yLocation;
    }
    
    /**
     * The building block is moved down a number of spaces, this 
     * adjusts the y location value by spaces X gap
     *
     * @param spaces int The number of spaces to move the building block down by
     */
    public void moveDown(int spaces) {
        int newYLocation = this.yLocation + spaces * KevtrisConstants.GAP;
        // TODO: is 0 at the top or bottom? So am I moving this correctly?
        this.yLocation = newYLocation;
    }
    
    /**
     * Draws the individual building block, first by setting the colour
     * of the graphics object and then drawing the building block, using
     * a filled rectangle.
     *
     * @param g Graphics The graphics object used in order to set the colour
     * and draw the rectangle.
     */
    public void drawBuildingBlock(Graphics g) {
        g.setColor(new Color(red, green, blue));
        g.fillRect(xLocation, yLocation, width, height);
    }
    
}
