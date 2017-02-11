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
    
    /** Creates a new instance of BuildingBlock */
    public BuildingBlock() {
    }
    
    public BuildingBlock(int xLocation, int yLocation) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }
    
    public void setColors(int red, int blue, int green) {
        this.red = red;
        this.blue = blue;
        this.green = green;
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
    
    public void drawBuildingBlock(Graphics g) {
        g.setColor(new Color(red, green, blue));
        g.fillRect(xLocation, yLocation, width, height);
    }
        
}
