/*
 * Arch Class
 */
package co.app.hc.grafo.utils;

import java.awt.Graphics;

/**
 *
 * @author Harold Castillo (HC)
 * @see <a href="http://www.haroldcastillo.netau.net">HC Web</a>
 */
public class Arc {

    private Graph pointFirst;
    private Graph pointSecond;
    private String value;
    private String nodeBegin;
    private String nodeEnd;
    public Midpoint midpoint = new Midpoint();

    /**
     * Method commissioned to paint the arc
     *
     * @param g
     */
    public void paint(Graphics g) {
        g.setColor(Systemdesing.colorSystem());
        midpoint.calulate(pointFirst.getPoint(), pointSecond.getPoint());
        g.drawString(value, midpoint.getPoint().x, midpoint.getPoint().y);
        g.drawLine(pointFirst.getX(), pointFirst.getY(), pointSecond.getX(), pointSecond.getY());
    }

    /**
     * Method to get the first point
     *
     * @return pointFirst
     */
    public Graph getPointFirst() {
        return pointFirst;
    }

    /**
     * Method to assign the first point
     *
     * @param pointFirst
     */
    public void setPointFirst(Graph pointFirst) {
        this.pointFirst = pointFirst;
    }

    /**
     * Method to get the second point
     *
     * @return pointFirst
     */
    public Graph getPointSecond() {
        return pointSecond;
    }

    /**
     * Method to assign the second point
     *
     * @param pointFirst
     */
    public void setPointSecond(Graph pointSecond) {
        this.pointSecond = pointSecond;
    }

    /**
     * Method to get the value of the arc
     *
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Method to assign the value of the arc
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Method to get the node begin
     *
     * @return nodeBegin
     */
    public String getNodeBegin() {
        return nodeBegin;
    }

    /**
     * Method to assing the node begin
     *
     * @return nodeBegin
     */
    public void setNodeBegin(String nodeBegin) {
        this.nodeBegin = nodeBegin;
    }

    /**
     * Method to get the node end
     *
     * @return nodeBegin
     */
    public String getNodeEnd() {
        return nodeEnd;
    }

    /**
     * Method to assing the node end
     *
     * @return nodeBegin
     */
    public void setNodeEnd(String nodeEnd) {
        this.nodeEnd = nodeEnd;
    }
}
