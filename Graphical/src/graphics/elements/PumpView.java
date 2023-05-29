package graphics.elements;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import game.GameManager;
import game.elements.*;

public class PumpView extends ElementView
{
	Pump pump;																//
	
	/**
	 * @param x
	 * @param y
	 * @param widths
	 * @param heights
	 * @param index
	 */
	public PumpView(int x, int y, int widths, int heights, int index)
	{
		posX = x;
		posY = y;
		width = widths;
		height = heights;
		pump = GameManager.GetPumps().get(index);
		LoadImage();
	}

	/**
	 */
	public Element GetElement()
	{
		return pump;
	}
	
	/**
	 * @return
	 */
	public Pump GetPump()
	{
		return pump;
	}
	
	/**
	 * @param i
	 */
	public void setIndex(int i)
	{
		pump = GameManager.GetPumps().get(i);
	}
	
	/**
	 */
	public Image LoadImage()
	{
		String pathPump = StringMagic().concat("pump.png");
		String pathBrokenPump = StringMagic().concat("pump_broken.png");

		BufferedImage  iPump;
		try
		{
			if(!pump.GetBroken())
			{
				iPump = ImageIO.read(new File(pathPump));
				return iPump;
			}
			else if(pump.GetBroken())
			{
				iPump = ImageIO.read(new File(pathBrokenPump));
				return iPump;
			}
			return null;
		}		
		catch(Exception e)
		{
			return null;
		}
	}
}