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
	
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	
	public PlayerView()
	{
		//TODO
	}
	public ElementView getPos()
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
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}


}
