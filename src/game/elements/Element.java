package game.elements;

import java.util.*;

import game.*;
import game.interfaces.*;
import game.players.*;

public abstract class Element
{
    private int waterInside = 0;
    private static ArrayList<Player> players = null;
    
    public int GetWaterInside()
    {
    	System.out.println("public int GetWaterInside()");
    	return 0;
    }

    public void SetWaterInside(int waterInside)
    {
    	System.out.println("public void SetWaterInside(int waterInside)");
    }

    public void DecreaseWater()
    {
    	System.out.println("public void DecreaseWater()");
    }
    
    public void IncreaseWater()
    {
    	System.out.println("public void IncreaseWater()");
    }
    
    public ArrayList<Player> GetPlayers()
    {
    	System.out.println("public ArrayList<Player> GetPlayers()");
    	return null;
    }
    
    public void SetPLayers(ArrayList<Player> players)
    {
    	System.out.println("public void SetPLayers(ArrayList<Player> players)");
    }
    
    public static boolean AddPlayer(Player player)
    {
    	System.out.println("public static boolean AddPlayer(Player player)");
    	if(players.add(player));
    	
    	return false;
    }
    
    // true ha sikerül vizet fogadnia
    public boolean FillWaterTo()
    {
    	System.out.println("public boolean FillWaterTo()");        
        return false;
    }

    public void WaterToDesert()
    {
    	System.out.println("public void WaterToDesert()");
        Desert.IncreaseWaterFromPipelineNetwork(waterInside);
    }

    public boolean RemovePlayer(Player player)
    {
    	System.out.println("public boolean RemovePlayer(Player player)");
    	if(players.remove(player));
    	
    	return false;
    }
    
    public abstract boolean AcceptPlayer(Player player);
    public abstract ArrayList<Element> GetNeighbours();
    public abstract boolean TryRepair();
    public abstract boolean TryDamage();
    public abstract boolean TryBuildPumpInto(Pump pump);
    public abstract Pipe DisconnectNeighbourPipe(int neighbourIdx);
    public abstract boolean TryConnectPipe(Pipe pipeInInventory);
    public abstract Pipe PickUpFreePipeEnd();
    public abstract Pump PickUpPump();
    public abstract boolean TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx);
}
