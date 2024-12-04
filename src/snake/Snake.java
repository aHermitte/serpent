package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

import main.InputHandler;

public class Snake {
  public LinkedList<Drawable> body = new LinkedList<Drawable>();

  enum Direction {
    UP, DOWN, LEFT, RIGHT, NONE
  }

  private Direction direction = Direction.NONE;
  private double speed = 0.9;
  private long lastMoveTime = System.nanoTime();
  private double moveDelay = 100000000 / speed;
  private int grow = 0;

  public Snake(int x, int y, Color c) {
    body.add(new Drawable(x, y, c));
  }

  private boolean shouldMove() {
    long currentTime = System.nanoTime();
    boolean sm = currentTime - lastMoveTime >= moveDelay;
    if (sm) {
      lastMoveTime = currentTime;
    }
    return sm;
  }

  public void grow() {
    grow += 1;
  }

  public boolean update(LinkedList<Fruit> fruits) {
    if (!shouldMove()) {
      return false;
    }
    int x = body.getFirst().x;
    int y = body.getFirst().y;

    if (InputHandler.UP_PRESSED) {
      direction = Direction.UP;
    } else if (InputHandler.DOWN_PRESSED) {
      direction = Direction.DOWN;
    } else if (InputHandler.LEFT_PRESSED) {
      direction = Direction.LEFT;
    } else if (InputHandler.RIGHT_PRESSED) {
      direction = Direction.RIGHT;
    }

    switch (direction) {
      case UP -> y -= Drawable.SIZE;
      case DOWN -> y += Drawable.SIZE;
      case LEFT -> x -= Drawable.SIZE;
      case RIGHT -> x += Drawable.SIZE;
    }

    body.addFirst(new Drawable(x, y, Color.GREEN));

    if (grow > 0) {
      grow--;
    } else {
      body.removeLast();
    }

    return checkCollision(x, y, fruits);
  }

  private boolean checkCollision(int x, int y, LinkedList<Fruit> fruits) {
    // WALLS
    if (x < 30 || x > 450 || y < 30 || y > 450) {
      System.out.println("Game Over !");
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
        System.out.println("Game Over !");
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
