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
    DOWN_PRESSED = false;
    LEFT_PRESSED = false;
    RIGHT_PRESSED = false;
    UP_PRESSED = true;
  }

  private void toggleDown() {
    UP_PRESSED = false;
    LEFT_PRESSED = false;
    RIGHT_PRESSED = false;
    DOWN_PRESSED = true;
  }

  private void toggleLeft() {
    UP_PRESSED = false;
    DOWN_PRESSED = false;
    RIGHT_PRESSED = false;
    LEFT_PRESSED = true;
  }

  private void toggleRight() {
    UP_PRESSED = false;
    DOWN_PRESSED = false;
    LEFT_PRESSED = false;
    RIGHT_PRESSED = true;
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

}
