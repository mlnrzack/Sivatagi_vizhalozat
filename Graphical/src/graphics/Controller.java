package graphics;

import graphics.elements.*;
import graphics.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import graphics.GameFrame;

public class Controller 
{
	
	protected GameFrame gf;
	protected ArrayList<JButton> buttons;
	
	public Controller(GameFrame gf)
	{
		this.gf = gf;
		buttons = gf.getActionButtons();
		initController();
	}
	
	private void initController() 
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
}
