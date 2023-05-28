package graphics;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import game.GameManager;
import game.elements.Pipe;
import game.elements.Pump;
import graphics.elements.*;

import game.IO.*;

/**Ez az osztály felel a játéktér Frame-jéért.
 */
public class GameFrame extends JFrame
{
	private Color color = Color.decode("#c9a77d");					//háttérszín

	private JPanel interfacePanel;									//a kezelőfelület panelja
	private ArrayList<String> damagedPipes;							//
	private ArrayList<String> damagedPumps;							//
	
	private ArrayList<JButton> actionButtons;						//
	
	private JLabel displayCurrentPlayerName;						//
	private JLabel displayRound;									//
	private JLabel playerRemainingActionCount;						//
	private JLabel mechPoints;										//
	private JLabel sabPoints;										//
	private JLabel damagedPipesLabel;								//
	private JLabel damagedPumpsLabel;								//
	private JPanel playerActionPanel;								//
	private JPanel buttonsHoldingPanel;								//
	
	/**
	 */
	public GameFrame(MapView map)
	{
		super("Sivatagi vizhalozat");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icon.png"));
		this.setIconImage(icon.getImage());
		this.setBackground(color);
		
		//A panelek feltöltése adatokkal
		InitializeInterfacePanel();		
		//a térkép méretének beállítása
		Dimension mapDimension = new Dimension(1400, 800);
		map.setMaximumSize(mapDimension);
		map.setMinimumSize(mapDimension);
		map.setPreferredSize(mapDimension);
		
		//A panelek hozzáadása a frame-hez
		this.add(map, BorderLayout.WEST);
		this.add(interfacePanel, BorderLayout.EAST);

		//további frame beállításaok
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Dimension dimension = new Dimension(JFrame.MAXIMIZED_HORIZ, JFrame.MAXIMIZED_VERT);
		this.setMaximumSize(dimension);
		this.setMinimumSize(dimension);
		this.setPreferredSize(dimension);
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
	
	/**
	 */
	public void InitializeInterfacePanel()
	{
		interfacePanel = new JPanel();
		interfacePanel.setPreferredSize(new Dimension(500, JFrame.MAXIMIZED_VERT));
		interfacePanel.setBackground(color);
		//A játékállás kiírása
		//Éppen lépő játékos nevének kiírása
		//Egyéb funkciók megjelenítése
		//Esetlegesen itt a nem működő pumpák nevét, vagy akár a lyukas csövek nevét is ki lehetne írni
		/**
		 Meg tudnátok csinálni a panel controllos részét?
			Gombokkal,
			Lépéssel stb.
			Meg adatokkal, hogy kinek hanyadik van a köre,
			hanyadik kör van,
			Lyukas / nem müködő csövek/pumpák felsorolva
			Hány pont stb,
			Minden ami hasznos lehet.
		**/
		displayCurrentPlayerName = new JLabel();
		displayRound = new JLabel();
		playerRemainingActionCount = new JLabel();
		mechPoints = new JLabel();
		sabPoints = new JLabel();
		damagedPipesLabel = new JLabel();
		damagedPumpsLabel = new JLabel();
		playerActionPanel = new JPanel(new GridLayout(0, 4));
		damagedPipes = new ArrayList<>();
		damagedPumps = new ArrayList<>();


		//Játékos akcióinak összeszedése:
		actionButtons = new ArrayList<JButton>();
		actionButtons.add(new JButton("move X"));
		actionButtons.add(new JButton("repair"));
		actionButtons.add(new JButton("pickfreepipe"));
		actionButtons.add(new JButton("picknewpump"));
		actionButtons.add(new JButton("droppump"));
		actionButtons.add(new JButton("connectpipe"));
		actionButtons.add(new JButton("pickneighbour X"));
		actionButtons.add(new JButton("setpump X Y X"));
		actionButtons.add(new JButton("leakpipe"));
		actionButtons.add(new JButton("stickypipe"));
		actionButtons.add(new JButton("slipperypipe"));
		actionButtons.add(new JButton("pass"));


		for(JButton butt: actionButtons)
		{
			playerActionPanel.add(butt);
		}

		interfacePanel.add(displayCurrentPlayerName);
		interfacePanel.add(displayRound);
		interfacePanel.add(playerRemainingActionCount);
		interfacePanel.add(mechPoints);
		interfacePanel.add(sabPoints);
		interfacePanel.add(damagedPipesLabel);
		interfacePanel.add(damagedPumpsLabel);
		//interfacePanel.add(playerActionPanel);

		UpdateHud();

		interfacePanel.revalidate();
		interfacePanel.repaint();
	}

	/**
	 */
	public void UpdateHud()
	{
		System.out.println(GameManager.GetMechanics().size());
		//System.out.println(GameManager.GetCurrentMechanic().GetName());
		if(GameManager.GetCurrentMechanic() != null)
			displayCurrentPlayerName.setText(GameManager.GetCurrentMechanic().GetName());
		
		else if(GameManager.GetCurrentSaboteur() != null)
			displayCurrentPlayerName.setText(GameManager.GetCurrentSaboteur().GetName());
		
		displayRound.setText(String.valueOf(GameManager.GetRound()));
		playerRemainingActionCount.setText(String.valueOf(GameManager.GetPlayerAction()));
		mechPoints.setText(String.valueOf(GameManager.GetMechanincsPoints()));
		sabPoints.setText(String.valueOf(GameManager.GetSaboteurPoints()));

		for (Pipe entity: GameManager.GetPipes())
		{
			if (entity.GetLeaking())
				damagedPipes.add(entity.GetId());
		}
		
		for (Pump entity: GameManager.GetPumps())
		{
			if (entity.GetBroken())
				damagedPumps.add(entity.GetId());
		}
		
		damagedPipesLabel.setText(String.format(damagedPipes.toString()+ "\n"));
		damagedPumpsLabel.setText(String.format(damagedPumps.toString()+ "\n"));
	}
}