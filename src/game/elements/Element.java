package game.elements;

import java.util.*;

import game.players.*;

/**
 * Egy elemet reprezentál, amin a játékosok mozogni tudnak.Absztrakt alaposztály.
 */
public abstract class Element
{
    private int waterInside = 0;
    private static ArrayList<Player> players = null;

    /**
     * Visszaadja a waterInside attribútum értékét.
     * @return
     */
    public int GetWaterInside()
    {
    	System.out.println("public int GetWaterInside()");
    	return 0;
    }

    /**
     * Beállítja a waterInside attribútum értékét.
     * @param waterInside
     */
    public void SetWaterInside(int waterInside)
    {
    	System.out.println("public void SetWaterInside(int waterInside)");
    }

    /**
     * Csökkenti a waterInside értékét.
     */
    public void DecreaseWater()
    {
    	System.out.println("public void DecreaseWater()");
    }

    /**
     * Növeli a waterInside értékét.
     */
    public void IncreaseWater()
    {
    	System.out.println("public void IncreaseWater()");
    }

    /**
     * Visszatér az elemen álló Játékosok listájával
     */
    public ArrayList<Player> GetPlayers()
    {
    	System.out.println("public ArrayList<Player> GetPlayers()");
    	return null;
    }

    /**
     * Átad egy Player lista referenciát
     * @param players
     */
    public void SetPLayers(ArrayList<Player> players)
    {
    	System.out.println("public void SetPLayers(ArrayList<Player> players)");
    }

    /**
     * Hozzáad egy Játékost a játékosok lisájához
     * @param player
     * @return
     */
    public static boolean AddPlayer(Player player)
    {
    	System.out.println("public static boolean AddPlayer(Player player)");
    	if(players.add(player));
    	
    	return false;
    }

    /**
     * Az elem feltöltésére szolgáló metódus.
     * @return true ha sikerül vizet fogadnia
     */

    public boolean FillWaterTo()
    {
    	System.out.println("public boolean FillWaterTo()");        
        return false;
    }
    /**
     * Az elemből a sivatagba folyó víz mennyiségét beállító függvény.
     * @return
     */
    public void WaterToDesert()
    {
    	System.out.println("public void WaterToDesert()");
    }
    /**
     * Az elemen álló játékos másik elemre lépését követő függvény, amely a volt helyéről törli a játékost.
     * @param players
     */
    public boolean RemovePlayer(Player player)
    {
    	System.out.println("public boolean RemovePlayer(Player player)");
    	if(players.remove(player));
    	
    	return false;
    }

    /**
     * Absztrakt metódus, ezt minden leszármazottnak felül kell definiálnia.Az elemre lépő játékos befogadása, amennyiben ez lehetséges.
     * @param player
     * @return
     */
    public abstract boolean AcceptPlayer(Player player);

    /**
     * Absztrakt metódus, ezt minden leszármazottnak felül kell definiálnia.Az elem szomszédjainak listájával visszatérő függvény.
     * @return
     */
    public abstract ArrayList<Element> GetNeighbours();

    /**
     * Absztrakt metódus, ezt minden leszármazottnak felül kell definiálnia.Ha az elem javításra szorul, akkor lehet javítani.
     * @return
     */
    public abstract boolean TryRepair();

    /**
     * Absztrakt metódus, ezt minden leszármazottnak felül kell definiálnia.Ha az elem javításra szorul, akkor lehet javítani.
     * @return
     */
    public abstract boolean TryDamage();

    /**
     * Absztrakt metódus, ezt minden leszármazottnak felül kell definiálnia.Új pumpa lerakásának kísérlete egy cső közepére.
     * @param pump
     * @return
     */
    public abstract boolean TryBuildPumpInto(Pump pump);

    /**
     * Absztrakt metódus, ezt minden leszármazottnak felül kell definiálnia.Szomszédos cső leválasztása.
     * @param neighbourIdx
     * @return
     */
    public abstract Pipe DisconnectNeighbourPipe(int neighbourIdx);

    /**
     * Absztrakt metódus, ezt minden leszármazottnak felül kell definiálnia. Szomszédos cső felcsatolása.
     * @param pipeInInventory
     * @return
     */
    public abstract boolean TryConnectPipe(Pipe pipeInInventory);

    /**
     * Absztrakt metódus, ezt minden leszármazottnak felül kell definiálnia.Szabad csővég felvétele.
     * @return
     */
    public abstract Pipe PickUpFreePipeEnd();

    /**
     * Absztrakt metódus, ezt minden leszármazottnak felül kell definiálnia.Új pumpa felvétele ciszternából.
     * @return
     */
    public abstract Pump PickUpPump();

    /**
     * Absztrakt metódus, ezt minden leszármazottnak felül kell definiálnia.Pumpa továbbítási irányának beállítása.
     * @param neighbourFromIdx
     * @param neighbourToIdx
     * @return
     */
    public abstract boolean TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx);
}
