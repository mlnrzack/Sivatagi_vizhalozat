package game;

import static org.junit.Assert.*;

import org.junit.Test;

import game.elements.Cistern;
import game.elements.Pipe;

public class StickyTest extends TestBaseJunit
{

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
	
	@Test
	public void TestStickyFunction()
	{
		//mini init
		Pipe pipe3 = new Pipe();
		Cistern cistern1 = new Cistern();
		cistern.AddPipe(pipe3);
		pipe3.AddNeighbour(cistern1);
		
		saboteur.SetCurrentPosition(pipe3);
		pipe3.AcceptPlayer(saboteur);
		GameManager.SetCurrentSaboteur(saboteur);
		GameManager.SetCurrentMechanic(null);
		
		mechanic.SetCurrentPosition(cistern1);
		cistern1.AcceptPlayer(mechanic);
		
		saboteur.SetStickyPipe();
		saboteur.Move(0);
		
		GameManager.SetCurrentSaboteur(null);
		GameManager.SetCurrentMechanic(mechanic);
		
		mechanic.Move(0);
		
		//A GameManager osztály ezt kezeli, itt ez külön nem hívódik meg, igy kézi átállítás lesz.
		GameManager.SetCurrentSaboteur(saboteur);
		GameManager.SetCurrentMechanic(null);
		
		assertNotEquals(mechanic, GameManager.GetCurrentMechanic());
		assertEquals(saboteur, GameManager.GetCurrentSaboteur());
	}
}
