package game.elements;

import game.*;
import game.interfaces.*;

public class Cistern extends ActiveElement implements ISteppable
{
    public Cistern() 
    {
    	GameManager.AddSteppable(this);
    }
    
    public Pipe PickUpFreePipeEnd()
    {
        return new Pipe();
    }
    
    public Pump PickUpPump()
    {
        var newPump = new Pump();
        return newPump;
    }
    
    private void PumpWaterToCisternFromNeighbour(Pipe neighbourPipe)
    {
    	if(neighbourPipe.GetWaterInside() >= 1)
    	{
    		neighbourPipe.SetWaterInside(neighbourPipe.GetWaterInside() - 1);
    		GameManager.SetMechanicsPoints(GameManager.GetMechanincsPoints() + 1);
            SetWaterInside(GetWaterInside() + 1);
    	}        
    }
    
    public boolean Step()
    {
        boolean actionDone = false;
        
        for(int i = 0; i < neighbours.size(); i++)
        {
        	if(neighbours.get(i).GetWaterInside() > 0)
        	{
        		PumpWaterToCisternFromNeighbour(neighbours.get(i));
            	actionDone = true;
        	}            	
        }
        
        return actionDone;
    }
}