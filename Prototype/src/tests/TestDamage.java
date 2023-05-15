package tests;

public class TestDamage extends TestBase
{
	public void testDamage()
	{
		saboteur.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(saboteur);
		System.out.println("Hamis értéket várunk");
		System.out.println(pipe1.GetLeaking()? "Igaz" : "Hamis");
		saboteur.Damage();
		System.out.println("Igaz értéket várunk");
		System.out.println(pipe1.GetLeaking()? "Igaz" : "Hamis");
		System.out.println("Hamis értéket várunk");
		System.out.println(pipe2.GetLeaking()? "Igaz" : "Hamis");
		mechanic.SetCurrentPosition(pipe2);
		pipe2.AcceptPlayer(mechanic);
		mechanic.Damage();
		System.out.println("Igaz értéket várunk");
		System.out.println(pipe2.GetLeaking()? "Igaz" : "Hamis");
		saboteur.SetCurrentPosition(pump);
		pump.AcceptPlayer(saboteur);
		saboteur.Damage();
	}
}
