package game.elements;

import java.util.*;

import game.*;
import game.IO.*;
import game.interfaces.*;
import game.players.*;

public class Pipe extends Element implements ISteppable
{
    private boolean leaking = false;										//megadja, hogy a cső jelenleg ki van-e lyukasztva
    private int noLeakageTimer = 0;											//megadja, hogy a cső mennyi ideig nem lyukasztható
    private int slipperyTimer = 0;											//megadja, hogy a cső jelenleg csúszós-e
    private int stickyTimer = 0;											//megadja, hogy a cső jelenleg ragadós-e
    
    private ArrayList<ActiveElement> neighbours = new ArrayList<ActiveElement>();

    /**A Pipe osztály konstruktora.
     */
    public Pipe()
    {
    	GameManager.AddSteppable(this);
    	GameManager.AddPipe(this);
    	this.SetId("pipe" + GameManager.TryPipeIdSet());
    }
    
    /**Az osztály paraméteres konstruktora.
     * @param leaks lyukas tulajdonsága
     * @param timer nem lyukaszthatóság ideje
     * @param slippery csúszossága
     * @param sticky ragadós tulajdonsága
     * @param neighbours szomszédai
     */
    public Pipe(int water, boolean leaks, int timer, int slippery, int sticky, ArrayList<ActiveElement> neighbours, String id)
    {
    	SetWaterInside(water);
    	leaking = leaks;
    	noLeakageTimer = timer;
    	slipperyTimer = slippery;
    	stickyTimer = sticky;
    	this.SetId(id);
    	if(neighbours != null)
    		this.neighbours = neighbours;
    	
    	GameManager.AddSteppable(this);
    	GameManager.AddPipe(this);
    }

    /**
     */
    public int GetNeighbourIndex(String name) 
    {
		for (IElement e : neighbours)
		{
			if (e.GetId().equals(name))
				return neighbours.indexOf(e);
		}
		
		return -1;
	}
    
    /**Visszaadja a neighbours.
     * @return aktív elem szomszédok.
     */
    public ArrayList<ActiveElement> GetNeighbours()
    {
    	return neighbours;
    }
    
    /**Visszaadja a leaking értékét.
     * @return lyukas vagy sem.
     */
    public boolean GetLeaking()
    {
    	return leaking;
    }
    
    /**Visszaadja a noLeakageTimer értékét.
     * @return meddig nem lyukasztható.
     */
    public int GetTimer()
    {
    	return noLeakageTimer;
    }
    
    /**Visszaadja a slipperyTime értékét.
     * @return csúszós vagy sem.
     */
    public int GetSlippery()
    {
    	return slipperyTimer;
    }    
    
    /**Beállít egy random, két érték közötti értéket a slipperyTimer-nek.
     */
    public void SetSlippery()
    {
    	slipperyTimer = new Random().nextInt(Constants.LeakageTimerBound, Constants.LeakageTimerBound + 1);
    }

    /**Visszaadja a stickyTimer értékét.
     * @return ragad vagy sem a cső.
     */
    public int GetSticky()
    {
    	return stickyTimer;
    }
    
    /**Beállítja a stickyTimer-t egy két érték közötti random értékre.
     */
    public void SetSticky()
    {
    	stickyTimer = new Random().nextInt(Constants.LeakageTimerBound, Constants.LeakageTimerBound + 1);
    }
    
    /**Visszaadja, hogy sikerült-e lerakni a pumpát a csőre.
     * @param pump lerakni kívánt pumpa
     * @return pumpa lerakásának sikeressége.
     */
    public boolean TryBuildPumpInto(Pump pump)
    {
    	/*
    	boolean in = false;
    	boolean out = false;
    	for(int i = 0; i < this.GetNeighbours().size(); i++)
    	{
    		if(GetNeighbours().get(i).GetInput() == this)
    		{
    			in = true;
    		}
    		else if(GetNeighbours().get(i).GetOutput() == this)
    		{
    			out = true;
    		}
    	}
    	if(in)
    		return pump.GetBuildedInto(this, "in");
    	if(out)
    		return pump.GetBuildedInto(this, "out");
    	else
    		return pump.GetBuildedInto(this, "none");
    		*/
    	return pump.GetBuildedInto(this);
    }

    /**Lyukas cső esetén befoltozza a csövet, majd beállítja a noLeakageTimer értékét.
     * @return cső javításának sikeressége.
     */
    public boolean TryRepair()
    {
        if (leaking)
        {
        	//itt állítódik be, hogy mennyi ideig nem lehet lyukasztani foltozás után
        	noLeakageTimer = new Random().nextInt(Constants.LeakageTimerBound, Constants.LeakageTimerBound + 1);
        	leaking = false;

            return true;
        }

        return false;
    }

    /**Ha nem lyukas a cső és a noLeakageTimer nulla, akkor a leaking értékét igazra állítja.
     * @return cső rongálásának sikeressége.
     */
    public boolean TryDamage()
    {
        if (!leaking && noLeakageTimer == 0)
        {
        	leaking = true;
        	DebugLog.WriteDebugLog("Cső rongálása sikeres volt. Lyukas lett.\n");
        	InfoLog.WriteInfoLog("Cső rongálása sikeres volt. Lyukas lett.\n");
        	
        	// System.out.println(this.GetId() + " rongálása sikeres volt. Lyukas lett.\n");
            return true;
        }
        DebugLog.WriteDebugLog("Cső rongálása nem sikerül. Már lyukas ez az elem.\n");
        InfoLog.WriteInfoLog("Cső rongálása nem sikerül. Már lyukas ez az elem.\n");
        // System.out.println(this.GetId() + " rongálása nem sikerül. Már lyukas ez az elem.\n");
        return false;
    }

