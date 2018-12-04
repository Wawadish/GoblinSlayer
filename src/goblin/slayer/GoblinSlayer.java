package goblin.slayer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GoblinSlayer extends Application {

    private static Stage window;
    private static Scene scene;
    public int difficulty;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setResizable(false);

        AssetManager.preloadAllAssets();
        MenuPane menu = new MenuPane(1);

        scene = new Scene(menu, 1280, 720);

        //the value of paused is not being printed.
        window.setTitle("Goblin Slayer");

        window.setScene(scene);
        window.sizeToScene();

        window.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        window.show();
    }

    public static void main(String[] args) {
        //Just in case
        try {
            launch(args);
        } catch (Exception e) {

        }
    }

    public static void changePane(Pane p) {
        scene = new Scene(p, 1280, 720);
        window.setScene(scene);
        p.requestFocus();
    }
}
