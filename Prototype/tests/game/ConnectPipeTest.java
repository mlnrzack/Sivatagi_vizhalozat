package game;
import game.elements.Pipe;
import org.junit.Test;

import static org.junit.Assert.*;
public class ConnectPipeTest extends TestBaseJunit
{
    /**8.2.11
     * A szerelő összecsatlakoztatja a csövet egy pumpával
     */
    @Test
    public void TestConnectPipe()
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
    }
}
