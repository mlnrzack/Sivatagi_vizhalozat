package game.players;

import game.*;

/**
 * Ez a szabotőr játékosok ostálya
 */
public class Saboteur extends Player
{
    public Saboteur()
    {
    	System.out.println("public Saboteur()");
    }

    /**
     * Egy pumpa elrontása, amin éppen áll a szabotőr
     * @return
     */
    public boolean Damage()
    {
    	System.out.println("public boolean Damage()");
        GetCurrentPosition().TryDamage();        
        GameManager.ActionExecuted();
        return false;
    }
}