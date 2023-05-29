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

	/**
	 * @param x
	 * @param y
	 * @param widths
	 * @param heights
	 * @param index
	 */
	public WaterSpringView(int x, int y, int widths, int heights, int index)
	{
		posX = x;
		posY = y;
		width = widths;
		height = heights;
		spring = GameManager.GetWaterSprings().get(index);
		LoadImage();
	}

	/**
	 */
	public Element GetElement() 
	{
		return spring;
	}
	
	/**
	 * @return
	 */
	public WaterSpring GetSpring()
	{
		return spring;
	}

	/**
	 */
	public Image LoadImage()
	{
		try
		{
			String path = StringMagic().concat("waterspring.png");
			BufferedImage iSpring = (BufferedImage) ImageIO.read(new File(path));
			return iSpring;
		}
		
		catch(Exception e)
		{
			return null;
		}
	}
}