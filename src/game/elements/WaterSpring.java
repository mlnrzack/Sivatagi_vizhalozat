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
    	System.out.println("");
        GameManager.AddWaterSpring(this);
    }

    public void FillNeighourPipes()
    {
    	System.out.println("public void FillNeighourPipes()");
    	ArrayList<Element> neighbourPipe = GetNeighbours();
    	for(int i = 0; i < GetNeighbours().size(); i++)
        {
            neighbourPipe.get(i).FillWaterTo();
        }
    }
}