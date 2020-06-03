package Pruebas;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel; 
import javax.swing.Timer;


class Frame03 extends JFrame{
    public Frame03() {
    add(new Panel03());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setSize(800, 600);
    }
}

class Panel03 extends JPanel implements ActionListener{
    private int xRef;
    private int yRef;
    private double tiempo;
    private Timer timer;
    
    public Panel03() {
        this.xRef = 200;
        this.yRef = 200;
        this.tiempo= 0;
        this.timer = new Timer(10, this);
        this.timer.start();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval(xRef, yRef, 50, 50);
        g.drawLine(0, 200, 1000, 200);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int vo =80;
        double angulo = (120*Math.PI*2)/360;
        tiempo = tiempo + 0.03;
        xRef = (int)(vo * Math.cos(angulo)* tiempo);
        xRef = xRef+200;
        yRef = (int)((vo* Math.sin(angulo)*tiempo)-(9.8 *( (tiempo*tiempo)/2)));
        yRef = -(yRef)+200;
        
        repaint();
    }
    
}
public class Disparo {
    public static void main(String[] args) {
        Frame03 miFrame = new Frame03();
    }
}
