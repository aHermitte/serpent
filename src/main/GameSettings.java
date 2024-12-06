package main;

import java.awt.event.KeyEvent;

public class GameSettings {

  // Game settings
  public static int MAP_SIZE = 20;
  public static int TILE_SIZE = 20;
  public static int FRUIT_COUNT = 1;
  public static int SNAKE_COUNT = 1;
  public static int START_SIZE = 4;
  public static int SNAKE_SPEED = 100;
  public static int SNAKE_SPEED_INCREMENT = 10;
  public static int SNAKE_GROWTH = 1;

  //Key bindings

  // Player 1 movements
  public static int P1_UP = KeyEvent.VK_UP;
  public static int P1_DOWN = KeyEvent.VK_DOWN;
  public static int P1_LEFT = KeyEvent.VK_LEFT;
  public static int P1_RIGHT = KeyEvent.VK_RIGHT;

  // Player 2 movements
  public static int P2_UP = KeyEvent.VK_W;
  public static int P2_DOWN = KeyEvent.VK_S;
  public static int P2_LEFT = KeyEvent.VK_A;
  public static int P2_RIGHT = KeyEvent.VK_D;

  // Game control
  public static int[] PAUSE_KEYS = {KeyEvent.VK_ESCAPE, KeyEvent.VK_P, KeyEvent.VK_SPACE, KeyEvent.VK_ENTER};

  // Change key bindings

  // Direction: 0 = UP, 1 = DOWN, 2 = LEFT, 3 = RIGHT
  public static int DIR_UP = 0;
  public static int DIR_DOWN = 1;
  public static int DIR_LEFT = 2;
  public static int DIR_RIGHT = 3;
  
  public static void changeMovementKey(int player, int direction, int key) {
    switch (player) {
      case 1:
        switch (direction) {
          case 0:
            P1_UP = key;
            break;
          case 1:
            P1_DOWN = key;
            break;
          case 2:
            P1_LEFT = key;
            break;
          case 3:
            P1_RIGHT = key;
            break;
        }
        break;
      case 2:
        switch (direction) {
          case 0:
            P2_UP = key;
            break;
          case 1:
            P2_DOWN = key;
            break;
          case 2:
            P2_LEFT = key;
            break;
          case 3:
            P2_RIGHT = key;
            break;
        }
        break;
    }
  }
}
