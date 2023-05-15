package tests;

public class TestDisconnectNeighbourPipe extends TestBase
{
	public void testDisconnectNeighbourPipe() {
        mechanic.SetCurrentPosition(pump);
        System.out.println("Egyforma értéket várunk");
		System.out.println(pipe1.GetSticky() == 0 ? "Egyformák" : "Nem egyformák");
        //assertEquals(2,pump.GetNeighbours().size());
        
        mechanic.DisconnectNeighbourPipe(0);
        System.out.println("Egyforma értéket várunk");
		System.out.println(pump.GetNeighbours().size() == 1 ? "Egyformák" : "Nem egyformák");
        //assertEquals(1,pump.GetNeighbours().size());
    }
}
