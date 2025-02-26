package Pacman;

import javax.swing.*;

import java.awt.*;

import static Pacman.Kierunek.*;

public class PacMan {
    private static int points;
    private static int points_to_collect;
    private static int lives = 3;
    private static Kierunek kierunek;
    private Kierunek nextKierunek;
    private Kierunek[] kierunki = {LEFT,RIGHT,UP,DOWN};
    private static int row;
    private static int baseRow;
    private static int column;
    private static int baseColumn;

    private static MyImageIcon pacRight = new MyImageIcon("src/images/pac_right.png");
    private static MyImageIcon pacLeft = new MyImageIcon("src/images/pac_left.png");
    private static MyImageIcon pacUp = new MyImageIcon("src/images/pac_up.png");
    private static MyImageIcon pacDown = new MyImageIcon("src/images/pac_down.png");

    public PacMan(int row, int column){
        this.row = row;
        this.baseRow = row;
        this.column = column;
        this.baseColumn = column;
        this.kierunek = RIGHT;
        this.nextKierunek = kierunek;
        points = 0;
        lives = 3;
        PlanszaTableModel.addPacManCell(row,column);
    };

    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }


    public static ImageIcon getPacManIcon(){
        switch (kierunek){
            case RIGHT:{
                return pacRight.getScaledImageIcon();
            }
            case LEFT:{
                return pacLeft.getScaledImageIcon();
            }
            case UP:{
                return pacUp.getScaledImageIcon();
            }
            case DOWN:{
                return pacDown.getScaledImageIcon();
            }
            default:{
                return pacRight.getScaledImageIcon();
            }
        }
    }

    public void movePacMan(int row, int column){
            if(
                    this.row+row < PlanszaTableModel.getPoziomRows() && this.row + row >= 0 //sprawdzam czy nie wychodzi poza zakres
                    && this.column+column < PlanszaTableModel.getPoziomColumns() && this.column + column >= 0
                    && PlanszaTableModel.getPoziomCell(this.row+row,this.column+column) != 0
            ){
                if((PlanszaTableModel.getPoziomCell(this.row+row,this.column+column)&8) == 8
                        || (PlanszaTableModel.getPoziomCell(this.row+row,this.column+column)&16) == 16
                        || (PlanszaTableModel.getPoziomCell(this.row+row,this.column+column)&32) == 32
                        || (PlanszaTableModel.getPoziomCell(this.row+row,this.column+column)&64) == 64
                ){
                    //przenosze pacmana na srodek jesli zderzy sie z duchem
                    killPacMan();
                } else{
                    PlanszaTableModel.movePacMan(this.row,this.column,this.row+row,this.column+column);
                    this.row += row;
                    this.column += column;
                }
            }
        //else kierunek = kierunki[(int)(Math.random()*4)];
    }

    public void movePacManWKierunku(){
        if(czyMozliwaZmianaKierunku())
            kierunek = nextKierunek;

        int row = 0;
        int column = 0;
        switch(kierunek){
            case RIGHT -> {
                column=1;
                break;
            }
            case LEFT -> {
                column = -1;
                break;
            }
            case UP -> {
                row = -1;
                break;
            }
            case DOWN -> {
                row = 1;
                break;
            }
        }
        movePacMan(row,column);
    }

    public boolean czyMozliwaZmianaKierunku(){
        int row = 0;
        int column = 0;
        switch(nextKierunek){
            case RIGHT -> {
                column=1;
                break;
            }
            case LEFT -> {
                column = -1;
                break;
            }
            case UP -> {
                row = -1;
                break;
            }
            case DOWN -> {
                row = 1;
                break;
            }
        }
        if(this.row+row < PlanszaTableModel.getPoziomRows() && this.row + row >= 0
                && this.column+column < PlanszaTableModel.getPoziomColumns() && this.column + column >= 0
                && PlanszaTableModel.getPoziomCell(this.row+row,this.column+column) != 0
        )  {
            return true;
        }
        else{
            return false;
        }

    }

    public static void killPacMan(){
        PlanszaTableModel.movePacMan(row,column,baseRow,baseColumn);
        row = baseRow;
        column = baseColumn;
        lives--;
        System.out.println(lives);
    }

    public static void addPoint(){
        points++;
        System.out.println("points "+points);
    }

    public static int getPoints() {
        return points;
    }

    public static int getLives() {
        return lives;
    }

    public static void setLives(int lives) {
        PacMan.lives = lives;
    }

    public static int getPoints_to_collect() {
        return points_to_collect;
    }

    public static void setPoints_to_collect(int points_to_collect) {
        PacMan.points_to_collect = points_to_collect;
    }

    public void setNextKierunek(Kierunek kierunek){
        this.nextKierunek = kierunek;
    }
    public void setKierunek(Kierunek kierunek){
        this.kierunek = kierunek;
    }


}
