package game;

import static org.junit.Assert.*;
import org.junit.Test;

public class DamageTest extends TestBaseJunit
{
	
	@Test
	public void testDamage()
	{
		saboteur.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(saboteur);
		assertFalse("Semmi baja a csőnek", pipe1.GetLeaking());
		saboteur.Damage();
		assertTrue("Sikeresen megrongálta a csövet!", pipe1.GetLeaking());
		assertFalse("Semmi baja nincs a másik csőnek", pipe2.GetLeaking());
		mechanic.SetCurrentPosition(pipe2);
		pipe2.AcceptPlayer(mechanic);
		mechanic.Damage();
		assertTrue("Sikeresen megrongálta a csövet!", pipe2.GetLeaking());
		saboteur.SetCurrentPosition(pump);
		pump.AcceptPlayer(saboteur);
		saboteur.Damage();
	}
}
