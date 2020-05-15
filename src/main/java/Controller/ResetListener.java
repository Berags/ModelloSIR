/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import View.Menu;

/**
 *
 * @author Administrator
 */
public class ResetListener implements ActionListener {
    private Menu m;
    
    public ResetListener(Menu m) {
        this.m = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        m.dispose();
        m = null;
        m = new Menu();
//        m.getnTF().setText("60000000");
//        m.getiTF().setText("100");
//        m.getsTF().setText(Integer.toString(Integer.parseInt(m.getnTF().getText()) - Integer.parseInt(m.getiTF().getText())));
//        m.getsTF().setEditable(false);
//        m.getrTF().setText("0");
//        m.getrTF().setEditable(false);
//        m.getDeltaTTF().setText("1");
//        m.getDeltaTTF().setEditable(false);
//        m.getAlphaTF().setText("1E-8");
//        m.getBetaTF().setText("0.24");
//        m.getErreZeroTF().setText(Double.toString(Double.parseDouble(m.getAlphaTF().getText()) * Integer.parseInt(m.getsTF().getText()) / Double.parseDouble(m.getBetaTF().getText())));
    }

}
