package entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import utils.ResourceManager;

public class Enemy extends Rectangle {
    private final BufferedImage sprite;

    public Enemy(int x, int y) {
        super(x, y, 50, 50);
        sprite = ResourceManager.loadImage("resource/assets/images/enemy.png");
    }

    public void update() {
        y += 2; // Musuh bergerak ke bawah
        if (y > 600) {
            y = 0;
            x = (int) (Math.random() * 750);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, width, height, null);
    }
}
