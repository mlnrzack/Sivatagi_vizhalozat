package tests;

public class TestPickUpFreePipeEnd extends TestBase
{
    /**8.2.10
     * Játékos felveszi egy csőnek az egyik végét a ciszternából.
     */
    public void testPickUpFreePipeEnd()
    {
        mechanic.SetCurrentPosition(pump);
        pump.AcceptPlayer(mechanic);
        System.out.println("Null értéket várunk");
		System.out.println(mechanic.GetPipeInInventory() == null ? "null" : "Hamis, van értéke");
		
        mechanic.DisconnectNeighbourPipe(1);
        System.out.println("Nem egyező értéket várunk");
		System.out.println(mechanic.GetPipeInInventory() == null ? "Egyeznek" : "Nem egyeznek");
    }
}
