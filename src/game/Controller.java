package game;

import ui.BoardPanel;
import ui.MainPanel;

import javax.swing.*;

public class Controller {
    private static final String TAG = "Controller";
    public static final String TITLE = "2048";
    private JFrame frame;
    private Matrix matrix;
    private JPanel mainPanel;
    private JPanel boardPanel;
    private JPanel winPanel;
    private JPanel losePanel;

    public Controller(){
        initializeMainPanel();
        initializeWinPanel();
        initializeLosePanel();
        displayMainPanel();
    }

    private void initializeMainPanel() {
        mainPanel = new MainPanel(this);
    }

    private void initializeBoardPanel(int difficulty) {
        boardPanel = new BoardPanel(this, difficulty, difficulty);
    }

    private void initializeWinPanel() {

    }

    private void initializeLosePanel() {

    }

    public void displayMainPanel(){
        SwingUtilities.invokeLater(() -> {
            if (frame != null && frame.isVisible()){
                frame.dispose();
            }
            frame = new JFrame(TITLE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.add(mainPanel);
            frame.pack();
        });
    }

    public void displayBoardPanel(int difficulty){
        initializeBoardPanel(difficulty);
        SwingUtilities.invokeLater(() -> {
            if (frame != null && frame.isVisible()){
                frame.dispose();
            }
            frame = new JFrame(TITLE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.add(boardPanel);
            frame.pack();
        });
    }

    public void displayWinPanel(){
        SwingUtilities.invokeLater(() -> {
            if (frame != null && frame.isVisible()){
                frame.dispose();
            }
            frame = new JFrame(TITLE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.add(winPanel);
            frame.pack();
        });
    }

    public void displayLosePanel(){
        SwingUtilities.invokeLater(() -> {
            if (frame != null && frame.isVisible()){
                frame.dispose();
            }
            frame = new JFrame(TITLE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.add(losePanel);
            frame.pack();
        });
    }
}
