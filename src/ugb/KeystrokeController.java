/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ugb;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amauter
 */
public class KeystrokeController {
    public static KeystrokeController instance = null;
    
    public static KeystrokeController getInstance(){
        if(instance == null){
            instance = new KeystrokeController();
        }
        return instance;
    }
    
    public void sendTest(){
        try {
            Process p = Runtime.getRuntime().exec("autoHotkey/keystroke_test.exe");
        } catch (IOException ex) {
            Logger.getLogger(KeystrokeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendKey1(){
        try {
            Process p = Runtime.getRuntime().exec("autoHotkey/key_1.exe");
        } catch (IOException ex) {
            Logger.getLogger(KeystrokeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("sendKey1");
    }
    
    public void sendKey2(){
        try {
            Process p = Runtime.getRuntime().exec("autoHotkey/key_2.exe");
        } catch (IOException ex) {
            Logger.getLogger(KeystrokeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("sendKey2");
    }   
    
    public void sendKey3(){
        try {
            Process p = Runtime.getRuntime().exec("autoHotkey/key_3.exe");
        } catch (IOException ex) {
            Logger.getLogger(KeystrokeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("sendKey3");
    } 
    
    public void sendKeyLeft(){
        try {
            Process p = Runtime.getRuntime().exec("autoHotkey/key_left.exe");
        } catch (IOException ex) {
            Logger.getLogger(KeystrokeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("sendKeyLeft");
    } 
    
    public void sendKeyRight(){
        try {
            Process p = Runtime.getRuntime().exec("autoHotkey/key_right.exe");
        } catch (IOException ex) {
            Logger.getLogger(KeystrokeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("sendKeyRight");
    } 
    
    public void sendKeyEnter(){
        try {
            Process p = Runtime.getRuntime().exec("autoHotkey/key_enter.exe");
        } catch (IOException ex) {
            Logger.getLogger(KeystrokeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("sendKeyEnter");
    } 
        
}
