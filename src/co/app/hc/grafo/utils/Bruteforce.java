/*
 * Brutefoce class
 */
package co.app.hc.grafo.utils;

import co.app.hc.grafo.view.Viewpanel;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.JTextArea;

/**
 *
 * @author Harold Castillo (HC)
 * @see <a href="http://www.haroldcastillo.netau.net">HC Web</a>
 */
public class Bruteforce extends javax.swing.JDialog  {

    private String[] node;
    private int[][] graph;

    JTextArea textArea = new JTextArea();
    ScrollPane scroll = new ScrollPane();
    Viewpanel window;

    /**
     * Method that initiates the calculations of the graph
     */
    public void initGraph() {
        graph = new int[node.length][node.length];
        add(scroll);
        scroll.add(textArea);
        this.setLocationRelativeTo(this.window);
        this.setTitle(" Resultado en Fuerza Bruta ");
        this.setSize(300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);

    }
    
    /**
     * Method to add a route to settlement
     * @param begin begin node
     * @param destination end node
     * @param distance value node
     */
    public void addRoute(String begin, String destination, int distance) {
        int b = nodePosition(begin);
        int d = nodePosition(destination);
        graph[b][d] = distance;
        graph[d][b] = distance;
    }
    
    /**
     * Method to obtain the position of a node
     * @param n name node
     * @return position of a node
     */
    public int nodePosition(String n) {
        for (int i = 0; i < this.node.length; i++) {
            if (node[i].equals(n)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Search method
     * @param begin
     * @param destination 
     */
    public void search(String begin, String destination) {
        int b = nodePosition(begin);
        int d = nodePosition(destination);
        Stack<Integer> Result = new Stack<Integer>();
        Result.push(b);
        walkingTrails(b, d, Result);
    }

    /**
     * Method to create the route
     * @param nodeBegin 
     * @param nodeDestination
     * @param result 
     */
    public void walkingTrails(int nodeBegin, int nodeDestination, Stack<Integer> result) {
        if (nodeBegin == nodeDestination) {
            for (int x : result) {
                textArea.append(node[x] + ">");
            }
             textArea.append("Resultado>" + evaluate(result));
             textArea.append("\n");
        }

        List<Integer> list = new ArrayList();
        for (int i = 0; i < graph.length; i++) {
            if (graph[nodeBegin][i] != 0 && !result.contains(i)) {
                list.add(i);
            }
        }

        for (int nodo : list) {
            result.push(nodo);
            walkingTrails(nodo, nodeDestination, result);
            result.pop();
        }
    }

    public int evaluate(Stack<Integer> result) {
        int response = 0;
        int[] r = new int[result.size()];
        int i = 0;
        for (int x : result) {
            r[i++] = x;
        }
        for (i = 1; i < r.length; i++) {
            response += graph[r[i]][r[i - 1]];
        }
        return response;
    }

    public String[] getNode() {
        return node;
    }

    public void setNode(String[] node) {
        this.node = node;
    }

    public int[][] getGraph() {
        return graph;
    }

    public void setGraph(int[][] graph) {
        this.graph = graph;
    }
}
