package game.players;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public abstract class Player
{
    // Ez nem kell sztem, tesztekhez esetleg.
    public String Name;// { get; set; }

    public IElement CurrentPosition;// { get; set; }

    public boolean Move(int neighbourIdx)
    {
        if (CurrentPosition.GetNeighbours().Count() > neighbourIdx && neighbourIdx >= 0)
        {
            IElementx toNeighbour = CurrentPosition.GetNeighbours().ElementAt(neighbourIdx);
            if (toNeighbour.AcceptPlayer(this))
            {
                CurrentPosition = CurrentPosition.GetNeighbours().ElementAt(neighbourIdx);
                CurrentPosition.RemovePlayer(this);

                GameController.ActionExecuted();
                return true;
            }
        }

        return false;
    }

    public boolean TrySetPump(int neighbourIdxFrom, int neighbourIdxTo)
    {
        if (CurrentPosition.TrySetInputOutput(neighbourIdxFrom, neighbourIdxTo))
        {
            GameController.ActionExecuted();
            return true;
        }

        return false;
    }
}
