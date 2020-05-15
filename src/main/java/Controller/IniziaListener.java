/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Calcolo;
import View.Grafico;
import View.Menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

/**
 *
 * @author jacop
 */
public class IniziaListener implements ActionListener {

    private Calcolo c;
    private Menu m;
    private Color colorError;
    private double nTot;
    private double infetti;
    private double sani;
    private double rimossi;
    private double deltaT;
    private double aggiornamentoValori;
    private double alpha;
    private double beta;
    private boolean controllo = true;

    public IniziaListener(Menu m) {
        this.m = m;
        this.colorError = new Color(237, 67, 55);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controllo = true;
        try {
            nTot = Long.parseLong(m.getnTF().getText());
            m.getnTF().setBackground(Color.WHITE);
            if (nTot <= 0) {
                controllo = false;
            }
        } catch (NumberFormatException ex) {
            m.getnTF().setBackground(colorError);
            JOptionPane.showMessageDialog(null, "Per favore inserire un numero intero di popolazione totale!");
            controllo = false;
        }

        try {
            infetti = Long.parseLong(m.getiTF().getText());
            m.getiTF().setBackground(Color.WHITE);
            if (infetti <= 0) {
                controllo = false;
            }
        } catch (NumberFormatException ex) {
            m.getiTF().setBackground(colorError);
            JOptionPane.showMessageDialog(null, "Per favore inserire un numero intero di popolazione sana!");
            controllo = false;
        }

        if (infetti >= nTot) {
            m.getiTF().setBackground(colorError);
            m.getnTF().setBackground(colorError);
            JOptionPane.showMessageDialog(null, "Il numero di infetti NON può essere maggiore o uguale al numero totale della popolazione!");
        }

        if (controllo) {
            sani = nTot - infetti;
            m.getsTF().setText(Double.toString(sani));
            m.getsTF().setBackground(Color.WHITE);
        } else {
            m.getsTF().setBackground(colorError);
        }

        rimossi = 0;
        deltaT = 1;

        try {
            aggiornamentoValori = (Integer) m.getfAggiornamentoComboBox().getSelectedItem();
            m.getAlphaTF().setBackground(Color.WHITE);
        } catch (NumberFormatException ex) {
            m.getfAggiornamentoComboBox().setBackground(colorError);
            JOptionPane.showMessageDialog(null, "È stato riscontrato un errore!");
            controllo = false;
        }

        try {
            alpha = Double.parseDouble(m.getAlphaTF().getText());
            m.getAlphaTF().setBackground(Color.WHITE);
        } catch (Exception ex) {
            m.getAlphaTF().setBackground(colorError);
            JOptionPane.showMessageDialog(null, "Per favore inserire un numero in doppia precisione (BigDecimal) come valore di alpha!");
            controllo = false;
        }

        try {
            beta = Double.parseDouble(m.getBetaTF().getText());
            m.getBetaTF().setBackground(Color.WHITE);
        } catch (Exception ex) {
            m.getBetaTF().setBackground(colorError);
            JOptionPane.showMessageDialog(null, "Per favore inserire un numero in doppia precisione (BigDecimal) come valore di beta!");
            controllo = false;
        }

        double rZero = alpha / beta * sani;
        if (!(rZero > 0.8 && rZero < 5)) {
            controllo = false;
            System.out.println(rZero);
            JOptionPane.showMessageDialog(null, "Il valore di R_ZERO deve essere compreso tra 0,8 e 5!");
        }

        m.getErreZeroTF().setText(Double.toString(rZero));

        boolean saniGrafico = m.getsCB().isSelected();
        boolean infettiGrafico = m.getiCB().isSelected();
        boolean rimossiGrafico = m.getrCB().isSelected();

        if (controllo) {
            double valoriInteri[] = {nTot, infetti, sani, rimossi, deltaT, aggiornamentoValori};
            c = new Calcolo(m, valoriInteri, alpha, beta);
            Grafico g = new Grafico(m, nTot, c.getValori(), saniGrafico, infettiGrafico, rimossiGrafico);
            c.setG(g);
            m.getMainPanel().add(g, BorderLayout.CENTER);
            g.repaint();
        }
    }
}
