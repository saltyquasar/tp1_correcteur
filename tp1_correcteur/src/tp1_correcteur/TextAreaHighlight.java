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
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TextAreaHighlight extends JFrame
{
	JTextArea textComp;
	
	TextAreaHighlight()
	{
		textComp = new JTextArea();
		add(textComp);
	}

	//surligne toutes les occurences du mot dans textComp
	public void highlight(String mot)
	{
		//retire les anciens highlights
	    removeHighlights(textComp);
	    try
	    {
	        Highlighter hilite = textComp.getHighlighter();
	        Document doc = textComp.getDocument();
	        String text = doc.getText(0, doc.getLength());
	        int pos = 0;
	        
	        //recherche le mot
	        while ((pos = text.indexOf(mot, pos)) >= 0)
	        {
	            //crée un highlighter avec la couleur définie et colore le mot
	            hilite.addHighlight(pos, pos+mot.length(), myHighlightPainter);
                pos += mot.length();
	        }
	    }
	    catch (BadLocationException e)
	    {}
	}
	
	//permet de retirer les highlights
	public void removeHighlights(JTextComponent textComp)
	{
	    Highlighter hilite = textComp.getHighlighter();
	    Highlighter.Highlight[] hilites = hilite.getHighlights();
	    for (int i=0; i<hilites.length; i++)
	    {
	        if (hilites[i].getPainter() instanceof MyHighlightPainter)
	        {
	            hilite.removeHighlight(hilites[i]);
	        }
	    }
	}
	
	//permet de choisir la couleur du highlighter
    Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter(Color.red);
	class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter
	{
		public MyHighlightPainter(Color color)
		{
			super(color);
		}
	}

	public static void main(String[] args)
	{
		TextAreaHighlight h = new TextAreaHighlight();
//		h.setTitle("TextAreaHighlight");
//		h.setVisible(true);
//		h.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//surligne les occurences du mot "public"
//		h.textComp.append("Ce système n'est pas vu par le public. Il est privé.");
//		h.highlight();
//    	h.highlight(h.textComp, "public");
	}
}
