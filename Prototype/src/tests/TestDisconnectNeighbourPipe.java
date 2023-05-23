package tests;

import game.elements.Pipe;
import game.elements.Pump;

public class TestDisconnectNeighbourPipe extends TestBase
{
    /**.
     * 8.2.9
     * A szerelő lecsatlakoztat egy már csatlakoztatott csövet a pumpáról.
     * Sikeres teszt esetén előszőr 2 szomszédja van, majd 1.
     */
	public static void TestDisconnectNeighbourPipes()
	{
        System.out.println("\nDisconnectNeighbourPipes Test");

        mechanic.SetCurrentPosition(pump);
        pump.AcceptPlayer(mechanic);
        System.out.println("A szerelő a pumpán.\nJelenlegi 2 szomszédjának ellenőrzése");
        System.out.println("Egyforma értéket várunk");
		System.out.println(pump.GetNeighbours().size() == 2 ? "Egyformák" : "Nem egyformák");
        
		mechanic.DisconnectNeighbourPipe(0);		
        System.out.println("Lecsatlakoztatás után az egyetlen szomszéd ellenőrzése.");
        System.out.println("A lecsatlakoztatás sikertelen volt?");
		System.out.println(pump.GetNeighbours().size() == 1 ? "Hamis" : "Igaz");
		
		System.out.println("Teszt pumpa szomszéddal való létrehozása.");
		Pump testPump = new Pump();
		testPump.SetId("testPump");
		Pipe testPipe = new Pipe();
		testPipe.AddNeighbour(testPump);
		testPump.AddPipe(testPipe);
		
		System.out.println("Szerelő tesztpumpára mozgatása.");
        mechanic.SetCurrentPosition(testPump);
        testPump.AcceptPlayer(mechanic);
		
        mechanic.DisconnectNeighbourPipe(0);		
        System.out.println("Lecsatlakoztatás után nincs szomszédja a " + testPump.GetId());
        System.out.println("A lecsatlakoztatás sikertelen volt?");
		System.out.println(testPump.GetNeighbours().size() == 0 ? "Igaz" : "Hamis");
    }
}
