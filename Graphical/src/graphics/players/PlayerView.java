package graphics.players;

import java.awt.Image;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

import game.interfaces.*;
import graphics.elements.*;

public abstract class PlayerView
{
	protected ElementView pos;
	protected int height;
	protected int width;
	
	/**
	 */
	public PlayerView()
	{
		//TODO
	}
	
	/**
	 * @return
	 */
	public ElementView GetPos()
	{
		return pos;
	}
	
	public abstract Image LoadImage();
	
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
	
	/**
	 * @return
	 */
	public int GetHeight()
	{
		return height;
	}

	/**
	 * @return
	 */
	public int GetWidth()
	{
		return width;
	}
	protected abstract void UpdatePos(ElementView pos);

}
