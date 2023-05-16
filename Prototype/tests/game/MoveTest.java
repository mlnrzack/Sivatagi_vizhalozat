package game;

import static org.junit.Assert.*;
import game.elements.*;
import game.players.Player;

import org.junit.Test;

public class MoveTest extends TestBaseJunit
{
	@Test
	public void test_defaultMove()
	{
		mechanic.SetCurrentPosition(spring);
		spring.AcceptPlayer(mechanic);
		assertSame(spring, mechanic.GetCurrentPosition());
		mechanic.Move(0);
		assertNotSame(spring, mechanic.GetCurrentPosition());
		assertSame(pipe1, mechanic.GetCurrentPosition());
		mechanic.Move(mechanic.GetCurrentPosition().GetNeighbours().indexOf(pump));
		assertSame(pump, mechanic.GetCurrentPosition());
	}
	
	@Test
	public void test_wrongIndex()
	{
		mechanic.SetCurrentPosition(pump);
		pump.AcceptPlayer(mechanic);
		assertSame(pump, mechanic.GetCurrentPosition());
		mechanic.Move(1);
		assertSame(pipe2, mechanic.GetCurrentPosition());
		assertFalse("Ilyen helyre nem léphet egy játékos sem", mechanic.Move(-1));
		assertFalse("Ilyen helyre nem léphet egy játékos sem", mechanic.Move(mechanic.GetCurrentPosition().GetNeighbours().indexOf(spring)));
	}
	
	@Test
	public void test_morePlayersOnActiveElement()
	{
		saboteur.SetCurrentPosition(pump);
		pump.AcceptPlayer(saboteur);
		mechanic.SetCurrentPosition(pipe2);
		pipe2.AcceptPlayer(mechanic);
		mechanic.Move(0);
		assertSame(saboteur.GetCurrentPosition(), mechanic.GetCurrentPosition());
	}
	
	@Test
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
		assertSame(pipe1, saboteur.GetCurrentPosition());
		assertSame(pump, mechanic.GetCurrentPosition());
	}
	
	@Test
	public void test_movePlayersToSamePipe()
	{
		saboteur.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(saboteur);
		mechanic.SetCurrentPosition(pump);
		pump.AcceptPlayer(mechanic);
		mechanic.Move(0);
		assertNotSame(saboteur.GetCurrentPosition(), mechanic.GetCurrentPosition());
	}
}
