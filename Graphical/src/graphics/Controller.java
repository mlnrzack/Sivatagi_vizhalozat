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
	static boolean dropNewPump = false;
	static boolean dropNewPipe = false;
	static boolean disconnectedPipeEnd = false;
	
	public Controller(GameFrame gf)
	{
		this.gf = gf;
		buttons = gf.getActionButtons();
		//InitController();
		AttachActionToButtons();
	}
	
	/**
	 * @return
	 */
	public static boolean GetdropNewPump()
	{
		return dropNewPump;
	}
	
	/**
	 */
	public static void EndGame()
	{
		String text = "";
		if(GameManager.GetSaboteurPoints() < GameManager.GetMechanincsPoints())
		{
			text = text.concat("Szerelők nyertek!");
			text = text.concat("A nyertes csapat tagjai:");
			for(int i = 0; i < GameManager.GetMechanics().size(); i++)
				text = text.concat(GameManager.GetMechanics().get(i).GetName() + " ");
		}
		else if(GameManager.GetSaboteurPoints() > GameManager.GetMechanincsPoints())
		{
			text = text.concat("Szabotőrök nyertek!\n");
			text = text.concat("A nyertes csapat tagjai:\n");
			for(int i = 0; i < GameManager.GetSaboteurs().size(); i++)
				text = text.concat(GameManager.GetSaboteurs().get(i).GetName() + " ");
		}
		else
			text = text.concat("Döntetlen!\nGratulálunk mindkét csapatnak!");
		
		JOptionPane.showMessageDialog(null, text);
		new MenuFrame();
		gf.setVisible(false);
		gf.dispose();
	}

	/**A szerelő játékos karakter lépéseinek menüje.
	 */
	public static void MechanicActions(String input)
	{		
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
			if(GameManager.GetCurrentMechanic().Repair())
				JOptionPane.showMessageDialog(null, "Sikeresen megjavítottad az elemet!");
			else
				JOptionPane.showMessageDialog(null, "Az elemet nem kellett megjavítani!");
			break;

		case "pickfreepipe":
			if(GameManager.GetCurrentMechanic().PickUpFreePipeEnd()) {
				dropNewPipe = true;
				JOptionPane.showMessageDialog(null, "Sikeresen felvettél egy csővéget!");
				gf.GetMap_G().UpdateMapDetails();
				dropNewPipe = false;
			}
			else
				JOptionPane.showMessageDialog(null, "Ez a csővég nem felvehető!");
			break;

		case "picknewpump":
			if(GameManager.GetCurrentMechanic().PickUpPump())
				JOptionPane.showMessageDialog(null, "Sikeresen felvettél egy új pumpát!");

			else
				JOptionPane.showMessageDialog(null, "Nem tudtad felvenni a pumpát!");
			break;

		case "droppump":
			if(GameManager.GetCurrentMechanic().BuildPumpIntoPipe())
			{
				dropNewPump = true;
				JOptionPane.showMessageDialog(null, "Sikeresen beépítetted a pumpát egy csőbe!");
				gf.GetMap_G().UpdateMapDetails();
				dropNewPump = false;
			}

			else
				JOptionPane.showMessageDialog(null, "A pumpa nem került beszerelésre!");
			break;

		case "connectpipe":
			if(GameManager.GetCurrentMechanic().ConnectPipe())
				JOptionPane.showMessageDialog(null, "Sikeresen beépítetted a pumpát egy csőbe!");

			else
				JOptionPane.showMessageDialog(null, "A pumpa nem került beszerelésre!");
			break;

		case "pickneighbourtodisconnect":
			k = Integer.parseInt(userinput.split(" ")[1]);	
			if(GameManager.GetCurrentMechanic().DisconnectNeighbourPipe(k)) {
				disconnectedPipeEnd = true;
				JOptionPane.showMessageDialog(null, "Sikeresen lecsatlakoztattad a csövet a "+GameManager.GetCurrentMechanic().GetCurrentPosition().GetId()  +"-ról");
				gf.GetMap_G().UpdateMapDetails();
				disconnectedPipeEnd = false;
			}
			else if(GameManager.GetCurrentMechanic().GetCurrentPosition().GetNeighbours().get(k).GetPlayers().size() > 0){
				JOptionPane.showMessageDialog(null, "A csövön állnak, ezért nem lecsatlakoztatható");
			}
			else {
				JOptionPane.showMessageDialog(null, "A cső a pumpa be vagy ki menete, ezért nem leválasztható");
			}
			
			break;

		case "setpump":
			int neighbourIdxFrom = Integer.parseInt(userinput.split(" ")[1]);
			int neighbourIdxTo = Integer.parseInt(userinput.split(" ")[2]);
			GameManager.GetCurrentMechanic().TrySetPump(neighbourIdxFrom, neighbourIdxTo);
			break;

		case "leakpipe":
			if(GameManager.GetCurrentMechanic().Damage())
				JOptionPane.showMessageDialog(null, "Sikeresen megrongáltad a csövet!");
			else
				JOptionPane.showMessageDialog(null, "Cső megrongálása sikertelen volt!");
			break;

		case "stickypipe":
			if(GameManager.GetCurrentMechanic().SetStickyPipe())
				JOptionPane.showMessageDialog(null, "Sikeresen beragasztóztad az elemet!");

			else
				JOptionPane.showMessageDialog(null, "Az elem nem lett ragacsosabb!");
			break;

		case "pass":
			GameManager.GetCurrentMechanic().Pass();
			JOptionPane.showMessageDialog(null, GameManager.GetCurrentMechanic().GetName()+ " passzolt");
			break;

		case "exit":
			GameManager.GetCurrentMechanic().Exit();
			break;

		default:
			break;
		}
	}
	
	/**A szabotőr játékos karakter lépéseinek menüje.
	 */
	public static void SaboteurActions(String input)
	{
		String userinput = _nextMove;

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
			if(GameManager.GetCurrentSaboteur().Damage())
				JOptionPane.showMessageDialog(null, "Sikeresen megrongáltad a csövet!");
			else
				JOptionPane.showMessageDialog(null, "A cső már meg volt rongálva!");
			break;

		case "setpump":
			int neighbourIdxFrom = Integer.parseInt(userinput.split(" ")[1]);
			int neighbourIdxTo = Integer.parseInt(userinput.split(" ")[2]);
			GameManager.GetCurrentSaboteur().TrySetPump(neighbourIdxFrom, neighbourIdxTo);
			break;

		case "stickypipe":
			if(GameManager.GetCurrentSaboteur().SetStickyPipe())
				JOptionPane.showMessageDialog(null, "Sikeresen beragasztóztad az elemet!");

			else
				JOptionPane.showMessageDialog(null, "Az elem nem lett ragacsosabb!");
			break;
			
		case "slipperypipe":
			if(GameManager.GetCurrentSaboteur().SetSlipperyPipe())
				JOptionPane.showMessageDialog(null, "Sikeresen csúszóssá tetted az elemet!");
			else
				JOptionPane.showMessageDialog(null, "Nem lett csúszósabb az elem!");
			break;
			
		case "pass":
			GameManager.GetCurrentSaboteur().Pass();
			JOptionPane.showMessageDialog(null, GameManager.GetCurrentSaboteur().GetName()+ " passzolt");
			break;
			
		case "exit":
			GameManager.GetCurrentSaboteur().Exit();
			break;
			
		default:
			break;
		}
	}
	
	public static String GetNextMove()
	{
		return _nextMove;
	}

	public static void SetNextMove(String nextMove)
	{		
		_nextMove = nextMove;
				
		
		if (GameManager.GetCurrentMechanic() == null)
		{
			Controller.SaboteurActions(_nextMove);
		}
		else
		{
			Controller.MechanicActions(_nextMove);
		}
		
		int a = GameManager.GetPlayerAction();
		if (a == Constants.ActionInRoundPerUser)
		{
			if (GameManager.GetCurrentMechanic() != null) 
			{
				int currentMechanicIndex = GameManager.GetMechanics().indexOf(GameManager.GetCurrentMechanic());
				if (GameManager.GetMechanics().size() > (currentMechanicIndex + 1)) 
				{
					GameManager.SetCurrentMechanic(GameManager.GetMechanics().get(currentMechanicIndex + 1));
					gf.GetMap_G().SetCurrentMechanic(gf.GetMap_G().GetCurrentMechanicByModell(GameManager.GetCurrentMechanic()));
				}					
				else {
					GameManager.SetCurrentMechanic(null);
					GameManager.SetCurrentSaboteur(GameManager.GetSaboteurs().get(0));
					
					gf.GetMap_G().SetCurrentMechanic(null);
					gf.GetMap_G().SetCurrentSaboteur(gf.GetMap_G().GetCurrentSaboteurByModell(GameManager.GetCurrentSaboteur()));
				}					
			}
			else 
			{
				int currentSaboteurIndex = GameManager.GetSaboteurs().indexOf(GameManager.GetCurrentSaboteur());
				if (GameManager.GetSaboteurs().size() > (currentSaboteurIndex + 1)) 
				{
					GameManager.SetCurrentSaboteur(GameManager.GetSaboteurs().get(currentSaboteurIndex + 1));
					gf.GetMap_G().SetCurrentSaboteur(gf.GetMap_G().GetCurrentSaboteurByModell(GameManager.GetCurrentSaboteur()));
				}					
				else
				{
					GameManager.SetCurrentSaboteur(null);
					GameManager.SetCurrentMechanic(GameManager.GetMechanics().get(0));
					
					gf.GetMap_G().SetCurrentSaboteur(null);
					gf.GetMap_G().SetCurrentMechanic(gf.GetMap_G().GetCurrentMechanicByModell(GameManager.GetCurrentMechanic()));
					GameManager.SetRound(GameManager.GetRound() + 1);
					
					if(GameManager.GetRound() >= Constants.RoundNumber)
						EndGame();
				}
			}
			
			GameManager.SetPlayerAction(0);
		}

		gf.UpdateHud();
	}
	
	public static void AttachActionToButtons()
	{
		//Az akciógombokhoz hozzá rendelünk a GameManagerből megfelelő akciókat
		for(JButton butt: gf.GetActionButtons())
		{
			String action = butt.getText();

			Mechanic m = GameManager.GetCurrentMechanic();
			Saboteur s = GameManager.GetCurrentSaboteur();
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
						SetNextMove("repair");
						gf.UpdateHud();
						gf.ResetActionButtons();

						break;

					case "leakpipe":
						SetNextMove("leakpipe");
						gf.ResetActionButtons();
						gf.UpdateHud();
						break;

					case "pickfreepipe":
						SetNextMove("pickfreepipe");

						gf.ResetActionButtons();
						gf.UpdateHud();
						break;

					case"picknewpump":
						SetNextMove("picknewpump");

						gf.ResetActionButtons();
						gf.UpdateHud();
						break;

					case "droppump":
						SetNextMove("droppump");

						gf.ResetActionButtons();
						gf.UpdateHud();
						break;

					case "connectpipe":
						SetNextMove("connectpipe");

						gf.ResetActionButtons();
						gf.UpdateHud();
						break;

					case "pickneighbour X":
						//TODO
						ArrayList<String>avaliableFields = new ArrayList<String>();

						String[] indexString = null;
						if(m != null)
						{
							indexString = new String[GameManager.GetCurrentMechanic().GetCurrentPosition().GetNeighbours().size()];
							int i = 0;
							avaliableFields.clear();
							for(IElement element : GameManager.GetCurrentMechanic().GetCurrentPosition().GetNeighbours())
							{
								avaliableFields.add(element.GetId());
								indexString[i] = i +": "+ element.GetId()+ "\n";
								i++;
							}
							i = 0;
						}


						/*
						 * Ha pumpán áll, akkor úgyan úgy mint ami ahogy Set X Y X van, 

									Kapja meg milyen szomszédai vannak. 

									Abból válasszon 1 csövet. 

									Ha olyat választott amin , 

									Nem áll senki és nincs benne víz. a csőben, 

									Akkor. 

									Vegye vegye fel az adott csövet az inventoryaba, ahol, 

									A neighbor [0] legyem az ami eddig is volt, 

									A neighbor [1] legyen null.*/


						frame = new JFrame();
						InputDialog pumpIODialog = new InputDialog(1, indexString, "Add meg a leválasztani kívánt elem indexét!");
						String[] asd = pumpIODialog.createAndShowGui();

						int index = Integer.parseInt(asd[0]);
						if(index < avaliableFields.size() && index >= 0)
						{
							if(m != null)
								SetNextMove("pickneighbourtodisconnect "+ asd[0]);
						}
						else {
							frame = new JFrame();
							JOptionPane.showMessageDialog(frame, "Hibás indexelés!");
						}

						gf.ResetActionButtons();
						gf.UpdateHud();
						break;

					case "setpump X Y X":
						//TODO
						avaliableFields = new ArrayList<String>();


						if(m != null)
						{
							String indicesString[] = new String[m.GetCurrentPosition().GetNeighbours().size()];
							int i = 0;
							avaliableFields.clear();

							if(m.GetCurrentPosition().GetId().contains("pump"))
							{
								for(IElement element : m.GetCurrentPosition().GetNeighbours())
								{
									avaliableFields.add(element.GetId());
									indicesString[i] = (i +": "+ element.GetId());
									i++;
								}
								i = 0;
							}

							else
							{
								frame = new JFrame();
								JOptionPane.showMessageDialog(frame, "A "+  m.GetName()  + " játékos nem áll pumpán, ezért nem tudja végrehajtani a műveletet!");
								break;
							}

							InputDialog pumpMIODialog = new InputDialog(2, indicesString, "A pumpa ki és bemenetének beállítása");
							String[] masd = pumpMIODialog.createAndShowGui();
							if(!masd[0].equals("") && !masd[1].equals("")) {
								int PumpIn = Integer.parseInt(masd[0]);
								int PumpOut = Integer.parseInt(masd[1]);

								if((PumpIn <= avaliableFields.size() && PumpIn >= 0)
										&& (PumpOut <= avaliableFields.size() && PumpOut >= 0))
								{
									SetNextMove("setpump " + masd[0]+ " "+ masd[1]);
									//m.TrySetPump(PumpIn, PumpOut);
								}
								else {
									frame = new JFrame();
									JOptionPane.showMessageDialog(frame, "Hibás indexelés");
								}
								gf.ResetActionButtons();
								gf.UpdateHud();
							}
							else {
								frame = new JFrame();
								JOptionPane.showMessageDialog(frame, "Nem sikerült átállítani a pumpát");
							}
						}

						else if(s != null)
						{
							String indicesString[] = new String[s.GetCurrentPosition().GetNeighbours().size()];
							int i = 0;
							avaliableFields.clear();
							if(s.GetCurrentPosition().GetId().contains("pump"))
							{
								for(IElement element : s.GetCurrentPosition().GetNeighbours())
								{
									avaliableFields.add(element.GetId());
									indicesString[i] = i +": "+ element.GetId()+ "\n";
									i++;
								}
								i = 0;
							}
							else
							{
								frame = new JFrame();
								JOptionPane.showMessageDialog(frame, "A "+ s.GetName() + " játékos nem áll pumpán, ezért nem tudja végrehajtani a műveletet!");
								break;
							}

							InputDialog pumpSIODialog = new InputDialog(2, indicesString, "A pumpa ki és bemenetének beállítása");
							String[] sasd = pumpSIODialog.createAndShowGui();

							if(!sasd[0].equals("") && !sasd[1].equals("")) {
								int PumpIn = Integer.parseInt(sasd[0]);
								int PumpOut = Integer.parseInt(sasd[1]);

								if((PumpIn <= avaliableFields.size() && PumpIn >= 0)
										&& (PumpOut <= avaliableFields.size() && PumpOut >= 0))
								{

									SetNextMove("setpump " + sasd[0]+ " "+ sasd[1]);
									//s.TrySetPump(PumpIn, PumpOut);							
								}
								else {
									frame = new JFrame();
									JOptionPane.showMessageDialog(frame, "Hibás indexelés");
								}

								gf.ResetActionButtons();
								gf.UpdateHud();
							}
							else {
								frame = new JFrame();
								JOptionPane.showMessageDialog(frame, "Nem sikerült átállítani a pumpát");
							}
						}


						break;

					case "stickypipe":
						SetNextMove("stickypipe");

						gf.ResetActionButtons();
						gf.UpdateHud();
						break;

					case "slipperypipe":
						SetNextMove("slipperypipe");

						gf.ResetActionButtons();
						gf.UpdateHud();
						break;

					case "pass":
						SetNextMove("pass");

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
	
	/**
	 * @return
	 */
	public static boolean GetdropNewPipe()
	{
		// TODO Auto-generated method stub
		return dropNewPipe;
	}

	/**
	 * @return
	 */
	public static boolean GetDisconnectedPipeEnd()
	{
		// TODO Auto-generated method stub
		return disconnectedPipeEnd;
	}
}
