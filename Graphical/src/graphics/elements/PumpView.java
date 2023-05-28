package graphics.elements;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import game.GameManager;
import game.elements.*;

public class PumpView extends ElementView
{
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	Pump pump;
	
	JLabel pumpLabel;
	
	public PumpView(int x, int y, int widths, int heights, int index)
	{
		posX = x;
		posY = y;
		width = widths;
		height = heights;
		pump = GameManager.GetPumps().get(index);
		LoadImage();
	}

	public void setIndex(int i)
	{
		pump = GameManager.GetPumps().get(i);
	}
	
	public Image LoadImage()
	{
		String pathPump = StringMagic().concat("pump.png");
		String pathBrokenPump = StringMagic().concat("pump_broken.png");

		BufferedImage  iPpump, brokenPump;
		Image loadedImage;
		try
		{
			if(!pump.GetBroken())
			{
				iPpump = ImageIO.read(new File(pathPump));
				loadedImage = iPpump.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				return loadedImage;
			}
			else if(pump.GetBroken())
			{
				brokenPump = ImageIO.read(new File(pathBrokenPump));
				loadedImage = brokenPump.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				return loadedImage;
			}
			return null;
		}
		
		catch(Exception e)
		{
			return null;
		}
	}
}