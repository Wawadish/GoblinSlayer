package space.invaders;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class MenuPane extends Pane{
 
    public MenuPane() {
        
        AssetManager.stopAllSound();
        
        setBackground(AssetManager.getBackground(0));
        
        Button playButton = new Button();
        playButton.setText("Play");
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                GamePane gamePane = new GamePane();
                GoblinSlayer.changePane(gamePane);
            }
        });
        playButton.setPrefSize(280, 85);
        playButton.setLayoutX(1280/2 - 140);
        playButton.setLayoutY(720/2 - 85/2);
        
        
        Button optionButton = new Button();
        optionButton.setText("Options");
        optionButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("OPTIONS");
            }
        });
        double r = 65;
        optionButton.setShape(new Circle(r));
        optionButton.setMinSize(2*r, 2*r);
        optionButton.setMaxSize(2*r, 2*r);
        optionButton.setLayoutX(1280/2 - r);
        optionButton.setLayoutY(720/2 + 100);
        
        getChildren().addAll(playButton, optionButton);
        
        AssetManager.getAudio(0).play();
    }
    
}
