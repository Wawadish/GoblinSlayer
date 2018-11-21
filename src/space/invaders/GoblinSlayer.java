/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.invaders;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author wawad
 */
public class GoblinSlayer extends Application {
    
    private static Stage window;
    private static Scene scene;
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        
        AssetManager.preloadAllAssets();
        MenuPane menu = new MenuPane();
        
        scene = new Scene(menu, 1280, 720);
        
        window.setTitle("Goblin Slayer");
        window.setScene(scene);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void changePane(Pane p){
        scene = new Scene(p, 1280, 720);
        window.setScene(scene);
    }
    
}
