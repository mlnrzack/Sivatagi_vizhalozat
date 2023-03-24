package game.elements;

import java.util.*;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Pipe extends Element implements ISteppable, IPipe
{
    private boolean isWrong; // { get; set; }

    private ArrayList<ActiveElement> neighbours /*{ get; set; }*/ = new ArrayList<ActiveElement>();

    public boolean TryBuildPumpInto(IPump pump)
    {
    	if(pump.GetBuildedInto(this))
    		return true;
    	
    	return false;
    }

    public boolean TryRepair()
    {
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
        if (isWrong && GetWaterInside() > 0)
        {
            WaterToDesert();

            return true;
        }
        
        return false;
    }

    public ArrayList<IElement> GetNeighbours()
    {
    	return neighbours;
    }

    public boolean TryConnectPipe(IPipe pipeInInventory)
    {
        System.out.println("Bocs tesa ez nem fog menni. Jelenleg nem lehet csövet csőhöz csatlakoztatni.");
        return false;
    }

    public void AddNeighbour(ActiveElement newNeighbour)
    {
        neighbours.add(newNeighbour);
    }

    public IPipe PickUpFreePipeEnd()
    {
        System.out.println("Bocs tesa ez nem fog menni. Jelenleg nem lehet szabad csővég a csövön.");
        return null;
    }

    public IPump PickUpPump()
    {
        System.out.println("Bocs tesa ez nem fog menni. Jelenleg nincs felvehető pumpa csövön.");
        return null;
    }

    public boolean AcceptPlayer(Player player)
    {
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
        System.out.println("Nem csinálunk semmit, a cső input/output nem állítható.");
        return false;
    }

    public IPipe DisconnectNeighbourPipe(int neighbourIdx)
    {
        System.out.println("Nem csinálunk semmit, cső szomszédja nem lecsatlakoztatható.");
        return null;
    }

    public void RemoveNeighbour(ActiveElement neighbour)
    {
    	neighbours.remove(neighbour);
    }

    public ArrayList<ActiveElement> GetNeighboursOfPipe()//IEnumerable
    {
    	return neighbours;
    }
}