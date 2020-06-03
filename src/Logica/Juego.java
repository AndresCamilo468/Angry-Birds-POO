package Logica;
import java.util.ArrayList;

public class Juego {
    private ArrayList<Pajaro> pajaros;
    private ArrayList<Cerdo> cerdos;
    private Resortera resortera;
    private int fallos;
    private int tiros;
    private int aciertos;

    public Juego() {
        this.fallos = 0;
        this.tiros =0;
        this.aciertos=0;
        this.cerdos = new ArrayList<>();
        this.cerdos.add(new Cerdo(800, 163, 80, 70));
        this.cerdos.add(new Cerdo(675, 300, 70, 50));
        this.cerdos.add(new Cerdo(925, 300, 60, 50));
        this.cerdos.add(new Cerdo(495, 395, 75, 50));
        this.cerdos.add(new Cerdo(1105, 395, 75, 50));
        this.resortera = new Resortera(200, 400, 200, 400, 70, false);
        this.pajaros = new ArrayList<>();
        this.pajaros.add(new Pajaro(200, 400, 0, 0, 0, 0));
    }

    public ArrayList<Pajaro> getPajaros() {
        return pajaros;
    }

    public void setPajaros(ArrayList<Pajaro> pajaros) {
        this.pajaros = pajaros;
    }

    public ArrayList<Cerdo> getCerdos() {
        return cerdos;
    }

    public void setCerdos(ArrayList<Cerdo> cerdos) {
        this.cerdos = cerdos;
    }

    public Resortera getResortera() {
        return resortera;
    }

    public void setResortera(Resortera resortera) {
        this.resortera = resortera;
    }

    public int getFallos() {
        return fallos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }
    
    public void generarPajaro(){
        this.pajaros.add(new Pajaro(200, 400, 0, 0, 0, 0));
    }

    public int getTiros() {
        return tiros;
    }

    public void setTiros(int tiros) {
        this.tiros = tiros;
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }
    
    
    
}
