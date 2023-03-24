package game.interfaces;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public interface IPipe extends IElement
{
    void AddNeighbour(ActiveElement newNeighbour);
    void RemoveNeighbour(ActiveElement neighbour);
    ArrayList<ActiveElement> GetNeighboursOfPipe();	//IEnumerable
}
