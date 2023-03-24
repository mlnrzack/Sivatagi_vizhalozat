package game;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Program
{
    static void Main(String[] args)
    {
        CreateTestMap();
        GameController.StartGame();
    }

    private static void CreateTestMap()
    {
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        Pipe pipe4 = new Pipe();
        Pipe pipe5 = new Pipe();
        WaterSpring source = new WaterSpring();
        Cistern cistern = new Cistern();
        Pump pump1 = new Pump();
        Pump pump2 = new Pump();
        Pump pump3 = new Pump();

        var mechanic1 = new Mechanic();
        var saboteur1 = new Saboteur();

        source.Neighbours.Add(pipe1);

        pipe1.AddNeighbour(source);
        pipe1.AddNeighbour(pump1);
        pipe1.AcceptPlayer(mechanic1);

        pipe2.AddNeighbour(pump1);
        pipe2.AddNeighbour(cistern);
        pipe2.AcceptPlayer(saboteur1);

        pipe3.AddNeighbour(pump1);
        pipe3.AddNeighbour(pump2);

        pipe4.AddNeighbour(pump2);
        pipe4.AddNeighbour(pump3);

        pipe5.AddNeighbour(pump3);
        pipe5.AddNeighbour(cistern);

        pump1.Neighbours.Add(pipe1);
        pump1.Neighbours.Add(pipe2);
        pump1.Neighbours.Add(pipe3);

        pump2.Neighbours.Add(pipe3);
        pump2.Neighbours.Add(pipe4);

        pump3.Neighbours.Add(pipe4);
        pump3.Neighbours.Add(pipe5);

        cistern.Neighbours.Add(pipe2);
        cistern.Neighbours.Add(pipe5);

        mechanic1.CurrentPosition = pipe1;
        saboteur1.CurrentPosition = pipe2;

        var map = new List<IElement>() { pipe1, pipe2, pipe3, pipe4, pipe5, cistern, source, pump1, pump2, pump3 };
    }
}