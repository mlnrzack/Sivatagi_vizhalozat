package game;

import org.junit.Test;

import game.elements.Pump;

import static org.junit.Assert.*;
public class TryBuildPumpTest extends TestBaseJunit
{
    /**8.2.8
     * Egy játékos pumpát állít fel
     */
    @Test
    public void testTryBuildPump()
    {
    	TestBaseJunit.init();
        mechanic.SetPumpInInventory(pump2);
        mechanic.SetCurrentPosition(pipe1);
        pipe1.AcceptPlayer(mechanic);
        
        mechanic.BuildPumpIntoPipe();        
        pump2.TrySetInputOutput(0, 1);
        pump.TrySetInputOutput(0, 1);
        
        spring.FillNeighourPipes();
        pump2.GetNeighbours().get(0).Step();
        pump2.Step();
        pump2.Step();
        pipe1.Step();
        pump.Step();
        pump.Step();
        pipe2.Step();
        cistern.Step();

        System.out.println("Spring: " + spring.GetWaterInside());
        System.out.println("Pump2 új szom: " + pump2.GetNeighbours().get(0).GetWaterInside());
        System.out.println("Pumpa2: " + pump2.GetWaterInside());
        System.out.println("Pipe1: " + pipe1.GetWaterInside());
        System.out.println("Pump: " + pump.GetWaterInside());
        System.out.println("Pipe2: " + pipe2.GetWaterInside());        
        System.out.println("Cistern: " + cistern.GetWaterInside());
        System.out.println("Mechanic points: " + GameManager.GetMechanincsPoints());
        assertEquals(1, cistern.GetWaterInside());
    }
}
