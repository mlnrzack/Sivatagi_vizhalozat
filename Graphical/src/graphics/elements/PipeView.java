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
	int centerX, centerY;
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

	public void SetDimensions() {
		if (neighbours[0] != null && neighbours[1] != null) {
			double slope = (neighbours[1].getPosY() - neighbours[0].getPosY()) / (double)(neighbours[1].getPosX() - neighbours[0].getPosX());
			int midX = (neighbours[0].getPosX() + neighbours[1].getPosX()) / 2;
			int midY = (neighbours[0].getPosY() + neighbours[1].getPosY()) / 2;
			width = (int) Math.sqrt(Math.pow(neighbours[0].getPosX() - neighbours[1].getPosX(), 2) + Math.pow(neighbours[0].getPosY() - neighbours[1].getPosY(), 2));
			height = 20;

			int pposX = 0;
			int pposY= 0 ;

			pposX = this.neighbours[0].getCenterX();
			pposY = this.neighbours[0].getCenterY();
			this.setPosX(pposX);
			this.setPosY(pposY);

			InfoLog.WriteInfoLog((pipe.GetId()));
			InfoLog.WriteInfoLog(("MidX: " + Integer.toString(midX)));
			InfoLog.WriteInfoLog(("MidY " + Integer.toString(midY)));
			InfoLog.WriteInfoLog(("Width  " + Integer.toString(width)));
			InfoLog.WriteInfoLog(("PosX " + Integer.toString(pposX)));
			InfoLog.WriteInfoLog(("PosY " + Integer.toString(pposY)));
		}
	}

	@Override
	public int getCenterX(){
		Point center = calculateCenter(this.GetNeighbours()[0].getCenterX(), this.GetNeighbours()[0].getCenterY(),
				this.GetNeighbours()[1].getCenterX(), this.GetNeighbours()[1].getCenterY());
		int imageX = (int) (center.x - this.getWidth() / 2);
		centerX = imageX + this.getWidth() / 2;
		return centerX;
	}

	@Override
	public int getCenterY(){
		Point center = calculateCenter(this.GetNeighbours()[0].getCenterX(), this.GetNeighbours()[0].getCenterY(),
				this.GetNeighbours()[1].getCenterX(), this.GetNeighbours()[1].getCenterY());
		int imageY = (int) (center.y - this.getHeight() / 2);
		centerY = imageY + this.getHeight() / 2;
		return centerY;
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

	public IElement GetPipe() {
		return pipe;
	}

	public static Point calculateCenter(int x1, int y1, int x2, int y2) {
		int centerX = (x1 + x2) / 2;
		int centerY = (y1 + y2) / 2;
		return new Point(centerX, centerY);
	}
}