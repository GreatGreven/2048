package v1.ui;

import v1.game.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel{
    private static final String TAG = "MainPanel";
    private static final int HGAP = 10;
    private static final int VGAP = 10;
    private Controller controller;
    private JComboBox<Integer> difficultyBox;
    private JButton playButton;

    public MainPanel(Controller controller) {
        this.controller = controller;
        setLayout(new BorderLayout(HGAP,VGAP));

        //initialize title
        JLabel titleLabel = new JLabel(Controller.TITLE, SwingConstants.CENTER);
        titleLabel.setFont(new Font("",Font.PLAIN, 36));

        //initialize play button
        playButton = new JButton("Play");
        playButton.addActionListener(new ButtonListener());

        //initialize difficulty picker
        Integer [] difficulties = {3, 4, 5, 6, 7, 8, 9, 10};
        difficultyBox = new JComboBox<Integer>(difficulties);
        difficultyBox.setPreferredSize(new Dimension(this.getWidth() - playButton.getWidth(), playButton.getHeight()));
        difficultyBox.setSelectedIndex(1);

        //add components
        add(titleLabel, BorderLayout.NORTH);
        JPanel gridPanel = new JPanel(new GridLayout(1,2, HGAP, VGAP));
        JPanel panel = new JPanel();
        gridPanel.add(difficultyBox);
        panel.add(playButton);
        gridPanel.add(panel);
        add(gridPanel, BorderLayout.CENTER);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playButton){
                int difficulty = (int) difficultyBox.getSelectedItem();
                controller.displayBoardPanel(difficulty);
            }
        }
    }
}
