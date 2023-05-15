package tests;

public class TestSetPump extends TestBase
{
	/**8.2.8
     * Egy játékos pumpák bemenetét állítja be.
     */
    public void TestTrySetPump()
    {
        mechanic.SetCurrentPosition(pump);
        pump.AcceptPlayer(mechanic);
        pump.AddPipe(pipe1);
        pump.AddPipe(pipe2);
        pump.TrySetInputOutput(0, 1);
        
        System.out.println("Egyforma értéket várunk");
		System.out.println(pipe1.GetId().equals(pump.GetInput().GetId()) ? "Egyformák" : "Nem egyformák");
		
        //elvileg itt így kellene nézni, és nem a neighbours.get(index)-re, ha fut törölendő a sor
        System.out.println("Egyforma értéket várunk");
		System.out.println(pipe2.GetId().equals(pump.GetOutput().GetId()) ? "Egyformák" : "Nem egyformák");
    }
}
