package mini.promy;

import mini.obsluga.WyjatekBiznesowy;
import mini.obsluga.WyjatekTransportu;

public class PromKosmiczny extends Rakieta{

    int maxLadownosc;
    int zaladowanie;
    public PromKosmiczny(int iloscTlenu, int iloscZalogi, String miejscePrzylotu, int maxLadownosc, int zaladowanie) throws WyjatekTransportu, WyjatekBiznesowy {
        super(iloscTlenu, iloscZalogi, miejscePrzylotu);  // dodaję WyjatekTransportu, by zastosować super()
        this.maxLadownosc = maxLadownosc;
        this.zaladowanie = zaladowanie;

        if(zaladowanie < 0.5*maxLadownosc){
            throw new WyjatekBiznesowy("Dorzućcie jeszcze towaru");
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                " Na pokładzie promu mamy " + zaladowanie +
                " ton ładunku.";
    }

    public int getMaxLadunek() {
        return maxLadownosc;
    }

    public void setMaxLadunek(int maxLadunek) {
        this.maxLadownosc = maxLadunek;
    }

    public int getLadunek() {
        return zaladowanie;
    }

    public void setLadunek(int ladunek) {
        if(ladunek > maxLadownosc){
            System.out.println("Za dużo ładunku");
            return;
        }
        this.zaladowanie = ladunek;
    }
}
