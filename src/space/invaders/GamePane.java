package space.invaders;

import javafx.scene.layout.Pane;

public class GamePane extends Pane {

    public GamePane() {

        AssetManager.stopAllSound();
        AssetManager.getAudio(1).play();
        setBackground(AssetManager.getBackground(1));

        //the player constructor is waaaay to complicated
        Player player = new Player(new Vector2D(1280 / 2, 720 / 2), new Vector2D(1, 1), new Vector2D(0, 0), USE_PREF_SIZE, USE_PREF_SIZE, USE_PREF_SIZE, USE_PREF_SIZE);

    }
}
