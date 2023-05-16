package game;

import org.junit.Test;

import static org.junit.Assert.*;
public class TrySetPumpTest extends TestBaseJunit{
    /**8.2.8
     * Egy játékos pumpák bemenetét állítja be.
     */
    @Test
    public void testTrySetPump()
    {
        mechanic.SetCurrentPosition(pump);
        pump.AddPipe(pipe1);
        pump.AddPipe(pipe2);
        pump.AcceptPlayer(mechanic);
        //spring.FillNeighourPipes();
        pump.TrySetInputOutput(0, 1);
        assertEquals(pipe1.GetId(), pump.GetInput().GetId());
        assertEquals(pipe2.GetId(), pump.GetOutput().GetId());
    }
}
