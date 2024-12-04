package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

import main.InputHandler;

public class Snake {
  public LinkedList<Drawable> body = new LinkedList<Drawable>();

  enum Direction {
    UP, DOWN, LEFT, RIGHT
  }

  private Direction direction = Direction.RIGHT;
  private double speed = 1;
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

  public void update() {
    if (!shouldMove()) {
      return;
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
  }

  public void draw(Graphics2D g) {
    for (Drawable d : body) {
      d.draw(g);
    }
  }

}
