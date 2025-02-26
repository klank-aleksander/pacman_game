package Pacman;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class PlanszaTable
        extends JTable {

    private static int cellSize = 1;
    public PlanszaTable(TableModel tm){
        super(tm);
    }

    @Override
    public void doLayout() {
        double calc = Math.min(getHeight()/getRowCount(),getWidth()/getColumnCount());
        cellSize = (int)(calc);
        if(cellSize < 1)
            cellSize = 1;
        setRowHeight(cellSize);
        for(int i = 0; i < getColumnCount(); i++) {
            getColumnModel().getColumn(i).setMaxWidth(cellSize);
            getColumnModel().getColumn(i).setMinWidth(cellSize);
        }
        super.doLayout();
    }


    public static int getCellSize() {
        return cellSize;
    }


    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
