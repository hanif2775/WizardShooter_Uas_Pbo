package entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import utils.ResourceManager;

public class Projectile extends Rectangle {
    private final BufferedImage sprite;
    private final int speed = 8; 

    public Projectile(int startX, int startY) {
        super(startX, startY, 10, 20); 
        sprite = ResourceManager.loadImage("resource/assets/images/projectile.png");
    }

    public void update() {
        y -= speed; // Bergerak ke atas
    }

    public void draw(Graphics g) {
        g.drawImage(sprite, x, y, width, height, null);
    }
}
