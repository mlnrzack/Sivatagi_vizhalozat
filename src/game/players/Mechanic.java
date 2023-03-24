package game.players;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Mechanic extends Player
{
    public Mechanic() 
    {
        Name = DateTime.Now.ToString();
        GameController.mechanics.Add(this);
    }

    public IPipe PipeInInventory /*{ get; set; }*/ = null;
    public IPump PumpInInventory /*{ get; set; }*/ = null;

    // Pumpa és cső megjavítása is.
    public bool Repair()
    {
        if (CurrentPosition.TryRepair())
        {
            GameController.ActionExecuted();
            return true;
        }

        return false;
    }

    public bool DisconnectNeighbourPipe(int neighbourIdx)
    {
        if (PipeInInventory == null)
            PipeInInventory = CurrentPosition.DisconnectNeighbourPipe(neighbourIdx);

        if (PipeInInventory != null)
        {
            GameController.ActionExecuted();
            return true;
        }

        return false;
    }

    public bool ConnectPipe()
    {
        if (PipeInInventory != null)
        {
            if (CurrentPosition.TryConnectPipe(PipeInInventory))
            {
                PipeInInventory = null;

                GameController.ActionExecuted();
                return true;
            }
        }

        System.out.println("Nem sikerül az akció. Próbálkozz úgy, aktív elemen állsz és van nálad csővég.");
        return false;
    }

    public bool BuildPumpIntoPipe()
    {
        if (PumpInInventory != null)
        {
            if (CurrentPosition.TryBuildPumpInto(PumpInInventory))
            {
                PumpInInventory = null;

                GameController.ActionExecuted();
                return true;
            }
        }

        System.out.println("Nem sikerül az akció. Próbálkozz úgy, hogy csövön állsz és van nálad pumpa.");
        return false;
    }

    public bool PickUpFreePipeEnd()
    {
        if (PipeInInventory == null)
        {
            var pickedUpPipe = CurrentPosition.PickUpFreePipeEnd();

            if (pickedUpPipe != null)
            {
                PipeInInventory = pickedUpPipe;

                GameController.ActionExecuted();
                return true;
            }
        }

        System.out.println("Nem sikerül a felvétel. Próbálkozz ciszternán állva.");
        return false;
    }

    public bool PickUpPump()
    {
        if (PipeInInventory == null)
        {
            var pickedUpPump = CurrentPosition.PickUpPump();

            if (pickedUpPump != null)
            {
                PumpInInventory = pickedUpPump;

                GameController.ActionExecuted();
                return true;
            }
        }

        System.out.println("Nem sikerül a felvétel. Próbálkozz ciszternán állva.");
        return false;
    }
}