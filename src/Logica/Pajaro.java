package Logica;

import java.awt.Rectangle;

public class Pajaro {
    private Rectangle rectangulo;
    private int xInicial;
    private int yInicial;
    private int xRef;
    private int yRef;
    private double tiempo;
    private double angulo01;
    private double angulo02;
    private double velocidadInicial;
    private boolean vivo;
    private boolean movimiento;

    public Pajaro(int xRef, int yRef, double tiempo, double angulo01, double angulo02, double velocidadInicial) {
        this.rectangulo = new Rectangle(xRef, yRef, 45, 40);
        this.xInicial = xRef;
        this.yInicial = yRef;
        this.xRef = xRef;
        this.yRef = yRef;
        this.tiempo = tiempo;
        this.angulo01 = angulo01;
        this.angulo02 = angulo02;
        this.velocidadInicial = velocidadInicial;
        this.vivo = true;
        this.movimiento=false;
    }

    public Rectangle getRectangulo() {return rectangulo;}

    public void setRectangulo(Rectangle rectangulo) {this.rectangulo = rectangulo;}

    public int getxRef() {return xRef;}

    public void setxRef(int xRef02) {
        this.xRef = xRef02;
        this.rectangulo = new Rectangle(xRef02, this.yRef, 45, 40);
        if(!(this.movimiento)){
            this.xInicial=xRef02;
        }
    }

    public int getyRef() {return yRef;}

    public void setyRef(int yRef02) {
        this.yRef = yRef02;
        this.rectangulo = new Rectangle(this.xRef, yRef02, 45, 40);
        if(!(this.movimiento)){
            this.yInicial=yRef02;
        }
    }

    public double getTiempo() {return tiempo;}

    public void setTiempo(double tiempo) {this.tiempo = tiempo;}

    public double getAngulo01() {return angulo01;}

    public void setAngulo01(double angulo01) {this.angulo01 = angulo01;}

    public double getAngulo02() {return angulo02;}

    public void setAngulo02(double angulo02) {this.angulo02 = angulo02;}

    public double getVelocidadInicial() {return velocidadInicial;}

    public void setVelocidadInicial(double velocidadInicial) {this.velocidadInicial = velocidadInicial;}

    public boolean isVivo() {return vivo;}

    public void setVivo(boolean vivo) {this.vivo = vivo;}

    public boolean isMovimiento() {return movimiento;}

    public void setMovimiento(boolean movimiento) {this.movimiento = movimiento;}
    
    public void mover(){
          this.tiempo = tiempo + 0.03;
          this.setxRef((int) (((velocidadInicial * Math.cos(angulo01)* tiempo)) + xInicial) );
          this.setyRef((int) ((-((velocidadInicial* Math.sin(angulo02)*tiempo)-(9.8 *( (tiempo*tiempo)/2))))+yInicial));
    }

    public int getxInicial() {
        return xInicial;
    }

    public void setxInicial(int xInicial) {
        this.xInicial = xInicial;
    }

    public int getyInicial() {
        return yInicial;
    }

    public void setyInicial(int yInicial) {
        this.yInicial = yInicial;
    }
    
    
}
