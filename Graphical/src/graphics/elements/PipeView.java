package graphics.elements;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
			//if(ha üres a cső)// Pipe.GetWaterInside() == 0
			ImageIcon  emptyPipe = new ImageIcon(this.getClass().getResource("/pipe_empty.png"));
			pipeLabel.setIcon(emptyPipe);
			//else if(ha van víz a csőben) //Pipe.GetWaterInside() == 1
			ImageIcon fullPipe = new ImageIcon(this.getClass().getResource("/pipe_full.png"));
			pipeLabel.setIcon(fullPipe);
			//else if(ha lyukas a cső) //Pipe.GetLeaking()
			ImageIcon leakingPipe = new ImageIcon(this.getClass().getResource("/pipe_leaking.png"));
			pipeLabel.setIcon(leakingPipe);
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