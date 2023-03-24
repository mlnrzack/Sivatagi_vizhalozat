package game.players;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Saboteur extends Player
{
    public Saboteur()
    {
        GameController.Sabouteurs.Add(this);
    }

    public boolean Damage()
    {
        if (CurrentPosition.TryDamage())
        {
            GameController.ActionExecuted();
            return true;
        }

        return false;
    }
}