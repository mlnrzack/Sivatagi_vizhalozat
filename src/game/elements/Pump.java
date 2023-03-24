package game.elements;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Pump implements ActiveElement extends ISteppable, IPump
{
    private IPipe Input;// { get; set; }
    private IPipe Output;// { get; set; }

    private boolean IsWrong;// { get; set; }
    
    public Pump()
    {
        GameController.AddSteppable(this);
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
        Input = input;
        Output = output;
    }

    public override boolean TrySetInputOutput(int neighbourIdxFrom, int neighbourIdxTo)
    {
        if (Neighbours.Count() > neighbourIdxFrom && neighbourIdxFrom >= 0 
            && neighbourIdxTo < Neighbours.Count() && neighbourIdxTo >= 0 && neighbourIdxFrom != neighbourIdxTo)
        {
            var from = Neighbours[neighbourIdxFrom];
            var to = Neighbours[neighbourIdxTo];
            Input = from;
            Output = to;

            return true;
        }

        Console.WriteLine("Pumpa átállítása sikertelen.");
        return false;
    }

    private void GoWrong()
    {
        Desert.IncreaseWaterFromPipelineNetwork(GetWaterInside());
        WaterInside = 0;
    }

    // Akkor hívjuk meg ha van szabad kapacitása a tartálynak
    public boolean PumpWaterFromInput()
    {
        if (Input != null && Input.GetWaterInside() > 0)
        {
            Input.DecreaseWater();
            WaterInside--;

            return true;
        }

        return false;
    }

    public boolean PumpWaterToOutput()
    {
        if (Output.FillWaterTo())
        {
            WaterInside--;
            return true;
        }

        return false;
    }

    private boolean GettingOlder()
    {
        if (new Random().NextDouble() < Constants.PumpErrorProbability)
        {
            GoWrong();

            return true;
        }

        return false;
    }

    public override boolean TryRepair()
    {
        if (IsWrong == true)
        {
            IsWrong = false;

            return true;
        }

        return false;
    }

    public override IPipe DisconnectNeighbourPipe(int neighbourIdx)
    {
        if (neighbourIdx < 0 || neighbourIdx >= Neighbours.Count()) return null;
        var neighbourtoDisconnect = Neighbours[neighbourIdx];

        if (Input == neighbourtoDisconnect || Output == neighbourtoDisconnect) return null;

        Neighbours.Remove(neighbourtoDisconnect);
        neighbourtoDisconnect.WaterToDesert();
        neighbourtoDisconnect.RemoveNeighbour(this);

        return neighbourtoDisconnect;
    }

    public boolean GetBuildedInto(IPipe pipe)
    {
        // Beépítésnél input/output beállítása nélkül kerül a pályára a pumpa, ezt állítani külön elemi művelet, itt nincs rá lehetőség.
        var newPipe = new Pipe() { WaterInside = 0, Neighbours = new List<ActiveElement>() { this, pipe.GetNeighboursOfPipe().ToList().First() } };
        pipe.AddNeighbour(this);
        pipe.RemoveNeighbour(pipe.GetNeighboursOfPipe().ToList().First());

        Neighbours.Add(newPipe);
        Neighbours.Add(pipe);

        return true;
    }
}
