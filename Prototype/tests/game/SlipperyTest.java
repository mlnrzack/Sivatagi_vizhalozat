package game;

import static org.junit.Assert.*;
import game.*;
import game.elements.*;
import java.util.ArrayList;

import org.junit.Test;

public class SlipperyTest extends TestBase
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
}