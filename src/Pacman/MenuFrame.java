package Pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame
        extends JFrame{
    public MenuFrame(){
        super("Menu");
        generateFrame();
    }

    private void generateFrame() {
        setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(400,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        WynikiModel.odczytajWyniki();

        JButton newGame = new JButton("New Game");
        newGame.setForeground(Color.YELLOW);
        newGame.setBackground(Color.BLACK);
        newGame.setOpaque(true);
        newGame.setBorderPainted(false);

        JButton highScores = new JButton("High Scores");
        highScores.setForeground(Color.YELLOW);
        highScores.setBackground(Color.BLACK);
        highScores.setOpaque(true);
        highScores.setBorderPainted(false);

        JButton exit = new JButton("Exit");
        exit.setForeground(Color.YELLOW);
        exit.setBackground(Color.BLACK);
        exit.setOpaque(true);
        exit.setBorderPainted(false);


        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.BLACK);
        menuPanel.setLayout(new GridLayout(3,1));

        menuPanel.add(newGame);
        menuPanel.add(highScores);
        menuPanel.add(exit);
        getContentPane().add(menuPanel,BorderLayout.CENTER);


        highScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->new WynikiFrame());
            }
        });

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->new SliderFrame());
                //SwingUtilities.invokeLater(()->new GraFrame());
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });



    }

}
