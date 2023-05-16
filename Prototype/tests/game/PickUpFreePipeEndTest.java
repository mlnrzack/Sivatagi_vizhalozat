package game;
import game.*;
import game.elements.Pipe;
import org.junit.Test;

import static org.junit.Assert.*;
public class PickUpFreePipeEndTest extends TestBaseJunit
{
    /**8.2.10
     * Játékos felveszi egy csőnek az egyik végét a ciszternából.
     */
    @Test
    public void testPickUpFreePipeEnd()
    {
        mechanic.SetCurrentPosition(pump);
        pump.AcceptPlayer(mechanic);
        
        assertNull(mechanic.GetPipeInInventory());
        mechanic.DisconnectNeighbourPipe(1);
        assertNotEquals(null, mechanic.GetPipeInInventory());
    }

}