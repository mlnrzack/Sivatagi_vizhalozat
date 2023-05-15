package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import game.elements.*;
import game.interfaces.*;
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
	ArrayList<IElement> map = new ArrayList<IElement>();
	
	Saboteur saboteur = new Saboteur();
	Mechanic mechanic = new Mechanic();
	
	@Before
	public void init()
	{	
		cistern.SetId("cistern");
		pipe1.SetId("pipe1");
		pipe2.SetId("pipe2");
		pump.SetId("pump");
		pump2.SetId("pump2");
		spring.SetId("spring");
		
		spring.AddPipe(pipe1);
		pump.AddPipe(pipe1);
		pump.AddPipe(pipe2);
		cistern.AddPipe(pipe2);
		pipe1.AddNeighbour(spring);
		pipe1.AddNeighbour(pump);
		pipe2.AddNeighbour(pump);
		pipe2.AddNeighbour(cistern);
		
		map.add(cistern);
		map.add(pipe1);
		map.add(pipe2);
		map.add(pump);
		map.add(pump2);
		map.add(spring);
	}
}
