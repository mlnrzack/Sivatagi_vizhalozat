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
	
	public MechanicView(ElementView pos, int index)
	{
		this.pos = pos;
		mechanic = GameManager.GetMechanics().get(index);
		LoadImage();
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
