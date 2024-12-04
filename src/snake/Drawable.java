package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Drawable extends Rectangle {
  int x;
  int y;
  public static final int SIZE = 15;
  public Color c;

  public Drawable(int x, int y, Color c) {
    this.x = x;
    this.y = y;
    this.c = c;
  }

  public void draw(Graphics2D g) {
    g.setColor(c);
    g.fillRect(x, y, SIZE, SIZE);
  }
}
