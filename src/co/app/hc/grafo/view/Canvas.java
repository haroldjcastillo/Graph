/*
 * Canvas to draw graphs and nodes
 */
package co.app.hc.grafo.view;

import co.app.hc.grafo.utils.Arc;
import co.app.hc.grafo.utils.EnumMessageSystem;
import co.app.hc.grafo.utils.Graph;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Harold Castillo (HC)
 * @see <a href="http://www.haroldcastillo.netau.net">HC Web</a>
 */
public class Canvas extends javax.swing.JPanel {

    private int dX;
    private int dY;
    private int Count = 0;
    private String NameGraph;
    private Viewpanel viewpanel;
    private Arc arc;
    private Graph graph;
    private Graph graphMove;
    private Point point;
    private List<Graph> ListGraph = new ArrayList<Graph>();
    private List<Arc> ListArc = new ArrayList<Arc>();
    private int CountGrahp = 0, CountArc = 0;

    /**
     * Constructor
     *
     * @param viewpanel instance of the class
     */
    public Canvas() {
        Listener();
    }

    /**
     * Draw objects in the panel
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Iterator<Graph> it = ListGraph.iterator(); it.hasNext();) {
            Graph gph = it.next();
            gph.paint(g, this);
        }
        for (Iterator<Arc> it = ListArc.iterator(); it.hasNext();) {
            Arc arco = it.next();
            arco.paint(g);
        }
    }

    /**
     * Method to add a graph
     */
    private void addGraph() {
        boolean duplicate = false;
        for (Graph grafo : ListGraph) {
            if (grafo.getName().equals(getNameGraph())) {
                JOptionPane.showMessageDialog(null, "No se creó el nodo, ya existe uno con este nombre", EnumMessageSystem.WARNING_MESSAGE.getValue(), JOptionPane.INFORMATION_MESSAGE);
                duplicate = true;
            }
        }
        if (!duplicate) {
            graph = new Graph();
            graph.setX(getDX());
            graph.setY(getDY());
            graph.setName(getNameGraph());
            ListGraph.add(graph);
            CountGrahp++;
            repaint();
        }
    }

    /**
     * Method to delete a graph
     *
     * @param evt
     */
    public void delGraph(MouseEvent evt) {
        for (Graph gph : ListGraph) {
            if (gph.isMoved(evt.getPoint())) {
                ListGraph.remove(getIndexListGraph(gph.getName()) - 1);
                CountGrahp--;
                repaint();
                break;
            }
        }
    }

