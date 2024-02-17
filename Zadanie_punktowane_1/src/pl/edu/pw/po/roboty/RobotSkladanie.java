package pl.edu.pw.po.roboty;

import pl.edu.pw.po.zasoby.PorozumieniePlus;

import java.util.Random;

public final class RobotSkladanie extends RobotFabryczny implements PorozumieniePlus {

    int limit;

    Random liczba = new Random();
    public RobotSkladanie(String nazwa, Zadanie zad) {
        super(nazwa, zad);
        this.limit = liczba.nextInt(10)+1;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public void meldunek() {

    }

    @Override
    public void jakDlugoZyje() {

    }

    @Override
    public void powitanie() {
        System.out.println("Robot " + this.nazwa +" o numerze fabrycznym "+nrFabryczny + "mówi tutaj");
    }

    public String toString(){
        return "Robot o nazwie " + this.nazwa + " data produkcji "+ this.dataProdukcji+ " identyfikator "+this.identyfikator+ " ile Zadan: "+this.ileZadan+ " nr fabryczny: "+ this.nrFabryczny + " zadanie "+ this.zadanie+" limit "+ this.limit;
    }

    public void zadanieWykonaj(){
        if(ileZadan + liczba.nextInt(20)+1 > limit){
            System.out.println("Przekroczenie limitu");
        } else {
            this.ileZadan = ileZadan + liczba.nextInt(20) + 1;
        }
    }
}
