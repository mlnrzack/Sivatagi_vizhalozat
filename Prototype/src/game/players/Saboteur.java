package game.players;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Saboteur extends Player
{
    public Saboteur()
    {
        GameManager.AddSaboteur(this);
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
}