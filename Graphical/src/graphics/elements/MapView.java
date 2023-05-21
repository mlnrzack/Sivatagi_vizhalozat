package graphics.elements;

import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

import graphics.*;
import graphics.elements.*;
import graphics.players.*;

/**Ez az osztály felel a játéktér megjelenítéséért.
 */
public class MapView extends JPanel
{
	//private MechanicView currentMechanic = new MechanicView();
	//private SaboteurView currentSaboteur = new SaboteurView();
	private static int SQUARE_SIZE = 100;
	
	private IElement[][] map;
	private Map mapView = new HashMap();
	//valami itt kell csinálni, hogy megjelenjenek az elemek
	
	
	
	public MapView()
	{
		map = new IElement[10][10];
		ArrayList<IElement> consoleMap = GameManager.GetMap();
		drawMap();
	}
	
	public void ReDraw()
	{
		
	}
	
	public void drawMap()
	{
		
	}
	
	/*
	private void MouseListner() 
	{
		 addMouseListener(new MouseAdapter()
		 { 
	         public void mousePressed(MouseEvent me)
	         {
	        	 int tempX = me.getX();
	        	 int tempY = me.getY();
	        	 if((board[tempX][tempY] == null && selected == null) || !enabled)
	        		 return;
	        	 
	        	 else if(selected == null)
	        	 {
	            	 selected = board[tempX][tempY];
	            	 if(selected.getColor() == SideColor.WHITE && !whiteMove) 
	            	 {
	            		 selected = null;
	            		 return;
	            	 }
	            	 
	            	 else if(selected.getColor() == SideColor.BLACK && whiteMove) 
	            	 {
	            		 selected = null;
	            		 return;
	            	 }
	            	 
	                 possibleMoves = preventCheck(selected.GetMoves(board), board, selected);
	                 repaint();
	        	 }
	        	 
	        	 else if(selected != null) 
	        	 {
	        		 if(board[tempX][tempY]!= null && board[tempX][tempY].getColor() == selected.getColor())
	        		 {
	                	 selected = board[tempX][tempY];
	                     possibleMoves = preventCheck(selected.GetMoves(board), board, selected);
	                     repaint();
	        		 }
	        		 
	        		 else
	        		 {	        			 
	        			 checkChess_pieceMove(new Position(tempX, tempY));
	        			 selected = null;
	        			 possibleMoves.clear();
	        			 repaint();
	        		 }	        		 
	        	 }
	         }
	     }); 
		 
		 addMouseMotionListener(new MouseMotionListener() 
		 {
			 @Override
			public void mouseMoved(MouseEvent me) 
			{
	        	 focus.setX(me.getX() / SQUARE_SIZE);
	        	 focus.setY(me.getY() / SQUARE_SIZE);
	        	 repaint();
			}
	
			@Override
			public void mouseDragged(MouseEvent arg0) {}
		 });		
	}*/
}