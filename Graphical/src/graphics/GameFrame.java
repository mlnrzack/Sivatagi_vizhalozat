package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.GridLayout;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import java.io.File;

import java.util.ArrayList;

import javax.swing.*;

import game.GameManager;
import game.elements.Pipe;
import game.elements.Pump;
import game.interfaces.IElement;
import game.players.Mechanic;
import game.players.Saboteur;
import graphics.elements.*;
import graphics.*;
import game.IO.*;

/**Ez az osztály felel a játéktér Frame-jéért.
 */
public class GameFrame extends JFrame
{
	private final Color color = Color.decode("#c9a77d");											//háttérszín
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();								//a képernyő mérete
	int screenHeight = screenSize.height;															//képernyő magassága
	int screenWidth = screenSize.width;																//képernyő szélessége
	
	private JPanel interfacePanel;																	//a kezelőfelület panelja
	
	private ArrayList<String> damagedPipes;															//
	private ArrayList<String> damagedPumps;															//

	private ArrayList<JButton> actionButtons;														//
	
	private JLabel displayCurrentPlayerName;														//
	private JLabel displayRound;																	//
	private JLabel playerRemainingActionCount;														//
	private JLabel mechPoints;																		//
	private JLabel sabPoints;																		//
	private JLabel damagedPartsText;																//
	private JLabel damagedPipesLabel;																//
	private JLabel damagedPumpsLabel;																//
	
	private JPanel playerActionPanel;																//
	private JPanel gameStatisticsPanel;																//
	private JPanel damagedPartsPanel;																//
	
    private MapView map_G;																			//

