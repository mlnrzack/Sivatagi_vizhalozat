package game;

public class TestWin extends TestBase
{
	public void InitTest()
	{
		GameManager.SetMechanicsPoints(0);
		GameManager.SetSaboteursPoints(0);
		spring.SetWaterInside(0);
		pipe1.SetWaterInside(0);
		pump.SetWaterInside(0);
		pipe2.SetWaterInside(0);
		cistern.SetWaterInside(0);
		mechanic.SetCurrentPosition(pump);
		saboteur.SetCurrentPosition(pump);
		
		System.out.println("Igaz értéket várunk");
		System.out.println(pump.TrySetInputOutput(0, 1) ? "Igaz" : "Hamis");
	}

	public void TestMechanicsWin()
	{
		saboteur.Move(1);
		
		System.out.println("Egyforma értéket várunk");
		System.out.println(pipe2.GetId().equals(saboteur.GetCurrentPosition().GetId()) ? "Egyformák" : "Nem egyformák");
		
		for(int i = 0; i < Constants.RoundNumber-2; i++)
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
		
		System.out.println("Nyert a szerelő csapat?");
		System.out.println("Igaz értéket várunk");
		System.out.println(GameManager.GetSaboteurPoints() < GameManager.GetMechanincsPoints() ? "Igaz" : "Hamis");
	}
	
	public void TestSaboteursWin()
	{
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
		
		System.out.println("Nyert a szabotőr csapat?");
		System.out.println("Igaz értéket várunk");
		System.out.println(GameManager.GetSaboteurPoints() > GameManager.GetMechanincsPoints() ? "Igaz" : "Hamis");
	}
}
