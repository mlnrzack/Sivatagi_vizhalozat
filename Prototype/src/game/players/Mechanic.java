package game.players;

import game.*;
import game.elements.*;

public class Mechanic extends Player
{	
    private Pipe pipeInInventory = null;			//A szerelő karakternél lévő cső.
    private Pump pumpInInventory = null;			//A szerelő játékosnál lévő pumpa.
    
    /**A szerelő osztály konstruktora.
     * Hozzáadja a létrehozott szerelőt, a GameManager osztály szerelők listájához.
     */
    public Mechanic() 
    {
        GameManager.AddMechanic(this);
    }
   
    /**
     * @return
     */
    public String GetType()
    {
    	return "mechanic";
    }
    
    /**Egy a szerelőnél lévő pumpa beépítése csőbe.
     * @return a beépíétés sikeressége.
     */
    public boolean BuildPumpIntoPipe()
    {
        if (pumpInInventory != null)
        {
            if (GetCurrentPosition().TryBuildPumpInto(pumpInInventory))
            {
                pumpInInventory = null;

                GameManager.ActionExecuted();
                return true;
            }
        }

        System.out.println("Nem sikerül az akció. Próbálkozz úgy, hogy csövön állsz és van nálad pumpa.");
        return false;
    }
    
    /**Felvett cső csatlakoztatása.
     * @return a csatlakoztatás sikeressége.
     */
    public boolean ConnectPipe()
    {
        if (pipeInInventory != null)
        {
            if (GetCurrentPosition().TryConnectPipe(pipeInInventory))
            {
            	Pipe pipe = pipeInInventory;
                pipeInInventory = null;

                GameManager.ActionExecuted();
                
                // System.out.println("Cső csatlakoztatása sikeres. Cső csatlakoztatva: " + pipe.GetId() + " Ide: " + this.GetCurrentPosition().GetId());
                return true;
            }
        }

        System.out.println("Nem sikerül a cső csatakoztatása ide: " + this.GetCurrentPosition().GetId());
        return false;
    }
    
    /**A szerelő lecsatlakoztat egy szomszédos csövet az alatt lévő elemről.
     * @param neighbourIdx a szomszédos cső indexe.
     * @return a lecsatlakoztatás sikeressége.
     */
    public boolean DisconnectNeighbourPipe(int neighbourIdx)
    {
        if (pipeInInventory == null)
            pipeInInventory = GetCurrentPosition().DisconnectNeighbourPipe(neighbourIdx);
        
        if (pipeInInventory != null)
        {
            GameManager.ActionExecuted();
            
            System.out.println("Cső lecsatlakoztatása sikeres neki: " + this.GetName() + " Innen: " + this.GetCurrentPosition().GetId() +  " Cső lecsatlakoztatva: " + pipeInInventory.GetId());
            return true;
        }

        System.out.println("Cső lecsatlakoztatása sikertelen neki: " + this.GetName() + " Innen: " + this.GetCurrentPosition().GetId());
        return false;
    }

    /**A szerelő felvesz egy szabad csővéget.
     * @return a felvétel sikeressége.
     */
    public boolean PickUpFreePipeEnd()
    {
        if (pipeInInventory == null)
        {
            var pickedUpPipe = GetCurrentPosition().PickUpFreePipeEnd();

            if (pickedUpPipe != null)
            {
                pipeInInventory = pickedUpPipe;

                GameManager.ActionExecuted();
                
   				System.out.println("Szabad csővég felvétele sikeres. Cső felvéve: " + pipeInInventory.GetId());
                return true;
            }
        }

        System.out.println("Nem sikerül a szabad csővég felvétele itt: " + this.GetCurrentPosition().GetId());
        return false;
    }

    /**A szerelő felvesz egy pumpát a ciszternából, ha azon áll.
     * @return a felvétel sikeressége.
     */
    public boolean PickUpPump()
    {
        if (pipeInInventory == null)
        {
            Pump pickedUpPump = GetCurrentPosition().PickUpPump();

            if (pickedUpPump != null)
            {
                pumpInInventory = pickedUpPump;

                GameManager.ActionExecuted();
                
                System.out.println("Pumpa felvétele sikeres. Pumpa felvéve: " + pumpInInventory.GetId());
                return true;
            }
        }

        System.out.println("Nem sikerül a pumpa felvétele itt: " + this.GetCurrentPosition().GetId());
        return false;
    }
    
    /**A szerelő megpróbálja megjavítani a maga alatt lévő elemet.
     * @return a szerelés sikeressége.
     */
    public boolean Repair()
    {
        if (GetCurrentPosition().TryRepair())
        {
            GameManager.ActionExecuted();
            
    		System.out.println(this.GetName() + " szerelő javítása sikeres itt: " + this.GetCurrentPosition().GetId());
            return true;
        }

        System.out.println(this.GetName() + " szerelő javítása sikertelen itt: " + this.GetCurrentPosition().GetId());
        return false;
    }
    
    /**teszthez szükséges metódus
     * @param pipe
     */
    public void SetPipeInInventory(Pipe pipe)
    {
    	this.pipeInInventory = pipe;
    }
    
    /**teszthez szükséges metódus
     * @param pump
     */
    public void SetPumpInInventory(Pump pump)
    {
    	this.pumpInInventory = pump;
    }
    
    /**teszthez szükséges metódus
     */
    public Pump GetPumpInInventory()
    {
    	return pumpInInventory;
    }
    
    /**teszthez szükséges metódus
     */
    public Pipe GetPipeInInventory()
    {
    	return pipeInInventory;
    }
}