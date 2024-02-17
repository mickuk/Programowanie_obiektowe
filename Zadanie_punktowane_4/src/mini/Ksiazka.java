package mini;

import java.io.*;
public class Ksiazka implements Serializable {

    private String autor;
    private String tytul;
    private int rokWydania;
    private boolean wypozyczona;

    public Ksiazka(String autor, String tytul, int rokWydania) {
        this.autor = autor;
        this.tytul = tytul;
        this.rokWydania = rokWydania;
        this.wypozyczona = false;
    }

    public String getAutor() {
        return autor;
    }
    public String getTytul() {
        return tytul;
    }
    public int getRokWydania() {
        return rokWydania;
    }
    public boolean jestWypozyczona() {
        return wypozyczona;
    }
    public void setWypozyczona(boolean wypozyczona) {
        this.wypozyczona = wypozyczona;
    }

    @Override
    public String toString() {
        return tytul + " --- " + autor +  " --- " + rokWydania;
    }
}
