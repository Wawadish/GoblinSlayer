/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.invaders;

import javafx.scene.layout.Pane;

/**
 *
 * @author wawad
 */
public class GamePane extends Pane{
    
    public GamePane(){
        
        AssetManager.stopAllSound();
        setBackground(AssetManager.getBackground(1));
        
        
    }
}
