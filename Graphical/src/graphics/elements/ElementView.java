package graphics.elements;

import javax.swing.JComponent;

import game.interfaces.*;

public abstract class ElementView// implements IElement
{
	protected int posX;
	protected int posY;
	
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	
	public ElementView()
	{
		//TODO
	}
	
	public abstract void LoadImage();
}