    /**
     * @param map
     */
    public GameFrame(MapView map)
    {
        //frame nevének beállítása
        super("Sivatagi vizhalozat");
        //frame képének beállítása
        File currentDirFile = new File(".");
        String helper = currentDirFile.getAbsolutePath();
        String midhelp = helper.substring(0, helper.length() - 2).concat("\\bin\\");
        
        ImageIcon icon = new ImageIcon(midhelp.concat("/icon.png"));
        this.setIconImage(icon.getImage());
        //map összekötése a kezelőfeülettel
        map_G = map;
        
        //A panelek feltöltése adatokkal
        InitializeInterfacePanel();

        //A panelek hozzáadása a frame-hez
        this.add(map, BorderLayout.WEST);
        this.add(interfacePanel, BorderLayout.EAST);

        //teljesképernyős megjelenítés
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        Dimension dimension = new Dimension(screenWidth, screenHeight);
        this.setMaximumSize(dimension);
        this.setMinimumSize(dimension);
        this.setPreferredSize(dimension);
        
        //további frame beállításaok
        this.setBackground(color);
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
        interfacePanel.setPreferredSize(new Dimension((int)(screenWidth * 0.3), screenHeight));
        interfacePanel.setBackground(color);

        //gombokon használt font
        Font f = new Font(Font.DIALOG, Font.PLAIN, 18);

        //A játékállás kiírása
        //Éppen lépő játékos nevének kiírása
        //Egyéb funkciók megjelenítése
        //Esetlegesen itt a nem működő pumpák nevét, vagy akár a lyukas csövek nevét is ki lehetne írni
        displayCurrentPlayerName = new JLabel();        
        displayRound = new JLabel();
        playerRemainingActionCount = new JLabel();
        mechPoints = new JLabel();
        sabPoints = new JLabel();
        damagedPartsText = new JLabel();
        damagedPipesLabel = new JLabel();
        damagedPumpsLabel = new JLabel();
        playerActionPanel = new JPanel();
        damagedPartsPanel = new JPanel();
        gameStatisticsPanel = new JPanel();
        playerActionPanel.setLayout(new GridLayout(0,2));
        damagedPipes = new ArrayList<>();
        damagedPumps = new ArrayList<>();

        damagedPartsPanel.add(damagedPartsText);
        damagedPartsPanel.add(damagedPipesLabel);
        damagedPartsPanel.add(damagedPumpsLabel);
        damagedPartsPanel.setLayout(new BoxLayout(damagedPartsPanel, BoxLayout.Y_AXIS));
        damagedPartsPanel.setBackground(color);

        gameStatisticsPanel.add(displayCurrentPlayerName);
        gameStatisticsPanel.add(displayRound);
        gameStatisticsPanel.add(mechPoints);
        gameStatisticsPanel.add(sabPoints);
        gameStatisticsPanel.add(damagedPartsPanel);

        gameStatisticsPanel.setBackground(color);
        gameStatisticsPanel.setLayout(new GridLayout(9,1));
        gameStatisticsPanel.setSize((int)(screenWidth * 0.3), this.getBounds().height);

        //Játékos akcióinak összeszedése:
        actionButtons = new ArrayList<JButton>();
        actionButtons.add(new JButton("move X"));
        actionButtons.add(new JButton("repair"));
        actionButtons.add(new JButton("leakpipe"));
        actionButtons.add(new JButton("pickfreepipe"));
        actionButtons.add(new JButton("picknewpump"));
        actionButtons.add(new JButton("droppump"));
        actionButtons.add(new JButton("connectpipe"));
        actionButtons.add(new JButton("pickneighbour X"));
        actionButtons.add(new JButton("setpump X Y X"));
        actionButtons.add(new JButton("stickypipe"));
        actionButtons.add(new JButton("slipperypipe"));
        actionButtons.add(new JButton("pass"));
        
        //playerActionPanel.setLayout(new GridLayout(12,1,2,2));

        //az actionButtons gombjainak beállítása
        for(JButton butt: actionButtons)
        {
            butt.setBackground(color);
            butt.setFont(f);
            butt.setBorder(BorderFactory.createDashedBorder(color, 2, 2, 2, false));
            playerActionPanel.add(butt);
        }

        //gombokhoz akció rendelése
        AttachActionToButtons();

        playerActionPanel.setBackground(color);
        playerActionPanel.setVisible(true);

        interfacePanel.add(displayCurrentPlayerName);
        interfacePanel.add(displayRound);
        interfacePanel.add(playerRemainingActionCount);
        interfacePanel.add(mechPoints);
        interfacePanel.add(sabPoints);
        interfacePanel.add(damagedPipesLabel);
        interfacePanel.add(damagedPumpsLabel);
        interfacePanel.add(playerActionPanel);
        interfacePanel.add(gameStatisticsPanel);

        interfacePanel.setLayout(new BoxLayout(interfacePanel, BoxLayout.Y_AXIS));

        UpdateHud();

        interfacePanel.revalidate();
        interfacePanel.repaint();
    }

    /**
     */
    public void UpdateHud()
    {
        if(GameManager.GetCurrentMechanic() != null)
            displayCurrentPlayerName.setText("Jelenlegi játékos: " + GameManager.GetCurrentMechanic().GetName());
        
        else if(GameManager.GetCurrentSaboteur() != null)
            displayCurrentPlayerName.setText("Jelenlegi játékos: " +GameManager.GetCurrentSaboteur().GetName());

        displayRound.setText(String.valueOf("Kör: " + GameManager.GetRound()));
        playerRemainingActionCount.setText("Műveletek: " + String.valueOf(GameManager.GetPlayerAction()));
        mechPoints.setText(String.valueOf("Szerelők pontjai: " + GameManager.GetMechanincsPoints()));
        sabPoints.setText(String.valueOf("Szabotőrök pontjai: " + GameManager.GetSaboteurPoints()));
        damagedPartsText.setText("Tönkrement elemek: ");
        
        for (Pipe entity: GameManager.GetPipes())
        {
            if (entity.GetLeaking())
                damagedPipes.add(entity.GetId());
            damagedPipesLabel.setText(String.format(entity.GetId()+ "\n"));
        }

        for (Pump entity: GameManager.GetPumps())
        {
            if (entity.GetBroken())
                damagedPumps.add(entity.GetId());
            damagedPumpsLabel.setText(String.format(entity.GetId() + "\n"));
        }

        //Szabotőr köre van
        if(GameManager.GetCurrentMechanic() == null && GameManager.GetCurrentSaboteur() != null)
        {
            for(JButton butt: actionButtons)
            {
                if(butt.getText().equals("repair") || butt.getText().equals("droppump") 
                || butt.getText().equals("connectpipe") || butt.getText().equals("pickneighbour")
                || butt.getText().equals("pickfreepipe") || butt.getText().equals("picknewpump"))
                    butt.setEnabled(false);
            }
        }
        //Szerelő köre van
        else if(GameManager.GetCurrentSaboteur() == null && GameManager.GetCurrentMechanic() != null)
        {
            for(JButton butt: actionButtons)
            {
                if(butt.getText().equals("slipperypipe"))
                    butt.setEnabled(false);
            }
        }
    }

