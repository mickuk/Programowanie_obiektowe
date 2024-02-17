package mini.spaceport;

import mini.obsluga.*; //wyjątki i interfejs
import mini.promy.PromKosmiczny;
import mini.promy.Rakieta;

import java.util.*;

public class KomputerKontrolny {





    public static void main(String[] args) {

        Random rand = new Random();
        String planety[] = {"Mars", "Trapist 1c", "Tytan", "Alfa Centauri", "Kepler 17"};

        //String[] tab = {"„Zderzenie z Marsjanami, bez panik","Problem z silnikiem grawitacyjnym, na zwykłym będziemy lecieć 1353 lata…"}; // zrobione poniżej

        //todo pkt 2 i 3



        Map<Integer, Rakieta> mapa = new LinkedHashMap<>();
        ArrayList<Rakieta> lista = new ArrayList<>();


        System.out.println("----------PRZYGOTOWANIE RAKIET----------------");
        //todo pkt 4 i 5

        int licznik = 1;
        int i = 0;

        while (licznik <= 10) {
            i ++;
            try {
                int iloscTlenu = 1000 + rand.nextInt(2001);
                int iloscZalogi = rand.nextInt(11);
                String miejscePrzylotu = planety[rand.nextInt(5)];
                int dniPozaZiemia = rand.nextInt(401);
                int maxLadownosc = 500;
                int zaladowanie = rand.nextInt(501);
                mapa.put(licznik, new PromKosmiczny(iloscTlenu, iloscZalogi, miejscePrzylotu, maxLadownosc, zaladowanie) {
                });
            } catch (WyjatekTransportu e) {
                System.out.println(i + "- " + "Nie można utworzyć tego pojazdu" + e);
                continue;
            }
            System.out.println(i + ") Dodano: " + mapa.get(licznik));
            licznik++;
        }

        lista.addAll(mapa.values());

        System.out.println("Lista rakiet");
        System.out.println(lista);


        System.out.println("----------W KOSMOSIE----------------");
        //todo pkt 6


        while(!lista.isEmpty()) {
            Iterator<Rakieta> iterator = lista.iterator();
            while (iterator.hasNext()) {
                Rakieta s = iterator.next();
                try {
                    s.getIloscTlenu();
                    s.setIloscTlenu(70);
                } catch(WyjatekBrakTlenu e) {
                    System.out.println("Usuwam z listy");
                    iterator.remove();
                }


                s.dzienPozaZiemia();
                System.out.println(lista);
            }
        }
    }

    }





