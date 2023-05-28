package graphics.elements;

import java.awt.*;

import javax.swing.*;

import game.*;
import game.elements.*;

public class PipeView extends ElementView
{
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	private Pipe pipe;
	private ElementView[] neighbours = new ElementView[2];
	
	public PipeView(int index)
	{
		//TODO
		pipe = GameManager.GetPipes().get(index);
		neighbours[0] = null;
		neighbours[1] = null;
		LoadImage();
	}
	
	public Element GetElement()
	{
		return pipe;
	}
	
	public ElementView[] GetNeighbours()
	{
		return neighbours;
	}
	
	public void SetNeighbours(ElementView[] neighbours)
	{
		for(int i = 0; i < this.neighbours.length; i++)
			this.neighbours[i] = neighbours[i];
	}
	/*
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(Color.BLACK);
		//vizsgálni, hogy van-e megfelelő számú szomszédja
		g2.drawLine(neighbours[0].getPosX(), neighbours[0].getPosY(), neighbours[1].getPosX(), neighbours[1].getPosY() );
	}
	 */
	public Image LoadImage()
	{
		//talán itt még a méretet be kell állítani
		try
		{
			String pathPipe = StringMagic().concat("pump.png");
			if(pipe.GetWaterInside() == 0)
			{
				if(!pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_empty.png"));
						Image loadedImage = iPipe.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
						return loadedImage;
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_empty_slippery.png"));
						Image loadedImage = iPipe.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
						return loadedImage;
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_empty_sticky.png"));
						Image loadedImage = iPipe.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
						return loadedImage;
					}
					return null;
				}

				else if(pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_empty.png"));
						Image loadedImage = iPipe.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
						return loadedImage;
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_empty_slippery.png"));
						Image loadedImage = iPipe.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
						return loadedImage;
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_empty_sticky.png"));
						Image loadedImage = iPipe.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
						return loadedImage;
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
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_full.png"));
						Image loadedImage = iPipe.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
						return loadedImage;
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_full_slippery.png"));
						Image loadedImage = iPipe.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
						return loadedImage;
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_full_sticky.png"));
						Image loadedImage = iPipe.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
						return loadedImage;
					}
					return null;
				}

				else if(pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_full.png"));
						Image loadedImage = iPipe.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
						return loadedImage;
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_full_slippery.png"));
						Image loadedImage = iPipe.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
						return loadedImage;
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						ImageIcon  iPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_full_sticky.png"));
						Image loadedImage = iPipe.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
						return loadedImage;
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