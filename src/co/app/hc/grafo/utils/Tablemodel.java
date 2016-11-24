/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.app.hc.grafo.utils;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Harold Castillo (HC)
 */
public class Tablemodel extends AbstractTableModel{
    
    String[] header;
    String[][] data;

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }
    
    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
    
    public void setValueAt(String header, int rowIndex, int columnIndex){
        this.setValueAt(header, rowIndex, columnIndex);
    }
}
