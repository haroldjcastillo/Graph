/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.app.hc.grafo.view;


import co.app.hc.grafo.utils.Tablemodel;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Harold Castillo (HC)
 * @see <a href="http://www.haroldcastillo.netau.net">HC Web</a>
 */
public class Tabulacion extends javax.swing.JDialog {

    public String[][] Data;
    public String[] Node;

    /**
     * Contructor
     * @param data table data
     * @param node name node 
     */
    public Tabulacion(String[][] data, String[] node) {
        this.Data = data;
        this.Node = node;
        Componentes();
    }
    
    /**
     * Settings window components
     */
    public final void Componentes() {

        // Componentes internos
        setLayout(new BorderLayout());
        tm = new Tablemodel();
        tm.setHeader(Node);
        tm.setData(Data);
        Tabla.setModel(tm);
        this.ScrollTabla = new JScrollPane(this.Tabla, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(ScrollTabla);

        // Menú Barra
        MenuOpciones.setMnemonic(KeyEvent.VK_O);
        BarraMenu.add(MenuOpciones);
        add(BarraMenu, BorderLayout.NORTH);

        // Menú Opciones - Items       
        MenuItem = new JMenuItem("Cerrar");
        MenuItem.setToolTipText("Cerrar la tabla");
        MenuOpciones.add(MenuItem);
        MenuItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                 dispose();
            }
        });

        // Propiedades de la ventana de Dialogo
        this.setLocationRelativeTo(null);
        this.setTitle("Tabla de rutas - Grafo (HC)");
        this.setSize(400, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
    }
    
    /**
     * Method for obtaining data
     * @return Data
     */
    public String[][] getData() {
        return Data;
    }

    /**
     * Method to add the values ​​to the array
     * @param Data 
     */
    public void setData(String[][] Data) {
        this.Data = Data;
    }
    
    /**
     * Method for arranging nodes
     * @return 
     */
    public String[] getNode() {
        return Node;
    }

    /**
     * Method to allocate the arrangement of nodes
     * @param Node 
     */
    public void setNode(String[] Node) {
        this.Node = Node;
    }
    
    Tablemodel tm;
    JTable Tabla = new JTable();
    JScrollPane ScrollTabla;
    JMenuBar BarraMenu = new JMenuBar();
    JMenu MenuOpciones = new JMenu("Opciones");
    JMenuItem MenuItem = new JMenuItem();
}
