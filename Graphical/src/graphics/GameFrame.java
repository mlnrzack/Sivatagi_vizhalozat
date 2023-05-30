package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.GridLayout;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import java.io.File;

import java.util.ArrayList;

import javax.swing.*;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.IO.*;
import game.players.*;
import graphics.elements.*;

/**Ez az osztály felel a játéktér Frame-jéért.
 */
public class GameFrame extends JFrame
{
    private final Color color = Color.decode("#c9a77d");                                            //háttérszín

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();                             //a képernyő mérete
    int screenHeight = screenSize.height;                                                           //képernyő magassága
    int screenWidth = screenSize.width;                                                             //képernyő szélessége

    private JPanel interfacePanel;                                                                  //a kezelőfelület panelja

    private ArrayList<String> damagedPipes;                                                         //lyukas csövek nevének listája
    private ArrayList<String> damagedPumps;                                                         //elromlott pumpák nevének listája

    private ArrayList<JButton> actionButtons;														//akciógombok listája
	
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
    private static String currentMove;																//
    private static ElementView element = null;														//

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

        //modell hívása

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
     * @return
     */
    public MapView GetMap_G()
    {
        return map_G;
    }

    
    /**
     * @return
     */
    public ArrayList<JButton> GetActionButtons()
    {
        return actionButtons;
    }

    /**
     * @param actionButtons
     */
    public void SetActionButtons(ArrayList<JButton> actionButtons)
    {
        this.actionButtons = actionButtons;
    }

    /**
     * @return
     */
    public ElementView GetSelectedElement()
    {
    	return element;
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
        gameStatisticsPanel.setSize((int)(screenWidth * 0.3), (int)(screenHeight * 0.1));
        gameStatisticsPanel.setFont(f);

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
            butt.setBorder(BorderFactory.createDashedBorder(color, 5, 5, 0, false));
            playerActionPanel.add(butt);
        }

        //gombokhoz akció rendelése

        playerActionPanel.setBackground(color);
        playerActionPanel.setVisible(true);
        
        interfacePanel.add(gameStatisticsPanel);
        interfacePanel.add(damagedPipesLabel);
        interfacePanel.add(damagedPumpsLabel);
        interfacePanel.add(playerActionPanel);

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
            displayCurrentPlayerName.setText("Jelenlegi játékos: " + GameManager.GetCurrentSaboteur().GetName());

        displayRound.setText(String.valueOf("Kör: " + GameManager.GetRound()));
        playerRemainingActionCount.setText("Műveletek: " + String.valueOf(GameManager.GetPlayerAction()));
        mechPoints.setText(String.valueOf("Szerelők pontjai: " + GameManager.GetMechanincsPoints()));
        sabPoints.setText(String.valueOf("Szabotőrök pontjai: " + GameManager.GetSaboteurPoints()));
        damagedPartsText.setText("Tönkrement elemek: ");

        for (Pipe entity: GameManager.GetPipes())
        {
            if (entity.GetLeaking())
            {    
            	damagedPipes.add(entity.GetId());
            	damagedPipesLabel.setText(String.format(entity.GetId()+ "\n"));
            }
        }

        for (Pump entity: GameManager.GetPumps())
        {
            if (entity.GetBroken())
            {
                damagedPumps.add(entity.GetId());
                damagedPumpsLabel.setText(String.format(entity.GetId() + "\n"));
            }
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

    public static void createString() {

        currentMove = new String();
        currentMove = "move ";
        currentMove = currentMove.concat(element.GetElement().GetId());
        System.out.println("3. GameFrame currentMOve createStringben "+currentMove);
        Controller.SetNextMove(currentMove);
    }

    /**
     */
    public void ResetActionButtons()
    {
        for(JButton button: actionButtons)
            button.setEnabled(true);
    }
    public static void SetElement(ElementView elementview)
    {
        element = elementview;
        System.out.println("2. GameFrame SetElement "+element.GetElement().GetId());
        createString();
        System.out.println("8. SetElemnt vége. "+element.GetElement().GetId());
        System.out.println(GameManager.GetCurrentMechanic() != null ? GameManager.GetCurrentMechanic().GetName() : GameManager.GetCurrentSaboteur().GetName());
    }
    /**
     * @return
     */
    public static String GetCurrentMove()
    {
        return currentMove;
    }
}