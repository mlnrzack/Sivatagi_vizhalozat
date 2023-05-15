package game;
import game.elements.Pipe;
import org.junit.Test;

import static org.junit.Assert.*;
public class ConnectPipeTest extends TestBase{
    /**
     * 8.2.11
     * A szerelő összecsatlakoztatja a csövet egy pumpával
     */
    @Test
    public void testConnectPipe(){
        Pipe pipe3 = new Pipe();
        mechanic.SetPipeInInventory(pipe3);
        mechanic.SetCurrentPosition(pump);
        mechanic.ConnectPipe();
        mechanic.SetCurrentPosition(pump2);
        mechanic.ConnectPipe();

    }

}
