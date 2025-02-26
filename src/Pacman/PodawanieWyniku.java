package Pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PodawanieWyniku
    extends JFrame {
    private JTextField imie;

    public PodawanieWyniku(int punkty){
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(300,200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(panel);

        JLabel komentarz = new JLabel("Write your nickname:");
        komentarz.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
        komentarz.setBackground(Color.BLACK);
        komentarz.setForeground(Color.YELLOW);

        imie = new JTextField();
        imie.setColumns(20);
        imie.setBackground(Color.BLACK);
        imie.setForeground(Color.YELLOW);

        JButton jButton = new JButton("Save");
        jButton.setBackground(Color.BLACK);
        jButton.setForeground(Color.YELLOW);

        panel.add(komentarz);
        panel.add(imie);
        panel.add(jButton);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WynikiModel.odczytajWyniki();
                WynikiModel.dodajWynik(new Wynik(imie.getText(),punkty));
                WynikiModel.zapiszWyniki();
                dispose();
            }
        });

    }


}
