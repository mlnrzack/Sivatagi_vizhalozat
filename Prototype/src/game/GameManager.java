package game;

import java.util.*;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class GameManager
{
	private static int round = 0;
	private static int mechanicsPoints = 0;
	private static int saboteursPoints = Desert.WaterFromPipelineNetwork;
    private static ArrayList<ISteppable> steppables = new ArrayList<ISteppable>();
    private static ArrayList<WaterSpring> waterSprings = new ArrayList<WaterSpring>();
    private static ArrayList<Saboteur> saboteurs = new ArrayList<Saboteur>();
    private static ArrayList<Mechanic> mechanics = new ArrayList<Mechanic>();
    public static Player currentPlayer;
    private static int playerActionCountInCurrentRound = 0;
    
    public static int GetRound()
    {
    	return round;
    }
    
    public static void SetRound(int round)
    {
    	GameManager.round = round;
    }
    
    public static Player GetCurrentPlayer()
    {
    	return currentPlayer;
    }
    
    public static void SetCurrentPlayer(Player player)
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
    
    public static void IncreaseMechanicsPoints()
    {
    	mechanicsPoints++;
    }
    
    public static void DecreaseMechanicsPoints()
    {
    	mechanicsPoints--;
    }
    
    public static int GetSaboteurPoints()
    {
    	return saboteursPoints;
    }
    
    public static void SetSaboteursPoints(int points)
    {
    	saboteursPoints = points;
    }
    
    public static void IncreaseSaboteursPoints()
    {
    	saboteursPoints++;
    }
    
    public static void DecreaseSaboteursPoints()
    {
    	saboteursPoints--;
    }

    public static ArrayList<Mechanic> GetMechanic()
    {
    	return mechanics;
    }
    
    public static void SetMechanic(ArrayList<Mechanic> mechanics)
    {
    	GameManager.mechanics = mechanics;
    }
    
    public static boolean AddMechanic(Mechanic mechanic)
    {
    	return mechanics.add(mechanic);
    }
    
    public static boolean RemoveMechanic(Mechanic mechanic)
    {
    	return mechanics.remove(mechanic);
    }
    
    public static ArrayList<Saboteur> GetSaboteurs()
    {
    	return saboteurs;
    }
    
    public static void SetSaboteurs(ArrayList<Saboteur> saboteurs)
    {
    	GameManager.saboteurs = saboteurs;
    }

    public static boolean AddSaboteur(Saboteur saboteur)
    {
    	return saboteurs.add(saboteur);
    }
    
    public static boolean RemoveSaboteur(Saboteur saboteur)
    {
    	return saboteurs.remove(saboteur);
    }
    
    public static ArrayList<ISteppable> GetSteppables()
    {
    	return steppables;
    }
    
    public static void SetSteppables(ArrayList<ISteppable> steppables)
    {
    	GameManager.steppables = steppables;
    }

    public static boolean AddSteppble(ISteppable steppable)
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

    public static void AddSteppable(ISteppable steppable)
    {
    	steppables.add(steppable);
        
    }
    
    public static void FireSourceActions()
    {
     	//watersrpings.ForEach(watersrpings => watersrpings.FillNeighourPipes());
    }
    
    public static void MechanicActions()
    {
    	for(int i = 0; i < mechanics.size(); i++)
    	{
    		playerActionCountInCurrentRound = 0;
    		
    		while (playerActionCountInCurrentRound < Constants.ActionInRoundPerUser)
    		{
    			System.out.println("{GameController.round + 1}. Kör");
    			System.out.println("Szerelő {player.Name} köre, {playerActionCountInCurrentRound + 1}. akció");
    			System.out.println("Lehetőségek:");
    			System.out.println("\t1;X - Mozgás, X szomszéd indexe, ahova mozogni szeretnél");
    			System.out.println("\t2 - Javítás");
    			System.out.println("\t3 - Szabad csővég felvétele");
               	System.out.println("\t4 - Pumpa felvétele");
               	System.out.println("\t5 - Pumpa beépítése a csőbe");
               	System.out.println("\t6 - Csővég csatlakoztatása");
               	System.out.println("\t7;X - Szomszédos csővég felvétele. Az X a szomszéd indexe.");
               	System.out.println("\t8;X;Y - Pumpa beállítása. Az X a kívánt input szomszéd indexe, Y a kívánt output szomszéd indexe.");
               	String userinput = System.in.toString();
               
               	switch (userinput.toCharArray()[0])
               	{
               		case '1':
               			int neighbourIdx = Integer.parseInt(userinput.split(";")[1]);
               			mechanics.get(i).Move(neighbourIdx);
                        break;
                    case '2':
                    	mechanics.get(i).Repair();
                        break;
                    case '3':
                    	mechanics.get(i).PickUpFreePipeEnd();
                        break;
                    case '4':
                    	mechanics.get(i).PickUpPump();
                        break;
                    case '5':
                    	mechanics.get(i).BuildPumpIntoPipe();
                        break;
                    case '6':
                    	mechanics.get(i).ConnectPipe();
                        break;
                    case '7':
                        neighbourIdx = Integer.parseInt(userinput.split(";")[1]);
                        mechanics.get(i).DisconnectNeighbourPipe(neighbourIdx);
                        break;
                    case '8':
                        int neighbourIdxFrom = Integer.parseInt(userinput.split(";")[1]);
                        int neighbourIdxTo = Integer.parseInt(userinput.split(";")[2]);
                        mechanics.get(i).TrySetPump(neighbourIdxFrom, neighbourIdxTo);
                        break;
                    default:
                        break;
               	}
    		}
    	}
    }

    public static void SaboteurActions()
    {
    	for(int i =0; i < saboteurs.size(); i++)
    	{
    		playerActionCountInCurrentRound = 0;

            while (playerActionCountInCurrentRound < Constants.ActionInRoundPerUser)
            {
            	System.out.println("{GameController.round + 1}. Kör");
                System.out.println("Szabotőr {player.Name} köre, {playerActionCountInCurrentRound + 1}. akció");
                System.out.println("Lehetőségek:");
                System.out.println("\t1;X - Mozgás, X szomszéd indexe, ahova mozogni szeretnél");
                System.out.println("\t2 - Lyukasztás");
                System.out.println("\t8;X;Y - Pumpa beállítása. Az X a kívánt input szomszéd indexe, Y a kívánt output szomszéd indexe.");
                String userinput = System.in.toString();//.ReadLine();

                switch (userinput.toCharArray()[0])
                {
                	case '1':
                		int neighbourIdx = Integer.parseInt(userinput.split(";")[1]);
                		if (neighbourIdx < saboteurs.get(i).GetCurrentPosition().GetNeighbours().size() && neighbourIdx >= 0)
                		{
                			saboteurs.get(i).Move(neighbourIdx);
                			ActionExecuted();
                		}
                		break;
                    case '2':
                    	if (saboteurs.get(i).Damage() == true)
                    		ActionExecuted();
                        break;
                    case '7':
                    	int neighbourIdxFrom = Integer.parseInt(userinput.split(";")[1]);
                    	int neighbourIdxTo = Integer.parseInt(userinput.split(";")[2]);
                    	if (neighbourIdxFrom < saboteurs.get(i).GetCurrentPosition().GetNeighbours().size() && neighbourIdxFrom >= 0
                    		&& neighbourIdxTo < saboteurs.get(i).GetCurrentPosition().GetNeighbours().size() && neighbourIdxTo >= 0
                    		&& saboteurs.get(i).TrySetPump(neighbourIdxFrom, neighbourIdxTo))
                    	{
                    		ActionExecuted();
                    	}
                        break;
                    default:
                    	break;
                }
            }
        }
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
    
    public static void StepSteppables()
    {
    	var actionDone = false;
        do
        {
            actionDone = false;
            for(int i = 0; i < steppables.size(); i++)
        	{
            	actionDone = steppables.get(i).Step()|| actionDone; 
        	}
        }
        while (actionDone);
    }   
 }
