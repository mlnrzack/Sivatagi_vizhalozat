package graphics;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import graphics.elements.*;

/**Ez az osztály felel a játéktér Frame-jéért.
 */
public class GameFrame extends JFrame
{
	JPanel gamePanel;
	JPanel interfacePanel;
	
	public GameFrame(MapView map)
	{
		super("Sivatagi vizhalozat");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icon.png"));
		this.setIconImage(icon.getImage());
		//A panelek feltöltése adatokkal
		InitializeGamePanel(map);
		InitializeInterfacePanel();
		//A panelek hozzáadása a frame-hez
		this.add(gamePanel, BorderLayout.WEST);
		this.add(interfacePanel, BorderLayout.EAST);
		//további frame beállításaok
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
 		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void InitializeGamePanel(MapView map)
	{
		//A játéktérkép megjelenítése
	}
	
	public void InitializeInterfacePanel()
	{
		//A játékállás kiírása
		//Éppen lépő játékos nevének kiírása
		//Egyéb funkciók megjelenítése
		//Esetlegesen itt a nem működő pumpák nevét, vagy akár a lyukas csövek nevét is ki lehetne írni
	}
}
