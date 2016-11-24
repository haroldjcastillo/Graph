/*
 * Main window containing the tools panel
 * 
 */
package co.app.hc.grafo.view;

import co.app.hc.grafo.utils.Arc;
import co.app.hc.grafo.utils.Bruteforce;
import co.app.hc.grafo.utils.EnumDataTools;
import co.app.hc.grafo.utils.EnumMessageSystem;
import co.app.hc.grafo.utils.Graph;
import co.app.hc.grafo.utils.Systemdesing;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Harold Castillo (HC)
 * @see <a href="http://www.haroldcastillo.netau.net">HC Web</a>
 */
public class Viewpanel extends javax.swing.JFrame implements Runnable {

    private String[] node;
    private int[][] matrizGraph;
    private boolean countStatus = false;
    private Bruteforce bruteforce;
    private Statuspanel statuspanel;
    private Canvas canvas = new Canvas();
    private Systemdesing systemdesing = new Systemdesing();
    private List<Graph> graph;
    private List<Arc> arc;
    private Thread count;

    /**
     * Constructor of class
     */
    @SuppressWarnings("CallToThreadStartDuringObjectConstruction")
    public Viewpanel() {
        initComponents();
        count = new Thread(this);
        count.start();
    }

    /**
     * Method that stop the thread in execution
     *
     * @param status thread status
     */
    public void stopCount(boolean status) {
        this.countStatus = status;
    }

