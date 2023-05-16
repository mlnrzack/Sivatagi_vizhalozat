package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class SlipperyTest extends TestBaseJunit
{
	@Test
	public void testSlippery()
	{
		saboteur.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(saboteur);
		assertEquals(0, pipe1.GetSlippery());
		saboteur.SetSlipperyPipe();
		assertNotEquals(0, pipe1.GetSlippery());
		assertEquals(Constants.LeakageTimerBound - 1, pipe1.GetSlippery());
		saboteur.SetSlipperyPipe();
		assertNotEquals(0, pipe1.GetSlippery());
		saboteur.SetCurrentPosition(pipe2);
		pipe2.AcceptPlayer(saboteur);
		assertEquals(0, pipe2.GetSlippery());
		saboteur.SetSlipperyPipe();
		assertEquals(Constants.LeakageTimerBound - 1, pipe2.GetSlippery());
	}

	@Test
	public void TestSlipperyFunction()
	{
		//TODO
		TestBaseJunit.init();
		
		saboteur.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(saboteur);
		
		mechanic.SetCurrentPosition(spring);
		spring.AcceptPlayer(mechanic);
		
		saboteur.SetSlipperyPipe();
		saboteur.Move(0);
		
		mechanic.Move(0);
		System.out.println("Vajon hova került a mechanic játékos?");
		System.out.println(mechanic.GetCurrentPosition().GetId() + " erre az elemere került a mechanic játkos!");
		assertSame(spring.GetNeighbours().get(0).GetId(), mechanic.GetCurrentPosition().GetId());
	}
}