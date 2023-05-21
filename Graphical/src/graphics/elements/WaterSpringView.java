package graphics.elements;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.*;
import game.elements.*;

public class WaterSpringView extends ElementView
{
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	WaterSpring spring;
	
	JLabel springLabel;
	
	public WaterSpringView(int x, int y, int index)
	{
		posX = x;
		posY = y;
		spring = GameManager.GetWaterSprings().get(index);
		LoadImage();
	}
	
	public void LoadImage()
	{
		try
		{
			ImageIcon waterSpring = new ImageIcon(this.getClass().getResource("/waterspring.png"));
			springLabel.setIcon(waterSpring);
			//fel kell rakni a gamePanel-ra.
		}
		
		catch(Exception e)
		{
			springLabel = new JLabel("WaterSpring");
			springLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
			springLabel.setHorizontalAlignment(SwingConstants.LEFT);
			//fel kell rakni a gamePanel-ra.
		}
	}
}