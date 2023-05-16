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
        Pipe pipe3 = new Pipe();
        mechanic.SetPipeInInventory(pipe3);
        mechanic.SetCurrentPosition(pump);
        pump.AcceptPlayer(mechanic);
        
        mechanic.ConnectPipe();        
        mechanic.SetPipeInInventory(pipe3);
        
        mechanic.SetCurrentPosition(pump2);
        pump2.AcceptPlayer(mechanic);
        mechanic.ConnectPipe();
        System.out.println("Amennyiben sikeres volt a cső lehelyezése az új cső két szomszédja pump és pump 1");
        //itt is lehet valamit ki kellene íratni, hogy mi történik, törlendő ez a sor
        System.out.println(pipe3.GetNeighbours().get(0).GetId() + " " + pipe3.GetNeighbours().get(1).GetId());
        System.out.println("A két szomszéd étékének vizsgálata. Két egymás utáni igaz értéket várunk");
        System.out.println(pump.GetId().equals(pipe3.GetNeighbours().get(0).GetId()) ? "Igaz" : "Hamis");
        System.out.println(pump2.GetId().equals(pipe3.GetNeighbours().get(1).GetId()) ? "Igaz" : "Hamis");
    }
}
