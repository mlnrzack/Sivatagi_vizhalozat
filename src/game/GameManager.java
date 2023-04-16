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
	private static int saboteursPoints = 0;
    private static ArrayList<ISteppable> steppables = null;
    private static ArrayList<WaterSpring> waterSprings = null;
    private static ArrayList<Saboteur> saboteurs = null;
    private static ArrayList<Mechanic> mechanics = null;
    public static Player CurrentPlayer = null;
    private static int playerActionCountInCurrentRound = 0;
    
    public int GetRound()
    {
    	System.out.println("public int GetRound()");
    	return round;
    }
    
    public void SetRound(int round)
    {
    	System.out.println("public void SetRound(int round)");
    	this.round = round;
    }
    
    public int GetMechanincsPoints()
    {
    	System.out.println("public int GetMechanincsPoints()");
    	return mechanicsPoints;
    }
    
    public void SetMechanicsPoints(int points)
    {
    	System.out.println("public void SetMechanicsPoints(int points)");
    	mechanicsPoints = points;
    }
    
    public static void IncreaseMechanicsPoints()
    {
    	System.out.println("public static void IncreaseMechanicsPoints()");
    	mechanicsPoints++;
    }
    
    public static void DecreaseMechanicsPoints()
    {
    	System.out.println("public static void DecreaseMechanicsPoints()");
    	mechanicsPoints--;
    }
    
    public int GetSaboteurPoints()
    {
    	System.out.println("public int GetSaboteurPoints()");
    	return saboteursPoints;
    }
    
    public void SetSaboteursPoints(int points)
    {
    	System.out.println("public void SetSaboteursPoints(int points)");
    	saboteursPoints = points;
    }
    
    public static void IncreaseSaboteursPoints()
    {
    	System.out.println("public static void IncreaseSaboteursPoints()");
    	saboteursPoints++;
    }
    
    public static void DecreaseSaboteursPoints()
    {
    	System.out.println("public static void DecreaseSaboteursPoints()");
    	saboteursPoints--;
    }

    public ArrayList<Mechanic> GetMechanic()
    {
    	System.out.println("public ArrayList<Mechanic> GetMechanic()");
    	return mechanics;
    }
    
    public void SetMechanic(ArrayList<Mechanic> mechanics)
    {
    	System.out.println("public void SetMechanic(ArrayList<Mechanic> mechanics)");
    	this.mechanics = mechanics;
    }
    
    public static boolean AddMechanic(Mechanic mechanic)
    {
    	System.out.println("public static boolean AddMechanic(Mechanic mechanic)");
    	return mechanics.add(mechanic);
    }
    
    public boolean RemoveMechanic(Mechanic mechanic)
    {
    	System.out.println("public boolean RemoveMechanic(Mechanic mechanic)");
    	return mechanics.remove(mechanic);
    }
    
    public ArrayList<Saboteur> GetSaboteurs()
    {
    	System.out.println("public ArrayList<Saboteur> GetSaboteurs()");
    	return saboteurs;
    }
    
    public void SetSaboteurs(ArrayList<Saboteur> saboteurs)
    {
    	System.out.println("public void SetSaboteurs(ArrayList<Saboteur> saboteurs)");
    	this.saboteurs = saboteurs;
    }

    public static boolean AddSaboteur(Saboteur saboteur)
    {
    	System.out.println("public static boolean AddSaboteur(Saboteur saboteur)");
    	return saboteurs.add(saboteur);
    }
    
    public boolean RemoveSaboteur(Saboteur saboteur)
    {
    	System.out.println("public boolean RemoveSaboteur(Saboteur saboteur)");
    	return saboteurs.remove(saboteur);
    }
    
    public ArrayList<ISteppable> GetSteppables()
    {
    	System.out.println("public ArrayList<ISteppable> GetSteppables()");
    	return steppables;
    }
    
    public void SetSteppables(ArrayList<ISteppable> steppables)
    {
    	System.out.println("public void SetSteppables(ArrayList<ISteppable> steppables)");
    	this.steppables = steppables;
    }

    public static boolean AddSteppble(ISteppable steppable)
    {
    	System.out.println("public static boolean AddSteppble(ISteppable steppable)");
    	return steppables.add(steppable);
    }
    
    public boolean RemoveSteppable(ISteppable steppable)
    {
    	System.out.println("public boolean RemoveSteppable(ISteppable steppable)");
    	return steppables.remove(steppable);
    }
    
    public ArrayList<WaterSpring> GetWaterSprings()
    {
    	System.out.println("public ArrayList<WaterSpring> GetWaterSprings()");
    	return waterSprings;
    }
    
    public void SetWaterSprings(ArrayList<WaterSpring> waterSprings)
    {
    	System.out.println("public void SetWaterSprings(ArrayList<WaterSpring> waterSprings)");
    	this.waterSprings = waterSprings;
    }
    
    public static boolean AddWaterSpring(WaterSpring waterspring)
    {
    	System.out.println("public static boolean AddWaterSpring(WaterSpring waterspring)");
    	return waterSprings.add(waterspring);
    }
    
    public boolean RemoveWaterSpring(WaterSpring waterspring)
    {
    	System.out.println("public boolean RemoveWaterSpring(WaterSpring waterspring)");
    	return waterSprings.remove(waterspring);
    }
    
    public int GetPlayerAction()
    {
    	System.out.println("public int GetPlayerAction()");
    	return playerActionCountInCurrentRound;
    }
    
    public void SetPlayerAction(int count)
    {
    	System.out.println("public void SetPlayerAction(int count)");
    	playerActionCountInCurrentRound = count;
    }
    
    public void IncreasePlayerAction()
    {
    	System.out.println("public void IncreasePlayerAction()");
    	playerActionCountInCurrentRound++;
    }
    
    // Ha sikeresen végrehajtott a játékos egy elemi akciót, utána hívjuk.
    public static void ActionExecuted()
    {
    	System.out.println("public static void ActionExecuted()");
        playerActionCountInCurrentRound++;
        FireSourceActions();
        StepSteppables();
    }

    public static void AddSteppable(ISteppable steppable)
    {
    	System.out.println("public static void AddSteppable(ISteppable steppable)");
    	steppables.add(steppable);
        
    }
    
    public static void FireSourceActions()
    {
    	System.out.println("public static void FireSourceActions()");
     	//watersrpings.ForEach(watersrpings => watersrpings.FillNeighourPipes());
    }
    
    public static void MechanicActions()
    {
    	System.out.println("public static void MechanicActions()");
        foreach (var player in GameController.mechanics)
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
               String userinput = System.in.toString();//Console.ReadLine();
               
               switch (userinput.toCharArray()[0])
               {
               		case '1':
               			int neighbourIdx;// = int.Parse(userinput.Split(';')[1]);
               			mechanic.Move(neighbourIdx);
                        break;
                    case '2':
                    	mechanic.Repair();
                        break;
                    case '3':
                    	mechanic.PickUpFreePipeEnd();
                        break;
                    case '4':
                    	mechanic.PickUpPump();
                        break;
                    case '5':
                    	mechanic.BuildPumpIntoPipe();
                        break;
                    case '6':
                    	mechanic.ConnectPipe();
                        break;
                    case '7':
                        //neighbourIdx = int.Parse(userinput.Split(';')[1]);
                        mechanic.DisconnectNeighbourPipe(neighbourIdx);
                        break;
                    case '8':
                        int neighbourIdxFrom;// = int.Parse(userinput.Split(';')[1]);
                        int neighbourIdxTo;// = int.Parse(userinput.Split(';')[2]);
                        mechanic.TrySetPump(neighbourIdxFrom, neighbourIdxTo);
                        break;
                    default:
                        break;
                }
            }
       }
    }

    public static void SaboteurActions()
    {
    	System.out.println("public static void SaboteurActions()");
    	for(Saboteur saboteur;;)
    	//foreach (var player in GameController.sabouteurs)
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
                		int neighbourIdx;// = int.Parse(userinput.Split(';')[1]);
                		if (neighbourIdx < saboteur.GetCurrentPosition().GetNeighbours().size() && neighbourIdx >= 0)
                		{
                			saboteur.Move(neighbourIdx);
                			ActionExecuted();
                		}
                		break;
                    case '2':
                    	if (saboteur.Damage() == true)
                    		ActionExecuted();
                        break;
                    case '7':
                    	int neighbourIdxFrom;// = int.Parse(userinput.Split(';')[1]);
                    	int neighbourIdxTo;// = int.Parse(userinput.Split(';')[2]);
                    	if (neighbourIdxFrom < saboteur.GetCurrentPosition().GetNeighbours().size() && neighbourIdxFrom >= 0
                    		&& neighbourIdxTo < saboteur.GetCurrentPosition().GetNeighbours().size() && neighbourIdxTo >= 0
                    		&& saboteur.TrySetPump(neighbourIdxFrom, neighbourIdxTo))
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
    	System.out.println("public static void StartGame()");
    	while (GameManager.round < Constants.RoundNumber)
        {
            GameManager.MechanicActions();
            GameManager.SaboteurActions();
            GameManager.round++;
        }
    }
    
    public static void StepSteppables()
    {
    	System.out.println("public static void StepSteppables()");
    	var actionDone = false;
        do
        {
        	actionDone = false;
            foreach (var steppable in steppables)
            {
            	actionDone = steppables.Step() || actionDone;
            }
        }
            while (actionDone);
    }  
 }
