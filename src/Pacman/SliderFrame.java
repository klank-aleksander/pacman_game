package Pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SliderFrame
    extends JFrame {

    public SliderFrame(){
        super("Maze size");
        setBackground(Color.BLACK);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(400,400);
        JPanel panel = new JPanel();
        add(panel);
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));

        JLabel jLabel = new JLabel("Choose the size of the maze");
        jLabel.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));
        jLabel.setBackground(Color.BLACK);
        jLabel.setForeground(Color.YELLOW);

        JSlider jSlider1 = new JSlider(JSlider.HORIZONTAL, 10,100, 60);
        jSlider1.setBackground(Color.BLUE);
        jSlider1.setMinorTickSpacing(5);
        jSlider1.setMajorTickSpacing(10);
        jSlider1.setPaintTicks(true);
        jSlider1.setPaintLabels(true);
        jSlider1.setForeground(Color.YELLOW);

        JSlider jSlider2 = new JSlider(JSlider.VERTICAL, 10, 100, 60);
        jSlider2.setBackground(Color.BLUE);
        jSlider2.setMinorTickSpacing(5);
        jSlider2.setMajorTickSpacing(10);
        jSlider2.setPaintTicks(true);
        jSlider2.setPaintLabels(true);
        jSlider2.setForeground(Color.YELLOW);

        JButton jButton = new JButton("OK");
        jButton.setBackground(Color.BLACK);
        jButton.setForeground(Color.YELLOW);
        jButton.setOpaque(true);
        jButton.setBorderPainted(false);

        panel.setLayout(new FlowLayout());
        panel.add(jLabel);
        panel.add(jButton);
        panel.add(jSlider1);
        panel.add(jSlider2);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(()->new GraFrame(jSlider2.getValue(),jSlider1.getValue()));
                dispose();
            }
        });



    }
}
