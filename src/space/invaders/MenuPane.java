package space.invaders;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class MenuPane extends Pane {

    public MenuPane() {

        AssetManager.stopAllSound();

        setBackground(AssetManager.getBackground(0));

        Button playButton = new Button();
        playButton.setText("Play");
        playButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                GoblinSlayer.changePane(new GamePane());
            }
        });
        playButton.setPrefSize(280, 85);
        playButton.setLayoutX(1280 / 2 - 140);
        playButton.setLayoutY(720 / 2 - 85 / 2);

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
        optionButton.setMinSize(2 * r, 2 * r);
        optionButton.setMaxSize(2 * r, 2 * r);
        optionButton.setLayoutX(1280 / 2 - r);
        optionButton.setLayoutY(720 / 2 + 100);
        
        /* not working even after addin in getChildren
        ImageView view1 = new ImageView(AssetManager.getTorch(0));
        view1.setLayoutX(0);
        view1.setLayoutY(720);

        ImageView view2 = new ImageView();
        view2.setImage(AssetManager.getTorch(1));
        view2.setLayoutX(1280 - view2.getImage().getWidth());
        view2.setLayoutY(720);
        view2.setVisible(true);
        */

        getChildren().addAll(playButton, optionButton);

        AssetManager.getAudio(0).play();
    }

}
