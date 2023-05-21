package graphics.players;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.*;
import game.players.*;

public class SaboteurView extends PlayerView
{
	Saboteur saboteur;
	JLabel saboteurLabel;
	
	public SaboteurView(int x, int y, int index)
	{
		posX = x;
		posY = y;
		saboteur = GameManager.GetSaboteurs().get(index);
		LoadImage();
	}
	
	public JLabel LoadImage()
	{
		try
		{
			saboteurLabel = new JLabel();
			ImageIcon  saboteurView = new ImageIcon(this.getClass().getResource("/pipe_leaking_full_sticky.png"));
			saboteurLabel.setIcon(saboteurView);
			return saboteurLabel;
		}
		catch (Exception e)
		{
			saboteurLabel = new JLabel(saboteur.GetName());
			saboteurLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
			return saboteurLabel;
		}
	}
}
