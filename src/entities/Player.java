package entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import utils.ResourceManager;

public class Player extends Rectangle {
    private BufferedImage sprite;

    public Player(int x, int y) {
        super(x, y, 50, 50);
        sprite = ResourceManager.loadImage("resource/assets/images/player.png");
    }

    public void update() {
        if (x < 0) x = 0;
        if (x + width > 800) x = 800 - width;
        if (y < 0) y = 0;
        if (y + height > 600) y = 600 - height;
    }

    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, width, height, null);
    }
}
