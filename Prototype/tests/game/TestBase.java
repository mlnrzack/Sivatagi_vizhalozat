package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.elements.*;
import game.players.*;

public class TestBase 
{
	GameManager gamem = new GameManager();
	Cistern cistern = new Cistern();
	Pipe pipe1 = new Pipe();
	Pipe pipe2 = new Pipe();
	Pump pump = new Pump();
	Pump pump2 = new Pump();
	WaterSpring spring = new WaterSpring();
	
	Saboteur saboteur = new Saboteur();
	Mechanic mechanic = new Mechanic();
	
	@Before
	public void init()
	{	
		spring.AddPipe(pipe1);
		pump.AddPipe(pipe1);
		pump.AddPipe(pipe2);
		cistern.AddPipe(pipe2);
		pipe1.AddNeighbour(spring);
		pipe1.AddNeighbour(pump);
		pipe2.AddNeighbour(pump);
		pipe2.AddNeighbour(cistern);
	}
}
