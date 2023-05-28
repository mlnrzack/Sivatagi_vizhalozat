package graphics.elements;

import javax.swing.*;

import game.interfaces.*;
import graphics.Paintable;

import java.awt.*;

public abstract class ElementView extends JPanel
{
	protected int posX;
	protected int posY;

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	protected int width;
	protected int height;
	
	//itt kell egy position változó, ami a grafika helyét tárolja...
	//valahogy összekötni a modellből egy objemktummal...
	
	public ElementView()
	{
		//TODO
	}
	
	public abstract Image LoadImage();

	public int getCenterX(){
		return (posX+getWidth()/2);
	}
	public int getCenterY(){
		return (posY+getHeight()/2);
	}
	public int getPosX(){
		return posX;
	}

	public int getPosY(){
		return posY;
	}

	public void setCenterX(int i)
	{
		posX = i - getWidth()/2;
	}

	public void setCenterY(int i)
	{
		posY = i - getHeight()/2;
	}
	public void setPosX(int i){
		posX = i;
	}
	public void setPosY(int i){
		posY = i;
	}


}
