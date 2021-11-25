package tp1_correcteur;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class FileChooser extends JPanel implements ActionListener
{
	static private final String newline = "\n";
	JButton openButton, saveButton;
	JTextArea log;
	JFileChooser fc;
	
	public static void main(String[] args)
	{
		//permet de créer et d'afficher l'interface graphique sous un Runnable
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		});
	}

	public FileChooser()
	{
		super(new BorderLayout());
		log = new JTextArea(5,20);
		log.setMargin(new Insets(5,5,5,5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);
		
		//crée un nouveau sélecteur de fichier
		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		//crée un nouveau bouton
		openButton = new JButton("Ouvrir...", createImageIcon("images/Open16.gif"));
		openButton.addActionListener(this);

		saveButton = new JButton("Enregistrer...", createImageIcon("images/Save16.gif"));
		saveButton.addActionListener(this);

		//crée un panel pour les boutons et y ajoute ces derniers
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(openButton);
		buttonPanel.add(saveButton);

		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
	}
	
	//permet de capter les actions produites
	public void actionPerformed(ActionEvent e)
	{
		//permet de gérer le bouton ouvrir
		if (e.getSource() == openButton)
		{
			int returnVal = fc.showOpenDialog(FileChooser.this);
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				log.append("Ouverture de : " + file.getName() + "." + newline);
			}
			else
			{
				log.append("Ouverture annulée par l'utilisateur." + newline);
			}
			log.setCaretPosition(log.getDocument().getLength());
		}
		
		//permet de gérer le bouton enregistrer
		else if (e.getSource() == saveButton)
		{
			int returnVal = fc.showSaveDialog(FileChooser.this);
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				//enregistrement du fichier
				log.append("Enregistrement de : " + file.getName() + "." + newline);
				}
			else
			{
				log.append("Enregistrement annulé par l'utilisateur." + newline);
			}
			log.setCaretPosition(log.getDocument().getLength());
		}
	}
	
	//permet d'afficher les icônes
	protected static ImageIcon createImageIcon(String path)
	{
		java.net.URL imgURL = FileChooser.class.getResource(path);
		if (imgURL != null)
		{
			return new ImageIcon(imgURL);
		}
		else
		{
			System.err.println("Chemin introuvable : " + path);
			return null;
		}
	}

	//permet de créer et d'afficher l'interface graphique
	private static void createAndShowGUI()
	{
		//crée la fenêtre
		JFrame frame = new JFrame("Sélecteur de fichier");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//ajoute le contenu
		frame.add(new FileChooser());
		
		//affiche la fenêtre
		frame.pack();
		frame.setVisible(true);
	}
}