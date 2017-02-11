/*
 * TestDraw1.java
 *
 * Created on 26 November 2007, 20:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package kevtris;

import java.applet.Applet;
import java.awt.Graphics;

/**
 *
 * @author Kevin Gordon
 */
public class TestDraw1 extends Applet {
    
    private String s = "Ohh looook at me!";
    private char c[] = { 'c', 'h', 'a', 'r', 's', ' ', '8' };
    private byte b[] = { 'b', 'y', 't', 'e', 1 , 2, 3 };
    
    /** Creates a new instance of TestDraw1 */
    public TestDraw1() {
    }
    
    /**
     * Initialise, set up objects or values at this point.
     */
    public void init() {
        
    }
    public void paint(Graphics g) {
        g.drawString(s, 100 , 25);
        g.drawChars(c, 2, 3, 100, 50);
        g.drawBytes(b, 0, 5, 100, 75);
        g.drawLine(10, 10, 230, 95);
        g.drawRect(10, 15, 100, 100);
        g.fillRect(150, 15, 100, 100);
    }
    
}
