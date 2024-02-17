package pl.edu.pw.po.roboty;

import java.util.Random;

public abstract class RobotFabryczny extends Robot{

    public enum Zadanie {
        SKRAWANIE,
        ZGRZEWANIE,
        MODELOWANIE,
        PODNOSNIK,
        LAKIEROWANIE,
        SKLEJANIE;

    }
    Random Los = new Random();
    Zadanie zadanie;

    public Zadanie getZadanie() {
        return zadanie;
    }

    public RobotFabryczny(String nazwa){
        super(nazwa);
        this.zadanie = Zadanie.values()[Los.nextInt(0,6)];
    }

    public RobotFabryczny(String nazwa, Zadanie zad){
        super(nazwa);
        this.zadanie = zad;
    }

    public String toString(){
        return "Robot o nazwie " + this.nazwa + " data produkcji "+ this.dataProdukcji+ " identyfikator "+this.identyfikator+ " ile Zadan: "+this.ileZadan+ " nr fabryczny: "+ this.nrFabryczny + " zadanie "+ this.zadanie;
    }


}
