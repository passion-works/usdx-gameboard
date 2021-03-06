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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 *
 * @author amauter
 */
public class GUI extends javax.swing.JFrame {
    public final int countdownSeconds = 7;
    public static GUI instance = null;
    public Timer timer = new Timer(100, null);
    public Timer delayTimer = new Timer(1000, null);
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
    Map<String, JLabel> jokerGameIconLabels = new HashMap<>();
    Map<String, JLabel> jokerGameIconSelectedLabels = new HashMap<>();
    Map<String, JLabel> jokerGameIconChosenLabels = new HashMap<>();
    public JLabel gameTimer = null;
    public JLayeredPane layerJokerGame;
    public JLayeredPane layerMainGame;
    public JLayeredPane layerSettings;
    Map<String, JLabel> activeJokerLabels = new HashMap<>();
    Map<String, JLabel> activeJokerIconLabels = new HashMap<>();
    private int delayTimeShort = 2000;
    private JokerGame jokerGame = null;
    
    
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
        
        layerJokerGame = jokerGameLayer;
        jokerGameLayer.setVisible(false);
        layerMainGame = mainGameLayer;
        mainGameLayer.setVisible(true);
        layerSettings = settingsLayer;
        layerSettings.setVisible(false);
        
      
        gameTimer = game_countdown;
        
        game_play.setText("\uF034");
        game_left.setText("\uF039");
        game_right.setText("\uF03A");
        game_random.setText("\uF071");
        
        game_play_selected.setText("\uF0A1");
        game_left_selected.setText("\uF0A1");
        game_random_selected.setText("\uF0A1");
        game_right_selected.setText("\uF0A1");
        
        game_play_chosen.setText("\uF06C");
        game_left_chosen.setText("\uF06C");
        game_right_chosen.setText("\uF06C");
        game_random_chosen.setText("\uF06C");
        
        jokerGameIconLabels.put("play", game_play);
        jokerGameIconSelectedLabels.put("play", game_play_selected);
        jokerGameIconLabels.put("left", game_left);
        jokerGameIconSelectedLabels.put("left", game_left_selected);
        jokerGameIconLabels.put("right", game_right);
        jokerGameIconSelectedLabels.put("right", game_right_selected);
        jokerGameIconLabels.put("random", game_random);
        jokerGameIconSelectedLabels.put("random", game_random_selected);
        
        jokerGameIconChosenLabels.put("play", game_play_chosen);
        jokerGameIconChosenLabels.put("left", game_left_chosen);
        jokerGameIconChosenLabels.put("right", game_right_chosen);
        jokerGameIconChosenLabels.put("random", game_random_chosen);
        
       
        
        activejoker_play.setText("\uF06C");
        activejoker_nudge.setText("\uF06C");
        activejoker_random.setText("\uF06C");
        activejoker_play_icon.setText("\uF034");
        activejoker_nudge_icon.setText("\uF046");
        activejoker_random_icon.setText("\uF071");
        
        activejoker_play1.setText("\uF06C");
        activejoker_nudge1.setText("\uF06C");
        activejoker_random1.setText("\uF06C");
        activejoker_play_icon1.setText("\uF034");
        activejoker_nudge_icon1.setText("\uF046");
        activejoker_random_icon1.setText("\uF071");
        
        activeJokerLabels.put("play", activejoker_play);
        activeJokerLabels.put("nudge", activejoker_nudge);
        activeJokerLabels.put("random", activejoker_random);
        
