package game.interfaces;

import java.util.*;

import game.elements.*;

public interface IPipe extends IElement
{
    void AddNeighbour(ActiveElement newNeighbour);
    void RemoveNeighbour(ActiveElement neighbour);
    ArrayList<ActiveElement> GetNeighboursOfPipe();	//IEnumerable
}
