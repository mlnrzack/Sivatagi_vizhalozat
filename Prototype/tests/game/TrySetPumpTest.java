package game;

import game.elements.Pipe;
import org.junit.Test;

import static org.junit.Assert.*;
public class TrySetPumpTest extends TestBase{
    /**8.2.8
     * Egy játékos pumpák bemenetét állítja be.
     */
    @Test
    public void testTrySetPump(){
        mechanic.SetCurrentPosition(pump);
        pump.AcceptPlayer(mechanic);
        //spring.FillNeighourPipes();
        pump.TrySetInputOutput(0, 1);
        assertEquals(pipe1.GetId(), pump.GetNeighbours().get(0).GetId());
        assertEquals(pipe2.GetId(), pump.GetNeighbours().get(1).GetId());
    }
}
