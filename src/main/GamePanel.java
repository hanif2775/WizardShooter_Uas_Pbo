package main;

import entities.*;
import utils.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class GamePanel extends JPanel implements ActionListener {
    private Timer timer;
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> projectiles;
    private InputHandler inputHandler;

    private int score; 
    private int currentWave; 
    private final int totalWave = 100; 
    private final int enemiesPerWave = 10; 
    private final int winningScore = 1000; 

    public GamePanel() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        initGame();

        timer = new Timer(16, this); 
        timer.start();
    }

    private void initGame() {
        player = new Player(400, 500);
        enemies = new ArrayList<>();
        projectiles = new ArrayList<>();
        inputHandler = new InputHandler(player, projectiles);
        addKeyListener(inputHandler);
        addMouseListener(inputHandler);

        score = 0;
        currentWave = 1; 
        spawnWaveEnemies();
    }

    private void spawnWaveEnemies() {
        
        for (int i = 0; i < enemiesPerWave; i++) {
            enemies.add(new Enemy((int) (Math.random() * 750), (int) (Math.random() * -200))); // Spawn musuh di lokasi acak di atas layar
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        
        player.draw(g);
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
        for (Projectile projectile : projectiles) {
            projectile.draw(g);
        }

        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 20);

        
        g.drawString("Wave: " + currentWave + "/" + totalWave, 700, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }

    private void updateGame() {
        inputHandler.updatePlayerMovement();
        player.update();

        for (Enemy enemy : enemies) {
            enemy.update();
        }

        for (Projectile projectile : projectiles) {
            projectile.update();
        }

        
        projectiles.removeIf(projectile -> projectile.y + projectile.height < 0);

        checkCollisions();
        checkWaveProgress(); 
        checkWinCondition(); 
    }

    private void checkCollisions() {
        
        Iterator<Projectile> projectileIterator = projectiles.iterator();
        while (projectileIterator.hasNext()) {
            Projectile projectile = projectileIterator.next();
            Iterator<Enemy> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                if (projectile.intersects(enemy)) {
                    enemyIterator.remove(); 
                    projectileIterator.remove(); 
                    score += 20;
                    break;
                }
            }
        }

        
        for (Enemy enemy : enemies) {
            if (enemy.intersects(player)) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "Game Over!");
                System.exit(0);
            }
        }
    }

    private void checkWaveProgress() {
        if (enemies.isEmpty() && currentWave <= totalWave) {
            if (currentWave < totalWave) {
                currentWave++;
                spawnWaveEnemies(); 
            }
        }
    }

    private void checkWinCondition() {
        if (score >= winningScore) {
            timer.stop();
            int option = JOptionPane.showOptionDialog(
                    this,
                    "Congratulations! You scored " + score + " points!\nYou are the WINNER!",
                    "WINNER",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"Retry", "Exit"},
                    "Retry"
            );

            if (option == JOptionPane.YES_OPTION) {
                restartGame(); 
            } else {
                System.exit(0); 
            }
        }
    }

    private void restartGame() {
        
        initGame();
        timer.start();
    }
}
