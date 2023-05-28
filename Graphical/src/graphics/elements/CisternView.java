package graphics.elements;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.imageio.ImageIO;
import game.*;
import game.elements.*;

public class CisternView extends ElementView
{
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	Cistern cistern;
	
	JPanel cisternPanel;
	
	public CisternView(int x, int y, int widths, int heights, int index)
	{
		posX = x;
		posY = y;
		width = widths;
		height = heights;
		cistern = GameManager.GetCisterns().get(index);
	}

	public Element GetElement()
	{
		return cistern;
	}
	
	public Cistern GetCistern()
	{
		return cistern;
	}
	
	public JPanel LoadCircle()
	 {
		try 
		{
			cisternPanel = new JPanel();
			ImageIcon image = new ImageIcon(this.getClass().getResource("/cistern.png"));
			Image loadedImage = image.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			image = new ImageIcon(loadedImage);
			return cisternPanel;
		}
		catch (Exception e)
		{
			cisternPanel = new JPanel();
			cisternPanel.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
			cisternPanel.setAlignmentX(SwingConstants.LEFT);

			return cisternPanel;
		}
	 }



	public Image LoadImage()
	{
		String path = StringMagic().concat("cistern.png");
		try
		{
			BufferedImage iCistern = ImageIO.read(new File(path));
			Image loadedImage = iCistern.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			return iCistern;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}