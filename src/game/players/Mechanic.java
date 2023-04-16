package game.players;

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
        GameManager.AddMechanic(this);
    }

    /**
     * Ha a szerelőnél van egy pumpa, akkor egy adott csövön végrehajtható ez a függvény, aminek hatására a cső elválasztódik és a két fél közé kerül egy új pumpa.
     * @return
     */
    public boolean BuildPumpIntoPipe()
    {
    	System.out.println("public boolean BuildPumpIntoPipe()");
        if (GetCurrentPosition().TryBuildPumpInto(pumpInInventory))
        {
            GameManager.ActionExecuted();
            return true;
        }

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
        if (GetCurrentPosition().TryConnectPipe(pipeInInventory))
        {
        	GameManager.ActionExecuted();
            return true;
        }

        System.out.println("Nem sikerül az akció. Próbálkozz úgy, aktív elemen állsz és van nálad csővég.");
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
        pipeInInventory = GetCurrentPosition().DisconnectNeighbourPipe(neighbourIdx);

        if (pipeInInventory != null)
        {
            GameManager.ActionExecuted();
            return true;
        }

        return false;
    }

    /**
     *  Egy szabad végű cső felvétele.
     * @return
     */
    public boolean PickUpFreePipeEnd()
    {
    	System.out.println("public boolean PickUpFreePipeEnd()");
        if (pipeInInventory == null)
        {
            var pickedUpPipe = GetCurrentPosition().PickUpFreePipeEnd();

            if (pickedUpPipe != null)
            {
                GameManager.ActionExecuted();
                return true;
            }
        }

        System.out.println("Nem sikerül a felvétel. Próbálkozz ciszternán állva.");
        return false;
    }

    /**
     *  Egy új pumpa objektum felvétele a ciszternából.
     * @return
     */
    public boolean PickUpPump()
    {
    	System.out.println("public boolean PickUpPump()");
        Pump pickedUpPump = GetCurrentPosition().PickUpPump();

        if (pickedUpPump != null)
        {
            GameManager.ActionExecuted();
            return true;
        }

        System.out.println("Nem sikerül a felvétel. Próbálkozz ciszternán állva.");
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
        {
            GameManager.ActionExecuted();
            return true;
        }

        return false;
    }
}