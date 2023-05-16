package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class StickyTest extends TestBaseJunit{

	@Test
	public void TestSticky()
	{
		saboteur.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(saboteur);
		assertEquals(0, pipe1.GetSticky());
		saboteur.SetStickyPipe();
		assertNotEquals(0, pipe1.GetSticky());
		saboteur.SetStickyPipe();
		assertNotEquals(0, pipe1.GetSticky());
		saboteur.SetCurrentPosition(pipe2);
		assertEquals(0, pipe2.GetSticky());
		saboteur.SetStickyPipe();
		assertEquals(Constants.LeakageTimerBound - 1, pipe2.GetSticky());
	}
	
	
}
