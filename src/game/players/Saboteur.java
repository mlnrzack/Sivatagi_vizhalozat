package game.players;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Saboteur extends Player
{
    public Saboteur()
    {
        GameController.sabouteurs.Add(this);
    }

    public boolean Damage()
    {
        if (currentPosition.TryDamage())
        {
            GameController.ActionExecuted();
            return true;
        }
        return false;
    }
}