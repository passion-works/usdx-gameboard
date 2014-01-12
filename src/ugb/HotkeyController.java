/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ugb;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
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
    }
    
    private void buzzerDown(int player){
        gameBoardGui.timer.stop();
        //keystrokeController.sendTest();
        if(gameBoard.jokerCount[player]>0){
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
        if(System.getProperty("sun.arch.data.model").equals("32")){
            JIntellitype.setLibraryLocation("lib/JIntellitype.dll");
        }
        else if(System.getProperty("sun.arch.data.model").equals("64")){
            JIntellitype.setLibraryLocation("lib/JIntellitype64.dll");
        }
        
        JIntellitype.getInstance().registerHotKey(4,"r");
        JIntellitype.getInstance().registerHotKey(5,"n");
        JIntellitype.getInstance().registerHotKey(6,"s");
        JIntellitype.getInstance().addHotKeyListener(HotkeyController.getInstance());
    }
    
    public void disableBuzzer(int player){
        JIntellitype.getInstance().unregisterHotKey(player);
    }
    
    public void enableBuzzer(int player){
        JIntellitype.getInstance().registerHotKey(player, String.valueOf(player));
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
