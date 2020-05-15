/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import View.Menu;
import javax.swing.JOptionPane;

/**
 *
 * @author jacop
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "<html>Programma per il calcolo del Modello SIR di <i><b>Beragnoli</i></b> & <i><b>Gasperini</i></b><br><br>"
                + "- Ogni volta che si intende avviare una nuova simulazione è consigliato <br>"
                + "&nbsp;&nbsp;&nbsp;premere il pulsante <b>RESET</b><br>"
                + "- Per visualizzare il valore di R0 è necessario cliccare sul pulsante <b>AVVIA</b>.</html>", "ATTENZIONE!", JOptionPane.INFORMATION_MESSAGE);
        new Menu();
    }
    
}
