package space.invaders;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.ImagePattern;
import javax.swing.JOptionPane;

public class AssetManager {
    
    private static ArrayList<Background> backgrounds = new ArrayList<>();
    private static ArrayList<ImagePattern> goblins = new ArrayList<>();
    private static ArrayList<AudioClip> audio = new ArrayList<>();
    private static ArrayList<ImagePattern> swords = new ArrayList<>();
    
    private static String fileURL(String relativePath){
        return new File(relativePath).toURI().toString();
    }
    
    public static void preloadAllAssets(){
        try{
            addBackground("Menu Background.png");
            addBackground("Earth Cave Background.png");
            addBackground("Ice Cave Background.png");
            addBackground("Fire Cave Background.png");
            
            addSound("Menu.wav");
            addSound("Playing.wav");
            addSound("Projectile Fire.wav");
            addSound("Goblin Death.wav");
            addSound("Victory.wav");
            
            addSword("original sword");
            addSword("used sword");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error! Some files cannot be found!");
        }
    }
    
    private static void addSword(String name){
        swords.add(new ImagePattern(new Image(fileURL("./assets/images/" + name))));
    }
    private static void addBackground(String name){
        Image backgroundImage = new Image(fileURL("./assets/images/" + name));
        Background background = new Background(new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT));
        backgrounds.add(background);
    }
    
    private static void addSound(String name){
        AudioClip clip = new AudioClip(fileURL("./assets/sound/" + name));
        audio.add(clip);
    }
    
    public static ImagePattern getSword(int position){
        return swords.get(position);
    }
    
    public static Background getBackground(int position){
        return backgrounds.get(position);
    }
    
    public static AudioClip getAudio(int position){
        return audio.get(position);
    }
    
    public static void stopAllSound(){
        for (AudioClip ac : audio) {
            ac.stop();
        }
    }
    
    public static ImagePattern getGoblinImage(int position){
        return goblins.get(position);
    }
}
