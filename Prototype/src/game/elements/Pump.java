package game.elements;

import java.util.*;

import game.*;
import game.interfaces.*;

public class Pump extends ActiveElement implements ISteppable
{
    private Pipe input;
    private Pipe output;
    private boolean broken;
    
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
            Pipe from = this.neighbours.get(neighbourIdxFrom);
            Pipe to = this.neighbours.get(neighbourIdxTo);
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
            input.SetWaterInside(GetWaterInside() - 1);
            SetWaterInside(GetWaterInside() - 1);

            return true;
        }

        return false;
    }

    public boolean PumpWaterToOutput()
    {
        if (output.FillWaterTo())
        {
        	SetWaterInside(GetWaterInside() - 1);
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
        if (broken == true)
        {
            broken = false;

            return true;
        }

        return false;
    }

    public Pipe DisconnectNeighbourPipe(int neighbourIdx)
    {
    	if(GetNeighbours().get(neighbourIdx).GetPlayers().size() > 0) return null;
    	
        if (neighbourIdx < 0 || neighbourIdx >= GetNeighbours().size()) return null;
        Pipe neighbourtoDisconnect = this.neighbours.get(neighbourIdx);

        if (input == neighbourtoDisconnect || output == neighbourtoDisconnect) return null;

        RemovePipe(neighbourtoDisconnect);
        neighbourtoDisconnect.WaterToDesert();
        neighbourtoDisconnect.RemoveNeighbour(this);

        return neighbourtoDisconnect;
    }

    public boolean GetBuildedInto(Pipe pipe)
    {
        // Beépítésnél input/output beállítása nélkül kerül a pályára a pumpa, ezt állítani külön elemi művelet, itt nincs rá lehetőség.
        Pipe newPipe = new Pipe(pipe.GetLeaking(), pipe.GetTimer(), pipe.GetSlippery(), pipe.GetSticky(), new ArrayList<ActiveElement>());
        
        newPipe.AddNeighbour(pipe.GetNeighbours().get(0));
        newPipe.AddNeighbour(this);
        
        pipe.RemoveNeighbour(pipe.GetNeighbours().get(0));
        pipe.AddNeighbour(this);
        
        AddPipe(newPipe);
        AddPipe(pipe);

        return true;
    }
}
