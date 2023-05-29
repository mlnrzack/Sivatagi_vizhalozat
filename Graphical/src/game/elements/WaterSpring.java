package game.elements;

import java.util.List;
import java.util.stream.Collectors;

import game.*;
import game.interfaces.IElement;

public class WaterSpring extends ActiveElement
{
	/**A WaterSpring oztály konstruktora.
	 * Meghívja a GameManager osztály AddWaterSpring függvényét,
	 * ezzel hozzáadva magát a waterSprings listához.
	 */
    public WaterSpring() 
    {
        GameManager.AddWaterSpring(this);
        this.TryIdSet();
    }

    /**Megtölti a vízforrás szomszédos csöveit vízzel.
     */
    public void FillNeighourPipes()
    {    	
    	for(int i = 0; i < this.neighbours.size(); i++)
        {
    		this.neighbours.get(i).FillWaterTo();
        }
    }
    
    public void TryIdSet() {
    	if (!this.GetId().equals(""))
    		return;
    	
    	String name = "waterspring";
    	boolean foundUniqueName = false;
    	int i = 1;
    	while (!foundUniqueName) {
    		String newName = name + i++; 
    		foundUniqueName = true;
    		for (IElement e : GameManager.GetMap()) {
        		if (newName.toUpperCase().equals(e.GetId().toUpperCase()))
        			foundUniqueName = false;
        	}
    		
    		if (foundUniqueName) {
    			this.SetId(newName);
    		}
    	}
    }
}