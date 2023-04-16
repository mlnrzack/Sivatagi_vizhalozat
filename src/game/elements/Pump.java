package game.elements;

import java.util.*;

import game.*;
import game.interfaces.*;

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
        
        if (GetWaterInside() > 0) pumpWaterToOutputDone = PumpWaterToOutput();
        if (GetWaterInside() < 0) pumpWaterFromInputDone = PumpWaterFromInput();

        return false;
    }

    public void SetPump(Pipe input, Pipe output)
    {
    	System.out.println("public void SetPump(Pipe input, Pipe output)");
    }

    public boolean TrySetInputOutput(int neighbourIdxFrom, int neighbourIdxTo)
    {
    	System.out.println("public boolean TrySetInputOutput(int neighbourIdxFrom, int neighbourIdxTo)");
        if (GetNeighbours().size() > neighbourIdxFrom && neighbourIdxFrom >= 0 
            && neighbourIdxTo < GetNeighbours().size() && neighbourIdxTo >= 0 && neighbourIdxFrom != neighbourIdxTo)
        {
            Pipe from = (Pipe) GetNeighbours().get(neighbourIdxFrom);
            Pipe to = (Pipe) GetNeighbours().get(neighbourIdxTo);

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
        if (new Random().nextDouble() < 0)
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
            return true;
        }

        return false;
    }

    public Pipe DisconnectNeighbourPipe(int neighbourIdx)
    {
    	System.out.println("public Pipe DisconnectNeighbourPipe(int neighbourIdx)");
        if (neighbourIdx < 0 || neighbourIdx >= GetNeighbours().size()) return null;
        Pipe neighbourtoDisconnect = (Pipe)GetNeighbours().get(neighbourIdx);			

        ActiveElement.RemovePipe(neighbourtoDisconnect);
        neighbourtoDisconnect.WaterToDesert();
        neighbourtoDisconnect.RemoveNeighbour(this);

        return null;
    }

    public boolean GetBuildedInto(Pipe pipe)
    {
    	System.out.println("public boolean GetBuildedInto(Pipe pipe)");
        pipe.AddNeighbour(this);
        pipe.RemoveNeighbour(pipe.GetNeighboursOfPipe().get(0));
        
        AddPipe(new Pipe());
        AddPipe(pipe);

        return false;
    }
}
