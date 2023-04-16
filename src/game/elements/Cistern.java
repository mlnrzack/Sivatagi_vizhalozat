package game.elements;

import java.util.*;

import game.*;
import game.interfaces.*;

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
        GameManager.AddSteppable(new Pump());

        return null;
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
        ArrayList<Element> neighbourPipe = GetNeighbours();
        
        for(int i = 0; i < GetNeighbours().size(); i++)
        {
        	if(neighbourPipe.get(i).GetWaterInside() > 0)
            	PumpWaterToCisternFromNeighbour(neighbourPipe.get(i));
        	
        	actionDone = true;
        }
        
        return actionDone;
    }
}