package game.IO;

import game.*;
import game.players.Mechanic;
import game.players.Player;
import game.players.Saboteur;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import java.util.Scanner;

public class Commands
{

/**
 * runTest
 * exit
 * start
 * createMap
 * playerMove
 * playerAction
 */

    public Commands()
    {
    	//TODO
    }

    /*public void GenerateMap(String[] param) throws IOException
    {
        if (param.length != 1)
        {
            System.out.println("Nem adott meg megfelelő elérési útvonalat!");
        }
        
        else
        {
            File f = new File(param[0]);
            if (f.exists())
            {
                FileReader fileReader = null;
                try
                {
                    fileReader = new FileReader(f);
                }
                catch (FileNotFoundException e)
                {
                    throw new RuntimeException(e);
                }
                
                BufferedReader br = new BufferedReader(fileReader);
                String neighbourList;
                //Elem Azonosító:Elem_Azonosító,Elem_Azonosító,..,Elem_Azonosító
                while ((neighbourList = br.readLine()) != null)
                {
                    Element temp;
                    String firstSplit[] = neighbourList.split(" ");

                    String [] secondSplit = firstSplit[1].split(":");
                    switch (firstSplit[0]){
                        case "Pipe":        temp = new Pipe(Integer.parseInt(secondSplit[0])); ProcessParams(secondSplit[1]); GameManager.GetMap().add(temp); break;
                        case "Pump":        temp = new Pump(Integer.parseInt(secondSplit[0])); ProcessParams(secondSplit[1]); GameManager.GetMap().add(temp); break;
                        case "Cistern" :    temp = new Cistern(Integer.parseInt(secondSplit[0])); ProcessParams(secondSplit[1]); GameManager.GetMap().add(temp); break;
                        case "Source" :     temp = new WaterSpring(Integer.parseInt(secondSplit[0])); ProcessParams(secondSplit[1]); GameManager.GetMap().add(temp); break;
                    }
                }
            }
        }
    }

    void ProcessParams(String params)
    {
        ArrayList<Element> elementNeighbours = new ArrayList<Element>();
        String neighbours[] = params.split(",");
        for (String elementArray: neighbours)
        {
            String[] element = elementArray.split("_");
            //Itt van az a pont, hogy mi a faszomért ilyen komplex a bemenet, de így tűnt a legegyszerűbbnek elválasztani egymástól a dolgokat
            switch (element[0])
            {
                case "Pipe":

                case "Pump":

                case "Cistern":

                case "Source":

            }
        }
    }*/

