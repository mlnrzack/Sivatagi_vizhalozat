package game.elements;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class WaterSpring implements ActiveElement
{
    public WaterSpring() 
    {
        GameController.waterSrpings.Add(this);
    }

    public void FillNeighourPipes()
    {
        foreach (var neighbourPipe in GetNeighbours())
        {
            neighbourPipe.FillWaterTo();
        }
    }
}