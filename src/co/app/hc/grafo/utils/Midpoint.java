/*
 * Calculate the midpoint
 */
package co.app.hc.grafo.utils;

import java.awt.Point;
import javax.swing.JOptionPane;

/**
 *
 * @author Harold Castillo (HC)
 * @see <a href="http://www.haroldcastillo.netau.net">HC Web</a>
 */
public class Midpoint {

    private double X1;
    private double Y1;
    private double X2;
    private double Y2;
    private Point point;
    
    public void calulate(Point p1, Point p2) {
        try {
            this.X1 = p1.getX();
            this.Y1 = p1.getY();
            this.X2 = p2.getX();
            this.Y2 = p2.getY();
            int dx = (int)(getX1()+ getX2() ) / 2;
            int dy = (int)(getY1()+ getY2() ) / 2;
            setPoint(new Point(dx, dy));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "calulate()" + e, "MidPoint", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double getX1() {
        return X1;
    }

    public void setX1(double X1) {
        this.X1 = X1;
    }

    public double getY1() {
        return Y1;
    }

    public void setY1(double Y1) {
        this.Y1 = Y1;
    }

    public double getX2() {
        return X2;
    }

    public void setX2(double X2) {
        this.X2 = X2;
    }

    public double getY2() {
        return Y2;
    }

    public void setY2(double Y2) {
        this.Y2 = Y2;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
