package game.elements;

import java.util.*;

import game.*;
import game.interfaces.*;
import game.players.*;

public class Pipe extends Element implements ISteppable
{
    private boolean leaking = false;							//lyukas-e
    private int noLeakageTimer = new Random().nextInt(0);		//lyukasztás tiltási ideje; ha 0, akkor lyukasztható
    private boolean slippery = false;							//csúszós-e
    private boolean sticky = false;								//ragadós-e
    private ArrayList<ActiveElement> neighbours = new ArrayList<ActiveElement>();

    public Pipe()
    {
    	
    }
    
    public Pipe(boolean leaks, int timer, boolean slippery, boolean sticky, ArrayList<ActiveElement> neighbours)
    {
    	leaking = leaks;
    	noLeakageTimer = timer;
    	this.slippery = slippery;
    	this.sticky = sticky;
    	this.neighbours = neighbours;
    }
    
    public boolean TryBuildPumpInto(Pump pump)
    {
    	if(pump.GetBuildedInto(this))
    		return true;
    	
    	return false;
    }

    public boolean TryRepair()
    {
        if (leaking)
        {
        	leaking = false;
        	//itt állítódik be, hogy mennyi ideig nem lehet lyukasztani foltozás után
        	noLeakageTimer = new Random().nextInt(Constants.LeakageTimerBound, Constants.LeakageTimerBound); 		
        	        	        	
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
    	 
        if ((leaking || neighbours.size() < 2) && GetWaterInside() > 0)
        {
            WaterToDesert();

            return true;
        }
        
        return false;
    }

    public ArrayList<ActiveElement> GetNeighbours()
    {
    	return neighbours;
    }

    public boolean TryConnectPipe(Pipe pipeInInventory)
    {
        System.out.println("Bocs tesa ez nem fog menni. Jelenleg nem lehet csövet csőhöz csatlakoztatni.");
        return false;
    }

    public void AddNeighbour(ActiveElement newNeighbour)
    {
        neighbours.add(newNeighbour);
    }

    public Pipe PickUpFreePipeEnd()
    {
        System.out.println("Bocs tesa ez nem fog menni. Jelenleg nem lehet szabad csővég a csövön.");
        return null;
    }

    public Pump PickUpPump()
    {
        System.out.println("Bocs tesa ez nem fog menni. Jelenleg nincs felvehető pumpa csövön.");
        return null;
    }

    public boolean AcceptPlayer(Player player)
    {
        if (GetPlayers().size() < Constants.AcceptedPlayersInPipe)
        {
        	SlipperyPipe(player);
        	StickyPipe(player);
        	
        	if(!slippery && !sticky)       	
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
    
    public boolean GetLeaking()
    {
    	return leaking;
    }
    
    public int GetTimer()
    {
    	return noLeakageTimer;
    }
    
    public boolean GetSlippery()
    {
    	return slippery;
    }    
    
    public void SetSlippery()
    {
    	slippery = !slippery;
    }
    
    public void SetSlippery(boolean slippery)
    {
    	this.slippery = slippery;
    }
    
    
    public boolean GetSticky()
    {
    	return sticky;
    }
    
    public void SetSticky()
    {
    	sticky = !sticky;
    }
    
    public void SetSticky(boolean sticky)
    {
    	this.sticky = sticky;
    }
    
    public boolean TrySetSlippery()
    {
    	if(!slippery)
    	{
    		SetSlippery();
    		return true;
    	}    		
    	System.out.println("Nem sikerült csúszóssá tenni a csövet.");
    	return false;
    }
    
    public boolean TrySetSticky()
    {
    	if(!sticky)
    	{
    		SetSticky();
    		return true;
    	}
    		
    	System.out.println("Nem sikerült ragadóssá tenni a csövet.");
    	return false;
    }
    
    public boolean SlipperyPipe(Player player)
    {
    	int playerCount = GetPlayers().size();
    	if(playerCount > 0)
    		GetNeighbours().get(new Random().nextInt(neighbours.size())).AcceptPlayer(player);
    	
    	return playerCount > 0;
    }
    
    public boolean StickyPipe(Player player)
    {
    	//TODO
    	
    	SetSticky();
    	return false;
    }
}