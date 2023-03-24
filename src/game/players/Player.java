package game.players;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public abstract class Player
{
    private String name;
    private IElement currentPosition;

    public String GetName()
    {
    	return name;
    }
    
    public void SetName(String name)
    {
    	this.name = name;
    }
    
    public IElement GetCurrentPosition()
    {
    	return currentPosition;
    }
    
    public void SetCurrentPosition(IElement newPos)
    {
    	currentPosition = newPos;
    }
    
    public boolean Move(int neighbourIdx)
    {
        if (currentPosition.GetNeighbours().size() > neighbourIdx && neighbourIdx >= 0)
        {
            IElement toNeighbour = GetCurrentPosition().GetNeighbours().ElementAt(neighbourIdx);
            if (toNeighbour.AcceptPlayer(this))
            {
                currentPosition = GetCurrentPosition().GetNeighbours().ElementAt(neighbourIdx);
                currentPosition.RemovePlayer(this);

                GameController.ActionExecuted();
                return true;
            }
        }

        return false;
    }

    public boolean TrySetPump(int neighbourIdxFrom, int neighbourIdxTo)
    {
        if (currentPosition.TrySetInputOutput(neighbourIdxFrom, neighbourIdxTo))
        {
            GameController.ActionExecuted();
            return true;
        }

        return false;
    }
}
