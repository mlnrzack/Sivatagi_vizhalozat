package graphics.players;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import game.*;
import game.players.*;
import graphics.elements.*;

public class MechanicView extends PlayerView 
{
	private Mechanic mechanic;



	public MechanicView(ElementView pos, int width , int height, int index )
	{
		this.pos = pos;
		this.width = width;
		this.height = height;
		mechanic = GameManager.GetMechanics().get(index);
		LoadImage();
	}
	public Mechanic getMechanic() {
		return mechanic;
	}
	
	public Image LoadImage()
	{
		try
		{
			String pathMechanic = StringMagic().concat("mechanic.png");
			BufferedImage  iMechanic = ImageIO.read(new File(pathMechanic));
			return iMechanic;
		}
		
		catch (Exception e)
		{
			return null;
		}
	}
}
