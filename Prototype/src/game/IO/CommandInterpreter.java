package game.IO;

import java.util.Scanner;

/**
 * Ez a java osztály felel a konzolon bekért parancsok értelmezéséért
 * A parancsok listájáért érdemes átnézni a dokumantáció 7.1.2-es fejezetét, ahol a Bemeneti nyelvet részletezzük
 * A parancsok a Commands osztályban találhatóak, azok hívódnak meg, miután az interpreter feldolhozta a beolvasott parancsot
 * A parancsok:
 * runTest
 * exit
 * start
 * createMap
 * playerMove
 * playerAction
 */
public class CommandInterpreter
{
    static Scanner sc;
    static Commands cmd;

    public CommandInterpreter()
    {
        sc = new Scanner(System.in);
        cmd = new Commands();
    }

    public static void getInput()
    {
        System.out.println("\t~ Sivatagi vízhálózat ~\nEpsilon csapat protorípusa\n");
        System.out.println("A lehetséges bemeneti parancsok: "
        		+ "\n\tcreateMap"
        		+ "\n\tstart"
        		+ "\n\tplayerMove"
        		+ "\n\tplayerAction"
        		+ "\n\texit"
        		+ "\n\trunTest");

        while (true)
        {
            String commandLineInput = sc.nextLine();
            String[] interpreterArray = commandLineInput.split(" ");
            System.out.println("A lehetséges bemeneti parancsok: \n\tcreateMap\n\tstart\n\tplayerMove\n\tplayerAction\n\texit\n\trunTest");

            switch (interpreterArray[0])
            {
                case "" -> System.out.println("\n");
//                 case "in" -> cmd.In(interpreterArray[1]);
//                 case "out" -> cmd.Out(interpreterArray[1]);
//                 case "log" -> cmd.Log(interpreterArray[1]);
//                 case "rnd" -> cmd.RND();
                case "exit" -> cmd.Exit();
                case "start" -> cmd.Start();
//                 case "addPipe" -> cmd.AddPipe();
//                 case "addPump" -> cmd.AddPump();
//                 case "addSource" -> cmd.AddSource();
//                 case "addCistern" -> cmd.AddCistern();
                case "createMap" -> cmd.CreateMap();
                case "playerMove" -> cmd.playerMove(interpreterArray);
//                 case "playerSetIO" -> cmd.playerSetIO(interpreterArray);
                case "playerAction" -> cmd.playerAction(interpreterArray);
//                 case "stat" -> cmd.stat(interpreterArray);
                case "runTest" -> cmd.RunTest(interpreterArray);
                default -> 
                {
                    System.err.println("Hibás parancs!");
                }
            }
        }
    }
}