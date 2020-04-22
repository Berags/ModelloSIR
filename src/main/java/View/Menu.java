/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author jacop
 */
public class Menu extends JFrame {
    private final JPanel mainPanel;
    private final JPanel titoloPanel;
    private final JPanel inserimentoParametriPanel;
    private final JPanel parametriPanel;
    private final JPanel tempoPanel;
    private final JPanel tempoPanelDue;
    private final JPanel graficoTesto;
    private final JPanel graficiCheckLabel;
    private final JPanel iniziaResetPanel;
    
    private final JLabel titolo;
    private final JLabel credits;
    private final JLabel nLabel;
    private final JLabel iLabel;
    private final JLabel sLabel;
    private final JLabel rLabel;
    private final JLabel deltaTLabel;
    private final JLabel fAggiornamentoLabel;
    private final JLabel alphaLabel;
    private final JLabel betaLabel;
    private final JLabel graficoLabel;
    private final JLabel erreZeroLabel;
    
    private JComboBox<Integer> fAggiornamentoComboBox;
    Integer valoriAggiornamento[] = {10, 25, 50, 100};
    
    private JCheckBox iCB;
    private JCheckBox sCB;
    private JCheckBox rCB;
    
    private JTextField nTF;
    private JTextField iTF;
    private JTextField sTF;
    private JTextField rTF;
    private JTextField deltaTTF;
    private JTextField alphaTF;
    private JTextField betaTF;
    private JTextField erreZeroTF;
    
    private final JButton inizia;
    private final JButton reset;
    
    public Menu() {
        super("Modello SIR");
        
        mainPanel = new JPanel(new BorderLayout());
        titoloPanel = new JPanel(new GridLayout(2, 1));
        inserimentoParametriPanel = new JPanel(new BorderLayout());
        parametriPanel = new JPanel(new GridLayout(10, 2));
        tempoPanel = new JPanel(new GridLayout(1, 2));
        tempoPanelDue = new JPanel(new GridLayout(1, 2));
        graficoTesto = new JPanel();
        graficiCheckLabel = new JPanel(new GridLayout(1, 3));
        iniziaResetPanel = new JPanel(new GridLayout(1, 2));
        inserimentoParametriPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getContentPane().add(mainPanel);
        
        fAggiornamentoComboBox = new JComboBox<>(valoriAggiornamento);
        
        iCB = new JCheckBox("Infetti (I)");
        sCB = new JCheckBox("Sani (S)");
        rCB = new JCheckBox("Rimossi (R)");
        
        nTF = new JTextField("60000000");
        iTF = new JTextField("100");
        sTF = new JTextField(Integer.toString(Integer.parseInt(nTF.getText()) - Integer.parseInt(iTF.getText())));
        sTF.setEditable(false);
        rTF = new JTextField("0");
        rTF.setEditable(false);
        deltaTTF = new JTextField("1");
        deltaTTF.setEditable(false);
        alphaTF = new JTextField("0.00000001‬");
        betaTF = new JTextField("0.24‬");
        erreZeroTF = new JTextField("2.5");
        
        titolo = new JLabel("Modello SIR");
        titolo.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 24));
        titolo.setHorizontalAlignment(JLabel.CENTER);
        titolo.setForeground(new Color(102, 0, 204));
        credits = new JLabel("programma di Beragnoli & Gasperini");
        credits.setFont(new Font("Consolas", Font.ITALIC, 14));
        credits.setHorizontalAlignment(JLabel.CENTER);
        nLabel = new JLabel("Totale (N):");
        iLabel = new JLabel("Infetti (I):");
        sLabel = new JLabel("Sani (S):");
        rLabel = new JLabel("Rimossi (R): ");
        deltaTLabel = new JLabel("Δt [Giorni]:");
        fAggiornamentoLabel = new JLabel("Aggiornamento valori [Giorni]:");
        alphaLabel = new JLabel("Alpha (α):");
        betaLabel = new JLabel("Beta (β):");
        graficoLabel = new JLabel("Disegno grafici");
        erreZeroLabel = new JLabel("R0:");
        erreZeroTF.setEditable(false);
        
        inizia = new JButton("Inizia");
        reset = new JButton("RESET");
        
        mainPanel.add(titoloPanel, BorderLayout.NORTH);
        mainPanel.add(inserimentoParametriPanel, BorderLayout.WEST);
        inserimentoParametriPanel.add(iniziaResetPanel, BorderLayout.SOUTH);
        inserimentoParametriPanel.add(parametriPanel, BorderLayout.CENTER);
        titoloPanel.add(titolo);
        titoloPanel.add(credits);
        iniziaResetPanel.add(reset);
        iniziaResetPanel.add(inizia);
        parametriPanel.add(nLabel);
        parametriPanel.add(nTF);
        parametriPanel.add(iLabel);
        parametriPanel.add(iTF);
        parametriPanel.add(sLabel);
        parametriPanel.add(sTF);
        parametriPanel.add(rLabel);
        parametriPanel.add(rTF);
        parametriPanel.add(deltaTLabel);
        parametriPanel.add(deltaTTF);
        parametriPanel.add(fAggiornamentoLabel);
        parametriPanel.add(fAggiornamentoComboBox);
        parametriPanel.add(graficoLabel);
        parametriPanel.add(graficiCheckLabel);
        graficiCheckLabel.add(iCB);
        graficiCheckLabel.add(sCB);
        graficiCheckLabel.add(rCB);
        parametriPanel.add(alphaLabel);
        parametriPanel.add(alphaTF);
        parametriPanel.add(betaLabel);
        parametriPanel.add(betaTF);
        parametriPanel.add(erreZeroLabel);
        parametriPanel.add(erreZeroTF);
        
        setVisible(true);
        setBounds(400, 400, 1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
