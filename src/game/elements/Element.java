package game.elements;

import java.util.*;

import game.*;
import game.interfaces.*;
import game.players.*;

public abstract class Element implements IElement
{
    private int waterInside;
    private ArrayList<Player> players = new ArrayList<Player>();
    
    public int GetWaterInside()
    {
    	return waterInside;
    }

    public void SetWaterInside(int waterInside)
    {
    	this.waterInside = waterInside;
    }

    public void DecreaseWater()
    {
    	waterInside--;
    }
    
    public void IncreaseWater()
    {
    	waterInside++;
    }
    
    public ArrayList<Player> GetPlayers()
    {
    	return players;
    }
    
    public void SetPLayers(ArrayList<Player> players)
    {
    	this.players = players;
    }
    
    public boolean AddPlayer(Player player)
    {
    	if(players.add(player))
    		return true;
    	return false;
    }
    
    // true ha sikerül vizet fogadnia
    public boolean FillWaterTo()
    {
        if (waterInside < Constants.PipeCapacity)
        {
            waterInside++;

            return true;
        }

        return false;
    }

    public void WaterToDesert()
    {
        Desert.IncreaseWaterFromPipelineNetwork(waterInside);
        waterInside = 0;
    }

    public boolean RemovePlayer(Player player)
    {
    	if(players.remove(player))
    		return true;
    	
    	return false;
    }
    
    public abstract boolean AcceptPlayer(Player player);
    public abstract ArrayList<IElement> GetNeighbours();	//IEnumerable
    public abstract boolean TryRepair();
    public abstract boolean TryDamage();
    public abstract boolean TryBuildPumpInto(IPump pump);
    public abstract IPipe DisconnectNeighbourPipe(int neighbourIdx);
    public abstract boolean TryConnectPipe(IPipe pipeInInventory);
    public abstract IPipe PickUpFreePipeEnd();
    public abstract IPump PickUpPump();
    public abstract boolean TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx);
}
