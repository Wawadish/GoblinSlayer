package space.invaders;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class MenuPane extends Pane {

    public MenuPane() {

        AssetManager.stopAllSound();

        setBackground(AssetManager.getBackground(0));

        ImageView[] buttonArray = new ImageView[4];
        for (int i = 0; i < buttonArray.length; i++) {
            buttonArray[i] = new ImageView(AssetManager.getImage(i));
        }
        
        //Play Button
        Button playButton = new Button();
        playButton.setOnAction((ActionEvent event) -> {
            GoblinSlayer.changePane(new GamePane());
        });
        playButton.setLayoutX(1280 / 2 - 140);
        playButton.setLayoutY(720 / 2 - 85 / 2);
        playButton.setBackground(Background.EMPTY);
        playButton.setGraphic(buttonArray[0]);
        
        playButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            playButton.setGraphic(buttonArray[1]);
        });

        playButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            playButton.setGraphic(buttonArray[0]);
        });
        
        //Option Button
        Button optionButton = new Button();
        optionButton.setOnAction((ActionEvent event) -> {
            //TODO: switch to option Pane
        });
        double r = 65;
        optionButton.setShape(new Circle(r));
        optionButton.setMinSize(2 * r, 2 * r);
        optionButton.setMaxSize(2 * r, 2 * r);
        optionButton.setLayoutX(1280 / 2 - r);
        optionButton.setLayoutY(720 / 2 + 100);
        optionButton.setBackground(Background.EMPTY);
        optionButton.setGraphic(buttonArray[3]);
        
        optionButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            optionButton.setGraphic(buttonArray[2]);
        });

        optionButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            optionButton.setGraphic(buttonArray[3]);
        });
        
        getChildren().addAll(playButton, optionButton);

        AssetManager.getAudio(0).play();
    }

}
