package graphics.elements;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.*;
import game.elements.*;

public class CisternView extends ElementView
{
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	Cistern cistern;
	
	JLabel cisternLabel;
	
	public CisternView(int x, int y, int index)
	{
		//TODO
		posX = x;
		posY = y;
		cistern = GameManager.GetCisterns().get(index);
		LoadImage();
	}
	
	public void LoadImage()
	{
		try
		{
			ImageIcon cisternImage = new ImageIcon(this.getClass().getResource("/cistern.png"));
			cisternLabel.setIcon(cisternImage);
			//fel kell rakni a gamePanel-ra.
		}
		
		catch(Exception e)
		{
			cisternLabel = new JLabel("Cistern");
			cisternLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
			cisternLabel.setHorizontalAlignment(SwingConstants.LEFT);
			//fel kell rakni a gamePanel-ra.
		}
	}
}