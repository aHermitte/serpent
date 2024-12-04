package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.LinkedList;

import snake.Fruit;
import snake.Snake;

public class GameManager {

  final int WIDTH = 450;
  final int HEIGHT = 450;
  static int border_left, border_right, border_top, border_bottom;

  private int score = 0;
  private int highscore = 0;

  private Snake snake;
  private LinkedList<Fruit> fruits = new LinkedList<Fruit>();

  public GameManager() {
    border_left = (GamePanel.WIDTH / 2) - (WIDTH / 2);
    border_right = (GamePanel.WIDTH / 2) + (WIDTH / 2);
    border_top = (GamePanel.HEIGHT / 2) - (HEIGHT / 2);
    border_bottom = (GamePanel.HEIGHT / 2) + (HEIGHT / 2);

    snake = new Snake(225, 225, Color.GREEN);
    Fruit fruit = new Fruit(225, 225);
    fruits.add(fruit);
  }

  public void update() {
    snake.update();
    fruits.forEach(fruit -> {
      if (snake.body.getFirst().intersects(fruit)) {
        score++;
        if (score > highscore) {
          highscore = score;
        }
        snake.grow();
        fruits.remove(fruit);
        fruits.add(new Fruit((int) (Math.random() * WIDTH) + border_left, (int) (Math.random() * HEIGHT) + border_top));
      }
    });
  }

  public void draw(Graphics2D g) {
    g.setColor(Color.WHITE);

    int wall_thickness = 5;
    g.setStroke(new BasicStroke(wall_thickness));
    g.drawRect(border_left - wall_thickness, border_top - wall_thickness, WIDTH + 2 * wall_thickness, HEIGHT + 2 * wall_thickness);

    g.setFont(new Font("Arial", Font.PLAIN, 20));
    g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
    g.drawString("Score: " + score, 20, 20);
    g.drawString("Highscore: " + highscore, 370, 20);

    snake.draw(g);
    fruits.forEach(fruit -> fruit.draw(g));
  }
}
