package graphics;

import game.Constants;
import game.GameManager;
import game.interfaces.IElement;
import game.players.Mechanic;
import game.players.Saboteur;
import graphics.elements.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Controller 
{
	
	protected static GameFrame gf;
	protected ArrayList<JButton> buttons;
	private static String _nextMove = "";
	private static String _currentMove = "";
	private static boolean mechanicStep = false;
	private static boolean saboteurStep = false;
	
	public Controller(GameFrame gf)
	{
		this.gf = gf;
		buttons = gf.getActionButtons();
		//InitController();
		AttachActionToButtons();
	}
	/*
	private void InitController() 
	{
		buttons.get(0).addActionListener(new MoveButtonListener());
		buttons.get(1).addActionListener(new RepairButtonListener());
		buttons.get(2).addActionListener(new PickfreepipeButtonListener());
		buttons.get(3).addActionListener(new PicknewpumpButtonListener());
		buttons.get(4).addActionListener(new DroppumpButtonListener());
		buttons.get(5).addActionListener(new ConnectpipeButtonListener());
		buttons.get(6).addActionListener(new PickneighbourButtonListener());
		buttons.get(7).addActionListener(new SetpumpButtonListener());
		buttons.get(8).addActionListener(new LeakpipeButtonListener());
		buttons.get(9).addActionListener(new StickypipeButtonListener());
		buttons.get(10).addActionListener(new SlipperypipeButtonListener());
		buttons.get(11).addActionListener(new PassButtonListener());
	}
	
	private class MoveButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("move X");
		}
	}
	
	private class RepairButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("repair");
		}
	}
	
	private class PickfreepipeButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("pickfreepipe");
		}
	}
	
	private class PicknewpumpButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("picknewpump");
		}
	}
	
	private class DroppumpButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("droppump");
		}
	}
	
	private class ConnectpipeButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("connectpipe");
		}
	}
	
	private class PickneighbourButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("pickneighbour");
		}
	}
	
	private class SetpumpButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("setpump");
		}
	}
	
	private class LeakpipeButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("leakpipe");
		}
	}
	
	private class StickypipeButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("stickypipe");
		}
	}

	private class SlipperypipeButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("slipperypipe");
		}
	}
	
	private class PassButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("pass");
		}
	}
	*/
	
	/**
	 */
	public static void StartGame()
	{
		while (GameManager.GetRound() < Constants.RoundNumber)
		{
			//Történt-e akció gomb nyomás
			
			//MechanicActions();
			//lépett e mindkét játékos
			
			//SaboteurActions();
			//lépett e mindkét játékos
			
			
			//lépett mindenki, akkor
			GameManager.SetRound(GameManager.GetRound() + 1);
		}

		if(GameManager.GetRound() == Constants.RoundNumber)
		{

			System.out.print("Gratulálunk a ");
			if(GameManager.GetSaboteurPoints() < GameManager.GetMechanincsPoints())
			{
				System.out.println("Szerelők nyertek!");
				System.out.println("A nyertes csapat tagjai:");
				for(int i = 0; i < GameManager.GetMechanics().size(); i++)
					System.out.println(GameManager.GetMechanics().get(i).GetName());
			}
			else if(GameManager.GetSaboteurPoints() > GameManager.GetMechanincsPoints())
			{
				System.out.println("Szabotőrök nyertek!");
				System.out.println("A nyertes csapat tagjai:");
				for(int i = 0; i < GameManager.GetSaboteurs().size(); i++)
					System.out.println(GameManager.GetSaboteurs().get(i).GetName());
			}
			else
				System.out.println("Döntetlen!\nGratulálunk mindkét csapatnak!");
		}
	}

	/**A szerelő játékos karakter lépéseinek menüje.
	 */
	public static void MechanicActions(String input)
	{
		//GameManager.SetPlayerAction(0);
			System.out.println(GameManager.GetCurrentMechanic().GetName());
			gf.GetMap_G().SetCurrentMechanic(gf.GetMap_G().GetCurrentMechanicByModell(GameManager.GetMechanics().get(i)));
			System.out.println(gf.GetMap_G().GetCurrentMechanicByModell(GameManager.GetMechanics().get(i)));
			gf.GetMap_G().SetCurrentSaboteur(null);
		
		System.out.println("A jelenlegi szabotőr: " + GameManager.GetCurrentMechanic().GetName());
		
		try
		{
			gf.GetMap_G().SetCurrentMechanic(gf.GetMap_G().GetCurrentMechanicByModell(GameManager.GetCurrentMechanic()));
			gf.GetMap_G().SetCurrentSaboteur(null);
			
			//Scanner reader = new Scanner(System.in);
			String userinput = _nextMove;

			switch (userinput.split(" ")[0])
			{
				case "move":
					String neighbourIdx = (userinput.split(" ")[1]);
					System.out.println("5.5 GameM neighbourIdx Mechanic "+neighbourIdx);
					System.out.println(GameManager.GetCurrentMechanic().GetName()+": "+GameManager.GetCurrentMechanic().GetCurrentPosition().GetId());
					
					int k = GameManager.GetCurrentMechanic().TryFindNeighbourId(neighbourIdx);
					
					if (k < GameManager.GetCurrentMechanic().GetCurrentPosition().GetNeighbours().size())
					{
						System.out.println("6. move k mechanic: "+k);
						GameManager.GetCurrentMechanic().Move(k);
						System.out.println("6.5 move done by " + GameManager.GetCurrentMechanic().GetName()+" to " + GameManager.GetCurrentMechanic().GetCurrentPosition().GetId());
						gf.GetMap_G().UpdateMapDetails();
						System.out.println("7. moved "+ GameManager.GetCurrentMechanic().GetCurrentPosition().GetId());
						_nextMove="";
						k = Constants.MaxNeighboursOfActiveElements + 1;
						gf.UpdateHud();
					}
					break;
					
				case "repair":
					GameManager.GetCurrentMechanic().Repair();
					break;

				case "pickfreepipe":
					GameManager.GetCurrentMechanic().PickUpFreePipeEnd();
					break;

				case "picknewpump":
					GameManager.GetCurrentMechanic().PickUpPump();
					break;

				case "droppump":
					GameManager.GetCurrentMechanic().BuildPumpIntoPipe();
					break;

				case "connectpipe":
					GameManager.GetCurrentMechanic().ConnectPipe();
					break;

				case "pickneighbour":
					neighbourIdx = (userinput.split(" ")[1]);
					k = 0;
					for (IElement ei : GameManager.GetCurrentMechanic().GetCurrentPosition().GetNeighbours())
					{
						k++;
						if (ei.GetId().equals(neighbourIdx))
						{
							GameManager.GetCurrentMechanic().DisconnectNeighbourPipe(k);
							break;
						}
					}
					break;

				case "setpump":
					int neighbourIdxFrom = Integer.parseInt(userinput.split(" ")[1]);
					int neighbourIdxTo = Integer.parseInt(userinput.split(" ")[2]);
					GameManager.GetCurrentMechanic().TrySetPump(neighbourIdxFrom, neighbourIdxTo);
					break;

				case "leakpipe":
					GameManager.GetCurrentMechanic().Damage();
					break;

				case "stickypipe":
					GameManager.GetCurrentMechanic().SetStickyPipe();
					break;

				case "pass":
					GameManager.GetCurrentMechanic().Pass();
					break;

				case "exit":
					GameManager.GetCurrentMechanic().Exit();
					break;

				default:
					break;
			}
		}
		catch(Exception e)
		{
			System.out.println("Hibás menü bemenet!\n");
		}
	}
	
	/**A szabotőr játékos karakter lépéseinek menüje.
	 */
	public static void SaboteurActions(String input)
	{
		System.out.println("A jelenlegi szabotőr: "+ GameManager.GetCurrentSaboteur().GetName());
			
		try
		{
			//Scanner reader = new Scanner(System.in);
			String userinput = _nextMove;//reader.nextLine();
			System.out.println("5. GameManager userinput = " + userinput);

			switch (userinput.split(" ")[0])
			{
				case "move":
					String neighbourIdx = (userinput.split(" ")[1]);
					System.out.println("5.5 GameM neighbourIdx Mechanic "+neighbourIdx);
					
					int k = GameManager.GetCurrentSaboteur().TryFindNeighbourId(neighbourIdx);
					
					if (k < GameManager.GetCurrentSaboteur().GetCurrentPosition().GetNeighbours().size())
					{
						System.out.println("6. move k mechanic: "+k);
						GameManager.GetCurrentSaboteur().Move(k);
						System.out.println("6.5 move done by " + GameManager.GetCurrentSaboteur().GetName()+" to " + GameManager.GetCurrentSaboteur().GetCurrentPosition().GetId());
						gf.GetMap_G().UpdateMapDetails();
						System.out.println("7. moved "+ GameManager.GetCurrentSaboteur().GetCurrentPosition().GetId());
						_nextMove="";
					}
					break;
					
				case "leakpipe":
					GameManager.GetCurrentSaboteur().Damage();
					break;

				case "setpump":
					int neighbourIdxFrom = Integer.parseInt(userinput.split(" ")[1]);
					int neighbourIdxTo = Integer.parseInt(userinput.split(" ")[2]);
					GameManager.GetCurrentSaboteur().TrySetPump(neighbourIdxFrom, neighbourIdxTo);
					break;

				case "stickypipe":
					GameManager.GetCurrentSaboteur().SetStickyPipe();
					break;
				case "slipperypipe":
					GameManager.GetCurrentSaboteur().SetSlipperyPipe();
					break;
				case "pass":
					GameManager.GetCurrentSaboteur().Pass();
					break;
				case "exit":
					GameManager.GetCurrentSaboteur().Exit();
					break;
				default:
					break;
			}
		}
		catch(Exception e)
		{
			System.out.println("Hibás menü bemenet!\n" + e);
		}
	}
	
	public static String GetNextMove()
	{
		return _nextMove;
	}

	public static void SetNextMove(String nextMove)
	{		
		_nextMove = nextMove;
				
				
		System.out.println("4. Controller SetNextMove " + nextMove);
		
		if (GameManager.GetCurrentMechanic() == null)
		{
			System.out.println("4.5 Szabotőr Action");
			Controller.SaboteurActions(_nextMove);
		}
		else
		{
			System.out.println("4.5 Mechanic Action");
			Controller.MechanicActions(_nextMove);
		}
		
		int a = GameManager.GetPlayerAction();
		if (a == Constants.ActionInRoundPerUser) {
			if (GameManager.GetCurrentMechanic() != null) {
				int currentMechanicIndex = GameManager.GetMechanics().indexOf(GameManager.GetCurrentMechanic());
				if (GameManager.GetMechanics().size() > (currentMechanicIndex + 1)) {
					GameManager.SetCurrentMechanic(GameManager.GetMechanics().get(currentMechanicIndex + 1));
					gf.GetMap_G().SetCurrentMechanic(gf.GetMap_G().GetCurrentMechanicByModell(GameManager.GetCurrentMechanic()));
				}					
				else {
					GameManager.SetCurrentMechanic(null);
					GameManager.SetCurrentSaboteur(GameManager.GetSaboteurs().get(0));
					
					gf.GetMap_G().SetCurrentMechanic(null);
					gf.GetMap_G().SetCurrentSaboteur(gf.GetMap_G().GetCurrentSaboteurByModell(GameManager.GetCurrentSaboteur()));
				}					
			} else {
				int currentSaboteurIndex = GameManager.GetSaboteurs().indexOf(GameManager.GetCurrentSaboteur());
				if (GameManager.GetSaboteurs().size() > (currentSaboteurIndex + 1)) {
					GameManager.SetCurrentSaboteur(GameManager.GetSaboteurs().get(currentSaboteurIndex + 1));
					gf.GetMap_G().SetCurrentSaboteur(gf.GetMap_G().GetCurrentSaboteurByModell(GameManager.GetCurrentSaboteur()));
				}					
				else {
					GameManager.SetCurrentSaboteur(null);
					GameManager.SetCurrentMechanic(GameManager.GetMechanics().get(0));
					
					gf.GetMap_G().SetCurrentSaboteur(null);
					gf.GetMap_G().SetCurrentMechanic(gf.GetMap_G().GetCurrentMechanicByModell(GameManager.GetCurrentMechanic()));
				}
			}
			
			GameManager.SetPlayerAction(0);
		}
		
		gf.UpdateHud();
	}
	
	public static void AttachActionToButtons()
	{
		//Az akciógombokhoz hozzá rendelünk a GameManagerből megfelelő akciókat
		
		for(int i = 0; i < gf.GetMap_G().GetMechanicsView().size(); i++)
		{
			mechanicStep = true;
		}
			
		for(JButton butt: gf.GetActionButtons())
		{
			String action = butt.getText();

			//GameManager.SetCurrentMechanic(GameManager.GetMechanics().get(0));
			//GameManager.SetCurrentSaboteur(GameManager.GetSaboteurs().get(0));


			//======================================================
			/*
			 * Paraszt debug sarok
			 *  System.out.println("MEchanics count: "+ GameManager.GetMechanics().size());
			 */
			//======================================================
			Mechanic m = GameManager.GetCurrentMechanic();
			//System.out.println(m.GetCurrentPosition().GetId());
			Saboteur s = GameManager.GetCurrentSaboteur();
			//System.out.println("Current player:" + (m != null? m.GetName(): s.GetName()));
			butt.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					//boolean buttonPressed = false;
					JFrame frame;
					String output;
					switch(action)
					{
						case "move X":
							/*
							 * mert akkor a gomb nyomására kellene gondolom egy event listener,
							 * ami az egér kattintást figyeli, ott megkap egy koordinátát,
							 * végig kell menni a mapview elemein, és mindegyiknek ellenőrizni,
							 * hogy az ő területére kattintottak-e, majd ha igen,
							 * akkor térjen vissza mondjuk az ID-jával a dolog,
							 * hogy utána meg összevesse a játékos mezejének a szomszédjaival
							 * */

							gf.GetMap_G().isPlayerMoving = false;

							int answer;
							answer = JOptionPane.showConfirmDialog(null, "Klikkelj a kiválasztott mezőre, ahova lépni szeretnél.","", JOptionPane.OK_CANCEL_OPTION);

							if(answer == JOptionPane.OK_OPTION)
							{
								gf.GetMap_G().isPlayerMoving = true;
							}

							else
								gf.GetMap_G().isPlayerMoving = false;

							gf.ResetActionButtons();
							gf.UpdateHud();
							
							break;
							

						case "repair":
							frame = new JFrame();
							if(m != null)
							{
								if(m.Repair() == true)
									JOptionPane.showMessageDialog(frame, "Sikeresen megjavítottad a csövet!");

								else
									JOptionPane.showMessageDialog(frame, "A csövet nem kellett megjavítani!");
							}
							else
								JOptionPane.showMessageDialog(frame, "Nem a szerelő köre van most!");

							gf.UpdateHud();
							gf.ResetActionButtons();
							break;

						case "leakpipe":
							frame = new JFrame();
							if(m != null)
							{
								if(m.Damage() == true)
									JOptionPane.showMessageDialog(frame, "Sikeresen megrongáltad a csövet!");

								else
									JOptionPane.showMessageDialog(frame, "A cső már meg volt rongálva!");
							}
							else if(s != null)
							{
								if(s.Damage() == true)
									JOptionPane.showMessageDialog(frame, "Sikeresen megrongáltad a csövet!");

								else
									JOptionPane.showMessageDialog(frame, "A cső már meg volt rongálva!");
							}

							gf.ResetActionButtons();
							gf.UpdateHud();
							break;

						case "pickfreepipe":
							frame = new JFrame();
							if(m != null)
							{
								if(m.PickUpFreePipeEnd() == true)
									JOptionPane.showMessageDialog(frame, "Sikeresen felvettél egy csővéget!");

								else
									JOptionPane.showMessageDialog(frame, "Ez a csővég nem felvehető!");
							}
							else
							{
								frame = new JFrame();
								JOptionPane.showMessageDialog(frame, "Nem a szerelő köre van most!");
							}

							gf.ResetActionButtons();
							gf.UpdateHud();
							break;

						case"picknewpump":
							frame = new JFrame();
							if(m != null)
							{
								if(m.PickUpPump() == true)
									JOptionPane.showMessageDialog(frame, "Sikeresen felvettél egy új pumpát!");

								else
									JOptionPane.showMessageDialog(frame, "Nem tudtad felvenni a pumpát!");
							}
							else
							{
								frame = new JFrame();
								JOptionPane.showMessageDialog(frame, "Nem a szerelő köre van most!");
							}

							gf.ResetActionButtons();
							gf.UpdateHud();
							break;

						case "droppump":
							frame = new JFrame();
							if(m!= null)
							{
								if(m.BuildPumpIntoPipe() == true)
									JOptionPane.showMessageDialog(frame, "Sikeresen beépítetted a pumpát egy csőbe!");

								else
									JOptionPane.showMessageDialog(frame, "A pumpa nem került beszerelésre!");
							}
							else
							{
								frame = new JFrame();
								JOptionPane.showMessageDialog(frame, "Nem a szerelő köre van most!");
							}

							gf.ResetActionButtons();
							gf.UpdateHud();
							break;

						case "connectpipe":
							frame = new JFrame();
							if(m != null)
							{
								if(m.ConnectPipe() == true)
									JOptionPane.showMessageDialog(frame, "Sikeresen beépítetted a pumpát egy csőbe!");

								else
									JOptionPane.showMessageDialog(frame, "A pumpa nem került beszerelésre!");
							}
							else
							{
								frame = new JFrame();
								JOptionPane.showMessageDialog(frame, "Nem a szerelő köre van most!");
							}

							gf.ResetActionButtons();
							gf.UpdateHud();
							break;

						case "pickneighbour X":
							ArrayList<String>avaliableFields = new ArrayList<String>();
							String indicesString = "";

							if(m != null)
							{
								int i = 0;
								avaliableFields.clear();
								for(IElement element : m.GetCurrentPosition().GetNeighbours())
								{
									avaliableFields.add(element.GetId());
									indicesString.concat(i +": "+ element.GetId()+ "\n");
									i++;
								}
							}

							frame = new JFrame();
							output = JOptionPane.showInputDialog(frame, "Add meg az indexét a kívánt mezőnek!", indicesString);
							int index = Integer.parseInt(output);
							if(index <= avaliableFields.size() && index >= 0)
							{
								if(m != null)
									m.Move(index);
							}

							gf.ResetActionButtons();
							gf.UpdateHud();
							break;

						case "setpump X Y X":
							avaliableFields = new ArrayList<String>();
							indicesString = "";

							if(m != null)
							{
								int i = 0;
								avaliableFields.clear();

								if(m.GetCurrentPosition().GetId().contains("pump"))
								{
									for(IElement element : m.GetCurrentPosition().GetNeighbours())
									{
										avaliableFields.add(element.GetId());
										indicesString.concat(i +": "+ element.GetId()+ "\n");
										i++;
									}
								}
								else
								{
									frame = new JFrame();
									JOptionPane.showMessageDialog(frame, "A "+  m.GetName()  + " játékos nem áll pumpán, ezért nem tudja végrehajtani a műveletet!");
									break;
								}
							}

							else if(s != null)
							{
								int i = 0;
								avaliableFields.clear();
								if(s.GetCurrentPosition().GetId().contains("pump"))
								{
									for(IElement element : m.GetCurrentPosition().GetNeighbours())
									{
										avaliableFields.add(element.GetId());
										indicesString.concat(i +": "+ element.GetId()+ "\n");
										i++;
									}
								}
								else
								{
									frame = new JFrame();
									JOptionPane.showMessageDialog(frame, "A "+ s.GetName() + " játékos nem áll pumpán, ezért nem tudja végrehajtani a műveletet!");
									break;
								}
							}

							InputDialog pumpIODialog = new InputDialog(2, indicesString, "A pumpa ki és bemenetének beállítása");
							String[] asd = pumpIODialog.createAndShowGui();

							int PumpIn = Integer.parseInt(asd[0]);
							int PumpOut = Integer.parseInt(asd[1]);

							if((PumpIn <= avaliableFields.size() && PumpIn >= 0)
									&& (PumpOut <= avaliableFields.size() && PumpOut >= 0))
							{
								if(m != null)
									m.TrySetPump(PumpIn, PumpOut);
							}
							else if (s!= null)
								s.TrySetPump(PumpIn, PumpOut);

							gf.ResetActionButtons();
							gf.UpdateHud();
							break;

						case "stickypipe":
							frame = new JFrame();
							if(m != null)
							{
								if(m.SetStickyPipe() == true)
									JOptionPane.showMessageDialog(frame, "Sikeresen beragasztóztad az elemet!");

								else
									JOptionPane.showMessageDialog(frame, "Az elem nem lett ragacsosabb!");
							}
							else if(s != null)
							{
								if(s.SetStickyPipe() == true)
									JOptionPane.showMessageDialog(frame, "Sikeresen beragasztóztad az elemet!");

								else
									JOptionPane.showMessageDialog(frame, "Az elem nem lett ragacsosabb!");
							}

							gf.ResetActionButtons();
							gf.UpdateHud();
							break;

						case "slipperypipe":
							frame = new JFrame();
							if(s != null)
							{
								if(s.SetSlipperyPipe() == true)
									JOptionPane.showMessageDialog(frame, "Sikeresen csúszóssá tetted az elemet!");

								else
									JOptionPane.showMessageDialog(frame, "Nem lett csúszósabb az elem!");
							}
							else
								JOptionPane.showMessageDialog(frame, "Nem a szabotőr köre van!");

							gf.ResetActionButtons();
							gf.UpdateHud();
							break;

						case "pass":
							frame = new JFrame();
							if(m != null)
							{
								m.Pass();
								JOptionPane.showMessageDialog(frame, GameManager.GetCurrentMechanic().GetName()+ " passzolt");
							}

							else if(s != null)
							{
								s.Pass();
								JOptionPane.showMessageDialog(frame, GameManager.GetCurrentSaboteur().GetName()+ " passzolt");
							}

							gf.ResetActionButtons();
							gf.UpdateHud();
							break;
					}
				}
			});
		}
	}
	
	public static void createString() 
	{
        _currentMove = new String();
        _currentMove = "move ";
        _currentMove = _currentMove.concat(gf.GetSelectedElement().GetElement().GetId());
        System.out.println("3. Controller currentMove createStringben " + _currentMove);
        Controller.SetNextMove(_currentMove);
    }
}
