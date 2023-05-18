package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuFrame extends JFrame
{
	private JButton newGameButton;
	
	private JLabel logoLabel;
	
	private JPanel eastPanel;
	private JPanel northPanel;
	private JPanel westPanel;
	
	private Font f = new Font(Font.DIALOG, Font.PLAIN, 24);
	private Font fi = new Font(Font.DIALOG, Font.ITALIC, 42);
	
	public MenuFrame()
	{
		super("Sivatagi vizhalozat");
		//ImageIcon icon = new ImageIcon(this.getClass().getResource("/icon.png"));
		//this.setIconImage(icon.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoadInterface();
	}
	
	public void LoadInterface()
	{
		InitializeNorthPanel();
		InitializeEastPanel();
		InitializeWestPanel();
		
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
		
	}
	
	public void InitializeEastPanel()
	{
		newGameButton = new JButton("New Game");
		newGameButton.setFont(f);
		newGameButton.setBackground(Color.decode("#285f5f"));
		newGameButton.setForeground(Color.decode("#289696"));
		newGameButton.setBorder(BorderFactory.createDashedBorder(Color.decode("#287878"), 35, 30, 10, false));
		newGameButton.addActionListener(new ActionListener() 
		{
		    @Override
		    public void actionPerformed(ActionEvent e) 
		    {
		    	newgameTrigger();
		    }
		});
	}
	
	public void InitializeWestPanel()
	{
		try
		{
			Image logo = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
			logoLabel = new JLabel();
			logoLabel.setIcon(new ImageIcon(logo));
			
			westPanel = new JPanel();
			westPanel.add(logoLabel);
			westPanel.setPreferredSize(new Dimension(500, 350));
			westPanel.setBackground(Color.decode("#287878"));
		}
		
		catch(Exception e)
		{
			logoLabel = new JLabel("Sivatagi vízhálózat");
			logoLabel.setFont(fi);
			logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
			
			westPanel = new JPanel();
			westPanel.add(logoLabel);
			westPanel.setPreferredSize(new Dimension(500, 350));
			westPanel.setBackground(Color.decode("#287878"));
		}
	}
	
	public void newgameTrigger()
	{
		eastPanel.removeAll();
		westPanel.removeAll();
		//csapatlétrehozó felület megvalósításának hívása, majd felrakása a panelra.
		//eastPanel.
    	eastPanel.revalidate();
    	eastPanel.repaint();
	}
}
