package game.elements;

import game.GameManager;

public class Desert
{
    public static void IncreaseWaterFromPipelineNetwork(int water)
    {
    	GameManager.SetSaboteursPoints(GameManager.GetSaboteurPoints() + water);
    }
}
