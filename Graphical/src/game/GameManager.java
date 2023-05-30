package game;

import java.util.*;

import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class GameManager
{
	private static int round = 0;																	//A kör száma.
	private static int mechanicsPoints = 0;															//A szerelők pontszáma.
	private static int saboteursPoints = 0;															//A szabotőrök pontszáma.
	private static ArrayList<IElement> map = new ArrayList<IElement>();								//A térképen lévő összes elem.
	private static ArrayList<Pipe> pipes = new ArrayList<Pipe>();									//A térképen lévő csövek listája.
	private static ArrayList<Pump> pumps = new ArrayList<Pump>();									//A térképen lévő pumpák listája.
	private static ArrayList<Cistern> cisterns = new ArrayList<Cistern>();							//A térképen lévő pumpák listája. 
    private static ArrayList<ISteppable> steppables = new ArrayList<ISteppable>();					//A léptethetők listája(ciszternák, csövek, pumpák).
    private static ArrayList<WaterSpring> waterSprings = new ArrayList<WaterSpring>();				//A vízforrások listája.
    private static ArrayList<Saboteur> saboteurs = new ArrayList<Saboteur>();						//A szabotőrök listája.
    private static ArrayList<Mechanic> mechanics = new ArrayList<Mechanic>();						//A szerelők listája.
    private static Mechanic currentMechanicPlayer = null;											//Az aktuális játékos.
    private static Saboteur currentSaboteurPlayer = null;											//Az aktuális játékos.
    private static int playerActionCountInCurrentRound = 0;											//Az aktuális játékos körben tett lépéseinek száma.

    /**
     * @param e
     */
    public static void AddToMap(IElement e)
    {
    	map.add(e);
    	System.out.println(e.GetId() + " hozzáadva a pályához.");
    }

    /**
     * @return
     */
    public static ArrayList<Pipe> GetPipes()
    {
    	return pipes;
    }

    /**Az aktuális körszám visszaadása.
     * @return az aktuális körszám.
     */
    public static int GetRound()
    {
        return round;
    }

    /**Az aktuális körszám beállítása adott értékre.
     * @param round az adott érték.
     */
    public static void SetRound(int round)
    {
        GameManager.round = round;
    }

    /**A szerelők ponjainak visszaadása.
     * @return a szerelők pontjai.
     */
    public static int GetMechanincsPoints()
    {
        return mechanicsPoints;
    }

    /**A szerelők ponjainak beállítása adott értékre.
     * @param points az adott érték.
     */
    public static void SetMechanicsPoints(int points)
    {
        mechanicsPoints = points;
    }

    /**A szabotőrök ponjainak visszaadása.
     * @return a szabotőrök pontjai.
     */
    public static int GetSaboteurPoints()
    {
        return saboteursPoints;
    }

    /**A szabotőrök pontjainak beállítása adott értékre.
     * @param points az adott érték.
     */
    public static void SetSaboteursPoints(int points)
    {
        saboteursPoints = points;
    }

    /**Térkép átadása külső osztályoknak.
     * @return a térkép elemeinek listája.
     */
    public static ArrayList<IElement> GetMap()
    {
        return map;
    }

    /**Beállítja a térképet egy a kapott paraméter szerintire.
     * @param map a kapott térkép.
     */
    public static void SetMap(ArrayList<IElement> map)
    {
        GameManager.map = map;
    }

    /**A szerelők listájának visszaadása.
     * @return a szerelők listája.
     */
    public static ArrayList<Mechanic> GetMechanics()
    {
        return mechanics;
    }
    
    /**Adott szerelő karakter hozzáfűzése a szerelők listához.
     * @param mechanic a hozzáfűzendő szerelő.
     * @return a hozzáfűzés sikeressége.
     */
    public static boolean AddMechanic(Mechanic mechanic)
    {
        return mechanics.add(mechanic);
    }

    /**Beállítja a külső osztálytól kapott értékre a szerelők listáját.
     * @param mechanics a kapott érték.
     */
    public static void SetMechanics(ArrayList<Mechanic> mechanics)
    {
    	GameManager.mechanics = mechanics;
    }

    /**A szabotőrök listájának visszaadása.
     * @return a szabotőrök listája.
     */
    public static ArrayList<Saboteur> GetSaboteurs()
    {
        return saboteurs;
    }

    /**Beállítja a külső osztálytól kapott értékre a szerelők listáját.
     * @param saboteurs a kapott érték.
     */
    public static void SetSaboteurs(ArrayList<Saboteur> saboteurs)
    {
        GameManager.saboteurs = saboteurs;
    }

    /**Egy szabotőr felvétele a szabotőrök listájához.
     * @param saboteur az adott szabotőr.
     * @return a felvétel sikeressége.
     */
    public static boolean AddSaboteur(Saboteur saboteur)
    {
        return saboteurs.add(saboteur);
    }

    /**A léptethetők listájának átadása más osztályok felé.
     * @return a léptethetők listája.
     */
    public static ArrayList<ISteppable> GetSteppables()
    {
        return steppables;
    }

    /**A léptethetők (ciszterna, cső, pumpa) listájának beállítása egy másik listából.
     * @param steppables a másik lista.
     */
    public static void SetSteppables(ArrayList<ISteppable> steppables)
    {
        GameManager.steppables = steppables;
    }

    /**Adott léptethető elem(ciszterna, cső, pumpa) felvétele a léptethetők listájába.
     * @param steppable az adott elem.
     * @return a felvétel sikeressége.
     */
    public static boolean AddSteppable(ISteppable steppable)
    {
        return steppables.add(steppable);
    }

    /**Adott léptethető elem(ciszterna, cső, pumpa) levétele a léptethetők listájából.
     * @param steppable az adott elem.
     * @return a levétel sikeressége.
     */
    public static boolean RemoveSteppable(ISteppable steppable)
    {
        return steppables.remove(steppable);
    }

    /**Adott cső felvétele a csövek listájába.
     * @param pipe steppable az adott elem.
     * @return a felvétel sikeressége.
     */
    public static boolean AddPipe(Pipe pipe)
    {
        return pipes.add(pipe);
    }

    /**Adott cső felvétele a csövek listájába.
     * @param pump steppable az adott elem.
     * @return a felvétel sikeressége.
     */
    public static boolean AddPump(Pump pump)
    {
        return pumps.add(pump);
    }

    /**Adott cső felvétele a csövek listájába.
     * @param cistern steppable az adott elem.
     * @return a felvétel sikeressége.
     */
    public static boolean AddCistern(Cistern cistern)
    {
        return cisterns.add(cistern);
    }

    /**A térképen lévő ciszternák listájának átadása más osztályok felé.
     * @return a ciszternák listája.
     */
    public static ArrayList<Cistern> GetCisterns()
    {
        return cisterns;
    }

    /**A térképen lévő pumpák listájának átadása más osztályok felé.
     * @return a pumpák listája.
     */
    public static ArrayList<Pump> GetPumps()
    {
        return pumps;
    }

    /**A térképen lévő vízforrások listájának átadása más osztályok felé.
     * @return a vízforrások listája.
     */
    public static ArrayList<WaterSpring> GetWaterSprings()
    {
        return waterSprings;
    }

    /**A térképen lévő vízforrások listájának beállítása adott listából. 
     * @param waterSprings az adott lista.
     */
    public static void SetWaterSprings(ArrayList<WaterSpring> waterSprings)
    {
        GameManager.waterSprings = waterSprings;
    }

    /**A térképre adott vízforrás felhelyezése.
     * @param waterspring az adott vízforrás.
     * @return a felhelyezés sikeressége.
     */
    public static boolean AddWaterSpring(WaterSpring waterspring)
    {
        return waterSprings.add(waterspring);
    }

    /**A térképről adott vízforrás levétele.
     * @param waterspring az adott vízforrás.
     * @return levétel sikeressége.
     */
    public static boolean RemoveWaterSpring(WaterSpring waterspring)
    {
        return waterSprings.remove(waterspring);
    }

    /**Átadja az éppen aktuális szerelő játékost külső osztályoknak.
     * @return az aktuális szerelő.
     */
    public static Mechanic GetCurrentMechanic()
    {
        return currentMechanicPlayer;
    }

    /**Az aktuális szerelő játékos beállítása adott játékos.
     * @param current az adott játékos.
     */
    public static void SetCurrentMechanic(Mechanic current)
    {
        currentMechanicPlayer = current;
    }

    /**Átadja az éppen aktuális szabotőr játékost külső osztályoknak.
     * @return az aktuális szabotőr.
     */
    public static Saboteur GetCurrentSaboteur()
    {
        return currentSaboteurPlayer;
    }

    /**Az aktuális szabotőr játékos beállítása adott játékos.
     * @param current az adott játékos.
     */
    public static void SetCurrentSaboteur(Saboteur current)
    {
        currentSaboteurPlayer = current;
    }

    /**Adott karakter körbeli lépésszámának átadása más osztályoknak.
     * @return adott karakter lépésszáma
     */
    public static int GetPlayerAction()
    {
        return playerActionCountInCurrentRound;
    }

    /**Adott karakter körbeli lépésszámának beállítása paraméterként kapott értékre.
     */
    public static void SetPlayerAction(int count)
    {
        playerActionCountInCurrentRound = count;
    }

    /**Adot karakter körbeli lépésszámának növelése.
     */
    public static void IncreasePlayerAction()
    {
        playerActionCountInCurrentRound++;
    }

    /**Megkersi a legkisebb, még nem használt id-t a csövekből.
     * @return az adott id szám értéke.
     */
    public static int TryPipeIdSet()
    {
    	int id = 1;
    	for(int i = 0; i < pipes.size(); i++)
    	{
    		String piid = "pipe" + id;
    		if(pipes.get(i).GetId().compareTo(piid) == 0)
    			id++;
    	}
    	return id;
    }
    
    /**Megkersi a legkisebb, még nem használt id-t a pumpákból.
     * @return az adott id szám értéke.
     */
    public static int TryPumpIdSet()
    {
    	int id = 1;
    	for(int i = 0; i < pumps.size(); i++)
    	{
    		String puid = "pump" + id;
    		if(pumps.get(i).GetId().compareTo(puid) == 0)
    			id++;
    	}
    	return id;
    }
    
    /**Megkersi a legkisebb, még nem használt id-t a ciszternákból.
     * @return az adott id szám értéke.
     */
    public static int TryCisternIdSet()
    {
    	int id = 1;
    	for(int i = 0; i < cisterns.size(); i++)
    	{
    		String cid = "cistern" + id;
    		if(cisterns.get(i).GetId().compareTo(cid) == 0)
    			id++;
    	}
    	return id;
    }
    
    /**Megkersi a legkisebb, még nem használt id-t a vízforrásokból.
     * @return az adott id szám értéke.
     */
    public static int TryWaterSpringIdSet()
    {
    	int id = 1;
    	for(int i = 0; i < waterSprings.size(); i++)
    	{
    		String spid = "spring" + id;
    		if(waterSprings.get(i).GetId().compareTo(spid) == 0)
    			id++;
    	}
    	return id;
    }
    
    /**Ha sikesen végrehajt egy játékos egy elemi akciót, akkor ez a függvény hívódik meg.
     * Növeli az adott játékos lépésszámát, valamint lépteti a vizet a rendszerben.
     */
    public static void ActionExecuted()
    {
        IncreasePlayerAction();
        StepSteppables();
        FireSourceActions();
    }

    /**A forrásokból a szomszédos elemekbe folyatja a vizet.
     * A források minden szomszédos elemébe(rákapcsolt cső) juttat vizet.
     */
    public static void FireSourceActions()
    {
    	for(int i = 0; i < waterSprings.size(); i++)
    	{
    		waterSprings.get(i).FillNeighourPipes();
    	}
    }

    /**A térkép összes - kivéve vízforrás - elemében történő vízfolyatás.
     */
    public static void StepSteppables()
    {

    	for(int i = steppables.size() - 1; i >= 0; i--)
    	{
        	steppables.get(i).Step();
    	}
    }
}


