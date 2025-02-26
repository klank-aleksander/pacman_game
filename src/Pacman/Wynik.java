package Pacman;

import java.io.Serializable;
import java.util.List;

public class Wynik implements Serializable{
    private String imie;
    private int punkty;
    private int index;

    public Wynik(String imie, int punkty){
        this.imie = imie;
        this.punkty = punkty;
    }

    public static void przydzielIndex(List<Wynik> wyniki){
        wyniki.sort((a,b)->b.punkty-a.punkty);
        for(int i = 0; i < wyniki.size(); i++){
            wyniki.get(i).index = i+1;
        }
    }

    @Override
    public String toString() {
        return index+" "+imie+" "+punkty;
    }

    public String getImie() {
        return imie;
    }

    public int getPunkty() {
        return punkty;
    }

    public int getIndex() {
        return index;
    }


}
