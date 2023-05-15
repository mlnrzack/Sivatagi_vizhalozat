package tests;

public class TestPickUpPump extends TestBase
{
	/**8.2.13
     * Egy szerelő felvesz egy pumpát (a ciszternánál).
     */
	public void testPickUpPump()
    {
        mechanic.SetCurrentPosition(cistern);
        cistern.AcceptPlayer(mechanic);
        
        System.out.println("Null értéket várunk");
		System.out.println(mechanic.GetPumpInInventory() == null ? "null" : "Hamis, van értéke");
        
        mechanic.PickUpPump();
        System.out.println("Nem egyező értéket várunk");
		System.out.println(mechanic.GetPumpInInventory() == null ? "Egyeznek" : "Nem egyeznek az értékek");
    }
}
