package game;

import org.junit.Test;


public class BuildPumpIntoPipeTest extends TestBaseJunit
{
    /** 8.2.12
     * Egy szerelő beépít egy pumpát egy cső közepébe.
     */
    @Test
    public void TestTryBuildPump()
    {
        mechanic.SetCurrentPosition(pipe1);
        pipe1.AcceptPlayer(mechanic);
        
        if (pipe1.TryBuildPumpInto(pump2))
        {
            System.out.println("Sikeres teszt");
        }
    }
}