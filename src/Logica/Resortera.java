package Logica;
public class Resortera {
    private int xCentro;
    private int yCentro;
    private int xPunta;
    private int yPunta;
    private int distanciaMaxima;
    private boolean seleccionada;

    public Resortera(int xCentro, int yCentro, int xPunta, int yPunta, int distanciaMaxima, boolean seleccionada) {
        this.xCentro = xCentro;
        this.yCentro = yCentro;
        this.xPunta = xPunta;
        this.yPunta = yPunta;
        this.distanciaMaxima = distanciaMaxima;
        this.seleccionada = seleccionada;
    }

    public int getxCentro() {return xCentro;}

    public void setxCentro(int xCentro) {this.xCentro = xCentro;}

    public int getyCentro() {return yCentro;}

    public void setyCentro(int yCentro) {this.yCentro = yCentro;}

    public int getxPunta() {return xPunta;}

    public void setxPunta(int xPunta) {this.xPunta = xPunta;}

    public int getyPunta() {return yPunta;}

    public void setyPunta(int yPunta) {this.yPunta = yPunta;}

    public int getDistanciaMaxima() {return distanciaMaxima;}

    public void setDistanciaMaxima(int distanciaMaxima) {this.distanciaMaxima = distanciaMaxima;}

    public boolean isSeleccionada() {return seleccionada;}

    public void setSeleccionada(boolean seleccionada) {this.seleccionada = seleccionada;}

    
    
    
}
