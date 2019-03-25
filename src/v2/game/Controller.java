package v2.game;

import v2.ui.BoardPanel;
import v2.ui.LosePanel;
import v2.ui.MainPanel;
import v2.ui.WinPanel;

import javax.swing.*;

public class Controller {
    private static final String TAG = "Controller";
    public static final String TITLE = "2048";
    private int difficulty;
    private Matrix matrix;
    private JFrame frame;
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
        this.difficulty = difficulty;
        boardPanel = new BoardPanel(this, difficulty, difficulty);
    }

    private void initializeWinPanel() {
        winPanel = new WinPanel(this);
    }

    private void initializeLosePanel() {
        losePanel = new LosePanel(this);
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
            frame.setLocationRelativeTo(null);
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
            frame.setLocationRelativeTo(null);
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
            frame.setLocationRelativeTo(null);
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
            frame.setLocationRelativeTo(null);
        });
    }

    public int getDifficulty() {
        return difficulty;
    }
}
