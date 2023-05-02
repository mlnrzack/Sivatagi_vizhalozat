package game;

import game.elements.*;
import game.players.*;

public class Program
{
    public static void main(String[] args)
    {
        CreateMap();
        GameManager.StartGame();
    }

    private static void CreateMap()
    {
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        Pipe pipe4 = new Pipe();
        Pipe pipe5 = new Pipe();
        Pipe pipe6 = new Pipe();
        Pipe pipe7 = new Pipe();
        Pipe pipe8 = new Pipe();
        Pipe pipe9 = new Pipe();
        Pipe pipe10 = new Pipe();
        Pipe pipe11 = new Pipe();
        Pipe pipe12 = new Pipe();
        Pipe pipe13 = new Pipe();
        Pipe pipe14 = new Pipe();
        Pipe pipe15 = new Pipe();
        Pipe pipe16 = new Pipe();
        WaterSpring spring1 = new WaterSpring();
        WaterSpring spring2 = new WaterSpring();
        Cistern cistern1 = new Cistern();
        Cistern cistern2 = new Cistern();
        Cistern cistern3 = new Cistern();
        Pump pump1 = new Pump();
        Pump pump2 = new Pump();
        Pump pump3 = new Pump();
        Pump pump4 = new Pump();
        Pump pump5 = new Pump();
        Pump pump6 = new Pump();
        Pump pump7 = new Pump();

        var mechanic1 = new Mechanic();
        var saboteur1 = new Saboteur();

        spring1.SetId("spring1");
        spring1.AddPipe(pipe1);
        
        spring2.SetId("spring2");

        pipe1.SetId("pipe1");
        pipe1.AddNeighbour(spring1);
        pipe1.AddNeighbour(pump1);
        pipe1.AcceptPlayer(mechanic1);

        pipe2.SetId("pipe2");
        pipe2.AddNeighbour(pump1);
        pipe2.AddNeighbour(cistern1);
        pipe2.AcceptPlayer(saboteur1);

        pipe3.SetId("pipe3");
        pipe3.AddNeighbour(pump1);
        pipe3.AddNeighbour(pump2);

        pipe4.SetId("pipe4");
        pipe4.AddNeighbour(pump2);
        pipe4.AddNeighbour(pump3);

        pipe5.SetId("pipe5");
        pipe5.AddNeighbour(pump3);
        pipe5.AddNeighbour(cistern1);
        
        pipe6.SetId("pipe6");
        
        pipe7.SetId("pipe7");

        pipe8.SetId("pipe8");
        
        pipe9.SetId("pipe9");
        
        pipe10.SetId("pipe10");
        
        pipe11.SetId("pipe11");
        
        pipe12.SetId("pipe12");
        
        pipe13.SetId("pipe13");
        
        pipe14.SetId("pipe14");
        
        pipe15.SetId("pipe15");
        
        pipe16.SetId("pipe16");
        
        pump1.SetId("pipe1");
        pump1.AddPipe(pipe1);
        pump1.AddPipe(pipe2);
        pump1.AddPipe(pipe3);

        pump2.SetId("pump2");
        pump2.AddPipe(pipe3);
        pump2.AddPipe(pipe4);

        pump3.SetId("pump3");
        pump3.AddPipe(pipe4);
        pump3.AddPipe(pipe5);

        pump4.SetId("pump4");
        
        pump5.SetId("pump5");
        
        pump6.SetId("pump6");
        
        pump7.SetId("pump7");
        
        cistern1.SetId("cistern1");
        cistern1.AddPipe(pipe2);
        cistern1.AddPipe(pipe5);

        cistern2.SetId("cistern2");
        
        cistern3.SetId("cistern3");
        
        mechanic1.SetCurrentPosition(pipe1);
        saboteur1.SetCurrentPosition(pipe2);
    }
}