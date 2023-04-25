package game.elements;

import java.util.ArrayList;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public abstract class ActiveElement extends Element
{
	protected ArrayList<Pipe> neighbours = new ArrayList<Pipe>();

	public void setNeighbours(ArrayList<Pipe> neighbours)
	{
		this.neighbours = neighbours;
	}

	public void setNeighbour(Pipe neighbour)
	{
		neighbours.add(neighbour);
	}

	public boolean AddPipe(Pipe p)
	{
		if (neighbours.add(p))
			return true;
		return false;
	}

	public boolean RemovePipe(Pipe p)
	{
		if (neighbours.remove(p))
			return true;
		return false;
	}

	public boolean AcceptPlayer(Player player)
	{
		return AddPlayer(player);
	}

	public Pipe DisconnectNeighbourPipe(int neighbourIdx)
    {
    	if(GetNeighbours().get(neighbourIdx).GetPlayers().size() > 0) return null;
    	
        if (neighbourIdx < 0 || neighbourIdx >= GetNeighbours().size()) return null;
        Pipe neighbourtoDisconnect = this.neighbours.get(neighbourIdx);

        RemovePipe(neighbourtoDisconnect);
        neighbourtoDisconnect.WaterToDesert();
        neighbourtoDisconnect.RemoveNeighbour(this);

        return neighbourtoDisconnect;
    }

	public ArrayList<Pipe> GetNeighbours()
	{
		return neighbours;
	}

	public Pipe PickUpFreePipeEnd()
	{
		// override-olni leszármazottakban, ott megvalósítani ha felvehető az adott
		// típuson szabad csővég.
		System.out.println("Nem csinálunk semmit, szabad csővég nem felvehető.");
		return null;
	}

	public Pump PickUpPump()
	{
		// override-olni leszármazottakban, ott megvalósítani ha felvehető az adott
		// típuson pumpa.
		System.out.println("Nem csinálunk semmit, szabad csővég nem felvehető.");
		return null;
	}

	public boolean TryBuildPumpInto(Pump pump)
	{
		// override-olni leszármazottakban, ott megvalósítani ha be lehet építeni
		// pumpát.
		System.out.println("Nem csinálunk semmit, a pumpa nem beépíthető.");
		return false;
	}

	public boolean TryConnectPipe(Pipe pipeInInventory)
	{
		if (neighbours.size() < Constants.MaxNeighboursOfActiveElements)
		{
			pipeInInventory.AddNeighbour(this);

			return true;
		}

		return false;
	}

	public boolean TryDamage()
	{
		// override-olni leszármazottakban, ott megvalósítani ha tönkretehető.
		System.out.println("Nem csinálunk semmit, a pályaelem nem tönkretehető.");
		return false;
	}

	public boolean TryRepair()
	{
		// override-olni leszármazottakban, ott megvalósítani ha javatható.
		System.out.println("Nem csinálunk semmit, a pályaelem nem javítható.");
		return false;
	}

	public boolean TrySetInputOutput(int inputIdx, int outputIdx)
	{
		// override-olni leszármazottakban, ott megvalósítani ha beállítható.
		System.out.println("Nem csinálunk semmit, a pályaelem nem állítható.");
		return false;
	}
	
	public boolean TrySetSlippery()
    {
    	System.out.println("Nem sikerült csúszóssá tenni a csövet.");
    	return false;
    }
    
    public boolean TrySetSticky()
    {
    	System.out.println("Nem sikerült ragadóssá tenni a csövet.");
    	return false;
    }
}
