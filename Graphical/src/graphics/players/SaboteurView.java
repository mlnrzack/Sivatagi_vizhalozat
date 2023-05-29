package graphics.players;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import game.*;
import game.players.*;
import graphics.elements.*;

public class SaboteurView extends PlayerView
{
	Saboteur saboteur;



	public SaboteurView(ElementView pos, int width , int height, int index)
	{
		this.pos = pos;
		this.width = width;
		this.height = height;
		saboteur = GameManager.GetSaboteurs().get(index);
		LoadImage();
	}
	public Saboteur getSaboteur() {
		return saboteur;
	}
	
	public Image LoadImage()
	{
		try
		{
			String pathSaboteur = StringMagic().concat("saboteur.png");
			BufferedImage  iSaboteur = ImageIO.read(new File(pathSaboteur));
			return iSaboteur;
		}
		
		catch (Exception e)
		{
			return null;
		}
	}
}
