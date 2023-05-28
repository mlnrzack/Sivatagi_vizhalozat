package graphics.elements;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import game.*;
import game.elements.*;

public class CisternView extends ElementView
{
	Cistern cistern;														//
	
	/**
	 * @param x
	 * @param y
	 * @param widths
	 * @param heights
	 * @param index
	 */
	public CisternView(int x, int y, int widths, int heights, int index)
	{
		posX = x;
		posY = y;
		width = widths;
		height = heights;
		cistern = GameManager.GetCisterns().get(index);
	}

	/**
	 */
	public Element GetElement()
	{
		return cistern;
	}
	
	/**
	 * @return
	 */
	public Cistern GetCistern()
	{
		return cistern;
	}

	/**
	 */
	public Image LoadImage()
	{
		try
		{
			String path = StringMagic().concat("cistern.png");
			BufferedImage iCistern = ImageIO.read(new File(path));
			return iCistern;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}