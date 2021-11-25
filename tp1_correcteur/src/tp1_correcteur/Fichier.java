package tp1_correcteur;

import java.io.*;
import java.util.*;

public class Fichier {

    public static void main(String[] args) {
        menu();
        Indexeur indexeur = new Indexeur();
        Scanner clavier = new Scanner(System.in);
        String nom = clavier.next();
        File f = new File(nom);
        if (f.isFile()) indexeur.lire_lignes(f);
        else if (f.isDirectory()) {
            File[] files = f.listFiles();

            for (File file : files)
                {
                   indexeur.lire_lignes(file);
                }
        }
        else System.err.println("Probleme avec le fichier.");

        clavier.close();
    }

    public static void menu() {
        System.out.println("Menu");
        System.out.println("Sasir un fichier/repertoire: ");
    }
}

class Indexeur
{
    public void lire_lignes(File file) {
        //ouverture du fichier
        try
        {
            BufferedReader input = new BufferedReader(new FileReader(file));

            //lire et traiter chaque ligne
            String ligne;
            ligne = input.readLine();
            while (ligne != null) {
                System.out.println(ligne);
                //remplacer cette ligne par votre traitement.
                ligne = input.readLine();
            }

            input.close();
        }
        catch (IOException e) {
            System.err.println("erreur fichier" + e.toString());
        }
    }
}