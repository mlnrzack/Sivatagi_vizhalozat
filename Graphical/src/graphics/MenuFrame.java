package graphics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import game.*;
import game.interfaces.*;
import game.players.*;
import graphics.elements.*;

public class MenuFrame extends JFrame 
{
	private Color color = Color.decode("#c9a77d");
	private JButton backButton;
	private JButton newGameButton;
	private JButton startGameButton;
	private JButton settingsButton;
	private JButton setButton;

	private JLabel westLabel;
	private JLabel northLabel;

	private static JPanel eastPanel;
	private static JPanel westPanel;
	private JPanel northPanel;
	private JPanel southPanel;

	private JTextField actionsPerUserSet;
	private JTextField gameRoundsSet;
	private JTextField leakageTimerSet;
	private JTextField maxNeighboursSet;
	private JTextField playerCapacitySet;
	private JTextField pipeCapacitySet;
	private JTextField pumpCapacitySet;
	private JTextField pumpErrorSet;

	private Font f = new Font(Font.DIALOG, Font.PLAIN, 28);
	private Font fi = new Font(Font.DIALOG, Font.ITALIC, 48);
	private Font fs = new Font(Font.DIALOG, Font.ITALIC, 19);
	
	private boolean firstTrigger = true;

	public MenuFrame() 
	{
		//a frame elnevezése
		super("Sivatagi vizhalozat");
		//icon betöltése
		File currentDirFile = new File(".");
		String helper = currentDirFile.getAbsolutePath();
		String midhelp = helper.substring(0, helper.length() - 2).concat("\\bin\\");
		ImageIcon icon = new ImageIcon(midhelp.concat("/icon.png"));
		this.setIconImage(icon.getImage());
		//további beállítások
		this.setBackground(Color.decode("#c29c84"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoadInterface();
	}

	public static JPanel GetEastPanel() 
	{
		return eastPanel;
	}

	public static void SetEastPanel(JPanel panel) 
	{
		eastPanel = panel;
	}

	public static JPanel GetWestPanel() 
	{
		return westPanel;
	}

	public static void SetWestPanel(JPanel panel)
	{
		westPanel = panel;
	}

	public void LoadInterface()
	{
		InitializeNorthPanel();
		InitializeEastPanel();
		InitializeWestPanel();
		InitializeSouthPanel();

		this.setLayout(new BorderLayout());
		this.add(northPanel, BorderLayout.NORTH);
		this.add(eastPanel, BorderLayout.EAST);
		this.add(westPanel, BorderLayout.WEST);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void InitializeNorthPanel() 
	{
		northLabel = new JLabel("Sivatagi vízhálózat");
		northLabel.setFont(fi);
		northLabel.setHorizontalAlignment(SwingConstants.CENTER);

		northPanel = new JPanel(new GridLayout(1, 1));
		northPanel.setBackground(color);
		northPanel.setPreferredSize(new Dimension(1000, 100));
		northPanel.add(northLabel);
	}

	public void InitializeEastPanel() 
	{
		newGameButton = new JButton("New Game");
		newGameButton.setFont(f);
		newGameButton.setBackground(color);
		newGameButton.setBorder(BorderFactory.createDashedBorder(color, 5, 5, 0, false));
		newGameButton.addActionListener
		(
			new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					NewGameTrigger();
				}
			}
		);

		settingsButton = new JButton("Settings");
		settingsButton.setFont(f);
		settingsButton.setBackground(color);
		settingsButton.setBorder(BorderFactory.createDashedBorder(color, 5, 5, 0, false));
		settingsButton.addActionListener
		(
			new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					SettingsTrigger();
				}
			}
		);
		
