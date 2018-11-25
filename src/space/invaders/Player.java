package space.invaders;

import javafx.scene.paint.ImagePattern;

public class Player extends GameObject {

    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;

    private static ImagePattern swordState;
    private static boolean swordUsable;

    public Player(Vector2D position, Vector2D velocity, Vector2D acceleration, double width, double height) {
        super(position.getX(), position.getY(), width, height);
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        swordState = AssetManager.getSword(0);
        swordUsable = true;
    }

    public static void changeSword() {
        if (swordUsable) {
            swordState = AssetManager.getSword(1);
        } else {
            swordState = AssetManager.getSword(0);
        }
        swordUsable = !swordUsable;
    }

    public static boolean getSwordUsable() {
        return swordUsable;
    }

    public static ImagePattern getSwordState() {
        return swordState;
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public Vector2D getAcceleration() {
        return acceleration;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public void setAcceleration(Vector2D acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    boolean collides(GameObject gameObject1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void animate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
