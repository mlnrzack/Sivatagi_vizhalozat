package game.elements;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public static class Desert
{
    public static int WaterFromPipelineNetwork /*{ get; set; }*/ = 0;

    public static void IncreaseWaterFromPipelineNetwork(int water) => WaterFromPipelineNetwork += water;
}
