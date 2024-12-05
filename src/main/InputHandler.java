package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class InputHandler implements KeyListener {
  public enum Direction {
    UP, DOWN, LEFT, RIGHT, NONE
  }
  private static  LinkedList<Direction> directions = new LinkedList<Direction>();
  private Direction lastDirection = Direction.NONE;
  private static boolean paused = false;

  public InputHandler() {
    System.out.println("InputHandler initialized !");
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    switch (key) {
      case KeyEvent.VK_UP -> addDirection(Direction.UP);
      case KeyEvent.VK_DOWN -> addDirection(Direction.DOWN);
      case KeyEvent.VK_LEFT -> addDirection(Direction.LEFT);
      case KeyEvent.VK_RIGHT -> addDirection(Direction.RIGHT);
      case KeyEvent.VK_ESCAPE -> paused = !paused;
      case KeyEvent.VK_R -> {
        if (paused) {
          paused = false;
          directions.add(Direction.RIGHT);
          directions.add(Direction.LEFT);
        }
      }
      case KeyEvent.VK_Q -> {
        if (paused) {
          System.exit(0);
        }
      }

    }
  }

  private void addDirection(Direction d) {
    if (paused) {
      return;
    }
    if (d == Direction.UP && lastDirection == Direction.DOWN) {
      return;
    }
    if (d == Direction.DOWN && lastDirection == Direction.UP) {
      return;
    }
    if (d == Direction.LEFT && lastDirection == Direction.RIGHT) {
      return;
    }
    if (d == Direction.RIGHT && lastDirection == Direction.LEFT) {
      return;
    }
    lastDirection = d;
    directions.add(d);
  }

  public static Direction getDirection() {
    if (directions.isEmpty()) {
      return Direction.NONE;
    }
    return directions.removeFirst();
  }

  public static  boolean isPaused() {
    return paused;
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

}
