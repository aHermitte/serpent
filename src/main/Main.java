package main;

import javax.swing.JFrame;

public class Main extends JFrame {
  public static void main(String[] args) {
    new Main();
  }

  public Main() {
    JFrame frame = new JFrame("Serpent");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 500);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
