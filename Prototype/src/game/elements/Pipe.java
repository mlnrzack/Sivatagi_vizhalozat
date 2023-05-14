package game.elements;

import java.util.*;
import java.util.Random;

import game.*;
import game.interfaces.*;
import game.players.*;

public class Pipe extends Element implements ISteppable
{
    private boolean leaking = false;							//lyukas-e
    private int noLeakageTimer = 0;								//lyukasztás tiltási ideje; ha 0, akkor lyukasztható
    private int slipperyTimer = 0;								//csúszós-e timere
    private int stickyTimer = 0;								//ragadós-e timere

    private  int _id = 0;
    private static ArrayList<Integer> PipeIDs = new ArrayList<>();
    private ArrayList<ActiveElement> neighbours = new ArrayList<ActiveElement>();

    public Pipe(){
        int max = PipeIDs.get(0);
        for (var item: PipeIDs) {
            if (item > max) max = item;
        }
        _id = max+1;
        PipeIDs.add(_id);
        typeString = "Pipe";
    }
    public Pipe(int id)
    {
        _id = id;
        PipeIDs.add(id);
        typeString = "Pipe";

    }
    
    public Pipe(boolean leaks, int timer, int slippery, int sticky, ArrayList<ActiveElement> neighbours)
    {
       ;
    	leaking = leaks;
    	noLeakageTimer = timer;
    	slipperyTimer = slippery;
    	stickyTimer = sticky;
    	if(neighbours != null)
    		this.neighbours = neighbours;
    }

    public ArrayList<ActiveElement> GetNeighbours()
    {
    	return neighbours;
    }
    
    public boolean GetLeaking()
    {
    	return leaking;
    }
    
    public int GetTimer()
    {
    	return noLeakageTimer;
    }
    
    public int GetSlippery()
    {
    	return slipperyTimer;
    }    
    
    public void SetSlippery()
    {
    	//TODO
        slipperyTimer = new Random().nextInt(Constants.LeakageTimerBound);
    }

    public int GetSticky()
    {
    	return stickyTimer;
    }
    
    public void SetSticky()
    {
        //TODO
    	stickyTimer = new Random().nextInt(Constants.LeakageTimerBound);
    }
    
    public boolean TryBuildPumpInto(Pump pump)
    {
    	return pump.GetBuildedInto(this);
    }

    public boolean TryRepair()
    {
        if (leaking)
        {
        	leaking = false;
        	//itt állítódik be, hogy mennyi ideig nem lehet lyukasztani foltozás után
        	//TODO
            noLeakageTimer = new Random().nextInt( Constants.LeakageTimerBound);
        	
            return true;
        }

        System.out.println("Cső javítása nem sikerül. Nincs elromolva ez az elem.");
        return false;
    }

    public boolean TryDamage()
    {
        if (!leaking && noLeakageTimer == 0)
        {
        	leaking = true;
        	
            return true;
        }

        System.out.println("Cső rongálása nem sikerül. Már el van rontva ez az elem.");
        return false;
    }

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

    public boolean TryConnectPipe(Pipe pipeInInventory)
    {
        System.out.println("Jelenleg nem lehet csövet csőhöz csatlakoztatni.");
        return false;
    }

    public void AddNeighbour(ActiveElement newNeighbour)
    {
    	neighbours.add(newNeighbour);
    }

    public Pipe PickUpFreePipeEnd()
    {
        System.out.println("Jelenleg nem lehet szabad csővég a csövön.");
        return null;
    }

    public Pump PickUpPump()
    {
        System.out.println("Jelenleg nincs felvehető pumpa csövön.");
        return null;
    }

    public boolean AcceptPlayer(Player player)
    {
        if (GetPlayers().size() < Constants.AcceptedPlayersInPipe)
        {
        	if(SlipperyPipe(player))
        	{
        		return true;
        	}
        	StickyPipe(player);
        	
        	AddPlayer(player);
                
        	return true;        
        }

        System.out.println("Cső nem tud fogadni, mert tele van. Válassz más műveletet.");
        return false;
    }

    public boolean TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx)
    {
        System.out.println("Nem csinálunk semmit, a cső input/output nem állítható.");
        return false;
    }

    public Pipe DisconnectNeighbourPipe(int neighbourIdx)
    {
        System.out.println("Nem csinálunk semmit, cső szomszédja nem lecsatlakoztatható.");
        return null;
    }

    public void RemoveNeighbour(ActiveElement neighbour)
    {
    	neighbours.remove(neighbour);
    }
    
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
    
    public boolean SlipperyPipe(Player player)
    {
    	if(slipperyTimer > 0)
    		GetNeighbours().get(new Random().nextInt(neighbours.size())).AcceptPlayer(player);
    	
    	return slipperyTimer > 0;
    }
    
    public boolean StickyPipe(Player player)
    {
    	if(stickyTimer > 0)
    	{
    		player.Stuck();
        	stickyTimer = 0;
        	return true;
    	}

    	return false;
    }
}