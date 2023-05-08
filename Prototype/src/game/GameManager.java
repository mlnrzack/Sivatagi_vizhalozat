package game;

import java.util.*;

import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class GameManager
{
	private static int round = 0;
	private static int mechanicsPoints = 0;
	private static int saboteursPoints = 0;
    private static ArrayList<ISteppable> steppables = new ArrayList<ISteppable>();
    private static ArrayList<WaterSpring> waterSprings = new ArrayList<WaterSpring>();
    private static ArrayList<Saboteur> saboteurs = new ArrayList<Saboteur>();
    private static ArrayList<Mechanic> mechanics = new ArrayList<Mechanic>();
    private static Player currentPlayer;
    private static int playerActionCountInCurrentRound = 0;
    
    public int GetRound()
    {
    	return round;
    }
    
    public static void SetRound(int round)
    {
    	GameManager.round = round;
    }
    
    public Player GetCurrentPlayer()
    {
    	return currentPlayer;
    }
    
    public void SetCurrentPlayer(Player player)
    {
    	currentPlayer = player;
    }
    
    public static int GetMechanincsPoints()
    {
    	return mechanicsPoints;
    }
    
    public static void SetMechanicsPoints(int points)
    {
    	mechanicsPoints = points;
    }
    
    public static int GetSaboteurPoints()
    {
    	return saboteursPoints;
    }
    
    public static void SetSaboteursPoints(int points)
    {
    	saboteursPoints = points;
    }
    
    public static ArrayList<Mechanic> GetMechanics()
    {
    	return mechanics;
    }
        
    public static boolean AddMechanic(Mechanic mechanic)
    {
    	return mechanics.add(mechanic);
    }

    public static ArrayList<Saboteur> GetSaboteurs()
    {
    	return saboteurs;
    }

    public static boolean AddSaboteur(Saboteur saboteur)
    {
    	return saboteurs.add(saboteur);
    }
    
    public static ArrayList<ISteppable> GetSteppables()
    {
    	return steppables;
    }
    
    public static void SetSteppables(ArrayList<ISteppable> steppables)
    {
    	GameManager.steppables = steppables;
    }

    public static boolean AddSteppable(ISteppable steppable)
    {
    	return steppables.add(steppable);
    }
    
    public static boolean RemoveSteppable(ISteppable steppable)
    {
    	return steppables.remove(steppable);
    }
    
    public static ArrayList<WaterSpring> GetWaterSprings()
    {
    	return waterSprings;
    }
    
    public static void SetWaterSprings(ArrayList<WaterSpring> waterSprings)
    {
    	GameManager.waterSprings = waterSprings;
    }
    
    public static boolean AddWaterSpring(WaterSpring waterspring)
    {
    	return waterSprings.add(waterspring);
    }
    
    public static boolean RemoveWaterSpring(WaterSpring waterspring)
    {
    	return waterSprings.remove(waterspring);
    }
    
    public static int GetPlayerAction()
    {
    	return playerActionCountInCurrentRound;
    }
    
    public static void SetPlayerAction(int count)
    {
    	playerActionCountInCurrentRound = count;
    }
    
    public static void IncreasePlayerAction()
    {
    	playerActionCountInCurrentRound++;
    }
    // Ha sikeresen végrehajtott a játékos egy elemi akciót, utána hívjuk.
    public static void ActionExecuted()
    {
        playerActionCountInCurrentRound++;
        FireSourceActions();
        StepSteppables();
    }
    
    public static void StartGame()
    {
    	while (round < Constants.RoundNumber)
        {
            MechanicActions();
            SaboteurActions();
            round++;
        }
    }
    
    public static void FireSourceActions()
    {
    	for(int i = 0; i < waterSprings.size(); i++)
    	{
    		waterSprings.get(i).FillNeighourPipes();
    	}
    }
    
    public static void StepSteppables()
    {
    	var actionDone = false;
        do
        {
            actionDone = false;
            for(int i = 0; i < steppables.size(); i++)
        	{
            	actionDone = steppables.get(i).Step() || actionDone; 
        	}
        }
        while (actionDone);
    }
    
    public static void MechanicActions()
    {
    	for(int i = 0; i < mechanics.size(); i++)
    	{
    		playerActionCountInCurrentRound = 0;
    		
    		while (playerActionCountInCurrentRound < Constants.ActionInRoundPerUser)
    		{
    			System.out.println((round + 1) + ". Kör");
    			System.out.println("Szerelő " + mechanics.get(i).GetName() + " köre, " + (playerActionCountInCurrentRound + 1) + ". akció");
    			System.out.println("Lehetőségek:");
    			System.out.println("\t1 X - Mozgás, X szomszéd indexe, ahova mozogni szeretnél");
    			System.out.println("\t2 - Javítás");
    			System.out.println("\t3 - Szabad csővég felvétele");
               	System.out.println("\t4 - Pumpa felvétele");
               	System.out.println("\t5 - Pumpa beépítése a csőbe");
               	System.out.println("\t6 - Csővég csatlakoztatása");
               	System.out.println("\t7 X - Szomszédos csővég felvétele. Az X a szomszéd indexe.");
               	System.out.println("\t8 X Y - Pumpa beállítása. Az X a kívánt input szomszéd indexe, Y a kívánt output szomszéd indexe.");
               	System.out.println("\t9 - cső lyukasztás");
               	System.out.println("\t10 - sticky");
               	Scanner input = new Scanner(System.in);
               	String userinput = input.next();
               	
               	switch (userinput.split(" ")[0])
               	{
               		case "1":
               			int neighbourIdx = Integer.parseInt(userinput.split(";")[1]);
               			mechanics.get(i).Move(neighbourIdx);
                        break;
                    case "2":
                    	mechanics.get(i).Repair();
                        break;
                    case "3":
                    	mechanics.get(i).PickUpFreePipeEnd();
                        break;
                    case "4":
                    	mechanics.get(i).PickUpPump();
                        break;
                    case "5":
                    	mechanics.get(i).BuildPumpIntoPipe();
                        break;
                    case "6":
                    	mechanics.get(i).ConnectPipe();
                        break;
                    case "7":
                        neighbourIdx = Integer.parseInt(userinput.split(" ")[1]);
                        mechanics.get(i).DisconnectNeighbourPipe(neighbourIdx);
                        break;
                    case "8":
                        int neighbourIdxFrom = Integer.parseInt(userinput.split(" ")[1]);
                        int neighbourIdxTo = Integer.parseInt(userinput.split(" ")[2]);
                        mechanics.get(i).TrySetPump(neighbourIdxFrom, neighbourIdxTo);
                        break;
                    case "9":
                    	mechanics.get(i).Damage();
                    	break;
                    case "10":
                    	mechanics.get(i).SetStickyPipe();
                    	break;
                    default:
                        break;
               	}
    		}
    	}
    }

    public static void SaboteurActions()
    {
    	for(int i = 0; i < saboteurs.size(); i++)
    	{
    		playerActionCountInCurrentRound = 0;

            while (playerActionCountInCurrentRound < Constants.ActionInRoundPerUser)
            {
            	System.out.println((round + 1) + ". Kör");
                System.out.println("Szabotőr " + saboteurs.get(i).GetName() + " köre, " + (playerActionCountInCurrentRound + 1) + " akció");
                System.out.println("Lehetőségek:");
                System.out.println("\t1 X - Mozgás, X szomszéd indexe, ahova mozogni szeretnél");
                System.out.println("\t2 - Maga alatt lévő cső lyukasztása");
                System.out.println("\t8 X Y - Pumpa beállítása. Az X a kívánt input szomszéd indexe, Y a kívánt output szomszéd indexe.");
                System.out.println("\t9 - A cső ragacsossá tétele maga alatt");
                System.out.println("\t10 - A cső csúszóssáa tétele");
                
                Scanner input = new Scanner(System.in);
               	String userinput = input.next();

                switch (userinput.split(" ")[0])
                {
                	case "1":
                		int neighbourIdx = Integer.parseInt(userinput.split(" ")[1]);
                		if (neighbourIdx < saboteurs.get(i).GetCurrentPosition().GetNeighbours().size() && neighbourIdx >= 0)
                		{
                			saboteurs.get(i).Move(neighbourIdx);
                			ActionExecuted();
                		}
                		break;
                    case "2":
                    	if (saboteurs.get(i).Damage() == true)
                    		ActionExecuted();
                        break;
                    case "8":
                    	int neighbourIdxFrom = Integer.parseInt(userinput.split(" ")[1]);
                    	int neighbourIdxTo = Integer.parseInt(userinput.split(" ")[2]);
                    	saboteurs.get(i).TrySetPump(neighbourIdxFrom, neighbourIdxTo);
                        break;
                    case "9":
                    	saboteurs.get(i).SetStickyPipe();
                    	break;
                    case "10":
                    	saboteurs.get(i).SetSlipperyPipe();
                    	break;
                    default:
                    	break;
                }
            }
        }
    }
}
