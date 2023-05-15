package game;

import java.util.*;
import java.io.*;

import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class GameManager
{
	private static int round = 0;
	private static int mechanicsPoints = 0;
	private static int saboteursPoints = 0;
    private static ArrayList<IElement> map = new ArrayList<IElement>();
    private static ArrayList<ISteppable> steppables = new ArrayList<ISteppable>();
    private static ArrayList<WaterSpring> waterSprings = new ArrayList<WaterSpring>();
    private static ArrayList<Saboteur> saboteurs = new ArrayList<Saboteur>();
    private static ArrayList<Mechanic> mechanics = new ArrayList<Mechanic>();
    private static Player currentPlayer;
    private static int playerActionCountInCurrentRound = 0;
    
    /**Az aktuális körszám visszaadása.
     * @return az aktuális körszám.
     */
    public static int GetRound()
    {
    	return round;
    }
    
    /**Az aktuális körszám beállítása adott értékre.
     * @param round az adott érték.
     */
    public static void SetRound(int round)
    {
    	GameManager.round = round;
    }

    /**
     *
     * @return Visszatér a beolvasott pálya összes elemével
     */
    public static ArrayList<IElement> GetMap() {
        return map;
    }

    /**Az aktuális játékos visszaadása.
     * @return az aktuális játékos.
     */
    public Player GetCurrentPlayer()
    {
    	return currentPlayer;
    }
    
    /**Az akutális játékos beállítása paraméterben kapott értékre.
     * @param player a kapott játékos.
     */
    public void SetCurrentPlayer(Player player)
    {
    	currentPlayer = player;
    }
    
    /**A szerelők ponjainak visszaadása.
     * @return a szerelők pontjai.
     */
    public static int GetMechanincsPoints()
    {
    	return mechanicsPoints;
    }
    
    /**A szerelők ponjainak beállítása adott értékre.
     * @param points az adott érték.
     */
    public static void SetMechanicsPoints(int points)
    {
    	mechanicsPoints = points;
    }
    
    /**A szabotőrök ponjainak visszaadása.
     * @return a szabotőrök pontjai.
     */
    public static int GetSaboteurPoints()
    {
    	return saboteursPoints;
    }
    
    /**A szabotőrök pontjainak beállítása adott értékre.
     * @param points az adott érték.
     */
    public static void SetSaboteursPoints(int points)
    {
    	saboteursPoints = points;
    }
    
    /**A szerelők listájának visszaadása.
     * @return a szerelők listája.
     */
    public static ArrayList<Mechanic> GetMechanics()
    {
    	return mechanics;
    }
        
    /**Adott szerelő karakter hozzáfűzése a szerelők listához.
     * @param mechanic a hozzáfűzendő szerelő.
     * @return a hozzáfűzés sikeressége.
     */
    public static boolean AddMechanic(Mechanic mechanic)
    {
    	return mechanics.add(mechanic);
    }

    /**A szabotőrök listájának visszaadása.
     * @return a szabotőrök listája.
     */
    public static ArrayList<Saboteur> GetSaboteurs()
    {
    	return saboteurs;
    }

    /**Egy szabotőr felvétele a szabotőrök listájához.
     * @param saboteur az adott szabotőr.
     * @return a felvétel sikeressége.
     */
    public static boolean AddSaboteur(Saboteur saboteur)
    {
    	return saboteurs.add(saboteur);
    }
    
    /**A léptethetők listájának átadása más osztályok felé.
     * @return a léptethetők listája.
     */
    public static ArrayList<ISteppable> GetSteppables()
    {
    	return steppables;
    }
    
    /**A léptethetők (ciszterna, cső, pumpa) listájának beállítása egy másik listából.
     * @param steppables a másik lista.
     */
    public static void SetSteppables(ArrayList<ISteppable> steppables)
    {
    	GameManager.steppables = steppables;
    }

    /**Adott léptethető elem(ciszterna, cső, pumpa) felvétele a léptethetők listájából.
     * @param steppable az adott elem.
     * @return a felvétel sikeressége.
     */
    public static boolean AddSteppable(ISteppable steppable)
    {
    	return steppables.add(steppable);
    }
    
    /**Adott léptethető elem(ciszterna, cső, pumpa) levétele a léptethetők listájából.
     * @param steppable az adott elem.
     * @return a levétel sikeressége.
     */
    public static boolean RemoveSteppable(ISteppable steppable)
    {
    	return steppables.remove(steppable);
    }
    
    /**A térképen lévő vízforrások listájának átadása más osztályok felé.
     * @return a vízforrások listája.
     */
    public static ArrayList<WaterSpring> GetWaterSprings()
    {
    	return waterSprings;
    }
    
    /**A térképen lévő vízforrások listájának beállítása adott listából. 
     * @param waterSprings az adott lista.
     */
    public static void SetWaterSprings(ArrayList<WaterSpring> waterSprings)
    {
    	GameManager.waterSprings = waterSprings;
    }
    
    /**A térképre adott vízforrás felhelyezése.
     * @param waterspring az adott vízforrás.
     * @return a felhelyezés sikeressége.
     */
    public static boolean AddWaterSpring(WaterSpring waterspring)
    {
    	return waterSprings.add(waterspring);
    }
    
    /**A térképről adott vízforrás levétele.
     * @param waterspring az adott vízforrás.
     * @return levétel sikeressége.
     */
    public static boolean RemoveWaterSpring(WaterSpring waterspring)
    {
    	return waterSprings.remove(waterspring);
    }
    
    /**Adott karakter körbeli lépésszámának átadása más osztályoknak.
     * @return adott karakter lépésszáma
     */
    public static int GetPlayerAction()
    {
    	return playerActionCountInCurrentRound;
    }
    
    /**Adott karakter körbeli lépésszámának beállítása paraméterként kapott értékre.
	*/
    public static void SetPlayerAction(int count)
    {
    	playerActionCountInCurrentRound = count;
    }
    
    /**Adot karakter körbeli lépésszámának növelése.
     */
    public static void IncreasePlayerAction()
    {
    	playerActionCountInCurrentRound++;
    }
    
    /**Ha sikesen végrehajt egy játékos egy elemi akciót, akkor ez a függvény hívódik meg.
     * Növeli az adott játékos lépésszámát, valamint lépteti a vizet a rendszerben.
     */
    public static void ActionExecuted()
    {
    	IncreasePlayerAction();
        FireSourceActions();
        StepSteppables();
    }

    /**A játékot menetéért felelős függvény.
     * A modell adott lejátszott körszámig játszatja a játékot.
     * Ha minden karakter meglépte adott körre vonatkozó lépéseit, akkor növelődik.
     */
    public static void StartGame()
    {
    	while (round < Constants.RoundNumber)
        {
            MechanicActions();
            SaboteurActions();
            SetRound(GetRound() + 1);
        }
    }
    
    /**A forrásokból a szomszédos elemekbe folyatja a vizet.
     * A források minden szomszédos elemébe(rákapcsolt cső) juttat vizet.
     */
    public static void FireSourceActions()
    {
    	for(int i = 0; i < waterSprings.size(); i++)
    	{
    		waterSprings.get(i).FillNeighourPipes();
    	}
    }
    
    /**A térkép összes - kivéve vízforrás - elemében történő vízfolyatás.
     */
    public static void StepSteppables()
    {
    	var actionDone = false;
        do
        {
            actionDone = false;
            for(int i = 0; i < steppables.size(); i++)
        	{
            	actionDone = steppables.get(i).Step() || actionDone; 
        	}
        }
        while (actionDone);
    }
    
    /**A szerelő játékos karakterek lépéseinek menüje.
     */
    public static void MechanicActions()
    {
    	for(int i = 0; i < mechanics.size(); i++)
    	{
    		playerActionCountInCurrentRound = 0;
    		
    		while (playerActionCountInCurrentRound < Constants.ActionInRoundPerUser)
    		{
    			System.out.println("\n A menü használata: "
    					+ "\n A kívánt menüpont kiválasztása a hozzátartozó szám leírásával, szóköz, "
    					+ "\n ha van további feltétel(a menüleírásban X és Y jelzi),"
    					+ "\n akkor a szóköz után, elemenként szóközzel elválasztva írandó.\n");    			
    			System.out.println((round + 1) + ". Kör");
    			System.out.println("__________________________________________________________________________");
    			System.out.println("\n\t(Szerelő csapat)" + mechanics.get(i).GetName() + " játékos köre, " + (playerActionCountInCurrentRound + 1) + ". akció");
    			System.out.println("Pozíció: " + mechanics.get(i).GetCurrentPosition().GetId() + "\n");
    			System.out.println("Lehetőségek:");
    			System.out.println("\t1 X - Mozgás, X szomszéd indexe, ahova mozogni szeretnél");
    			System.out.println("\t2 - Javítás");
    			System.out.println("\t3 - Szabad csővég felvétele");
               	System.out.println("\t4 - Pumpa felvétele");
               	System.out.println("\t5 - Pumpa beépítése a csőbe");
               	System.out.println("\t6 - Csővég csatlakoztatása");
               	System.out.println("\t7 X - Szomszédos csővég felvétele. Az X a szomszéd indexe.");
               	System.out.println("\t8 X Y - Pumpa beállítása. Az X a kívánt input szomszéd indexe, Y a kívánt output szomszéd indexe.");
               	System.out.println("\t9 - cső lyukasztás");
               	System.out.println("\t10 - sticky");
               	
               	try
               	{
               		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                   	String userinput = reader.readLine();
                   	
                   	switch (userinput.split(" ")[0])
                   	{
                   		case "1":
                   			int neighbourIdx = Integer.parseInt(userinput.split(" ")[1]);
                   			mechanics.get(i).Move(neighbourIdx);
                            break;
                        case "2":
                        	mechanics.get(i).Repair();
                            break;
                        case "3":
                        	mechanics.get(i).PickUpFreePipeEnd();
                            break;
                        case "4":
                        	mechanics.get(i).PickUpPump();
                            break;
                        case "5":
                        	mechanics.get(i).BuildPumpIntoPipe();
                            break;
                        case "6":
                        	mechanics.get(i).ConnectPipe();
                            break;
                        case "7":
                            neighbourIdx = Integer.parseInt(userinput.split(" ")[1]);
                            mechanics.get(i).DisconnectNeighbourPipe(neighbourIdx);
                            break;
                        case "8":
                            int neighbourIdxFrom = Integer.parseInt(userinput.split(" ")[1]);
                            int neighbourIdxTo = Integer.parseInt(userinput.split(" ")[2]);
                            mechanics.get(i).TrySetPump(neighbourIdxFrom, neighbourIdxTo);
                            break;
                        case "9":
                        	mechanics.get(i).Damage();
                        	break;
                        case "10":
                        	mechanics.get(i).SetStickyPipe();
                        	break;
                        default:
                            break;
                   	}
               	}
               	catch(IOException e)
               	{
               		System.out.println("Hibás menü bemenet!");
               	}
               	catch(Exception e)
               	{
               		System.out.println("Hibás menü bemenet!");
               	}
    		}
    	}
    }

    /**A szabőtr játékos karakter lépéseinek menüje.
     */
    public static void SaboteurActions()
    {
    	for(int i = 0; i < saboteurs.size(); i++)
    	{
    		playerActionCountInCurrentRound = 0;

            while (playerActionCountInCurrentRound < Constants.ActionInRoundPerUser)
            {
            	System.out.println("\n A menü használata: "
    					+ "\n A kívánt menüpont kiválasztása a hozzátartozó szám leírásával, szóköz, "
    					+ "\n ha van további feltétel(a menüleírásban X és Y jelzi),"
    					+ "\n akkor a szóköz után, elemenként szóközzel elválasztva írandó.\n");
            	System.out.println((round + 1) + ". Kör");
            	System.out.println("__________________________________________________________________________");
                System.out.println("Szabotőr " + saboteurs.get(i).GetName() + " köre, " + (playerActionCountInCurrentRound + 1) + ". akció");
                System.out.println("Pozíció: " + saboteurs.get(i).GetCurrentPosition().GetId() + "\n");
                System.out.println("Lehetőségek:");
                System.out.println("\t1 X - Mozgás, X szomszéd indexe, ahova mozogni szeretnél");
                System.out.println("\t2 - Maga alatt lévő cső lyukasztása");
                System.out.println("\t8 X Y - Pumpa beállítása. Az X a kívánt input szomszéd indexe, Y a kívánt output szomszéd indexe.");
                System.out.println("\t9 - A cső ragacsossá tétele maga alatt");
                System.out.println("\t10 - A cső csúszóssá tétele");
                
                try
               	{
               		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                   	String userinput = reader.readLine();
                   	
                   	switch (userinput.split(" ")[0])
                    {
                    	case "1":
                    		int neighbourIdx = Integer.parseInt(userinput.split(" ")[1]);
                    		if (neighbourIdx < saboteurs.get(i).GetCurrentPosition().GetNeighbours().size() && neighbourIdx >= 0)
                    		{
                    			saboteurs.get(i).Move(neighbourIdx);
                    			ActionExecuted();
                    		}
                    		break;
                        case "2":
                        	if (saboteurs.get(i).Damage() == true)
                        		ActionExecuted();
                            break;
                        case "8":
                        	int neighbourIdxFrom = Integer.parseInt(userinput.split(" ")[1]);
                        	int neighbourIdxTo = Integer.parseInt(userinput.split(" ")[2]);
                        	saboteurs.get(i).TrySetPump(neighbourIdxFrom, neighbourIdxTo);
                            break;
                        case "9":
                        	saboteurs.get(i).SetStickyPipe();
                        	break;
                        case "10":
                        	saboteurs.get(i).SetSlipperyPipe();
                        	break;
                        default:
                        	break;
                    }
               	}
                catch(IOException e)
                {
                	System.out.println("Hibás menü bemenet!");
                }
                catch(Exception e)
                {
                	System.out.println("Hibás menü bemenet!");
                }
            }
        }
    }
    public static void print() {
        String className = new Exception().getStackTrace()[1].getClassName();
        String methodName1 = new Exception().getStackTrace()[1].getMethodName();
        int depth1 = new Exception().getStackTrace().length;
        String filler = "\t".repeat(depth1 - 3);

        System.out.println(filler + className + " " + methodName1);
    }
}
