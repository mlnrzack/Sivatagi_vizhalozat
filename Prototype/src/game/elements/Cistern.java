package game.elements;

import java.util.*;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Cistern extends ActiveElement implements ISteppable
{
    public Cistern() 
    {
    	System.out.println("public Cistern()");
        GameManager.AddSteppable(this);
    }
    
    public Pipe PickUpFreePipeEnd()
    {
    	System.out.println("public Pipe PickUpFreePipeEnd()");
        return new Pipe();
    }
    
    public Pump PickUpPump()
    {
    	System.out.println("public Pump PickUpPump()");
        var newPump = new Pump();
        GameManager.AddSteppable(newPump);

        return newPump;
    }
    
    private void PumpWaterToCisternFromNeighbour(Pipe neighbourPipe)
    {
    	System.out.println("private void PumpWaterToCisternFromNeighbour(Element neighbourPipe)");
        neighbourPipe.DecreaseWater();
        GameManager.IncreaseMechanicsPoints();
        IncreaseWater();
    }
    
    public boolean Step()
    {
    	System.out.println("public boolean Step()");
        boolean actionDone = false;
        
        for(int i = 0; i < neighbours.size(); i++)
        {
        	if(neighbours.get(i).GetWaterInside() > 0)
            	PumpWaterToCisternFromNeighbour(neighbours.get(i));
        	
        	actionDone = true;
        }
        
        return actionDone;
    }
}