package mini.promy;

import mini.promy.PojazdKosmiczny;
import mini.obsluga.WyjatekBrakTlenu;
import mini.obsluga.WyjatekTransportu;

public abstract class Rakieta implements PojazdKosmiczny{

    private int iloscTlenu;
    private int iloscZalogi;
    private String miejscePrzylotu;
    private int dniPozaZiemia;

    public Rakieta(int iloscTlenu, int iloscZalogi, String miejscePrzylotu) throws WyjatekTransportu {
        this.iloscTlenu = iloscTlenu;
        this.iloscZalogi = iloscZalogi;
        this.miejscePrzylotu = miejscePrzylotu;
        this.dniPozaZiemia = 0;

        if(iloscZalogi < 5){
            throw new WyjatekTransportu("Wczoraj kapitan miał urodziny, nie polecimy");
        }
        if(iloscTlenu < 500){
            throw new WyjatekBrakTlenu("Mamy wyciek tlenu, polecimy, gdy tylko znajdzie się taśma klejąca");
        }
    }

        @Override
    public String toString() {
        return "Lecimy na " + miejscePrzylotu + " już " + this.dniPozaZiemia +
                " dzień. Pozostało " + iloscTlenu + " jednostek tlenu na " +
                iloscZalogi + " czlonków załogi.";
    }

    @Override
    public int getIloscTlenu() throws WyjatekBrakTlenu {
        if(iloscTlenu <= 100){
            throw new WyjatekBrakTlenu("Rakieta rozpoczęła przyspieszony powrót na Ziemię");
        }
        return iloscTlenu;
    }

    @Override
    public void zuzycieTlenu(int utrataTlenu){
        iloscTlenu -= utrataTlenu;
    }

    public void setIloscTlenu(int iloscTlenu) {
        this.iloscTlenu = iloscTlenu;
    }

    public void setIloscZalogi(int iloscZalogi) {
        this.iloscZalogi = iloscZalogi;
    }

    public void setMiejscePrzylotu(String miejscePrzylotu) {
        this.miejscePrzylotu = miejscePrzylotu;
    }

    public void setDniPozaZiemia(int dniPozaZiemia) {
        this.dniPozaZiemia = dniPozaZiemia;
    }

    public int getIloscZalogi() {
        return iloscZalogi;
    }

    public String getMiejscePrzylotu() {
        return miejscePrzylotu;
    }

    public int getDniPozaZiemia() {
        return dniPozaZiemia;
    }

    public void dzienPozaZiemia(){
        dniPozaZiemia++;
    }

    // getter getIloscTlenu jest na górze
}
