/*
 * Software Main Class
 */
package co.app.hc.grafo.view;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Harold Castillo (HC)
 * @see <a href="http://www.haroldcastillo.netau.net">HC Web</a>
 */
public class Main {

    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, "UnsupportedLookAndFeel", "Main - Exception Message", JOptionPane.WARNING_MESSAGE);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "ClassNotFound", "Main - Exception Message", JOptionPane.WARNING_MESSAGE);
        } catch (InstantiationException e) {
            JOptionPane.showMessageDialog(null, "Instantiation", "Main - Exception Message", JOptionPane.WARNING_MESSAGE);
        } catch (IllegalAccessException e) {
            JOptionPane.showMessageDialog(null, "IllegalAccess", "Main - Exception Message", JOptionPane.WARNING_MESSAGE);
        }
        Viewpanel viewpanel = new Viewpanel();
    }
}
