package game;

import java.util.Scanner;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Skeleton 
{
	public static void main(String args[])
	{
		System.out.println("static void main(String[] args)");
		Scanner input = new Scanner(System.in);
		
		System.out.println("Init");
		Mechanic mechanic = new Mechanic();
		Saboteur saboteur = new Saboteur();
		Cistern cistern = new Cistern();
		Pipe pipe = new Pipe();
		Pump pump = new Pump();
		WaterSpring wspring = new WaterSpring();

		while (true)
        {
			System.out.println("\n1: Lépés egy másik elemre");
			System.out.println("2: Felvesz egy pumpát");
			System.out.println("3: Pumpa javítása");
			System.out.println("4: Csővég felétele");
			System.out.println("5: Szabad csővég felvétele");
			System.out.println("6: Pumpa felvétele ciszternából");
			System.out.println("7: Cső kilyukasztása");
			System.out.println("8: Cső megjavítása");
			System.out.println("9: Csővég lehelyezése");
			System.out.println("10: Pumpa beágyazása csőre");
			System.out.println("11: Pumpa be-/kimenet állítása");
			System.out.println("12: Az összes szekvencia egyben opciómentes futtatása");

            int userinput = GetUserInput(1, 11);

            switch (userinput)
            {
            	// Lépés egy másik elemre
                case 1:
                	System.out.println("1: A játékos átlép egy szomszédos mezőre");
                	System.out.println("2: A játékos nem tud átlépni, mert a mező foglalt");
                	userinput = GetUserInput(1, 2);

                	// TODO: Call method
                	mechanic.Move(userinput);
                	saboteur.Move(userinput);
                	

                	AfterPrintingMethodCallsDialog();
            		break;
        		// Felvesz egy pumpát
                case 2:
                	System.out.println("1: Felvesz egy pumpát");
                	System.out.println("2: Már birtokol egy pumpát, ezért nem vehet fel még egyet");
                	userinput = GetUserInput(1, 2);

                	// TODO: Call method
                	if(userinput == 1)
                		mechanic.PickUpPump();
                	else if(userinput == 2)
                		System.out.println("Nem sikerül a felvétel. Próbálkozz ciszternán állva.");
                	
                	AfterPrintingMethodCallsDialog();
                    break;
                // Pumpa javítása
                case 3:
                	System.out.println("1: Sikeresen megjavítja a pumpát");
                	System.out.println("2: A pumpa nem szorult javításra");
                	userinput = GetUserInput(1, 2);

                	// TODO: Call method
                	if(userinput == 1)
                		pump.TryRepair();
                	else if(userinput == 2)
                		System.out.println("Nem szroult a pumpa javításra.");

                	AfterPrintingMethodCallsDialog();
                    break;
                // Csővég felvétele
                case 4:
                	System.out.println("1: A csövet felvette a szerelő");
                	System.out.println("2: A cső nem választható el az elemtől");
                	userinput = GetUserInput(1, 2);

                	// TODO: Call method
                	mechanic.DisconnectNeighbourPipe(userinput);

                	AfterPrintingMethodCallsDialog();
                    break;
                // Szabad csővég felvétele
                case 5:
                	System.out.println("1: A csővéget felveszi a szerelő");
                	userinput = GetUserInput(1, 1);

        			// TODO: Call method
                	if(userinput == 1)
                		mechanic.PickUpFreePipeEnd();
                	else if(userinput == 2)
                		System.out.println("Nem sikerül a felvétel. Próbálkozz ciszternán állva.");

                	AfterPrintingMethodCallsDialog();
                	break;
            	// Pumpa felvétele ciszternából
            	case 6:
            		System.out.println("1: A szerelő magához vesz egy pumpát");
            		System.out.println("2: A szerelő már birtokol egy pumpát, így nem vehet fel még egyet");
            		userinput = GetUserInput(1, 2);

                	// TODO: Call method
            		if(userinput == 1)
            			mechanic.PickUpPump();
                	else if(userinput == 2)
                		System.out.println("Nem sikerül a felvétel, a szerelőnél már van pumpa.");
            		
            		AfterPrintingMethodCallsDialog();
            		break;
            	// Cső kilyukasztása
            	case 7:
            		System.out.println("1: A cső kilyukad");
            		System.out.println("2: A cső már lyukas volt, ezért nem lehet újra kilyukasztani");
            		userinput = GetUserInput(1, 2);

            		// TODO: Call method
            		if(userinput == 1)
            			saboteur.Damage();
                	else if(userinput == 2)
                		System.out.println("Nem sikerül a lyukasztás, a cső már lyukas volt.");

            		AfterPrintingMethodCallsDialog();
            		break;
        		// Cső megjavítása
            	case 8:
            		System.out.println("1: A csövet megjavítja a szerelő");
            		System.out.println("2: A cső már meg volt javítva, ezért nem lehet újra megjavítani");
            		userinput = GetUserInput(1, 2);

            		// TODO: Call method
            		if(userinput == 1)
            			mechanic.Repair();
                	else if(userinput == 2)
                		System.out.println("Nem sikerül a javítás, a cső már rendben volt.");
            		
            		AfterPrintingMethodCallsDialog();
            		break;
        		// Csővég lehelyezése
            	case 9:
            		System.out.println("1: A csővéget elhelyezték a pumpa egy csatlakozására");
            		System.out.println("2: A pumpa már telítve van csövekkel, ezért nem rakhatja le");
            		userinput = GetUserInput(1, 2);

            		// TODO: Call method
            		if(userinput == 1)
            			mechanic.ConnectPipe();
            		else if(userinput == 2)
            			System.out.println("Nem sikerül az akció. Próbálkozz úgy, aktív elemen állsz és van nálad csővég.");
            		
            		AfterPrintingMethodCallsDialog();
            		break;
        		// Pumpa beágyazása csőre
            	case 10:
            		System.out.println("1: A cső elválasztás után a pumpa behelyezésre kerül");
            		userinput = GetUserInput(1, 1);

            		// TODO: Call method
            		mechanic.BuildPumpIntoPipe();
            		
            		AfterPrintingMethodCallsDialog();
            		break;
        		// Pumpa be-/kimenet állítása
            	case 11:
            		System.out.println("1: Beállítja a be-és kimenetet");
            		userinput = GetUserInput(1, 1);

            		// TODO: Call method
            		mechanic.TrySetPump(0, 0);
            		saboteur.TrySetPump(0, 0);
            		
            		AfterPrintingMethodCallsDialog();
            		break;
            	case 12:
            		mechanic.Move(0);
            		saboteur.Move(0);
            		mechanic.TrySetPump(0, 0);
            		saboteur.TrySetPump(0, 0);
            		saboteur.Damage();
            		mechanic.Repair();
            		mechanic.DisconnectNeighbourPipe(0);
            		mechanic.PickUpFreePipeEnd();
            		mechanic.ConnectPipe();
            		mechanic.BuildPumpIntoPipe();
            		new Pump().Step();
            		new Pipe().Step();
            		mechanic.PickUpPump();
            		new WaterSpring().FillNeighourPipes();
            	default:            		
            		break;
            }
        }
	}

	private static int GetUserInput(int firstValid, int lastValid)
	{
		Scanner input = new Scanner(System.in);
		int userinput = input.nextInt();
		while (userinput < firstValid || userinput > lastValid)
		{
			System.out.println("Adj meg " + firstValid + " és " + lastValid + " közötti értéket!");
			userinput = input.nextInt();
		}
		
		return userinput;
	}

	private static String GetYesNoAnswer(boolean terminateIfNo) 
	{
		String yesString = "y";
		String noString = "n";
		Scanner input = new Scanner(System.in);
		String userinput = input.next();
		while (!userinput.equalsIgnoreCase("y") && !userinput.equalsIgnoreCase("n"))
		{
			System.out.println("Adj meg n/N vagy y/Y karaktert!");
			userinput = input.next();
		}

		if (userinput.equalsIgnoreCase(noString) && terminateIfNo)
			System.exit(0);

		return userinput.toLowerCase();
	}

	private static void AfterPrintingMethodCallsDialog()
	{
		System.out.println("Would you like to check another use-case? [y/n]");
		GetYesNoAnswer(true);
	}
}
