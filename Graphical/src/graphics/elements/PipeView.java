package graphics.elements;

import java.awt.*;
import java.awt.geom.Point2D;

import javax.swing.*;

import game.*;
import game.elements.Pipe;
//import graphics.Paintable;

public class PipeView extends ElementView
{
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	private Pipe pipe;
	private JLabel pipeLabel;
	private ElementView[] neighbours = new ElementView[2];

	private final ElementView obj1;
	private ElementView obj2 ;

	public PipeView(ElementView obj1, ElementView obj2, int index)
	{
		//TODO
		this.obj1 = obj1;
		this.obj2 = obj2;
		pipe = GameManager.GetPipes().get(index);
		LoadImage();
	}

	public ElementView getObj1(){
		return obj1;
	}

	public ElementView getObj2()
	{
		return obj2;
	}

	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(Color.BLACK);
		g2.drawLine(obj1.getPosX(),obj1.getPosY(), obj2.getPosX(), obj2.getPosY() );
	}


	
	public Image LoadImage()
	{
		//talán itt még a méretet be kell állítani
		try
		{
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
				}
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
				}
			}
			return null;
		}
		
		catch(Exception e)
		{
			if(pipe.GetWaterInside() == 0)
			{
				if(!pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						pipeLabel = new JLabel("Pipe empty");
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						pipeLabel = new JLabel("Pipe empty slippery");
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						pipeLabel = new JLabel("Pipe empty sticky");
					}
				}

				else if(pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						pipeLabel = new JLabel("Pipe empty leaking");
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						pipeLabel = new JLabel("Pipe empty leaking slippery");
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						pipeLabel = new JLabel("Pipe empty leaking sticky");
					}
				}
			}

			else if(pipe.GetWaterInside() == 1)
			{
				if(!pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						pipeLabel = new JLabel("Pipe full");
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						pipeLabel = new JLabel("Pipe full slippery");
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						pipeLabel = new JLabel("Pipe full sticky");
					}
				}

				else if(pipe.GetLeaking())
				{
					if(pipe.GetSlippery() == 0 && pipe.GetSticky() == 0)
					{
						pipeLabel = new JLabel("Pipe full leaking");
					}
					else if(pipe.GetSlippery() != 0 && pipe.GetSticky() == 0)
					{
						pipeLabel = new JLabel("Pipe full leaking slippery");
					}
					else if(pipe.GetSlippery() == 0 && pipe.GetSticky() != 0)
					{
						pipeLabel = new JLabel("Pipe full leaking sticky");
					}
				}
			}
			pipeLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
			System.err.println(e);
			return null;
		}
	}
}