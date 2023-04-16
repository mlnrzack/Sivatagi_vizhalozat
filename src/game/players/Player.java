package game.players;

import game.*;
import game.elements.*;

public abstract class Player
{
    private String name = "";
    private Element currentPosition = null;

    public String GetName()
    {
    	System.out.println("public String GetName()");
    	return null;
    }
    
    public void SetName(String name)
    {
    	System.out.println("public void SetName(String name)");
    }
    
    public Element GetCurrentPosition()
    {
    	System.out.println("public Element GetCurrentPosition()");
    	return null;
    }
    
    public void SetCurrentPosition(Element newPos)
    {
    	System.out.println("public void SetCurrentPosition(Element newPos)");
    }
    
    public boolean Move(int neighbourIdx)
    {
    	System.out.println("public boolean Move(int neighbourIdx)");
        if (currentPosition.GetNeighbours().size() > neighbourIdx && neighbourIdx >= 0)
        {
            Element toNeighbour = GetCurrentPosition().GetNeighbours().get(neighbourIdx);
            if (toNeighbour.AcceptPlayer(this))
            {
                currentPosition = GetCurrentPosition().GetNeighbours().get(neighbourIdx);
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
