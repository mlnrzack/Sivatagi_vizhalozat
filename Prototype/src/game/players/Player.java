package game.players;

import game.*;
import game.interfaces.*;

public abstract class Player
{
    private String name;
    protected String typeString = "player";
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

    public String GetType() {return typeString;}
    public boolean Move(int neighbourIdx)
    {
        if (currentPosition.GetNeighbours().size() > neighbourIdx && neighbourIdx >= 0)
        {
            IElement toNeighbour = GetCurrentPosition().GetNeighbours().get(neighbourIdx);
            if (toNeighbour.AcceptPlayer(this))
            {
            	currentPosition.RemovePlayer(this);
                currentPosition = GetCurrentPosition().GetNeighbours().get(neighbourIdx);
                currentPosition.AcceptPlayer(this);
                GameManager.ActionExecuted();
                return true;
            }
        }

        return false;
    }

    public boolean TrySetPump(int neighbourIdxFrom, int neighbourIdxTo)
    {
        if (currentPosition.TrySetInputOutput(neighbourIdxFrom, neighbourIdxTo))
        {
            GameManager.ActionExecuted();
            return true;
        }

        return false;
    }
    
    public boolean Damage()
    {
        if (GetCurrentPosition().TryDamage())
        {
            GameManager.ActionExecuted();
            return true;
        }
        return false;
    }
    
    public boolean SetStickyPipe()
    {
    	return currentPosition.TrySetSticky();
    }
    
    public void Stuck()
    {
    	//todo leragad a játékos
    	//Itt csak az adott köréből zárja ki a játékost, ha kell még további körökből is kizárni, akkor azt valahogy le kellene tárolni kb
    	GameManager.SetPlayerAction(Constants.ActionInRoundPerUser);
    }
}
