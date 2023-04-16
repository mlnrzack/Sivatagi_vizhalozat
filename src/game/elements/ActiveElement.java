package game.elements;

import java.util.ArrayList;

import game.players.*;

public abstract class ActiveElement extends Element
{
    protected static ArrayList<Pipe> neighbours = null;
    
    public void setNeighbours(ArrayList<Pipe> neighbours)
    {
    	System.out.println("public void setNeighbours(ArrayList<Pipe> neighbours)");
    }

    public void setNeighbour(Pipe neighbour)
    {
    	System.out.println("public void setNeighbour(Pipe neighbour)");
    }
    
    public boolean AddPipe(Pipe p)
    {
    	System.out.println("public boolean AddPipe(Pipe p)");
    	if(neighbours.add(p));
    	
    	return false;
    }
    
    public static boolean RemovePipe(Pipe p)
    {
    	System.out.println("public static boolean RemovePipe(Pipe p)");
    	if(neighbours.remove(p));
    	
    	return false;
    }
    
    public boolean AcceptPlayer(Player player)
    {
    	System.out.println("public boolean AcceptPlayer(Player player)");
        return AddPlayer(player);
    }
    
    public Pipe DisconnectNeighbourPipe(int neighbourIdx)
    {
    	System.out.println("public Pipe DisconnectNeighbourPipe(int neighbourIdx)");
        // override-olni leszármazottakban, ott megvalósítani ha beállítható.
        System.out.println("Nem csinálunk semmit, az elem nem lecsatlakoztatható.");
        return null;
    }
    
    public ArrayList<Element> GetNeighbours()
    {
    	System.out.println("public ArrayList<Element> GetNeighbours()");
    	return null;
    }
    
    public Pipe PickUpFreePipeEnd()
    {
    	System.out.println("public IPipe PickUpFreePipeEnd()");
        // override-olni leszármazottakban, ott megvalósítani ha felvehető az adott típuson szabad csővég.
        System.out.println("Nem csinálunk semmit, szabad csővég nem felvehető.");
        return null;
    }
    
    public Pump PickUpPump()
    {
    	System.out.println("public IPump PickUpPump()");
        // override-olni leszármazottakban, ott megvalósítani ha felvehető az adott típuson pumpa.
        System.out.println("Nem csinálunk semmit, szabad csővég nem felvehető.");
        return null;
    }
    
    public boolean TryBuildPumpInto(Pump pump)
    {
    	System.out.println("public boolean TryBuildPumpInto(IPump pump)");
        // override-olni leszármazottakban, ott megvalósítani ha be lehet építeni pumpát.
        System.out.println("Nem csinálunk semmit, a pumpa nem beépíthető.");
        return false;
    }

    public boolean TryConnectPipe(Pipe pipeInInventory)
    {
    	System.out.println("public boolean TryConnectPipe(IPipe pipeInInventory)");
    	pipeInInventory.AddNeighbour(this);

        return false;
    }
    
    public boolean TryDamage()
    {
    	System.out.println("public boolean TryDamage()");
        // override-olni leszármazottakban, ott megvalósítani ha tönkretehető.
        System.out.println("Nem csinálunk semmit, a pályaelem nem tönkretehető.");
        return false;
    }

    public boolean TryRepair()
    {
    	System.out.println("public boolean TryRepair()");
        // override-olni leszármazottakban, ott megvalósítani ha javatható.
        System.out.println("Nem csinálunk semmit, a pályaelem nem javítható.");
        return false;
    }

    public boolean TrySetInputOutput(int inputIdx, int outputIdx)
    {
    	System.out.println("public boolean TrySetInputOutput(int inputIdx, int outputIdx)");
        // override-olni leszármazottakban, ott megvalósítani ha beállítható.
        System.out.println("Nem csinálunk semmit, a pályaelem nem állítható.");
        return false;
    }
}
