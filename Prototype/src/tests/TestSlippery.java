package tests;

import game.*;

public class TestSlippery extends TestBase
{
	public void TestSetSlippery()
	{
		saboteur.SetCurrentPosition(pipe1);
		pipe1.AcceptPlayer(saboteur);
		
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe1.GetSlippery() == 0 ? "Egyformák" : "Nem egyformák");

		saboteur.SetSlipperyPipe();
		System.out.println("Nem egyforma értéket várunk");
		System.out.println(pipe1.GetSlippery() == 0 ? "Egyformák" : "Nem egyformák");

		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe1.GetSlippery() == Constants.LeakageTimerBound - 1 ? "Egyformák" : "Nem egyformák");

		saboteur.SetSlipperyPipe();
		System.out.println("Nem egyforma értéket várunk");
		System.out.println(pipe1.GetSlippery() == 0 ? "Egyformák" : "Nem egyformák");
		
		saboteur.SetCurrentPosition(pipe2);
		pipe2.AcceptPlayer(saboteur);
		
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe2.GetSlippery() == 0 ? "Egyformák" : "Nem egyformák");

		saboteur.SetSlipperyPipe();
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe2.GetSlippery() == Constants.LeakageTimerBound - 1 ? "Egyformák" : "Nem egyformák");
	}
	
	public void TestSlipperyFunction()
	{
		//TODO
	}
}
