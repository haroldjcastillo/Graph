/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.app.hc.grafo.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author Harold Castillo (HC)
 */
public class Systemdesing {
    
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension dimension = tk.getScreenSize();
    
    public Font font(int size) {
        Font font = null;
        try {
            font = new Font("Tahoma", Font.PLAIN, size);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception Font " + e, "Systemdesing - Exception Message", JOptionPane.WARNING_MESSAGE);
        }
        return font;
    }
    
    public Font font(int size, boolean Bold) {
        Font font = null;
        try {
            font = new Font("Tahoma", Font.BOLD, size);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception Font " + e, "Systemdesing - Exception Message", JOptionPane.WARNING_MESSAGE);
        }
        return font;
    }

    public double sizeWindowX() {
       return tk.getScreenSize().getWidth();
    }
    
    public double sizeWindowY() {
       return tk.getScreenSize().getHeight();
    }
    
    public static Color colorSystem(){
        Color color=new Color(1, 40, 64);
        return color;
    }
}