        activeJokerIconLabels.put("play", activejoker_play_icon);
        activeJokerIconLabels.put("nudge", activejoker_nudge_icon);
        activeJokerIconLabels.put("random", activejoker_random_icon);
        
    }
    
    public void hideAllJokeGameIcons(){
        for(String thisJoker:jokerGameIconLabels.keySet()){
            jokerGameIconLabels.get(thisJoker).setVisible(false);
        }
    }
    
    public void hideAllJokeGameActiveLabels(){
        for(String thisJoker:jokerGameIconSelectedLabels.keySet()){
            jokerGameIconSelectedLabels.get(thisJoker).setVisible(false);
        }
    }
    
    public void resetAllJokeGameChosenLabels(){
        for(String thisJoker:jokerGameIconSelectedLabels.keySet()){
            jokerGameIconChosenLabels.get(thisJoker).setVisible(false);
            jokerGameIconLabels.get(thisJoker).setForeground(Color.black);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        settingsLayer = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        activejoker_nudge_icon1 = new javax.swing.JLabel();
        activejoker_nudge1 = new javax.swing.JLabel();
        activejoker_play_icon1 = new javax.swing.JLabel();
        activejoker_play1 = new javax.swing.JLabel();
        activejoker_random_icon1 = new javax.swing.JLabel();
        activejoker_random1 = new javax.swing.JLabel();
        sliderPlay = new javax.swing.JSlider();
        sliderNudge = new javax.swing.JSlider();
        sliderRand = new javax.swing.JSlider();
        probPlay = new javax.swing.JTextField();
        probNudge = new javax.swing.JTextField();
        probRand = new javax.swing.JTextField();
        settingsSave = new javax.swing.JButton();
        settingsCancek = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        mainGameLayer = new javax.swing.JLayeredPane();
        activejoker_nudge_icon = new javax.swing.JLabel();
        activejoker_nudge = new javax.swing.JLabel();
        activejoker_play_icon = new javax.swing.JLabel();
        activejoker_play = new javax.swing.JLabel();
        activejoker_random_icon = new javax.swing.JLabel();
        activejoker_random = new javax.swing.JLabel();
        remTimePlchldLabel = new javax.swing.JLabel();
        buzzer1_tooslow = new javax.swing.JLabel();
        buzzer1_joker = new javax.swing.JLabel();
        buzzer1_active = new javax.swing.JLabel();
        buzzer1 = new javax.swing.JLabel();
        buzzer1_count = new javax.swing.JLabel();
        buzzer2_tooslow = new javax.swing.JLabel();
        buzzer2_joker = new javax.swing.JLabel();
        buzzer2_active = new javax.swing.JLabel();
        buzzer2 = new javax.swing.JLabel();
        buzzer2_count = new javax.swing.JLabel();
        buzzer3_tooslow = new javax.swing.JLabel();
        buzzer3_joker = new javax.swing.JLabel();
        buzzer3_active = new javax.swing.JLabel();
        buzzer3 = new javax.swing.JLabel();
        buzzer3_count = new javax.swing.JLabel();
        jokerGameLayer = new javax.swing.JLayeredPane();
        game_countdown = new javax.swing.JLabel();
        game_random_selected = new javax.swing.JLabel();
        game_random = new javax.swing.JLabel();
        game_random_chosen = new javax.swing.JLabel();
        game_right_selected = new javax.swing.JLabel();
        game_right = new javax.swing.JLabel();
        game_right_chosen = new javax.swing.JLabel();
        game_play_selected = new javax.swing.JLabel();
        game_play = new javax.swing.JLabel();
        game_play_chosen = new javax.swing.JLabel();
        game_left_selected = new javax.swing.JLabel();
        game_left = new javax.swing.JLabel();
        game_left_chosen = new javax.swing.JLabel();
        jokerGamePanel = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UltraStar Deluxe Gameboard");
        setMaximumSize(new java.awt.Dimension(1280, 900));
        setMinimumSize(new java.awt.Dimension(1280, 900));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Game settings:");
        settingsLayer.add(jLabel1);
        jLabel1.setBounds(0, 0, 430, 120);

        activejoker_nudge_icon1.setFont(new java.awt.Font("Wingdings 3", 0, 75)); // NOI18N
        activejoker_nudge_icon1.setForeground(new java.awt.Color(255, 255, 255));
        activejoker_nudge_icon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activejoker_nudge_icon1.setText("4");
        settingsLayer.add(activejoker_nudge_icon1);
        activejoker_nudge_icon1.setBounds(170, 220, 130, 115);

        activejoker_nudge1.setFont(new java.awt.Font("Wingdings", 0, 150)); // NOI18N
        activejoker_nudge1.setForeground(new java.awt.Color(255, 153, 0));
        activejoker_nudge1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activejoker_nudge1.setText(" ");
        activejoker_nudge1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        settingsLayer.add(activejoker_nudge1);
        activejoker_nudge1.setBounds(170, 220, 130, 120);

        activejoker_play_icon1.setFont(new java.awt.Font("Webdings", 0, 75)); // NOI18N
        activejoker_play_icon1.setForeground(new java.awt.Color(255, 255, 255));
        activejoker_play_icon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activejoker_play_icon1.setText("4");
        settingsLayer.add(activejoker_play_icon1);
        activejoker_play_icon1.setBounds(10, 220, 130, 115);

        activejoker_play1.setFont(new java.awt.Font("Wingdings", 0, 150)); // NOI18N
        activejoker_play1.setForeground(new java.awt.Color(255, 153, 0));
        activejoker_play1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activejoker_play1.setText(" ");
        activejoker_play1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        settingsLayer.add(activejoker_play1);
        activejoker_play1.setBounds(10, 220, 130, 120);

        activejoker_random_icon1.setFont(new java.awt.Font("Webdings", 0, 75)); // NOI18N
        activejoker_random_icon1.setForeground(new java.awt.Color(255, 255, 255));
        activejoker_random_icon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activejoker_random_icon1.setText("4");
        settingsLayer.add(activejoker_random_icon1);
        activejoker_random_icon1.setBounds(330, 220, 110, 120);

        activejoker_random1.setFont(new java.awt.Font("Wingdings", 0, 150)); // NOI18N
        activejoker_random1.setForeground(new java.awt.Color(255, 153, 0));
        activejoker_random1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activejoker_random1.setText(" ");
        activejoker_random1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        settingsLayer.add(activejoker_random1);
        activejoker_random1.setBounds(320, 220, 130, 120);

        sliderPlay.setOrientation(javax.swing.JSlider.VERTICAL);
        sliderPlay.setPaintLabels(true);
        sliderPlay.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderPlayStateChanged(evt);
            }
        });
        settingsLayer.add(sliderPlay);
        sliderPlay.setBounds(50, 350, 50, 200);

        sliderNudge.setOrientation(javax.swing.JSlider.VERTICAL);
        sliderNudge.setPaintLabels(true);
        sliderNudge.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderNudgeStateChanged(evt);
            }
        });
        settingsLayer.add(sliderNudge);
        sliderNudge.setBounds(210, 350, 50, 200);

        sliderRand.setOrientation(javax.swing.JSlider.VERTICAL);
        sliderRand.setPaintLabels(true);
        sliderRand.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderRandStateChanged(evt);
            }
        });
        settingsLayer.add(sliderRand);
        sliderRand.setBounds(360, 350, 50, 200);

        probPlay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        probPlay.setText("jTextField1");
        probPlay.setFocusable(false);
        settingsLayer.add(probPlay);
        probPlay.setBounds(40, 550, 59, 20);

        probNudge.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        probNudge.setText("jTextField1");
        probNudge.setFocusable(false);
        settingsLayer.add(probNudge);
        probNudge.setBounds(200, 550, 59, 20);

        probRand.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        probRand.setText("jTextField1");
        probRand.setFocusable(false);
        settingsLayer.add(probRand);
        probRand.setBounds(350, 550, 59, 20);

        settingsSave.setText("save");
        settingsSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsSaveActionPerformed(evt);
            }
        });
        settingsLayer.add(settingsSave);
        settingsSave.setBounds(30, 640, 120, 23);

        settingsCancek.setText("cancel");
        settingsCancek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsCancekActionPerformed(evt);
            }
        });
        settingsLayer.add(settingsCancek);
        settingsCancek.setBounds(190, 640, 80, 23);

        jLabel2.setText("<html><h1>Joker probability</h1>Use the sliders below to adjust the probability that a joker is available in a round. (0% means the joker will never be available, 100% that it will be available in each round)</html>");
        settingsLayer.add(jLabel2);
        jLabel2.setBounds(50, 110, 430, 100);

        getContentPane().add(settingsLayer);
        settingsLayer.setBounds(0, 0, 1280, 800);

        mainGameLayer.setMaximumSize(new java.awt.Dimension(1280, 800));
        mainGameLayer.setMinimumSize(new java.awt.Dimension(1280, 800));
        mainGameLayer.setOpaque(true);

        activejoker_nudge_icon.setFont(new java.awt.Font("Wingdings 3", 0, 75)); // NOI18N
        activejoker_nudge_icon.setForeground(new java.awt.Color(255, 255, 255));
        activejoker_nudge_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activejoker_nudge_icon.setText("4");
        mainGameLayer.add(activejoker_nudge_icon);
        activejoker_nudge_icon.setBounds(580, 290, 130, 115);

        activejoker_nudge.setFont(new java.awt.Font("Wingdings", 0, 150)); // NOI18N
        activejoker_nudge.setForeground(new java.awt.Color(255, 153, 0));
        activejoker_nudge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activejoker_nudge.setText(" ");
        activejoker_nudge.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        mainGameLayer.add(activejoker_nudge);
        activejoker_nudge.setBounds(580, 290, 130, 120);

        activejoker_play_icon.setFont(new java.awt.Font("Webdings", 0, 75)); // NOI18N
        activejoker_play_icon.setForeground(new java.awt.Color(255, 255, 255));
        activejoker_play_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activejoker_play_icon.setText("4");
        mainGameLayer.add(activejoker_play_icon);
        activejoker_play_icon.setBounds(420, 290, 130, 115);

        activejoker_play.setFont(new java.awt.Font("Wingdings", 0, 150)); // NOI18N
        activejoker_play.setForeground(new java.awt.Color(255, 153, 0));
        activejoker_play.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activejoker_play.setText(" ");
        activejoker_play.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        mainGameLayer.add(activejoker_play);
        activejoker_play.setBounds(420, 290, 130, 120);

        activejoker_random_icon.setFont(new java.awt.Font("Webdings", 0, 75)); // NOI18N
        activejoker_random_icon.setForeground(new java.awt.Color(255, 255, 255));
        activejoker_random_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activejoker_random_icon.setText("4");
        mainGameLayer.add(activejoker_random_icon);
        activejoker_random_icon.setBounds(731, 293, 130, 115);

        activejoker_random.setFont(new java.awt.Font("Wingdings", 0, 150)); // NOI18N
        activejoker_random.setForeground(new java.awt.Color(255, 153, 0));
        activejoker_random.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        activejoker_random.setText(" ");
        activejoker_random.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        mainGameLayer.add(activejoker_random);
        activejoker_random.setBounds(730, 290, 130, 120);

        remTimePlchldLabel.setFont(new java.awt.Font("Lucida Grande", 0, 300)); // NOI18N
        remTimePlchldLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        remTimePlchldLabel.setText(" ");
        remTimePlchldLabel.setOpaque(true);
        mainGameLayer.add(remTimePlchldLabel);
        remTimePlchldLabel.setBounds(0, 11, 1280, 280);

        buzzer1_tooslow.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        buzzer1_tooslow.setForeground(new java.awt.Color(255, 255, 255));
        buzzer1_tooslow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer1_tooslow.setText("too slow");
        mainGameLayer.add(buzzer1_tooslow);
        buzzer1_tooslow.setBounds(60, 500, 290, 140);

        buzzer1_joker.setFont(new java.awt.Font("Tahoma", 1, 80)); // NOI18N
        buzzer1_joker.setForeground(new java.awt.Color(255, 255, 255));
        buzzer1_joker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer1_joker.setText("JOKER");
        mainGameLayer.add(buzzer1_joker);
        buzzer1_joker.setBounds(60, 500, 290, 140);

        buzzer1_active.setFont(new java.awt.Font("Wingdings", 0, 480)); // NOI18N
        buzzer1_active.setForeground(new java.awt.Color(0, 0, 255));
        buzzer1_active.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer1_active.setText(" ");
        buzzer1_active.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        mainGameLayer.add(buzzer1_active);
        buzzer1_active.setBounds(0, 330, 426, 516);

        buzzer1.setFont(new java.awt.Font("Wingdings", 0, 470)); // NOI18N
        buzzer1.setForeground(new java.awt.Color(0, 0, 255));
        buzzer1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer1.setText(" ");
        mainGameLayer.add(buzzer1);
        buzzer1.setBounds(0, 330, 426, 516);

        buzzer1_count.setFont(new java.awt.Font("Tahoma", 1, 80)); // NOI18N
        buzzer1_count.setForeground(new java.awt.Color(0, 51, 255));
        buzzer1_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer1_count.setText("2");
        mainGameLayer.add(buzzer1_count);
        buzzer1_count.setBounds(170, 500, 70, 140);

        buzzer2_tooslow.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        buzzer2_tooslow.setForeground(new java.awt.Color(255, 255, 255));
        buzzer2_tooslow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer2_tooslow.setText("too slow");
        mainGameLayer.add(buzzer2_tooslow);
        buzzer2_tooslow.setBounds(500, 500, 290, 140);

        buzzer2_joker.setFont(new java.awt.Font("Tahoma", 1, 80)); // NOI18N
        buzzer2_joker.setForeground(new java.awt.Color(255, 255, 255));
        buzzer2_joker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer2_joker.setText("JOKER");
        mainGameLayer.add(buzzer2_joker);
        buzzer2_joker.setBounds(500, 500, 290, 140);

        buzzer2_active.setFont(new java.awt.Font("Wingdings", 0, 480)); // NOI18N
        buzzer2_active.setForeground(new java.awt.Color(255, 0, 51));
        buzzer2_active.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer2_active.setText(" ");
        buzzer2_active.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        mainGameLayer.add(buzzer2_active);
        buzzer2_active.setBounds(430, 330, 426, 516);

        buzzer2.setFont(new java.awt.Font("Wingdings", 0, 470)); // NOI18N
        buzzer2.setForeground(new java.awt.Color(255, 0, 51));
        buzzer2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer2.setText(" ");
        mainGameLayer.add(buzzer2);
        buzzer2.setBounds(430, 330, 426, 516);

        buzzer2_count.setFont(new java.awt.Font("Tahoma", 1, 80)); // NOI18N
        buzzer2_count.setForeground(new java.awt.Color(255, 0, 51));
        buzzer2_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer2_count.setText("3");
        mainGameLayer.add(buzzer2_count);
        buzzer2_count.setBounds(610, 500, 70, 140);

        buzzer3_tooslow.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        buzzer3_tooslow.setForeground(new java.awt.Color(255, 255, 255));
        buzzer3_tooslow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer3_tooslow.setText("too slow");
        mainGameLayer.add(buzzer3_tooslow);
        buzzer3_tooslow.setBounds(920, 500, 290, 140);

        buzzer3_joker.setFont(new java.awt.Font("Tahoma", 1, 80)); // NOI18N
        buzzer3_joker.setForeground(new java.awt.Color(255, 255, 255));
        buzzer3_joker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer3_joker.setText("JOKER");
        mainGameLayer.add(buzzer3_joker);
        buzzer3_joker.setBounds(920, 500, 290, 140);

        buzzer3_active.setFont(new java.awt.Font("Wingdings", 0, 480)); // NOI18N
        buzzer3_active.setForeground(new java.awt.Color(0, 255, 0));
        buzzer3_active.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer3_active.setText(" ");
        buzzer3_active.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        mainGameLayer.add(buzzer3_active);
        buzzer3_active.setBounds(850, 330, 426, 516);

        buzzer3.setFont(new java.awt.Font("Wingdings", 0, 470)); // NOI18N
        buzzer3.setForeground(new java.awt.Color(0, 255, 0));
        buzzer3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer3.setText(" ");
        buzzer3.setToolTipText("");
        mainGameLayer.add(buzzer3);
        buzzer3.setBounds(850, 330, 426, 516);

        buzzer3_count.setFont(new java.awt.Font("Tahoma", 1, 80)); // NOI18N
        buzzer3_count.setForeground(new java.awt.Color(0, 255, 0));
        buzzer3_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buzzer3_count.setText("4");
        mainGameLayer.add(buzzer3_count);
        buzzer3_count.setBounds(1030, 500, 70, 140);

        getContentPane().add(mainGameLayer);
        mainGameLayer.setBounds(0, 0, 1280, 800);

        jokerGameLayer.setMaximumSize(new java.awt.Dimension(1280, 800));
        jokerGameLayer.setMinimumSize(new java.awt.Dimension(1280, 800));
        jokerGameLayer.setOpaque(true);

        game_countdown.setFont(new java.awt.Font("Lucida Grande", 0, 150)); // NOI18N
        game_countdown.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        game_countdown.setText("19");
        game_countdown.setOpaque(true);
        jokerGameLayer.add(game_countdown);
        game_countdown.setBounds(540, 330, 210, 130);

        game_random_selected.setFont(new java.awt.Font("Wingdings", 0, 400)); // NOI18N
        game_random_selected.setForeground(new java.awt.Color(0, 0, 255));
        game_random_selected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        game_random_selected.setText(" ");
        jokerGameLayer.add(game_random_selected);
        game_random_selected.setBounds(440, 370, 410, 516);

        game_random.setFont(new java.awt.Font("Webdings", 0, 200)); // NOI18N
        game_random.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        game_random.setText("4");
        jokerGameLayer.add(game_random);
        game_random.setBounds(520, 490, 250, 280);

        game_random_chosen.setFont(new java.awt.Font("Wingdings", 0, 450)); // NOI18N
        game_random_chosen.setForeground(new java.awt.Color(0, 0, 255));
        game_random_chosen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        game_random_chosen.setText(" ");
        game_random_chosen.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jokerGameLayer.add(game_random_chosen);
        game_random_chosen.setBounds(430, 370, 426, 516);

        game_right_selected.setFont(new java.awt.Font("Wingdings", 0, 400)); // NOI18N
        game_right_selected.setForeground(new java.awt.Color(0, 0, 255));
        game_right_selected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        game_right_selected.setText(" ");
        jokerGameLayer.add(game_right_selected);
        game_right_selected.setBounds(840, 140, 410, 516);

        game_right.setFont(new java.awt.Font("Webdings", 0, 200)); // NOI18N
        game_right.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        game_right.setText("4");
        jokerGameLayer.add(game_right);
        game_right.setBounds(920, 240, 250, 280);

        game_right_chosen.setFont(new java.awt.Font("Wingdings", 0, 450)); // NOI18N
        game_right_chosen.setForeground(new java.awt.Color(0, 0, 255));
        game_right_chosen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        game_right_chosen.setText(" ");
        game_right_chosen.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jokerGameLayer.add(game_right_chosen);
        game_right_chosen.setBounds(830, 140, 426, 516);

        game_play_selected.setFont(new java.awt.Font("Wingdings", 0, 400)); // NOI18N
        game_play_selected.setForeground(new java.awt.Color(0, 0, 255));
        game_play_selected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        game_play_selected.setText(" ");
        jokerGameLayer.add(game_play_selected);
        game_play_selected.setBounds(440, -80, 410, 516);

        game_play.setFont(new java.awt.Font("Webdings", 0, 200)); // NOI18N
        game_play.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        game_play.setText("4");
        jokerGameLayer.add(game_play);
        game_play.setBounds(520, 20, 250, 280);

        game_play_chosen.setFont(new java.awt.Font("Wingdings", 0, 450)); // NOI18N
        game_play_chosen.setForeground(new java.awt.Color(0, 0, 255));
        game_play_chosen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        game_play_chosen.setText(" ");
        game_play_chosen.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jokerGameLayer.add(game_play_chosen);
        game_play_chosen.setBounds(430, -80, 426, 516);

        game_left_selected.setFont(new java.awt.Font("Wingdings", 0, 400)); // NOI18N
        game_left_selected.setForeground(new java.awt.Color(0, 0, 255));
        game_left_selected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        game_left_selected.setText(" ");
        jokerGameLayer.add(game_left_selected);
        game_left_selected.setBounds(-20, 140, 410, 516);

        game_left.setFont(new java.awt.Font("Webdings", 0, 200)); // NOI18N
        game_left.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        game_left.setText("4");
        jokerGameLayer.add(game_left);
        game_left.setBounds(60, 240, 250, 280);

        game_left_chosen.setFont(new java.awt.Font("Wingdings", 0, 450)); // NOI18N
        game_left_chosen.setForeground(new java.awt.Color(0, 0, 255));
        game_left_chosen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        game_left_chosen.setText(" ");
        game_left_chosen.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jokerGameLayer.add(game_left_chosen);
        game_left_chosen.setBounds(-30, 140, 426, 516);

        getContentPane().add(jokerGameLayer);
        jokerGameLayer.setBounds(0, 0, 1280, 800);

        jokerGamePanel.setPreferredSize(new java.awt.Dimension(1280, 800));
        jokerGamePanel.setLayout(null);
        getContentPane().add(jokerGamePanel);
        jokerGamePanel.setBounds(0, 0, 1200, 800);

        mainPanel.setLayout(null);
        getContentPane().add(mainPanel);
        mainPanel.setBounds(0, 0, 1280, 800);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sliderPlayStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderPlayStateChanged
        probPlay.setText(sliderPlay.getValue() + "%");
    }//GEN-LAST:event_sliderPlayStateChanged

    private void sliderNudgeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderNudgeStateChanged
        probNudge.setText(sliderNudge.getValue() + "%");
    }//GEN-LAST:event_sliderNudgeStateChanged

    private void sliderRandStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderRandStateChanged
        probRand.setText(sliderRand.getValue() + "%");
    }//GEN-LAST:event_sliderRandStateChanged

    private void settingsSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsSaveActionPerformed
        jokerGame.setJokerProb("nudge", sliderNudge.getValue());
        jokerGame.setJokerProb("play", sliderPlay.getValue());
        jokerGame.setJokerProb("random", sliderRand.getValue());
        
        settingsLayer.setVisible(false);
        mainGameLayer.setVisible(true);
    }//GEN-LAST:event_settingsSaveActionPerformed

    private void settingsCancekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsCancekActionPerformed
        settingsLayer.setVisible(false);
        mainGameLayer.setVisible(true);
    }//GEN-LAST:event_settingsCancekActionPerformed
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
                    
                    delayedAction("startSong", 3000);
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
    
    public void delayedAction(final String action, int delayTime){
        //wait for 3 seconds then perform action
        timerDelay = delayTime;
        if(action.equals("startSong")) soundPlayer.playCountdownEnd();
        System.out.println("DelayedAction: " + action);

        //delete the old actionListener if there is one
        if(delayTimer.getActionListeners().length>0) delayTimer.removeActionListener(delayTimer.getActionListeners()[0]);
        
        delayTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerDelay -= 1000;
                if(timerDelay<=0){
                    delayTimer.stop();
                    //switch delayed actions here
                    switch(action){
                        case "startSong":
                            gameBoard.startSong();
                        break;                        
                        case "joker1":
                            gameBoard.useJoker(1);
                        break;
                        case "joker2":
                            gameBoard.useJoker(2);
                        break;
                        case "joker3":
                            gameBoard.useJoker(3);
                        break;
                        case "left":
                            gameBoard.nudge("left");
                        break;     
                        case "right":
                            gameBoard.nudge("right");
                        break;     
                        case "random":
                            gameBoard.randomSong(JokerGame.getInstance().currentPlayer);
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
        delayedAction("joker" + player, 3000);
    }
    
    public void tooSlowBuzzer(int player){
        buzzerActiveArray[player].setVisible(true);
        buzzerTooSlowArray[player].setVisible(true);
    }
        
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel activejoker_nudge;
    private javax.swing.JLabel activejoker_nudge1;
    private javax.swing.JLabel activejoker_nudge_icon;
    private javax.swing.JLabel activejoker_nudge_icon1;
    private javax.swing.JLabel activejoker_play;
    private javax.swing.JLabel activejoker_play1;
    private javax.swing.JLabel activejoker_play_icon;
    private javax.swing.JLabel activejoker_play_icon1;
    private javax.swing.JLabel activejoker_random;
    private javax.swing.JLabel activejoker_random1;
    private javax.swing.JLabel activejoker_random_icon;
    private javax.swing.JLabel activejoker_random_icon1;
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
    private javax.swing.JLabel game_countdown;
    private javax.swing.JLabel game_left;
    private javax.swing.JLabel game_left_chosen;
    private javax.swing.JLabel game_left_selected;
    private javax.swing.JLabel game_play;
    private javax.swing.JLabel game_play_chosen;
    private javax.swing.JLabel game_play_selected;
    private javax.swing.JLabel game_random;
    private javax.swing.JLabel game_random_chosen;
    private javax.swing.JLabel game_random_selected;
    private javax.swing.JLabel game_right;
    private javax.swing.JLabel game_right_chosen;
    private javax.swing.JLabel game_right_selected;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jokerGameLayer;
    private javax.swing.JPanel jokerGamePanel;
    private javax.swing.JLayeredPane mainGameLayer;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField probNudge;
    private javax.swing.JTextField probPlay;
    private javax.swing.JTextField probRand;
    private javax.swing.JLabel remTimePlchldLabel;
    private javax.swing.JButton settingsCancek;
    private javax.swing.JLayeredPane settingsLayer;
    private javax.swing.JButton settingsSave;
    private javax.swing.JSlider sliderNudge;
    private javax.swing.JSlider sliderPlay;
    private javax.swing.JSlider sliderRand;
    // End of variables declaration//GEN-END:variables

    public void openSettings() {
        if(timer.isRunning()) timer.stop();
        if(jokerGame==null) jokerGame=JokerGame.getInstance();
        
        sliderNudge.setValue(jokerGame.getJokerProb("nudge"));
        sliderPlay.setValue(jokerGame.getJokerProb("play"));
        sliderRand.setValue(jokerGame.getJokerProb("random"));
        
        settingsLayer.setVisible(true);
        mainGameLayer.setVisible(false);
    }
}

