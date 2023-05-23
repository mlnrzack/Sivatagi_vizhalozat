package tests;

import game.Constants;
import game.GameManager;

public class TestWin extends TestBase
{
	public static void WinInit()
	{
		System.out.println("\nWin Test előtti Init");

		GameManager.SetMechanicsPoints(0);
		GameManager.SetSaboteursPoints(0);
		
		spring.SetWaterInside(0);
		pipe1.SetWaterInside(0);
		pump.SetWaterInside(0);
		pipe2.SetWaterInside(0);
		cistern.SetWaterInside(0);
		
		pipe2.RemovePlayer(saboteur);
		mechanic.SetCurrentPosition(pump);
		pump.AcceptPlayer(mechanic);
		saboteur.SetCurrentPosition(pump);
		pump.AcceptPlayer(saboteur);
		
		System.out.println("Igaz értéket várunk");
		System.out.println(pump.TrySetInputOutput(0, 1) ? "Igaz" : "Hamis");
	}

	public static void TestMechanicsWin()
	{
		WinInit();
		System.out.println("\nMechanics Win Test");

		saboteur.Move(1);
		
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe2.GetId().equals(saboteur.GetCurrentPosition().GetId()) ? "Egyformák" : "Nem egyformák");
		
		for(int i = 0; i < Constants.RoundNumber - 2; i++)
		{
			spring.FillNeighourPipes();
			pipe1.Step();
			pump.Step();
			pipe2.Step();
			cistern.Step();
		}
		
		saboteur.Damage();
		
		for(int i = 0; i < 2; i++)
		{
			spring.FillNeighourPipes();
			pipe1.Step();
			pump.Step();
			pipe2.Step();
			cistern.Step();
		}
		System.out.println(GameManager.GetMechanincsPoints() + ":" + GameManager.GetSaboteurPoints());
		System.out.println("Nyert a szerelő csapat?");
		System.out.println("Igaz értéket várunk");
		System.out.println(GameManager.GetSaboteurPoints() < GameManager.GetMechanincsPoints() ? "Igaz" : "Hamis");
	}
	
	public static void TestSaboteursWin()
	{
		WinInit();
		System.out.println("\nSaboteur Win Test");

		saboteur.Move(1);
		
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe2.GetId().equals(saboteur.GetCurrentPosition().GetId()) ? "Egyformák" : "Nem egyformák");
		
		for(int i = 0; i < Constants.RoundNumber / 2; i++)
		{
			spring.FillNeighourPipes();
			pipe1.Step();
			pump.Step();
			pipe2.Step();
			cistern.Step();
		}
		
		saboteur.Damage();
		
		for(int i = Constants.RoundNumber / 2; i < Constants.RoundNumber; i++)
		{
			spring.FillNeighourPipes();
			pipe1.Step();
			pump.Step();
			pipe2.Step();
			cistern.Step();
		}
		
		System.out.println(GameManager.GetMechanincsPoints() + ":" + GameManager.GetSaboteurPoints());
		System.out.println("Nyert a szabotőr csapat?");
		System.out.println("Igaz értéket várunk");
		System.out.println(GameManager.GetSaboteurPoints() > GameManager.GetMechanincsPoints() ? "Igaz" : "Hamis");
	}
}
