package game;

import org.junit.Test;

import static org.junit.Assert.*;

public class PumpWaterTest extends TestBase
{
    /**8.2.7
     * A pumpák minden elemi akció után pumpálnak a beállításuk alapján.
     */
    @Test
    public void testWaterPump()
    {
        mechanic.SetCurrentPosition(pump);
        pump.AcceptPlayer(mechanic);
        spring.FillNeighourPipes();
        pump.TrySetInputOutput(0, 1);
        //pipe1.Step();
        pump.Step();
        spring.FillNeighourPipes();
        pump.Step();
        //pipe2.Step();
        cistern.Step();
        System.out.println("Pipe1: "+pipe1.GetWaterInside());
        System.out.println("Pumpa: "+pump.GetWaterInside());
        System.out.println("Pipe2: "+pipe2.GetWaterInside());
        System.out.println("Cistern: "+cistern.GetWaterInside());
        System.out.println("Mechanic points: "+GameManager.GetMechanincsPoints());
        assertEquals(1, cistern.GetWaterInside());
    }
}
