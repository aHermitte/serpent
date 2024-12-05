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
  private int pid;
  private Color color;
  private boolean dead;

  public Snake(int x, int y, int pid, Color color, int snakes_count) {
    body.add(new Drawable(x, y, color));
    for (int i = 0; i < START_SIZE; i++) {
      body.add(new Drawable(x, y, color));
    }
    this.pid = pid;
    this.color = color;
    this.dead = false;
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

  public boolean update(LinkedList<Fruit> fruits, Snake s) {
    if (dead) {
      return true;
    }
    if (!shouldMove()) {
      return false;
    }
    int x = body.getFirst().x;
    int y = body.getFirst().y;

    Direction dir = InputHandler.getDirection(pid);
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

    body.addFirst(new Drawable(x, y, color));

    if (grow > 0) {
      grow--;
    } else {
      body.removeLast();
    }

    dead = checkCollision(x, y, fruits, s);
    return dead;
  }

  private boolean checkCollision(int x, int y, LinkedList<Fruit> fruits, Snake s) {
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

    // Other snakes
    if (s != null && !s.dead) {
      for (Drawable d : s.body) {
        if (d.x == x && d.y == y) {
          System.out.println("Don't eat the other snake!");
          return true;
        }
      }
    }

    return false;
  }

  public void draw(Graphics2D g) {
    if (dead) {
      return;
    }
    for (Drawable d : body) {
      d.draw(g);
    }
  }

}
