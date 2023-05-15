package game;

import org.junit.Test;

import game.elements.Pump;

import static org.junit.Assert.*;
public class TryBuildPumpTest extends TestBase{
    /**8.2.8
     * Egy játékos pumpát állít fel
     */
    @Test
    public void testTryBuildPump()
    {
        mechanic.SetPumpInInventory(pump2);
        mechanic.SetCurrentPosition(pipe1);
        pipe1.AcceptPlayer(mechanic);
        
        System.out.println(map.size());
        for(int i = 0; i < map.size(); i++)
        {
        	System.out.print(map.get(i).GetId() + ": ");
        	for(int j = 0; j < map.get(i).GetNeighbours().size(); j++)
        		System.out.print(map.get(i).GetNeighbours().get(j).GetId() + " ");
        	System.out.print("\n");
        }
        
        System.out.println("____________________");
        mechanic.BuildPumpIntoPipe();
        System.out.println(map.size());
        
        for(int i = 0; i < map.size(); i++)
        {
        	System.out.print(map.get(i).GetId() + ": ");
        	for(int j = 0; j < map.get(i).GetNeighbours().size(); j++)
        		System.out.print(map.get(i).GetNeighbours().get(j).GetId() + " ");
        	System.out.print("\n");
        }
        
        System.out.println("____________________");
        pump2.TrySetInputOutput(0, 1);
        for(int i = 0; i < map.size(); i++)
        {
        	if(map.get(i).GetType() == "pump")
        	{
        		Pump temp = (Pump)map.get(i);
        		System.out.print(temp.GetInput().GetId() + " " + temp.GetOutput().GetId());
        	}
        	System.out.println("");
        }
        /*
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
        assertEquals(1, pump2.GetWaterInside());*/
    }
}
