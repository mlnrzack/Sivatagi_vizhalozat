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

    /**
     * Megvalósítja a Steppable interfész függvényét. Lépteti a vizet a csőrendszerben.
     * @return
     */
    public boolean Step()
    {
    	System.out.println("public boolean Step()");
        var pumpWaterToOutputDone = false;
        var pumpWaterFromInputDone = false;
        
        if (GetWaterInside() > 0) pumpWaterToOutputDone = PumpWaterToOutput();
        if (GetWaterInside() < 0) pumpWaterFromInputDone = PumpWaterFromInput();

        return false;
    }

    /**
     * Beállítja a pumpa be-és kimenetét.
     * @param input
     * @param output
     */
    public void SetPump(Pipe input, Pipe output)
    {
    	System.out.println("public void SetPump(Pipe input, Pipe output)");
    }

    /**
     * Egy játékos kísérletét vizsgálja, hogy egy pumpán változtasson a be-és kimeneteken.
     * @param neighbourIdxFrom
     * @param neighbourIdxTo
     * @return
     */
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

    /**
     * A pumpa elromlik, és a benne tárolt víz bekerül a sivatagba, növelve a kifolyt víz mennyiségét.
     */
    private void GoWrong()
    {
    	System.out.println("private void GoWrong()");
        Desert.IncreaseWaterFromPipelineNetwork(GetWaterInside());
        SetWaterInside(0);
    }

    /**
     * Megvalósítja a víz folyását a bemenetből.
     * Akkor hívjuk meg ha van szabad kapacitása a tartálynak
     * @return
     */
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

    /**
     * Megvalósítja a víz folyását a kimenetbe.
     * @return
     */
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

    /**
     * Egy valószínűségi változó hatására a pumpa tönkremehet.
     * @return
     */
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

    /**
     * Az adott pumpán álló szerelő javítási kísérletét vizsgáló függvény.
     * @return
     */
    public boolean TryRepair()
    {
    	System.out.println("public boolean TryRepair()");
        if (isWrong == true)
        {
            return true;
        }

        return false;
    }

    /**
     *  Az aktív elemtől elválasztja a hozzá kapcsolódó csövet.
     * @param neighbourIdx
     * @return
     */
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

    /**
     * Egy pumpát beépít egy csőbe, amit előtte ketté választ,hogy közéjük lehessen illeszteni.
     * @param pipe
     * @return
     */
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
