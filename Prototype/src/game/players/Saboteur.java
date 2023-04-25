package game.players;

import game.*;

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