package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

import main.InputHandler;
import main.InputHandler.Direction;
import main.GameSettings;

public class Snake {
  public LinkedList<Drawable> body = new LinkedList<Drawable>();

  private Direction direction = Direction.RIGHT;
  private double speed = GameSettings.SNAKE_SPEED;
  private long lastMoveTime = System.nanoTime();
  private double moveDelay = 100000000;
  private int grow = 0;

  public Snake(int x, int y) {
    body.add(new Drawable(x, y, Color.BLUE));
    for (int i = 0; i < GameSettings.START_SIZE; i++) {
      body.add(new Drawable(x, y, Color.BLUE));
    }
  }

  private boolean shouldMove() {
    long currentTime = System.nanoTime();
    boolean sm = currentTime - lastMoveTime >= moveDelay / speed;
    if (sm) {
      lastMoveTime = currentTime;
    }
    return sm;
  }

  public void grow() {
    grow += GameSettings.SNAKE_GROWTH;
    if (body.size() % 10 == 0) {
      speed += GameSettings.SNAKE_SPEED_INCREMENT;
    }
  }

  public boolean update(LinkedList<Fruit> fruits) {
    if (!shouldMove()) {
      return false;
    }
    int x = body.getFirst().x;
    int y = body.getFirst().y;

    Direction dir = InputHandler.getDirection();
    if (dir != Direction.NONE) {
      direction = dir;
    }

    switch (direction) {
      case UP:
        y -= Drawable.SIZE;
        break;
      case DOWN:
        y += Drawable.SIZE;
        break;
      case LEFT:
        x -= Drawable.SIZE;
        break;
      case RIGHT:
        x += Drawable.SIZE;
        break;
      case NONE:
        break;

    }

    body.addFirst(new Drawable(x, y, Color.BLUE));

    if (grow > 0) {
      grow--;
    } else {
      body.removeLast();
    }

    return checkCollision(x, y, fruits);
  }

  private boolean checkCollision(int x, int y, LinkedList<Fruit> fruits) {
    // WALLS
    int border_left = GameSettings.TILE_SIZE;
    int border_right = GameSettings.MAP_SIZE * GameSettings.TILE_SIZE;
    if (x < border_left || x > border_right || y < border_left || y > border_right) {
      System.out.println("Stay in the map !");
      return true;
    }

    // FRUITS
    for (Fruit f : fruits) {
      if (f.x == x && f.y == y) {
        f.respawn();
        System.out.println("Fruit eaten !");

        grow();
      }
    }

    // SELF
    for (int i = 1; i < body.size(); i++) {
      if (body.get(i).x == x && body.get(i).y == y) {
        System.out.println("Don't eat yourself!");
        return true;
      }
    }

    return false;
  }

  public void draw(Graphics2D g) {
    for (Drawable d : body) {
      d.draw(g);
    }
  }

}
