package game.elements;

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
    	for(IElement neighbourPipe;;)
        //foreach (var neighbourPipe in GetNeighbours())
        {
            neighbourPipe.FillWaterTo();
        }
    }
}