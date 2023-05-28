package graphics.elements;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import game.*;
import game.elements.*;

public class WaterSpringView extends ElementView
{
	WaterSpring spring;																				//a megjelenítő modellbeli párja

	public WaterSpringView(int x, int y, int widths, int heights, int index)
	{
		posX = x;
		posY = y;
		width = widths;
		height = heights;
		spring = GameManager.GetWaterSprings().get(index);
		LoadImage();
	}

	public WaterSpring getSpring() 
	{
		return spring;
	}

	public Image LoadImage()
	{
		String path = StringMagic().concat("waterspring.png");
		try
		{
			BufferedImage iSpring = (BufferedImage) ImageIO.read(new File(path));
			BufferedImage resizedImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
			Graphics2D graphics2D = resizedImage.createGraphics();
			graphics2D.drawImage(iSpring, 0, 0, 50, 50, null);
			graphics2D.dispose();
			Image loadedImage = resizedImage;
			return loadedImage;
		}
		
		catch(Exception e)
		{
			return null;
		}
	}
}