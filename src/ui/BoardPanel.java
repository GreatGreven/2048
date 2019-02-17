package ui;

import game.Controller;
import game.Matrix;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class BoardPanel extends JPanel {
    //    private static final String TAG = "BoardPanel: ";
    private final static Dimension LABEL_DIMENSION = new Dimension(50, 50);
    private final static Border LABEL_BORDER = BorderFactory.createLineBorder(Color.BLACK, 1, true);
    private final static Font LABEL_FONT = new Font("Arial", Font.PLAIN, 20);
    private Controller controller;
    private JLabel scoreLabel;
    private JLabel moveLabel;
    private JLabel[][] displayLabels;
    private Matrix matrix;
    private int row;
    private int col;

    public BoardPanel(Controller controller, int row, int col) {
        this.controller = controller;
        this.matrix = new Matrix(row, col);
        this.row = row;
        this.col = col;

        setLayout(new BorderLayout(5, 5));
        setOpaque(true);
        setBackground(Color.WHITE);

        //Game-grid
        JPanel gridPanel = new JPanel(new GridLayout(row, col, 5, 5));
        gridPanel.setOpaque(true);
        gridPanel.setBackground(Color.white);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        displayLabels = new JLabel[row][col];
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                displayLabels[i][j] = new JLabel("", SwingConstants.CENTER);
                displayLabels[i][j].setPreferredSize(LABEL_DIMENSION);
                displayLabels[i][j].setBorder(LABEL_BORDER);
                displayLabels[i][j].setFont(LABEL_FONT);
                displayLabels[i][j].setOpaque(true);
                displayLabels[i][j].setBackground(Color.WHITE);
                int number = matrix.getNumber(i, j);
                if (number != 0) {
                    displayLabels[i][j].setText("" + number);
                    Color color = matrix.getColor(i, j);
                    displayLabels[i][j].setBackground(color);
                }
                gridPanel.add(displayLabels[i][j]);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        //score- and move-board
        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
        panel.setOpaque(true);
        panel.setBackground(Color.WHITE);
        scoreLabel  = new JLabel("Score: " + matrix.getScore(), SwingConstants.CENTER);
        moveLabel= new JLabel("Moves: " + matrix.getMoves(), SwingConstants.CENTER);
        panel.add(scoreLabel);
        panel.add(moveLabel);
        add(panel, BorderLayout.NORTH);

        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new KeyListener());
    }

    private void update() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int number = matrix.getNumber(i, j);
                if (number <= 0) {
                    displayLabels[i][j].setText("");
                } else {
                    displayLabels[i][j].setText("" + number);
                }
                Color color = matrix.getColor(i, j);
                displayLabels[i][j].setBackground(color);
            }
        }
        scoreLabel.setText("Score: " + matrix.getScore());
        moveLabel.setText("Moves: " + matrix.getMoves());
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
                    break;
                case KeyEvent.VK_Q:
                    addActionListener();
            }
            update();
            if (matrix.hasWon()) {
                controller.displayWinPanel();
            }
            if (matrix.isOver()) {
                controller.displayLosePanel();
            }
        }

    }

    private void addActionListener() {
        DebuggingActionListener listener = new DebuggingActionListener();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                displayLabels[i][j].addMouseListener(listener);
            }
        }
    }

    private class DebuggingActionListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (e.getSource() == displayLabels[i][j]) {
                        String[] options = {"0", "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024", "2048"};
                        int inputIndex = Integer.MAX_VALUE;
//                        while (inputIndex != JOptionPane.CLOSED_OPTION)
                            inputIndex = JOptionPane.showOptionDialog(null, "Change value of tile at position [" + j + ":" + i + "]", "GODMODE", JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE, null, options , JOptionPane.DEFAULT_OPTION);
                        matrix.setTile(Integer.parseInt(options[inputIndex]), false, i, j);
//                        if (inputIndex >= 0 || inputIndex < options.length){
//                            break;
//                        }
                    }
                }
            }
        update();
        }
    }
}
