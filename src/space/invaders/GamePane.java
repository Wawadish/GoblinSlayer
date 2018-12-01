package space.invaders;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class GamePane extends Pane {

    private boolean game = true;
    private Canvas canvas;
    private GraphicsContext gc;
    private Player player;
    private double posX = 0;
    final private double posY = 700 - 140;
    private Executor executor;
    private ArrayList<Enemy> enemies;
    private boolean firstIteration;
    private boolean changeDirection;
    private boolean paused = false;
    private ArrayList<Projectile> playerProjectiles = new ArrayList<>();
    private ArrayList<Projectile> projectileRemove = new ArrayList<>();
    private ArrayList<Projectile> enemyProjectiles = new ArrayList<>();
    private Image currentPlayerImage;

    //maximum amount of player projectiles at the same time, 
    //change for a "skill"
    private int allowedPlayerProjectiles = 1;

    public GamePane() {
        //Player moves in relation to the mouse
        setOnMouseMoved(e -> {
            posX = e.getX();
        });
        //Fixed It
        player = new Player(new Vector2D(posX, posY), 60, 140);

        enemies = new ArrayList<>();
        initializePane();
        initializeEnemies();

        //Pause game
        setOnKeyPressed(e -> {

        });
        

        //fires a projectile when the mouse is clicked
        setOnMouseClicked(e -> {
            if (playerProjectiles.size() < allowedPlayerProjectiles) {
                playerProjectiles.add(new Projectile(new Vector2D(posX
                        + player.getWidth() * 0.25, player.getY()
                        + player.getHeight()), new Vector2D(0, -20),
                        new Vector2D(0, 0), player.getWidth() * 0.50,
                        player.getHeight() * 0.50));

                AssetManager.getAudio(2).play();
            }
        });

        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.P) {
                paused = !paused;
                if (paused) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(0, 0, 1280, 720);
                    gc.setFill(Color.WHITE);
                    gc.setFont(new Font("Arial", 48));
                    gc.setTextAlign(TextAlignment.CENTER);
                    gc.fillText("Paused", 1280 / 2, 720 / 2);
                }

            }
        });
        //Actions will be executed 60 times per second (60 FPS)
        loop();
    }

    private void drawCanvas() {
        //change direction keeps the enemies aligned.
        checkDirectionChange();
        gc.clearRect(0, 0, 1280, 720);
        addEnemyProjectiles();
        updateEnemyProjectiles();
        updatePlayer();
        updateEnemies();
        updatePlayerProjectiles();

        changeDirection = false;
    }

    private void moveEnemiesDown() {
        enemies.forEach((e) -> {
            e.setY(e.getY() + 10);
        });
    }

    private void updatePlayerProjectiles() {
        if (!playerProjectiles.isEmpty()) {
            currentPlayerImage = AssetManager.getSword(1);
            for (Projectile projectile : playerProjectiles) {
                if (projectile.getY() + projectile.getHeight() <= 1) {
                    projectileRemove.add(projectile);
                } else {
                    updateProjectile(projectile);
                }
            }
        } else {
            currentPlayerImage = AssetManager.getSword(0);
        }
        playerProjectiles.removeAll(projectileRemove);
        projectileRemove.clear();
    }

    private void addEnemyProjectiles() {
        for (Enemy e : enemies) {
            int random = (int) (Math.random() * 750 + 1);
            if (random == 5) {
                enemyProjectiles.add(new Projectile(new Vector2D(e.getX(), e.getY()),
                        new Vector2D(0, 4), new Vector2D(0, 0), 30, 30));
            }
        }

    }

    private void updateEnemyProjectiles() {
        if (!enemyProjectiles.isEmpty()) {
            for (Projectile projectile : enemyProjectiles) {
                if (projectile.getY() + projectile.getHeight() >= 719) {
                    projectileRemove.add(projectile);
                } else {
                    updateProjectile(projectile);
                }
            }
        }
        enemyProjectiles.removeAll(projectileRemove);
        projectileRemove.clear();
    }

    private void updatePlayer() {
        gc.drawImage(currentPlayerImage, posX, posY, player.getWidth(),
                player.getHeight());
        player.setPosition(new Vector2D(posX, posY));
    }

    private void updateEnemies() {
        if (enemies.size() != 0) {
            gc.setFill(Color.RED);
            for (Enemy e : enemies) {
                double enemyPosX = e.getX();
                double enemyPosY = e.getY();
                if (changeDirection) {
                    e.setVelocity(new Vector2D((-1) * e.getVelocity().getX(),
                            e.getVelocity().getY()));
                }
                e.setPosition(new Vector2D(enemyPosX + e.getVelocity().getX(),
                        e.getY()));
                gc.fillRect(e.getX(), e.getY(), e.getWidth(), e.getHeight());
            }
        } else {
            AssetManager.stopAllSound();
            AssetManager.getAudio(4).play();
            game = false;
            displayMessage("Victory!!");
        }
    }

    private void updateProjectile(Projectile projectile) {
        double projectilePosY = projectile.getY();
        projectile.setY(projectilePosY + projectile.getVelocity().getY());
        //50% of player height and width;
        gc.drawImage(AssetManager.getSword(0), projectile.getX(),
                projectile.getY(), projectile.getWidth(),
                projectile.getHeight());
    }

    private void checkDirectionChange() {
        for (Enemy e : enemies) {
            if (e.getX() <= 1 || e.getX() + e.getWidth() >= 1279) {
                changeDirection = true;
                moveEnemiesDown();
            }
        }
    }

    private void initializeEnemies() {
        double enemyPosX = 0;
        double enemyPosY = 0;
        for (int i = 0; i < 4; i++) {
            enemyPosX = 0;
            enemyPosY += 60;
            for (int j = 0; j < 8; j++) {
                enemyPosX += 120;
                enemies.add(new Enemy(new Vector2D(enemyPosX, enemyPosY),
                        new Vector2D(1, 0), 40, 40));
            }
        }
    }

    private void initializePane() {
        executor = Executors.newSingleThreadExecutor();
        canvas = new Canvas(1280, 720);
        gc = canvas.getGraphicsContext2D();
        getChildren().add(canvas);
        AssetManager.stopAllSound();
        AssetManager.getAudio(1).play();
        setBackground(AssetManager.getBackground(1));
    }

    //TODO make it iterate for every projectile if powerup
    public void playerProjectileCollison() {
        if (!playerProjectiles.isEmpty()) {
            for (Enemy e : enemies) {
                if (playerProjectiles.get(0).collides(e)) {
                    enemies.remove(e);
                    projectileRemove.add(playerProjectiles.get(0));
                    playerProjectiles.remove(playerProjectiles.get(0));
                    AssetManager.getAudio(3).play();
                    break;
                }
            }
        }
    }

    //Not perfect but working on it
    public void enemyPlayerCollison() {
        if (!enemies.isEmpty()) {
            for (Enemy e : enemies) {
                if (player.collides(e)) {
                    AssetManager.stopAllSound();
                    displayMessage("Game Over!");
                    AssetManager.getAudio(5).play();
                    game = false;
                }
            }
        }
    }

    private void displayMessage(String message) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 1280, 720);
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font("Arial", 48));
        gc.fillText(message, 1280 / 2, 720 / 2);
        gc.setFont(new Font("Arial", 24));
        gc.fillText("Press \"ENTER\" to return to the menu!",
                1280 / 2, 720 / 2 + 100);

        this.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                GoblinSlayer.changePane(new MenuPane());
            }
        });
    }

    private void loop() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Game Loop
                long initialTime = System.nanoTime();
                long currentTime;
                long deltaTime;
                while (game) {
                    if (!paused) {
                        currentTime = System.nanoTime();
                        deltaTime = (currentTime - initialTime) / 1000000;
                        //Will update every 1/60 seconds (60 frames per second) 
                        //and 16.6 = 1/60;
                        if (deltaTime >= 16.6) {
                            initialTime = currentTime;
                            firstIteration = true;
                            drawCanvas();
                            playerProjectileCollison();
                            enemyPlayerCollison();
                        }
                    }
                }
            }
        });
    }
}
