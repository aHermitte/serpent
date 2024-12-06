package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class InputHandler implements KeyListener {
  public enum Direction {
    UP, DOWN, LEFT, RIGHT, NONE
  }

  private static LinkedList<Direction> directions = new LinkedList<Direction>();
  private static Direction lastDirection = Direction.NONE;
  private static boolean paused = false;
  private static boolean gameOver = false;

  public InputHandler() {
    System.out.println("InputHandler initialized !");
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    switch (key) {
      case KeyEvent.VK_UP:
        addDirection(Direction.UP);
        break;
      case KeyEvent.VK_DOWN:
        addDirection(Direction.DOWN);
        break;
      case KeyEvent.VK_LEFT:
        addDirection(Direction.LEFT);
        break;
      case KeyEvent.VK_RIGHT:
        addDirection(Direction.RIGHT);
        break;
      case KeyEvent.VK_ESCAPE:
        paused = !paused;
        break;
      case KeyEvent.VK_P:
        paused = !paused;
        break;
      case KeyEvent.VK_SPACE:
        paused = !paused;
        break;
      case KeyEvent.VK_ENTER:
        paused = !paused;
        break;
      case KeyEvent.VK_R:
        if (paused) {
          paused = false;
          directions.add(Direction.RIGHT);
          directions.add(Direction.LEFT);
          lastDirection = Direction.RIGHT;
        }
        break;
      case KeyEvent.VK_Q:
        if (paused) {
          System.exit(0);
        }
        addDirection(Direction.LEFT);
        break;
      case KeyEvent.VK_D:
        addDirection(Direction.RIGHT);
        break;
      case KeyEvent.VK_Z:
        addDirection(Direction.UP);
        break;
      case KeyEvent.VK_S:
        addDirection(Direction.DOWN);
        break;
      default:
        break;
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

  public static boolean isPaused() {
    return paused;
  }

  public static void reset() {
    gameOver = false;
    directions.clear();
    lastDirection = Direction.NONE;
  }

  public static void setEndOfGame() {
    paused = true;
    gameOver = true;
  }

  public static boolean isGameOver() {
    return gameOver;
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

}
