/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Frame02 extends JFrame {

    public Frame02() {
        add(new Panel02());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
    }

}

class Panel02 extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    private Point puntoFijo;
    private Point puntoVariable;
    private Point puntoReferencia;
    private Point puntoBird;
    private Point puntoReferenciaBird;
    private int distanciaMaxima;
    private int distancia;
    private Timer timer;
    private boolean presionado;
    private Rectangle centro;

    public Panel02() {
        this.puntoFijo = new Point(200, 200);
        this.puntoVariable = new Point(0, 0);
        this.puntoReferencia = new Point(0, 0);
        this.puntoBird = new Point(0, 0);
        this.puntoReferenciaBird = new Point(0, 0);
        this.distanciaMaxima = 50;
        this.distancia = 0;
        this.timer = new Timer(10, this);
        this.timer.start();
        this.presionado = false;
        this.centro = new Rectangle(190, 190, 20, 20);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.setBackground(Color.white);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval((int) this.puntoBird.getX(), (int) this.puntoBird.getY(), 20, 20);
        g.drawRect(190, 190, 20, 20);
        g.drawOval(150, 150, 100, 100);
        g.drawOval(200, 200, 1, 1);

    }

    public int medirDistacia() {
        double distanciaAux = Math.sqrt((((puntoFijo.getX()) - (puntoVariable.getX())) * ((puntoFijo.getX()) - (puntoVariable.getX())))
                + (((puntoFijo.getY()) - (puntoVariable.getY())) * ((puntoFijo.getY()) - (puntoVariable.getY()))));
        int distaciaAux02 = (int) distanciaAux;
        return distaciaAux02;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (presionado) {
            presionado = true;
        } else {
            if (centro.contains(e.getPoint())) {
                presionado = true;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        presionado = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (presionado) {
            this.puntoVariable = e.getPoint();
            this.distancia = medirDistacia();
            if (distancia <= distanciaMaxima) {
                this.puntoBird = e.getPoint();
            } else {

                double yAux = (puntoVariable.getY() - puntoFijo.getY()) / this.distancia;
                double xAux = (puntoVariable.getX() - puntoFijo.getX()) / this.distancia;
                double angulo = Math.asin(yAux);
                double angulo02 = Math.acos(xAux);
                this.puntoReferenciaBird = new Point((int) (Math.cos(angulo02) * distanciaMaxima), (int) (Math.sin(angulo) * distanciaMaxima));
                int x = (int) (this.puntoReferenciaBird.getX() + this.puntoFijo.getX());
                int y = (int) (this.puntoReferenciaBird.getY() + this.puntoFijo.getY());
                this.puntoBird = new Point(x, y);

            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}

public class Resortera02 {

    public static void main(String[] args) {
        Frame02 miFrame = new Frame02();
    }
}
