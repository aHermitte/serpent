package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

import main.InputHandler;
import main.InputHandler.Direction;

public class Snake {
  public LinkedList<Drawable> body = new LinkedList<Drawable>();

  private Direction direction = Direction.RIGHT;
  private double speed = 0.9;
  private long lastMoveTime = System.nanoTime();
  private double moveDelay = 100000000;
  public final static int START_SIZE = 4;
  private int grow = 0;

  public Snake(int x, int y) {
    body.add(new Drawable(x, y, Color.BLUE));
    for (int i = 0; i < START_SIZE; i++) {
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
    grow += 1;
    if (body.size() % 10 == 0) {
      speed += 0.1;
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
      case UP -> y -= Drawable.SIZE;
      case DOWN -> y += Drawable.SIZE;
      case LEFT -> x -= Drawable.SIZE;
      case RIGHT -> x += Drawable.SIZE;
      case NONE -> {
      }
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
    if (x < 30 || x > 450 || y < 30 || y > 450) {
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
