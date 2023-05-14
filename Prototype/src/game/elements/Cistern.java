package game.elements;

import game.*;
import game.interfaces.*;

import java.util.ArrayList;

public class Cistern extends ActiveElement implements ISteppable
{

    private int _id = 0;
    private static ArrayList<Integer> CisternIds = new ArrayList<>();

    public Cistern()
    {
        int max = CisternIds.get(0);
        for (var item: CisternIds) {
            if (item > max) max = item;
        }
        _id = max+1;
        CisternIds.add(_id);
        typeString = "Cistern";

        GameManager.AddSteppable(this);
    }

    public Cistern(int id){
        _id = id;
        CisternIds.add(_id);
        typeString = "Cistern";

    }

    public Pipe PickUpFreePipeEnd()
    {
        return new Pipe();
    }
    
    public Pump PickUpPump()
    {
        return new Pump();
    }
    
    private void PumpWaterToCisternFromNeighbour(Pipe neighbourPipe)
    {
    	neighbourPipe.SetWaterInside(neighbourPipe.GetWaterInside() - 1);
    	GameManager.SetMechanicsPoints(GameManager.GetMechanincsPoints() + 1);
        SetWaterInside(GetWaterInside() + 1);        
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