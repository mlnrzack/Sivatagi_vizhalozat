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
        PumpWaterToOutput();
        PumpWaterFromInput();
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
    	System.out.println("Pump.public boolean TrySetInputOutput(int neighbourIdxFrom, int neighbourIdxTo)");
        return false;
    }

    /**
     * A pumpa elromlik, és a benne tárolt víz bekerül a sivatagba, növelve a kifolyt víz mennyiségét.
     */
    public void GoWrong()
    {
    	System.out.println("public void GoWrong()");
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
        input.DecreaseWater();
        DecreaseWater();
        return false;
    }

    /**
     * Megvalósítja a víz folyását a kimenetbe.
     * @return
     */
    public boolean PumpWaterToOutput()
    {
    	System.out.println("public boolean PumpWaterToOutput()");
        output.FillWaterTo();
        DecreaseWater();
        return false;
    }

    /**
     * Egy valószínűségi változó hatására a pumpa tönkremehet.
     * @return
     */
    public boolean GettingOlder()
    {
    	System.out.println("public boolean GettingOlder()");
        GoWrong();
        return false;
    }

    /**
     * Az adott pumpán álló szerelő javítási kísérletét vizsgáló függvény.
     * @return
     */
    public boolean TryRepair()
    {
    	System.out.println("public boolean TryRepair()");
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
    	if(neighbourIdx == 1)
    	{
    		Pipe neighbourtoDisconnect = (Pipe)GetNeighbours().get(neighbourIdx);			
            RemovePipe(neighbourtoDisconnect);
            neighbourtoDisconnect.WaterToDesert();
            neighbourtoDisconnect.RemoveNeighbour(this);
            return neighbourtoDisconnect;
    	}
    	else if(neighbourIdx == 2)
    		return null;
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
        AddPipe(new Pipe());
        AddPipe(pipe);
        return false;
    }
}
