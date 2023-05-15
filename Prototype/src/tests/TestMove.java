package tests;

public class TestMove extends TestBase
{
	public void test_defaultMove()
	{
		mechanic.SetCurrentPosition(spring);
		spring.AcceptPlayer(mechanic);
		System.out.println("Igaz értéket várunk");
		System.out.println(spring.GetId().equals(mechanic.GetCurrentPosition()) ? "Igaz" : "Hamis");
		
		mechanic.Move(0);		
		System.out.println("Hamis értéket várunk");
		System.out.println(spring.GetId().equals(mechanic.GetCurrentPosition()) ? "Igaz" : "Hamis");
		
		System.out.println("Igaz értéket várunk");
		System.out.println(pipe1.GetId().equals(mechanic.GetCurrentPosition()) ? "Igaz" : "Hamis");
		
		mechanic.Move(mechanic.GetCurrentPosition().GetNeighbours().indexOf(pump));
		System.out.println("Igaz értéket várunk");
		System.out.println(pump.GetId().equals(mechanic.GetCurrentPosition()) ? "Igaz" : "Hamis");
	}
	
	public void test_wrongIndex()
	{
		mechanic.SetCurrentPosition(pump);
		pump.AcceptPlayer(mechanic);
		System.out.println("Igaz értéket várunk");
		System.out.println(pump.GetId().equals(mechanic.GetCurrentPosition()) ? "Igaz" : "Hamis");
		
		mechanic.Move(1);
		System.out.println("Igaz értéket várunk");
		System.out.println(pipe2.GetId().equals(mechanic.GetCurrentPosition()) ? "Igaz" : "Hamis");
		
		System.out.println("Hamis értéket várunk");
		System.out.println(mechanic.Move(-1) ? "Igaz" : "Hamis");
		
		System.out.println("Hamis értéket várunk");
		System.out.println(mechanic.Move(mechanic.GetCurrentPosition().GetNeighbours().indexOf(spring)) ? "Igaz" : "Hamis");
	}
	
	public void test_morePlayersOnActiveElement()
	{
		saboteur.SetCurrentPosition(pump);
		pump.AcceptPlayer(saboteur);
		mechanic.SetCurrentPosition(pipe2);
		pipe2.AcceptPlayer(mechanic);
		mechanic.Move(0);
		System.out.println("Igaz értéket várunk");
		System.out.println(saboteur.GetCurrentPosition().GetId().equals(mechanic.GetCurrentPosition()) ? "Igaz" : "Hamis");
	}	
	
	public void test_morePlayersOnPipe()
	{
		saboteur.SetCurrentPosition(spring);
		spring.AcceptPlayer(saboteur);
		saboteur.SetName("sab");
		mechanic.SetName("mec");
		mechanic.SetCurrentPosition(pump);
		pump.AcceptPlayer(mechanic);
		saboteur.Move(0);
		mechanic.Move(0);
		
		System.out.println("Igaz értéket várunk");
		System.out.println(pipe1.GetId().equals(saboteur.GetCurrentPosition()) ? "Igaz" : "Hamis");

		System.out.println("Igaz értéket várunk");
		System.out.println(pump.GetId().equals(mechanic.GetCurrentPosition()) ? "Igaz" : "Hamis");
	}
	
	public void test_movePlayersToSamePipe()
	{
		saboteur.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(saboteur);
		mechanic.SetCurrentPosition(pump);
		pump.AcceptPlayer(mechanic);
		mechanic.Move(0);
		System.out.println("Hamis értéket várunk");
		System.out.println(saboteur.GetCurrentPosition().GetId().equals(mechanic.GetCurrentPosition()) ? "Igaz" : "Hamis");
	}
}
