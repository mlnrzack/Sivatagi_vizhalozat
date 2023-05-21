package graphics;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.GameManager;
import graphics.elements.*;

import game.IO.*;

/**Ez az osztály felel a játéktér Frame-jéért.
 */
public class GameFrame extends JFrame
{
	private JPanel gamePanel;
	private JPanel interfacePanel;
	
	public GameFrame(MapView map)
	{
		super("Sivatagi vizhalozat");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icon.png"));
		this.setIconImage(icon.getImage());
		//Esetleg valami hátteret (akár képet is) érdemes lehet betölteni a játéktérnek
		
		//A panelek feltöltése adatokkal
		InitializeInterfacePanel();
		
		//A panelek hozzáadása a frame-hez
		this.add(map, BorderLayout.WEST);
		this.add(interfacePanel, BorderLayout.EAST);
		
		//további frame beállításaok
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
 		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Logolások hívása záráskor
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				DebugLog.WriteOutDebugLog();
				InfoLog.WriteOutInfoLog();
			}
		});
	}
	
	public void InitializeInterfacePanel()
	{
		//A játékállás kiírása
		//Éppen lépő játékos nevének kiírása
		//Egyéb funkciók megjelenítése
		//Esetlegesen itt a nem működő pumpák nevét, vagy akár a lyukas csövek nevét is ki lehetne írni
		JLabel displayCurrentPlayerName = new JLabel();
		JLabel displayRound = new JLabel();
		/*if(GameManager.GetCurrentMechanic() != null) 
			displayCurrentPlayerName.setText(GameManager.GetCurrentMechanic().GetName());
		else if(GameManager.GetCurrentSaboteur() != null)
			displayCurrentPlayerName.setText(GameManager.GetCurrentSaboteur().GetName());
		*/
		//interfacePanel.add(displayCurrentPlayerName);
		/*
		interfacePanel.revalidate();
		interfacePanel.repaint();*/
	}
}
