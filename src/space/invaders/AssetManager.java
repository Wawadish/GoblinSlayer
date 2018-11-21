/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author wawad
 */
public class AssetManager {
    static private ArrayList<Background> backgrounds = new ArrayList<>();
    static private ArrayList<ImagePattern> goblins = new ArrayList<>();
    static private ArrayList<AudioClip> audio = new ArrayList<>();
    
    static private String fileURL(String relativePath){
        return new File(relativePath).toURI().toString();
    }
    
    static public void preloadAllAssets(){
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
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error! Some files cannot be found!");
        }
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
