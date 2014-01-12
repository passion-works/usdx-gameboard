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
import java.io.IOException;
import java.text.DecimalFormatSymbols;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.Timer;
/**
 *
 * @author amauter
 */
public class GUI extends javax.swing.JFrame {
    public final int countdownSeconds = 7;
    public static GUI instance = null;
    public Timer timer = new Timer(100, null);
    public Timer delayTimer = new Timer(5000, null);
    public SoundPlayer soundPlayer;
    public Ugb gameBoard;
    public JLabel[] buzzerArray = new JLabel[4];
    public JLabel[] buzzerActiveArray = new JLabel[4];
    public JLabel[] buzzerJokerArray = new JLabel[4];
    public JLabel[] buzzerTooSlowArray = new JLabel[4];
    public JLabel[] buzzerCountArray = new JLabel[4];
    public long timerTimeLeft = 8000;
    public long timerDelay = 5000;
    final DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    
    public static GUI getInstance(){
        if(instance == null){
            instance = new GUI();
        }
        return instance;
    }
    
    /**
     * Creates new form MainScreen
     */
    public GUI() {    
        initComponents();
        
        buzzerArray[1] = buzzer1;
        buzzerArray[2] = buzzer2;
        buzzerArray[3] = buzzer3;
        
        buzzerActiveArray[1] = buzzer1_active;
        buzzerActiveArray[2] = buzzer2_active;
        buzzerActiveArray[3] = buzzer3_active;
        
        buzzerJokerArray[1] = buzzer1_joker;
        buzzerJokerArray[2] = buzzer2_joker;
        buzzerJokerArray[3] = buzzer3_joker;
        
        buzzerTooSlowArray[1] = buzzer1_tooslow;
        buzzerTooSlowArray[2] = buzzer2_tooslow;
        buzzerTooSlowArray[3] = buzzer3_tooslow;
        
        buzzerCountArray[1] = buzzer1_count;
        buzzerCountArray[2] = buzzer2_count;
        buzzerCountArray[3] = buzzer3_count;
        try {
            this.soundPlayer = SoundPlayer.getInstance();
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        gameBoard = Ugb.getInstance();
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        remTimePlchldLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        buzzer1_joker = new javax.swing.JLabel();
        buzzer1_tooslow = new javax.swing.JLabel();
        buzzer1 = new javax.swing.JLabel();
        buzzer1_active = new javax.swing.JLabel();
        buzzer1_count = new javax.swing.JLabel();
        buzzer2_joker = new javax.swing.JLabel();
        buzzer2_tooslow = new javax.swing.JLabel();
        buzzer2 = new javax.swing.JLabel();
        buzzer2_active = new javax.swing.JLabel();
        buzzer2_count = new javax.swing.JLabel();
        buzzer3_joker = new javax.swing.JLabel();
        buzzer3_tooslow = new javax.swing.JLabel();
        buzzer3 = new javax.swing.JLabel();
        buzzer3_active = new javax.swing.JLabel();
        buzzer3_count = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UltraStar Deluxe Gameboard");
        setMinimumSize(new java.awt.Dimension(1280, 900));
        setPreferredSize(new java.awt.Dimension(1280, 900));
        setResizable(false);
        getContentPane().setLayout(null);

        remTimePlchldLabel.setFont(new java.awt.Font("Lucida Grande", 0, 300)); // NOI18N
        remTimePlchldLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        remTimePlchldLabel.setText(" ");
        remTimePlchldLabel.setOpaque(true);
        getContentPane().add(remTimePlchldLabel);
        remTimePlchldLabel.setBounds(0, 11, 1268, 312);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(1310, 200, 1, 212);

        buzzer1_joker.setFont(new java.awt.Font("Tahoma", 1, 80)); // NOI18N
        buzzer1_joker.setForeground(new java.awt.Color(255, 255, 255));
        buzzer1_joker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer1_joker.setText("JOKER");
        getContentPane().add(buzzer1_joker);
        buzzer1_joker.setBounds(70, 500, 290, 140);

        buzzer1_tooslow.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        buzzer1_tooslow.setForeground(new java.awt.Color(255, 255, 255));
        buzzer1_tooslow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer1_tooslow.setText("too slow");
        getContentPane().add(buzzer1_tooslow);
        buzzer1_tooslow.setBounds(70, 500, 290, 140);

        buzzer1.setFont(new java.awt.Font("Wingdings", 0, 470)); // NOI18N
        buzzer1.setForeground(new java.awt.Color(0, 0, 255));
        buzzer1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer1.setText(" ");
        getContentPane().add(buzzer1);
        buzzer1.setBounds(0, 330, 426, 516);

        buzzer1_active.setFont(new java.awt.Font("Wingdings", 0, 470)); // NOI18N
        buzzer1_active.setForeground(new java.awt.Color(0, 0, 255));
        buzzer1_active.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer1_active.setText(" ");
        buzzer1_active.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        getContentPane().add(buzzer1_active);
        buzzer1_active.setBounds(0, 330, 426, 516);

        buzzer1_count.setFont(new java.awt.Font("Tahoma", 1, 80)); // NOI18N
        buzzer1_count.setForeground(new java.awt.Color(0, 51, 255));
        buzzer1_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer1_count.setText("2");
        getContentPane().add(buzzer1_count);
        buzzer1_count.setBounds(180, 500, 70, 140);

        buzzer2_joker.setFont(new java.awt.Font("Tahoma", 1, 80)); // NOI18N
        buzzer2_joker.setForeground(new java.awt.Color(255, 255, 255));
        buzzer2_joker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer2_joker.setText("JOKER");
        getContentPane().add(buzzer2_joker);
        buzzer2_joker.setBounds(500, 500, 290, 140);

        buzzer2_tooslow.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        buzzer2_tooslow.setForeground(new java.awt.Color(255, 255, 255));
        buzzer2_tooslow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer2_tooslow.setText("too slow");
        getContentPane().add(buzzer2_tooslow);
        buzzer2_tooslow.setBounds(500, 500, 290, 140);

        buzzer2.setFont(new java.awt.Font("Wingdings", 0, 470)); // NOI18N
        buzzer2.setForeground(new java.awt.Color(255, 0, 51));
        buzzer2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer2.setText(" ");
        getContentPane().add(buzzer2);
        buzzer2.setBounds(430, 330, 426, 516);

        buzzer2_active.setFont(new java.awt.Font("Wingdings", 0, 470)); // NOI18N
        buzzer2_active.setForeground(new java.awt.Color(255, 0, 51));
        buzzer2_active.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer2_active.setText(" ");
        buzzer2_active.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        getContentPane().add(buzzer2_active);
        buzzer2_active.setBounds(430, 330, 426, 516);

        buzzer2_count.setFont(new java.awt.Font("Tahoma", 1, 80)); // NOI18N
        buzzer2_count.setForeground(new java.awt.Color(255, 0, 51));
        buzzer2_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer2_count.setText("3");
        getContentPane().add(buzzer2_count);
        buzzer2_count.setBounds(610, 500, 70, 140);

        buzzer3_joker.setFont(new java.awt.Font("Tahoma", 1, 80)); // NOI18N
        buzzer3_joker.setForeground(new java.awt.Color(255, 255, 255));
        buzzer3_joker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer3_joker.setText("JOKER");
        getContentPane().add(buzzer3_joker);
        buzzer3_joker.setBounds(920, 500, 290, 140);

        buzzer3_tooslow.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        buzzer3_tooslow.setForeground(new java.awt.Color(255, 255, 255));
        buzzer3_tooslow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer3_tooslow.setText("too slow");
        getContentPane().add(buzzer3_tooslow);
        buzzer3_tooslow.setBounds(920, 500, 290, 140);

        buzzer3.setFont(new java.awt.Font("Wingdings", 0, 470)); // NOI18N
        buzzer3.setForeground(new java.awt.Color(0, 255, 0));
        buzzer3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer3.setText(" ");
        buzzer3.setToolTipText("");
        getContentPane().add(buzzer3);
        buzzer3.setBounds(850, 330, 426, 516);

        buzzer3_active.setFont(new java.awt.Font("Wingdings", 0, 470)); // NOI18N
        buzzer3_active.setForeground(new java.awt.Color(0, 255, 0));
        buzzer3_active.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer3_active.setText(" ");
        buzzer3_active.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        getContentPane().add(buzzer3_active);
        buzzer3_active.setBounds(850, 330, 426, 516);

        buzzer3_count.setFont(new java.awt.Font("Tahoma", 1, 80)); // NOI18N
        buzzer3_count.setForeground(new java.awt.Color(0, 255, 0));
        buzzer3_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer3_count.setText("4");
        getContentPane().add(buzzer3_count);
        buzzer3_count.setBounds(1030, 500, 70, 140);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void initTimer(){
        symbols.setGroupingSeparator(':');
        timer.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                timerTimeLeft -= 100;
                
                if(timerTimeLeft < 0){
                    timer.stop();
                    gameBoard.jokerTriggered=true;
                    remTimePlchldLabel.setText("LET'S GO");
                    remTimePlchldLabel.setFont(new Font("Lucide Grande", 0, 200));
                   
                    remTimePlchldLabel.setBackground(Color.green);
                    remTimePlchldLabel.setForeground(Color.white);
                    
                    soundPlayer.playCountdownEnd();
                    delayedAction("startSong");
                }
                else{
                    if(timerTimeLeft <= 3000){
                        if(timerTimeLeft%1000==0 && timerTimeLeft/1000<=3){
                            //this adds a flashing effect
                            remTimePlchldLabel.setBackground(Color.red);
                            remTimePlchldLabel.setForeground(Color.white);
                            soundPlayer.playTimeTicking();
                        } else{
                            //this resets the flash
                            remTimePlchldLabel.setBackground(null);
                            remTimePlchldLabel.setForeground(Color.red);
                        } 
                    }
                    long diff = (timerTimeLeft/100)*10;
                    String timeRemaining = StringFormatter.customFormat("00,00", diff, symbols);
                    remTimePlchldLabel.setText(timeRemaining);
                }
            }
        });

    }
    /**
     * @param args the command line arguments
     */
    public void init() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI.getInstance().setVisible(true);
            }
        });
        
        //as we're using Wingdings font for displaying the different Symbols we have to explicitly use character codes to address the dingbat characters
        buzzer1_active.setText("\uF06C");
        buzzer2_active.setText("\uF06C");
        buzzer3_active.setText("\uF06C");
        
        buzzer1.setText("\uF0A1");
        buzzer2.setText("\uF0A1");
        buzzer3.setText("\uF0A1");
               
        initTimer();
        reset();
    }
    
    public void reset(){
        timer.stop();
        
        buzzer1_active.setVisible(false);
        buzzer1_joker.setVisible(false);
        buzzer1_tooslow.setVisible(false);
       
        buzzer2_active.setVisible(false);
        buzzer2_joker.setVisible(false);
        buzzer2_tooslow.setVisible(false);
        
        buzzer3_active.setVisible(false);
        buzzer3_joker.setVisible(false);
        buzzer3_tooslow.setVisible(false);
        
        timerTimeLeft = 8000;
        long diff = (timerTimeLeft/100)*10;
        String timeRemaining = StringFormatter.customFormat("00,00", diff, symbols);
        remTimePlchldLabel.setText(timeRemaining);
        
        remTimePlchldLabel.setBackground(null);
        remTimePlchldLabel.setForeground(Color.black);
        remTimePlchldLabel.setFont(new Font("Lucide Grande", 0, 300));
        
        buzzer1_count.setText(String.valueOf(gameBoard.jokerCount[1]));
        buzzer2_count.setText(String.valueOf(gameBoard.jokerCount[2]));
        buzzer3_count.setText(String.valueOf(gameBoard.jokerCount[3]));
        
        for(int i=1;i<=3;i++){
            if(gameBoard.jokerCount[i]>0){
                buzzerArray[i].setVisible(true);
            }
            else{
                buzzerArray[i].setVisible(false);
            }
        }
    }
    
    public void delayedAction(final String action){
        //wait for 3 seconds then perform action
        timerDelay = 3000;
        System.out.println("DelayedAction: " + action);

        //delete the old actionListener if there is one
        if(delayTimer.getActionListeners().length>0) delayTimer.removeActionListener(delayTimer.getActionListeners()[0]);
        
        delayTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerDelay -= 4000;
                if(timerDelay<=0){
                    //switch delayed actions here
                    switch(action){
                        case "startSong":
                            delayTimer.stop();
                            gameBoard.startSong();
                        break;                        
                        case "joker1":
                            delayTimer.stop();
                            gameBoard.useJoker(1);
                        break;
                        case "joker2":
                            delayTimer.stop();
                            gameBoard.useJoker(2);
                        break;
                        case "joker3":
                            delayTimer.stop();
                            gameBoard.useJoker(3);
                        break;
                    }
                }
            }
        });
        delayTimer.start();
    }
    public void enableBuzzer(int player){
        buzzerActiveArray[player].setVisible(true);
        buzzerJokerArray[player].setVisible(true);
        delayedAction("joker" + player);
    }
    
    public void tooSlowBuzzer(int player){
        buzzerActiveArray[player].setVisible(true);
        buzzerTooSlowArray[player].setVisible(true);
    }
        
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel buzzer1;
    private javax.swing.JLabel buzzer1_active;
    private javax.swing.JLabel buzzer1_count;
    private javax.swing.JLabel buzzer1_joker;
    private javax.swing.JLabel buzzer1_tooslow;
    private javax.swing.JLabel buzzer2;
    private javax.swing.JLabel buzzer2_active;
    private javax.swing.JLabel buzzer2_count;
    private javax.swing.JLabel buzzer2_joker;
    private javax.swing.JLabel buzzer2_tooslow;
    private javax.swing.JLabel buzzer3;
    private javax.swing.JLabel buzzer3_active;
    private javax.swing.JLabel buzzer3_count;
    private javax.swing.JLabel buzzer3_joker;
    private javax.swing.JLabel buzzer3_tooslow;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel remTimePlchldLabel;
    // End of variables declaration//GEN-END:variables
}

