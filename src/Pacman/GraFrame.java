package Pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GraFrame
        extends JFrame{
    private boolean inGame;
    private Thread movement;
    private Thread odswiezanieObrazu;
    private Thread odliczanieCzasu;
    private static int czas = 0;
    private int rows, columns;

    public GraFrame(int rows, int columns){
        super("PACMAN");
        this.rows = rows;
        this.columns = columns;
        generateFrame();
    }

    public void generateFrame(){
        PlanszaTable plansza = new PlanszaTable(new PlanszaTableModel(rows,columns));
        PacMan pacMan = new PacMan(PlanszaTableModel.getPoziomRows()/2,PlanszaTableModel.getPoziomColumns()/2);
        Ghost red = new Ghost(PlanszaTableModel.getPoziomRows()/2-3,PlanszaTableModel.getPoziomColumns()/2-1,8);
        Ghost blue = new Ghost(PlanszaTableModel.getPoziomRows()/2-3,PlanszaTableModel.getPoziomColumns()/2+1,16);
        Ghost pink = new Ghost(PlanszaTableModel.getPoziomRows()/2-4,PlanszaTableModel.getPoziomColumns()/2-1,32);
        Ghost yellow = new Ghost(PlanszaTableModel.getPoziomRows()/2-4,PlanszaTableModel.getPoziomColumns()/2+1,64);
        inGame = true;


        czas = 0;

        JLabel czasLabel = new JLabel("Time: "+czas);
        czasLabel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("time changed")){
                    czasLabel.setText("Time: "+(++czas));
                }
            }
        });
        czasLabel.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
        czasLabel.setForeground(Color.YELLOW);
//        JPanel czasPanel = new JPanel();
//        czasPanel.setBackground(Color.BLACK);
//        czasPanel.add(czasLabel);

        JLabel punkty = new JLabel("Points: "+PacMan.getPoints());
        punkty.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("changed points")){
                    punkty.setText("Points: "+PacMan.getPoints());
                }
            }
        });
        punkty.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
        punkty.setForeground(Color.YELLOW);


        JLabel zycia = new JLabel("Zycia: "+PacMan.getLives());
        zycia.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("changed hearts")){
                    zycia.setText("Hearts: "+PacMan.getLives());
                }
            }
        });
        zycia.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
        zycia.setForeground(Color.YELLOW);

        JPanel inGameInfo = new JPanel();
        inGameInfo.setLayout(new GridLayout(3,1));
        inGameInfo.setBackground(Color.BLACK);
        inGameInfo.add(punkty);
        inGameInfo.add(zycia);
        inGameInfo.add(czasLabel);

        plansza.setBackground(Color.BLUE);
        plansza.setCellSelectionEnabled(false);
        plansza.setTableHeader(null);
        plansza.setDefaultRenderer(Object.class, new MyTableCellRenderer());
        plansza.setPreferredSize(new Dimension(500,500));
        plansza.setBorder(BorderFactory.createLineBorder(Color.YELLOW,1));


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setLayout(new BorderLayout());
//        add(plansza,BorderLayout.CENTER);
//        add(inGameInfo, BorderLayout.LINE_END);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 3.0;
        gbc.weighty = 3.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(plansza,gbc);

        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(inGameInfo,gbc);

        setResizable(true);
        setVisible(true);
        setBackground(Color.BLUE);
        getContentPane().setBackground(Color.BLUE);
        pack();

        odswiezanieObrazu = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                while(inGame){
                    try {
                        Thread.sleep(33);
                        plansza.repaint();
                        PacMan.getPacManIcon();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    if(Thread.interrupted()){
                        break;
                    }
                }
            }
        });

        movement = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                while(inGame){
                    try {
                        Thread.sleep(200);
                        red.moveGhostDirection();
                        blue.moveGhostDirection();
                        pink.moveGhostDirection();
                        yellow.moveGhostDirection();
                        pacMan.movePacManWKierunku();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    if(!punkty.getText().equals("Points: "+PacMan.getPoints())){
                        punkty.firePropertyChange("changed points",0,1);
                    }
                    if(!zycia.getText().equals("Hearts: "+PacMan.getLives())){
                        zycia.firePropertyChange("changed hearts",0,1);
                    }
                    if(PacMan.getLives()<=0 && inGame){
                        dispose();
                    }
                    if(PacMan.getPoints() >= PacMan.getPoints_to_collect() && inGame){
                        dispose();
                    }
                    if(Thread.interrupted()){
                        break;
                    }
                }
            }
        });

        int czas = 0;
        odliczanieCzasu = new Thread(new Runnable() {
            @Override
            public void run() {
                while(inGame){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    czasLabel.firePropertyChange("time changed",0,1);

                }
            }
        });

        odliczanieCzasu.start();
        odswiezanieObrazu.start();
        movement.start();

        setFocusable(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.isControlDown() && e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_Q && inGame){
                    dispose();
                }
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:{
                        pacMan.setNextKierunek(Kierunek.UP);
                        break;
                    }
                    case KeyEvent.VK_DOWN:{
                        pacMan.setNextKierunek(Kierunek.DOWN);
                        break;
                    }
                    case KeyEvent.VK_RIGHT:{
                        pacMan.setNextKierunek(Kierunek.RIGHT);
                        break;
                    }
                    case KeyEvent.VK_LEFT:{
                        pacMan.setNextKierunek(Kierunek.LEFT);
                        break;
                    }
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        plansza.setFocusable(false);

    }

//    public void endGame(){
//        inGame = false;
//        odswiezanieObrazu.interrupt();
//        movement.interrupt();
//
//        dispose();
//        SwingUtilities.invokeLater(()->new PodawanieWyniku(PacMan.getPoints()));
//        System.out.println("the end");
//    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void dispose() {
        inGame = false;
        odswiezanieObrazu.interrupt();
        movement.interrupt();
        odliczanieCzasu.interrupt();
        SwingUtilities.invokeLater(()->new PodawanieWyniku(PacMan.getPoints()));
        System.out.println("the end");
        try {
            movement.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        try {
            odswiezanieObrazu.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        try {
            odliczanieCzasu.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        super.dispose();
    }
}


