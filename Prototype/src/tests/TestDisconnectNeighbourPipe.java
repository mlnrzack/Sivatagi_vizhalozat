package tests;

public class TestDisconnectNeighbourPipe extends TestBase
{
	public void testDisconnectNeighbourPipe() 
	{
        mechanic.SetCurrentPosition(pump);
        System.out.println("Egyforma értéket várunk");
		System.out.println(pump.GetNeighbours().size() == 2 ? "Egyformák" : "Nem egyformák");
        
        mechanic.DisconnectNeighbourPipe(0);
        System.out.println("Egyforma értéket várunk");
		System.out.println(pump.GetNeighbours().size() == 1 ? "Egyformák" : "Nem egyformák");
    }
}
