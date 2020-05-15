/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.IniziaListener;
import Controller.ResetListener;
import Model.Calcolo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    private final JPanel legendaPanel;
    private final JPanel legenda;
    private final JPanel panelAlpha;
    private final JPanel panelBeta;
    private final JScrollPane valori;

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
    private BufferedImage iniziaIm;
    private BufferedImage resetIm;

    private JTextArea valoriFinali;

    private final JButton incrementaAlpha;
    private final JButton decrementaAlpha;
    private final JButton incrementaBeta;
    private final JButton decrementaBeta;
    private final JButton inizia;
    private final JButton reset;

    public Menu() {
        super("Modello SIR - Beragnoli & Gasperini");
        inizia = new JButton();
        reset = new JButton();
        try {
            iniziaIm = ImageIO.read(getClass().getResourceAsStream("/button_inizia.png"));
            resetIm = ImageIO.read(getClass().getResourceAsStream("/button_reset.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Impossibile caricare le immagini!");
            inizia.setText("Inizia");
            reset.setText("Reset");
        }

        mainPanel = new JPanel(new BorderLayout());
        titoloPanel = new JPanel(new GridLayout(2, 1));
        inserimentoParametriPanel = new JPanel(new BorderLayout());
        parametriPanel = new JPanel(new GridLayout(10, 2));
        tempoPanel = new JPanel(new GridLayout(1, 2));
        tempoPanelDue = new JPanel(new GridLayout(1, 2));
        graficoTesto = new JPanel();
        graficiCheckLabel = new JPanel(new GridLayout(1, 3));
        iniziaResetPanel = new JPanel(new GridLayout(1, 2));
        legendaPanel = new JPanel(new GridLayout(3, 2));
        panelAlpha = new JPanel(new BorderLayout());
        panelBeta = new JPanel(new BorderLayout());
        legenda = new JPanel(new BorderLayout());
        //grafico = new Grafico(this,60000000);
        valori = new JScrollPane();
        valori.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        inserimentoParametriPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        getContentPane().add(mainPanel);

        valoriFinali = new JTextArea();

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
        alphaTF = new JTextField("1E-8");
        betaTF = new JTextField("0.24");
        erreZeroTF = new JTextField(Double.toString(Double.parseDouble(alphaTF.getText()) * Integer.parseInt(sTF.getText()) / Double.parseDouble(betaTF.getText())));

        titolo = new JLabel("Modello SIR");
        titolo.setFont(new Font("Georgia", Font.BOLD, 25));
        titolo.setHorizontalAlignment(JLabel.CENTER);
        titolo.setForeground(new Color(51, 102, 153));

        credits = new JLabel("programma di Beragnoli & Gasperini");
        credits.setFont(new Font("Courier", Font.ITALIC, 14));
        credits.setHorizontalAlignment(JLabel.CENTER);

        nLabel = new JLabel("Totale (N):");
        nLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        //nLabel.setForeground(new Color(0,0,0));
        nLabel.setForeground(new Color(51, 102, 153));

        iLabel = new JLabel("Infetti (I):");
        iLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        iLabel.setForeground(new Color(51, 102, 153));

        sLabel = new JLabel("Suscettibili (S):");
        sLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        sLabel.setForeground(new Color(51, 102, 153));

        rLabel = new JLabel("Rimossi (R): ");
        rLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        rLabel.setForeground(new Color(51, 102, 153));

        deltaTLabel = new JLabel("Δt [Giorni]:");
        deltaTLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        deltaTLabel.setForeground(new Color(51, 102, 153));

        fAggiornamentoLabel = new JLabel("Aggiornamento valori [Giorni]:");
        fAggiornamentoLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        fAggiornamentoLabel.setForeground(new Color(51, 102, 153));

        alphaLabel = new JLabel("Alpha (α):");
        alphaLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        alphaLabel.setForeground(new Color(51, 102, 153));

        betaLabel = new JLabel("Beta (β):");
        betaLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        betaLabel.setForeground(new Color(51, 102, 153));

        graficoLabel = new JLabel("Disegno grafici");
        graficoLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        graficoLabel.setForeground(new Color(51, 102, 153));

        erreZeroLabel = new JLabel("R0:");
        erreZeroLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        erreZeroLabel.setForeground(new Color(51, 102, 153));

        erreZeroTF.setEditable(false);

        incrementaAlpha = new JButton("↑");
        decrementaAlpha = new JButton("↓");
        incrementaBeta = new JButton("↑");
        decrementaBeta = new JButton("↓");

        mainPanel.add(titoloPanel, BorderLayout.NORTH);
        mainPanel.add(inserimentoParametriPanel, BorderLayout.WEST);
        mainPanel.add(legenda, BorderLayout.EAST);
        legenda.add(legendaPanel, BorderLayout.NORTH);
        legenda.add(valori, BorderLayout.CENTER);
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
        parametriPanel.add(panelAlpha);
        parametriPanel.add(betaLabel);
        parametriPanel.add(panelBeta);
        parametriPanel.add(erreZeroLabel);
        parametriPanel.add(erreZeroTF);
        panelAlpha.add(alphaTF, BorderLayout.CENTER);
        JPanel grid = new JPanel(new GridLayout(2, 1));
        JPanel gridBeta = new JPanel(new GridLayout(2, 1));
        panelAlpha.add(grid, BorderLayout.EAST);
        grid.add(incrementaAlpha);
        grid.add(decrementaAlpha);

        panelBeta.add(betaTF, BorderLayout.CENTER);
        panelBeta.add(gridBeta, BorderLayout.EAST);
        gridBeta.add(incrementaBeta);
        gridBeta.add(decrementaBeta);

        legendaPanel.add(new JTextArea("SANI"));
        JTextArea redArea = new JTextArea();
        redArea.setBackground(Color.red);
        JTextArea greenArea = new JTextArea();
        greenArea.setBackground(Color.GREEN);
        JTextArea blackArea = new JTextArea();
        blackArea.setBackground(Color.BLACK);
        legendaPanel.add(greenArea);
        legendaPanel.add(new JTextArea("INFETTI"));
        legendaPanel.add(redArea);
        legendaPanel.add(new JTextArea("RIMOSSI"));
        legendaPanel.add(blackArea);
        valori.setViewportView(valoriFinali);

        incrementaAlpha.addActionListener(new IncrementaAlphaAL());
        decrementaAlpha.addActionListener(new DecrementaAlphaAL());
        incrementaBeta.addActionListener(new IncrementaBetaAL());
        decrementaBeta.addActionListener(new DecrementaBetaAL());
        inizia.addActionListener(new IniziaListener(this));
        reset.addActionListener(new ResetListener(this));

        inizia.setIcon(new ImageIcon(iniziaIm));
        inizia.setBorder(BorderFactory.createEmptyBorder());
        inizia.setContentAreaFilled(false);
        reset.setIcon(new ImageIcon(resetIm));
        reset.setBorder(BorderFactory.createEmptyBorder());
        reset.setContentAreaFilled(false);

        setVisible(true);
        setBounds(0, 0, 1900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void aggiornaView(double[] valoriInteri, double alpha, double beta, double rZero) {
        nTF.setText(Integer.toString((int) valoriInteri[Calcolo.INDICE_N]));
        iTF.setText(Integer.toString((int) valoriInteri[Calcolo.INDICE_I]));
        sTF.setText(Integer.toString((int) valoriInteri[Calcolo.INDICE_S]));
        rTF.setText(Integer.toString((int) valoriInteri[Calcolo.INDICE_R]));
        deltaTTF.setText(Integer.toString((int) valoriInteri[Calcolo.INDICE_DELTAT]));
        parametriPanel.revalidate();
        parametriPanel.repaint();
    }

    private class IncrementaAlphaAL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            double incremento = 1E-8;
            double nuovoValoreAlpha = Double.parseDouble(alphaTF.getText()) + incremento;
            try {
                double rZeroNuovo = nuovoValoreAlpha / Double.parseDouble(betaTF.getText()) * Integer.parseInt(sTF.getText());

                if (rZeroNuovo >= 0.8 && rZeroNuovo <= 5) {
                    alphaTF.setText(Double.toString(nuovoValoreAlpha));
                    erreZeroTF.setText(Double.toString(rZeroNuovo));
                }
            } catch (NumberFormatException ex) {
            }
        }
    }

    private class DecrementaAlphaAL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            double incremento = 1E-8;
            try {
                double nuovoValoreAlpha = Double.parseDouble(alphaTF.getText()) - incremento;
                if (nuovoValoreAlpha < 1E-8) {
                    alphaTF.setText(Double.toString(1E-8));
                    return;
                }
                double rZeroNuovo = nuovoValoreAlpha / Double.parseDouble(betaTF.getText()) * Integer.parseInt(sTF.getText());
                if (rZeroNuovo >= 0.8 && rZeroNuovo <= 5) {
                    alphaTF.setText(Double.toString(nuovoValoreAlpha));
                    erreZeroTF.setText(Double.toString(rZeroNuovo));
                }
            } catch (NumberFormatException ex) {
            }
        }

    }

    private class IncrementaBetaAL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            double incremento = 0.01;
            try {
                double nuovoValoreBeta = Double.parseDouble(betaTF.getText()) + incremento;
                if (nuovoValoreBeta < 0) {
                    alphaTF.setText(Double.toString(0.00));
                    return;
                }
                double rZeroNuovo = Double.parseDouble(alphaTF.getText()) / nuovoValoreBeta * Integer.parseInt(sTF.getText());
                if (rZeroNuovo >= 0.8 && rZeroNuovo <= 5) {
                    betaTF.setText(Double.toString(nuovoValoreBeta));
                    erreZeroTF.setText(Double.toString(rZeroNuovo));
                }
            } catch (NumberFormatException ex) {
            }
        }

    }

    private class DecrementaBetaAL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            double incremento = 0.01;
            try {
                double nuovoValoreBeta = Double.parseDouble(betaTF.getText()) - incremento;
                if (nuovoValoreBeta < 0) {
                    alphaTF.setText(Double.toString(0.00));
                    return;
                }
                double rZeroNuovo = Double.parseDouble(alphaTF.getText()) / nuovoValoreBeta * Integer.parseInt(sTF.getText());
                if (rZeroNuovo >= 0.8 && rZeroNuovo <= 5) {
                    betaTF.setText(Double.toString(nuovoValoreBeta));
                    erreZeroTF.setText(Double.toString(rZeroNuovo));
                }
            } catch (NumberFormatException ex) {
            }
        }

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getTitoloPanel() {
        return titoloPanel;
    }

    public JPanel getInserimentoParametriPanel() {
        return inserimentoParametriPanel;
    }

    public JPanel getParametriPanel() {
        return parametriPanel;
    }

    public JPanel getTempoPanel() {
        return tempoPanel;
    }

    public JPanel getTempoPanelDue() {
        return tempoPanelDue;
    }

    public JPanel getGraficoTesto() {
        return graficoTesto;
    }

    public JPanel getGraficiCheckLabel() {
        return graficiCheckLabel;
    }

    public JPanel getIniziaResetPanel() {
        return iniziaResetPanel;
    }

    public JLabel getTitolo() {
        return titolo;
    }

    public JLabel getCredits() {
        return credits;
    }

    public JLabel getnLabel() {
        return nLabel;
    }

    public JLabel getiLabel() {
        return iLabel;
    }

    public JLabel getsLabel() {
        return sLabel;
    }

    public JLabel getrLabel() {
        return rLabel;
    }

    public JLabel getDeltaTLabel() {
        return deltaTLabel;
    }

    public JLabel getfAggiornamentoLabel() {
        return fAggiornamentoLabel;
    }

    public JLabel getAlphaLabel() {
        return alphaLabel;
    }

    public JLabel getBetaLabel() {
        return betaLabel;
    }

    public JLabel getGraficoLabel() {
        return graficoLabel;
    }

    public JLabel getErreZeroLabel() {
        return erreZeroLabel;
    }

    public JButton getInizia() {
        return inizia;
    }

    public JButton getReset() {
        return reset;
    }

    public JComboBox<Integer> getfAggiornamentoComboBox() {
        return fAggiornamentoComboBox;
    }

    public void setfAggiornamentoComboBox(JComboBox<Integer> fAggiornamentoComboBox) {
        this.fAggiornamentoComboBox = fAggiornamentoComboBox;
    }

    public Integer[] getValoriAggiornamento() {
        return valoriAggiornamento;
    }

    public void setValoriAggiornamento(Integer[] valoriAggiornamento) {
        this.valoriAggiornamento = valoriAggiornamento;
    }

    public JCheckBox getiCB() {
        return iCB;
    }

    public void setiCB(JCheckBox iCB) {
        this.iCB = iCB;
    }

    public JCheckBox getsCB() {
        return sCB;
    }

    public void setsCB(JCheckBox sCB) {
        this.sCB = sCB;
    }

    public JCheckBox getrCB() {
        return rCB;
    }

    public void setrCB(JCheckBox rCB) {
        this.rCB = rCB;
    }

    public JTextField getnTF() {
        return nTF;
    }

    public void setnTF(JTextField nTF) {
        this.nTF = nTF;
    }

    public JTextField getiTF() {
        return iTF;
    }

    public void setiTF(JTextField iTF) {
        this.iTF = iTF;
    }

    public JTextField getsTF() {
        return sTF;
    }

    public void setsTF(JTextField sTF) {
        this.sTF = sTF;
    }

    public JTextField getrTF() {
        return rTF;
    }

    public void setrTF(JTextField rTF) {
        this.rTF = rTF;
    }

    public JTextField getDeltaTTF() {
        return deltaTTF;
    }

    public void setDeltaTTF(JTextField deltaTTF) {
        this.deltaTTF = deltaTTF;
    }

    public JTextField getAlphaTF() {
        return alphaTF;
    }

    public void setAlphaTF(JTextField alphaTF) {
        this.alphaTF = alphaTF;
    }

    public JTextField getBetaTF() {
        return betaTF;
    }

    public void setBetaTF(JTextField betaTF) {
        this.betaTF = betaTF;
    }

    public JTextField getErreZeroTF() {
        return erreZeroTF;
    }

    public void setErreZeroTF(JTextField erreZeroTF) {
        this.erreZeroTF = erreZeroTF;
    }

    public BufferedImage getIniziaIm() {
        return iniziaIm;
    }

    public void setIniziaIm(BufferedImage iniziaIm) {
        this.iniziaIm = iniziaIm;
    }

    public BufferedImage getResetIm() {
        return resetIm;
    }

    public void setResetIm(BufferedImage resetIm) {
        this.resetIm = resetIm;
    }

    public JTextArea getValoriFinali() {
        return valoriFinali;
    }

    public void setValoriFinali(JTextArea valoriFinali) {
        this.valoriFinali = valoriFinali;
    }
}
