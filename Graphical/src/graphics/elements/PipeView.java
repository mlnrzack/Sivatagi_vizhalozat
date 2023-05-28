package graphics.elements;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import game.*;
import game.elements.*;

public class PipeView extends ElementView
{
	private Pipe pipe;														//
	private ElementView[] neighbours = new ElementView[2];					//
	
	/**
	 * @param index
	 */
	public PipeView(int index)
	{
		//TODO
		pipe = GameManager.GetPipes().get(index);
		neighbours[0] = null;
		neighbours[1] = null;
		LoadImage();
	}
	
	/**
	 */
	public Element GetElement()
	{
		return pipe;
	}
	
	/**
	 * @return
	 */
	public ElementView[] GetNeighbours()
	{
		return neighbours;
	}
	
	/**
	 * @param neighbours
	 */
	public void SetNeighbours(ElementView[] neighbours)
	{
		for(int i = 0; i < this.neighbours.length; i++)
			this.neighbours[i] = neighbours[i];
		SetDimensions();
	}

	public void SetDimensions()
	{
		if(neighbours[0] != null)
		{
			posX = neighbours[0].getCenterX();
			posY = neighbours[0].getCenterY();
			
			if(neighbours[1] != null)
			{
				int midX =((neighbours[0].getPosX()+neighbours[1].getPosX())/2);
				int midY =((neighbours[0].getPosY()+neighbours[1].getPosY())/2)-50;

				width = (int) Math.sqrt(((neighbours[0].getPosX() - neighbours[1].getPosX())*(neighbours[0].getPosX() - neighbours[1].getPosX()))
							+ ((neighbours[0].getPosY() - neighbours[1].getPosY())*(neighbours[0].getPosY() - neighbours[1].getPosY())));
				height = 20;
				this.setPosX(midX-(width/2));
				this.setPosY(midY-(height/2));

			}
		}
	}
	
	/**
	 */
	public Image LoadImage()
	{
		//talán itt még a méretet be kell állítani
		try
		{
			String pathPipe;
			BufferedImage iPipe;
			
			if(pipe.GetWaterInside() == 0)
			{
				if(!pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						pathPipe = StringMagic().concat("pipe_empty.png");
						iPipe = ImageIO.read(new File(pathPipe));
						return iPipe;
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						pathPipe = StringMagic().concat("pipe_empty_slippery.png");
						iPipe = ImageIO.read(new File(pathPipe));
						return iPipe;
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						pathPipe = StringMagic().concat("pipe_empty_sticky.png");
						iPipe = ImageIO.read(new File(pathPipe));
						return iPipe;
					}
					return null;
				}

				else if(pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						pathPipe = StringMagic().concat("/pipe_leaking_empty.png");
						iPipe = ImageIO.read(new File(pathPipe));
						return iPipe;
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						pathPipe = StringMagic().concat("/pipe_leaking_empty_slippery.png");
						iPipe = ImageIO.read(new File(pathPipe));
						return iPipe;
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						pathPipe = StringMagic().concat("/pipe_leaking_empty_sticky.png");
						iPipe = ImageIO.read(new File(pathPipe));
						return iPipe;
					}
					return null;
				}
				return null;
			}

			else if(pipe.GetWaterInside() == 1)
			{
				if(!pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						pathPipe = StringMagic().concat("/pipe_full.png");
						iPipe = ImageIO.read(new File(pathPipe));
						return iPipe;
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						pathPipe = StringMagic().concat("/pipe_full_slippery.png");
						iPipe = ImageIO.read(new File(pathPipe));
						return iPipe;
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						pathPipe = StringMagic().concat("/pipe_full_sticky.png");
						iPipe = ImageIO.read(new File(pathPipe));
						return iPipe;
					}
					return null;
				}

				else if(pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						pathPipe = StringMagic().concat("/pipe_leaking_full.png");
						iPipe = ImageIO.read(new File(pathPipe));
						return iPipe;
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						pathPipe = StringMagic().concat("/pipe_leaking_full_slippery.png");
						iPipe = ImageIO.read(new File(pathPipe));
						return iPipe;
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						pathPipe = StringMagic().concat("/pipe_leaking_full_sticky.png");
						iPipe = ImageIO.read(new File(pathPipe));
						return iPipe;
					}
					return null;
				}
				return null;
			}
			return null;
		}
		
		catch(Exception e)
		{
			return null;
		}
	}
}