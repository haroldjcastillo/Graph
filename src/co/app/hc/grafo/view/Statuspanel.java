/*
 * Panel showing status information current graphs and arcs
 */
package co.app.hc.grafo.view;

import co.app.hc.grafo.utils.Systemdesing;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Harold Castillo (HC)
 * @see <a href="http://www.haroldcastillo.netau.net">HC Web</a>
 */
public class Statuspanel extends javax.swing.JPanel {

    private String graph = "0";
    private String arc = "0";
    private String x = "0";
    private String y = "0";
    Systemdesing systemdesing = new Systemdesing();

    /**
     * Constructor of class
     */
    public Statuspanel() {
        initComponents();
    }

    /**
     * Load the initial components
     */
    private void initComponents() {
        try {

            this.setBackground(java.awt.Color.white);
            
            this.lblx = new JLabel("X: ");
            lblx.setFont(systemdesing.font(14, true));
            this.add(lblx);
            
            this.lbldx = new JLabel(getDx());
            lbldx.setFont(systemdesing.font(14));
            this.add(lbldx);
            
            this.lbly = new JLabel("Y: ");
            lbly.setFont(systemdesing.font(14, true));
            this.add(lbly);
            
            this.lbldy = new JLabel(getDy());
            lbldy.setFont(systemdesing.font(14));
            this.add(lbldy);
                      
            this.Graph = new JLabel("Grafos: ");
            Graph.setFont(systemdesing.font(14, true));
            this.add(Graph);


            this.Graph = new JLabel(getGraph());
            Graph.setFont(systemdesing.font(14));
            this.add(Graph);


            Arc = new JLabel("Arcos: ");
            Arc.setFont(systemdesing.font(14, true));
            this.add(Arc);

            Arc = new JLabel(getArc());
            Arc.setFont(systemdesing.font(14));
            this.add(Arc);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception: " + e, "Statuspanel - Exception Message", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Returns the value of the variable x
     *
     * @return x
     */
    public String getDx() {
        return x;
    }

    /**
     * Assigns a value to the label of the x
     *
     * @param x
     */
    public void setDx(String x) {
        this.x = x;
    }

    /**
     * Returns the value of the variable y
     *
     * @return y
     */
    public String getDy() {
        return y;
    }

    /**
     * Assigns a value to the label of the y
     *
     * @param y
     */
    public void setDy(String y) {
        this.y = y;
    }

    /**
     * Returns the value of the variable graph
     *
     * @return graph
     */
    public String getGraph() {
        return graph;
    }

    /**
     * Assigns a value to the label of the graph
     *
     * @param graph
     */
    public void setGraph(String graph) {
        this.graph = graph;
    }

    /**
     * Returns the value of the variable arc
     *
     * @return arc
     */
    public String getArc() {
        return arc;
    }

    /**
     * Assigns a value to the label of the arc
     *
     * @param arc
     */
    public void setArc(String arc) {
        this.arc = arc;
    }
    javax.swing.JLabel Graph;
    javax.swing.JLabel Arc;
    javax.swing.JLabel lblx;
    javax.swing.JLabel lbldx;
    javax.swing.JLabel lbly;
    javax.swing.JLabel lbldy;
}
