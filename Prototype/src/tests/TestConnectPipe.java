package tests;

import game.elements.*;

public class TestConnectPipe extends TestBase
{
	/**8.2.11
     * A szerelő összecsatlakoztatja a csövet egy pumpával.
     * A sikeres teszt esetén a
     */
    public static void TestConnect()
    {
        System.out.println("\nTestConnectTest");
        Pipe testPipe = new Pipe();
        testPipe.SetId("pipe3");
        Pump testPump1 = new Pump();
        testPump1.SetId("testPump1");
        Pump testPump2 = new Pump();
        testPump2.SetId("testPump2");
        mechanic.SetPipeInInventory(testPipe);
        mechanic.SetCurrentPosition(testPump1);
        testPump1.AcceptPlayer(mechanic);
        
        mechanic.ConnectPipe();        
        mechanic.SetPipeInInventory(testPipe);
        
        mechanic.SetCurrentPosition(testPump2);
        testPump2.AcceptPlayer(mechanic);
        mechanic.ConnectPipe();

        System.out.println("Amennyiben sikeres volt a cső lehelyezése az új cső két szomszédja testPump1 és testPump2");
        //itt is lehet valamit ki kellene íratni, hogy mi történik, törlendő ez a sor
        System.out.println(testPipe.GetNeighbours().get(0).GetId() + " " + testPipe.GetNeighbours().get(1).GetId());
        System.out.println("A két szomszéd étékének vizsgálata. Két egymás utáni igaz értéket várunk");
        System.out.println(testPump1.GetId().equals(testPipe.GetNeighbours().get(0).GetId()) ? "Igaz" : "Hamis");
        System.out.println(testPump2.GetId().equals(testPipe.GetNeighbours().get(1).GetId()) ? "Igaz" : "Hamis");
    }
}
