package game;

import java.util.*;

import game.elements.*;
import game.interfaces.*;
import game.players.*;

/**
 * A játékállást és cselekvést kezelő osztály
 */
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

    /**
     * Visszatér a jelenlegi kör értékével
     * @return
     */
    public int GetRound()
    {
    	System.out.println("public int GetRound()");
    	return 0;
    }

    /**
     * Beállítható rajta keresztül az aktuális kör száma
     * @param round
     */
    public void SetRound(int round)
    {
    	System.out.println("public void SetRound(int round)");
    }

    /**
     * Visszatér a Szerelők pontjaival
     * @return
     */
    public int GetMechanincsPoints()
    {
    	System.out.println("public int GetMechanincsPoints()");
    	return 0;
    }

    /**
     * A Szerelők pontjait állítja be
     * @param points
     */
    public void SetMechanicsPoints(int points)
    {
    	System.out.println("public void SetMechanicsPoints(int points)");
    }
    /**
     * Inkrementálja a Szerelők pontjait
     * @return
     */
    public static void IncreaseMechanicsPoints()
    {
    	System.out.println("public static void IncreaseMechanicsPoints()");
    }
    /**
     * Dekrementlálja a Szerelők pontjait
     * @return
     */
    public static void DecreaseMechanicsPoints()
    {
    	System.out.println("public static void DecreaseMechanicsPoints()");
    }
    /**
     * Visszatér a Szabotőrök pontjaival
     * @return
     */
    public int GetSaboteurPoints()
    {
    	System.out.println("public int GetSaboteurPoints()");
    	return 0;
    }
    /**
     * A Szabotőrök pontjait állítja be
     * @param points
     */
    public void SetSaboteursPoints(int points)
    {
    	System.out.println("public void SetSaboteursPoints(int points)");
    }
    /**
     * Inkrementálja a Szabotőrök pontjait
     * @return
     */
    public static void IncreaseSaboteursPoints()
    {
    	System.out.println("public static void IncreaseSaboteursPoints()");
    }
    /**
     * Dekrementlálja a Szabotőrök pontjait
     * @return
     */
    public static void DecreaseSaboteursPoints()
    {
    	System.out.println("public static void DecreaseSaboteursPoints()");
    }

    /**
     * Visszatér a Szerelők listájával
     * @return
     */
    public ArrayList<Mechanic> GetMechanic()
    {
    	System.out.println("public ArrayList<Mechanic> GetMechanic()");
    	return null;
    }

    /**
     * Megad egy Szerelő lista referenciát
     * @param mechanics
     */
    public void SetMechanic(ArrayList<Mechanic> mechanics)
    {
    	System.out.println("public void SetMechanic(ArrayList<Mechanic> mechanics)");
    }

    /**
     * Hozzáad egy Szerelőt a Szerelők listájához
     * @param mechanic
     * @return
     */
    public static boolean AddMechanic(Mechanic mechanic)
    {
    	System.out.println("public static boolean AddMechanic(Mechanic mechanic)");
    	return mechanics.add(mechanic);
    }
    /**
     * Elvesz egy Szerelőt a Szerelők listájából
     * @param mechanic
     * @return
     */
    public boolean RemoveMechanic(Mechanic mechanic)
    {
    	System.out.println("public boolean RemoveMechanic(Mechanic mechanic)");
    	return mechanics.remove(mechanic);
    }
    /**
     * Visszatér a Szabotőrök listájával
     * @return
     */
    public ArrayList<Saboteur> GetSaboteurs()
    {
    	System.out.println("public ArrayList<Saboteur> GetSaboteurs()");
    	return saboteurs;
    }
    /**
     * Megad egy Szabotőr lista referenciát
     * @param saboteurs
     */
    public void SetSaboteurs(ArrayList<Saboteur> saboteurs)
    {
    	System.out.println("public void SetSaboteurs(ArrayList<Saboteur> saboteurs)");
    }
    /**
     * Hozzáad egy Szabotőrt a Szabotőrök listájához
     * @param saboteur
     * @return
     */
    public static boolean AddSaboteur(Saboteur saboteur)
    {
    	System.out.println("public static boolean AddSaboteur(Saboteur saboteur)");
    	return saboteurs.add(saboteur);
    }
    /**
     * Elvesz egy Szabotőrt a Szabotőrök listájából
     * @param saboteur
     * @return
     */
    public boolean RemoveSaboteur(Saboteur saboteur)
    {
    	System.out.println("public boolean RemoveSaboteur(Saboteur saboteur)");
    	return saboteurs.remove(saboteur);
    }

    /**
     * Visszatér az ISteppable interfészt megvalósító elemekkel (azaz olyanokkal, amelyekben folyik víz)
     * @return
     */
    public ArrayList<ISteppable> GetSteppables()
    {
    	System.out.println("public ArrayList<ISteppable> GetSteppables()");
    	return steppables;
    }

    /**
     * Megad egy ISteppable lista referenciát
     * @param steppables
     */
    public void SetSteppables(ArrayList<ISteppable> steppables)
    {
    	System.out.println("public void SetSteppables(ArrayList<ISteppable> steppables)");
    }

    /**
     * Hozzáad egy ISteppable elemet a steppables listájához
     * @param steppable
     * @return
     */
    public static boolean AddSteppble(ISteppable steppable)
    {
    	System.out.println("public static boolean AddSteppble(ISteppable steppable)");
    	return steppables.add(steppable);
    }
    /**
     * Elvesz egy ISteppable elemet a steppables listájából
     * @param steppable
     * @return
     */
    public boolean RemoveSteppable(ISteppable steppable)
    {
    	System.out.println("public boolean RemoveSteppable(ISteppable steppable)");
    	return steppables.remove(steppable);
    }

    /**
     * Visszatér a WaterSpring listájával
     * @return
     */
    public ArrayList<WaterSpring> GetWaterSprings()
    {
    	System.out.println("public ArrayList<WaterSpring> GetWaterSprings()");
    	return waterSprings;
    }

    /**
     * Megad egy WaterSpring lista referenciát
     * @param waterSprings
     */
    public void SetWaterSprings(ArrayList<WaterSpring> waterSprings)
    {
    	System.out.println("public void SetWaterSprings(ArrayList<WaterSpring> waterSprings)");
    }

    /**
     * Hozzáad egy WaterSpring elemet a watersprings listájához
     * @param waterspring
     * @return
     */
    public static boolean AddWaterSpring(WaterSpring waterspring)
    {
    	System.out.println("public static boolean AddWaterSpring(WaterSpring waterspring)");
    	return waterSprings.add(waterspring);
    }

    /**
     * Elvesz egy WaterSpring elemet a watersprings listájából
     * @param waterspring
     * @return
     */
    public boolean RemoveWaterSpring(WaterSpring waterspring)
    {
    	System.out.println("public boolean RemoveWaterSpring(WaterSpring waterspring)");
    	return waterSprings.remove(waterspring);
    }

    /**
     * Visszatér egy játékos hátralévő lépésszámával
     * @return
     */
    public int GetPlayerAction()
    {
    	System.out.println("public int GetPlayerAction()");
    	return playerActionCountInCurrentRound;
    }
    /**
     * Beállítja egy játékos hátralévő lépésszámát
     * @param count
     * @return
     */
    public void SetPlayerAction(int count)
    {
    	System.out.println("public void SetPlayerAction(int count)");
    }
    /**
     * Inkrementálja egy játékos hátralévő lépésszámát
     * @return
     */
    public void IncreasePlayerAction()
    {
    	System.out.println("public void IncreasePlayerAction()");
    }

    /**
     * Ha sikeresen végrehajtott a játékos egy elemi akciót, utána hívjuk.
     * Csökkenti a hátralévő lépésszámát az adott játékosnak,
     * majd a menedzseli a víz folyását a forrásokból és a
     * Steppable interfészt megvalósító objektumokban.
     * @return void
     */
    public static void ActionExecuted()
    {
    	System.out.println("public static void ActionExecuted()");
        FireSourceActions();
        StepSteppables();
    }

    /**
     * A steppables listához ad egy új elemet.
     * @param steppable
     */
    public static void AddSteppable(ISteppable steppable)
    {
    	System.out.println("public static void AddSteppable(ISteppable steppable)");
    	steppables.add(steppable);
        
    }

    /**
     * A forrásokból vizet küld a hozzájuk csatlakozó szomszédos elemekbe
     */
    public static void FireSourceActions()
    {
    	System.out.println("public static void FireSourceActions()");
    }

    /**
     * A szerelő lépéseit kezelő függvény
     */
    public static void MechanicActions()
    {
    	System.out.println("public static void MechanicActions()");
    }

    /**
     * A szabotőr lépéseit kezelő függvény
     */
    public static void SaboteurActions()
    {
    	System.out.println("public static void SaboteurActions()");
    }

    /**
     * A játék futásáért és indításáért felel, ellenőrzi, hogy a játék nem haladt-e túl a megszabott kör limiten.
     */
    public static void StartGame()
    {
    	System.out.println("public static void StartGame()");
    	GameManager.MechanicActions();
        GameManager.SaboteurActions();
    }

    /**
     *  végig iterál a steppables listáján, majd mindegyik ilyen objektumnak meghívja a Step() függvényét.
     */
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
