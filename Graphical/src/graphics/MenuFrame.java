package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.GameManager;
import game.Program;

public class MenuFrame extends JFrame
{
	private Color color = Color.decode("#c9a77d");
	private JButton backButton;
	private JButton newGameButton;
	private JButton startGameButton;
	//private JButton settingsButton;
	//private JButton setButton;
	
	private static JLabel westLabel;
	private JLabel northLabel;
	
	private static JPanel eastPanel;
	private static JPanel westPanel;
	private JPanel northPanel;	
	private JPanel southPanel;
	
	private Font f = new Font(Font.DIALOG, Font.PLAIN, 28);
	private Font fi = new Font(Font.DIALOG, Font.ITALIC, 48);
	
	public MenuFrame()
	{
		super("Sivatagi vizhalozat");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/icon.png"));
		this.setIconImage(icon.getImage());
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
		newGameButton.setBorder(BorderFactory.createDashedBorder(color, 5, 5, 5, false));
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
		/*
		settingsButton = new JButton("Settings");
		settingsButton.setFont(f);
		settingsButton.setBackground(color);
		settingsButton.setBorder(BorderFactory.createDashedBorder(color, 5, 5, 5, false));
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
		*/
		eastPanel = new JPanel();
		eastPanel.add(newGameButton);
		//eastPanel.add(settingsButton);
		eastPanel.setPreferredSize(new Dimension(250, 500));
		eastPanel.setBackground(color);
		eastPanel.revalidate();
		eastPanel.repaint();
	}
	
	public void InitializeWestPanel()
	{
		try
		{
			ImageIcon logo = new ImageIcon(this.getClass().getResource("/logo.png"));
			westLabel = new JLabel();
			westLabel.setIcon(logo);
			
			westPanel = new JPanel();
			westPanel.add(westLabel);
			westPanel.setPreferredSize(new Dimension(750, 500));
			westPanel.setBackground(color); 
		}
		
		catch(Exception e)
		{
			westLabel = new JLabel("Sivatagi vízhálózat");
			westLabel.setFont(fi);
			westLabel.setHorizontalAlignment(SwingConstants.LEFT);
			
			westPanel = new JPanel();
			westPanel.add(westLabel);
			westPanel.setPreferredSize(new Dimension(750, 350));
			westPanel.setBackground(color);
		}
	}
	
	public void InitializeSouthPanel()
	{
		backButton = new JButton("Back");
		backButton.setFont(f);
		backButton.setBackground(color);
		backButton.setBorder(BorderFactory.createDashedBorder(color, 2, 2, 2, false));
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
		startGameButton.setBorder(BorderFactory.createDashedBorder(color, 2, 2, 2, false));
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
		/*
		setButton = new JButton("Set");
		setButton.setFont(f);
		setButton.setBackground(color);
		setButton.setBorder(BorderFactory.createDashedBorder(color, 2, 2, 2, false));
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
		*/
		southPanel = new JPanel(new GridLayout(1, 2));
		southPanel.setBackground(color);
		southPanel.setPreferredSize(new Dimension(800, 80));
		southPanel.add(backButton);
		southPanel.add(startGameButton);
	}
	
	public void NewGameTrigger()
	{
		this.add(southPanel, BorderLayout.SOUTH);
		eastPanel.removeAll();
		eastPanel.setPreferredSize(new Dimension(500, 500));
		eastPanel.revalidate();
    	eastPanel.repaint();
		
		westPanel.removeAll();
		westPanel.setPreferredSize(new Dimension(500, 500));
    	westPanel.revalidate();
    	westPanel.repaint();
    	
    	Program.CreateMap();
	}
	
	public void BackTrigger()
	{
		this.remove(southPanel);
		eastPanel.removeAll();
		eastPanel.setPreferredSize(new Dimension(200, 500));
		eastPanel.add(newGameButton);
		//eastPanel.add(settingsButton);
		eastPanel.revalidate();
		eastPanel.repaint();
		
		westPanel.removeAll();
		westPanel.setPreferredSize(new Dimension(800, 500));
		westPanel.add(westLabel);
		westPanel.revalidate();
		westPanel.repaint();
	}
	
	public void StartGameTrigger()
	{
		//TODO
		if(GameManager.GetMechanics().size() != 0 && GameManager.GetSaboteurs().size() != 0)
		{
			GameManager.StartGame();
			/*
			GameFrame gFrame = new GameFrame();
			setVisible(false);
			dispose();
			*/
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Kérlek előbb hozd létre a csapatokat");
		}
	}
	/*
	public void SettingsTrigger()
	{
		this.add(southPanel);
		eastPanel.removeAll();
		eastPanel.revalidate();
		eastPanel.repaint();
	}
	
	public void SetTrigger()
	{
		this.remove(southPanel);
		eastPanel.removeAll();
		eastPanel.setPreferredSize(new Dimension(200, 500));
		eastPanel.add(newGameButton);
		eastPanel.add(settingsButton);
		eastPanel.revalidate();
		eastPanel.repaint();
		
		westPanel.removeAll();
		westPanel.setPreferredSize(new Dimension(800, 500));
		westPanel.add(westLabel);
		westPanel.revalidate();
		westPanel.repaint();
	}
	*/
}