    /**Léptetéssel kapcsolatos értékek csökkentése egyel.
     * Lyukas vagy vég nélküli nem üres csőből a vizet a sivatagba engedi.
     * @return true, ha sikerült a vizet a sivatgba engedni.
     * @return false minden más esetben.
     */
    public boolean Step()
    {
    	if(noLeakageTimer > 0)
         	noLeakageTimer--;
    	
    	if(slipperyTimer > 0)
    		slipperyTimer--;
    	
    	if(stickyTimer > 0)
    		stickyTimer--;
    	
        if ((leaking || neighbours.size() < 2) && GetWaterInside() > 0)
        {
            WaterToDesert();

            return true;
        }
        
        return false;
    }

    /**Cső csőhöz csatlakoztatása.
     * @return nem lehetséges.
     */
    public boolean TryConnectPipe(Pipe pipeInInventory)
    {
        System.out.println("Jelenleg nem lehet csövet csőhöz csatlakoztatni.");
        return false;
    }

    /**Hozzáadja a paraméterben kapott aktív elemet a szomszédsági listájához. 
     * @param newNeighbour az új aktív elem (szomszéd) azonosítója.
     */
    public void AddNeighbour(ActiveElement newNeighbour)
    {
    	System.out.println(GetId() + " és " + newNeighbour.GetId() + " szomszédok lettek.");
    	neighbours.add(newNeighbour);
    }

    /**Szabad csővég felvétele.
     * @return null nem lehet szabad csővég a csövön
     */
    public Pipe PickUpFreePipeEnd()
    {
        System.out.println("Jelenleg nem lehet szabad csővég a csövön.");
        return null;
    }

    /**Pumpa felvétele csövön állva.
     * @return null
     */
    public Pump PickUpPump()
    {
        System.out.println("Pumpát kizárólag valamelyik ciszternánál lehet felvenni..");
        return null;
    }

    /**Játékos adott csőre helyezése.
     * Ha nem áll más játéko a csövön, akkor ráléphet.
     * Vizsgálja a csúszósságot és a ragacsosságot, ezeknek megfelően jár el.
     * @return játékos csőre helyezésének sikeressége.
     */
    public boolean AcceptPlayer(Player player)
    {
        if (GetPlayers().size() < Constants.AcceptedPlayersInPipe)
        {
        	if(this.slipperyTimer > 0)
        	{
        		IElement randomNeighbour = this.GetNeighbours().get(new Random().nextInt(this.neighbours.size()));
        		System.out.println(player.GetName() + " csúszós csőre érkezett: " + this.GetId() + " Random szomszédra kerül: " + randomNeighbour.GetId());
        		return randomNeighbour.AcceptPlayer(player);
        	}

    		StickyPipe(player);
    		
    		player.SetCurrentPosition(this);
            AddPlayer(player);

           	return true;
        }

        return false;
    }

    /**Kimenet és bemenet vizsgálata.
     * @return false ugyanis a csőnek nincs ilyen tulajdonsága.
     */
    public boolean TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx)
    {
        System.out.println("Nem csinálunk semmit, a cső input/output nem állítható.");
        return false;
    }

    /**Szomszédos cső lecsatlakoztatáasa.
     * @return null nem lehet a cső szomszédját lecsatlakoztatni
     */
    public Pipe DisconnectNeighbourPipe(int neighbourIdx)
    {
        System.out.println("Nem csinálunk semmit, cső szomszédja nem lecsatlakoztatható.");
        return null;
    }

    /**A neighbours listából kiveszi a paraméterül kapott szomszédot.
     * @param neighbour a szomszéd
     */
    public void RemoveNeighbour(ActiveElement neighbour)
    {
    	neighbours.remove(neighbour);
    }
    
    /**A slipperyTimer értékének beállítása,
     * @return true ha az érték nulla, és sikerült beállítani.
     */
    public boolean TrySetSlippery()
    {
    	if(slipperyTimer == 0)
    	{
    		SetSlippery();

    		return true;
    	}
    	System.out.println("Nem sikerült csúszóssá tenni a csövet.");
    	return false;
    }
    
    /**A stickyTimer értékének beállítása,
     *@return true ha az értéke nulla, és sikerült beállítani.
     */
    public boolean TrySetSticky()
    {
    	if(stickyTimer == 0)
    	{
    		SetSticky();

    		return true;
    	}

    	System.out.println("Nem sikerült ragadóssá tenni a csövet.");
    	return false;
    }

    /**Ragacsos cső általi műveletek.
     * Ha ragacsos a cső, akkor a rálépő játékos kizárása a játékkörből,
     * majd a ragacsosság megszüntetése a csövön.
     * @param player a játékos.
     * @return ragacsos a cső vagy sem.
     */
    public boolean StickyPipe(Player player)
    {
    	if(stickyTimer > 0)
    	{
    		System.out.println(player.GetName() + " ragadós csőre érkezett: " + this.GetId());
    		player.Stuck();
        	stickyTimer = 0;
        	return true;
    	}

    	return false;
    }
    
    /**A vizmennyiség növelése.
     * @return true ha sikerül vizet fogadnia
     * @return false ha nem sikerül vizet fogadnia
     */
    public boolean FillWaterTo()
    {
        if (this.GetWaterInside() < Constants.PipeCapacity)
        {
            this.SetWaterInside(this.GetWaterInside() + 1);;
            
            if (leaking || neighbours.size() < 2)
            	WaterToDesert();            	
            
            return true;
        }

        return false;
    }

    public void SetLeaking(boolean leaks) 
    {
    	leaking = leaks;
    }
    
    public void SetNeighbours(ArrayList<ActiveElement> neighbourList) {
    	neighbours = neighbourList;
    }
}