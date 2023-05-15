package game;

import org.junit.Test;
import static org.junit.Assert.*;
public class PickUpPumpTest extends TestBase{
    /**
     * 8.2.13
     * Egy szerelő felvesz egy pumpát (a ciszternánál).
     */
    @Test
    public void testPickUpPump(){
        mechanic.SetCurrentPosition(cistern);
        assertNull("Felvétel előtt.", mechanic.GetPumpInInventory());
        mechanic.PickUpPump();
        assertNotEquals("Sikeres felvétel.",null, mechanic.GetPumpInInventory());
    }
}