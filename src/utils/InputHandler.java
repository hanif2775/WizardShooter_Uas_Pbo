package utils;

import entities.Player;
import entities.Projectile;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InputHandler extends KeyAdapter implements MouseListener {
    private final Player player;
    private final ArrayList<Projectile> projectiles;
    private boolean moveUp, moveDown, moveLeft, moveRight;

    // Variabel untuk kontrol penembakan
    private Timer shootTimer;

    public InputHandler(Player player, ArrayList<Projectile> projectiles) {
        this.player = player;
        this.projectiles = projectiles;

        // Timer untuk menembak terus-menerus
        shootTimer = new Timer(100, e -> {
            projectiles.add(new Projectile(player.x + player.width / 2 - 5, player.y));
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> moveUp = true;
            case KeyEvent.VK_S -> moveDown = true;
            case KeyEvent.VK_A -> moveLeft = true;
            case KeyEvent.VK_D -> moveRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> moveUp = false;
            case KeyEvent.VK_S -> moveDown = false;
            case KeyEvent.VK_A -> moveLeft = false;
            case KeyEvent.VK_D -> moveRight = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Mulai menembak terus-menerus saat mouse ditekan
        if (!shootTimer.isRunning()) {
            shootTimer.start();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Hentikan penembakan saat mouse dilepas
        shootTimer.stop();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
     
    }

    public void updatePlayerMovement() {
        if (moveUp) player.y -= 5;
        if (moveDown) player.y += 5;
        if (moveLeft) player.x -= 5;
        if (moveRight) player.x += 5;
    }
}
