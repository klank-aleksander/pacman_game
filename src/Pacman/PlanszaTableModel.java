package Pacman;

import javax.swing.table.AbstractTableModel;

public class PlanszaTableModel
        extends AbstractTableModel{
    private static int[][] poziom;
    //0 to sciany
    //1 to pola po ktorych postac moze sie poruszac
    //2 to punkty
    //4 to punkt zmieniajacy duchy
    //8 to duch czerwony
    //16 to duch blekitny
    //32 to duch rozowy
    //64 to duch zolty
    //128 to postac pacmana
    public PlanszaTableModel(int rows, int columns){
        Maze maze = new Maze(rows,columns);
        this.poziom  = wygenerujPlansze(maze.generateMaze());
    }

    @Override
    public int getRowCount() {
        return poziom.length;
    }

    @Override
    public int getColumnCount() {
        return poziom[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if((poziom[rowIndex][columnIndex]&2) == 2){
            return "*";
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            fireTableCellUpdated(rowIndex,columnIndex);
    }

    public static int getPoziomCell(int row, int column){
        return poziom[row][column];
    }
    public static void setPoziomCell(int value,int row, int column){
        poziom[row][column] = value;
    }
    public static void addPacManCell(int row, int column){
        poziom[row][column] += 128;
    }

    public static void removePacManCell(int row, int column){
        poziom[row][column] -= 128;
    }

    public static void movePacMan(int lastX, int lastY, int newX, int newY){
        removePacManCell(lastX, lastY);
        if((poziom[newX][newY]&2) == 2){
            poziom[newX][newY] -= 2;
            PacMan.addPoint();
        }
        addPacManCell(newX, newY);
    }

    public static void removeGhostCell(int row, int column, int ghostValue){ poziom[row][column] -= ghostValue; }
    public static void addGhostCell(int row, int column, int ghostValue){ poziom[row][column] += ghostValue; }

    public static void moveGhost(int lastX, int lastY, int newX, int newY, int ghostVal){
        removeGhostCell(lastX,lastY,ghostVal);
        addGhostCell(newX,newY,ghostVal);
    }

    public static int getPoziomRows(){
        return poziom.length;
    }

    public static int getPoziomColumns(){
        return poziom[0].length;
    }

    public int[][] wygenerujPlansze(int[][] maze) {

        int counter;
        //wyznaczam dodatkowe przejscia w labiryncie (w poziomie i pionie)
        for(int i = 0; i < maze.length; i++){
            counter = 0;
            for(int j = 0; j < maze[0].length; j++){
                if(maze[i][j] == 0){
                    counter++;
                    if(counter%4 == 0){
                        maze[i][j] = 1;
                    }
                }else {
                    counter = 0;
                }
            }
        }
        for(int i = 0; i < maze[0].length; i++){
            counter = 0;
            for(int j = 0; j < maze.length; j++){
                if(maze[j][i] == 0){
                    counter++;
                    if(counter%4 == 0){
                        maze[j][i] = 1;
                    }
                }else {
                    counter = 0;
                }
            }
        }


        //ustawiam przejscia na skrajnych scianach poziomu
        if(maze.length > 15 && maze[0].length > 15){
            for(int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[0].length; j++) {
                    if (i == 0 || i == maze.length-1 || j == 0 || j == maze[0].length-1){
                        maze[i][j] = 1;
                    }
                }
            }
        }

        int pacmanRow = maze.length/2;
        int pacmanColumn = maze[0].length/2;

        //wyznaczam srodek poziomu
        for(int i = pacmanRow-5; i <= pacmanRow; i++){
            for(int j = pacmanColumn-3; j <= pacmanColumn+3; j++){
                if((i == pacmanRow - 4 && (j == pacmanColumn -2 || j == pacmanColumn+2))
                        || (i == pacmanRow - 1 && (j >= pacmanColumn -2 && j <= pacmanColumn+2))
                        || (i > pacmanRow - 4 && i < pacmanRow -1)
                        && (j == pacmanColumn -2 || j == pacmanColumn+2)
                ){
                    maze[i][j] = 0;
                }else{
                    maze[i][j] = 1;
                }

            }
        }

        //przyznaczam punkty;
        PacMan.setPoints_to_collect(0);
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                if(maze[i][j] == 1){
                    maze[i][j] += 2;
                    PacMan.setPoints_to_collect(PacMan.getPoints_to_collect()+1);
                }
            }
        }
        System.out.println("points to collect: "+PacMan.getPoints_to_collect());
        return maze;
    }

}
