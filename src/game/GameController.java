package game;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class GameController
{
	private static int round /*{ get; set; }*/ = 0;
	private static int mechanicsPoints /*{ get; set; }*/ = 0;
	private static int saboteursPoints = 0;/*=> Desert.WaterFromPipelineNetwork;*/
    private static ArrayList<ISteppable> steppables /*{ get; set; }*/ = new ArrayList<ISteppable>();
    private static ArrayList<WaterSpring> waterSrpings /*{ get; set; }*/ = new ArrayList<WaterSpring>();
    private static ArrayList<Saboteur> sabouteurs /*{ get; set; }*/ = new ArrayList<Saboteur>();
    private static ArrayList<Mechanic> mechanics /*{ get; set; }*/ = new ArrayList<Mechanic>();
    /*public static Player CurrentPlayer { get; set; }*/
    private static int playerActionCountInCurrentRound /*{ get; set; }*/;

    // Ha sikeresen végrehajtott a játékos egy elemi akciót, utána hívjuk.
    public static void ActionExecuted()
    {
        playerActionCountInCurrentRound++;
        FireSourceActions();
        StepSteppables();
    }

    public static void MechanicActions()
    {
       foreach (var player in GameController.mechanics)
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
               String userinput = Console.ReadLine();
               
               switch (userinput.ToCharArray()[0])
               {
               		case '1':
               			String neighbourIdx = int.Parse(userinput.Split(';')[1]);
                        player.Move(neighbourIdx);
                        break;
                    case '2':
                    	player.Repair();
                        break;
                    case '3':
                        player.PickUpFreePipeEnd();
                        break;
                    case '4':
                        player.PickUpPump();
                        break;
                    case '5':
                        player.BuildPumpIntoPipe();
                        break;
                    case '6':
                        player.ConnectPipe();
                        break;
                    case '7':
                        neighbourIdx = int.Parse(userinput.Split(';')[1]);
                        player.DisconnectNeighbourPipe(neighbourIdx);
                        break;
                    case '8':
                        var neighbourIdxFrom = int.Parse(userinput.Split(';')[1]);
                        var neighbourIdxTo = int.Parse(userinput.Split(';')[2]);
                        player.TrySetPump(neighbourIdxFrom, neighbourIdxTo);
                        break;
                    default:
                        break;
                }
            }
       }
    }

    public static void SaboteurActions()
    {
    	foreach (var player in GameController.sabouteurs)
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
                    String neighbourIdx = int.Parse(userinput.Split(';')[1]);
                    	if (neighbourIdx < player.CurrentPosition.GetNeighbours().Count() && neighbourIdx >= 0)
                        {
                    		player.Move(neighbourIdx);
                            ActionExecuted();
                        }
                        break;
                    case '2':
                    	if (player.Damage() == true)
                    		ActionExecuted();
                        break;
                    case '7':
                    	String neighbourIdxFrom = int.Parse(userinput.Split(';')[1]);
                    	String neighbourIdxTo = int.Parse(userinput.Split(';')[2]);
                    	if (neighbourIdxFrom < player.CurrentPosition.GetNeighbours().Count() && neighbourIdxFrom >= 0
                    		&& neighbourIdxTo < player.CurrentPosition.GetNeighbours().Count() && neighbourIdxTo >= 0
                    		&& player.TrySetPump(neighbourIdxFrom, neighbourIdxTo))
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

    public static void StartGame()
    {
    	while (GameController.round < Constants.RoundNumber)
        {
            GameController.MechanicActions();
            GameController.SaboteurActions();
            GameController.round++;
        }
    }

    public static void AddSteppable(ISteppable steppable) => steppables.Add(steppable);
        
    public static void StepSteppables()
    {
    	var actionDone = false;
        do
        {
        	actionDone = false;
            foreach (var steppable in steppables)
            {
            	actionDone = steppable.Step() || actionDone;
            }
        }
            while (actionDone);
        }
            
        public static void FireSourceActions() => waterSrpings.ForEach(source => source.FillNeighourPipes());
    }
}
