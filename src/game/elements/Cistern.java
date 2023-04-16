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
    
    private void PumpWaterToCisternFromNeighbour(Element neighbourPipe)
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
        if(neighbourPipe.GetWaterInside() > 0)
        	PumpWaterToCisternFromNeighbour(neighbourPipe);
        	
        actionDone = true;
        return actionDone;
    }
}