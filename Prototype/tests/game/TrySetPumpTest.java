package game;

import org.junit.Test;

import static org.junit.Assert.*;
public class TrySetPumpTest extends TestBase{
    /**8.2.8
     * Egy játékos pumpát állít fel
     */
    @Test
    public void testTrySetPump(){
        mechanic.SetPumpInInventory(pump2);
        mechanic.SetCurrentPosition(pipe1);
        mechanic.BuildPumpIntoPipe();
        pump2.TrySetInputOutput(0, 1);
        System.out.println("pipe1: "+pipe1+"\npipe2: "+pipe2+"\n"+"newpipe: "+pump2.GetNeighbours().get(0));
        System.out.println("pump1: "+pump+"\npump2: "+pump2+"\n");
        System.out.println("WS: "+spring+"\n");
        System.out.println("WS 0. szom: "+spring.GetNeighbours().get(0)+"\nWS size:"+spring.GetNeighbours().size());
        System.out.println("Cistern 0. szom: "+cistern.GetNeighbours().get(0)+"\nCistern size:"+cistern.GetNeighbours().size());
        System.out.println("Pump1 0. szom: "+pump.GetNeighbours().get(0)+"\nPump1 1. szom: "+pump.GetNeighbours().get(1)+"\n");
        System.out.println("Pump2 0. szom: "+pump2.GetNeighbours().get(0)+"\nPump2 1. szom: "+pump2.GetNeighbours().get(1));
        spring.FillNeighourPipes();
        System.out.println("\n");
        System.out.println("NewPipe 0. szom: "+pump2.GetNeighbours().get(0).GetNeighbours().get(0)+"\nNewPipe 1. szom: "+pump2.GetNeighbours().get(0).GetNeighbours().get(1));
        System.out.println("Pipe1 0. szom: "+pipe1.GetNeighbours().get(0)+"\nPipe1 1. szom: "+pipe1.GetNeighbours().get(1));
        pipe1.Step();
        pump2.GetNeighbours().get(0).Step();
        pump.Step();
        pump2.Step();
        pipe2.Step();
        cistern.Step();
        System.out.println("Pumpa: "+pump.GetWaterInside());
        System.out.println("Pumpa2: "+pump2.GetWaterInside());
        System.out.println("Pipe1: "+pipe1.GetWaterInside());
        System.out.println("Pipe2: "+pipe2.GetWaterInside());
        System.out.println("Pump2 új szom: "+pump2.GetNeighbours().get(0).GetWaterInside());
        System.out.println("Cistern: "+cistern.GetWaterInside());
        System.out.println("Mechanic points: "+GameManager.GetMechanincsPoints());
        assertEquals(1, pump2.GetWaterInside());
    }
}
