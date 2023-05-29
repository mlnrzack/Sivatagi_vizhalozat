package graphics.elements;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import game.*;
import game.IO.InfoLog;
import game.elements.*;

public class PipeView extends ElementView
{
	private Pipe pipe;														//modellel összekötésben álló elem
	private ElementView[] neighbours = new ElementView[2];					//az elem szomszédjai
	private int centerX, centerY;											//az elem közepének koordinátái
	private double angle;													//az elem szöge az x tengelyhez képes radiánban
	
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

	/**modellbeli elem visszaadása elemként
	 * @return
	 */
	public Element GetElement()
	{
		return pipe;
	}
	
	/**modellbeli elem visszaadása csőként
	 * @return
	 */
	public Pipe GetPipe()
	{
		return pipe;
	}
	
	/**cső szomszédjainak átadása
	 * @return
	 */
	public ElementView[] GetNeighbours()
	{
		return neighbours;
	}
	
	/**cső szomszédjainak beállítása
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
		if (neighbours[0] != null && neighbours[1] != null) 
		{
			double slope = (neighbours[1].GetPosY() - neighbours[0].GetPosY()) / (double)(neighbours[1].GetPosX() - neighbours[0].GetPosX());
			int midX = (neighbours[0].GetPosX() + neighbours[1].GetPosX()) / 2;
			int midY = (neighbours[0].GetPosY() + neighbours[1].GetPosY()) / 2;
			width = (int) Math.sqrt(Math.pow(neighbours[0].GetPosX() - neighbours[1].GetPosX(), 2) + Math.pow(neighbours[0].GetPosY() - neighbours[1].GetPosY(), 2));
			height = 20;

			int pposX = 0;
			int pposY= 0 ;

			pposX = this.neighbours[0].GetCenterX();
			pposY = this.neighbours[0].GetCenterY();
			this.SetPosX(pposX);
			this.SetPosY(pposY);

			angle = Math.atan2(this.neighbours[1].GetCenterY() - this.neighbours[0].GetCenterY(), this.neighbours[1].GetCenterX() - this.neighbours[0].GetCenterX());
			
			InfoLog.WriteInfoLog((pipe.GetId()));
			InfoLog.WriteInfoLog(("MidX: " + Integer.toString(midX)));
			InfoLog.WriteInfoLog(("MidY " + Integer.toString(midY)));
			InfoLog.WriteInfoLog(("Width  " + Integer.toString(width)));
			InfoLog.WriteInfoLog(("PosX " + Integer.toString(pposX)));
			InfoLog.WriteInfoLog(("PosY " + Integer.toString(pposY)));
		}
	}

	public double GetAngle()
	{
		return angle;
	}
	
	@Override
	public int GetCenterX()
	{
		Point center = calculateCenter(this.GetNeighbours()[0].GetCenterX(), this.GetNeighbours()[0].GetCenterY(),
				this.GetNeighbours()[1].GetCenterX(), this.GetNeighbours()[1].GetCenterY());
		int imageX = (int) (center.x - this.GetWidth() / 2);
		centerX = imageX + this.GetWidth() / 2;
		return centerX;
	}

	@Override
	public int GetCenterY()
	{
		Point center = calculateCenter(this.GetNeighbours()[0].GetCenterX(), this.GetNeighbours()[0].GetCenterY(),
				this.GetNeighbours()[1].GetCenterX(), this.GetNeighbours()[1].GetCenterY());
		int imageY = (int) (center.y - this.GetHeight() / 2);
		centerY = imageY + this.GetHeight() / 2;
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

	public static Point calculateCenter(int x1, int y1, int x2, int y2)
	{
		int centerX = (x1 + x2) / 2;
		int centerY = (y1 + y2) / 2;
		return new Point(centerX, centerY);
	}
}