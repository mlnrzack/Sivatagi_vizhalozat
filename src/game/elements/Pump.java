package game.elements;

import java.util.*;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Pump extends ActiveElement implements ISteppable
{
    private Pipe input = null;
    private Pipe output = null;
    private boolean isWrong;
    
    public Pump()
    {
    	System.out.println("public Pump()");
        GameManager.AddSteppable(this);
    }

    public boolean Step()
    {
    	System.out.println("public boolean Step()");
        var pumpWaterToOutputDone = false;
        var pumpWaterFromInputDone = false;

        // Feltételeket akár be is lehet vinni a meghívott függvényekbe és akkor azok mehetnek a return-be, de most nekem így szimpibb.
        if (GetWaterInside() > 0) pumpWaterToOutputDone = PumpWaterToOutput();
        if (GetWaterInside() < Constants.PumpWaterCapacity) pumpWaterFromInputDone = PumpWaterFromInput();

        return GettingOlder() || pumpWaterToOutputDone || pumpWaterFromInputDone;
    }

    public void SetPump(Pipe input, Pipe output)
    {
    	System.out.println("public void SetPump(Pipe input, Pipe output)");
        this.input = input;
        this.output = output;
    }

    public boolean TrySetInputOutput(int neighbourIdxFrom, int neighbourIdxTo)
    {
    	System.out.println("public boolean TrySetInputOutput(int neighbourIdxFrom, int neighbourIdxTo)");
        if (GetNeighbours().size() > neighbourIdxFrom && neighbourIdxFrom >= 0 
            && neighbourIdxTo < GetNeighbours().size() && neighbourIdxTo >= 0 && neighbourIdxFrom != neighbourIdxTo)
        {
            Pipe from = GetNeighbours()[neighbourIdxFrom];
            Pipe to = GetNeighbours()[neighbourIdxTo];
            input = from;
            output = to;

            return true;
        }

        System.out.println("Pumpa átállítása sikertelen.");
        return false;
    }

    private void GoWrong()
    {
    	System.out.println("private void GoWrong()");
        Desert.IncreaseWaterFromPipelineNetwork(GetWaterInside());
        SetWaterInside(0);
    }

    // Akkor hívjuk meg ha van szabad kapacitása a tartálynak
    public boolean PumpWaterFromInput()
    {
    	System.out.println("public boolean PumpWaterFromInput()");
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
    	System.out.println("public boolean PumpWaterToOutput()");
        if (output.FillWaterTo())
        {
        	DecreaseWater();
            return true;
        }

        return false;
    }

    private boolean GettingOlder()
    {
    	System.out.println("private boolean GettingOlder()");
        if (new Random().nextDouble() < Constants.PumpErrorProbability)
        {
            GoWrong();

            return true;
        }

        return false;
    }

    public boolean TryRepair()
    {
    	System.out.println("public boolean TryRepair()");
        if (isWrong == true)
        {
            isWrong = false;

            return true;
        }

        return false;
    }

    public Pipe DisconnectNeighbourPipe(int neighbourIdx)
    {
    	System.out.println("public Pipe DisconnectNeighbourPipe(int neighbourIdx)");
        if (neighbourIdx < 0 || neighbourIdx >= GetNeighbours().size()) return null;
        Pipe neighbourtoDisconnect = GetNeighbours()[neighbourIdx];

        if (input == neighbourtoDisconnect || output == neighbourtoDisconnect) return null;

        ActiveElement.RemovePipe(neighbourtoDisconnect);
        neighbourtoDisconnect.WaterToDesert();
        neighbourtoDisconnect.RemoveNeighbour(this);

        return neighbourtoDisconnect;
    }

    public boolean GetBuildedInto(Pipe pipe)
    {
    	System.out.println("public boolean GetBuildedInto(Pipe pipe)");
        // Beépítésnél input/output beállítása nélkül kerül a pályára a pumpa, ezt állítani külön elemi művelet, itt nincs rá lehetőség.
        Pipe newPipe = new Pipe();
        // { waterInside = 0, Neighbours = new List<ActiveElement>() { this, pipe.GetNeighboursOfPipe().ToList().First() } };
        pipe.AddNeighbour(this);
        pipe.RemoveNeighbour(pipe.GetNeighboursOfPipe().ToList().First());//??
        AddPipe(newPipe);
        AddPipe(pipe);
        neighbours.Add(pipe);

        return true;
    }
}
