package game.elements;

import java.util.*;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Pump extends ActiveElement implements ISteppable, IPump
{
    private IPipe input;
    private IPipe output;
    private boolean isWrong;
    
    public Pump()
    {
        GameManager.AddSteppable(this);
    }

    public boolean Step()
    {
        var pumpWaterToOutputDone = false;
        var pumpWaterFromInputDone = false;

        // Feltételeket akár be is lehet vinni a meghívott függvényekbe és akkor azok mehetnek a return-be, de most nekem így szimpibb.
        if (GetWaterInside() > 0) pumpWaterToOutputDone = PumpWaterToOutput();
        if (GetWaterInside() < Constants.PumpWaterCapacity) pumpWaterFromInputDone = PumpWaterFromInput();

        return GettingOlder() || pumpWaterToOutputDone || pumpWaterFromInputDone;
    }

    public void SetPump(Pipe input, Pipe output)
    {
        this.input = input;
        this.output = output;
    }

    public boolean TrySetInputOutput(int neighbourIdxFrom, int neighbourIdxTo)
    {
        if (GetNeighbours().size() > neighbourIdxFrom && neighbourIdxFrom >= 0 
            && neighbourIdxTo < GetNeighbours().size() && neighbourIdxTo >= 0 && neighbourIdxFrom != neighbourIdxTo)
        {
            IPipe from = GetNeighbours().get(neighbourIdxFrom);
            IPipe to = GetNeighbours().get(neighbourIdxTo);
            input = from;
            output = to;

            return true;
        }

        System.out.println("Pumpa átállítása sikertelen.");
        return false;
    }

    private void GoWrong()
    {
        Desert.IncreaseWaterFromPipelineNetwork(GetWaterInside());
        SetWaterInside(0);
    }

    // Akkor hívjuk meg ha van szabad kapacitása a tartálynak
    public boolean PumpWaterFromInput()
    {
        if (input != null && input.GetWaterInside() > 0)
        {
            input.DecreaseWater();
            DecreaseWater();

            return true;
        }

        return false;
    }

    public boolean PumpWaterToOutput()
    {
        if (output.FillWaterTo())
        {
        	DecreaseWater();
            return true;
        }

        return false;
    }

    private boolean GettingOlder()
    {
        if (new Random().nextDouble() < Constants.PumpErrorProbability)
        {
            GoWrong();

            return true;
        }

        return false;
    }

    public boolean TryRepair()
    {
        if (isWrong == true)
        {
            isWrong = false;

            return true;
        }

        return false;
    }

    public IPipe DisconnectNeighbourPipe(int neighbourIdx)
    {
        if (neighbourIdx < 0 || neighbourIdx >= GetNeighbours().size()) return null;
        IPipe neighbourtoDisconnect = GetNeighbours().get(neighbourIdx);

        if (input == neighbourtoDisconnect || output == neighbourtoDisconnect) return null;

        ActiveElement.RemovePipe(neighbourtoDisconnect);
        neighbourtoDisconnect.WaterToDesert();
        neighbourtoDisconnect.RemoveNeighbour(this);

        return neighbourtoDisconnect;
    }

    public boolean GetBuildedInto(IPipe pipe)
    {
        // Beépítésnél input/output beállítása nélkül kerül a pályára a pumpa, ezt állítani külön elemi művelet, itt nincs rá lehetőség.
        Pipe newPipe = new Pipe();
        // { waterInside = 0, Neighbours = new List<ActiveElement>() { this, pipe.GetNeighboursOfPipe().ToList().First() } };
        pipe.AddNeighbour(this);
        pipe.RemoveNeighbour(pipe.GetNeighboursOfPipe().get(0));//.ToList().First());		??ez így vajon jó?
        AddPipe(newPipe);
        AddPipe(pipe);

        return true;
    }
}
