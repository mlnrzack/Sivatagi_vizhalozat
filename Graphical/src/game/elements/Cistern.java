package game.elements;

import java.util.ArrayList;

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
    	GameManager.AddCistern(this);
    	this.SetId("cistern" + GameManager.TryCisternIdSet());
    }

    /**Visszaad egy új csövet.
     */
    public Pipe PickUpFreePipeEnd()
    {
    	Pipe newPipe = new Pipe();
    	this.AddPipe(newPipe);
    	newPipe.AddNeighbour(this);
        return newPipe;
    }
    
    /**Visszaad egy új pumpát.
     */
    public Pump PickUpPump()
    {
    	return new Pump();
    }
    
    /**A szomszédos csőből a ciszternába pumpálja a vizet
     * @param neighbourPipe a szomszéd cső
     */
    public void PumpWaterToCisternFromNeighbour(Pipe neighbourPipe)
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
}