package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.elements.Desert;

import org.junit.BeforeClass;

public class WinTest extends TestBase {

	@Before
	public void initTest() {
		GameManager.SetMechanicsPoints(0);
		GameManager.SetSaboteursPoints(0);
		spring.SetWaterInside(0);
		pipe1.SetWaterInside(0);
		pump.SetWaterInside(0);
		pipe2.SetWaterInside(0);
		cistern.SetWaterInside(0);
		mechanic.SetCurrentPosition(pump);
		saboteur.SetCurrentPosition(pump);
		assertTrue("Sikerült beállítani a pumpálást", pump.TrySetInputOutput(0, 1));
	}
	
	@Test
	public void test_mechanicWin() {
		saboteur.Move(1);
		assertSame(pipe2, saboteur.GetCurrentPosition());
		for(int i = 0; i < Constants.RoundNumber-2; i++) {
			spring.FillNeighourPipes();
			pipe1.Step();
			pump.Step();
			pipe2.Step();
			cistern.Step();
		}
		saboteur.Damage();
		for(int i = 0; i < 2; i++) {
			spring.FillNeighourPipes();
			pipe1.Step();
			pump.Step();
			pipe2.Step();
			cistern.Step();
		}
		assertTrue("Nyert a szerelő csapat", GameManager.GetSaboteurPoints() < GameManager.GetMechanincsPoints());
		
	}
	
	@Test
	public void test_saboteurWin() {
		saboteur.Move(1);
		assertSame(pipe2, saboteur.GetCurrentPosition());
		for(int i = 0; i < Constants.RoundNumber / 2; i++) {
			spring.FillNeighourPipes();
			pipe1.Step();
			pump.Step();
			pipe2.Step();
			cistern.Step();
		}
		saboteur.Damage();
		for(int i = Constants.RoundNumber /2; i < Constants.RoundNumber; i++) {
			spring.FillNeighourPipes();
			pipe1.Step();
			pump.Step();
			pipe2.Step();
			cistern.Step();
		}
		assertTrue("Nyert a szabotőr csapat", GameManager.GetSaboteurPoints() > GameManager.GetMechanincsPoints());
	}

}
