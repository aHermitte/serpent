package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class InputHandler implements KeyListener {
  public enum Direction {
    UP, DOWN, LEFT, RIGHT, NONE
  }

  private static LinkedList<Direction> directions1 = new LinkedList<Direction>();
  private static LinkedList<Direction> directions2 = new LinkedList<Direction>();
  private Direction lastDirection1 = Direction.NONE;
  private Direction lastDirection2 = Direction.NONE;

  private static boolean paused = false;

  public InputHandler() {
    System.out.println("InputHandler initialized !");
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    switch (key) {
      case KeyEvent.VK_UP -> addDirection(Direction.UP, 1);
      case KeyEvent.VK_DOWN -> addDirection(Direction.DOWN, 1);
      case KeyEvent.VK_LEFT -> addDirection(Direction.LEFT, 1);
      case KeyEvent.VK_RIGHT -> addDirection(Direction.RIGHT, 1);
      case KeyEvent.VK_ESCAPE -> paused = !paused;
      case KeyEvent.VK_R -> {
        if (paused) {
          paused = false;
          directions1.add(Direction.RIGHT);
          directions1.add(Direction.LEFT);
          lastDirection1 = Direction.RIGHT;
          directions2.add(Direction.RIGHT);
        }
      }
      case KeyEvent.VK_Q -> {
        if (paused) {
          System.exit(0);
        }
        addDirection(Direction.LEFT, 2);
      }
      case KeyEvent.VK_D -> addDirection(Direction.RIGHT, 2);
      case KeyEvent.VK_Z -> addDirection(Direction.UP, 2);
      case KeyEvent.VK_S -> addDirection(Direction.DOWN, 2);
    }
  }

  private void addDirection(Direction d, int player) {

    if (paused) {
      return;
    }
    Direction ld = player == 1 ? lastDirection1 : lastDirection2;
    if (d == Direction.UP && ld == Direction.DOWN) {
      return;
    }
    if (d == Direction.DOWN && ld == Direction.UP) {
      return;
    }
    if (d == Direction.LEFT && ld == Direction.RIGHT) {
      return;
    }
    if (d == Direction.RIGHT && ld == Direction.LEFT) {
      return;
    }
    if (player == 1) {
      lastDirection1 = d;
      directions1.add(d);
    } else if (player == 2) {
      lastDirection2 = d; 
      directions2.add(d);
    }
  }

  public static Direction getDirection(int player) {
    switch (player) {
      case 1 -> {
        if (directions1.isEmpty()) {
          return Direction.NONE;
        }
        return directions1.removeFirst();
      }
      case 2 -> {
        if (directions2.isEmpty()) {
          return Direction.NONE;
        }
        return directions2.removeFirst();
      }
    }
    return Direction.NONE;
  }

  public static boolean isPaused() {
    return paused;
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

}
