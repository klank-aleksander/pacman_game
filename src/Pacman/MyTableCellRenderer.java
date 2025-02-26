package Pacman;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
public class MyTableCellRenderer extends DefaultTableCellRenderer{
    private MyImageIcon redGhostIcon = new MyImageIcon("src/images/red.png");
    private MyImageIcon blueGhostIcon = new MyImageIcon("src/images/blue.png");
    private MyImageIcon pinkGhostIcon = new MyImageIcon("src/images/pink.png");
    private MyImageIcon yellowGhostIcon = new MyImageIcon("src/images/yellow.png");
    //private MyImageIcon points = new MyImageIcon("C:/Users/alekl/IdeaProjects/Projekt_pacman/src/Pacman/point.png");

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);

        table.setGridColor(Color.BLACK);
        if(PlanszaTableModel.getPoziomCell(row,column) == 0){
            setBackground(Color.BLUE);
        }else{
            setBackground(Color.BLACK);
        }

        if((PlanszaTableModel.getPoziomCell(row, column)&128) == 128){
            setIcon(PacMan.getPacManIcon());
            setHorizontalAlignment(JLabel.LEFT);
        }
        else if((PlanszaTableModel.getPoziomCell(row, column)&8) == 8){
            //setIcon(PacMan.getPacManIcon());
            setIcon(redGhostIcon.getScaledImageIcon());
            setHorizontalAlignment(JLabel.LEFT);
        }
        else if((PlanszaTableModel.getPoziomCell(row, column)&16) == 16){
            //setIcon(PacMan.getPacManIcon());
            setIcon(blueGhostIcon.getScaledImageIcon());
            setHorizontalAlignment(JLabel.LEFT);
        }
        else if((PlanszaTableModel.getPoziomCell(row, column)&32) == 32){
            //setIcon(PacMan.getPacManIcon());
            setIcon(pinkGhostIcon.getScaledImageIcon());
            setHorizontalAlignment(JLabel.LEFT);
        }
        else if((PlanszaTableModel.getPoziomCell(row, column)&64) == 64){
            //setIcon(PacMan.getPacManIcon());
            setIcon(yellowGhostIcon.getScaledImageIcon());
            setHorizontalAlignment(JLabel.LEFT);
        } else {
            setIcon(null);
            setForeground(Color.YELLOW);
            setHorizontalAlignment(JLabel.CENTER);
        }
//       else if ((PlanszaTableModel.getPoziomCell(row,column)&1)== 1) {
//            setIcon(points.getScaledImageIcon());
//            setHorizontalAlignment(JLabel.LEFT);
//
//        }


        return this;
    }
}
