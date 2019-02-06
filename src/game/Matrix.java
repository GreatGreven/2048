package game;

import java.awt.*;
import java.util.Random;

//23
public class Matrix {
    private static final String TAG = "Matrix: ";
    public static final Color COLOR_2 = new Color(255, 255, 232);
    public static final Color COLOR_4 = new Color(255, 209, 209);
    public static final Color COLOR_8 = new Color(255, 186, 186);
    public static final Color COLOR_16 = new Color(255, 163, 163);
    public static final Color COLOR_32 = new Color(255, 140, 140);
    public static final Color COLOR_64 = new Color(255, 177, 117);
    public static final Color COLOR_128 = new Color(255, 153, 0);
    public static final Color COLOR_256 = new Color(255, 71, 71);
    public static final Color COLOR_512 = new Color(255, 25, 25);
    public static final Color COLOR_1024 = new Color(200, 2, 200);
    public static final Color COLOR_2048 = new Color(100, 48, 150);
    private int rows;
    private int cols;
    private Square[][] matrix;
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

    public void moveUp() {
        for (int row = 1; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] != null) {
                    boolean merged = false;
                    for (int i = row - 1; i >= 0; i--) {
                        if (matrix[i][col] != null) {
                            if (matrix[row][col].number == matrix[i][col].number) {
                                smashTogether(row, col, i, col);
                                merged = true;
                                move(i, col, -1, 0);
                                break;
                            }
                        }
                    }
                    if (!merged) {
                        move(row, col, -1, 0);
                    }
                }
            }
        }
        resetUsed();
        spawnRandom();
    }

    public void moveLeft() {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 1; col < matrix[row].length; col++) {
                if (matrix[row][col] != null) {
                    boolean merged = false;
                    for (int i = col - 1; i >= 0; i--) {
                        if (matrix[row][i] != null) {
                            if (matrix[row][col].number == matrix[row][i].number) {
                                smashTogether(row, col, row, i);
                                move(row, i, 0, -1);
                                merged = true;
                                break;
                            }
                        }
                    }
                    if (!merged) {
                        move(row, col, 0, -1);
                    }
                }
            }
        }
        resetUsed();
        spawnRandom();
    }

    public void moveRight() {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = matrix[row].length - 2; col >= 0; col--) {
                if (matrix[row][col] != null) {
                    boolean merged = false;
                    for (int i = col + 1; i < cols; i++) {
                        if (matrix[row][i] != null) {
                            if (matrix[row][col].number == matrix[row][i].number) {
                                smashTogether(row, col, row, i);
                                move(row, i, 0, +1);
                                merged = true;
                                break;
                            }
                        }
                    }
                    if (!merged) {
                        move(row, col, 0, +1);
                    }
                }
            }
        }
        resetUsed();
        spawnRandom();
    }

    public void moveDown() {
        for (int row = matrix.length - 2; row >= 0; row--) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] != null) {
                    boolean merged = false;
                    for (int i = row + 1; i < rows; i++) {
                        if (matrix[i][col] != null) {
                            if (matrix[row][col].number == matrix[i][col].number) {
                                smashTogether(row, col, i, col);
                                move(i, col, +1, 0);
                                merged = true;
                                break;
                            }
                        }
                    }
                    if (!merged) {
                        move(row, col, +1, 0);
                    }
                }
            }
        }
        resetUsed();
        spawnRandom();
    }

    private void spawnRandom() {
        int randomRow, randomCol;
        do {
            randomRow = rand.nextInt(rows);
            randomCol = rand.nextInt(cols);
        } while (matrix[randomRow][randomCol] != null);
        Square square = new Square();
        square.number = 2;
        square.color = COLOR_2;
        square.used = false;
        matrix[randomRow][randomCol] = square;
    }

    private void smashTogether(int fRow, int fCol, int tRow, int tCol) {
        Square square = new Square();
        square.number = matrix[fRow][fCol].number + matrix[fRow][fCol].number;
        square.used = true;
        switch (square.number) {
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
        matrix[tRow][tCol] = square;
        matrix[fRow][fCol] = null;
    }

    private void move(int fromRow, int fromCol, int dRow, int dCol) {
        int row = fromRow;
        int col = fromCol;
        if (dRow < 0) {
            while (row - 1 >= 0 && matrix[row - 1][fromCol] == null) {
                row--;
            }
        } else if (dRow > 0) {
            while (row + 1 < rows && matrix[row + 1][fromCol] == null) {
                row++;
            }
        } else if (dCol < 0) {
            while (col - 1 >= 0 && matrix[fromRow][col - 1] == null) {
                col--;
            }
        } else if (dCol > 0) {
            while (col + 1 < cols && matrix[fromRow][col + 1] == null) {
                col++;
            }
        } else {
            System.out.println(TAG + "Move Error");
            System.out.println(dCol);
            System.out.println(dRow);
        }
        if (row != fromRow || col != fromCol) {
            try {
                matrix[row][col] = matrix[fromRow][fromCol];
                matrix[fromRow][fromCol] = null;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("From row: " + fromRow + " and col: " + fromCol);
                System.out.println("To row: " + row + " and col: " + col);
                e.printStackTrace();
            }
        }
    }

    private void resetUsed() {
        for (Square[] row : matrix) {
            for (Square square : row) {
                if (square != null) {
                    square.used = false;
                }
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return cols;
    }

    public Square getSquare(int row, int col) {
        if (row > this.rows || col > this.cols) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return matrix[row][col];
    }

    public int getNumber(int row, int col) {
        if (row > this.rows || col > this.cols) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (matrix[row][col] != null) {
            return matrix[row][col].number;
        }
        return 0;
    }

    public Color getColor(int row, int col) {
        if (row > this.rows || col > this.cols) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (matrix[row][col] != null) {
            return matrix[row][col].color;
        }
        return null;
    }

    public boolean hasWon() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] != null){
                    if (matrix[row][col].number == 2048){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isOver() {
        int pairs = 0;
        int freeSpace = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if(matrix[row][col] != null) {
                    //check up
                    for (int i = row - 1; i >= 0; i--) {
                        if (matrix[i][col] != null) {
                            if (matrix[row][col].number == matrix[i][col].number) {
                                pairs++;
                            } else {
                                break;
                            }
                        }
                    }
                    //check left
                    for (int i = col + 1; i < cols; i++) {
                        if (matrix[row][i] != null){
                            if (matrix[row][col].number == matrix[row][i].number) {
                                pairs++;
                            } else {
                                break;
                            }
                        }
                    }
                    //check right
                    for (int i = col - 1; i >= 0; i--) {
                        if (matrix[row][i] != null){
                            if (matrix[row][col].number == matrix[row][i].number) {
                                pairs++;
                            } else {
                                break;
                            }
                        }
                    }
                    //check down
                    for (int i = row + 1; i < rows; i++) {
                        if (matrix[i][col] != null) {
                            if (matrix[row][col].number == matrix[i][col].number) {
                                pairs++;
                            } else {
                                break;
                            }
                        }
                    }
                } else {
                    freeSpace++;
                }
            }
        }
        System.out.println("isOver pairs: " + pairs);
        System.out.println("isOver free space: " + freeSpace);
        return pairs == 0 && freeSpace == 0;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                toReturn.append("|");
                toReturn.append(getNumber(i, j));
                toReturn.append("|");
            }
            toReturn.append("\n");
        }
        toReturn.append("-----------------------------------------------");
        return toReturn.toString();
    }
}
