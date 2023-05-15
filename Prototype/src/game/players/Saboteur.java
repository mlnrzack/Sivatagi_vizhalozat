package game.players;

import game.*;

public class Saboteur extends Player
{
    public Saboteur()
    {
        GameManager.AddSaboteur(this);
    }
    private final String typeString= "saboteur";

    @Override
    public String GetType(){
        return typeString;
    }
    public boolean SetSlipperyPipe()
    {
    	return GetCurrentPosition().TrySetSlippery();
    }
}