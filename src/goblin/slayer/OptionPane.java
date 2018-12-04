package goblin.slayer;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class OptionPane extends Pane {

    Font gameFont;

    CheckBox easy;
    CheckBox normal;
    CheckBox hard;
    CheckBox insane;

    int difficulty;

    public OptionPane(int difficultyTemp) {
        
        
        gameFont = AssetManager.getGameFont();
        setBackground(AssetManager.getBackground(0));

        difficulty = difficultyTemp;
        
        //Option Label
        Label option = new Label("Options");
        option.setFont(gameFont);
        option.setScaleX(3);
        option.setScaleY(3);
        option.setLayoutX(1280 / 2 - 50);
        option.setLayoutY(720 / 2 - 50);
        option.setTextFill(Color.WHITE);
        option.setTextAlignment(TextAlignment.CENTER);
        
        //Choose your difficulty Label
        Label difficultyLabel = new Label("Choose your Difficulty:");
        difficultyLabel.setFont(gameFont);
        difficultyLabel.setScaleX(2);
        difficultyLabel.setScaleY(2);
        difficultyLabel.setLayoutX(1280 / 2 - 100);
        difficultyLabel.setLayoutY(720 / 2 + 20);
        difficultyLabel.setTextFill(Color.WHITE);
        difficultyLabel.setTextAlignment(TextAlignment.CENTER);

        //Easy
        easy = new CheckBox("Easy");
        easy.setFont(gameFont);
        easy.setTextFill(Color.WHITE);
        easy.setLayoutX(1280 / 2 - 400);
        easy.setLayoutY(720 / 2 + 100);

        easy.setOnAction((ActionEvent event) -> {
            normal.setSelected(false);
            hard.setSelected(false);
            insane.setSelected(false);
            difficulty = 1;
        });

        //Normal
        normal = new CheckBox("Normal");
        normal.setFont(gameFont);
        normal.setTextFill(Color.WHITE);
        normal.setLayoutX(1280 / 2 - 175);
        normal.setLayoutY(720 / 2 + 100);

        normal.setOnAction((ActionEvent event) -> {
            easy.setSelected(false);
            hard.setSelected(false);
            insane.setSelected(false);
            difficulty = 2;
        });

        //Hard
        hard = new CheckBox("Hard");
        hard.setFont(gameFont);
        hard.setTextFill(Color.WHITE);
        hard.setLayoutX(1280 / 2 + 100);
        hard.setLayoutY(720 / 2 + 100);

        hard.setOnAction((ActionEvent event) -> {
            easy.setSelected(false);
            normal.setSelected(false);
            insane.setSelected(false);
            difficulty = 3;
        });

        //Insane
        insane = new CheckBox("Insane");
        insane.setFont(gameFont);
        insane.setTextFill(Color.WHITE);
        insane.setLayoutX(1280 / 2 + 325);
        insane.setLayoutY(720 / 2 + 100);

        insane.setOnAction((ActionEvent event) -> {
            easy.setSelected(false);
            normal.setSelected(false);
            hard.setSelected(false);
            difficulty = 4;
        });
        
        initializePane();
        
        //Back to menu Button
        Button optionButton = new Button();
        optionButton.setOnAction((ActionEvent event) -> {
            GoblinSlayer.changePane(new MenuPane(difficulty));
        });
        double r = 50;
        optionButton.setShape(new Circle(r));
        optionButton.setMinSize(2 * r, 2 * r);
        optionButton.setMaxSize(2 * r, 2 * r);
        optionButton.setLayoutX(1280 / 2 - r);
        optionButton.setLayoutY(720 / 2 + 200);
        optionButton.setBackground(Background.EMPTY);
        optionButton.setGraphic(new ImageView(AssetManager.getImage(3)));

        optionButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            optionButton.setGraphic(new ImageView(AssetManager.getImage(2)));
        });

        optionButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            optionButton.setGraphic(new ImageView(AssetManager.getImage(3)));
        });

        getChildren().addAll(optionButton, option, difficultyLabel, easy, normal, hard, insane);

    }

    //Checks for current difficulty
    private void initializePane() {        
        switch (difficulty) {
            case 1:
                easy.fire();
                break;
            case 2:
                normal.fire();
                break;
            case 3:
                hard.fire();
                break;
            case 4: 
                insane.fire();
                break;
        }
    }
}
