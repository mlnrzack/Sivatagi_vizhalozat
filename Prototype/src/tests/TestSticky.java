package tests;

import game.*;
public class TestSticky extends TestBase
{
	/**
	 * 8.2.4
	 * Egy játékos ragadóssá tesz egy csövet.
	 */
	public void TestSetSticky()
	{
		saboteur.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(saboteur);
		System.out.println("Szabotőr a pipe1-n\nEllenőrizzük, hogy a pipe1 az egy sima cső-e?");
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe1.GetSticky() == 0 ? "Egyformák" : "Nem egyformák");
		System.out.println("Szabotőr Sticky-vé tesz egy csövet.\nEllenőrizzük, hogy a pipe1 az egy sima cső-e");
		saboteur.SetStickyPipe();
		System.out.println("Nem egyforma értéket várunk");
		System.out.println(pipe1.GetSticky() == 0 ? "Egyformák" : "Nem egyformák");
		System.out.println("Ellenőrizzük, hogy a pipe1 az egy sima cső-e");
		saboteur.SetStickyPipe();
		System.out.println("Nem egyforma értéket várunk");
		System.out.println(pipe1.GetSticky() == 0 ? "Egyformák" : "Nem egyformák");
		System.out.println("Szabotőr pipe2-re mozgatása majd, ellenőrzése, hogy a pipe2 sima cső-e");
		saboteur.SetCurrentPosition(pipe2);
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe2.GetSticky() == 0 ? "Egyformák" : "Nem egyformák");
		System.out.println("Szabotőr Sticky-vé teszi a pipe2-t. Ellenőrizzük.");
		saboteur.SetStickyPipe();
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe2.GetSticky() == Constants.LeakageTimerBound - 1 ? "Egyformák" : "Nem egyformák");
	}
	
	public void TestStickyFunction()
	{
		//TODO
	}
}
