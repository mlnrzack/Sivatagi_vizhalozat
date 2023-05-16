package tests;

import game.elements.*;

public class TestConnectPipe extends TestBase
{
	/**8.2.11
     * A szerelő összecsatlakoztatja a csövet egy pumpával.
     * A sikeres teszt esetén a
     */
    public void TestConnect()
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
    }
}
