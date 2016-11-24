/*
 * Graph Class
 */
package co.app.hc.grafo.utils;

import co.app.hc.grafo.view.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Harold Castillo (HC)
 * @see <a href="http://www.haroldcastillo.netau.net">HC Web</a>
 */
public class Graph {

    private int X;
    private int Y;
    private String Name;
    private Point point;
    private Image image;

    /**
     * Load the image in the system
     */
    public Graph(){
        getImage();
    }
    
    /**
     * Paint the graph in canvas
     */
    public void paint(Graphics g, Canvas canvas) {
        g.setColor(Systemdesing.colorSystem());
        g.drawString(Name, getX() + (Integer.parseInt(EnumDataTools.GRAPH_RADIO.getValue()) / 2), getY() + (Integer.parseInt(EnumDataTools.GRAPH_RADIO.getValue()) / 2));
        g.drawImage(image, getX() - (Integer.parseInt(EnumDataTools.GRAPH_RADIO.getValue()) / 2), getY() - (Integer.parseInt(EnumDataTools.GRAPH_RADIO.getValue()) / 2), canvas);
    }
    
    /**
     * Method to get a picture
     * @return Image
     */
    private Image getImage() {
        try {
            image = new ImageIcon(getClass().getResource("/co/app/hc/grafo/resources/images/HC30x30.png")).getImage();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "getImage()", "Graph", JOptionPane.ERROR_MESSAGE);
        }
        return image;
    }

    /**
     * Method valid if a node has been moved
     * @param point
     * @return boolean
     */
    public boolean isMoved(Point point) {
        if (point.distance(getX(), getY()) <= 15) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Method returns the value of the position x
     * @return X
     */
    public int getX() {
        return X;
    }
    
    /**
     * Method to assign the value of the position x
     * @param X 
     */
    public void setX(int X) {
        this.X = X;
    }

    /**
     * Method returns the value of the position y
     * @return Y
     */
    public int getY() {
        return Y;
    }
    
    /**
     * Method to assign the value of the position y
     * @param Y 
     */
    public void setY(int Y) {
        this.Y = Y;
    }
    
    /**
     * Method returns the name of the graph
     * @return String Name
     */
    public String getName() {
        return Name;
    }
    
    /**
     * Method to assign the name of the graph
     * @param Name 
     */
    public void setName(String Name) {
        this.Name = Name;
    }
    
    /**
     * Method to get the point
     * @return Point
     */
    public Point getPoint() {
        return new Point(getX(), getY());
    }
    
    /**
     * Method to assign the point
     * @param Point
     */
    public void setPoint(Point point) {
        point.setLocation(getX(), getY());
    }
}
