package snake;

import java.awt.Color;


public class Fruit extends Drawable {
  public Fruit(int x, int y) {
    super(x, y, Color.RED);
  }

  public void respawn() {
    x = (int) (Math.random() * 15) * SIZE;
    y = (int) (Math.random() * 15) * SIZE;
    if (x == 0) {
      x = SIZE;
    }

    if (y == 0) {
      y = SIZE;
    }
  }

}
