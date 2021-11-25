package tp1_correcteur;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class TextArea extends JFrame
{
	JTextArea textArea;
	JScrollPane scrollPane;
	
	public TextArea()
	{
		//crée une zone de texte
		textArea = new JTextArea("one two\nthree four", 10, 30)
		{
			//gère le bouton "tab"
			public boolean isManagingFocus()
			{
				return false;
			}
		};
		textArea.setLineWrap(true);
		textArea.setSize(textArea.getPreferredSize());
		textArea.setSelectionColor(Color.red);
		textArea.setSelectedTextColor(Color.green);
		try
		{
			System.out.println(textArea.modelToView(4));
			System.out.println(Utilities.getRowStart(textArea, 4));
		}
		catch(Exception e)
		{}
		
		//permet d'attraper les clics de souris
		textArea.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if(SwingUtilities.isRightMouseButton(e))
				{
					try
					{
						int offset = textArea.viewToModel(e.getPoint());
						System.out.println(textArea.modelToView(offset));
						int start = Utilities.getWordStart(textArea,  offset);
						int end = Utilities.getWordEnd(textArea,  offset);
						String word = textArea.getDocument().getText(start, end-start);
						System.out.println("Mot sélectionné :" + word);
						int rowStart = Utilities.getRowStart(textArea,  offset);
						int rowEnd = Utilities.getRowEnd(textArea,  offset);
						System.out.println("Row start offset:" + rowStart);
						System.out.println("Row end offet:" + rowEnd);
						textArea.select(rowStart,  rowEnd);
					}
					catch(Exception e2)
					{}
				}
			}
		});
		
		//permet d'attraper
		textArea.addCaretListener(new CaretListener()
        {
            public void caretUpdate(CaretEvent e)
            {
                int caretPosition = textArea.getCaretPosition();
                Element root = textArea.getDocument().getDefaultRootElement();
                int row = root.getElementIndex(caretPosition);
                int column = caretPosition - root.getElement(row).getStartOffset();
                System.out.println("Ligne : " + (row + 1));
                System.out.println("Colonne : " + (column + 1));
            }
        });
		
		//permet d'attraper les touches pressées
		textArea.addKeyListener(new KeyAdapter()
        {

            public void keyPressed(KeyEvent e)
            {
                System.out.println(textArea.getDocument().getDefaultRootElement().getElementCount());
            }
        });

		scrollPane = new JScrollPane(textArea);
		getContentPane().add(scrollPane);
		getContentPane().add(new JTextField(10), BorderLayout.SOUTH);
	}
	
	
}
