/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goblin.slayer;

/**
 *
 * @author Sam
 */
public class Enemy extends GameObject{
    
    private Vector2D position;
    private Vector2D velocity;

    public Enemy(Vector2D position, Vector2D velocity, double width, double height) {
        super(position.getX(), position.getY(), width, height);
        this.position = position;
        this.velocity = velocity;
    }

    public Vector2D getVelocity() {
        return velocity;
    }
    
    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
        this.setX(position.getX());
        this.setY(position.getY());
    }

    public Vector2D getPosition() {
        return position;
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

