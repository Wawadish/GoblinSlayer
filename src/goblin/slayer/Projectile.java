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
public class Projectile extends GameObject {
    
    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;

    public Projectile(Vector2D position, Vector2D velocity, Vector2D acceleration, double width, double height) {
        super(position.getX(), position.getY(), width, height);
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }
    
    public Vector2D getVelocity() {
        return velocity;
    }
    
    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
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
