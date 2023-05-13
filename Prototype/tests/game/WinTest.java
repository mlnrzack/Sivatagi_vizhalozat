package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.elements.Desert;
import game.interfaces.ISteppable;

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
			GameManager.FireSourceActions();
			GameManager.StepSteppables();
		}
		saboteur.Damage();
		for(int i = 0; i < 2; i++) {
			GameManager.FireSourceActions();
			GameManager.StepSteppables();
		}
		assertTrue("Nyert a szerelő csapat", GameManager.GetSaboteurPoints() < GameManager.GetMechanincsPoints());
	}
	
	@Test
	public void test_saboteurWin() {
		saboteur.Move(1);
		assertSame(pipe2, saboteur.GetCurrentPosition());
		for(int i = 0; i < 3; i++) {
			GameManager.FireSourceActions();
			GameManager.StepSteppables();
		}
		saboteur.Damage();
		for(int i = 3; i < 20; i++) {
			GameManager.FireSourceActions();
			pipe1.Step();
			pump.Step();
			pipe2.Step();
			cistern.Step();
			//GameManager.StepSteppables();
		}
		assertTrue("Nyert a szabotőr csapat", GameManager.GetSaboteurPoints() > GameManager.GetMechanincsPoints());
	}

}
