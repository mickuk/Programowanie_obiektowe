package mini.obsluga;

public class WyjatekTransportu extends Exception{

    public WyjatekTransportu() {}

    public WyjatekTransportu(String opis) {

        super(opis);
    }
}
