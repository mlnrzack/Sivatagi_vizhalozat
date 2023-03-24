package game.elements;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Cistern extends ActiveElement implements ISteppable
{
    public Cistern() 
    {
        GameController.AddSteppable(this);
    }
    
    public IPipe PickUpFreePipeEnd()
    {
        return new Pipe();
    }
    
    public IPump PickUpPump()
    {
        var newPump = new Pump();
        GameController.AddSteppable(newPump);

        return newPump;
    }
    
    private void PumpWaterToCisternFromNeighbour(IElement neighbourPipe)
    {
        neighbourPipe.DecreaseWater();
        GameController.IncreaseMechanicsPoints();
        IncreaseWater();
    }
    
    public boolean Step()
    {
        var actionDone = false;

        //for(IElement neighbourPipe; neighbourPipe < GetNeighbours().size(); neighbourPipe++)
        {
        	if(neighbourPipe.GetWaterInside() > 0)
        		PumpWaterToCisternFromNeighbour(neighbourPipe);
        	
        	actionDone = true;
        }

        return actionDone;
    }
}