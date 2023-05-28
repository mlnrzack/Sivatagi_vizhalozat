package graphics.elements;

import javax.swing.*;

import game.elements.*;
import game.interfaces.*;

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
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * @return
	 */
	public int getHeight()
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
	public int getCenterX()
	{
		return (posX+getWidth()/2);
	}
	
	/**
	 * @return
	 */
	public int getCenterY()
	{
		return (posY+getHeight()/2);
	}
	
	/**
	 * @return
	 */
	public int getPosX()
	{
		return posX;
	}

	/**
	 * @return
	 */
	public int getPosY()
	{
		return posY;
	}

	/**
	 * @param i
	 */
	public void setCenterX(int i)
	{
		posX = i - getWidth()/2;
	}

	/**
	 * @param i
	 */
	public void setCenterY(int i)
	{
		posY = i - getHeight()/2;
	}
	
	/**
	 * @param i
	 */
	public void setPosX(int i)
	{
		posX = i;
	}
	
	/**
	 * @param i
	 */
	public void setPosY(int i)
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