    public ArrayList<JButton> getActionButtons()
    {
    	return actionButtons; 
    }
    
    /**
     */
    private void AttachActionToButtons() 
    {
        //Az akciógombokhoz hozzá rendelünk a gameManagerből megfelelő akciókat

        for(JButton butt: actionButtons)
        {
            String action = butt.getText();
        
            //GameManager.SetCurrentMechanic(GameManager.GetMechanics().get(0));
            GameManager.SetCurrentSaboteur(GameManager.GetSaboteurs().get(0));
            
            
            //======================================================
            /*
             * Paraszt debug sarok
             *  System.out.println("MEchanics count: "+ GameManager.GetMechanics().size());
            */
            //======================================================
            Mechanic m = GameManager.GetCurrentMechanic();
            //System.out.println(m.GetCurrentPosition().GetId());
            Saboteur s= GameManager.GetCurrentSaboteur();
            //System.out.println("Current player:" + (m != null? m.GetName(): s.GetName()));
            butt.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    JFrame frame;
                    String output;
                    switch(action) 
                    {
                    case "move X":

                        /*
                         * mert akkor a gomb nyomására kellene gondolom egy event listener, 
                         * ami az egér kattintást figyeli, ott megkap egy koordinátát, 
                         * végig kell menni a mapview elemein, és mindegyiknek ellenőrizni, 
                         * hogy az ő területére kattintottak-e, majd ha igen, 
                         * akkor térjen vissza mondjuk az ID-jával a dolog, 
                         * hogy utána meg összevesse a játékos mezejének a szomszédjaival
                         * */

                        //Ez a flag jöhetne a MapViewból
                        map_G.isPlayerMoving = false;

                        int answer;
                        answer = JOptionPane.showConfirmDialog(null, "Klikkelj a kiválasztott mezőre, ahova lépni szeretnél.","", JOptionPane.OK_CANCEL_OPTION);
                        
                        if(answer == JOptionPane.OK_OPTION)
                        {
                            map_G.isPlayerMoving = true;
                            System.out.println(map_G.isPlayerMoving);
                        }
                        else
                        {
                            map_G.isPlayerMoving = false;
                            System.out.println(map_G.isPlayerMoving);
                        }

                        ResetActionButtons();
                        UpdateHud();

                        break;

                    case "repair":
                        frame = new JFrame();
                        if(m!= null) 
                        {
                            if(m.Repair() == true) 
                                JOptionPane.showMessageDialog(frame, "Sikeresen megjavítottad a csövet!");

                            else
                                JOptionPane.showMessageDialog(frame, "A csövet nem kellett megjavítani!");
                        }
                        else 
                            JOptionPane.showMessageDialog(frame, "Nem a szerelő köre van most!");   

                        UpdateHud();
                        ResetActionButtons();
                        break;

                    case "leakpipe":
                        frame = new JFrame();
                        if(m != null) 
                        {
                            if(m.Damage() == true)
                                JOptionPane.showMessageDialog(frame, "Sikeresen megrongáltad a csövet!");

                            else
                                JOptionPane.showMessageDialog(frame, "A cső már meg volt rongálva!");
                        }
                        else if(s != null)
                        {
                            if(s.Damage() == true) 
                                JOptionPane.showMessageDialog(frame, "Sikeresen megrongáltad a csövet!");

                            else
                                JOptionPane.showMessageDialog(frame, "A cső már meg volt rongálva!");
                        }
                        
                        ResetActionButtons();
                        UpdateHud();
                        break;

                    case "pickfreepipe":
                        frame = new JFrame();
                        if(m!= null) 
                        {
                            if(m.PickUpFreePipeEnd() == true)
                                JOptionPane.showMessageDialog(frame, "Sikeresen felvettél egy csővéget!");

                            else
                                JOptionPane.showMessageDialog(frame, "Ez a csővég nem felvehető!");
                        }
                        else
                        {
                            frame = new JFrame();
                            JOptionPane.showMessageDialog(frame, "Nem a szerelő köre van most!");
                        }
                        
                        ResetActionButtons();
                        UpdateHud();
                        break;

                    case"picknewpump":
                        frame = new JFrame();
                        if(m!= null) 
                        {
                            if(m.PickUpPump() == true)
                                JOptionPane.showMessageDialog(frame, "Sikeresen felvettél egy új pumpát!");

                            else
                                JOptionPane.showMessageDialog(frame, "Nem tudtad felvenni a pumpát!");
                        }
                        else
                        {
                            frame = new JFrame();
                            JOptionPane.showMessageDialog(frame, "Nem a szerelő köre van most!");
                        }
                        
                        ResetActionButtons();
                        UpdateHud();
                        break;

                    case "droppump":
                        frame = new JFrame();
                        if(m!= null) 
                        {
                            if(m.BuildPumpIntoPipe() == true)
                                JOptionPane.showMessageDialog(frame, "Sikeresen beépítetted a pumpát egy csőbe!");

                            else
                                JOptionPane.showMessageDialog(frame, "A pumpa nem került beszerelésre!");
                        }
                        else 
                        {
                        	frame = new JFrame();
                        	JOptionPane.showMessageDialog(frame, "Nem a szerelő köre van most!");
                        }
                        
                        ResetActionButtons();
                        UpdateHud();
                        break;

                    case "connectpipe":
                    	frame = new JFrame();
                    	if(m!= null)
                    	{
                    		if(m.ConnectPipe() == true)
                    			JOptionPane.showMessageDialog(frame, "Sikeresen beépítetted a pumpát egy csőbe!");

                    		else
                    			JOptionPane.showMessageDialog(frame, "A pumpa nem került beszerelésre!");
                    	}
                    	else 
                    	{
                    		frame = new JFrame();
                    		JOptionPane.showMessageDialog(frame, "Nem a szerelő köre van most!");
                    	}
                    
                    	ResetActionButtons();
                    	UpdateHud();
                    	break;
                    	
                    case "pickneighbour X":
                    	ArrayList<String>avaliableFields = new ArrayList<String>();
                    	String indicesString = "";

                    	if(m != null) 
                    	{
                    		int i = 0;
                    		avaliableFields.clear();
                    		for(IElement element : m.GetCurrentPosition().GetNeighbours()) 
                    		{
                    			avaliableFields.add(element.GetId());
                    			indicesString.concat(i +": "+ element.GetId()+ "\n");
                    			i++;
                    		}
                    	}

                    	frame = new JFrame();
                    	output = JOptionPane.showInputDialog(frame, "Add meg az indexét a kívánt mezőnek!", indicesString);
                    	int index = Integer.parseInt(output); 
                    	if(index <= avaliableFields.size() && index >= 0) 
                    	{
                    		if(m != null)
                    			m.Move(index);
                    	}
                    
                    	ResetActionButtons();
                    	UpdateHud();
                    	break;

                    case "setpump X Y X":
                    	avaliableFields = new ArrayList<String>();
                    	indicesString ="";

                    	if(m != null)
                    	{
                    		int i = 0;
                    		avaliableFields.clear();
                    		
                    		if(m.GetCurrentPosition().GetId().contains("pump"))
                    		{
                    			for(IElement element : m.GetCurrentPosition().GetNeighbours()) 
                    			{
                    				avaliableFields.add(element.GetId());
                    				indicesString.concat(i +": "+ element.GetId()+ "\n");
                    				i++;
                    			}
                    		}
                    		else 
                    		{
                    			frame = new JFrame();
                    			JOptionPane.showMessageDialog(frame, "A "+  m.GetName()  + " játékos nem áll pumpán, ezért nem tudja végrehajtani a műveletet!");
                    			break;
                    		}
                    	}
                    	
                    	else if(s != null) 
                    	{
                    		int i = 0;
                    		avaliableFields.clear();
                    		if(s.GetCurrentPosition().GetId().contains("pump")) 
                    		{
                    			for(IElement element : m.GetCurrentPosition().GetNeighbours()) 
                    			{
                    				avaliableFields.add(element.GetId());
                    				indicesString.concat(i +": "+ element.GetId()+ "\n");
                    				i++;
                    			}
                    		}
                    		else
                    		{
                    			frame = new JFrame();
                    			JOptionPane.showMessageDialog(frame, "A "+ s.GetName() + " játékos nem áll pumpán, ezért nem tudja végrehajtani a műveletet!");
                    			break;
                    		}
                    	}
                    	
                    	InputDialog pumpIODialog = new InputDialog(2, indicesString, "A pumpa ki és bemenetének beállítása");
                    	String[] asd = pumpIODialog.createAndShowGui();
                    	
                    	int PumpIn = Integer.parseInt(asd[0]);
                    	int PumpOut = Integer.parseInt(asd[1]);
                    	
                    	if((PumpIn <= avaliableFields.size() && PumpIn >= 0) 
                    			&& (PumpOut <= avaliableFields.size() && PumpOut >= 0))
                    	{
                    		if(m != null) 
                    			m.TrySetPump(PumpIn, PumpOut);
                    	}
                    	else if (s!= null)
                    		s.TrySetPump(PumpIn, PumpOut);
                    	
                    	ResetActionButtons();
                    	UpdateHud();
                    	break;
                    	
                    case "stickypipe":
                    	frame = new JFrame();
                    	if(m != null) 
                    	{
                    		if(m.SetStickyPipe() == true)
                    			JOptionPane.showMessageDialog(frame, "Sikeresen beragasztóztad az elemet!");

                    		else
                    			JOptionPane.showMessageDialog(frame, "Az elem nem lett ragacsosabb!");
                    	}
                    	else if(s != null) 
                    	{
                    		if(s.SetStickyPipe() == true)
                    			JOptionPane.showMessageDialog(frame, "Sikeresen beragasztóztad az elemet!");

                    		else
                            JOptionPane.showMessageDialog(frame, "Az elem nem lett ragacsosabb!");
                    	}
                    	
                    	ResetActionButtons();
                    	UpdateHud();
                    	break;

                    case "slipperypipe":
                    	frame = new JFrame();
                    	if(s != null) 
                    	{
                        if(s.SetSlipperyPipe() == true)
                            JOptionPane.showMessageDialog(frame, "Sikeresen csúszóssá tetted az elemet!");

                        else
                            JOptionPane.showMessageDialog(frame, "Nem lett csúszósabb az elem!");
                    	}
                    	else
                    		JOptionPane.showMessageDialog(frame, "Nem a szabotőr köre van!");
                    	
                    	ResetActionButtons();
                    	UpdateHud();
                    	break;
                    	
                    case "pass":
                    	frame = new JFrame();
                    	if(m != null)
                    	{
                    		m.Pass();
                    		JOptionPane.showMessageDialog(frame, GameManager.GetCurrentMechanic().GetName()+ " passzolt");
                    	}
                    	
                    	else if(s != null)
                    	{
                    		s.Pass();
                    		JOptionPane.showMessageDialog(frame, GameManager.GetCurrentSaboteur().GetName()+ " passzolt");
                    	}
                    	
                    	ResetActionButtons();
                    	UpdateHud();
                    	break;                  
                    }
                }
            });
        }
    }
    
    /**
     */
    private void ResetActionButtons() 
    {
    	for(JButton button: actionButtons)
        button.setEnabled(true);
    }
}