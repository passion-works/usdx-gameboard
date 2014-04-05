/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ugb;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amauter
 */
public class HotkeyController implements HotkeyListener {
    public static HotkeyController instance = null;
    public Ugb gameBoard;
    public GUI gameBoardGui;
    private final KeystrokeController keystrokeController = KeystrokeController.getInstance();
    private final JokerGame jokerGame = JokerGame.getInstance();
    private SoundPlayer soundPlayer = null;

    public HotkeyController() {
        System.out.println("BuzzerController constructor() called!");
        this.gameBoardGui = GUI.getInstance();
        this.gameBoard = Ugb.getInstance();
        try {
            this.soundPlayer = SoundPlayer.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(HotkeyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static HotkeyController getInstance(){
        if(instance == null){
            instance = new HotkeyController();
        }
        return instance;
    }
    
    @Override
    public void onHotKey(int i) {
        if(i<=3) buzzerDown(i);
        else if(i==4) roundReset();
        else if(i==5) newGame();
        else if(i==6) toggleCountdown();
        else if(i==101){
            gameBoard.jokerCount[1]++;
            gameBoardGui.buzzerCountArray[1].setText(String.valueOf(gameBoard.jokerCount[1]));
        }
        else if(i==102){
            gameBoard.jokerCount[2]++;
            gameBoardGui.buzzerCountArray[2].setText(String.valueOf(gameBoard.jokerCount[2]));
        }
        else if(i==103){
            gameBoard.jokerCount[3]++;
            gameBoardGui.buzzerCountArray[3].setText(String.valueOf(gameBoard.jokerCount[3]));
        }
        else if(i==999){
            gameBoardGui.openSettings();
        }
    }
    
    private void buzzerDown(int player){
        //keystrokeController.sendTest();
        if(jokerGame.timer.isRunning()){
            if(player==jokerGame.currentPlayer){
                System.out.println("STOP JOKER GAME! Player " + player);
                jokerGame.stopGame();
            }
        }
        else if(gameBoard.jokerCount[player]>0){
            gameBoardGui.timer.stop();
            if(gameBoard.jokerTriggered==false){
                soundPlayer.playBuzzer();
                System.out.println("JOKER! Player " + player);
                gameBoard.jokerTriggered=true;
                gameBoardGui.enableBuzzer(player);
            }
            else{
                if(!gameBoardGui.buzzerActiveArray[player].isVisible()){
                    soundPlayer.playBuzzerTooLate();
                    System.out.println("too slow Player " + player);
                    gameBoardGui.tooSlowBuzzer(player);                                
                }
            }
        }
    }
    
    private void roundReset(){
         System.out.println("Round RESET");
         gameBoard.reset();
    }
    
    private void newGame(){
        System.out.println("NEW game");
        gameBoard.newGame();
    }
    
    public void init(){
        // This didn't work - just put the dll file to C:\Windows\, C:\Windows\system\ and C:\Windows\system32\
        if(System.getProperty("sun.arch.data.model").equals("32")){
            JIntellitype.setLibraryLocation(System.getProperty("user.dir") + "/lib/JIntellitype.dll");
        }
        else if(System.getProperty("sun.arch.data.model").equals("64")){
            JIntellitype.setLibraryLocation(System.getProperty("user.dir") + "/lib/JIntellitype64.dll");
        }
        
        
        JIntellitype.getInstance().registerHotKey(4,0, KeyEvent.VK_DELETE); //reset/new round
        JIntellitype.getInstance().registerHotKey(5,0, KeyEvent.VK_INSERT); //new match
        JIntellitype.getInstance().registerHotKey(6,0, KeyEvent.VK_END); //start countdown
        JIntellitype.getInstance().registerHotKey(101,JIntellitype.MOD_SHIFT, (int)'1');
        JIntellitype.getInstance().registerHotKey(102,JIntellitype.MOD_SHIFT, (int)'2');
        JIntellitype.getInstance().registerHotKey(103,JIntellitype.MOD_SHIFT, (int)'3');
        JIntellitype.getInstance().registerHotKey(999,JIntellitype.MOD_CONTROL,(int)'S');
        
        JIntellitype.getInstance().addHotKeyListener(HotkeyController.getInstance());
    }
    
    public void disableBuzzer(int player){
        JIntellitype.getInstance().unregisterHotKey(player);
    }
    
    public void enableBuzzer(int player){
        switch(player){
            //the strings 1-3 assigned here are generated by actually pressing the buzzers!
            case 1:
                JIntellitype.getInstance().registerHotKey(player, "1");
                //JIntellitype.getInstance().registerHotKey(player, "1");
            break;
            case 2:
                JIntellitype.getInstance().registerHotKey(player, "2");
                //JIntellitype.getInstance().registerHotKey(player, "2");
            break;
            case 3:
                JIntellitype.getInstance().registerHotKey(player, "3");
                //JIntellitype.getInstance().registerHotKey(player, "3");
            break;
        }
    }
    
    public void enableAllBuzzers(){
        for(int i=1; i<=3; i++){
            enableBuzzer(i);
        }
    }
    
    public void disableAllBuzzers(){
        for(int i=1; i<=3; i++){
            disableBuzzer(i);
        }
    }
    
    public void toggleCountdown(){
        System.out.println("Toggle Countdown");    
        if(gameBoardGui.timer.isRunning()){
            gameBoardGui.timer.stop();
        } else {
            gameBoardGui.timer.start();
            soundPlayer.playCountdownStart();
        }
    }
            
    
}
