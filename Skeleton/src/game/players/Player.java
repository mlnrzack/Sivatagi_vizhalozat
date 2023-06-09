package game.players;

import game.*;
import game.elements.*;

/**
 * A szereplők alapvető függvényeit definiálja.
 */
public abstract class Player
{
    private String name = "";
    private Element currentPosition = null;

    /**
     * A név gettere
     * @return
     */
    public String GetName()
    {
    	System.out.println("public String GetName()");
    	return null;
    }

    /**
     * A név settere
     * @param name
     */
    public void SetName(String name)
    {
    	System.out.println("public void SetName(String name)");
    }

    /**
     * A játékos jelenlegi pozícójának a gettere
     * @return
     */
    public Element GetCurrentPosition()
    {
    	System.out.println("public Element GetCurrentPosition()");
    	return null;
    }
    /**
     * A játékos jelenlegi pozícójának a settere
     * @param newPos
     * @return
     */
    public void SetCurrentPosition(Element newPos)
    {
    	System.out.println("public void SetCurrentPosition(Element newPos)");
    }

    /**
     * A játékost mozgatja az adott indexű elemre, ha az létezik.
     * @param neighbourIdx
     * @return
     */
    public boolean Move(int neighbourIdx)
    {
    	System.out.println("public boolean Move(int neighbourIdx)");
    	if(neighbourIdx == 1)
    	{
    		new Pump().GetNeighbours();
    		new Pipe().AcceptPlayer(null);
    		new Pump().RemovePlayer(null);
    	}
    	else if(neighbourIdx == 2)
    	{
    		return false;
    	}
        return false;
    }

    /**
     * A játékos megkísérli beállítani annak a pumpának a be-és kimenetét, amelyen áll.
     * @param neighbourIdxFrom
     * @param neighbourIdxTo
     * @return
     */
    public boolean TrySetPump(int neighbourIdxFrom, int neighbourIdxTo)
    {
    	System.out.println("public boolean TrySetPump(int neighbourIdxFrom, int neighbourIdxTo)");
        currentPosition.TrySetInputOutput(neighbourIdxFrom, neighbourIdxTo);
        GameManager.ActionExecuted();
        return false;
    }
}
