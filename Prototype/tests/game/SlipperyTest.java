package game;

import static org.junit.Assert.*;
import game.elements.*;
import java.util.ArrayList;

import org.junit.Test;

public class SlipperyTest extends TestBase {

	@Test
	public void testSlippery() {
		saboteur.SetCurrentPosition(pipe1);
		assertEquals(0, pipe1.GetSlippery());
		saboteur.SetSlipperyPipe();
		assertNotEquals(0, pipe1.GetSlippery());
		saboteur.SetSlipperyPipe();
		assertNotEquals(0, pipe1.GetSlippery());
		saboteur.SetCurrentPosition(pipe2);
		assertEquals(0, pipe2.GetSlippery());
		saboteur.SetStickyPipe();
		saboteur.SetSlipperyPipe();
		assertEquals(0, pipe2.GetSlippery());
	}

}
