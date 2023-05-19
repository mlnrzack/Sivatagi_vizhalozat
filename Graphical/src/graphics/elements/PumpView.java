package graphics.elements;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PumpView extends ElementView
{
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	JLabel pumpLabel;
	
	public PumpView()
	{
		//TODO
	}
	
	public void LoadImage()
	{
		try
		{
			ImageIcon  pump = new ImageIcon(this.getClass().getResource("/pump.png"));
			pumpLabel.setIcon(pump);
			ImageIcon brokenPump = new ImageIcon(this.getClass().getResource("/pump_broken.png"));
			pumpLabel.setIcon(brokenPump);
			//fel kell rakni a gamePanel-ra.
		}
		
		catch(Exception e)
		{
			pumpLabel = new JLabel("Pump");
			pumpLabel = new JLabel("Broken Pump");
			pumpLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
			pumpLabel.setHorizontalAlignment(SwingConstants.LEFT);
			//fel kell rakni a gamePanel-ra.
		}
	}

}