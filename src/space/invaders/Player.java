/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.invaders;

/**
 *
 * @author Sam
 */
public class Player extends GameObject {
    
    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;

    public Player(Vector2D position, Vector2D velocity, Vector2D acceleration, double x, double y, double width, double height) {
        super(x, y, width, height);
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

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
