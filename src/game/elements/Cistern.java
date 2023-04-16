package game.elements;

import java.util.*;

import game.*;
import game.interfaces.*;

/**
 * A ciszternák a városok vízforrásai, ezek gyűjtik a szerelők által gyűjtött vizet.
 */
public class Cistern extends ActiveElement implements ISteppable
{
    public Cistern() 
    {
    	System.out.println("public Cistern()");
        GameManager.AddSteppable(this);
    }

    /**
     * Ciszternán álló szerelő felveheti az onnan kiinduló szabad vegű csövet.
     * @return Pipe a kiválaszott cső referenciája
     */
    public Pipe PickUpFreePipeEnd()
    {
    	System.out.println("public Pipe PickUpFreePipeEnd()");
        return new Pipe();
    }

    /**
     * A szerelő magához vehet egy Pumpa objektumot.
     * @return Pump
     */

    public Pump PickUpPump()
    {
    	System.out.println("public Pump PickUpPump()");
        GameManager.AddSteppable(new Pump());

        return null;
    }

    /**
     * A ciszternákba érkező csövekből, amiben víz van ezzel a metódussal kerülnek a ciszternába
     * @param neighbourPipe
     */
    private void PumpWaterToCisternFromNeighbour(Element neighbourPipe)
    {
    	System.out.println("private void PumpWaterToCisternFromNeighbour(Element neighbourPipe)");
        neighbourPipe.DecreaseWater();
        GameManager.IncreaseMechanicsPoints();
        IncreaseWater();
    }

    /**
     * A függvény hatására hajtódnak végre az elem „lépései”
     * @return boolean
     */
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