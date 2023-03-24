package game.elements;

import java.util.ArrayList;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public abstract class ActiveElement extends Element
{
    private static ArrayList<IPipe> neighbours = new ArrayList<IPipe>();
    
    public void setNeighbours(ArrayList<IPipe> neighbours)
    {
    	this.neighbours = neighbours;
    }

    public void setNeighbour(IPipe neighbour)
    {
    	neighbours.add(neighbour);
    }
    
    public boolean AddPipe(IPipe p)
    {
    	if(neighbours.add(p))
    		return true;
    	return false;
    }
    
    public static boolean RemovePipe(IPipe p)
    {
    	if(neighbours.remove(p))
    		return true;
    	return false;
    }
    
    public static boolean AcceptPlayer(Player player)
    {
        return AddPlayer(player);
    }
    
    public IPipe DisconnectNeighbourPipe(int neighbourIdx)
    {
        // override-olni leszármazottakban, ott megvalósítani ha beállítható.
        System.out.println("Nem csinálunk semmit, az elem nem lecsatlakoztatható.");
        return null;
    }
    
    public ArrayList<IElement> GetNeighbours()
    {
    	return neighbours;
    }
    
    public IPipe PickUpFreePipeEnd()
    {
        // override-olni leszármazottakban, ott megvalósítani ha felvehető az adott típuson szabad csővég.
        System.out.println("Nem csinálunk semmit, szabad csővég nem felvehető.");
        return null;
    }
    
    public IPump PickUpPump()
    {
        // override-olni leszármazottakban, ott megvalósítani ha felvehető az adott típuson pumpa.
        System.out.println("Nem csinálunk semmit, szabad csővég nem felvehető.");
        return null;
    }
    
    public boolean TryBuildPumpInto(IPump pump)
    {
        // override-olni leszármazottakban, ott megvalósítani ha be lehet építeni pumpát.
        System.out.println("Nem csinálunk semmit, a pumpa nem beépíthető.");
        return false;
    }

    public boolean TryConnectPipe(IPipe pipeInInventory)
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
}
