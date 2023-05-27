package game.players;

import game.*;

public class Saboteur extends Player
{
	/**A szabotőr osztály konstruktora.
	 * Hozzáadja a létrehozott szabotőrt, a GameManager osztály szabotőrök listájához.
	 */
    public Saboteur()
    {
        GameManager.AddSaboteur(this);
    }
    
    public String GetType()
    {
    	return "saboteur";
    }
    
    /**A szabotőr megpróbálja csúszóssá tenni az alatta lévő csövet
     * @return a csúszóssátétel sikeressége.
     */
    public boolean SetSlipperyPipe()
    {
    	if(GetCurrentPosition().TrySetSlippery())
    	{
    		GameManager.ActionExecuted();
    		
    		System.out.println(this.GetName() + " csúszóssá tette a csövet: " + this.GetCurrentPosition().GetId());
    		return true;
    	}
    	
    	System.out.println(this.GetName() + " nem tudott csövet csúszóssá tenni.");
    	return false;
    }
}