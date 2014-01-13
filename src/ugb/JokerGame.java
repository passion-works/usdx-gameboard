/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ugb;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author amauter
 */
public class JokerGame {
    public static JokerGame instance = null;
    public GUI gui = GUI.getInstance();
    public Ugb gameBoard = Ugb.getInstance();
    final DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    public Timer timer = new Timer(1000, null);
    public long timerTimeLeft = 8000;
    
    public static JokerGame getInstance(){
        if(instance == null){
            instance = new JokerGame();
        }
        return instance;
    }
    
    JokerGame(){
        initTimer();
        timer.start();
        setPlayer(3);
    }
    
    public void setPlayer(int player){
        Color red = new Color(255,0,51);
        Color green = new Color(0,255,0);
        Color blue = new Color(0,0,255);
        Color activeColor = null;
        
        switch(player){
            case 1:
                activeColor = blue;
            break;
            case 2:
                activeColor = red;
            break;
            case 3:
                activeColor = green;
            break;
        }
        for(String key : gui.jokerGameIconActiveLabels.keySet()) {
            System.out.println("key: " + key);
            gui.jokerGameIconActiveLabels.get(key).setForeground(activeColor);

        }
    }
    public void initTimer(){
        symbols.setGroupingSeparator(':');
        timer.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                timerTimeLeft -= 1000;
                
                if(timerTimeLeft < 0){
                    timer.stop();
                    gameBoard.jokerTriggered=true;
                    gui.gameTimer.setText("LET'S GO");
                    gui.gameTimer.setFont(new Font("Lucide Grande", 0, 200));
                   
                    gui.gameTimer.setBackground(Color.green);
                    gui.gameTimer.setForeground(Color.white);
                    
                    //soundPlayer.playCountdownEnd();
                    //delayedAction("startSong");
                }
                else{
                    if(timerTimeLeft <= 3000){
                        if(timerTimeLeft%1000==0 && timerTimeLeft/1000<=3){
                            //this adds a flashing effect
                            gui.gameTimer.setBackground(Color.red);
                            gui.gameTimer.setForeground(Color.white);
                            //soundPlayer.playTimeTicking();
                        } else{
                            //this resets the flash
                            gui.gameTimer.setBackground(null);
                            gui.gameTimer.setForeground(Color.red);
                        } 
                    }
                    long diff = (timerTimeLeft)/1000;
                    String timeRemaining = StringFormatter.customFormat("0", diff, symbols);
                    gui.gameTimer.setText(timeRemaining);
                }
            }
        });

    }

}
