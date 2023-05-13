package game;

import game.elements.*;
import game.players.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DamageTest extends TestBase {
	
	@Test
	public void testDamage() {
		saboteur.SetCurrentPosition(pipe1);
		assertFalse("Semmi baja a csőnek", pipe1.GetLeaking());
		saboteur.Damage();
		assertTrue("Sikeresen megrongálta a csövet!", pipe1.GetLeaking());
		assertFalse("Semmi baja nincs a másik csőnek", pipe2.GetLeaking());
		saboteur.SetCurrentPosition(pump);
		saboteur.Damage();
	}

}