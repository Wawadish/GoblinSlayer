/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package goblin.slayer;

/**
 *
 * @author bergeron
 */
public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Accessors and Mutators
    public double getX(){ return x; }
    public double getY(){ return y; }
    
    public void  setX(double value){ x=value; }
    public void  setY(double value){ y=value; }
    
    // Operations
    public Vector2D add(Vector2D other) {
        return new Vector2D(x + other.x, y + other.y);
    }

    public Vector2D sub(Vector2D other) {
        return new Vector2D(x - other.x, y - other.y);
    }

    public Vector2D mult(double multiplier) {
        return new Vector2D(x * multiplier, y * multiplier);
    }
    
    public double dot(Vector2D other){
        return x*other.x + y*other.y;
    }
}
