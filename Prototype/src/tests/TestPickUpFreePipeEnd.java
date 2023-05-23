package tests;

import game.elements.*;

public class TestPickUpFreePipeEnd extends TestBase
{
    /**8.2.10
     * Játékos felveszi egy csőnek az egyik végét a ciszternából.
     */
    public static void TestPickUpFreePipe()
    {
        System.out.println("\nPick Up Free Pipe Test");

        mechanic.SetCurrentPosition(pump);
        pump.AcceptPlayer(mechanic);
        System.out.println("Szerelő a " + mechanic.GetCurrentPosition().GetId() + "-n majd ellenőrzése, hogy rendelkezik-e pipe-al az inventory-ában.");
        System.out.println("null értéket várunk");
		System.out.println(mechanic.GetPipeInInventory() == null ? "null" : "Hamis, van értéke");

        System.out.println("Pipe lecsatlakoztatása után ellenőrizzük, hogy még mindig null értékű a nála pipe (ha van nála).");
        mechanic.DisconnectNeighbourPipe(1);
        System.out.println("Mivel olyan csövet akart leválasztani a pumpáról, amely be-, vagy kimeneti, így nem tudott felvenni csővéget, ugye?");
		System.out.println(mechanic.GetPipeInInventory() == null ? "Nem tudott." : "Fel tudott");
        
        Pipe testPipe = new Pipe();
        testPipe.AddNeighbour(cistern);
        cistern.AddPipe(testPipe);
        
        mechanic.SetCurrentPosition(cistern);
        cistern.AcceptPlayer(mechanic);
        System.out.println("\nSzerelő a " + mechanic.GetCurrentPosition().GetId()  + "-n majd ellenőrzése, hogy rendelkezik-e pipe-al az inventory-ában.");
        System.out.println("Null értéket várunk");
		System.out.println(mechanic.GetPipeInInventory() == null ? "null" : "Hamis, van értéke");

        System.out.println("Pipe lecsatlakoztatása után ellenőrizzük, hogy még mindig null értékű a nála pipe (ha van nála).");
        mechanic.DisconnectNeighbourPipe(1);
        System.out.println("Mivel ciszternán állt, így fel tudott venni csővéget.");
		System.out.println(mechanic.GetPipeInInventory() == null ? "Nem tudott felvenni." : "Fel tudott venni.");
    }
}
