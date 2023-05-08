package game.elements;

import game.*;

public class WaterSpring extends ActiveElement
{
    public WaterSpring() 
    {
        GameManager.AddWaterSpring(this);
    }

    public void FillNeighourPipes()
    {    	
    	for(int i = 0; i < this.neighbours.size(); i++)
        {
    		this.neighbours.get(i).FillWaterTo();
        }
    }
}