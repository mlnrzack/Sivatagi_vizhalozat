package graphics.elements;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CisternView extends ElementView// implements JComponent
{
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	JLabel cisternLabel;
	
	public CisternView()
	{
		//TODO
		//LoadImage();
	}
	
	public void LoadImage()
	{
		try
		{
			ImageIcon cistern = new ImageIcon(this.getClass().getResource("/cistern.png"));
			cisternLabel.setIcon(cistern);
			//fel kell rakni a gamePanel-ra.
		}
		
		catch(Exception e)
		{
			cisternLabel = new JLabel("Cistern");
			cisternLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 28));
			cisternLabel.setHorizontalAlignment(SwingConstants.LEFT);
			//fel kell rakni a gamePanel-ra.
		}
	}
}