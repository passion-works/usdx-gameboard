/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ugb;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public long timerTimeLeft = 11000;
    Map<String, Double> jokerProb = new HashMap<>();
    public String[] availableJokers = new String[3];
    public List<String> roundJokerLabels = new ArrayList<>();
    public List<String> roundJoker = new ArrayList<>();
    private int jokerPointer;
    public int currentPlayer;
    public SoundPlayer soundPlayer;
    
    public static JokerGame getInstance(){
        if(instance == null){
            instance = new JokerGame();
        }
        return instance;
    }
    
    JokerGame(){
        try {
            soundPlayer = SoundPlayer.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(JokerGame.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        jokerProb.put("play", 0.4);
        jokerProb.put("nudge", 0.65);
        jokerProb.put("random", 0.9);
        
        availableJokers[0]="play";
        availableJokers[1]="nudge";
        availableJokers[2]="random";
    }
    
    public int getJokerProb(String joker){
        return (int) (jokerProb.get(joker) * 100);
    }
    
    public void setJokerProb(String joker, int value){
        System.out.println("SET New Joker propability for " + joker + ": " + value);
        double newProb = (double) value / 100;
        jokerProb.put(joker, newProb);
        System.out.println("New Joker propability for " + joker + ": " + jokerProb.get(joker));
    }
    
    public List<String> getLabelsForJokerType(String jokerType){
        List<String> returnJokerLabels = new ArrayList<>();
        switch(jokerType){
            case "play":
                returnJokerLabels.add("play");
            break;
            case "nudge":
                returnJokerLabels.add("left");
                returnJokerLabels.add("right");
            break;
            case "random":
                returnJokerLabels.add("random");
            break;
        }
        return returnJokerLabels;
    }
    
    public void stopGame(){
        String activeJoker = getActiveJoker();
        if(!activeJoker.equals("error!")){
            timer.stop();
            if(roundJokerLabels.size()>1) soundPlayer.playBuzzer();
            gui.jokerGameIconChosenLabels.get(activeJoker).setVisible(true);
            gui.jokerGameIconLabels.get(activeJoker).setForeground(Color.white);
            System.out.println("GAME STOPPED at Joker: " + getActiveJoker());
            //TODO: run gui.delayedAction("nudge_left") with 3 seconds delay, then reset everything
            switch(activeJoker){
                case "play":
                    gui.delayedAction("startSong", 3000);
                break;
                case "left":
                    gameBoard.nudge("left");
                break;
                case "right":
                    gameBoard.nudge("right");
                break;
                case "random":
                    gui.delayedAction("random", 0);
                break;
            }
        }
    }
    public List<String> getRoundJokers(){
        List<String> returnJokers=new ArrayList<>();
        double thisProb;
        for(String thisJoker:availableJokers){
            thisProb = Math.random();
            if(jokerProb.get(thisJoker)>=thisProb){
                System.out.println("ADD! -> prob for Joker " + thisJoker + " > thisProb ("+thisProb+")");
                returnJokers.addAll(getLabelsForJokerType(thisJoker));
                roundJoker.add(thisJoker);
            }
            else{
                System.out.println("NO-ADD! -> prob for Joker " + thisJoker + " < thisProb ("+thisProb+")");
            }
        }
        if(returnJokers.size()==0){
                System.out.println("FALLBACK - NO JOKERS WERE CHOSEN SO ADD THE RANDOM JOKER");
                returnJokers.addAll(getLabelsForJokerType("random"));
                roundJoker.add("random");
        }
        return returnJokers;
    }
    
    public void setPlayer(int player){
        currentPlayer = player;
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
        for(String key : gui.jokerGameIconSelectedLabels.keySet()) {
            gui.jokerGameIconSelectedLabels.get(key).setForeground(activeColor);
            gui.jokerGameIconChosenLabels.get(key).setForeground(activeColor);
        }
    }
    public void setRoundJoker(){
        System.out.println("SETTING JOKERS");
        roundJokerLabels = getRoundJokers();   
    }
    
    public void startRound(int player){
        setPlayer(player);
        gui.gameTimer.setText(String.valueOf(timerTimeLeft/1000));
        
        
        System.out.println("we have " + roundJokerLabels.size()+ " different jokers this round!");
        
        gui.layerMainGame.setVisible(false);
        gui.layerJokerGame.setVisible(true);
        
        for(String thisJoker: roundJokerLabels){
            gui.jokerGameIconLabels.get(thisJoker).setVisible(true);
        }
        //jokerPointer = 0;
        
        if(timer.getActionListeners().length==0) initTimer();
        
        timer.start();
        
    }
    
    public void nextJoker(){
        System.out.println("jokerPointer: " + jokerPointer);
        
        int nextJoker = jokerPointer;
        int previousJoker = jokerPointer - 1;
        
        if(previousJoker<0) previousJoker = roundJokerLabels.size()-1;
        
        if(nextJoker+1==roundJokerLabels.size()){
            nextJoker = jokerPointer;
            jokerPointer = 0;
        }
        else jokerPointer++;
        
        gui.jokerGameIconSelectedLabels.get(roundJokerLabels.get(previousJoker)).setVisible(false);
        gui.jokerGameIconSelectedLabels.get(roundJokerLabels.get(nextJoker)).setVisible(true);       
    }
    
    public String getActiveJoker(){
        for(String thisJoker:roundJokerLabels){
            if(gui.jokerGameIconSelectedLabels.get(thisJoker).isVisible()) return thisJoker;
        }
        return "error!";
    }
    
    
    public void initTimer(){
        symbols.setGroupingSeparator(':');
        timer.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                timerTimeLeft -= 1000;
                nextJoker();
                
                if(timerTimeLeft <= 0){
                    stopGame();
                    gui.gameTimer.setText("0");
                    
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
    
    public void reset(){
        timer.stop();
        timerTimeLeft = 11000;
        gui.layerMainGame.setVisible(true);
        gui.layerJokerGame.setVisible(false);
        gui.gameTimer.setForeground(Color.black);
        gui.gameTimer.setBackground(null);
        gui.resetAllJokeGameChosenLabels();
        roundJoker.clear();
        roundJokerLabels.clear();
        setRoundJoker();
        System.out.println("BLABLABLA");
        jokerPointer = 0;
        gui.hideAllJokeGameIcons();
        gui.hideAllJokeGameActiveLabels();
        nextJoker();
    }

}
