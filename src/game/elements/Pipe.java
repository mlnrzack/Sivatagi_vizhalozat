package game.elements;

import java.util.*;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Pipe extends Element implements ISteppable
{
    private boolean isWrong;

    private ArrayList<ActiveElement> neighbours = null;

    public boolean TryBuildPumpInto(Pump pump)
    {
    	System.out.println("public boolean TryBuildPumpInto(Pump pump)");
    	if(pump.GetBuildedInto(this))
    		return true;
    	
    	return false;
    }

    public boolean TryRepair()
    {
    	System.out.println("public boolean TryRepair()");
        if (isWrong)
        {
            isWrong = false;

            return true;
        }

        System.out.println("Cső javítása nem sikerül. Nincs elromolva ez az elem.");
        return false;
    }

    public boolean TryDamage()
    {
    	System.out.println("public boolean TryDamage()");
        if (!isWrong)
        {
            isWrong = true;

            return true;
        }

        System.out.println("Cső rongálása nem sikerül. Már el van rontva ez az elem.");
        return false;
    }

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

    public ArrayList<Element> GetNeighbours()
    {
    	System.out.println("public ArrayList<Element> GetNeighbours()");
    	return neighbours;
    }

    public boolean TryConnectPipe(Pipe pipeInInventory)
    {
    	System.out.println("public boolean TryConnectPipe(Pipe pipeInInventory)");
        System.out.println("Bocs tesa ez nem fog menni. Jelenleg nem lehet csövet csőhöz csatlakoztatni.");
        return false;
    }

    public void AddNeighbour(ActiveElement newNeighbour)
    {
    	System.out.println("public void AddNeighbour(ActiveElement newNeighbour)");
        neighbours.add(newNeighbour);
    }

    public Pipe PickUpFreePipeEnd()
    {
    	System.out.println("public Pipe PickUpFreePipeEnd()");
        System.out.println("Bocs tesa ez nem fog menni. Jelenleg nem lehet szabad csővég a csövön.");
        return null;
    }

    public Pump PickUpPump()
    {
    	System.out.println("public Pump PickUpPump()");
        System.out.println("Bocs tesa ez nem fog menni. Jelenleg nincs felvehető pumpa csövön.");
        return null;
    }

    public boolean AcceptPlayer(Player player)
    {
    	System.out.println("public boolean AcceptPlayer(Player player)");
        if (GetPlayers().size() < Constants.AcceptedPlayersInPipe)
        {
            AddPlayer(player);
            return true;
        }

        System.out.println("Cső nem tud fogadni, mert tele van. Válassz más műveletet.");
        return false;
    }

    public boolean TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx)
    {
    	System.out.println("public boolean TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx)");
        System.out.println("Nem csinálunk semmit, a cső input/output nem állítható.");
        return false;
    }

    public Pipe DisconnectNeighbourPipe(int neighbourIdx)
    {
    	System.out.println("public Pipe DisconnectNeighbourPipe(int neighbourIdx)");
        System.out.println("Nem csinálunk semmit, cső szomszédja nem lecsatlakoztatható.");
        return null;
    }

    public void RemoveNeighbour(ActiveElement neighbour)
    {
    	System.out.println("public void RemoveNeighbour(ActiveElement neighbour)");
    	neighbours.remove(neighbour);
    }

    public ArrayList<ActiveElement> GetNeighboursOfPipe()
    {
    	System.out.println("public ArrayList<ActiveElement> GetNeighboursOfPipe()");
    	return neighbours;
    }
}