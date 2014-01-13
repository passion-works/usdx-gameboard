/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ugb;

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
    //TODO: Maybe set playerCount here
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Ugb gameBoard = Ugb.getInstance();
        gameBoard.baseDir = args[0].replaceAll("\\\\", "/");
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
        
        keystrokeController = KeystrokeController.getInstance();
        jokerGame = JokerGame.getInstance();
    }
    
    public void newGame(){
        buzzerController.disableAllBuzzers();
        Object antwort = JOptionPane.showInputDialog(null, "Wieviele Joker", "Neue Runde", 
        JOptionPane.INFORMATION_MESSAGE, null, null, null);
        //TODO: Validate Antwort as int
        //TODO: set jokerCount[player]=antwort for each player 
        System.out.println("Setze: " + Integer.parseInt((String) antwort) + " für jeden Spieler");
        for(int i=1; i<=3; i++){
            jokerCount[i] = Integer.parseInt((String) antwort);
        }
        buzzerController.enableAllBuzzers();
        reset();
    }
    
    public void startSong(){
        System.out.println("START ROUND!!!");
        keystrokeController.sendKeyEnter();
        reset();
    }

    public void useJoker(int player) {
         System.out.println("PLAY JOKER!!! And reducing joker count of Player" + player);
         jokerCount[player]--;
         reset();
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
         //TODO: send keystroke player to USDX
        
    }
    
    public void reset(){
        System.out.println("--- NEW ROUND ---");
        gameBoardGUI.reset();
        jokerTriggered = false;
        for(int i=1; i<=3; i++){
            System.out.println("Player " + i + ": " + jokerCount[i] + " Joker left");
        }
    }
}