    public void RunTest(String[] param)
    {
        if (param.length == 0)
        {
            //exception
        }
        
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));

        switch (param[1])
        {
        	case"BuildPumpIntoPipeTest":
        		junit.run(BuildPumpIntoPipeTest.class);
        		
        	case"ConnectPipeTest" :
                junit.run(ConnectPipeTest.class);
                
            case"DamageTest" :
                junit.run(DamageTest.class);

            case"MoveTest" :
                junit.run(MoveTest.class);

            case"PickUpFreePipeEndTest" :
                junit.run(PickUpFreePipeEndTest.class);
                
            case"PickUpPumpTest" :
                junit.run(PickUpPumpTest.class);
                
            case"PumpWaterTest" :
                junit.run(PumpWaterTest.class);
                
            case"RepairTest" :
                junit.run(RepairTest.class);

            case"SlipperyTest" :
                junit.run(SlipperyTest.class);

            case"TestDisconnectNeighbourPipe" :
                junit.run(TestDisconnectNeighbourPipe.class);

            case"TryBuildPumpTest" :
                junit.run(TryBuildPumpTest.class);
                
            case"TrySetPumpTest" :
                junit.run(TrySetPumpTest.class);
                
            case"WinTest" :
                junit.run(WinTest.class);

            default:
                System.err.println("Nem létező tesz eset!");
        }
    }

    public void Exit()
    {
        System.exit(1);
    }

    public void Start()
    {
    	GameManager.StartGame();
    }
    
    /*public void In(String path)
    {
        String line;
        File f = new File(path);
        if (f.exists())
        {
            FileReader reader = null;
            try
            {
                reader = new FileReader(f);
            }
            catch (FileNotFoundException e)
            {
                throw new RuntimeException(e);
            }
            
            BufferedReader br = new BufferedReader(reader);
            while (true)
            {
                try 
                {
                    if ((line = br.readLine()) != null)
                    	command_interpreter.getInput(line);

                    else
                    {
                        break;
                    }
                } 
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
        }
        else 
        {
            System.err.println("A fájl nem létezik");
        }
    }

    public void Log(String path)
    {

    }
    
    public void RND()
    {

    }*/

    /*public void AddPipe()
    {
        Scanner sc = new Scanner(System.in);
        //cső paraméter bekérése, maajd egy új cső behelyezése a map-ra
        //Pipe p = new Pipe()
        // boolean leaks,
        //    int timer,
        //    int slippery,
        //    int sticky,
        //    ArrayList<ActiveElement> neighbours
    }
    */

    /*public void AddPump()
    {

    }
    
    public void AddCistern()
    {

    }
    
    public void AddSource()
    {

    }*/

    public void CreateMap()
    {
        Program.CreateMap();
    }
    
   /* public void SpawnMechanic(String[] param)
    {
        switch (param.length)
        {
            case 2:
                break;
            default:
                break;
        }
    }
    public void SpawnSaboteur(String[] param)
    {
        switch (param.length)
        {
            case 2:
                break;
            default:
                break;
        }
    }*/

    public void playerMove(String[] param)
    {
        Player p = null;
        Scanner sc = new Scanner(System.in);
        if (param.length < 2) System.err.println("Hibás bemenet!");
        else
        {
            String name = param[1];
            for(var n: GameManager.GetMechanics())
            {
                if(n.GetName() == name)
                    p = n;
            }
            //Tudom, ez rák xd, de nem fogom átírni
            for(var n: GameManager.GetSaboteurs())
            {
                if(n.GetName() == name)
                    p = n;
            }
            assert p != null;
            int neighbourIndex = sc.nextInt();
            if (neighbourIndex < 0 || neighbourIndex > p.GetCurrentPosition().GetNeighbours().size())
            {
            	System.err.println("Hibás mezőre lépés"); 
            	return;
            }
            else
            {
                p.Move(neighbourIndex);
            }
        }
    }
    
    public void playerAction(String[] param)
    {
        Mechanic m = null;
        Saboteur s = null;
        if (param.length < 2) System.err.println("Hibás bemenet!");
        else
        {
            String name = param[1];
            for (var n : GameManager.GetMechanics())
            {
                if (n.GetName().equals(name))
                     m = n;
            }

            //Tudom, ez rák xd, de nem fogom átírni
            for (var n : GameManager.GetSaboteurs()) 
            {
                if (n.GetName().equals(name))
                    s = n;
            }

            Scanner sc = new Scanner(System.in);
            Scanner input = new Scanner(System.in);
            String[] params;
            if(m != null)
            {
                if (m.GetType().equals("mechanic"))
                {
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("Szerelő akció lehetőségek:");
                    System.out.println("\t1 - Javítás");
                    System.out.println("\t2 - Szabad csővég felvétele");
                    System.out.println("\t3 - Pumpa felvétele");
                    System.out.println("\t4 - Pumpa beépítése a csőbe");
                    System.out.println("\t5 - Csővég csatlakoztatása");
                    System.out.println("\t6 X - Szomszédos csővég felvétele. Az X a szomszéd indexe.");
                    System.out.println("\t7 X Y - Pumpa beállítása. Az X a kívánt input szomszéd indexe, Y a kívánt output szomszéd indexe.");
                    System.out.println("\t8 - cső lyukasztás");
                    System.out.println("\t9 - sticky");
                    System.out.println("-----------------------------------------------------------------");
                    switch (Integer.parseInt(sc.nextLine())) 
                    {
                        case 1:     m.Repair(); break;
                        case 2:     m.PickUpFreePipeEnd(); break;
                        case 3:     m.PickUpPump(); break;
                        case 4:     m.BuildPumpIntoPipe(); break;
                        case 5:     m.ConnectPipe(); break;
                        case 6:
                                    params = input.nextLine().split(" ");
                                    int neighbourIdxFrom = Integer.parseInt(params[0]);
                                    int neighbourIdxTo = Integer.parseInt(params[1]);
                                    m.TrySetPump(neighbourIdxFrom, neighbourIdxTo); break;
                        case 7:
                                    params = input.nextLine().split(" ");
                                    int neighbourIdx = Integer.parseInt(params[1]);
                                    m.DisconnectNeighbourPipe(neighbourIdx); break;
                        case 8:
                                    m.Damage(); break;
                        case 9:
                                    m.SetStickyPipe(); break;
                        default:
                            System.err.println("Nem létező parancs!"); break;
                    }
                }
            }
            
            if(s != null)
            {
                if (s.GetType().equals("saboteur"))
                {
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("Szabotőr akció lehetőségek:");
                    System.out.println("\t1 - Maga alatt lévő cső lyukasztása");
                    System.out.println("\t2 X Y - Pumpa beállítása. Az X a kívánt input szomszéd indexe, Y a kívánt output szomszéd indexe.");
                    System.out.println("\t3 - A cső ragacsossá tétele maga alatt");
                    System.out.println("\t4 - A cső csúszóssá tétele");
                    System.out.println("-----------------------------------------------------------------");

                    switch (Integer.parseInt(sc.nextLine()))
                    {
                        case 1:
                                    if (s.Damage())
                                    {
                                    	GameManager.ActionExecuted();
                                    } 
                                    break;
                        case 2:
                                    params = input.nextLine().split(" ");
                                    int neighbourIdxFrom = Integer.parseInt(params[0]);
                                    int neighbourIdxTo = Integer.parseInt(params[1]);
                                    s.TrySetPump(neighbourIdxFrom, neighbourIdxTo); break;
                        case 3:
                                    s.SetStickyPipe(); break;
                        case 4:
                                    s.SetSlipperyPipe(); break;
                        default:
                            System.err.println("Nem létező parancs!"); break;
                    }
                }
            }
        }
    }
    /*public void stat(String[] param){

    }*/
}