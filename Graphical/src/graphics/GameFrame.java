package graphics;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame
{
	JPanel gamePanel;
	JPanel interfacePanel;
	
	public GameFrame()
	{
		super("Sivatagi vizhalozat");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//A következő sorok a frame icon-ját állítja be, ha szeretnénk iyet.
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icon.png"));
		this.setIconImage(icon.getImage());
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
 		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void InitializeGamePanel()
	{
		//A játéktérkép megjelenítése
	}
	
	public void InitializeInterfacePanel()
	{
		//A játékállás kiírása
		//Éppen lépő játékos nevének kiírása
		//Egyéb funkciók megjelenítése
	}
}
