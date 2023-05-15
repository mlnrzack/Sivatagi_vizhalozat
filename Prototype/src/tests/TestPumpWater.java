package tests;

import game.GameManager;

public class TestPumpWater extends TestBase
{
	public void testWaterPump()
    {
        mechanic.SetCurrentPosition(pump);
        pump.AcceptPlayer(mechanic);
        
        spring.FillNeighourPipes();
        pump.TrySetInputOutput(0, 1);
        pipe1.Step();
        pump.Step();
        spring.FillNeighourPipes();
        pump.Step();
        pipe2.Step();
        cistern.Step();
        
        System.out.println("Pipe1: "+pipe1.GetWaterInside());
        System.out.println("Pumpa: "+pump.GetWaterInside());
        System.out.println("Pipe2: "+pipe2.GetWaterInside());
        System.out.println("Cistern: "+cistern.GetWaterInside());
        System.out.println("Mechanic points: "+GameManager.GetMechanincsPoints());
        
        System.out.println("Egyező értéket várunk");
		System.out.println(cistern.GetWaterInside() == 1 ? "Egyeznek" : "Nem egyeznek az értékek");
    }
}
