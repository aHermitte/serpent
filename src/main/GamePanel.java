package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

  static final int WIDTH = 500;
  static final int HEIGHT = 500;
public static Object g;
  private final int FPS = 200;

  private Thread gameThread;
  private GameManager gameManager;

  public GamePanel() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setBackground(Color.BLACK);
    setLayout(null);

    gameManager = new GameManager();
  }

  public void startGame() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run() {
    System.out.println("Running game loop !");
    while (gameThread != null) {
      long lastTime = System.nanoTime();
      long currentTime;

      double nsPerTick = 1000000000.0 / FPS;
      double delta = 0;

      while (true) {
        currentTime = System.nanoTime();
        delta += (currentTime - lastTime) / nsPerTick;
        lastTime = currentTime;
        if (delta >= 1) {
          update();
          repaint();
          delta--;
        }
      }
    }
  }

  private void update() {
    gameManager.update();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    gameManager.draw(g2d);
  }

}
