package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
  public static boolean UP_PRESSED, DOWN_PRESSED, LEFT_PRESSED, RIGHT_PRESSED = false;

  public InputHandler() {
    UP_PRESSED = false;
    DOWN_PRESSED = false;
    LEFT_PRESSED = false;
    RIGHT_PRESSED = false;

    System.out.println("InputHandler initialized !");
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    switch (key) {
      case KeyEvent.VK_UP -> toggleUp();
      case KeyEvent.VK_DOWN -> toggleDown();
      case KeyEvent.VK_LEFT -> toggleLeft();
      case KeyEvent.VK_RIGHT -> toggleRight();
    }
  }

  private void toggleUp() {
    if (DOWN_PRESSED) {
      return;
    }
    reset();
    UP_PRESSED = true;
  }

  private void toggleDown() {
    if (UP_PRESSED) {
      return;
    }
    reset();
    DOWN_PRESSED = true;
  }

  private void toggleLeft() {
    if (RIGHT_PRESSED) {
      return;
    }
    reset();
    LEFT_PRESSED = true;
  }

  private void toggleRight() {
    if (LEFT_PRESSED) {
      return;
    }
    reset();
    RIGHT_PRESSED = true;
  }

  public static void reset() {
    UP_PRESSED = false;
    DOWN_PRESSED = false;
    LEFT_PRESSED = false;
    RIGHT_PRESSED = false;
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

}
