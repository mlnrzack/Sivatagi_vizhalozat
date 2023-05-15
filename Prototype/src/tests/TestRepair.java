package tests;

public class TestRepair extends TestBase
{
	public void testRepair()
	{
		mechanic.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(mechanic);
		
		System.out.println("Hamis értéket várunk");
		System.out.println(pipe1.GetLeaking() ? "Igaz" : "Hamis");

		mechanic.Damage();
		System.out.println("Igaz értéket várunk");
		System.out.println(pipe1.GetLeaking() ? "Igaz" : "Hamis");

		System.out.println("Hamis értéket várunk");
		System.out.println(pipe2.GetLeaking() ? "Igaz" : "Hamis");

		mechanic.Repair();
		System.out.println("Hamis értéket várunk");
		System.out.println(pipe1.GetLeaking() ? "Igaz" : "Hamis");
		
		mechanic.SetCurrentPosition(pipe2);
		pipe2.AcceptPlayer(mechanic);
		mechanic.Repair();
		System.out.println("Hamis értéket várunk");
		System.out.println(pipe2.GetLeaking() ? "Igaz" : "Hamis");
	}
}