		eastPanel = new JPanel();
		eastPanel.add(newGameButton);
		eastPanel.add(settingsButton);
		eastPanel.setPreferredSize(new Dimension(250, 500));
		eastPanel.setBackground(color);
		eastPanel.revalidate();
		eastPanel.repaint();
	}

	public void InitializeWestPanel() 
	{
		try
		{
			File currentDirFile = new File(".");
			String helper = currentDirFile.getAbsolutePath();
			String midhelp = helper.substring(0, helper.length() - 2).concat("\\bin\\");

			ImageIcon logo = new ImageIcon(midhelp.concat("/logo.png"));

			westLabel = new JLabel();
			westLabel.setIcon(logo);

			westPanel = new JPanel();
			westPanel.add(westLabel);
			westPanel.setPreferredSize(new Dimension(750, 500));
			westPanel.setBackground(color);
		} 
		catch (Exception e) 
		{
			westLabel = new JLabel("Sivatagi vízhálózat");
			westLabel.setFont(fi);
			westLabel.setHorizontalAlignment(SwingConstants.LEFT);

			westPanel = new JPanel();
			westPanel.add(westLabel);
			westPanel.setPreferredSize(new Dimension(750, 500));
			westPanel.setBackground(color);
		}
	}

	public void InitializeSouthPanel()
	{
		backButton = new JButton("Back");
		backButton.setFont(f);
		backButton.setBackground(color);
		backButton.setBorder(BorderFactory.createDashedBorder(color, 2, 2, 0, false));
		backButton.addActionListener
		(
			new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					BackTrigger();
				}
			}
		);
		
		startGameButton = new JButton("Start Game");
		startGameButton.setFont(f);
		startGameButton.setBackground(color);
		startGameButton.setBorder(BorderFactory.createDashedBorder(color, 2, 2, 0, false));
		startGameButton.addActionListener
		(
			new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					StartGameTrigger();
				}
			}
		);
		
		setButton = new JButton("Set");
		setButton.setFont(f);
		setButton.setBackground(color);
		setButton.setBorder(BorderFactory.createDashedBorder(color, 2, 2, 0, false));
		setButton.addActionListener
		(
			new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					SetTrigger();
				}
			}
		);
		
		southPanel = new JPanel(new GridLayout(1, 2));
		southPanel.setBackground(color);
		southPanel.setPreferredSize(new Dimension(1000, 100));
		southPanel.add(backButton);
		southPanel.add(startGameButton);
	}

	public void NewGameTrigger() 
	{
		southPanel.removeAll();
		southPanel.add(backButton);
		southPanel.add(startGameButton);
		this.add(southPanel, BorderLayout.SOUTH);
		
		eastPanel.removeAll();
		eastPanel.setPreferredSize(new Dimension(500, 500));
		eastPanel.revalidate();
		eastPanel.repaint();

		westPanel.removeAll();
		westPanel.setPreferredSize(new Dimension(500, 500));
		westPanel.revalidate();
		westPanel.repaint();

		if(firstTrigger) 
		{
            Program.InitializeMap();
            firstTrigger = false;
        }
		
		ArrayList<IElement> map = GameManager.GetMap();
		
		String[] counts = {"2", "3", "4", "5"};
		
		westPanel.setBackground(color);
		JLabel mechanicLabel = new JLabel("Mechanics' team");
		mechanicLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 32));

		JLabel mechanicsCountQ = new JLabel("Hányan vannak a szerelők csapatában?");
		mechanicsCountQ.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));

		var mechanicsCountA = new JComboBox(counts);
		mechanicsCountA.setSelectedIndex(0);
		mechanicsCountA.setEditable(true);
		mechanicsCountA.setBackground(color);
		mechanicsCountA.setPreferredSize(new Dimension(50, 25));

		JButton mOkButton = new JButton("Ok");
		mOkButton.setBackground(color);
		mOkButton.setBorder(BorderFactory.createDashedBorder(color, 5, 5, 5, false));
		mOkButton.addActionListener
		(
				new ActionListener() 
				{
					int mCount = 0;
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						String[] names = new String[5];

						if(mCount != 0) 
						{
							westPanel.removeAll();
							westPanel.add(mechanicLabel);
							westPanel.add(mechanicsCountQ);
							westPanel.add(mechanicsCountA);
							westPanel.add(mOkButton);

							ArrayList<Mechanic> temp = GameManager.GetMechanics();

							for(int i = 0; i < temp.size(); i++)
								names[i] = temp.get(i).GetName();

							temp.clear();
							GameManager.SetMechanics(temp);

							westPanel.revalidate();
							westPanel.repaint();
						}

						mCount = mechanicsCountA.getSelectedIndex() + 2;

						for(int i = 0; i < mCount; i++)
						{
							var mechanic = new Mechanic();

							JLabel mechanicNameLabel = new JLabel("Add meg a karakter nevét!");
							mechanicNameLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));

							JTextField mechanicNameText = new JTextField();
							mechanicNameText.setPreferredSize(new Dimension(100, 20));
							mechanicNameText.setText(names[i] == null ? "Mechanic_" + (i + 1) : names[i]);
							mechanicNameText.setPreferredSize(new Dimension(100, 20));

							JButton setNameButton = new JButton("Set");
							setNameButton.setBackground(color);
							setNameButton.setBorder(BorderFactory.createDashedBorder(color, 5, 5, 5, false));
							setNameButton.addActionListener
							(
									new ActionListener() 
									{
										@Override
										public void actionPerformed(ActionEvent e) 
										{
											mechanic.SetName(mechanicNameText.getText());
										}
									}
									);
							mechanic.SetName(mechanicNameText.getText());

							int pos = new Random().nextInt(map.size() - 1);
							while(map.get(pos).AcceptPlayer(mechanic) == false)
								pos = new Random().nextInt(map.size() - 1);
							mechanic.SetCurrentPosition(map.get(pos));

							westPanel.revalidate();
							westPanel.repaint();

							westPanel.add(mechanicNameLabel);
							westPanel.add(mechanicNameText);
							westPanel.add(setNameButton);
						}
					}
				}
				);

		westPanel.add(mechanicLabel);
		westPanel.add(mechanicsCountQ);
		westPanel.add(mechanicsCountA);
		westPanel.add(mOkButton);

		JLabel saboteurLabel = new JLabel("Saboteurs' team");
		saboteurLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 32));

		JLabel saboteursCountQ = new JLabel("Hányan vannak a szerelők csapatában?");
		saboteursCountQ.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));

		var saboteursCountA = new JComboBox(counts);
		saboteursCountA.setSelectedIndex(0);
		saboteursCountA.setEditable(true);
		saboteursCountA.setBackground(color);
		saboteursCountA.setPreferredSize(new Dimension(50, 25));

		JButton sOkButton = new JButton("Ok");
		sOkButton.setBackground(color);
		sOkButton.setBorder(BorderFactory.createDashedBorder(color, 5, 5, 5, false));
		sOkButton.addActionListener
		(
			new ActionListener() 
			{
				int sCount = 0;

				@Override
				public void actionPerformed(ActionEvent e) 
				{
					String[] names = new String[5];

					if(sCount != 0) 
					{
						eastPanel.removeAll();
						eastPanel.add(saboteurLabel);
						eastPanel.add(saboteursCountQ);
						eastPanel.add(saboteursCountA);
						eastPanel.add(sOkButton);

						ArrayList<Saboteur> temp = GameManager.GetSaboteurs();

						for(int i = 0; i < temp.size(); i++)
							names[i] = temp.get(i).GetName();
						
						temp.clear();
						GameManager.SetSaboteurs(temp);

						eastPanel.revalidate();
						eastPanel.repaint();
					}

					sCount = saboteursCountA.getSelectedIndex() + 2;

					for(int i = 0; i < sCount; i++)
					{
						var saboteur = new Saboteur();
						JLabel saboteurNameLabel = new JLabel("Add meg a karakter nevét!");
						saboteurNameLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));

						JTextField saboteurNameText = new JTextField();
						saboteurNameText.setText("Saboteur_" + (i + 1));
						saboteurNameText.setPreferredSize(new Dimension(100, 20));

						JButton setNameButton = new JButton("Set");
						setNameButton.setBackground(color);
						setNameButton.setBorder(BorderFactory.createDashedBorder(color, 5, 5, 5, false));
						setNameButton.addActionListener
						(
							new ActionListener() 
							{
								@Override
								public void actionPerformed(ActionEvent e) 
								{
									saboteur.SetName(saboteurNameText.getText());
								}
							}
						);
						
						saboteur.SetName(saboteurNameText.getText());

						int pos = new Random().nextInt(map.size() - 1);
						while(map.get(pos).AcceptPlayer(saboteur) == false)
						pos = new Random().nextInt(map.size() - 1);
						saboteur.SetCurrentPosition(map.get(pos));

						eastPanel.revalidate();
						eastPanel.repaint();

						eastPanel.add(saboteurNameLabel);
						eastPanel.add(saboteurNameText);
						eastPanel.add(setNameButton);
					}
				}
			}
		);

		eastPanel.add(saboteurLabel);
		eastPanel.add(saboteursCountQ);
		eastPanel.add(saboteursCountA);
		eastPanel.add(sOkButton);
	}

	public void BackTrigger()
	{
		this.remove(southPanel);
		eastPanel.removeAll();
		eastPanel.setPreferredSize(new Dimension(250, 500));
		eastPanel.add(newGameButton);
		eastPanel.add(settingsButton);
		eastPanel.revalidate();
		eastPanel.repaint();
		
		if(GameManager.GetMap().size() != 0)
		{
			GameManager.EreaseMap();
		}
		
		westPanel.removeAll();
		westPanel.setPreferredSize(new Dimension(750, 500));
		westPanel.add(westLabel);
		westPanel.revalidate();
		westPanel.repaint();
	}

	public void StartGameTrigger()
	{
		if (GameManager.GetMechanics().size() != 0 && GameManager.GetSaboteurs().size() != 0)
		{
			GameManager.SetCurrentMechanic(GameManager.GetMechanics().get(0));
			new Controller(new GameFrame(new MapView()));
			setVisible(false);
			dispose();
		}
		else
			JOptionPane.showMessageDialog(this, "Kérlek előbb hozd létre a csapatokat");
	}

	public void SettingsTrigger() 
	{
		southPanel.removeAll();
		southPanel.add(backButton);
		southPanel.add(setButton);
		southPanel.revalidate();
		southPanel.repaint();
		this.add(southPanel, BorderLayout.SOUTH);

		eastPanel.removeAll();

		JLabel playerCapacity = new JLabel("Pipe capacity: ");
		playerCapacity.setFont(fs);
		playerCapacitySet = new JTextField();
		playerCapacitySet.setText(Constants.PipeCapacity + "");
		playerCapacitySet.setPreferredSize(new Dimension(100, 20));
		eastPanel.add(playerCapacity);
		eastPanel.add(playerCapacitySet);

		JLabel actionPerUser = new JLabel("Actions per user: ");
		actionPerUser.setFont(fs);
		actionsPerUserSet = new JTextField();
		actionsPerUserSet.setText(Constants.ActionInRoundPerUser + "");
		actionsPerUserSet.setPreferredSize(new Dimension(75, 20));
		eastPanel.add(actionPerUser);
		eastPanel.add(actionsPerUserSet);

		JLabel gameRound = new JLabel("Game rounds: ");
		gameRound.setFont(fs);
		gameRoundsSet = new JTextField();
		gameRoundsSet.setText(Constants.RoundNumber + "");
		gameRoundsSet.setPreferredSize(new Dimension(100, 20));
		eastPanel.add(gameRound);
		eastPanel.add(gameRoundsSet);

		JLabel leakageTimer = new JLabel("Leakage timer: ");
		leakageTimer.setFont(fs);
		leakageTimerSet = new JTextField();
		leakageTimerSet.setText(Constants.LeakageTimerBound + "");
		leakageTimerSet.setPreferredSize(new Dimension(95, 20));
		eastPanel.add(leakageTimer);
		eastPanel.add(leakageTimerSet);

		JLabel maxNeighbours = new JLabel("Neighbours of elements: ");
		maxNeighbours.setFont(new Font(Font.DIALOG, Font.ITALIC, 19));
		maxNeighboursSet = new JTextField();
		maxNeighboursSet.setText(Constants.MaxNeighboursOfActiveElements + "");
		maxNeighboursSet.setPreferredSize(new Dimension(18, 20));
		eastPanel.add(maxNeighbours);
		eastPanel.add(maxNeighboursSet);

		JLabel pipeCapacity = new JLabel("Pipe capacity: ");
		pipeCapacity.setFont(fs);
		pipeCapacitySet = new JTextField();
		pipeCapacitySet.setText(Constants.PipeCapacity + "");
		pipeCapacitySet.setPreferredSize(new Dimension(105, 20));
		eastPanel.add(pipeCapacity);
		eastPanel.add(pipeCapacitySet);

		JLabel pumpCapacity = new JLabel("Pump water capacity: ");
		pumpCapacity.setFont(fs);
		pumpCapacitySet = new JTextField();
		pumpCapacitySet.setText(Constants.PumpWaterCapacity + "");
		pumpCapacitySet.setPreferredSize(new Dimension(40, 20));
		eastPanel.add(pumpCapacity);
		eastPanel.add(pumpCapacitySet);

		JLabel pumpError = new JLabel("Pump age before error: ");
		pumpError.setFont(fs);
		pumpErrorSet = new JTextField();
		pumpErrorSet.setText(Constants.PumpErrorProbability + "");
		pumpErrorSet.setPreferredSize(new Dimension(25, 20));
		eastPanel.add(pumpError);
		eastPanel.add(pumpErrorSet);

		eastPanel.revalidate();
		eastPanel.repaint();
	}

	public void SetTrigger() 
	{
		if(Constants.MaxNeighboursOfActiveElements <= Integer.parseInt(maxNeighboursSet.getText()) ) 
		{
			this.remove(southPanel);
			eastPanel.removeAll();
			eastPanel.setPreferredSize(new Dimension(250, 500));
			eastPanel.add(newGameButton);
			eastPanel.add(settingsButton);
			eastPanel.revalidate();
			eastPanel.repaint();

			//értékek beállítása
			Constants.AcceptedPlayersInPipe = Integer.parseInt(playerCapacitySet.getText());
			Constants.ActionInRoundPerUser = Integer.parseInt(actionsPerUserSet.getText());
			Constants.LeakageTimerBound = Integer.parseInt(leakageTimerSet.getText());
			Constants.MaxNeighboursOfActiveElements = Integer.parseInt(maxNeighboursSet.getText());
			Constants.PipeCapacity = Integer.parseInt(pipeCapacitySet.getText());
			Constants.PumpErrorProbability = Integer.parseInt(pumpErrorSet.getText());
			Constants.PumpWaterCapacity = Integer.parseInt(pumpCapacitySet.getText());
			Constants.RoundNumber = Integer.parseInt(gameRoundsSet.getText());

			westPanel.removeAll();
			westPanel.setPreferredSize(new Dimension(750, 500));
			westPanel.add(westLabel);
			westPanel.revalidate();
			westPanel.repaint();
		}
		
		else 
		{
			JOptionPane.showMessageDialog(this, "Kérlek helyesen állítsd a maximum szomsédok méretét legalább 4-re.");
			SettingsTrigger();
			return;
		}
	}
}