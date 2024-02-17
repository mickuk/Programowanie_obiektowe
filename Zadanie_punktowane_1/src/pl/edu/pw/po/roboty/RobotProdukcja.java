package pl.edu.pw.po.roboty;

public final class RobotProdukcja extends RobotFabryczny{
    public RobotProdukcja(String nazwa, Zadanie zad) {
        super(nazwa, zad);
    }

    public String toString(){
        return "Robot o nazwie " + this.nazwa + " data produkcji "+ this.dataProdukcji+ " identyfikator "+this.identyfikator+ " ile Zadan: "+this.ileZadan+ " nr fabryczny: "+ this.nrFabryczny + " zadanie "+ this.zadanie;
    }
}
