package game.elements;

import java.util.List;
import java.util.stream.Collectors;

import game.*;

public class WaterSpring extends ActiveElement
{
	/**A WaterSpring oztály konstruktora.
	 * Meghívja a GameManager osztály AddWaterSpring függvényét,
	 * ezzel hozzáadva magát a waterSprings listához.
	 */
    public WaterSpring() 
    {
        GameManager.AddWaterSpring(this);
        this.SetId("spring" + GameManager.TryWaterSpringIdSet());
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
    
    public String toString() {
    	List<String> neighbours = this.GetNeighbours().stream().map(p -> p.GetId()).collect(Collectors.toList());
    	
        return this.GetId() + ": {" + "\n"
		        + "\t" + "neighbours: [" + String.join(", ", neighbours) + "]" + "," + "\n"
		        + "\t" + "waterInside: " + this.GetWaterInside() + "," + "\n"
		        + "\t" + "playersHere: [" + String.join(", ", this.GetPlayers().stream().map(p -> p.GetName()).collect(Collectors.toList())) + "]" + "," + "\n"
        		+ "}";
    }
}