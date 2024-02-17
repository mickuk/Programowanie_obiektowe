package pl.edu.pw.po.roboty;

import java.util.Objects;

public class SztucznaInteligencja {

    String reprezentacjaDanych;


    public SztucznaInteligencja(String repr) {
        if (!Objects.equals(repr, "binarna") && !Objects.equals(repr, "rozmyta") && !Objects.equals(repr, "przyblizona")) {
            System.out.println("Zła reprezentacja danych");
        } else {
            this.reprezentacjaDanych = repr;
        }
    }
    public String getReprezentacjaDanych() {
        return reprezentacjaDanych;
    }

    public void setReprezentacjaDanych(String reprezentacjaDanych) {
        this.reprezentacjaDanych = reprezentacjaDanych;
    }
    public String toString(){
        return "Sztuczna inteligencja: "+this.reprezentacjaDanych;
    }


}
