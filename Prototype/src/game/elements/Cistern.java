package game.elements;

import game.*;
import game.interfaces.*;

public class Cistern extends ActiveElement implements ISteppable
{
	/**a Cistern osztály konstruktora
	 * meghívja a GameManager osztály AddSteppables függvényét,
	 * ezzel hozzáadva magát a léptethető elemekhez
	 */
    public Cistern()
    {
    	GameManager.AddSteppable(this);
    }

    /**visszaad egy új csövet
     */
    public Pipe PickUpFreePipeEnd()
    {
        return new Pipe();
    }
    
    /**visszaad egy új pumpát
     */
    public Pump PickUpPump()
    {
        return new Pump();
    }
    
    /** szomszédos csőből a ciszternába pumpálja a vizet
     * @param neighbourPipe a szomszéd cső
     */
    private void PumpWaterToCisternFromNeighbour(Pipe neighbourPipe)
    {
    	neighbourPipe.SetWaterInside(neighbourPipe.GetWaterInside() - 1);
    	GameManager.SetMechanicsPoints(GameManager.GetMechanincsPoints() + 1);
        SetWaterInside(GetWaterInside() + 1);        
    }
    
    /**végigmegy a szomszédos csöveken
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