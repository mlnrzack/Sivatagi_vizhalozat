package game.elements;

import java.util.ArrayList;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class WaterSpring extends ActiveElement
{
    public WaterSpring() 
    {
        GameManager.AddWaterSpring(this);
    }

    public void FillNeighourPipes()
    {
    	System.out.println("public void FillNeighourPipes()");
    	
    	for(int i = 0; i < this.neighbours.size(); i++)
        {
    		this.neighbours.get(i).FillWaterTo();
        }
    }
}