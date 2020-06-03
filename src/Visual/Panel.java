package Visual;

import Logica.Cerdo;
import Logica.Juego;
import Logica.Pajaro;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements MouseMotionListener, ActionListener, MouseListener {

    private Juego juego;
    private Timer timer;
    private Rectangle piso;
    private Rectangle baseEstructura;
    private Rectangle primerPisoEstructura;
    private Rectangle segundoPisoEstructura;
    private Rectangle botonReiniciar;
    private boolean explosion;
    private boolean explosionCerdo;
    private int cerdoMuerto;
    private double explosionStep;

    public Panel() {
        this.juego = new Juego();
        this.timer = new Timer(8, this);
        this.piso = new Rectangle(0, 520, 2250, 80);
        this.baseEstructura = new Rectangle(500, 440, 680, 45);
        this.primerPisoEstructura = new Rectangle(650, 350, 380, 105);
        this.segundoPisoEstructura = new Rectangle(770, 230, 140, 120);
        this.botonReiniciar = new Rectangle(10, 10, 50, 50);
        this.timer.start();
        this.explosionStep = 1;
        this.explosion = false;
        this.explosionCerdo = false;
        this.cerdoMuerto = 0;
        addMouseListener(this);
        addMouseMotionListener(this);

    }

    public Image loadImage(String imageName) {
        ImageIcon imagen = new ImageIcon(imageName);
        Image image = imagen.getImage();
        return image;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.dibujarEstructura(g);
        //Resortera
        Image resortera02 = loadImage("resortera02.png");
        g.drawImage(resortera02, 200, 400, 40, 90, null);

        //Pajaro
        Image pajaro01 = loadImage("pajaro01.png");
        Pajaro pajaroAux = juego.getPajaros().get(juego.getPajaros().size() - 1);

        //Caucho
        if (!(this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).isMovimiento())) {
            g2.setStroke(new BasicStroke(10));
            g2.setColor(new Color(186, 74, 0));
            g2.drawLine(pajaroAux.getxRef() + 10, pajaroAux.getyRef() + 40, 235, 415);
        }
        //Pajaro
        if ((this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).isVivo())) {
            g.drawImage(pajaro01, pajaroAux.getxRef(), pajaroAux.getyRef(), 45, 40, null);
        }

        //Resortera
        Image resortera01 = loadImage("resortera01.png");
        g.drawImage(resortera01, 200, 400, 40, 90, null);

        //Cucho
        if (!(this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).isMovimiento())) {
            g2.setColor(new Color(55, 6, 6));
            g2.drawLine(pajaroAux.getxRef() + 10, pajaroAux.getyRef() + 40, 207, 415);
            g.setColor(Color.BLACK);
            g.fillRect(pajaroAux.getxRef(), pajaroAux.getyRef() + 28, 14, 18);
        }

