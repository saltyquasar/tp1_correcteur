package tp1_correcteur;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

//cette classe construit un tableau contenant les mots connus
public class Dictionnaire extends JPanel implements Serializable
{		
	
	public void readFile()
	{
		FileReader fileReader = null;
		try
		{
			fileReader = new FileReader("readme.txt");
			int c = fileReader.read();
			while (c!= -1)
			{
				char d = ((char)c);
				c = fileReader.read();
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Fichier introuvable.");
		}
		catch (IOException e)
		{
			System.out.println("Erreur lors de la lecture du fichier.");
		}
		if (fileReader != null)
		{
			try
			{
				fileReader.close();
			}
			catch (IOException e)
			{}
		}
	}
	
	public static void main(String[] args)
	{
		ouvrir_fichier("dict.f.txt");
		traiter_fichier();
		fermer_fichier();
	}
	
	//permet d'ouvrir un fichier
	public static void ouvrir_fichier(String nom)
	{
		try
		{
			input = new BufferedReader(new FileReader(nom));
		}
		catch (IOException e)
		{
			System.err.println("Impossible d'ouvrir le fichier d'entrée.\n" + e.toString());
			System.exit(1);
		}
	}
	
	//permet de traiter le fichier
	public static void traiter_fichier()
	{
		String ligne;
		try
		{
			ligne = input.readLine();
			while (ligne != null)
			{
				System.out.println(ligne);
				ligne = input.readLine();
			}
		}
		
		//attrape l'exception fin du fichier
		catch (EOFException e)
		{
			System.err.println("Fin du fichier atteinte.\n" + e.toString());
		}
	}
	
	//permet de fermer le fichier
	public static void fermer_fichier()
	{
		try
		{
			input.close();
			System.exit(0);
		}
		catch (IOException e)
		{
			System.err.println("Impossible de fermer les fichiers.\n" + e.toString());
		}
	}
}
