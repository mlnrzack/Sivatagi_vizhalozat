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
    
    /**Egy statikus térképet létrehozó függvény.
     */
    private static void CreateMap()
    {
        Cistern cistern1 = new Cistern();
        Cistern cistern2 = new Cistern();
        Cistern cistern3 = new Cistern();
        
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
        Pipe pipe17 = new Pipe();
        Pipe pipe18 = new Pipe();
        Pipe pipe19 = new Pipe();
        
        Pump pump1 = new Pump();
        Pump pump2 = new Pump();
        Pump pump3 = new Pump();
        Pump pump4 = new Pump();
        Pump pump5 = new Pump();
        Pump pump6 = new Pump();
        Pump pump7 = new Pump();
        Pump pump8 = new Pump();
        Pump pump9 = new Pump();
        Pump pump10 = new Pump();
        Pump pump11 = new Pump();
        
        WaterSpring spring1 = new WaterSpring();
        WaterSpring spring2 = new WaterSpring();
        
        cistern1.SetId("cistern1");
        cistern1.AddPipe(pipe19);

        cistern2.SetId("cistern2");
        cistern2.AddPipe(pipe18);
        
        cistern3.SetId("cistern3");
        cistern3.AddPipe(pipe15);
        
        pipe1.SetId("pipe1");
        pipe1.AddNeighbour(spring1);
        pipe1.AddNeighbour(pump1);

        pipe2.SetId("pipe2");
        pipe2.AddNeighbour(spring2);
        pipe2.AddNeighbour(pump2);

        pipe3.SetId("pipe3");
        pipe3.AddNeighbour(spring1);
        pipe3.AddNeighbour(pump2);

        pipe4.SetId("pipe4");
        pipe4.AddNeighbour(pump1);
        pipe4.AddNeighbour(pump3);

        pipe5.SetId("pipe5");
        pipe5.AddNeighbour(pump2);
        pipe5.AddNeighbour(pump3);
        
        pipe6.SetId("pipe6");
        pipe6.AddNeighbour(pump1);
        pipe6.AddNeighbour(pump4);
        
        pipe7.SetId("pipe7");
        pipe7.AddNeighbour(pump4);
        pipe7.AddNeighbour(pump5);

        pipe8.SetId("pipe8");
        pipe8.AddNeighbour(pump4);
        pipe8.AddNeighbour(pump6);
        
        pipe9.SetId("pipe9");
        pipe9.AddNeighbour(pump3);
        pipe9.AddNeighbour(pump6);
        
        pipe10.SetId("pipe10");
        pipe10.AddNeighbour(pump3);
        pipe10.AddNeighbour(pump7);
        
        pipe11.SetId("pipe11");
        pipe11.AddNeighbour(pump5);
        pipe11.AddNeighbour(pump8);
        
        pipe12.SetId("pipe12");
        pipe12.AddNeighbour(pump6);
        pipe12.AddNeighbour(pump8);
        
        pipe13.SetId("pipe13");
        pipe13.AddNeighbour(pump6);
        pipe13.AddNeighbour(pump9);
        
        pipe14.SetId("pipe14");
        pipe14.AddNeighbour(pump7);
        pipe14.AddNeighbour(pump9);
        
        pipe15.SetId("pipe15");
        pipe15.AddNeighbour(pump8);
        pipe15.AddNeighbour(cistern3);
        
        pipe16.SetId("pipe16");
        pipe16.AddNeighbour(pump9);
        pipe16.AddNeighbour(pump10);
        
        pipe17.SetId("pipe17");
        pipe17.AddNeighbour(pump9);
        pipe17.AddNeighbour(pump11);
        
        pipe18.SetId("pipe18");
        pipe18.AddNeighbour(pump10);
        pipe18.AddNeighbour(cistern2);
        
        pipe19.SetId("pipe19");
        pipe19.AddNeighbour(pump11);
        pipe19.AddNeighbour(cistern1);
        
        pump1.SetId("pipe1");
        pump1.AddPipe(pipe1);
        pump1.AddPipe(pipe4);
        pump1.AddPipe(pipe6);
        pump1.TrySetInputOutput(0, 2);

        pump2.SetId("pump2");
        pump2.AddPipe(pipe2);
        pump2.AddPipe(pipe3);
        pump2.AddPipe(pipe5);        
        pump2.TrySetInputOutput(0, 2);

        pump3.SetId("pump3");
        pump3.AddPipe(pipe4);
        pump3.AddPipe(pipe5);
        pump3.AddPipe(pipe9);
        pump3.AddPipe(pipe10);
        pump3.TrySetInputOutput(1, 3);

        pump4.SetId("pump4");
        pump4.AddPipe(pipe6);
        pump4.AddPipe(pipe7);
        pump4.AddPipe(pipe8);
        pump4.TrySetInputOutput(0, 1);
        
        pump5.SetId("pump5");
        pump5.AddPipe(pipe7);
        pump5.AddPipe(pipe11);
        pump5.TrySetInputOutput(0, 1);
        
        pump6.SetId("pump6");
        pump6.AddPipe(pipe8);
        pump6.AddPipe(pipe9);
        pump6.AddPipe(pipe12);
        pump6.AddPipe(pipe13);
        pump6.TrySetInputOutput(1, 3);
        
        pump7.SetId("pump7");
        pump7.AddPipe(pipe10);
        pump7.AddPipe(pipe14);
        pump7.TrySetInputOutput(0, 1);
        
        
        pump8.SetId("pump8");
        pump8.AddPipe(pipe11);
        pump8.AddPipe(pipe12);
        pump8.AddPipe(pipe15);
        pump8.TrySetInputOutput(0, 2);
        
        pump9.SetId("pump9");
        pump9.AddPipe(pipe13);
        pump9.AddPipe(pipe14);
        pump9.AddPipe(pipe16);
        pump9.AddPipe(pipe17);
        pump9.TrySetInputOutput(1, 3);
        
        pump10.SetId("pump10");
        pump10.AddPipe(pipe16);
        pump10.AddPipe(pipe18);
        pump10.TrySetInputOutput(0, 1);
        
        pump11.SetId("pum11");
        pump11.AddPipe(pipe17);
        pump11.AddPipe(pipe19);
        pump11.TrySetInputOutput(0, 1);
        
        spring1.SetId("spring1");
        spring1.AddPipe(pipe1);
        spring1.AddPipe(pipe3);
        
        spring2.SetId("spring2");
        spring2.AddPipe(pipe2);
    }
}