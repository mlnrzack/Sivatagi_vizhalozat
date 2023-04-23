package game.elements;

import java.util.*;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Pipe extends Element implements ISteppable, IPipe
{
    private boolean leaking; // { get; set; }
    private boolean leakable = true; 					//lyukasztaható-e
    private Random noLeakageTimer = new Random(0);		//lyukasztás tiltási ideje, mi lépteti?
    private boolean slippery = false;					//csúszós-e
    private boolean sticky = false;						//ragadós-e
    private ArrayList<ActiveElement> neighbours = new ArrayList<ActiveElement>();

    public Pipe()
    {
    	
    }
    
    public boolean TryBuildPumpInto(IPump pump)
    {
    	if(pump.GetBuildedInto(this))
    		return true;
    	
    	return false;
    }

    public boolean TryRepair()
    {
        if (leaking)
        {
        	leaking = false;
        	noLeakageTimer = new Random(1); //itt állítódik be, hogy mennyi ideig nem lehet lyukasztani foltozás után
        	leakable = false;
        	
            return true;
        }

        System.out.println("Cső javítása nem sikerül. Nincs elromolva ez az elem.");
        return false;
    }

    public boolean TryDamage()
    {
        if (!leaking)
        {
        	leaking = true;
        	
            return true;
        }

        System.out.println("Cső rongálása nem sikerül. Már el van rontva ez az elem.");
        return false;
    }

    public boolean Step()
    {
        if (leaking && GetWaterInside() > 0)
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
    
    public void SetLeakable()
    {
    	if(noLeakageTimer.toString() == "0")		//ha elfogy a timer
    	{
    		leakable = true;						//visszaállítja a leakable értékét true-ra
    	}
    }
    
    public boolean GetSlippery()
    {
    	return slippery;
    }
    
    public void SetSlippery()
    {
    	slippery ^= true;			//XOR-ral beállítódik az érték híváskor
    }
    
    public boolean GetSticky()
    {
    	return sticky;
    }
    
    public void SetSticky()
    {
    	sticky ^= true;				//XOR-ral beállítódik az érték híváskor
    }
    
    public boolean SlipperyPipe()
    {
    	//TODO
    	//GetNeighbours()
    	//player.move(valamelyik szomszéd)
    	return false;
    }
    
    public boolean StickyPipe()
    {
    	//TODO
    	//player-re körkimaradás, vagyis, bármennyi lépése is volt a körből nem lép többet.
    	SetSticky();
    	return false;
    }
}