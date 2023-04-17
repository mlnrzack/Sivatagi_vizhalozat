package game.players;

import java.io.IOException;

import game.*;
import game.elements.*;

/**
 * A szerelő játékos role-ját testesíti meg.
 */
public class Mechanic extends Player
{	
    public Pipe pipeInInventory = null;
    public Pump pumpInInventory = null;
    
    public Mechanic() 
    {
    	System.out.println("public Mechanic() ");
    }

    /**
     * Ha a szerelőnél van egy pumpa, akkor egy adott csövön végrehajtható ez a függvény, aminek hatására a cső elválasztódik és a két fél közé kerül egy új pumpa.
     * @return
     */
    public boolean BuildPumpIntoPipe()
    {
    	System.out.println("public boolean BuildPumpIntoPipe()");
    	//Pump.AddPipe(new Pipe());
    	//Pump.AddPipe(new Pipe());
        System.out.println("Nem sikerül az akció. Próbálkozz úgy, hogy csövön állsz és van nálad pumpa.");
        return false;
    }

    /**
     * A szerelőnél lévő csövet hozzákötjük egy pumpához
     * @return
     */
    public boolean ConnectPipe()
    {
    	System.out.println("public boolean ConnectPipe()");
        GetCurrentPosition().TryConnectPipe(pipeInInventory);
        return false;
    }

    /**
     * Az aktív elemtől elválasztja a hozzá kapcsolódó csövet.
     * @param neighbourIdx
     * @return
     */
    public boolean DisconnectNeighbourPipe(int neighbourIdx)
    {
    	System.out.println("public boolean DisconnectNeighbourPipe(int neighbourIdx)");
        GetCurrentPosition().DisconnectNeighbourPipe(neighbourIdx);
        return false;
    }

    /**
     *  Egy szabad végű cső felvétele.
     * @return
     */
    public Pipe PickUpFreePipeEnd()
    {
    	System.out.println("public boolean PickUpFreePipeEnd()");
        GetCurrentPosition().PickUpFreePipeEnd();
        GameManager.ActionExecuted();
        return null;
    }

    /**
     *  Egy új pumpa objektum felvétele a ciszternából.
     * @return
     */
    public boolean PickUpPump()
    {
    	System.out.println("public boolean PickUpPump()");
    	
        GetCurrentPosition().PickUpPump();
        return false;
    }

    /**
     * Egy elromlott pumpa megjavítása.
     * @return
     */
    public boolean Repair()
    {
    	System.out.println("public boolean Repair()");
        if (GetCurrentPosition().TryRepair())
        GameManager.ActionExecuted();
        return false;
    }
}