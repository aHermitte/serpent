package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.LinkedList;

import snake.Fruit;
import snake.Snake;

public class GameManager {

  private int border_left, border_right;
  private int wall_thickness = 5;

  private int score = 0;
  private int highscore = 0;

  private Snake snake;
  private LinkedList<Fruit> fruits = new LinkedList<Fruit>();

  public GameManager() {
    border_left = GameSettings.TILE_SIZE;
    border_right = GameSettings.MAP_SIZE * GameSettings.TILE_SIZE;

    snake = new Snake( GameSettings.TILE_SIZE * 5 , GameSettings.TILE_SIZE * 5);

    fruits.add(new Fruit( 7 * GameSettings.TILE_SIZE, 7 * GameSettings.TILE_SIZE));

    // Add a fruit on each tile of the map to help map creation
  //  for (int i = 1; i <= GameSettings.MAP_SIZE; i++) {
  //    for (int j = 1; j <= GameSettings.MAP_SIZE; j++) {
  //      fruits.add(new Fruit(i * GameSettings.TILE_SIZE, j * GameSettings.TILE_SIZE));
  //    }
  //  }
  }


  public void update() {
    if (InputHandler.isPaused()) {
      return;
    }
    if (InputHandler.isGameOver()) {
      InputHandler.reset();
      score = 0;
      snake = new Snake( GameSettings.TILE_SIZE * 5 , GameSettings.TILE_SIZE * 5);
      fruits.forEach(fruit -> fruit.respawn());
    }
    boolean isGameOver = snake.update(fruits);
    score = snake.body.size() - 1 - GameSettings.START_SIZE;
    if (score > highscore) {
      highscore = score;
    }
    if (isGameOver) {
      InputHandler.setEndOfGame();

    }
  }

  public void draw(Graphics2D g) {
    fruits.forEach(fruit -> fruit.draw(g));
    g.setColor(Color.WHITE);

    g.setStroke(new BasicStroke(wall_thickness));
    g.drawRect(border_left - 3, border_left - 3, border_right + 7, border_right + 7);

    g.setFont(new Font("Arial", Font.PLAIN, 20));
    g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
    g.drawString("Score: " + score, 20, 50 + border_right);
    g.drawString("Highscore: " + highscore, 20, 75 + border_right);


    snake.draw(g);
    if (InputHandler.isPaused()) {
      drawPauseMenu(g);
      return;
    }
  }

  private void drawPauseMenu(Graphics2D g) {
    g.setColor(Color.BLACK);
    int pause_left = border_left + border_right/8;
    int pause_right = border_right*6/8;
    g.fillRect(pause_left, pause_left, pause_right, pause_right);
    g.setColor(Color.WHITE);
    g.setStroke(new BasicStroke(wall_thickness));
    g.drawRect(pause_left, pause_left, pause_right, pause_right);

    g.setFont(new Font("Arial", Font.PLAIN, 20));
    g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
    if (InputHandler.isGameOver()) {
      g.drawString("Game Over", pause_left + pause_right/2 - 55, pause_left + 50);
      g.drawString("ESC : play", pause_left +pause_right/9, pause_left + pause_right/2);

    } else {
      g.drawString("Paused", pause_left + pause_right/2 - 40, pause_left + 50);
      g.drawString("ESC : resume", pause_left +pause_right/9, pause_left + pause_right/2);
    }
      g.drawString("Q : quit", pause_left +pause_right/9, pause_left + pause_right/2 + 25);
      g.drawString("R : restart", pause_left +pause_right/9, pause_left + pause_right/2 + 50);
  }
}
