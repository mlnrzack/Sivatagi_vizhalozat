package graphics.elements;

import game.elements.*;

import java.awt.*;
import java.io.File;

public abstract class ElementView //extends JPanel
{
	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	
	/**
	 * @return
	 */
	public int GetWidth()
	{
		return width;
	}
	
	/**
	 * @return
	 */
	public int GetHeight()
	{
		return height;
	}

	
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	/**
	 */
	public ElementView()
	{
		//TODO
	}
	/**
	 * @return
	 */
	public abstract Image LoadImage();
	
	/**
	 * @return
	 */
	public abstract Element GetElement();

	/**
	 * @return
	 */
	public int GetCenterX()
	{
		return (posX + GetWidth()/2);
	}
	
	/**
	 * @return
	 */
	public int GetCenterY()
	{
		return (posY + GetHeight()/2);
	}
	
	/**
	 * @return
	 */
	public int GetPosX()
	{
		return posX;
	}

	/**
	 * @return
	 */
	public int GetPosY()
	{
		return posY;
	}

	/**
	 * @param i
	 */
	public void SetCenterX(int i)
	{
		posX = i - GetWidth()/2;
	}

	/**
	 * @param i
	 */
	public void SetCenterY(int i)
	{
		posY = i - GetHeight()/2;
	}
	
	/**
	 * @param i
	 */
	public void SetPosX(int i)
	{
		posX = i;
	}
	
	/**
	 * @param i
	 */
	public void SetPosY(int i)
	{
		posY = i;
	}

	/**
	 * @return
	 */
	public static String StringMagic()
	{
		File currentDirFile = new File(".");
		String helper = currentDirFile.getAbsolutePath();
		String midhelp = helper.substring(0, helper.length() - 2);
		return midhelp.concat("\\bin\\");
	}
}
