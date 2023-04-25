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
    
    public boolean SetSlipperyPipe()
    {
    	return GetCurrentPosition().TrySetSlippery();
    }
}