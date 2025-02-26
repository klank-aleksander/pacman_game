package Pacman;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WynikiModel{
    private static DefaultListModel listModel = new DefaultListModel();
    private static JList<Wynik> wynikJList= new JList<>(listModel);
    private static String path = "src/wyniki.txt";

    public WynikiModel(){}

    public static void odczytajWyniki(){
        try(FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis)){
            List<Wynik> tmp = (List<Wynik>) ois.readObject();
            Wynik.przydzielIndex(tmp);
            listModel.clear();
            for(Wynik w:tmp){
                listModel.addElement(w);
                System.out.println(w);
            }

        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void zapiszWyniki(){
        try(FileOutputStream fileOut = new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(fileOut)){
            List<Wynik> tmp = new ArrayList<>();
           for(int i = 0; i < listModel.size(); i++){
               tmp.add((Wynik)listModel.getElementAt(i));
           }
           out.writeObject(tmp);
            System.out.println("saved");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dodajWynik(Wynik wynik){
        listModel.addElement(wynik);
    }
    public static JList<Wynik> getWynikJList() {
        return wynikJList;
    }
}
