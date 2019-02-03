package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BoardPanel extends JPanel {
    private static final String TAG = "BoardPanel: ";
    private Matrix matrix;
    private int row;
    private int col;

    public BoardPanel(int row, int col) {
        this.matrix = new Matrix(row, col);
        this.row = row;
        this.col = col;

        setLayout(new BorderLayout());

        JPanel grid = new JPanel(new GridLayout(row, col, 5, 5));
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                JLabel label = new JLabel("", SwingConstants.CENTER);
                label.setPreferredSize(new Dimension(50,50));
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
                    break;
                case KeyEvent.VK_RIGHT:
                    matrix.moveRight();
                    break;
                case KeyEvent.VK_DOWN:
                    matrix.moveDown();
            }
        }

    }
}
