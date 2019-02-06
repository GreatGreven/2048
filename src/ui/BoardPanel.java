package ui;

import game.Controller;
import game.Matrix;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BoardPanel extends JPanel {
    private static final String TAG = "BoardPanel: ";
    private final static Dimension LABEL_DIMENSION = new Dimension(50, 50);
    private final static Border LABEL_BORDER = BorderFactory.createLineBorder(Color.BLACK, 1, true);
    private final static Font LABEL_FONT = new Font("Arial", Font.PLAIN, 20);
    private Controller controller;
    private JPanel grid;
    private JLabel[][] displayLabels;
    private Matrix matrix;
    private int row;
    private int col;

    public BoardPanel(Controller controller, int row, int col) {
        this.controller = controller;
        this.matrix = new Matrix(row, col);
        System.out.println(matrix.toString());
        this.row = row;
        this.col = col;

        setLayout(new BorderLayout(5, 5));
        setOpaque(true);
        setBackground(Color.WHITE);

        grid = new JPanel(new GridLayout(row, col, 5, 5));
        grid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        displayLabels = new JLabel[row][col];
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                displayLabels[i][j] = new JLabel("", SwingConstants.CENTER);
                displayLabels[i][j].setPreferredSize(LABEL_DIMENSION);
                displayLabels[i][j].setBorder(LABEL_BORDER);
                displayLabels[i][j].setFont(LABEL_FONT);
                displayLabels[i][j].setOpaque(true);
                int number = matrix.getNumber(i, j);
                if (number != 0) {
                    displayLabels[i][j].setText("" + number);
                    Color color = matrix.getColor(i, j);
                    displayLabels[i][j].setBackground(color);
                }
                grid.add(displayLabels[i][j]);
            }
        }
        add(grid, BorderLayout.CENTER);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new KeyListener());
    }

    private void updateGrid() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int number = matrix.getNumber(i, j);
                if (number <= 0){
                    displayLabels[i][j].setText("");
                } else {
                    displayLabels[i][j].setText("" + number);
                }
                Color color = matrix.getColor(i, j);
                displayLabels[i][j].setBackground(color);
            }
        }
    }

    private class KeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    matrix.moveLeft();
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
            updateGrid();
            if (matrix.hasWon()){
                controller.displayWinPanel();
            }
            if (matrix.isOver()){
                controller.displayLosePanel();
            }
        }
    }
}
