package graphics.elements;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import game.*;
import game.IO.InfoLog;
import game.elements.*;
import game.interfaces.IElement;

public class PipeView extends ElementView
{
	private Pipe pipe;														//
	private ElementView[] neighbours = new ElementView[2];
	//

	double angle= 0;
	int index= 0;
	/**
	 * @param index
	 */
	public PipeView(int index)
	{
		//TODO
		pipe = GameManager.GetPipes().get(index);
		neighbours[0] = null;
		neighbours[1] = null;
		this.index = index;
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

	public void SetDimensions() {
		if (neighbours[0] != null && neighbours[1] != null) {
			double slope = (neighbours[1].getPosY() - neighbours[0].getPosY()) / (double)(neighbours[1].getPosX() - neighbours[0].getPosX());
			int midX = (neighbours[0].getPosX() + neighbours[1].getPosX()) / 2;
			int midY = (neighbours[0].getPosY() + neighbours[1].getPosY()) / 2;
			width = (int) Math.sqrt(Math.pow(neighbours[0].getPosX() - neighbours[1].getPosX(), 2) + Math.pow(neighbours[0].getPosY() - neighbours[1].getPosY(), 2));
			height = 20;

			int pposX = 0;
			int pposY= 0 ;
			/*
			pposY = midY - (height / 2);
			if (slope > 0) {
				System.out.println("slope > 0");
				pposX -= (int) (slope * (pposY - midY));
			} else if (slope < 0) {
				System.out.println("slope < 0");
				pposX += (int) (slope * (pposY - midY));
			} else {
				pposX = midX - (width / 2);
			}

			// Adjust pposX based on the vertical position of point A
			int verticalDistance = Math.abs(midY - neighbours[0].getPosY());

			// Define a scaling factor based on the vertical distance from the horizontal line
			double scalingFactor = 1.0; // Adjust this value to control the amount of movement on the X coordinates

			pposX += (int) (scalingFactor * verticalDistance);
*/

			pposX = this.neighbours[0].getCenterX();
			pposY = this.neighbours[0].getCenterY();
			this.setPosX(pposX);
			this.setPosY(pposY);

			InfoLog.WriteInfoLog((GameManager.GetPipes().get(index).GetId()));
			InfoLog.WriteInfoLog(("MidX: " + Integer.toString(midX)));
			InfoLog.WriteInfoLog(("MidY " + Integer.toString(midY)));
			InfoLog.WriteInfoLog(("Width  " + Integer.toString(width)));
			InfoLog.WriteInfoLog(("PosX " + Integer.toString(pposX)));
			InfoLog.WriteInfoLog(("PosY " + Integer.toString(pposY)));
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