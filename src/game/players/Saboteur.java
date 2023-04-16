package game.players;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Saboteur extends Player
{
    public Saboteur()
    {
    	System.out.println("public Saboteur()");
        GameManager.AddSaboteur(this);
    }

    public boolean Damage()
    {
    	System.out.println("public boolean Damage()");
        if (GetCurrentPosition().TryDamage())
        {
            GameManager.ActionExecuted();
            return false;
        }
        return false;
    }
}