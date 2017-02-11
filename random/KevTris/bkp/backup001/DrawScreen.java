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
    
    /** Creates a new instance of DrawScreen */
    public DrawScreen() {
    }
    
    /**
     * Initialise, set up objects or values at this point.
     */
    public void init() {
        
        addKeyListener(this);
        
        blockStack = new Stack();
        generateBlock();
        
        int delay = 1000; //milliseconds
        ActionListener refreshBlocks = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Redraw the block on the screen
                BlockAbstract tempBlock = (BlockAbstract) blockStack.peek();
                if(tempBlock.canMove(1, KevtrisConstants.MOVE_DOWN) && !tempBlock.isAtBottom()) {
                    tempBlock.moveDown(1);
                } else { // Create a new Block
                    debugMessages2 = "At bottom!";
                    generateBlock();
                    
                }
                
                repaint((long) 0);
            }
        };
        new Timer(delay, refreshBlocks).start();
        // use .stop to stop the timer and clear down
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
        g.drawString("build 0.19", 10 , 10);
        
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