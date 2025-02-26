package Pacman;

import javax.swing.*;
import java.awt.*;

public class MyImageIcon
    extends ImageIcon {

    public MyImageIcon(String plik){
        super(plik);
    }
    public MyImageIcon(Image image){
        super(image);
    }

    public ImageIcon getScaledImageIcon(){
        if(getIconWidth() != PlanszaTable.getCellSize()){
            return new ImageIcon(getImage().getScaledInstance(PlanszaTable.getCellSize(),PlanszaTable.getCellSize(),Image.SCALE_SMOOTH));
        }
        else{
            return this;
        }
    }

}
