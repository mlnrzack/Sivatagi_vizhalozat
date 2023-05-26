package game.elements;

import java.util.List;
import java.util.stream.Collectors;

import game.*;
import game.interfaces.*;

public class Cistern extends ActiveElement implements ISteppable
{
	/**A Cistern osztály konstruktora
	 * meghívja a GameManager osztály AddSteppables függvényét,
	 * ezzel hozzáadva magát a léptethető elemekhez.
	 */
    public Cistern()
    {
    	GameManager.AddSteppable(this);
    	GameManager.AddCistern(this);
    	this.SetId("cistern" + GameManager.TryCisternIdSet());
    }

    /**Visszaad egy új csövet.
     */
    public Pipe PickUpFreePipeEnd()
    {
    	Pipe pipe = new Pipe();
    	String name = "pipe";
    	boolean foundUniqueName = false;
    	int i = 1;
    	while (!foundUniqueName) {
    		String pipeName = name + i++; 
    		foundUniqueName = true;
    		for (IElement e : GameManager.GetMap()) {
        		if (pipeName.toUpperCase().equals(e.GetId().toUpperCase()))
        			foundUniqueName = false;
        	}
    		
    		if (foundUniqueName)
    			pipe.SetId(pipeName);
    	}
    	
    	pipe.AddNeighbour(this);
    	this.AddPipe(pipe);
    	
        return pipe;
    }
    
    /**Visszaad egy új pumpát.
     */
    public Pump PickUpPump()
    {
    	Pump pump = new Pump();
        
        String name = "pump";
    	boolean foundUniqueName = false;
    	int i = 1;
    	while (!foundUniqueName) {
    		String pumpName = name + i++; 
    		foundUniqueName = true;
    		for (IElement e : GameManager.GetMap()) {
        		if (pumpName.toUpperCase().equals(e.GetId().toUpperCase()))
        			foundUniqueName = false;
        	}
    		
    		if (foundUniqueName)
    			pump.SetId(pumpName);
    	}
        
        GameManager.AddToMap(pump);
        
        return pump;
    }
    
    /**A szomszédos csőből a ciszternába pumpálja a vizet
     * @param neighbourPipe a szomszéd cső
     */
    private void PumpWaterToCisternFromNeighbour(Pipe neighbourPipe)
    {
    	neighbourPipe.SetWaterInside(neighbourPipe.GetWaterInside() - 1);
        SetWaterInside(GetWaterInside() + 1); 
    	GameManager.SetMechanicsPoints(GameManager.GetMechanincsPoints() + 1);
    }
    
    /**Végigmegy a szomszédos csöveken
     *  meghívja a PumpWaterToCisternFromNeighbour függvényt a vizet tartalmazó csövekre
     */
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
    
    @Override
    public String toString() {
    	List<String> neighbours = this.GetNeighbours().stream().map(p -> p.GetId()).collect(Collectors.toList());
    	
        return this.GetId() + ": {" + "\n"
        		+ "\t" + "neighbours: [" + String.join(", ", neighbours) + "]" + "," + "\n"
        		+ "\t" + "waterInside: " + this.GetWaterInside() + "," + "\n"
        		+ "\t" + "playersHere: [" + String.join(", ", this.GetPlayers().stream().map(p -> p.GetName()).collect(Collectors.toList())) + "]" + "," + "\n"
        		+ "}";        
    }
}