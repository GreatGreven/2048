package v2.ui;

import v2.game.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LosePanel extends JPanel {
    private static final String TAG = "LosePanel";
    private static final int HGAP = 10;
    private static final int VGAP = 10;
    private Controller controller;
    private JComboBox<Integer> difficultyBox;
    private JButton playButton;
    private JButton backToMenuButton;

    public LosePanel(Controller controller) {
        this.controller = controller;
        setLayout(new BorderLayout(HGAP,VGAP));

        //initialize title
        JLabel titleLabel = new JLabel("Game Over.", SwingConstants.CENTER);
        titleLabel.setFont(new Font("",Font.PLAIN, 36));

        //initialize play button
        ButtonListener listener =new ButtonListener();
        playButton = new JButton("Play again");
        playButton.addActionListener(listener);
        backToMenuButton = new JButton("Back to menu");
        backToMenuButton.addActionListener(listener);

        //add components
        add(titleLabel, BorderLayout.NORTH);
        JPanel gridPanel = new JPanel(new GridLayout(2,1, HGAP, VGAP));
        JPanel panel = new JPanel();
        panel.add(playButton);
        gridPanel.add(panel);
        panel = new JPanel();
        panel.add(backToMenuButton);
        gridPanel.add(panel);
        add(gridPanel, BorderLayout.CENTER);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playButton){
                int difficulty = controller.getDifficulty();
                controller.displayBoardPanel(difficulty);
            } else if (e.getSource() == backToMenuButton){
                controller.displayMainPanel();
            }
        }
    }
}
