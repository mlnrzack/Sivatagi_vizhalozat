package game;

import java.util.*;

import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class TestBaseJunit 
{
	static GameManager gamem = new GameManager();
	static Cistern cistern = new Cistern();
	static Pipe pipe1 = new Pipe();
	static Pipe pipe2 = new Pipe();
	static Pump pump = new Pump();
	static Pump pump2 = new Pump();
	static WaterSpring spring = new WaterSpring();
	static ArrayList<IElement> map = new ArrayList<IElement>();
	
	Saboteur saboteur = new Saboteur();
	Mechanic mechanic = new Mechanic();
	
	public static void init()
	{	
		/*
		cistern.SetId("cistern");
		pipe1.SetId("pipe1");
		pipe2.SetId("pipe2");
		pump.SetId("pump");
		pump2.SetId("pump2");
		spring.SetId("spring");
		*/
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
