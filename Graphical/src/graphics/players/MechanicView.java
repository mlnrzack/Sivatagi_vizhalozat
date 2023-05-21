package graphics.players;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.*;
import game.players.*;

public class MechanicView extends PlayerView 
{
	private Mechanic mechanic;
	private JLabel mechanicLabel;
	
	public MechanicView(int x, int y, int index)
	{
		posX = x;
		posY = y;
		mechanic = GameManager.GetMechanics().get(index);
		LoadImage();
	}
	
	public JLabel LoadImage()
	{
		try
		{
			mechanicLabel = new JLabel();
			ImageIcon  mechanicView = new ImageIcon(this.getClass().getResource("/pipe_leaking_full_sticky.png"));
			mechanicLabel.setIcon(mechanicView);
			return mechanicLabel;
		}
		catch (Exception e)
		{
			mechanicLabel = new JLabel(mechanic.GetName());
			mechanicLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
			return mechanicLabel;
		}
	}
}
