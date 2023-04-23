package game;

import java.util.*;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class GameManager
{
	private int round = 0;
	private int mechanicsPoints = 0;
	private int saboteursPoints = Desert.WaterFromPipelineNetwork;
    private ArrayList<ISteppable> steppables = new ArrayList<ISteppable>();
    private ArrayList<WaterSpring> waterSprings = new ArrayList<WaterSpring>();
    private ArrayList<Saboteur> saboteurs = new ArrayList<Saboteur>();
    private ArrayList<Mechanic> mechanics = new ArrayList<Mechanic>();
    public Player currentPlayer;
    private int playerActionCountInCurrentRound = 0;
    
    public int GetRound()
    {
    	return round;
    }
    
    public void SetRound(int round)
    {
    	this.round = round;
    }
    
    public Player GetCurrentPlayer()
    {
    	return currentPlayer;
    }
    
    public void SetCurrentPlayer(Player player)
    {
    	currentPlayer = player;
    }
    
    public int GetMechanincsPoints()
    {
    	return mechanicsPoints;
    }
    
    public void SetMechanicsPoints(int points)
    {
    	mechanicsPoints = points;
    }
    
    public void IncreaseMechanicsPoints()
    {
    	mechanicsPoints++;
    }
    
    public void DecreaseMechanicsPoints()
    {
    	mechanicsPoints--;
    }
    
    public int GetSaboteurPoints()
    {
    	return saboteursPoints;
    }
    
    public void SetSaboteursPoints(int points)
    {
    	saboteursPoints = points;
    }
    
    public void IncreaseSaboteursPoints()
    {
    	saboteursPoints++;
    }
    
    public void DecreaseSaboteursPoints()
    {
    	saboteursPoints--;
    }

    public ArrayList<Mechanic> GetMechanic()
    {
    	return mechanics;
    }
    
    public void SetMechanic(ArrayList<Mechanic> mechanics)
    {
    	this.mechanics = mechanics;
    }
    
    public boolean AddMechanic(Mechanic mechanic)
    {
    	return mechanics.add(mechanic);
    }
    
    public boolean RemoveMechanic(Mechanic mechanic)
    {
    	return mechanics.remove(mechanic);
    }
    
    public ArrayList<Saboteur> GetSaboteurs()
    {
    	return saboteurs;
    }
    
    public void SetSaboteurs(ArrayList<Saboteur> saboteurs)
    {
    	this.saboteurs = saboteurs;
    }

    public boolean AddSaboteur(Saboteur saboteur)
    {
    	return saboteurs.add(saboteur);
    }
    
    public boolean RemoveSaboteur(Saboteur saboteur)
    {
    	return saboteurs.remove(saboteur);
    }
    
    public ArrayList<ISteppable> GetSteppables()
    {
    	return steppables;
    }
    
    public void SetSteppables(ArrayList<ISteppable> steppables)
    {
    	this.steppables = steppables;
    }

    public boolean AddSteppble(ISteppable steppable)
    {
    	return steppables.add(steppable);
    }
    
    public boolean RemoveSteppable(ISteppable steppable)
    {
    	return steppables.remove(steppable);
    }
    
    public ArrayList<WaterSpring> GetWaterSprings()
    {
    	return waterSprings;
    }
    
    public void SetWaterSprings(ArrayList<WaterSpring> waterSprings)
    {
    	this.waterSprings = waterSprings;
    }
    
    public boolean AddWaterSpring(WaterSpring waterspring)
    {
    	return waterSprings.add(waterspring);
    }
    
    public boolean RemoveWaterSpring(WaterSpring waterspring)
    {
    	return waterSprings.remove(waterspring);
    }
    
    public int GetPlayerAction()
    {
    	return playerActionCountInCurrentRound;
    }
    
    public void SetPlayerAction(int count)
    {
    	playerActionCountInCurrentRound = count;
    }
    
    public void IncreasePlayerAction()
    {
    	playerActionCountInCurrentRound++;
    }
    
    // Ha sikeresen végrehajtott a játékos egy elemi akciót, utána hívjuk.
    public void ActionExecuted()
    {
        playerActionCountInCurrentRound++;
        FireSourceActions();
        StepSteppables();
    }

    public void AddSteppable(ISteppable steppable)
    {
    	steppables.add(steppable);
        
    }
    
    public void FireSourceActions()
    {
     	//watersrpings.ForEach(watersrpings => watersrpings.FillNeighourPipes());
    }
    
    public void MechanicActions()
    {
    	for(int i = 0; i < mechanics.size(); i++)
    	{
    		playerActionCountInCurrentRound = 0;
    		
    		while (playerActionCountInCurrentRound < Constants.ActionInRoundPerUser)
    		{
    			System.out.println("{GameController.round + 1}. Kör");
    			System.out.println("Szerelő {player.Name} köre, {playerActionCountInCurrentRound + 1}. akció");
    			System.out.println("Lehetőségek:");
    			System.out.println("\t1;X - Mozgás, X szomszéd indexe, ahova mozogni szeretnél");
    			System.out.println("\t2 - Javítás");
    			System.out.println("\t3 - Szabad csővég felvétele");
               	System.out.println("\t4 - Pumpa felvétele");
               	System.out.println("\t5 - Pumpa beépítése a csőbe");
               	System.out.println("\t6 - Csővég csatlakoztatása");
               	System.out.println("\t7;X - Szomszédos csővég felvétele. Az X a szomszéd indexe.");
               	System.out.println("\t8;X;Y - Pumpa beállítása. Az X a kívánt input szomszéd indexe, Y a kívánt output szomszéd indexe.");
               	String userinput = System.in.toString();
               
               	switch (userinput.toCharArray()[0])
               	{
               		case '1':
               			int neighbourIdx = Integer.parseInt(userinput.split(";")[1]);
               			mechanics.get(i).Move(neighbourIdx);
                        break;
                    case '2':
                    	mechanics.get(i).Repair();
                        break;
                    case '3':
                    	mechanics.get(i).PickUpFreePipeEnd();
                        break;
                    case '4':
                    	mechanics.get(i).PickUpPump();
                        break;
                    case '5':
                    	mechanics.get(i).BuildPumpIntoPipe();
                        break;
                    case '6':
                    	mechanics.get(i).ConnectPipe();
                        break;
                    case '7':
                        neighbourIdx = Integer.parseInt(userinput.split(";")[1]);
                        mechanics.get(i).DisconnectNeighbourPipe(neighbourIdx);
                        break;
                    case '8':
                        int neighbourIdxFrom = Integer.parseInt(userinput.split(";")[1]);
                        int neighbourIdxTo = Integer.parseInt(userinput.split(";")[2]);
                        mechanics.get(i).TrySetPump(neighbourIdxFrom, neighbourIdxTo);
                        break;
                    default:
                        break;
               	}
    		}
    	}
    }

    public void SaboteurActions()
    {
    	for(int i =0; i < saboteurs.size(); i++)
    	{
    		playerActionCountInCurrentRound = 0;

            while (playerActionCountInCurrentRound < Constants.ActionInRoundPerUser)
            {
            	System.out.println("{GameController.round + 1}. Kör");
                System.out.println("Szabotőr {player.Name} köre, {playerActionCountInCurrentRound + 1}. akció");
                System.out.println("Lehetőségek:");
                System.out.println("\t1;X - Mozgás, X szomszéd indexe, ahova mozogni szeretnél");
                System.out.println("\t2 - Lyukasztás");
                System.out.println("\t8;X;Y - Pumpa beállítása. Az X a kívánt input szomszéd indexe, Y a kívánt output szomszéd indexe.");
                String userinput = System.in.toString();//.ReadLine();

                switch (userinput.toCharArray()[0])
                {
                	case '1':
                		int neighbourIdx = Integer.parseInt(userinput.split(";")[1]);
                		if (neighbourIdx < saboteurs.get(i).GetCurrentPosition().GetNeighbours().size() && neighbourIdx >= 0)
                		{
                			saboteurs.get(i).Move(neighbourIdx);
                			ActionExecuted();
                		}
                		break;
                    case '2':
                    	if (saboteurs.get(i).Damage() == true)
                    		ActionExecuted();
                        break;
                    case '7':
                    	int neighbourIdxFrom = Integer.parseInt(userinput.split(";")[1]);
                    	int neighbourIdxTo = Integer.parseInt(userinput.split(";")[2]);
                    	if (neighbourIdxFrom < saboteurs.get(i).GetCurrentPosition().GetNeighbours().size() && neighbourIdxFrom >= 0
                    		&& neighbourIdxTo < saboteurs.get(i).GetCurrentPosition().GetNeighbours().size() && neighbourIdxTo >= 0
                    		&& saboteurs.get(i).TrySetPump(neighbourIdxFrom, neighbourIdxTo))
                    	{
                    		ActionExecuted();
                    	}
                        break;
                    default:
                    	break;
                }
            }
        }
    }

    public void StartGame()
    {
    	while (round < Constants.RoundNumber)
        {
            MechanicActions();
            SaboteurActions();
            round++;
        }
    }
    
    public void StepSteppables()
    {
    	var actionDone = false;
        do
        {
            actionDone = false;
            for(int i = 0; i < steppables.size(); i++)
        	{
            	actionDone = steppables.get(i).Step()|| actionDone; 
        	}
        }
        while (actionDone);
    }   
 }
