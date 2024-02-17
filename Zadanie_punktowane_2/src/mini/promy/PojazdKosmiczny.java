package mini.promy;

import mini.obsluga.WyjatekBrakTlenu;

public interface PojazdKosmiczny {

    int getIloscTlenu() throws WyjatekBrakTlenu;
    void zuzycieTlenu(int iloscTlenu);
}