//        //Dibujar Pajaros
//        for(Pajaro pajaro: this.juego.getPajaros()){
//           g.drawImage(pajaro01, pajaro.getxRef(), pajaro.getyRef(), 45, 40, null);
//           g.drawOval(pajaro.getxInicial(), pajaro.getyInicial(), 1, 1);
//        }
        //Explosiones
        if (explosion) {
            Image explosion = loadImage("explosion0" + (int) (explosionStep) + ".png");
            g.drawImage(explosion, this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).getxRef(),
                    this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).getyRef(), 85, 85, null);
        }
        if (explosionCerdo) {
            Image explosion02 = loadImage("explosion20" + (int) (explosionStep) + ".png");
            g.drawImage(explosion02, this.juego.getCerdos().get(cerdoMuerto).getxPos(),
                    this.juego.getCerdos().get(cerdoMuerto).getyPos(), 105, 105, null);
        }
        
        //ACabar el Juego cuando Pierde
        if (this.juego.getFallos() >= 5) {
            timer.stop();
            int a = JOptionPane.showConfirmDialog(this, "Has Perdido, Reiniciar el Juego");
            if(a==1 || a ==2){
                JOptionPane.showMessageDialog(this, "Gracias Por Jugar");
            }
            else{
                this.juego = new Juego();
                this.timer.start();
            }
        }
        
        //Acabar el juego cuando Gan
        if (this.juego.getAciertos()>= 5) {
            timer.stop();
            int a = JOptionPane.showConfirmDialog(this, "Ganado, Quieres Jugar Ora vez");
            if(a==1 || a ==2){
                JOptionPane.showMessageDialog(this, "Gracias Por Jugar");
            }
            else{
                this.juego = new Juego();
                this.timer.start();
            }
        }
        
        //Dibujar Flecha
        if((this.juego.getPajaros().get(this.juego.getPajaros().size()-1).getyRef())<0){
            int[] xPoints= new int[3];
            int[] yPoints= new int[3];
            xPoints[0] = (this.juego.getPajaros().get(this.juego.getPajaros().size()-1).getxRef());
            yPoints[0] = 10;
            xPoints[1] = (this.juego.getPajaros().get(this.juego.getPajaros().size()-1).getxRef()) + 10;
            yPoints[1] = 25;
            xPoints[2] = (this.juego.getPajaros().get(this.juego.getPajaros().size()-1).getxRef())-10;
            yPoints[2] = 25;
            g.setColor(Color.WHITE);
            g.fillPolygon(xPoints, yPoints, 3);
            g.setColor(Color.red);
            g.drawPolygon(xPoints, yPoints, 3);
        }
        
        
        //Dibujar el ultimo disparo
        if(this.juego.getPajaros().size() > 1){
            Pajaro pajaroAux02 = this.juego.getPajaros().get(this.juego.getPajaros().size()-2);
            for(int i=0; i<100; i++){
                double tiempo =  i/4;
                int xRef = ((int) ((( pajaroAux02.getVelocidadInicial() * Math.cos(pajaroAux02.getAngulo01())* tiempo)) + pajaroAux02.getxInicial()));
                int yRef =((int) ((-((pajaroAux02.getVelocidadInicial()* Math.sin(pajaroAux02.getAngulo02())*tiempo)-(9.8 *( (tiempo*tiempo)/2))))+ pajaroAux02.getyInicial()));
                g.setColor(new Color(225, 9, 9  ));
                g.fillOval(xRef, yRef, 10, 10);
            }
        }
    }

    public void dibujarEstructura(Graphics g) {
        Image fondo = loadImage("fondo.png");
        g.drawImage(fondo, 0, 0, null);
        Graphics2D g2 = (Graphics2D) g;
        Image cuadrado01 = loadImage("cuadrado.png");
        Image cuadrado02 = loadImage("cuadrado02.png");
        Image cuadrado03 = loadImage("cuadrado03.png");
        Image cuadrado04 = loadImage("cuadrado04.png");
        Image cuadrado05 = loadImage("cuadrado05.png");
        //Primera Base
        g.drawImage(cuadrado02, 500, 470, 40, 15, null);
        g.drawImage(cuadrado02, 540, 470, 40, 15, null);
        g.drawImage(cuadrado02, 580, 470, 40, 15, null);
        g.drawImage(cuadrado02, 620, 470, 40, 15, null);
        g.drawImage(cuadrado02, 660, 470, 40, 15, null);
        g.drawImage(cuadrado02, 700, 470, 40, 15, null);
        g.drawImage(cuadrado02, 740, 470, 40, 15, null);
        g.drawImage(cuadrado02, 780, 470, 40, 15, null);
        g.drawImage(cuadrado02, 820, 470, 40, 15, null);
        g.drawImage(cuadrado02, 860, 470, 40, 15, null);
        g.drawImage(cuadrado02, 900, 470, 40, 15, null);
        g.drawImage(cuadrado02, 940, 470, 40, 15, null);
        g.drawImage(cuadrado02, 980, 470, 40, 15, null);
        g.drawImage(cuadrado02, 1020, 470, 40, 15, null);
        g.drawImage(cuadrado02, 1060, 470, 40, 15, null);
        g.drawImage(cuadrado02, 1100, 470, 40, 15, null);
        g.drawImage(cuadrado02, 1140, 470, 40, 15, null);

        //Segunda Base
        g.drawImage(cuadrado02, 540, 455, 40, 15, null);
        g.drawImage(cuadrado02, 500, 455, 40, 15, null);
        g.drawImage(cuadrado02, 540, 440, 40, 15, null);
        g.drawImage(cuadrado02, 500, 440, 40, 15, null);
        g.drawImage(cuadrado02, 1100, 455, 40, 15, null);
        g.drawImage(cuadrado02, 1140, 455, 40, 15, null);
        g.drawImage(cuadrado02, 1100, 440, 40, 15, null);
        g.drawImage(cuadrado02, 1140, 440, 40, 15, null);

        //Columnas
        g.drawImage(cuadrado03, 650, 370, 20, 100, null);
        g.drawImage(cuadrado03, 770, 370, 20, 100, null);
        g.drawImage(cuadrado03, 890, 370, 20, 100, null);
        g.drawImage(cuadrado03, 1010, 370, 20, 100, null);

        //Plancha
        g.drawImage(cuadrado04, 670, 350, 100, 20, null);
        g.drawImage(cuadrado04, 790, 350, 100, 20, null);
        g.drawImage(cuadrado04, 910, 350, 100, 20, null);
        g.drawImage(cuadrado05, 650, 350, 20, 20, null);
        g.drawImage(cuadrado05, 770, 350, 20, 20, null);
        g.drawImage(cuadrado05, 890, 350, 20, 20, null);
        g.drawImage(cuadrado05, 1010, 350, 20, 20, null);

        //Segundo Piso
        g.drawImage(cuadrado03, 770, 250, 20, 100, null);
        g.drawImage(cuadrado03, 890, 250, 20, 100, null);
        g.drawImage(cuadrado04, 790, 230, 100, 20, null);
        g.drawImage(cuadrado05, 770, 230, 20, 20, null);
        g.drawImage(cuadrado05, 890, 230, 20, 20, null);

        //Cerdos
        Image cerdo01 = loadImage("cerdo01.png");
        Image cerdo02 = loadImage("cerdo02.png");
        Image cerdo03 = loadImage("cerdo03.png");
        Image cerdo04 = loadImage("cerdo04.png");

        //Dibujando Cerdos
        //Cerdo Rey
        if (this.juego.getCerdos().get(0).isVivo()) {
            g.drawImage(cerdo01, 800, 163, 80, 70, null);
            this.juego.getCerdos().get(0).setRectangulo(new Rectangle(810, 175, 55, 55));
        } else {
        }

        //Cerdo Casco
        if (this.juego.getCerdos().get(1).isVivo()) {
            g.drawImage(cerdo02, 675, 300, 70, 50, null);
            this.juego.getCerdos().get(1).setRectangulo(new Rectangle(680, 300, 65, 50));
            

        } else {
        }

        //Cerdo Bigote
        if (this.juego.getCerdos().get(2).isVivo()) {
            g.drawImage(cerdo03, 925, 300, 60, 50, null);
            this.juego.getCerdos().get(2).setRectangulo(new Rectangle(925, 300, 60, 50));
        } else {
        }

        //Cerdo Izquierda Abajo
        if (this.juego.getCerdos().get(3).isVivo()) {
            g.drawImage(cerdo04, 495, 395, 75, 50, null);
            this.juego.getCerdos().get(3).setRectangulo(new Rectangle(510, 400, 45, 40));
        } else {
        }

        //Cerdo Derecha Abajo
        if (this.juego.getCerdos().get(4).isVivo()) {
            g.drawImage(cerdo04, 1105, 395, 75, 50, null);
            this.juego.getCerdos().get(4).setRectangulo(new Rectangle(1120, 400, 45, 40));
        } else {
        }
        //Piso
        //g.setColor(Color.red);
        //g.drawRect(0, 480, 1250, 120);

        //Estructura
        //g.drawRect(500, 440, 680, 45);
        //g.drawRect(650, 350, 380, 105);
        //g.drawRect(770, 230, 140, 120);

        //Reiniciar
        Image reiniciar = loadImage("reiniciar.png");
        g.drawImage(reiniciar, 10, 10, 50, 50, null);

        //Disparos
        g.setColor(Color.WHITE);
        String prueba01 = "prueba01";
        g.setFont(new Font(prueba01, Font.BOLD, 25));
        g.drawString("Disparos : " + this.juego.getTiros(), 1000, 30);
        g.drawString("Puntaje: " + this.juego.getAciertos() * 100, 1000, 60);
        g.drawString("Fallos Seguidos: " + this.juego.getFallos(), 1000, 90);
    }

    public int medirDisitancia(int x1, int y1, int x2, int y2) {
        double distanciaAux = Math.sqrt(((x1 - x2) * (x1 - x2))
                + ((y1 - y2) * (y1 - y2)));
        int distaciaAux02 = (int) distanciaAux;
        return distaciaAux02;

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.juego.getFallos() < 5) {
            if (!(this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).isMovimiento())) {
                if (this.juego.getResortera().isSeleccionada()) {
                    int distanciaReal = medirDisitancia((int) e.getPoint().getX(), (int) e.getPoint().getY(),
                            this.juego.getResortera().getxCentro(), this.juego.getResortera().getyCentro());

                    if (distanciaReal <= this.juego.getResortera().getDistanciaMaxima()) {
                        this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).setxRef((int) e.getPoint().getX());
                        this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).setyRef((int) e.getPoint().getY());
                        double xRefAux = (e.getPoint().getX() - this.juego.getResortera().getxCentro()) / distanciaReal;
                        double yRefAux = (e.getPoint().getY() - this.juego.getResortera().getyCentro()) / distanciaReal;
                        double angulo01 = Math.acos(xRefAux);
                        double angulo02 = Math.asin(yRefAux);
                        if (angulo01 < (Math.PI / 2)) {
                            angulo01 = angulo01 + (Math.PI);
                        } else {
                            angulo01 = angulo01 - (Math.PI);
                        }
                        this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).setAngulo01(angulo01);
                        this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).setAngulo02(angulo02);
                    } else {

                        double xRefAux = (e.getPoint().getX() - this.juego.getResortera().getxCentro()) / distanciaReal;
                        double yRefAux = (e.getPoint().getY() - this.juego.getResortera().getyCentro()) / distanciaReal;
                        double angulo01 = Math.acos(xRefAux);
                        double angulo02 = Math.asin(yRefAux);
                        double xRefAux02 = Math.cos(angulo01) * this.juego.getResortera().getDistanciaMaxima();
                        double yRefAux02 = Math.sin(angulo02) * this.juego.getResortera().getDistanciaMaxima();
                        int x = (int) (xRefAux02 + this.juego.getResortera().getxCentro());
                        int y = (int) (yRefAux02 + this.juego.getResortera().getyCentro());
                        this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).setxRef(x);
                        this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).setyRef(y);
                        if (angulo01 < (Math.PI / 2)) {
                            angulo01 = angulo01 + (Math.PI);
                        } else {
                            angulo01 = angulo01 - (Math.PI);
                        }
                        this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).setAngulo01(angulo01);
                        this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).setAngulo02(angulo02);
                    }
                }
            }
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.juego.getFallos() < 5) {
            if (this.botonReiniciar.contains(e.getPoint())) {
                this.juego = new Juego();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (this.juego.getFallos() < 5) {
            if (this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).getRectangulo().contains(e.getPoint())) {
                if (!(this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).isMovimiento())) {
                    this.juego.getResortera().setSeleccionada(true);
                } else {
                    this.juego.getResortera().setSeleccionada(false);
                }
            } else {
                this.juego.getResortera().setSeleccionada(false);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.juego.getFallos() < 5) {
            if (this.juego.getResortera().isSeleccionada()) {
                this.juego.setTiros(this.juego.getTiros() + 1);
                if (!(this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).isMovimiento())) {
                    this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).setMovimiento(true);
                    int distanciaReal = medirDisitancia((int) e.getPoint().getX(), (int) e.getPoint().getY(),
                            this.juego.getResortera().getxCentro(), this.juego.getResortera().getyCentro());

                    if (distanciaReal >= this.juego.getResortera().getDistanciaMaxima()) {
                        this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).setVelocidadInicial(120);
                    } else {
                        double velocidadAux = (120 * distanciaReal) / this.juego.getResortera().getDistanciaMaxima();
                        this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).setVelocidadInicial(velocidadAux);
                    }
                }
                this.juego.getResortera().setSeleccionada(false);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.juego.getFallos() < 5) {
            if (explosion) {
                this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).setVivo(false);
                this.explosionStep = this.explosionStep + 0.1;
                if (this.explosionStep >= 10) {
                    this.explosion = false;
                    this.explosionCerdo = false;
                    this.explosionStep = 0;
                    this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).setMovimiento(false);
                    this.juego.generarPajaro();
                }
            } else {
                if (this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).isMovimiento()) {
                    this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).mover();
                }

                //Colisones
                String choco = "";

                //Colisiones con cerdo
                int i = 0;
                for (Cerdo cerdo : this.juego.getCerdos()) {
                    if (this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).getRectangulo().intersects(cerdo.getRectangulo())) {
                        if (cerdo.isVivo()) {
                            this.explosion = true;
                            this.explosionCerdo = true;
                            this.cerdoMuerto = i;
                            this.juego.setAciertos(this.juego.getAciertos()+1);
                            cerdo.setVivo(false);
                            choco = "CERDO";
                        }
                    }
                    i++;
                }

                //Colision Piso
                if ((this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).getRectangulo().intersects(piso))
                        || (this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).getRectangulo().intersects(baseEstructura))
                        || (this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).getRectangulo().intersects(primerPisoEstructura))
                        || (this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).getRectangulo().intersects(segundoPisoEstructura))) {
                    if (!(this.juego.getResortera().isSeleccionada())) {
                        this.explosion = true;
                        choco = "MAPA";
                    }
                }
                
                //Se Sale
                if((this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).getxRef() > 1360) ||
                   (this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).getxRef() < -100)){
                    choco = "SALIO"; 
                }
                
                //Cosa fea
                if (choco.equals("MAPA")) {
                    this.juego.setFallos(this.juego.getFallos() + 1);

                } else {
                    if (choco.equals("CERDO")) {
                        this.juego.setFallos(0);
                    }
                    else{
                        if(choco.equals("SALIO")){
                            this.juego.setFallos(this.juego.getFallos() + 1);
                            this.juego.getPajaros().get(this.juego.getPajaros().size() - 1).setMovimiento(false);
                            this.juego.generarPajaro();
                        }
                    }

                }
            }
        }
        repaint();
    }

}
