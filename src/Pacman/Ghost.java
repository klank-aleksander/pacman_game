package Pacman;

import static Pacman.Kierunek.*;
import static Pacman.Kierunek.DOWN;

public class Ghost {

    private int row;
    private int column;
    private int ghostValue;
    private Kierunek kierunek = UP;
    private Kierunek[] kierunki = {LEFT,RIGHT,UP,DOWN};

    public Ghost(int row, int column, int ghostValue){
        this.row = row;
        this.column = column;
        this.ghostValue = ghostValue;
        PlanszaTableModel.addGhostCell(this.row,this.column,this.ghostValue);
    }

    public void moveGhost(int row, int column){
        if(
                this.row+row < PlanszaTableModel.getPoziomRows() && this.row + row >= 0 //sprawdzam czy nie wychodzi poza zakres
                        && this.column+column < PlanszaTableModel.getPoziomColumns() && this.column + column >= 0
                        && PlanszaTableModel.getPoziomCell(this.row+row,this.column+column) != 0
                        && (Math.random()<0.9)  //dodaje losowosci ruchowi duchow
        ){
            PlanszaTableModel.moveGhost(this.row,this.column,this.row+row,this.column+column,ghostValue);
            this.row += row;
            this.column += column;
            if((PlanszaTableModel.getPoziomCell(this.row,this.column)&128) == 128){
                PacMan.killPacMan();
            }
        }else{
            kierunek = kierunki[(int)(Math.random()*4)];
        }
    }

    public void moveGhostDirection(){
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
        moveGhost(row,column);
    }

}
