package game.elements;

import java.util.*;

import game.*;
import game.interfaces.*;

public class Pump extends ActiveElement implements ISteppable
{
    private Pipe input = null;			//a pumpa bemeneti csöve
    private Pipe output = null;			//a pumpa kimeneti csöve
    private boolean broken = false;		//elromlott pumpa tulajdonság
    private int age = 0;				//pumpa életkora
    
    /**Pump osztály konstruktora,
     * amely meghívja a GameManager osztály AddSteppables függvényét, ezzel hozzáadva magát a léptethető elemekhez.
     */
    public Pump()
    {
        GameManager.AddSteppable(this);
        GameManager.AddPump(this);
        //GameManager.AddToMap(this);
        this.SetId("pump" + GameManager.TryPumpIdSet());
    }

    /**Visszaadja a pumpa működési állapotát
     * @return
     */
    public boolean GetBroken()
    {
    	return broken;
    }
    
    /**Visszaadja a pumpa bemeneti csövét
     * @return
     */
    public Pipe GetInput()
    {
    	return input;
    }

    /**Visszaadja a pumpa kimeneti csövét
     * @return
     */
    public Pipe GetOutput()
    {
    	return output;
    }

    /**Víz pumpálása adott irányba.
     * Ha a pumpában van elegendő víz, akkor kifele vagy befele pumpálja a vizet.
     * @return elromlott pumpa vagy sikeres pumpálás
     */
    public boolean Step()
    {
        var pumpWaterToOutputDone = false;
        var pumpWaterFromInputDone = false;
        var isBroken = false;
        
        if(!broken)
        {
            if (GetWaterInside() == Constants.PumpWaterCapacity )
            {
                pumpWaterToOutputDone = PumpWaterToOutput();
                pumpWaterFromInputDone = PumpWaterFromInput();
            }
        	if (GetWaterInside() < Constants.PumpWaterCapacity) pumpWaterFromInputDone = PumpWaterFromInput();
        	
            isBroken = GettingOlder();
        }

        return broken || isBroken || pumpWaterToOutputDone || pumpWaterFromInputDone;
    }

    /**Ha megfelelő indexeket kap a függvény,
     * akkor beállítja az indexeknek megfelelően a be-, és kimeneti értékeket.
     * @return beállítás sikeressége 
     */
    public boolean TrySetInputOutput(int neighbourIdxFrom, int neighbourIdxTo)
    {
        if (GetNeighbours().size() > neighbourIdxFrom && neighbourIdxFrom >= 0 
            && neighbourIdxTo < GetNeighbours().size() && neighbourIdxTo >= 0
            && neighbourIdxFrom != neighbourIdxTo)
        {
            input = this.neighbours.get(neighbourIdxFrom);
            output = this.neighbours.get(neighbourIdxTo);

            return true;
        }

        System.out.println("Pumpa átállítása sikertelen.");
        return false;
    }
    
    /**Ha megfelelő indexeket kap a függvény,
     * akkor beállítja az indexeknek megfelelően a be-, és kimeneti értékeket.
     */
    public void TrySetInputOutputByPipe(Pipe input, Pipe output)
    {
        this.input = input;
        this.output = output;
    }
    
    /**Ha van szabad kapacitása a tartálynak.
     * Ha a bejövő mennyiség nem nulla, akkor a bejövőn a vízmennyiséget csökkentjük annyival,
     * amennyivel amennyivel a puffertartály tartalmát növeltük. 
     * @return művelet sikeressége
     */
    public boolean PumpWaterFromInput()
    {
        if (input != null && input.GetWaterInside() > 0 && GetWaterInside() < Constants.PumpWaterCapacity)
        {
            input.SetWaterInside(input.GetWaterInside() - 1);
            SetWaterInside(GetWaterInside() + 1);
            
            System.out.println(this.GetId() + " vizet pumpált az átmeneti tárolójába.");
            return true;
        }

        return false;
    }

    /**A bemeneti csőből a kimeneti csőbe juttatja a vizet.
     * @return művelet sikeressége
     */
    public boolean PumpWaterToOutput()
    {
        if (output != null && output.GetWaterInside() < Constants.PipeCapacity && GetWaterInside() > 0)
        {
        	output.FillWaterTo();
        	SetWaterInside(GetWaterInside() - 1);
        	
        	System.out.println(this.GetId() + " vizet pumpált a szomszédjába: " + output.GetId());
            return true;
        }

        return false;
    }

    /**A pumpa elromlásának a valószínűségét egy randommal dönti el.
     * Ha a random által elromlik a pumpa, akkor a vizet a sivatagba engedi.
     * @return pumpa állapota (hibás vagy sem)
     */
    private boolean GettingOlder()
    {
    	if((age >= Constants.PumpErrorProbability) && !broken)
    	{
    		GoWrong();
            return true;
    	}
    	
    	if(age < Constants.PumpErrorProbability)
    	{
    		for(int i = 0; i < GameManager.GetPumps().size(); i++)
    		{
    			if(this.GetId().compareTo("pump" + (i + 1)) == 0)
    				age += ((i % 5) + 1) + 4;
    		}
    	}
    	
        return false;
    }