    /**
     * Method that return the index list of graph
     *
     * @param name
     * @return indexList
     */
    public int getIndexListGraph(String name) {
        int indexList = 0;
        try {
            for (Graph gph : ListGraph) {
                indexList++;
                if (name.equals(gph.getName())) {
                    getIndexListArc(gph);
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "getIndexListGraph() " + e, "Canvas", JOptionPane.ERROR_MESSAGE);
        }
        return indexList;
    }

    public void getIndexListArc(Graph point) {
        int count = 0;
        int[] positions = new int[ListArc.size()];
        try {
            for (Iterator<Arc> it = ListArc.iterator(); it.hasNext();) {
                Arc arco = it.next();

                if (point.getName().equals(arco.getNodeEnd())) {
                    CountArc--;
                    it.remove();
                } else if (point.getName().equals(arco.getNodeBegin())) {
                    CountArc--;
                    it.remove();
                }
                count++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "getIndexListGraph() " + e, "Canvas", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method to add a arc
     *
     * @param begin Starting graph
     * @param end End graph
     * @param value value between graphs
     */
    private void addArc(String begin, String end, String value) {
        try {
            int positionBegin = -1, positionEnd = -1, count = 0;
            for (Iterator<Graph> it = ListGraph.iterator(); it.hasNext();) {
                Graph gph = it.next();
                if (gph.getName().equals(begin)) {
                    positionBegin = count;
                }
                if (gph.getName().equals(end)) {
                    positionEnd = count;
                }
                count++;
            }
            if (positionBegin >= 0 && positionEnd >= 0) {
                this.arc = new Arc();
                this.arc.setPointFirst(ListGraph.get(positionBegin));
                this.arc.setPointSecond(ListGraph.get(positionEnd));
                this.arc.setValue(value);
                this.arc.setNodeBegin(begin);
                this.arc.setNodeEnd(end);
                this.ListArc.add(arc);
                CountArc++;
                repaint();
            }
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "addArc()", "Canvas", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void dataNewArc() {
        try {
            String begin = JOptionPane.showInputDialog("Nombre del grafo de partida");
            if (begin != null) {
                String end = JOptionPane.showInputDialog("Nombre del grafo de destino");
                if (end != null) {
                    String value = JOptionPane.showInputDialog("Valor del arco");
                    if (value != null) {
                        try {
                            double data = Double.parseDouble(value);
                            addArc(begin, end, value);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, EnumMessageSystem.NUMBER_FORMAT.getValue(), "Canvas", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "dataNewGraph()", "Canvas", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method which adds mouse events
     */
    public final void Listener() {
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                statusMouseMoved(evt);
            }

            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                statusMouseDragged(evt);
            }
        });
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                statusMousePressed(evt);
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                statusMouseClicked(evt);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                statusMouseReleased(evt);
            }
        });
    }

    /**
     * Method that validates if the mouse has moved on canvas
     *
     * @param evt event MouseMoved
     */
    private void statusMouseMoved(MouseEvent evt) {
        try {
            this.graphMove = null;
            setDX(evt.getX());
            setDY(evt.getY());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "statusMouseMoved()", "Canvas", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method that validates if the mouse has dragged on canvas
     *
     * @param evt event MouseDragged
     */
    private void statusMouseDragged(MouseEvent evt) {
        try {
            if (this.graphMove == null) {
                for (Graph gph : ListGraph) {
                    if (gph.isMoved(evt.getPoint())) {
                        graphMove = gph;
                    } else {
                        setDX(evt.getPoint().x);
                        setDY(evt.getPoint().y);
                        repaint();
                    }
                }
            } else {
                graphMove.setX(getDX());
                graphMove.setY(getDY());
                setDX(evt.getPoint().x);
                setDY(evt.getPoint().y);
                repaint();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "statusMouseDragged()", "Canvas", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method that validates if the mouse has pressed on canvas
     *
     * @param evt event MousePressed
     */
    private void statusMousePressed(MouseEvent evt) {
    }

    /**
     * Method that validates if the mouse has clicked on canvas
     *
     * @param evt event MouseClicked
     */
    private void statusMouseClicked(MouseEvent evt) {
        try {
            if (evt.getButton() == 1) {
                String tmpName = "";
                tmpName = JOptionPane.showInputDialog(null, "Nombre del nodo", "Nodo", JOptionPane.QUESTION_MESSAGE);
                if (!"".equals(tmpName) && tmpName != null) {
                    setDX(evt.getX());
                    setDY(evt.getY());
                    setNameGraph(tmpName);
                    addGraph();
                }
            } else if (evt.getButton() == 2) {
                int respuesta = JOptionPane.showConfirmDialog(this, " ¿Desea eliminar el nodo? ", "Información - Grafo", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (JOptionPane.YES_OPTION == respuesta) {
                    delGraph(evt);
                }
            } else {
                dataNewArc();
            }
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "statusMouseClicked()", "Canvas", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method that validates if the mouse has released on canvas
     *
     * @param evt event MouseReleased
     */
    private void statusMouseReleased(MouseEvent evt) {
        //No action
    }

    /**
     * Method returns the value of the position x
     *
     * @return dX
     */
    public int getDX() {
        return this.dX;
    }

    /**
     * Method to assign the value of the position x
     *
     * @param dX
     */
    public void setDX(int dX) {
        this.dX = dX;
    }

    /**
     * Method returns the value of the position y
     *
     * @return dY
     */
    public int getDY() {
        return this.dY;
    }

    /**
     * Method to assign the value of the position y
     *
     * @param dY
     */
    public void setDY(int dY) {
        this.dY = dY;
    }

    /**
     * Method returns the name of the graph
     *
     * @return NameGraph
     */
    public String getNameGraph() {
        return NameGraph;
    }

    /**
     * Method to assign the name of the graph
     *
     * @param NameGraph
     */
    public void setNameGraph(String NameGraph) {
        this.NameGraph = NameGraph;
    }

    /**
     * Method returns the list of the graph
     *
     * @return ListGraph
     */
    public List<Graph> getListGraph() {
        return ListGraph;
    }

    /**
     * Method to assign the list of the graph
     *
     * @param ListGraph
     */
    public void setListGraph(List<Graph> ListGraph) {
        this.ListGraph = ListGraph;
    }

    /**
     * Method returns the list of the arc
     *
     * @return ListArc
     */
    public List<Arc> getListArc() {
        return ListArc;
    }

    /**
     * Method to assign the list of the arc
     *
     * @param ListGraph
     */
    public void setListArc(List<Arc> ListArc) {
        this.ListArc = ListArc;
    }

    /**
     * Method returns the graph count
     *
     * @return CountGrahp
     */
    public int getCountGrahp() {
        return CountGrahp;
    }

    /**
     * Method to assign the graph count
     *
     * @param CountGrahp
     */
    public void setCountGrahp(int CountGrahp) {
        this.CountGrahp = CountGrahp;
    }

    /**
     * Method returns the arc count
     *
     * @return CountArc
     */
    public int getCountArc() {
        return CountArc;
    }

    /**
     * Method to assign the arc count
     *
     * @param CountGrahp
     */
    public void setCountArc(int CountArc) {
        this.CountArc = CountArc;
    }

    /**
     *
     * Clear the list Grahp
     */
    public void clearListGraph() {
        try {
            ListGraph.clear();
            CountGrahp = 0;
            repaint();
        } catch (Exception e) {
        }
    }

    /**
     *
     * Clear the list Grahp
     */
    public void clearListArc() {
        try {
            ListArc.clear();
            CountArc = 0;
            repaint();
        } catch (Exception e) {
        }
    }
}
