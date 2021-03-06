package goblin.slayer;

import javafx.scene.image.Image;

public class Player extends GameObject {

    private Vector2D position;

    private static Image swordState;
    private static boolean swordUsable;

    public Player(Vector2D position, final double width, double height) {
        super(position.getX(), position.getY(), width, height);
        this.position = position;
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

    public static Image getSwordState() {
        return swordState;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
        this.setX(position.getX());
        this.setY(position.getY());
    }
    
    @Override
    boolean collides(GameObject gameObject1) {
        return this.intersects(gameObject1.getBoundsInLocal());
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
