package tests;

import game.*;

public class TestBuildPumpIntoPipe extends TestBase
{
    /** 8.2.12
     * Egy szerelő beépít egy pumpát egy cső közepébe.
     */
    public void TestTryBuildPump()
    {
        mechanic.SetCurrentPosition(pipe1);
        pipe1.AcceptPlayer(mechanic);
        
        if (pipe1.TryBuildPumpInto(pump2))
        {
            System.out.println("Sikeres teszt");
        }
    }
    
    /**8.2.8
     * Egy játékos pumpát állít fel
     */
    public void TestTryBuildPumpWithWaterFlow()
    {
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
        
        System.out.println("Egyforma értéket várunk");
		System.out.println(cistern.GetWaterInside() == 1 ? "Egyformák" : "Nem egyformák");
    }
}