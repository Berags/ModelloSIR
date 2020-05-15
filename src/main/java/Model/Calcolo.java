/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.Grafico;
import View.Menu;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jacop
 */
public class Calcolo implements Runnable {

    public static final int INDICE_N = 0;
    public static final int INDICE_I = 1;
    public static final int INDICE_S = 2;
    public static final int INDICE_R = 3;
    public static final int INDICE_DELTAT = 4;
    public static final int INDICE_AGGIORNAMENTO = 5;
    private double nTot;
    private double infetti;
    private double sani;
    private double rimossi;
    private double deltaT;
    private double aggiornamentoValori;
    private double alpha;
    private double beta;
    private double rZero;
    private double[] valoriInteri;
    private Menu m;
    private Thread t;
    private Grafico g;
    private ArrayList<Double> valori;
    private ArrayList<Double> valoriRimossi;
    private ArrayList<Double> valoriSani;

    public Calcolo(Menu m, double[] valoriInteri, double alpha, double beta) {
        this.m = m;
        this.t = new Thread(this);
        this.nTot = valoriInteri[INDICE_N];
        this.infetti = valoriInteri[INDICE_I];
        this.sani = valoriInteri[INDICE_S];
        this.rimossi = valoriInteri[INDICE_R];
        this.deltaT = valoriInteri[INDICE_DELTAT];
        this.aggiornamentoValori = valoriInteri[INDICE_AGGIORNAMENTO];
        this.alpha = alpha;
        this.beta = beta;
        this.valoriInteri = valoriInteri;
        m.aggiornaView(valoriInteri, alpha, beta, rZero);
        t.start();
    }

    private void updateValori() {
        valoriInteri[INDICE_I] = this.infetti;
        valoriInteri[INDICE_S] = this.sani;
        valoriInteri[INDICE_R] = this.rimossi;
        valoriInteri[INDICE_DELTAT] = this.deltaT;
        valoriInteri[INDICE_AGGIORNAMENTO] = this.aggiornamentoValori;
    }

    @Override
    public void run() {
        valori = new ArrayList<>();
        ArrayList<Double> valoriRimossi = new ArrayList<>();
        ArrayList<Double> valoriSani = new ArrayList<>();

        double saniP, infettiP, rimossiP;

        boolean aggiornamento = true;
        double saniInfinito = 0, rimossiInfinito = 0, maxInfetti = infetti, tMax = 0;
        m.getErreZeroTF().setText(Double.toString(Double.parseDouble(m.getAlphaTF().getText()) * Integer.parseInt(m.getsTF().getText()) / Double.parseDouble(m.getBetaTF().getText())));

        for (int i = 1; i < 1000; i++) {
            if (i == 1) {
                double saniUno = sani, infettiUno = infetti, rimossiUno = rimossi;
                int tempo = 0, j = 0, tGrafico = 300;
                do {
                    saniP = saniUno;
                    infettiP = infettiUno;
                    rimossiP = rimossiUno;
                    saniUno = (saniP - (alpha * infettiP * saniP * deltaT));
                    infettiUno = (infettiP + (alpha * infettiP * saniP * deltaT) - (beta * infettiP * deltaT));
                    rimossiUno = (rimossiP + (beta * infettiP * deltaT));
                    tempo++;
                    if ((int) infettiUno == 0) {
                        j++;
                    } else {
                        j = 0;
                    }
                } while (j != 9);
                tGrafico = tempo;
                g.setTempo(tGrafico);
            }
            
            saniP = sani;
            infettiP = infetti;
            rimossiP = rimossi;
            sani = (saniP - (alpha * infettiP * saniP * deltaT));
            infetti = (infettiP + (alpha * infettiP * saniP * deltaT) - (beta * infettiP * deltaT));
            rimossi = (rimossiP + (beta * infettiP * deltaT));
            valori.add(infetti);
            valoriSani.add(sani);
            valoriRimossi.add(rimossi);

            m.getAlphaTF().setText(Double.toString(alpha));
            m.getBetaTF().setText(Double.toString(beta));
            m.getValoriFinali().setText(m.getValoriFinali().getText() + "\n" + "T" + i + "-> S: " + (int) sani + " - I: " + (int) infetti + " - R: " + (int) rimossi);
            if (i % aggiornamentoValori == 0 && aggiornamento) {
                updateValori();
                m.aggiornaView(valoriInteri, alpha, beta, rZero);
                g.setValori(valori);
                g.setValoriSani(valoriSani);
                g.setValoriRimossi(valoriRimossi);
                try {
                    t.sleep(700);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Calcolo.class.getName()).log(Level.SEVERE, null, ex);
                }
                g.revalidate();
                g.repaint();
            }

            if (infetti > maxInfetti) {
                maxInfetti = infetti;
                tMax = i;
            }

            if (infetti < 1 || infetti <= 0) {
                saniInfinito = Math.round(sani);
                rimossiInfinito = Math.round(rimossi);
                aggiornamento = false;
                updateValori();
                m.aggiornaView(valoriInteri, alpha, beta, rZero);
            }
        }

        if (m.isShowing()) {
            JOptionPane.showMessageDialog(null, "<html><b>Max Infetti</b>: " + (int) maxInfetti + "<br><b>T_Max</b>: " + tMax + "<br><b>Sani Infinito</b>: " + (int) saniInfinito + "<br><b>Rimossi Infinito</b>: " + (int) rimossiInfinito,
                    "Simulazione completata!", JOptionPane.INFORMATION_MESSAGE);
        }

        /*
            Adesso abbiamo i valori anche di tMAX, maxInfetti e i saniInfinito e rimossiInfinito
         */
    }

    public double getnTot() {
        return nTot;
    }

    public void setnTot(double nTot) {
        this.nTot = nTot;
    }

    public double getInfetti() {
        return infetti;
    }

    public void setInfetti(double infetti) {
        this.infetti = infetti;
    }

    public double getSani() {
        return sani;
    }

    public void setSani(double sani) {
        this.sani = sani;
    }

    public double getRimossi() {
        return rimossi;
    }

    public void setRimossi(double rimossi) {
        this.rimossi = rimossi;
    }

    public double getDeltaT() {
        return deltaT;
    }

    public void setDeltaT(double deltaT) {
        this.deltaT = deltaT;
    }

    public double getAggiornamentoValori() {
        return aggiornamentoValori;
    }

    public void setAggiornamentoValori(double aggiornamentoValori) {
        this.aggiornamentoValori = aggiornamentoValori;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getrZero() {
        return rZero;
    }

    public void setrZero(double rZero) {
        this.rZero = rZero;
    }

    public double[] getValoriInteri() {
        return valoriInteri;
    }

    public void setValoriInteri(double[] valoriInteri) {
        this.valoriInteri = valoriInteri;
    }

    public Menu getM() {
        return m;
    }

    public void setM(Menu m) {
        this.m = m;
    }

    public Thread getT() {
        return t;
    }

    public void setT(Thread t) {
        this.t = t;
    }

    public Grafico getG() {
        return g;
    }

    public void setG(Grafico g) {
        this.g = g;
    }

    public ArrayList<Double> getValori() {
        return valori;
    }

    public void setValori(ArrayList<Double> valori) {
        this.valori = valori;
    }

}
