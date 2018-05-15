/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto1;

import GUI.inicioSesion;
import javax.swing.UIManager;

/**
 *
 * @author Caty
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            com.jtattoo.plaf.noire.NoireLookAndFeel.setTheme("Default", "INSERT YOUR LICENSE KEY HERE", "");                        
            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            inicioSesion i = new inicioSesion();
            i.setVisible(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