    /**A vizet a sivatagba engedi és a víztartályt lenullázza.
     */
    private void GoWrong()
    {
    	System.out.println("A " + this.GetId() + " elromlott, javításra szorul!");
    	broken = true;
        Desert.IncreaseWaterFromPipelineNetwork(GetWaterInside());
        SetWaterInside(0);
    }
    
    /**A meghibásodott pumpa megjavítása.
     * @return true sikeres javítás esetén
     * @return false ha nem volt meghibásodva
     */
    public boolean TryRepair()
    {
        if (broken == true)
        {
            broken = false;
            age = 0;
            System.out.println("Pumpa javítása sikeres volt.\n");
            return true;
        }

        System.out.println("A pumpa nem romlott még el.\n");
        return false;
    }

    /**Az eltávolítandó elemre vonatkozó vizsgálatok után a szomszéd eltávolítása, 
     * valamint a szomszéd szomszédsági listájából a pumpa eltávolítása. 
     * @param neighbourIdx a szomszéd azonosítója
     * @return szomszéd cső azonosítója
     */
    public Pipe DisconnectNeighbourPipe(int neighbourIdx)
    {
    	if (neighbourIdx < 0 || neighbourIdx >= GetNeighbours().size())
        {
    		System.out.println("Szomszéd lecsatlakoztatása sikertelen, mert érvénytelen a szomszéd azonosítója.");
    		return null;
    	}
    	
    	if(GetNeighbours().get(neighbourIdx).GetPlayers().size() > 0) 
    	{
    		System.out.println(GetNeighbours().get(neighbourIdx).GetId() + " lecsatlakoztatása sikertelen, mert állnak rajta.");
    		return null;
    	}
        Pipe neighbourtoDisconnect = this.neighbours.get(neighbourIdx);

        if (input == neighbourtoDisconnect || output == neighbourtoDisconnect)
        {
    		System.out.println(GetNeighbours().get(neighbourIdx).GetId() + " lecsatlakoztatása sikertelen, mert a pumpa egy bemeneti, vagy kimeneti ága.");
    		return null;
    	}

        RemovePipe(neighbourtoDisconnect);
        neighbourtoDisconnect.WaterToDesert();
        neighbourtoDisconnect.RemoveNeighbour(this);

        return neighbourtoDisconnect;
    }

    /**Új cső létrehozása.
     * @param pipe a létrehozandó cső
     * @return a létrehozás sikeressége
     */
    public boolean GetBuildedInto(Pipe pipe)
    { 
    	//egyik oladli szomszéd elem letárolási
    	ActiveElement neighbour = pipe.GetNeighbours().get(0);
    	Pump p = this;
    	//az új cső szomszédjainak beállítása
    	ArrayList<ActiveElement> newPipeNeighbours = new ArrayList<ActiveElement>() 
    	{
    		{ 
    			add(neighbour); 
    			add(p);
    		}
    	};

    	// Beépítésnél input/output beállítása nélkül kerül a pályára a pumpa, ezt állítani külön elemi művelet, itt nincs rá lehetőség.
    	String pipeId = "pipe" + GameManager.TryPipeIdSet();

    	Pipe newPipe = new Pipe(pipe.GetWaterInside(), pipe.GetLeaking(), pipe.GetTimer(), pipe.GetSlippery(), pipe.GetSticky(), new ArrayList<ActiveElement>(), pipeId);
    	newPipe.SetNeighbours(newPipeNeighbours);
    	
    	//frissíti a be és kimenetet
    	if(neighbour.GetInput() == pipe)
    	{
    		Pipe out = neighbour.GetOutput();
    		neighbour.TrySetInputOutputByPipe(newPipe, out);
    	}
    	else if(neighbour.GetOutput() == pipe)
    	{
    		Pipe in = neighbour.GetInput();
    		neighbour.TrySetInputOutputByPipe(in, newPipe);
    	}
    	
    	//a szomszéd elem értesítése a szomszéd változásról
    	neighbour.RemovePipe(pipe);
    	neighbour.AddPipe(newPipe);

    	
    	/*
    	if(inout.equals("out"))
    	{
    		Pipe in = neighbour.GetInput();
    		neighbour.TrySetInputOutputByPipe(in, newPipe);
    	}
    	if(inout.equals("in"))
    	{
    		Pipe out = neighbour.GetOutput();
    		neighbour.TrySetInputOutputByPipe(newPipe, out);
    	}
    	 */
    	//az eredeti cső szomszédjainak kezelése
    	pipe.RemoveNeighbour(neighbour);
    	ArrayList<ActiveElement> oldPipeNeighbours = new ArrayList<ActiveElement>(pipe.GetNeighbours());
    	oldPipeNeighbours.add(this);
    	pipe.SetNeighbours(oldPipeNeighbours);

    	System.out.println("Pumpa beépítve a cső közepére: " + pipe.GetId());
    	
    	this.age = 0;
    	//az új pumpa szomszédjainak beállítása
    	this.AddPipe(newPipe);
    	this.AddPipe(pipe);
    	this.TrySetInputOutput(0, 1);

    	return true;
    }

}