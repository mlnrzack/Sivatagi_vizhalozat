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
    
    public boolean Step()
    {
        var actionDone = false;

        for(IElement neighbourPipe; neighbourPipe < GetNeighbours().Length(); neighbourPipe++)
        {
        	if(neighbourPipe.GetWaterInside() > 0)
        		PumpWaterToCisternFromNeighbour(neighbourPipe);
        	
        	actionDone = true;
        }

        return actionDone;
    }

    private void PumpWaterToCisternFromNeighbour(IElement neighbourPipe)
    {
        neighbourPipe.DecreaseWater();
        GameController.mechanicsPoints++;
        WaterInside ++;
    }

    public override IPipe PickUpFreePipeEnd()
    {
        return new Pipe();
    }

    public override IPump PickUpPump()
    {
        var newPump = new Pump();
        GameController.AddSteppable(newPump);

        return newPump;
    }
}