    /**
     * Interface method Runneable
     */
    @Override
    public void run() {
        while (!countStatus) {
            try {
                statuspanel.Graph.setText(canvas.getCountGrahp() + "");
                statuspanel.Arc.setText(canvas.getCountArc() + "");
                statuspanel.lbldx.setText(canvas.getDX() + "");
                statuspanel.lbldy.setText(canvas.getDY() + "");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "run()", "Viewpanel", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Method tha return the class instance
     *
     * @return statuspanel class instance
     */
    public Statuspanel getStatuspanel() {
        return statuspanel;
    }

    /**
     * Method to load components in the window
     */
    private void initComponents() {
        menuBar();
        statusBar();
        configurationWindow();
        canvasPainter();
    }

    /**
     * Configurations of menu bar
     */
    private void menuBar() {
        try {
            menu = new javax.swing.JMenu("Opciones");
            menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/app/hc/grafo/resources/icons/Options.png")));
            menu.setMnemonic(java.awt.event.KeyEvent.VK_O);
            menu.getAccessibleContext().setAccessibleDescription(
                    "Opciones del sistema");
            menuBar.add(menu);
            setJMenuBar(menuBar);

            menuItem = new javax.swing.JMenuItem("Ejecutar", java.awt.event.KeyEvent.VK_E);
            menuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/app/hc/grafo/resources/icons/Execute.png")));
            menuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                    java.awt.event.KeyEvent.VK_E, java.awt.event.ActionEvent.ALT_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "Encontrar la ruta crítica");
            menuItem.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    menuExecuteActionPerformed(evt);
                }
            });
            menu.add(menuItem);

            menuItem = new javax.swing.JMenuItem("Nuevo Arco", java.awt.event.KeyEvent.VK_A);
            menuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/app/hc/grafo/resources/icons/Arc.png")));
            menuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                    java.awt.event.KeyEvent.VK_A, java.awt.event.ActionEvent.ALT_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "Crear un arco nuevo");
            menuItem.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    menuNewArcActionPerformed(evt);
                }
            });
            menu.add(menuItem);

            menuItem = new javax.swing.JMenuItem("Limpiar", java.awt.event.KeyEvent.VK_E);
            menuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/app/hc/grafo/resources/icons/Clear.png")));
            menuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                    java.awt.event.KeyEvent.VK_L, java.awt.event.ActionEvent.ALT_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "Limpiar todo el panel de dibujo");
            menuItem.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    menuClearActionPerformed(evt);
                }
            });
            menu.add(menuItem);

            menuItem = new javax.swing.JMenuItem("Guardar imagen", java.awt.event.KeyEvent.VK_G);
            menuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/app/hc/grafo/resources/icons/Image.png")));
            menuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                    java.awt.event.KeyEvent.VK_G, java.awt.event.ActionEvent.ALT_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "Guardar una imagen");
            menuItem.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    menuSaveActionPerformed(evt);
                }
            });

            menu.add(menuItem);

            menu.addSeparator();

            menuItem = new javax.swing.JMenuItem("Salir", java.awt.event.KeyEvent.VK_S);
            menuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/app/hc/grafo/resources/icons/Exit.png")));
            menuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                    java.awt.event.KeyEvent.VK_F4, java.awt.event.ActionEvent.ALT_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "Salir del sistema");
            menuItem.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    menuCloseActionPerformed(evt);
                }
            });
            menu.add(menuItem);

            menu = new javax.swing.JMenu("Acerca de");
            menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/app/hc/grafo/resources/icons/About.png")));
            menu.setMnemonic(java.awt.event.KeyEvent.VK_R);
            menu.getAccessibleContext().setAccessibleDescription(
                    "Descripción de desarrollo");
            menuBar.add(menu);

            menuItem = new javax.swing.JMenuItem("Información", java.awt.event.KeyEvent.VK_A);
            menuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/co/app/hc/grafo/resources/icons/Information.png")));
            menuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
                    java.awt.event.KeyEvent.VK_D, java.awt.event.ActionEvent.ALT_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription(
                    "Descripción del desarrollo");
            menuItem.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    menuAboutActionPerformed(evt);
                }
            });
            menu.add(menuItem);

            setJMenuBar(menuBar);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "menuBar()", "Viewpanel", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Shows the number of graphs and arcs drawn
     */
    private void statusBar() {
        try {
            statuspanel = new Statuspanel();
            this.getContentPane().add(BorderLayout.SOUTH, statuspanel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "statusBar()", "Viewpanel", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Place the canvas in the window
     */
    private void canvasPainter() {
        try {
            setLayout(null);
            canvas.setBackground(Color.WHITE);
            canvas.setBounds(new java.awt.Rectangle(10, 10, 765, 390));
            canvas.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.black));
            canvas.setDoubleBuffered(true);
            add(canvas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "canvasPainter()", "Viewpanel", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Method to set the appearance of the window
     */
    public final void configurationWindow() {
        this.setIconImage(getIconImage());
        this.getContentPane().setBackground(Color.WHITE);
        this.setSize(Integer.parseInt(EnumDataTools.WINDOW_SIZE_X.getValue()), Integer.parseInt(EnumDataTools.WINDOW_SIZE_Y.getValue()));
        this.setResizable(Boolean.parseBoolean(EnumDataTools.WINDOW_ESTATE_F.getValue()));
        this.setVisible(Boolean.parseBoolean(EnumDataTools.WINDOW_ESTATE_T.getValue()));
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setTitle(EnumDataTools.WINDOW_TITLE_VIEWPANEL.getValue());
        this.setLocationRelativeTo(null);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                Closewindow();
            }
        });
    }

    /**
     * Icon system
     */
    @Override
    public Image getIconImage() {
        Image image = null;
        try {
            image = new ImageIcon(getClass().getResource("/co/app/hc/grafo/resources/images/HC30x30.png")).getImage();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "getImage()", "Graph", JOptionPane.ERROR_MESSAGE);
        }
        return image;
    }

    /**
     * Method that close window
     */
    private void Closewindow() {
        int respuesta = JOptionPane.showConfirmDialog(this, " ¿Desea salir de la sistema? ", "Información - Grafo", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (JOptionPane.YES_OPTION == respuesta) {
            System.exit(0);
        }
    }

    /**
     * Method that execute the calculus of the graph system
     *
     * @param evt ActionEvent
     */
    private void menuExecuteActionPerformed(ActionEvent evt) {
        try {
            String begin = JOptionPane.showInputDialog("Nodo de partida");
            String end = JOptionPane.showInputDialog("Nodo de llegada");
            if (begin != null || !"".equals(begin) || end != null || !"".equals(end)) {
                graph = canvas.getListGraph();
                node = new String[graph.size()];
                bruteforce = new Bruteforce();
                int counting = 0;
                for (Iterator<Graph> it = graph.iterator(); it.hasNext();) {
                    Graph graph1 = it.next();
                    node[counting] = graph1.getName();
                    counting++;
                }
                bruteforce.setNode(node);
                bruteforce.initGraph();
                arc = canvas.getListArc();
                for (Iterator<Arc> it = arc.iterator(); it.hasNext();) {
                    Arc arc1 = it.next();
                    bruteforce.addRoute(arc1.getNodeBegin(), arc1.getNodeEnd(), Integer.parseInt(arc1.getValue()));
                }
                bruteforce.search(begin, end);
            } else {
                JOptionPane.showMessageDialog(null, "Debe indicar el nodo de inicio y el nodo final", EnumMessageSystem.WARNING_MESSAGE.getValue(), JOptionPane.WARNING_MESSAGE);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_INDEX.getValue(), EnumMessageSystem.WARNING_MESSAGE.getValue(), JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "menuExecuteActionPerformed(ActionEvent evt) " + e, "Viewpanel", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Method that create a new arc in the canvas
     *
     * @param evt ActionEvent
     */
    private void menuNewArcActionPerformed(ActionEvent evt) {
        try {
            canvas.dataNewArc();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "menuNewArcActionPerformed(ActionEvent evt) " + e, "Viewpanel", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Clear all canvas
     *
     * @param evt ActionEvent
     */
    private void menuClearActionPerformed(ActionEvent evt) {
        try {
            canvas.clearListArc();
            canvas.clearListGraph();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "menuClearActionPerformed(ActionEvent evt) " + e, "Viewpanel", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Sava a image of canvas
     * @param evt ActionEvent
     */
    private void menuSaveActionPerformed(ActionEvent evt) {
        try {
            Graphics g = canvas.getGraphics();
            g = image.getGraphics();
            canvas.paint(g);
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.png", "png", "PNG");
            fileChooser.setFileFilter(filter);
            fileChooser.setApproveButtonText("Guardar imagen");
            fileChooser.showSaveDialog(null);
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                String path = file.getAbsolutePath();
                File temp = new File(path + ".png");
                file.renameTo(temp);
                ImageIO.write(image, "png", temp);
                JOptionPane.showMessageDialog(null, "Se ha generado la imagen correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            System.out.println("Error de escritura");
        }
    }

    /**
     * Method that close the graph system
     *
     * @param evt ActionEvent
     */
    private void menuCloseActionPerformed(ActionEvent evt) {
        try {
            Closewindow();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "menuCloseActionPerformed(ActionEvent evt)", "Viewpanel", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Method that shows system information graph
     *
     * @param evt ActionEvent
     */
    private void menuAboutActionPerformed(ActionEvent evt) {
        try {
            Information information = new Information();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, EnumMessageSystem.EXCEPTION_MESSAGE.getValue() + "menuCloseActionPerformed(ActionEvent evt)", "Viewpanel", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Components
     */
    private javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();
    private javax.swing.JMenuItem menuItem = new javax.swing.JMenuItem();
    private javax.swing.JMenu menu;
    private BufferedImage image = new BufferedImage(765, 390, BufferedImage.TYPE_INT_RGB);
}
