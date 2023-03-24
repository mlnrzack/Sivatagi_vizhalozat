package game.players;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Saboteur extends Player
{
    public Saboteur()
    {
        GameController.AddSaboteur(this);
    }

    public boolean Damage()
    {
        if (GetCurrentPosition().TryDamage())
        {
            GameController.ActionExecuted();
            return true;
        }
        return false;
    }
}