package pl.edu.pw.po.roboty;

import pl.edu.pw.po.zasoby.PorozumieniePlus;

public class RobotDomowy extends Robot implements PorozumieniePlus {


    public RobotDomowy(String nazwa) {
        super(nazwa);
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
        return "Robot o nazwie " + this.nazwa + " data produkcji "+ this.dataProdukcji+ " identyfikator "+this.identyfikator+ " ile Zadan: "+this.ileZadan+ " nr fabryczny: "+ this.nrFabryczny;
    }

}
