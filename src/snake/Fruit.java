package snake;

import java.awt.Color;

import main.GameSettings;
public class Fruit extends Drawable {
  public Fruit(int x, int y) {
    super(x, y, Color.RED);
  }

  public void respawn() {
    x = (int) (Math.random() * GameSettings.MAP_SIZE) * SIZE;
    y = (int) (Math.random() * GameSettings.MAP_SIZE) * SIZE;
    if (x == 0) {
      x = SIZE;
    }

    if (y == 0) {
      y = SIZE;
    }
  }

}
