package game;

import org.junit.Test;


public class BuildPumpIntoPipeTest extends TestBase
{
    /** 8.2.12
     * Egy szerelő beépít egy pumpát egy cső közepébe.
     */
    @Test
    public void testTrySetPump()
    {
        mechanic.SetCurrentPosition(pipe1);
        
        if (pipe1.TryBuildPumpInto(pump2, 1000, 1000))
        {
            System.out.println("Sikeres teszt");
        }
    }
}