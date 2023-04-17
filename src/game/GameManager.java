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
	private int round = 0;
	private int mechanicsPoints = 0;
	private int saboteursPoints = 0;
    private ArrayList<ISteppable> steppables = null;
    private ArrayList<WaterSpring> waterSprings = null;
    private ArrayList<Saboteur> saboteurs = null;
    private ArrayList<Mechanic> mechanics = null;
    public Player CurrentPlayer = null;
    private int playerActionCountInCurrentRound = 0;

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
    public void IncreaseMechanicsPoints()
    {
    	System.out.println("public void IncreaseMechanicsPoints()");
    }
    /**
     * Dekrementlálja a Szerelők pontjait
     * @return
     */
    public void DecreaseMechanicsPoints()
    {
    	System.out.println("public void DecreaseMechanicsPoints()");
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
    public void IncreaseSaboteursPoints()
    {
    	System.out.println("public void IncreaseSaboteursPoints()");
    }
    /**
     * Dekrementlálja a Szabotőrök pontjait
     * @return
     */
    public void DecreaseSaboteursPoints()
    {
    	System.out.println("public void DecreaseSaboteursPoints()");
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
    public boolean AddMechanic(Mechanic mechanic)
    {
    	System.out.println("public boolean AddMechanic(Mechanic mechanic)");
    	return false;
    }
    /**
     * Elvesz egy Szerelőt a Szerelők listájából
     * @param mechanic
     * @return
     */
    public boolean RemoveMechanic(Mechanic mechanic)
    {
    	System.out.println("public boolean RemoveMechanic(Mechanic mechanic)");
    	return false;
    }
    /**
     * Visszatér a Szabotőrök listájával
     * @return
     */
    public ArrayList<Saboteur> GetSaboteurs()
    {
    	System.out.println("public ArrayList<Saboteur> GetSaboteurs()");
    	return null;
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
    public boolean AddSaboteur(Saboteur saboteur)
    {
    	System.out.println("public boolean AddSaboteur(Saboteur saboteur)");
    	return false;
    }
    /**
     * Elvesz egy Szabotőrt a Szabotőrök listájából
     * @param saboteur
     * @return
     */
    public boolean RemoveSaboteur(Saboteur saboteur)
    {
    	System.out.println("public boolean RemoveSaboteur(Saboteur saboteur)");
    	return false;
    }

    /**
     * Visszatér az ISteppable interfészt megvalósító elemekkel (azaz olyanokkal, amelyekben folyik víz)
     * @return
     */
    public ArrayList<ISteppable> GetSteppables()
    {
    	System.out.println("public ArrayList<ISteppable> GetSteppables()");
    	return null;
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
    public boolean AddSteppble(ISteppable steppable)
    {
    	System.out.println("public boolean AddSteppble(ISteppable steppable)");
    	return false;
    }
    /**
     * Elvesz egy ISteppable elemet a steppables listájából
     * @param steppable
     * @return
     */
    public boolean RemoveSteppable(ISteppable steppable)
    {
    	System.out.println("public boolean RemoveSteppable(ISteppable steppable)");
    	return false;
    }

    /**
     * Visszatér a WaterSpring listájával
     * @return
     */
    public ArrayList<WaterSpring> GetWaterSprings()
    {
    	System.out.println("public ArrayList<WaterSpring> GetWaterSprings()");
    	return null;
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
    public boolean AddWaterSpring(WaterSpring waterspring)
    {
    	System.out.println("public boolean AddWaterSpring(WaterSpring waterspring)");
    	return false;
    }

    /**
     * Elvesz egy WaterSpring elemet a watersprings listájából
     * @param waterspring
     * @return
     */
    public boolean RemoveWaterSpring(WaterSpring waterspring)
    {
    	System.out.println("public boolean RemoveWaterSpring(WaterSpring waterspring)");
    	return false;
    }

    /**
     * Visszatér egy játékos hátralévő lépésszámával
     * @return
     */
    public int GetPlayerAction()
    {
    	System.out.println("public int GetPlayerAction()");
    	return 0;
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
    public void ActionExecuted()
    {
    	System.out.println("public void ActionExecuted()");
        FireSourceActions();
        StepSteppables();
    }

    /**
     * A steppables listához ad egy új elemet.
     * @param steppable
     */
    public void AddSteppable(ISteppable steppable)
    {
    	System.out.println("public void AddSteppable(ISteppable steppable)");        
    }

    /**
     * A forrásokból vizet küld a hozzájuk csatlakozó szomszédos elemekbe
     */
    public void FireSourceActions()
    {
    	System.out.println("public void FireSourceActions()");
    }

    /**
     * A szerelő lépéseit kezelő függvény
     */
    public void MechanicActions()
    {
    	System.out.println("public void MechanicActions()");
    }

    /**
     * A szabotőr lépéseit kezelő függvény
     */
    public void SaboteurActions()
    {
    	System.out.println("public void SaboteurActions()");
    }

    /**
     * A játék futásáért és indításáért felel, ellenőrzi, hogy a játék nem haladt-e túl a megszabott kör limiten.
     */
    public void StartGame()
    {
    	System.out.println("public void StartGame()");
    	MechanicActions();
        SaboteurActions();
    }

    /**
     *  végig iterál a steppables listáján, majd mindegyik ilyen objektumnak meghívja a Step() függvényét.
     */
    public void StepSteppables()
    {
    	System.out.println("public void StepSteppables()");
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
