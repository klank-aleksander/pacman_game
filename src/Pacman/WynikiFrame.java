package Pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WynikiFrame
        extends JFrame{
    public static boolean isOpen = false;
    public WynikiFrame(){
        setBackground(Color.BLACK);
        setForeground(Color.YELLOW);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(300,300);


        WynikiModel.odczytajWyniki();
        WynikiModel.zapiszWyniki();


        JList wyniki = WynikiModel.getWynikJList();

        wyniki.setBackground(Color.BLACK);
        wyniki.setForeground(Color.YELLOW);
        add(new JScrollPane(wyniki));
        getContentPane().setBackground(Color.BLACK);
        setBackground(Color.BLACK);

        setFocusable(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q){
                    dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }


}
