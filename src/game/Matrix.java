package game;

import java.awt.*;
import java.util.Random;

//23
public class Matrix {
    public static final int COLOR_2 = new Color(255,255,232).getRGB();
    public static final int COLOR_4 = new Color(255,209,209).getRGB();
    public static final int COLOR_8 = new Color(255,186,186).getRGB();
    public static final int COLOR_16 = new Color(255,163,163).getRGB();
    public static final int COLOR_32 = new Color(255,140,140).getRGB();
    public static final int COLOR_64 = new Color(255,177,117).getRGB();
    public static final int COLOR_128 = new Color(255,153,0).getRGB();
    public static final int COLOR_256 = new Color(255,71,71).getRGB();
    public static final int COLOR_512 = new Color(255,25,25).getRGB();
    public static final int COLOR_1024 = new Color(200,2,200).getRGB();
    public static final int COLOR_2048 = new Color(100,48,150).getRGB();
    private int rows;
    private int cols;
    private Square [][] matrix;
    private Random rand = new Random();

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new Square[rows][cols];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = null;
            }
        }
        spawnRandom();
        spawnRandom();
    }

    public void moveUp(){
        for (int row = 1; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] != null) {
                    if (matrix[row-1][col] != null && matrix[row][col].number == matrix[row-1][col].number){
                        //smash together
                        smashTogether(col,row,col,row-1);
                    } else if (row >= 2 && matrix[row-2][col] != null && matrix[row][col].number == matrix[row-2][col].number){
                        //smash together
                        smashTogether(col,row,col,row-2);
                    } else if (row >= 3 && matrix[row-3][col] != null && matrix[row][col].number == matrix[row-3][col].number){
                        //smash together
                        smashTogether(col,row,col,row-3);
                    }
                    //then move up
                    move(col,row,col,row -(row-1));
                }
            }
        }
        resetUsed();
        spawnRandom();
    }

    public void moveLeft(){
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 1; col < matrix[row].length; col++) {
                if (matrix[row][col] != null) {
                    if (matrix[row][col-1] != null && matrix[row][col].number == matrix[row][col-1].number){
                        //smash together
                        smashTogether(col,row,col-1,row);
                    } else if (col >= 2 && matrix[row][col-2] != null && matrix[row][col].number == matrix[row][col-2].number){
                        //smash together
                        smashTogether(col,row,col-2,row);
                    } else if (col >= 3 && matrix[row][col-3] != null && matrix[row][col].number == matrix[row][col-3].number){
                        //smash together
                        smashTogether(col,row,col-3,row);
                    }
                    //then move left
                    move(col,row,col - (cols - 1),row);
                }
            }
        }
        resetUsed();
        spawnRandom();
    }

    public void moveRight(){
        for (int row = 0; row < matrix.length; row++) {
            for (int col = matrix[row].length-2; col >= 0 ; col--) {
                if (matrix[row][col] != null) {
                    if (matrix[row][col+1] != null && matrix[row][col].number == matrix[row][col+1].number){
                        //smash together
                        smashTogether(col,row,col+1,row);
                    } else if (row >= 2 && matrix[row][col+2] != null && matrix[row][col].number == matrix[row][col+2].number){
                        //smash together then move up
                        smashTogether(col,row,col+2,row);
                    } else if (row >= 3 && matrix[row][col+3] != null && matrix[row][col].number == matrix[row][col+3].number){
                        //smash together then move up
                        smashTogether(col,row,col,row+3);
                    }
                    //then move up
                    move(col,row,col + (cols - 1),row);
                }
            }
        }
        resetUsed();
        spawnRandom();
    }

    public void moveDown(){
        for (int row = matrix.length-2; row >= 0 ; row--) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] != null) {
                    if (matrix[row+1][col] != null && matrix[row][col].number == matrix[row+1][col].number){
                        //smash together
                        smashTogether(col,row,col,row+1);
                    } else if (row >= 2 && matrix[row+2][col] != null && matrix[row][col].number == matrix[row+2][col].number){
                        //smash together then move up
                        smashTogether(col,row,col,row+2);
                    } else if (row >= 3 && matrix[row+3][col] != null && matrix[row][col].number == matrix[row+3][col].number){
                        //smash together then move up
                        smashTogether(col,row,col,row+3);
                    }
                    //then move up
                    move(col,row,col,row + (rows - 1));
                }
            }
        }
        resetUsed();
        spawnRandom();
    }

    private void spawnRandom(){
        int randomRow = rand.nextInt(rows);
        int randomCol = rand.nextInt(cols);
        while (matrix[randomRow][randomCol] != null){
            randomRow = rand.nextInt(rows);
            randomCol = rand.nextInt(cols);
        }
        Square square = new Square();
        matrix[randomRow][randomCol] = new Square();
    }

    private void smashTogether(int col1, int row1, int col2, int row2){
        Square square = new Square();
        square.number = matrix[row1][col1].number + matrix[row2][col2].number;
        square.used = true;
        switch (square.number){
            case 2:
                square.color = COLOR_2;
                break;
            case 4:
                square.color = COLOR_4;
                break;
            case 8:
                square.color = COLOR_8;
                break;
            case 16:
                square.color = COLOR_16;
                break;
            case 32:
                square.color = COLOR_32;
                break;
            case 64:
                square.color = COLOR_64;
                break;
            case 128:
                square.color = COLOR_128;
                break;
            case 256:
                square.color = COLOR_256;
                break;
            case 512:
                square.color = COLOR_512;
                break;
            case 1024:
                square.color = COLOR_1024;
                break;
            case 2048:
                square.color = COLOR_2048;
        }
        matrix[row1][col1] = square;
        matrix[row2][col2] = null;
    }

    private void move(int fromCol, int fromRow, int toCol, int toRow){
        matrix[toRow][toCol] = matrix[fromRow][fromCol];
        matrix[fromRow][fromCol] = null;
    }

    private void resetUsed(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != null){
                    matrix[i][j].used = false;
                }
            }
        }
    }

    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return cols;
    }

    public Square getSquare(int row, int col){
        if (row > this.rows || col > this.cols){
            throw new ArrayIndexOutOfBoundsException();
        }
        return matrix[row][col];
    }

    public int getNumber(int row, int col) {
        if (row > this.rows || col > this.cols){
            throw new ArrayIndexOutOfBoundsException();
        }
        if (matrix[row][col] != null){
            return matrix[row][col].number;
        }
        return 0;
    }

    public int getColor(int row, int col) {
        if (row > this.rows || col > this.cols){
            throw new ArrayIndexOutOfBoundsException();
        }
        if (matrix[row][col] != null){
            return matrix[row][col].color;
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                toReturn.append("|");
                toReturn.append(getNumber(i,j));
                toReturn.append("|");
            }
            toReturn.append("\n");
        }
        toReturn.append("-----------------------------------------------");
        return toReturn.toString();
    }

    private class Square {
        private int number;
        private int color;
        private boolean used;
    }
}
