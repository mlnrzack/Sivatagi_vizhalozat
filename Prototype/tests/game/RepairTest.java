package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class RepairTest extends TestBase
{
	@Test
	public void testRepair()
	{
		mechanic.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(mechanic);
		assertFalse("Semmi baja a csőnek", pipe1.GetLeaking());
		mechanic.Damage();
		assertTrue("Sikeresen megrongálta a csövet!", pipe1.GetLeaking());
		assertFalse("Semmi baja nincs a másik csőnek", pipe2.GetLeaking());
		mechanic.Repair();
		assertFalse("Megjavította a rossz csövet", pipe1.GetLeaking());
		mechanic.SetCurrentPosition(pipe2);
		pipe2.AcceptPlayer(mechanic);
		mechanic.Repair();
		assertFalse("A cső nem is volt lyukas", pipe2.GetLeaking());
	}

}
