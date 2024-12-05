package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.LinkedList;

import snake.Fruit;
import snake.Snake;

public class GameManager {

  public final static int WIDTH = 450;
  static int border_left, border_right, border_top, border_bottom;
  public static final int SCALE = 30;

  private int score = 0;
  private int highscore = 0;

  private Snake snake;
  private LinkedList<Fruit> fruits = new LinkedList<Fruit>();

  public GameManager() {
    border_left = SCALE;
    border_right = SCALE * 15;
    border_top = SCALE;
    border_bottom = SCALE * 15;

    snake = new Snake(5 * SCALE, 7 * SCALE);

    fruits.add(new Fruit(10 * SCALE, 7 * SCALE));
  }

  public void update() {
    if (InputHandler.isPaused()) {
      return;
    }

    boolean isGameOver = snake.update(fruits);
    score = snake.body.size() - 1;
    if (score > highscore) {
      highscore = score;
    }
    if (isGameOver) {
      score = 0;
      snake = new Snake(5 * SCALE, 7 * SCALE);
      fruits.forEach(fruit -> fruit.respawn());
    }
  }

  public void draw(Graphics2D g) {
    g.setColor(Color.WHITE);

    if (InputHandler.isPaused()) {
      g.setFont(new Font("Arial", Font.PLAIN, 50));
      g.drawString("PAUSED", 150, 250);
      return;
    }

    int wall_thickness = 5;
    g.setStroke(new BasicStroke(wall_thickness));
    g.drawRect(border_left - wall_thickness, border_top - wall_thickness, border_right + wall_thickness + 4,
        border_bottom + wall_thickness + 4);

    g.setFont(new Font("Arial", Font.PLAIN, 20));
    g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
    g.drawString("Score: " + score, 20, 20);
    g.drawString("Highscore: " + highscore, 370, 20);

    snake.draw(g);
    fruits.forEach(fruit -> fruit.draw(g));
  }
}
