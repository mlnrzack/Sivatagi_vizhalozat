package game.players;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public abstract class Player
{
    private String name = "";
    private Element currentPosition = null;

    public String GetName()
    {
    	System.out.println("public String GetName()");
    	return name;
    }
    
    public void SetName(String name)
    {
    	System.out.println("public void SetName(String name)");
    	this.name = name;
    }
    
    public Element GetCurrentPosition()
    {
    	System.out.println("public Element GetCurrentPosition()");
    	return currentPosition;
    }
    
    public void SetCurrentPosition(Element newPos)
    {
    	System.out.println("public void SetCurrentPosition(Element newPos)");
    	currentPosition = newPos;
    }
    
    public boolean Move(int neighbourIdx)
    {
    	System.out.println("public boolean Move(int neighbourIdx)");
        if (currentPosition.GetNeighbours().size() > neighbourIdx && neighbourIdx >= 0)
        {
            Element toNeighbour = GetCurrentPosition().GetNeighbours().ElementAt(neighbourIdx);
            if (toNeighbour.AcceptPlayer(this))
            {
                currentPosition = GetCurrentPosition().GetNeighbours().ElementAt(neighbourIdx);
                currentPosition.RemovePlayer(this);

                GameManager.ActionExecuted();
                return true;
            }
        }

        return false;
    }

    public boolean TrySetPump(int neighbourIdxFrom, int neighbourIdxTo)
    {
    	System.out.println("public boolean TrySetPump(int neighbourIdxFrom, int neighbourIdxTo)");
        if (currentPosition.TrySetInputOutput(neighbourIdxFrom, neighbourIdxTo))
        {
            GameManager.ActionExecuted();
            return true;
        }

        return false;
    }
}
