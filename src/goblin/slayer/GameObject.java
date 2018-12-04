/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goblin.slayer;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author Sam
 */
public abstract class GameObject extends Rectangle {

    public GameObject(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
    
    abstract boolean collides(GameObject o);
    
    abstract void update();
    
    abstract void animate();
    
}
