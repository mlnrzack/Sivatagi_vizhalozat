package game.elements;

import java.util.*;

import game.*;
import game.interfaces.*;
import game.players.*;

public abstract class Element implements IElement
{
    private int waterInside = 0;
    private String id = "";
    private ArrayList<Player> players = new ArrayList<Player>();

    public int GetWaterInside()
    {
    	return waterInside;
    }

    public void SetWaterInside(int waterInside)
    {
    	this.waterInside = waterInside;
    }

    public String GetId()
    {
    	return id;
    }
    
    public void SetId(String newid)
    {
    	id = newid;
    }
    
    public ArrayList<Player> GetPlayers()
    {
    	return players;
    }
    
    public boolean AddPlayer(Player player)
    {
    	if(players.add(player))
    		return true;
    	return false;
    }
    
    public boolean RemovePlayer(Player player)
    {
    	if(players.remove(player))
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

    public abstract boolean AcceptPlayer(Player player);
    public abstract Pipe DisconnectNeighbourPipe(int neighbourIdx);
    public abstract ArrayList<? extends IElement> GetNeighbours();
    public abstract Pipe PickUpFreePipeEnd();
    public abstract Pump PickUpPump();
    public abstract boolean TryBuildPumpInto(Pump pump);
    public abstract boolean TryConnectPipe(Pipe pipeInInventory);
    public abstract boolean TryDamage();
    public abstract boolean TryRepair();
    public abstract boolean TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx);
}
