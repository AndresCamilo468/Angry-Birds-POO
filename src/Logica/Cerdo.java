package Logica;

import java.awt.Rectangle;

public class Cerdo {
    private Rectangle rectangulo;
    private int xPos;
    private int yPos;
    private boolean vivo;

    public Cerdo(int xPos, int yPos, int xTam, int yTam) {
        this.rectangulo= new Rectangle(xPos, yPos, xTam, yTam);
        this.xPos = xPos;
        this.yPos = yPos;
        this.vivo=true;
    }

    public Rectangle getRectangulo() {return rectangulo;}

    public void setRectangulo(Rectangle rectangulo) {this.rectangulo = rectangulo; }

    public int getxPos() {return xPos;}

    public void setxPos(int xPos) {this.xPos = xPos;}

    public int getyPos() {return yPos;}

    public void setyPos(int yPos) {this.yPos = yPos;}

    public boolean isVivo() {return vivo;}

    public void setVivo(boolean vivo) {this.vivo = vivo;}
    
    
}
