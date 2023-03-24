package game.players;

import java.util.Date;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Mechanic extends Player
{	
    public IPipe pipeInInventory = null;
    public IPump pumpInInventory = null;
    
    public Mechanic() 
    {
        //name = DateTime.Now.ToString();
        GameController.mechanics.Add(this);
    }
   
    public boolean BuildPumpIntoPipe()
    {
        if (pumpInInventory != null)
        {
            if (currentPosition.TryBuildPumpInto(pumpInInventory))
            {
                pumpInInventory = null;

                GameController.ActionExecuted();
                return true;
            }
        }

        System.out.println("Nem sikerül az akció. Próbálkozz úgy, hogy csövön állsz és van nálad pumpa.");
        return false;
    }
    
    public boolean ConnectPipe()
    {
        if (pipeInInventory != null)
        {
            if (currentPosition.TryConnectPipe(pipeInInventory))
            {
                pipeInInventory = null;

                GameController.ActionExecuted();
                return true;
            }
        }

        System.out.println("Nem sikerül az akció. Próbálkozz úgy, aktív elemen állsz és van nálad csővég.");
        return false;
    }

    public boolean DisconnectNeighbourPipe(int neighbourIdx)
    {
        if (pipeInInventory == null)
            pipeInInventory = currentPosition.DisconnectNeighbourPipe(neighbourIdx);

        if (pipeInInventory != null)
        {
            GameController.ActionExecuted();
            return true;
        }

        return false;
    }

    public boolean PickUpFreePipeEnd()
    {
        if (pipeInInventory == null)
        {
            var pickedUpPipe = currentPosition.PickUpFreePipeEnd();

            if (pickedUpPipe != null)
            {
                pipeInInventory = pickedUpPipe;

                GameController.ActionExecuted();
                return true;
            }
        }

        System.out.println("Nem sikerül a felvétel. Próbálkozz ciszternán állva.");
        return false;
    }

    public boolean PickUpPump()
    {
        if (pipeInInventory == null)
        {
            var pickedUpPump = currentPosition.PickUpPump();

            if (pickedUpPump != null)
            {
                pumpInInventory = pickedUpPump;

                GameController.ActionExecuted();
                return true;
            }
        }

        System.out.println("Nem sikerül a felvétel. Próbálkozz ciszternán állva.");
        return false;
    }
    
    public boolean Repair()
    {
        if (currentPosition.TryRepair())
        {
            GameController.ActionExecuted();
            return true;
        }

        return false;
    }
}