package graphics.elements;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import game.*;
import game.elements.*;

public class WaterSpringView extends ElementView
{
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	WaterSpring spring;
	
	JLabel springLabel;

	public WaterSpring getSpring() {
		return spring;
	}

	public WaterSpringView(int x, int y, int widths, int heights, int index)
	{
		posX = x;
		posY = y;
		width = widths;
		height = heights;
		spring = GameManager.GetWaterSprings().get(index);
		LoadImage();
	}


	
	public Image LoadImage()
	{
		String path = StringMagic().concat("waterspring.png");
		try
		{
			BufferedImage iSpring = ImageIO.read(new File(path));
			Image loadedImage = iSpring.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			return loadedImage;
		}
		
		catch(Exception e)
		{
			return null;
		}
	}
}