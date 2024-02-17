package pl.edu.pw.po.test;

import pl.edu.pw.po.roboty.*;

public class Main {

    public static void main(String[] args) {

        Robot[] roboty = new Robot[6];

        String[] imiona = {"Adam1000", "Roman2", "Aleks900", "Zosia Samosia", "Ala2", "Robert01", "Ula"};

        RobotDomowy rd1 = new RobotDomowy(imiona[0]);
        RobotDomowy rd2 = new RobotDomowy(imiona[1]);
        RobotDomowy rd3 = new RobotDomowy(imiona[2]);

        RobotProdukcja rp1 = new RobotProdukcja(imiona[3], RobotFabryczny.Zadanie.SKLEJANIE);
        RobotProdukcja rp2 = new RobotProdukcja(imiona[4], RobotFabryczny.Zadanie.SKRAWANIE);
        RobotProdukcja rp3 = new RobotProdukcja(imiona[5], RobotFabryczny.Zadanie.LAKIEROWANIE);

        RobotSkladanie rs1 = new RobotSkladanie(imiona[6], RobotFabryczny.Zadanie.ZGRZEWANIE);
        RobotSkladanie rs2 = new RobotSkladanie(imiona[0], RobotFabryczny.Zadanie.MODELOWANIE);
        RobotSkladanie rs3 = new RobotSkladanie(imiona[1], RobotFabryczny.Zadanie.PODNOSNIK);

        Robot[] robociki = {rd1, rd2, rd3, rp1,rp2,rp3,rs1,rs2,rs3};

        for(Robot i:robociki){
            System.out.println(i.toString());
            if (i instanceof RobotSkladanie){
                i.zadanieWykonaj();
                ((RobotSkladanie) i).powitanie();
                ((RobotSkladanie) i).jakDlugoZyje();
            }
            if (i instanceof RobotDomowy){
                ((RobotDomowy) i).powitanie();
            }
            if(i instanceof RobotSkladanie){
                ((RobotSkladanie) i).powitanie();
                i.zadanieWykonaj();
                ((RobotSkladanie) i).jakDlugoZyje();
            }
        }


    }
}
