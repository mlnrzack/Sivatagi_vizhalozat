package game;

import org.junit.Test;

import static org.junit.Assert.*;
public class TestDisconnectNeighbour extends TestBaseJunit
{
    /** 8.2.9
     * A szerelő lecsatlakoztat egy már csatlakoztatott csövet.
     */
    @Test
    public void testDisconnectNeighbourPipe() {
        mechanic.SetCurrentPosition(pump);
        assertEquals(2,pump.GetNeighbours().size());
        mechanic.DisconnectNeighbourPipe(0);
        assertEquals(1,pump.GetNeighbours().size());
    }
}
