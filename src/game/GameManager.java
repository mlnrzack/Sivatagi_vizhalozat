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
    private static ArrayList<ISteppable> steppables = null;
    private static ArrayList<WaterSpring> waterSprings = null;
    private static ArrayList<Saboteur> saboteurs = null;
    private static ArrayList<Mechanic> mechanics = null;
    public static Player CurrentPlayer = null;
    private static int playerActionCountInCurrentRound = 0;
    
    public int GetRound()
    {
    	System.out.println("public int GetRound()");
    	return 0;
    }
    
    public void SetRound(int round)
    {
    	System.out.println("public void SetRound(int round)");
    }
    
    public int GetMechanincsPoints()
    {
    	System.out.println("public int GetMechanincsPoints()");
    	return 0;
    }
    
    public void SetMechanicsPoints(int points)
    {
    	System.out.println("public void SetMechanicsPoints(int points)");
    }
    
    public static void IncreaseMechanicsPoints()
    {
    	System.out.println("public static void IncreaseMechanicsPoints()");
    }
    
    public static void DecreaseMechanicsPoints()
    {
    	System.out.println("public static void DecreaseMechanicsPoints()");
    }
    
    public int GetSaboteurPoints()
    {
    	System.out.println("public int GetSaboteurPoints()");
    	return 0;
    }
    
    public void SetSaboteursPoints(int points)
    {
    	System.out.println("public void SetSaboteursPoints(int points)");
    }
    
    public static void IncreaseSaboteursPoints()
    {
    	System.out.println("public static void IncreaseSaboteursPoints()");
    }
    
    public static void DecreaseSaboteursPoints()
    {
    	System.out.println("public static void DecreaseSaboteursPoints()");
    }

    public ArrayList<Mechanic> GetMechanic()
    {
    	System.out.println("public ArrayList<Mechanic> GetMechanic()");
    	return null;
    }
    
    public void SetMechanic(ArrayList<Mechanic> mechanics)
    {
    	System.out.println("public void SetMechanic(ArrayList<Mechanic> mechanics)");
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
    }
    
    public void IncreasePlayerAction()
    {
    	System.out.println("public void IncreasePlayerAction()");
    }
    
    // Ha sikeresen végrehajtott a játékos egy elemi akciót, utána hívjuk.
    public static void ActionExecuted()
    {
    	System.out.println("public static void ActionExecuted()");
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
    }
    
    public static void MechanicActions()
    {
    	System.out.println("public static void MechanicActions()");
    }

    public static void SaboteurActions()
    {
    	System.out.println("public static void SaboteurActions()");
    }

    public static void StartGame()
    {
    	System.out.println("public static void StartGame()");
    	GameManager.MechanicActions();
        GameManager.SaboteurActions();
    }
    
    public static void StepSteppables()
    {
    	System.out.println("public static void StepSteppables()");
    	boolean actionDone = false;
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
