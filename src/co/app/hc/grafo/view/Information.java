/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.app.hc.grafo.view;

import co.app.hc.grafo.utils.EnumDataTools;
import co.app.hc.grafo.utils.EnumMessageSystem;
import co.app.hc.grafo.utils.Systemdesing;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Harold Castillo (HC)
 * @see <a href="http://www.haroldcastillo.netau.net">HC Web</a>
 */
public class Information extends JDialog {

    Systemdesing systemdesing = new Systemdesing();

    /**
     * Constructor
     */
    public Information() {
        initComponents();
    }

    /**
     * Load init components
     */
    public final void initComponents() {
        configurationWindow();
        setLayout(null);

        lblHC.setIcon(new ImageIcon(getClass().getResource("/co/app/hc/grafo/resources/images/HC242x242.png")));
        lblHC.setBounds(new Rectangle(59, 2, 242, 242));

        this.add(lblHC);

        lblTitle.setText("Harold Castillo");
        lblTitle.setBounds(new Rectangle(90, 240, 200, 25));
        lblTitle.setFont(systemdesing.font(24, true));
        this.add(lblTitle);

        txtContetn.setText("Grafos, es una aplicación para solucionar ejercicios de teoría de gráficas,"
                         + "en donde  \nel conjunto de nodos o puntos y arcos o aristas, se relacionan con "
                         + "un valor para \ndar una solución óptima a un problema presentado.\n");
        txtContetn.setFont(systemdesing.font(12));
        txtContetn.setEditable(Boolean.parseBoolean(EnumDataTools.WINDOW_ESTATE_F.getValue()));
        txtContetn.setBorder(null);
        scrollpane1=new JScrollPane(txtContetn);
        scrollpane1.setBorder(null);
        scrollpane1.setBounds(310, 108,458,100);
        this.add(scrollpane1);

    }

    /**
     * Method to set the appearance of the window
     */
    public final void configurationWindow() {
        this.setIconImage(getIconImage());
        this.getContentPane().setBackground(Color.WHITE);
        this.setSize(800, 300);
        this.setResizable(Boolean.parseBoolean(EnumDataTools.WINDOW_ESTATE_F.getValue()));
        this.setVisible(Boolean.parseBoolean(EnumDataTools.WINDOW_ESTATE_T.getValue()));
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(Boolean.parseBoolean(EnumDataTools.WINDOW_ESTATE_T.getValue()));
        this.setTitle(EnumDataTools.WINDOW_TITLE_VIEWPANEL.getValue());
        this.setLocationRelativeTo(null);
    }

    /*
     * Icon system
     */
    public Image getIconImage() {
        Image image = null;
        try {
            image = new ImageIcon(getClass().getResource("/co/app/hc/grafo/resources/icons/Information.png")).getImage();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "getImage()", "Graph", JOptionPane.ERROR_MESSAGE);
        }
        return image;
    }

    /*
     * Icon of HC
     */
    public Image getHCImage() {
        Image image = null;
        try {
            image = new ImageIcon(getClass().getResource("/co/app/hc/grafo/resources/images/HC242x242.png")).getImage();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "getImage()", "Graph", JOptionPane.ERROR_MESSAGE);
        }
        return image;
    }
    
    JButton buttonClose = new JButton();
    JLabel lblHC = new JLabel();
    JLabel lblTitle = new JLabel();
    JTextArea txtContetn = new JTextArea();
    private JScrollPane scrollpane1;
}
