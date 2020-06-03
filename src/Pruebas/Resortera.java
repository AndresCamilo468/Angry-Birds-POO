package Pruebas;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Frame extends JFrame {

    public Frame() {
        this.setVisible(true);
        this.setSize(400, 300);
        add(new Panel());
    }
    

}

class Panel extends JPanel implements MouseListener, ActionListener{
    private int xRef;
    private int yRef;
    private Timer timer;
    private Rectangle rec;
    private boolean oprimido;
    private boolean move;
    private Point punto;
    
    public Panel() {
        this.xRef=50;
        this.yRef=50;
        this.addMouseListener(this);
        this.timer = new Timer(30, this);
        this.timer.start();
        this.rec  = new Rectangle(xRef, yRef, 100, 100);
        this.oprimido= false;
        this.move = false;
        this.punto = new Point(xRef+50,yRef+50);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(xRef, yRef, 100, 100);
        rec = new Rectangle(xRef, yRef, 100, 100);
    }
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point punto02 = e.getPoint();
        if(oprimido){
            if(punto02.getX() != punto.getX()){
                if(punto02.getY() != punto.getY()){move = true;}
                else{ move=false;}
            }
            punto = punto02;
        }
        else{
            if(rec.contains(punto02)){
                oprimido = true;
                if(punto02.getX() != punto.getX()){
                    if(punto02.getY() != punto.getY()){move = true;}
                    else{ move=false;}
                }

                punto = punto02;
            }
        }
        System.out.println("olo");
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        oprimido = false;
        move=false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(oprimido){
            if(move){
                if(yRef+50 > punto.getY()){yRef--;}
                else{ if(yRef+50 < punto.getY()){ yRef++;}}
                
                if(xRef+50 > punto.getX()){xRef--;}
                else{ if(xRef+50 < punto.getX()){ xRef++;}}
            }
        }
        repaint();
    }
    

}
public class Resortera {
    public static void main(String[] args) {
        Frame miFrame = new Frame();
        miFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
