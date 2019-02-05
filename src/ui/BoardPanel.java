package ui;

import game.Matrix;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BoardPanel extends JPanel {
    private static final String TAG = "BoardPanel: ";
    private final static Dimension SQUARE_DIMENSION = new Dimension(50,50);
    private Matrix matrix;
    private int row;
    private int col;

    public BoardPanel(int row, int col) {
        this.matrix = new Matrix(row, col);
        System.out.println(matrix.toString());
        this.row = row;
        this.col = col;

        setLayout(new BorderLayout());

        JPanel grid = new JPanel(new GridLayout(row, col, 5, 5));
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                JLabel label = new JLabel("0", SwingConstants.CENTER);
                label.setPreferredSize(SQUARE_DIMENSION);
                int number = + matrix.getNumber(i,j);
                if (number != 0) {
                    label.setText("" + number);
                    int color = matrix.getColor(i, j);
                    label.setBackground(new Color(color));
                }
                grid.add(label);
            }
        }
        add(grid, BorderLayout.CENTER);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new KeyListener());
    }

    private class KeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println(TAG + "released " + e.getKeyCode());
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    matrix.moveLeft();
                    System.out.println(matrix.toString());

                    break;
                case KeyEvent.VK_UP:
                    matrix.moveUp();
                    System.out.println(matrix.toString());
                    break;
                case KeyEvent.VK_RIGHT:
                    matrix.moveRight();
                    System.out.println(matrix.toString());
                    break;
                case KeyEvent.VK_DOWN:
                    matrix.moveDown();
                    System.out.println(matrix.toString());
            }
        }

    }
}
