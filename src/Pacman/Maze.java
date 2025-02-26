package Pacman;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private int rows;
    private int columns;
    private List<int[]> possibleCells;
    private int[][] maze;
    private int currRow;
    private int currCol;
    public Maze(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        possibleCells = new ArrayList<>();
        maze = new int[rows][columns];
    }

    public int[][] generateMaze(){
        currRow = (int)(Math.random()*rows);
        currCol = (int)(Math.random()*columns);
        maze[currRow][currCol] = 1;

        //wyznaczam pierwsze mozliwe ruchy
        calculateCellsNearby();

        int[] nextCell;
        //bazowane na algorytmie Prima
        while(!possibleCells.isEmpty()){
            //losuje jeden z mozliwych ruchow
            nextCell = possibleCells.get((int)(Math.random()*possibleCells.size()));
            //System.out.println("nextcell: "+nextCell[0]+" "+nextCell[1]);

            //usuwam z mozliwych ruchow
            possibleCells.remove(nextCell);

            //nadpisuje pozycje
            currRow = nextCell[0];
            //System.out.println("currRow "+currRow);
            currCol = nextCell[1];
            //System.out.println("currCol "+currCol);

            //wyznaczam sciezke do drogi
            if (currRow-2 < maze.length && currRow-2 >= 0
                    && maze[currRow-2][currCol] == 1) {
                maze[currRow-1][currCol] = 1;
                maze[currRow][currCol] = 1;
            }else if (currCol+2 < maze[0].length && currCol+2 >= 0
                    && maze[currRow][currCol+2] == 1) {
                maze[currRow][currCol+1] = 1;
                maze[currRow][currCol] = 1;
            }else if(currRow+2 < maze.length && currRow+2 >= 0
                    && maze[currRow+2][currCol] == 1){
                maze[currRow+1][currCol] = 1;
                maze[currRow][currCol] = 1;
            }else if (currCol-2 < maze[0].length && currCol-2 >= 0
                    && maze[currRow][currCol-2] == 0) {
                maze[currRow][currCol-1] = 1;
                maze[currRow][currCol] = 1;
            }

            //znowu wyznaczam mozliwe ruchy
            calculateCellsNearby();
        }

        return maze;
    }

    public void calculateCellsNearby(){
        //System.out.println("calculateCellsNearby()");
        //int cell[] = new int[2];
        if(currRow+2 < maze.length && currRow+2 >= 0
        && maze[currRow+2][currCol] == 0){
            int cell[] = new int[2];
            cell[0] = currRow+2;
            cell[1] = currCol;
            if(!isChecked(cell)){
                //System.out.println("added possible cell: "+cell[0]+" "+cell[1]);
                possibleCells.add(cell);
            }
        }
        if (currRow-2 < maze.length && currRow-2 >= 0
                && maze[currRow-2][currCol] == 0){
            int cell[] = new int[2];
            cell[0] = currRow-2;
            cell[1] = currCol;
            if(!isChecked(cell)){
                //System.out.println("added possible cell: "+cell[0]+" "+cell[1]);
                possibleCells.add(cell);
            }
        }
        if(currCol+2 < maze[0].length && currCol+2 >= 0
        && maze[currRow][currCol+2] == 0){
            int cell[] = new int[2];
            cell[0] = currRow;
            cell[1] = currCol+2;
            if(!isChecked(cell)){
                //System.out.println("added possible cell "+cell[0]+" "+cell[1]);
                possibleCells.add(cell);
            }
        }
        if (currCol-2 < maze[0].length && currCol-2 >= 0
                && maze[currRow][currCol-2] == 0) {
            int cell[] = new int[2];
            cell[0] = currRow;
            cell[1] = currCol-2;
            if(!isChecked(cell)){
                //System.out.println("added possible cell: "+cell[0]+" "+cell[1]);
                possibleCells.add(cell);
            }
        }
    }

    public void findPath(){
        int paths = 4;
        int los = 0;
        while(paths > 0){
            los = (int)(Math.random()*paths);
            switch(los){
                case 0:{
                    if (currRow-2 < maze.length && currRow-2 >= 0
                            && maze[currRow-2][currCol] == 1) {
                        maze[currRow-1][currCol] = 1;
                        maze[currRow][currCol] = 1;
                        return;
                    }
                }
                case 1:{
                    if (currCol+2 < maze[0].length && currCol+2 >= 0
                            && maze[currRow][currCol+2] == 1) {
                        maze[currRow][currCol+1] = 1;
                        maze[currRow][currCol] = 1;
                        return;
                    }
                }
                case 2:{
                    if(currRow+2 < maze.length && currRow+2 >= 0
                            && maze[currRow+2][currCol] == 1){
                        maze[currRow+1][currCol] = 1;
                        maze[currRow][currCol] = 1;
                        return;
                    }
                }
                case 3:{
                    if (currCol-2 < maze[0].length && currCol-2 >= 0
                            && maze[currRow][currCol-2] == 0) {
                        maze[currRow][currCol-1] = 1;
                        maze[currRow][currCol] = 1;
                        return;
                    }
                }
            }

        }

    }


    public boolean isChecked(int[] cell){
        for(int i = 0; i < possibleCells.size(); i++){
            if(possibleCells.get(i)[0] == cell[0] && possibleCells.get(i)[1] == cell[1]){
                return true;
            }
        }
        return false;

    }
}
