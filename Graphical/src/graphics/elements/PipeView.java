package graphics.elements;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.elements.Pipe;

public class PipeView extends ElementView
{
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	JLabel pipeLabel;
	
	public PipeView()
	{
		//TODO
	}
	
	public void LoadImage()
	{
		try
		{
			//if(Pipe.GetWaterInside() == 0)
			{
				//if(!Pipe.getLeaking())
				{
					//if(!Pipe.GetSlippery() && !Pipe.GetSticky())
					{
						ImageIcon  emptyPipe = new ImageIcon(this.getClass().getResource("/pipe_empty.png"));
						pipeLabel.setIcon(emptyPipe);
					}
					//else if(Pipe.GetSlippery() && !Pipe.GetSticky())
					{
						ImageIcon  emptyPipe = new ImageIcon(this.getClass().getResource("/pipe_empty_slippery.png"));
						pipeLabel.setIcon(emptyPipe);
					}
					//else if(!Pipe.GetSlippery() && Pipe.GetSticky())
					{
						ImageIcon  emptyPipe = new ImageIcon(this.getClass().getResource("/pipe_empty_sticky.png"));
						pipeLabel.setIcon(emptyPipe);
					}
				}
				
				//else if(Pipe.getLeaking())
				{
					//if(!Pipe.GetSlippery() && !Pipe.GetSticky())
					{
						ImageIcon  emptyPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_empty.png"));
						pipeLabel.setIcon(emptyPipe);
					}
					//else if(Pipe.GetSlippery() && !Pipe.GetSticky())
					{
						ImageIcon  emptyPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_empty_slippery.png"));
						pipeLabel.setIcon(emptyPipe);
					}
					//else if(!Pipe.GetSlippery() && Pipe.GetSticky())
					{
						ImageIcon  emptyPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_empty_sticky.png"));
						pipeLabel.setIcon(emptyPipe);
					}
				}
				
			}			
			//else if(Pipe.GetWaterInside() == 1){
			{
				//if(!Pipe.getLeaking())
				{
					//if(!Pipe.GetSlippery() && !Pipe.GetSticky())
					{
						ImageIcon  emptyPipe = new ImageIcon(this.getClass().getResource("/pipe_full.png"));
						pipeLabel.setIcon(emptyPipe);
					}
					//else if(Pipe.GetSlippery() && !Pipe.GetSticky())
					{
						ImageIcon  emptyPipe = new ImageIcon(this.getClass().getResource("/pipe_full_slippery.png"));
						pipeLabel.setIcon(emptyPipe);
					}
					//else if(!Pipe.GetSlippery() && Pipe.GetSticky())
					{
						ImageIcon  emptyPipe = new ImageIcon(this.getClass().getResource("/pipe_full_sticky.png"));
						pipeLabel.setIcon(emptyPipe);
					}
				}
				
				//else if(Pipe.getLeaking())
				{
					//if(!Pipe.GetSlippery() && !Pipe.GetSticky())
					{
						ImageIcon  emptyPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_full.png"));
						pipeLabel.setIcon(emptyPipe);
					}
					//else if(Pipe.GetSlippery() && !Pipe.GetSticky())
					{
						ImageIcon  emptyPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_full_slippery.png"));
						pipeLabel.setIcon(emptyPipe);
					}
					//else if(!Pipe.GetSlippery() && Pipe.GetSticky())
					{
						ImageIcon  emptyPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking_full_sticky.png"));
						pipeLabel.setIcon(emptyPipe);
					}
				}
			}
			//fel kell rakni a gamePanel-ra.
		}
		
		catch(Exception e)
		{
			pipeLabel = new JLabel("Pipe");
			pipeLabel = new JLabel("Pipe full of water");
			pipeLabel = new JLabel("Leaking Pipe");
			pipeLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
			pipeLabel.setHorizontalAlignment(SwingConstants.LEFT);
			//fel kell rakni a gamePanel-ra.
		}
	}
}