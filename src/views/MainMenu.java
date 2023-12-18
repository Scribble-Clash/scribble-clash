package views;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MainMenu extends JPanel {

    public MainMenu(GameWindow gameWindow) {
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton roomButton = createStyledButton("");
        JButton playDemoButton = createStyledButton("");
        JButton exitButton = createStyledButton("");

        roomButton.setPreferredSize(new Dimension(350, 350));
        roomButton.setOpaque(false);

        playDemoButton.setPreferredSize(new Dimension(300, 300));
        playDemoButton.setOpaque(false);


        exitButton.setPreferredSize(new Dimension(350, 350));
        exitButton.setOpaque(false);

        ImageIcon playButton = new ImageIcon("src/assets/mainmenu/play.png");
        Image image = playButton.getImage();
        Image newimg = image.getScaledInstance(roomButton.getPreferredSize().width, roomButton.getPreferredSize().height,  java.awt.Image.SCALE_SMOOTH);
        playButton = new ImageIcon(newimg);

        ImageIcon playDemoButtonIcon = new ImageIcon("src/assets/mainmenu/playdemo.png");
        image = playDemoButtonIcon.getImage();
        newimg = image.getScaledInstance(playDemoButton.getPreferredSize().width, playDemoButton.getPreferredSize().height,  java.awt.Image.SCALE_SMOOTH);
        playDemoButtonIcon = new ImageIcon(newimg);

        ImageIcon exitButtonIcon = new ImageIcon("src/assets/mainmenu/exit.png");
        image = exitButtonIcon.getImage();
        newimg = image.getScaledInstance(exitButton.getPreferredSize().width, exitButton.getPreferredSize().height,  java.awt.Image.SCALE_SMOOTH);
        exitButtonIcon = new ImageIcon(newimg);






        roomButton.setIcon(playButton);
        playDemoButton.setIcon(playDemoButtonIcon);
        exitButton.setIcon(exitButtonIcon);
        roomButton.addActionListener(e -> gameWindow.showJoinRoomPanel());
        playDemoButton.addActionListener(e -> gameWindow.showGamePanel(true));
        exitButton.addActionListener(e -> System.exit(0));

        roomButton.setBounds(775, 400, roomButton.getPreferredSize().width, roomButton.getPreferredSize().height);
        playDemoButton.setBounds(425, 600, playDemoButton.getPreferredSize().width, playDemoButton.getPreferredSize().height);
        exitButton.setBounds(1125, 600, exitButton.getPreferredSize().width, exitButton.getPreferredSize().height);

        add(roomButton);
        add(playDemoButton);
        add(exitButton);
//        buttonPanel.add(roomButton);
////        gbc.gridy++;
//        buttonPanel.add(playDemoButton);
////        gbc.gridy++;
//        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setPreferredSize(new Dimension(100, 30));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.LIGHT_GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
            }
        });

        return button;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage image=null;

        try {
            image = ImageIO.read(new File("src/assets/mainmenu/title3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image != null){
            g2d.drawImage(image, 725, 0, 450, 450, null);
        }

    }
}
