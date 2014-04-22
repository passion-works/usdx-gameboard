/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ugb;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author amauter
 */
public class Ugb {
    public String baseDir = null;
    public static Ugb instance = null;
    public int[] jokerCount = new int[4]; //this array holds the number of remaining jokers the players have left
    public boolean jokerTriggered = false; //whether the joker was triggered this round
    public GUI gameBoardGUI = null;
    public HotkeyController buzzerController = null;
    public KeystrokeController keystrokeController = null;
    public JokerGame jokerGame = null;
    public boolean playJokerGame = true;
    public SoundPlayer soundPlayer;
    private int matchCount = 0;
    private int roundCount = 1;
    //TODO: Maybe set playerCount here
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ugb gameBoard = Ugb.getInstance();
        
        String path;
        String classDir = Ugb.class.getResource("Ugb.class").toString();
        if(classDir.startsWith("jar:")){
            path=Ugb.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path=path.substring(1, path.lastIndexOf("/")) + "/lib";
        }
        else{
            path=System.getProperty("user.dir") + "\\lib";
        }
        gameBoard.baseDir=path;
        gameBoard.init();
        System.out.println("Running UltraStar Gameboard on a " + System.getProperty("sun.arch.data.model") + "bit system");
        System.out.println("Current base dir: " + gameBoard.baseDir);
        
    }
    
    public static Ugb getInstance(){
        if(instance==null){
            instance = new Ugb();
        }
        return instance;
    }
    
    public void init(){
        //Initialize the GameBoard Main GUI (singleton)
        gameBoardGUI = GUI.getInstance();
        gameBoardGUI.init();
        
        //Initialize the singleton that handles the buzzer actions
        buzzerController = HotkeyController.getInstance();
        buzzerController.init();
        
        try {
            soundPlayer = SoundPlayer.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(Ugb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        keystrokeController = KeystrokeController.getInstance();
        jokerGame = JokerGame.getInstance();
    }
    
    public void newGame(){
        matchCount++;
        buzzerController.disableAllBuzzers();
        Object antwort = JOptionPane.showInputDialog(null, "How many jokers per player?", "New round", 
        JOptionPane.INFORMATION_MESSAGE, null, null, null);
        //TODO: Validate Antwort as int
        System.out.println("Setting " + Integer.parseInt((String) antwort) + " jokers for each player");
        for(int i=1; i<=3; i++){
            jokerCount[i] = Integer.parseInt((String) antwort);
        }
        buzzerController.enableAllBuzzers();
        reset();
    }
    
    public void startSong(){
        System.out.println(">>>START SONG");
        keystrokeController.sendKeyEnter();
        reset();
    }
    
    public void nudge(String direction){
        System.out.println(">>>NUDGE " + direction.toUpperCase());
        switch(direction){
            case "left":
                keystrokeController.sendKeyLeft();
            break;
            case "right":
                keystrokeController.sendKeyRight();
            break;
        }
        gameBoardGUI.delayedAction("startSong", 10000);
    }
    
    public void randomSong(int player){
        System.out.println(">>>RANDOM SONG");
          switch(player){
             case 1:
                 keystrokeController.sendKey1();
             break;
             case 2:
                 keystrokeController.sendKey2();
             break;
             case 3:
                keystrokeController.sendKey3();
             break;
            }
          reset();
    }
    
    public void useJoker(int player) {
         System.out.println("PLAY JOKER!!! And reducing joker count of Player" + player);
         jokerCount[player]--;
         if(playJokerGame){
             if(jokerGame.roundJokerLabels.size()==1){
                 jokerGame.setPlayer(player);
                 jokerGame.stopGame();
             }
             else jokerGame.startRound(player);
         }
         else{
            randomSong(player);
         } 
    }
    
    public void reset(){
        System.out.println("------ NEW ROUND ------");
        System.out.println("This is match #" + matchCount + " and overall round #" + roundCount);
        roundCount++;
        gameBoardGUI.reset();
        jokerGame.reset();
        jokerTriggered = false;
        for(int i=1; i<=3; i++){
            System.out.println("Player " + i + ": " + jokerCount[i] + " Joker left");
        }
        if(playJokerGame) showRoundJokers();
    }
    
    public void showRoundJokers(){
         for(String availableJoker:jokerGame.availableJokers){
             System.out.println("availableJoker: " + availableJoker);
             gameBoardGUI.activeJokerIconLabels.get(availableJoker).setVisible(false);
             gameBoardGUI.activeJokerLabels.get(availableJoker).setVisible(false);
         }
         for(String activeJoker:jokerGame.roundJoker){
             System.out.println("activeJoker: " + activeJoker);
             gameBoardGUI.activeJokerIconLabels.get(activeJoker).setVisible(true);
             gameBoardGUI.activeJokerLabels.get(activeJoker).setVisible(true);
         }
    }
}
