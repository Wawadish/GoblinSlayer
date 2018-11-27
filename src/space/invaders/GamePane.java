package space.invaders;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GamePane extends Pane {

    private boolean game = true;
    private Canvas canvas;
    private GraphicsContext gc;
    private Player player;
    private Projectile projectile;
    private double posX = 0;
    private double posY = 0;
    private Executor executor;
    private ArrayList<Enemy> enemies;
    private boolean firstIteration;
    private boolean changeDirection;

    public GamePane() {
        //the player constructor is waaaay to complicated
        player = new Player(new Vector2D(1280 / 2, 720 / 2), new Vector2D(1, 1),
                new Vector2D(0, 0), 60, 140);

        enemies = new ArrayList<>();
        initializePane();
        initializeEnemies();

        //Player moves in relation to the mouse
        setOnMouseMoved(e -> {
            posX = e.getX();
        });
        //fires a projectile when the mouse is clicked
        setOnMouseClicked(e -> {
            projectile = new Projectile(new Vector2D(posX, 720 / 2), new Vector2D(0,-20), 
                    new Vector2D(0,0), 40, 80);
            gc.fillRect(projectile.getX(), projectile.getY(), 60, 140);
        });
        
        //Actions will be executed 60 times per second (60 FPS)
        loop();
    }

    private void drawCanvas() {
        //change direction keeps the enemies aligned.
        checkDirectionChange();
        gc.clearRect(0, 0, 1280, 720);
        updatePlayer();
        updateEnemies();
        if (projectile != null) {
            updateProjectile(projectile);
        }
        changeDirection = false;
    }

    private void updatePlayer() {
        gc.setFill(Color.BLUE);
        gc.fillRect(posX, 700 - 140, player.getWidth(), player.getHeight());
        //gc.drawImage(AssetManager.getSword(0).getImage(), posX, 650-140);
        player.setPosition(new Vector2D(posX, posY));
    }

    private void updateEnemies() {
        if (enemies.size() != 0) {
            gc.setFill(Color.RED);
            for (Enemy e : enemies) {
                double enemyPosX = e.getX();
                double enemyPosY = e.getY();
                if (changeDirection) {
                    e.setVelocity(new Vector2D((-1) * e.getVelocity().getX(), e.getVelocity().getY()));
                }
                e.setX(enemyPosX + e.getVelocity().getX());
                e.setY(enemyPosY + e.getVelocity().getY());
                gc.fillRect(e.getX(), e.getY(), e.getWidth(), e.getHeight());
            }
        }
    }
    
    private void updateProjectile(Projectile projectile) {
        double projectilePosY = projectile.getY();
        projectile.setY(projectilePosY + projectile.getVelocity().getY());
        gc.fillRect(projectile.getX(), projectile.getY(), 60, 140);
    }

    private void checkDirectionChange() {
        for (Enemy e : enemies) {
            if (e.getX() <= 1 || e.getX() >= 1279) {
                changeDirection = true;
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
                        new Vector2D(1, 0), new Vector2D(1, 1), 40, 40));
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

    private void loop() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Game Loop
                long initialTime = System.nanoTime();
                long currentTime;
                long deltaTime;
                while (game) {
                    currentTime = System.nanoTime();
                    deltaTime = (currentTime - initialTime) / 1000000;
                    //Will update every 1/60 seconds (60 frames per second) and 16.6 = 1/60;
                    if (deltaTime >= 16.6) {
                        initialTime = currentTime;
                        firstIteration = true;
                        drawCanvas();
                    }
                }
            }
        });
    }
}
