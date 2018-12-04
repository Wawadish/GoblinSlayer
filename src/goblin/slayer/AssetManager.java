package goblin.slayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;

public class AssetManager {
    
    private static ArrayList<Background> backgrounds = new ArrayList<>();
    private static ArrayList<Image> goblinsLeft = new ArrayList<>();
    private static ArrayList<Image> goblinsRight = new ArrayList<>();
    private static ArrayList<AudioClip> audio = new ArrayList<>();
    private static ArrayList<Image> swords = new ArrayList<>();
    private static ArrayList<Image> images = new ArrayList<>();
    private static Font gameFont;

    public static void preloadAllAssets(){
        try{
            //0
            addBackground("Menu Background.png");
            //1
            addBackground("Earth Cave Background.png");
            //2
            addBackground("Ice Cave Background.png");
            //3
            addBackground("Fire Cave Background.png");
            

            //0
            addImage("button0.png");
            //1
            addImage("button1.png");            
            //2
            addImage("wheel0.png");
            //3
            addImage("wheel1.png");
            //4
            addImage("life.png");
            
            
            //0
            addSound("Menu.wav");
            //1
            addSound("Playing.wav");
            //2
            addSound("Projectile Fire.wav");
            //3
            addSound("Goblin Death.wav");
            //4
            addSound("Victory.wav");
            //5
            addSound("Regret.wav");
            //6
            addSound("Swords_Collide_Sound.wav");
            
            //0
            addSword("original sword.png");
            //1
            addSword("used sword.png");
            //2
            addSword("goblin sword.png");
            
            addFont("Lohengrin.ttf");
            
            addGoblinDirection("/walking left/00.png", goblinsLeft);
            addGoblinDirection("/walking left/01.png", goblinsLeft);
            addGoblinDirection("/walking left/02.png", goblinsLeft);
            addGoblinDirection("/walking left/03.png", goblinsLeft);
            addGoblinDirection("/walking left/04.png", goblinsLeft);
            addGoblinDirection("/walking left/05.png", goblinsLeft);
            addGoblinDirection("/walking left/06.png", goblinsLeft);
            addGoblinDirection("/walking left/07.png", goblinsLeft);
            
            addGoblinDirection("/walking right/00.png", goblinsRight);
            addGoblinDirection("/walking right/01.png", goblinsRight);
            addGoblinDirection("/walking right/02.png", goblinsRight);
            addGoblinDirection("/walking right/03.png", goblinsRight);
            addGoblinDirection("/walking right/04.png", goblinsRight);
            addGoblinDirection("/walking right/05.png", goblinsRight);
            addGoblinDirection("/walking right/06.png", goblinsRight);
            addGoblinDirection("/walking right/07.png", goblinsRight);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error! Some files cannot be found!");
        }
    }
    
    private static String fileURL(String relativePath){
        return new File(relativePath).toURI().toString();
    }
    
    public static void addFont(String name) throws FileNotFoundException {
        gameFont = Font.loadFont(new FileInputStream(new File("./assets/fonts/" + name)), 24);
    }
    
    private static void addSword(String name){
        swords.add(new Image(fileURL("./assets/images/" + name)));
    }
    
    private static void addImage(String name){
        images.add(new Image(fileURL("./assets/images/" + name)));
    }
    
    private static void addGoblinDirection(String name, ArrayList<Image> goblinDirection){
        goblinDirection.add(new Image(fileURL("./assets/images/" + name)));
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
    
    public static Image getSword(int position){
        return swords.get(position);
    }
    
    public static Image getImage(int position){
        return images.get(position);
    }
    
    public static Background getBackground(int position){
        return backgrounds.get(position);
    }
    
    public static AudioClip getAudio(int position){
        return audio.get(position);
    }
    
    public static Font getGameFont() {
        return gameFont;
    }
    
    public static void stopAllSound(){
        for (AudioClip ac : audio) {
            ac.stop();
        }
    }
    
    public static Image getGoblinsRight(int position){
        return goblinsRight.get(position);
    }
    
    public static Image getGoblinsLeft(int position){
        return goblinsLeft.get(position);
    }
}
