package main;

import javax.swing.JFrame;

public class Main extends JFrame {
  public static void main(String[] args) {
    new Main();
  }

  public Main() {
    JFrame frame = new JFrame("");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setFocusable(true);

    frame.addKeyListener(new InputHandler());
    frame.setVisible(true);

    GamePanel gamePanel = new GamePanel();
    frame.add(gamePanel);
    frame.pack();

    gamePanel.startGame();
  }
}
