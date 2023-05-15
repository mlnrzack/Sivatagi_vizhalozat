package tests;

import game.elements.*;

public class TestConnectPipe extends TestBase
{
	/**8.2.11
     * A szerelő összecsatlakoztatja a csövet egy pumpával.
     * A sikeres teszt esetén a
     */
    public void testConnectPipe()
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
        //itt is lehet valamit ki kellene íratni, hogy mi történik, törlendő ez a sor
    }
}
