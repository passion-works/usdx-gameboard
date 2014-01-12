/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ugb;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author amauter
 */
public class SoundPlayer {
    public Ugb gameBoard = Ugb.getInstance();
    public static SoundPlayer instance = null;

//** add this into your application code as appropriate
// Open an input stream  to the audio file.
    File timeTicking = new File(gameBoard.baseDir + "/sfx/time_running_out.wav");
    File countdownStart = new File(gameBoard.baseDir + "/sfx/countdown_start.wav");
    File countdownEnd = new File(gameBoard.baseDir + "/sfx/countdown_end.wav");
    File buzzer = new File(gameBoard.baseDir + "/sfx/buzzer.wav");
    File buzzerTooLate = new File(gameBoard.baseDir + "/sfx/yoshi_mount.wav");
    InputStream in = null;

    SoundPlayer() throws IOException {
        System.out.println("Sound Player initizialized");
    }
    
   
    public static SoundPlayer getInstance() throws IOException{
        if(instance == null){
            instance = new SoundPlayer();
        }
        return instance;
    }
    
    public void playTimeTicking() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(timeTicking);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error playing playTimeTicking sound.");
            ex.printStackTrace();
        }
    }
    
    public void playBuzzer() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(buzzer);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error playing buzzer sound.");
            ex.printStackTrace();
        }
    }
    
    public void playBuzzerTooLate() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(buzzerTooLate);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error playing buzzerTooLate sound.");
            ex.printStackTrace();
        }
    }
    
    public void playCountdownStart() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(countdownStart);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error playing countdownStart sound.");
            ex.printStackTrace();
        }
    }
     
    public void playCountdownEnd() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(countdownEnd);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error playing countdownEnd sound.");
            ex.printStackTrace();
        }
    }
}
