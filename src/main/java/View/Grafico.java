package View;

import Model.Calcolo;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Grafico extends JPanel {

    private Menu m;
    private Calcolo c;
    private Graphics2D g2;
    private double uX;
    private double uY;
    private double Ymax;
    private double Ymin = 0;
    private boolean sani;
    private boolean infetti;
    private boolean rimossi;
    private ArrayList<Double> valori;
    private ArrayList<Double> valoriSani;
    private ArrayList<Double> valoriRimossi;
    private int tempo = 300;

    public Grafico() {
    }

    public Grafico(Menu m, double Ymax, ArrayList<Double> valori, boolean sani, boolean infetti, boolean rimossi) {
        this.m = m;
        this.valori = valori;
        this.c = c;
        this.Ymax = Ymax;
        this.sani = sani;
        this.infetti = infetti;
        this.rimossi = rimossi;
    }

    public int trasformaX(double x) {
        return (int) ((x) * uX);
    }

    public int trasformaX(int x) {
        return (int) ((x) * uX);
    }

    public int trasformaY(double y) {
        return (int) ((Ymax - y) * uY);
    }

    public int trasformaY(int y) {
        return (int) ((Ymax - y) * uY);
    }

    public void disegnaAssi() {
        g2.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                if (i % 40 == 0 && j % 30 == 0) {
                    g2.drawRect(i, j, 40, 30);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) { //per disegnare grafico
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        this.g2 = (Graphics2D) g;
        uX = this.getWidth() / tempo;
        uY = this.getHeight() / (Ymax - Ymin);
        disegnaAssi();
        disegnaPunti();
    }

    public void disegnaPunti() {
        try {
            for (int i = 1; i < valori.size(); i++) {
                if (infetti) {
                    g2.setStroke(new BasicStroke(3));
                    g2.setColor(Color.red);
                    g2.drawLine(trasformaX(i - 1), trasformaY(valori.get(i - 1)), trasformaX(i), trasformaY(valori.get(i)));
                }
                if (sani) {
                    g2.setColor(Color.GREEN);
                    g2.drawLine(trasformaX(i - 1), trasformaY(valoriSani.get(i - 1)), trasformaX(i), trasformaY(valoriSani.get(i)));
                }
                if (rimossi) {
                    g2.setColor(Color.BLACK);
                    g2.setStroke(new BasicStroke(2));
                    g2.drawLine(trasformaX(i - 1), trasformaY(valoriRimossi.get(i - 1)), trasformaX(i), trasformaY(valoriRimossi.get(i)));
                }

                if (i - 10 == tempo) {
                    this.repaint();
                    return;
                }
            }
        } catch (NullPointerException ex) {

        }

        this.repaint();
    }

    public double getYmin() {
        return Ymin;
    }

    public void setYmin(double Ymin) {
        this.Ymin = Ymin;
    }

    public ArrayList<Double> getValori() {
        return valori;
    }

    public void setValori(ArrayList<Double> valori) {
        this.valori = valori;
    }

    public Menu getM() {
        return m;
    }

    public void setM(Menu m) {
        this.m = m;
    }

    public Calcolo getC() {
        return c;
    }

    public void setC(Calcolo c) {
        this.c = c;
    }

    public Graphics2D getG2() {
        return g2;
    }

    public void setG2(Graphics2D g2) {
        this.g2 = g2;
    }

    public double getuX() {
        return uX;
    }

    public void setuX(double uX) {
        this.uX = uX;
    }

    public double getuY() {
        return uY;
    }

    public void setuY(double uY) {
        this.uY = uY;
    }

    public double getYmax() {
        return Ymax;
    }

    public void setYmax(double Ymax) {
        this.Ymax = Ymax;
    }

    public ArrayList<Double> getValoriSani() {
        return valoriSani;
    }

    public void setValoriSani(ArrayList<Double> valoriSani) {
        this.valoriSani = valoriSani;
    }

    public ArrayList<Double> getValoriRimossi() {
        return valoriRimossi;
    }

    public void setValoriRimossi(ArrayList<Double> valoriRimossi) {
        this.valoriRimossi = valoriRimossi;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

}
