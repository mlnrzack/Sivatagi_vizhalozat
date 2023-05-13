package game;

import static org.junit.Assert.*;
import game.elements.*;
import game.players.Player;

import org.junit.Test;

public class MoveTest extends TestBase {

	@Test
	public void test_defaultMove() {
		mechanic.SetCurrentPosition(spring);
		assertSame(spring, mechanic.GetCurrentPosition());
		mechanic.Move(0);
		assertNotSame(spring, mechanic.GetCurrentPosition());
		assertSame(pipe1, mechanic.GetCurrentPosition());
		mechanic.Move(mechanic.GetCurrentPosition().GetNeighbours().indexOf(pump));
		assertSame(pump, mechanic.GetCurrentPosition());
	}
	
	@Test
	public void test_wrongIndex() {
		mechanic.SetCurrentPosition(pump);
		assertSame(pump, mechanic.GetCurrentPosition());
		mechanic.Move(1);
		assertSame(pipe2, mechanic.GetCurrentPosition());
		assertFalse("Ilyen helyre nem léphet egy játékos sem", mechanic.Move(-1));
		assertFalse("Ilyen helyre nem léphet egy játékos sem", mechanic.Move(mechanic.GetCurrentPosition().GetNeighbours().indexOf(spring)));
	}
	
	@Test
	public void test_morePlayersOnActiveElement(){
		saboteur.SetCurrentPosition(pump);
		mechanic.SetCurrentPosition(pipe2);
		mechanic.Move(0);
		assertSame(saboteur.GetCurrentPosition(), mechanic.GetCurrentPosition());
	}
	
	@Test
	public void test_morePlayersOnPipe() {
		saboteur.SetCurrentPosition(spring);
		saboteur.SetName("sab");
		mechanic.SetName("mec");
		mechanic.SetCurrentPosition(pump);
		saboteur.Move(0);
		mechanic.Move(0);
		assertSame(pipe1, saboteur.GetCurrentPosition());
		assertSame(pump, mechanic.GetCurrentPosition());
	}

}
