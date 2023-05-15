package tests;

import game.*;
public class TestSticky extends TestBase
{
	public void testSticky()
	{
		saboteur.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(saboteur);
		
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe1.GetSticky() == 0 ? "Egyformák" : "Nem egyformák");
		
		saboteur.SetStickyPipe();
		System.out.println("Nem egyforma értéket várunk");
		System.out.println(pipe1.GetSticky() == 0 ? "Egyformák" : "Nem egyformák");

		saboteur.SetStickyPipe();
		System.out.println("Nem egyforma értéket várunk");
		System.out.println(pipe1.GetSticky() == 0 ? "Egyformák" : "Nem egyformák");
		
		saboteur.SetCurrentPosition(pipe2);
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe2.GetSticky() == 0 ? "Egyformák" : "Nem egyformák");
		
		saboteur.SetStickyPipe();
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe2.GetSticky() == Constants.LeakageTimerBound - 1 ? "Egyformák" : "Nem egyformák");
	}
	
	public void TestStickyFunction()
	{
		//TODO
	}
}
