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
  private int wall_thickness = 5;

  private int score = 0;
  private int highscore = 0;

  private Snake snake;
  private Snake snake2;
  private LinkedList<Fruit> fruits = new LinkedList<Fruit>();
  boolean is1Dead= true;
  boolean is2Dead= true;

  public GameManager() {
    border_left = SCALE;
    border_right = SCALE * 15;
    border_top = SCALE;
    border_bottom = SCALE * 15;

    snake = new Snake(5 * SCALE, 5 * SCALE, 1, Color.GREEN, 2);
    snake2 = new Snake(5 * SCALE, 10 * SCALE, 2, Color.BLUE, 2);

    fruits.add(new Fruit(10 * SCALE, 7 * SCALE));
  }

  public void update() {
    if (InputHandler.isPaused()) {
      return;
    }

    is1Dead = snake.update(fruits, snake2);
    is2Dead = snake2.update(fruits, snake);
    boolean isGameOver = is1Dead && is2Dead;
    score = snake.body.size() - 1;
    if (score > highscore) {
      highscore = score;
    }
    if (isGameOver) {
      System.out.println("Game Over !");
      score = 0;
      snake = new Snake(5 * SCALE, 5 * SCALE, 1, Color.GREEN, 2);
      snake2 = new Snake(5 * SCALE, 10 * SCALE, 2, Color.BLUE, 2);
      fruits.forEach(fruit -> fruit.respawn());
    }
  }

  public void draw(Graphics2D g) {
    g.setColor(Color.WHITE);

    g.setStroke(new BasicStroke(wall_thickness));
    g.drawRect(border_left - wall_thickness, border_top - wall_thickness, border_right + wall_thickness + 4,
        border_bottom + wall_thickness + 4);

    g.setFont(new Font("Arial", Font.PLAIN, 20));
    g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
    g.drawString("Score: " + score, 20, 20);
    g.drawString("Highscore: " + highscore, 370, 20);

    snake.draw(g);
    snake2.draw(g);
    fruits.forEach(fruit -> fruit.draw(g));

    if (InputHandler.isPaused()) {
      drawPauseMenu(g);
      return;
    }
  }

  private void drawPauseMenu(Graphics2D g) {
    g.setColor(Color.BLACK);
    g.fillRect(border_left - wall_thickness + 50, border_top - wall_thickness + 50, 350 + wall_thickness + 4,
        350 + wall_thickness + 4);
    g.setColor(Color.WHITE);
    g.setStroke(new BasicStroke(wall_thickness));

    g.drawRect(border_left - wall_thickness + 50, border_top - wall_thickness + 50, 350 + wall_thickness + 4,
        350 + wall_thickness + 4);

    g.setFont(new Font("Arial", Font.PLAIN, 30));
    g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
    g.drawString("Paused", 205, 120);
    g.drawString("ESC : resume", 101, 200);
    g.drawString("Q : quit", 140, 250);
    g.drawString("R : start a new game", 140, 300);
  }
}
