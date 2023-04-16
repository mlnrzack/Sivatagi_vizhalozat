package game.players;

import java.util.Date;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Mechanic extends Player
{	
    public Pipe pipeInInventory = null;
    public Pump pumpInInventory = null;
    
    public Mechanic() 
    {
    	System.out.println("public Mechanic() ");
        GameManager.AddMechanic(this);
    }
   
    public boolean BuildPumpIntoPipe()
    {
    	System.out.println("public boolean BuildPumpIntoPipe()");
        if (pumpInInventory != null)
        {
            if (GetCurrentPosition().TryBuildPumpInto(pumpInInventory))
            {
                pumpInInventory = null;

                GameManager.ActionExecuted();
                return true;
            }
        }

        System.out.println("Nem sikerül az akció. Próbálkozz úgy, hogy csövön állsz és van nálad pumpa.");
        return false;
    }
    
    public boolean ConnectPipe()
    {
    	System.out.println("public boolean ConnectPipe()");
        if (pipeInInventory != null)
        {
            if (GetCurrentPosition().TryConnectPipe(pipeInInventory))
            {
                pipeInInventory = null;

                GameManager.ActionExecuted();
                return true;
            }
        }

        System.out.println("Nem sikerül az akció. Próbálkozz úgy, aktív elemen állsz és van nálad csővég.");
        return false;
    }

    public boolean DisconnectNeighbourPipe(int neighbourIdx)
    {
    	System.out.println("public boolean DisconnectNeighbourPipe(int neighbourIdx)");
        if (pipeInInventory == null)
            pipeInInventory = GetCurrentPosition().DisconnectNeighbourPipe(neighbourIdx);

        if (pipeInInventory != null)
        {
            GameManager.ActionExecuted();
            return true;
        }

        return false;
    }

    public boolean PickUpFreePipeEnd()
    {
    	System.out.println("public boolean PickUpFreePipeEnd()");
        if (pipeInInventory == null)
        {
            var pickedUpPipe = GetCurrentPosition().PickUpFreePipeEnd();

            if (pickedUpPipe != null)
            {
                pipeInInventory = pickedUpPipe;

                GameManager.ActionExecuted();
                return true;
            }
        }

        System.out.println("Nem sikerül a felvétel. Próbálkozz ciszternán állva.");
        return false;
    }

    public boolean PickUpPump()
    {
    	System.out.println("public boolean PickUpPump()");
        if (pipeInInventory == null)
        {
            Pump pickedUpPump = GetCurrentPosition().PickUpPump();

            if (pickedUpPump != null)
            {
                pumpInInventory = pickedUpPump;

                GameManager.ActionExecuted();
                return true;
            }
        }

        System.out.println("Nem sikerül a felvétel. Próbálkozz ciszternán állva.");
        return false;
    }
    
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