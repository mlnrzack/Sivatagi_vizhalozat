package game.elements;

import java.util.*;

import game.interfaces.*;
import game.players.*;

/**
 * Ez az osztály valósítja meg a csöveket a játékban
 */
public class Pipe extends Element implements ISteppable
{
    private boolean isWrong;

    private ArrayList<ActiveElement> neighbours = null;

    /**
     * Beépít egy pumpát egy csőre.
     * @param pump
     * @return
     */
    public boolean TryBuildPumpInto(Pump pump)
    {
    	System.out.println("public boolean TryBuildPumpInto(Pump pump)");
    	if(pump.GetBuildedInto(this))
    		return true;
    	
    	return false;
    }

    /**
     * A tönkrement pumpa megjavítása.
     * @return
     */
    public boolean TryRepair()
    {
    	System.out.println("public boolean TryRepair()");
        if (isWrong)
        {
            return true;
        }

        System.out.println("Cső javítása nem sikerül. Nincs elromolva ez az elem.");
        return false;
    }

    /**
     * Az adott elemen álló szabotőr rongálási kísérletét vizsgáló függvény.
     * @return
     */
    public boolean TryDamage()
    {
    	System.out.println("public boolean TryDamage()");
        if (!isWrong)
        {
            return true;
        }

        System.out.println("Cső rongálása nem sikerül. Már el van rontva ez az elem.");
        return false;
    }

    /**
     * Megvalósítja a Steppable interfész függvényét. Lépteti a vizet a csőrendszerben.
     * @return
     */
    public boolean Step()
    {
    	System.out.println("public boolean Step()");
        if (isWrong && GetWaterInside() > 0)
        {
            WaterToDesert();

            return true;
        }
        
        return false;
    }

    /**
     * Visszaadja az elem szomszédjait
     * @return
     */
    public ArrayList<Element> GetNeighbours()
    {
    	System.out.println("public ArrayList<Element> GetNeighbours()");
    	return null;
    }

    /**
     * Egy adott elemhez csövet kísérel a szerelő csatlakoztatni.
     * @param pipeInInventory
     * @return
     */
    public boolean TryConnectPipe(Pipe pipeInInventory)
    {
    	System.out.println("public boolean TryConnectPipe(Pipe pipeInInventory)");
        System.out.println("Bocs tesa ez nem fog menni. Jelenleg nem lehet csövet csőhöz csatlakoztatni.");
        return false;
    }

    /**
     * Hozzáad a cső szomszédsági listájába egy aktív elemet.
     * @param newNeighbour
     */
    public void AddNeighbour(ActiveElement newNeighbour)
    {
    	System.out.println("public void AddNeighbour(ActiveElement newNeighbour)");
        neighbours.add(newNeighbour);
    }

    /**
     *  A játékos felveszi az adott aktív elemből induló szabad végű csövet.
     * @return
     */
    public Pipe PickUpFreePipeEnd()
    {
    	System.out.println("public Pipe PickUpFreePipeEnd()");
        System.out.println("Bocs tesa ez nem fog menni. Jelenleg nem lehet szabad csővég a csövön.");
        return null;
    }

    /**
     * A szerelő a ciszternán állva felvesz egy pumpát.
     * @return
     */
    public Pump PickUpPump()
    {
    	System.out.println("public Pump PickUpPump()");
        System.out.println("Bocs tesa ez nem fog menni. Jelenleg nincs felvehető pumpa csövön.");
        return null;
    }

    /**
     * Az elemre lépő játékos elfogadását valósítja meg.
     * @param player
     * @return
     */
    public boolean AcceptPlayer(Player player)
    {
    	System.out.println("public boolean AcceptPlayer(Player player)");
        AddPlayer(player);

        System.out.println("Cső nem tud fogadni, mert tele van. Válassz más műveletet.");
        return false;
    }

    /**
     * A pumpa be és kifolyásának irányítása.
     * @param neighbourFromIdx
     * @param neighbourToIdx
     * @return
     */
    public boolean TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx)
    {
    	System.out.println("public boolean TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx)");
        System.out.println("Nem csinálunk semmit, a cső input/output nem állítható.");
        return false;
    }

    /**
     * Az aktív elemtől elválasztja a hozzá kapcsolódó csövet.
     * @param neighbourIdx
     * @return
     */
    public Pipe DisconnectNeighbourPipe(int neighbourIdx)
    {
    	System.out.println("public Pipe DisconnectNeighbourPipe(int neighbourIdx)");
        System.out.println("Nem csinálunk semmit, cső szomszédja nem lecsatlakoztatható.");
        return null;
    }

    /**
     * Eltávolít egy kiválasztott szomszédos elemet a cső szomszédjai közül.
     * @param neighbour
     */
    public void RemoveNeighbour(ActiveElement neighbour)
    {
    	System.out.println("public void RemoveNeighbour(ActiveElement neighbour)");
    	neighbours.remove(neighbour);
    }

    /**
     * Visszaadja a csőhöz kapcsolódó aktív elemeket.
     * @return
     */
    public ArrayList<ActiveElement> GetNeighboursOfPipe()
    {
    	System.out.println("public ArrayList<ActiveElement> GetNeighboursOfPipe()");
    	return null;
    }
}