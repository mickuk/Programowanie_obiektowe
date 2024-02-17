package pl.edu.pw.po.roboty;

import java.time.LocalDate;
import java.util.Random;

public abstract class Robot {

    LocalDate dataProdukcji;
    String nazwa;
    String identyfikator;
    int ileZadan;
    SztucznaInteligencja si;

    Random liczba = new Random();
    Random losowaLiczba = new Random();

    private String generujUnikalnaNazwe() {

        char[] losoweLitery = new char[5];
        for (int i = 0; i < 5; i++){
            losoweLitery[i] = (char) (losowaLiczba.nextInt(26) + 'A');
        }
        return new String(losoweLitery);
    }
    int nrFabryczny = 0;

    public Robot(String nazwa){
        this.nazwa = nazwa;
        int rok = liczba.nextInt(22) + 2000;
        int miesiac = liczba.nextInt(12) +1;
        int dzien = liczba.nextInt(31) + 1;
        this.dataProdukcji = LocalDate.of(rok,miesiac,dzien);
        this.identyfikator = generujUnikalnaNazwe();
        this.ileZadan = 0;
        this.nrFabryczny = nrFabryczny +1;
        nrFabryczny++;
    }

    public void setIleZadan(int ileZadan) {
        this.ileZadan = ileZadan;
    }

    public void zadanieWykonaj(){
        this.ileZadan = ileZadan + liczba.nextInt(20)+1;
    }
}
