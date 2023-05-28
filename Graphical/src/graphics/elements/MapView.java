package graphics.elements;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;
import javax.imageio.ImageIO;
import graphics.*;
import graphics.elements.*;
import graphics.players.*;

/**Ez az osztály felel a játéktér megjelenítéséért.
 */
public class MapView extends JPanel
{
	private final Color color = Color.decode("#c9a77d");											//a háttérszín
	private final Color circleColor = Color.decode("#c9a77d");										// a körök színe
	
	private HashMap<Element, ElementView> mapView = new HashMap<Element, ElementView>();			//térkép összekötése a modellel
	
	private MechanicView currentMechanic;															//az aktuális szerelő megjelenítése
	private SaboteurView currentSaboteur;															//az aktuális szabotőr megjenenítése
	private ArrayList<CisternView> cisternsView = new ArrayList<CisternView>();						//a ciszternák megjelenítésere szolgáló lista
	private ArrayList<PipeView> pipesView = new ArrayList<PipeView>();								//a csövek megjelenítésére szolgáló lista
	private ArrayList<PumpView> pumpsView = new ArrayList<PumpView>();								//a pumpák megjelenítésére szolgáló lista
	private ArrayList<WaterSpringView> springsView = new ArrayList<WaterSpringView>();				//a vízforrások megjelenítésére szolgáló lista
	private ArrayList<MechanicView> mechanicsView = new ArrayList<MechanicView>();					//a szerelők megjelenítésére szolgáló lista
	private ArrayList<SaboteurView> saboteursView = new ArrayList<SaboteurView>();					//a szabotőrök megjelenítésére szolgáló lista
	//private Graphics g ;
	private int x, y, imX, imY;
	private boolean dragging;

	public MapView()
	{
		//háttérszín beállítása
		this.setBackground(Color.decode("#c9a77d"));
		//méret beállítása
		Dimension dimension = new Dimension(JFrame.MAXIMIZED_HORIZ, JFrame.MAXIMIZED_VERT);
		this.setMaximumSize(dimension);
		this.setMinimumSize(dimension);
		this.setPreferredSize(dimension);
		
		//térkép felrajzolása
		DrawMap();
		//egértevékenység figyelése
		MouseListener();
	}

	/*
	//egyelőre csak ráfrissít a nézetekre...
	public void ReDraw()
	{
		for (PipeView pipeView : pipesView) 
			this.add(pipeView);

		for (PumpView pumpView : pumpsView)
			this.add(pumpView);

		for (CisternView cisternView : cisternsView)
			this.add(cisternView);

		for (WaterSpringView waterSpringView : springsView)
			this.add(waterSpringView);
	}
	*/
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g.create();
		/*
		for (PipeView pipeView: pipesView){
			Point2D p1 = new Point2D.Double(pipeView.getObj1().getBounds().getCenterX(), pipeView.getObj1().getBounds().getCenterY());
			Point2D p2 = new Point2D.Double(pipeView.getObj2().getBounds().getCenterX(), pipeView.getObj2().getBounds().getCenterY());

			g2.draw(new Line2D.Double(p1,p2));
		}*/
		this.setBackground(color);
		imX = 200;
		imY = 633;
		dragging = false;
		
		for (int i = 0 ; i <GameManager.GetCisterns().size(); i++)
		{
			g2.drawImage(cisternsView.get(i).LoadImage(), cisternsView.get(i).getPosX() ,cisternsView.get(i).getPosY(),
					cisternsView.get(i).getWidth(), cisternsView.get(i).getHeight(), null, null );
			g2.setColor(circleColor);
			g2.drawOval(cisternsView.get(i).getPosX(), cisternsView.get(i).getPosY(), 100, 100);
		}
		
		for (int i = 0; i < GameManager.GetWaterSprings().size();i++)
		{
			/*BufferedImage bufferedImage =  springsView.get(i).LoadImage();
			int height = bufferedImage.getHeight();
			int width = bufferedImage.getWidth();
			int diameter =Math.min(width,height);
			BufferedImage circleBuffer = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = circleBuffer.createGraphics();
			g2d.setClip(new Ellipse2D.Float(springsView.get(i).getPosX(), springsView.get(i).getPosY(), diameter, diameter));
			g2d.drawImage(bufferedImage, springsView.get(i).getPosX(), springsView.get(i).getPosY(), diameter, diameter, null);*/
			//BufferedImage bi = springsView.get(i).LoadImage();
			//Image centerIm = bi.getScaledInstance(150,150,Image.SCALE_SMOOTH);
			/*int height = bi.getHeight();
			int width = bi.getWidth();
			int diameter =Math.min(width,height);*/
			//g2.setClip(springsView.get(i).getPosX()+ diameter /2, springsView.get(i).getPosY() +  diameter /2,width, height);
			g2.drawImage(springsView.get(i).LoadImage(), springsView.get(i).getPosX(), springsView.get(i).getPosY(),
					springsView.get(i).getWidth(), springsView.get(i).getHeight(), null, null);
			g2.setColor(circleColor);
			g2.drawOval(springsView.get(i).getPosX(), springsView.get(i).getPosY(), 100, 100);
		}
		for(int i = 0; i < (GameManager.GetPumps().size()); i++)
		{
			g2.drawImage(pumpsView.get(i).LoadImage(), pumpsView.get(i).getPosX(), pumpsView.get(i).getPosY(), 90, 90, null, null );
		}

		/*int centerXspring0 = (int)(springsView.get(0).getPosX() + springsView.get(0).getWidth() / 2d);
		int centerXspring1 = (int)(springsView.get(1).getPosX() + springsView.get(1).getWidth() / 2d);
		int centerYspring0 = (int)(springsView.get(0).getPosY() + springsView.get(0).getHeight() / 2d);
		int centerYspring1 = (int)(springsView.get(1).getPosY() + springsView.get(1).getHeight() / 2d);
		int centerXpump0 = (int)(pumpsView.get(0).getPosX() + pumpsView.get(0).getHeight() / 2d);
		int centerYpump0 = (int)(pumpsView.get(0).getPosY() + pumpsView.get(0).getWidth() / 2d);
*/
		g2.setColor(color.BLACK);
		g2.drawLine( springsView.get(0).getCenterX(), springsView.get(0).getCenterY(), pumpsView.get(0).getCenterX(), pumpsView.get(0).getCenterY());
		g2.drawLine( springsView.get(0).getCenterX(), springsView.get(0).getCenterY(), pumpsView.get(1).getCenterX(), pumpsView.get(1).getCenterY());
		g2.drawLine( springsView.get(1).getCenterX(), springsView.get(1).getCenterY(), pumpsView.get(1).getCenterX(), pumpsView.get(1).getCenterY());
		g2.drawLine( pumpsView.get(1).getCenterX(), pumpsView.get(1).getCenterY(), pumpsView.get(2).getCenterX(), pumpsView.get(2).getCenterY());
		g2.drawLine( pumpsView.get(0).getCenterX(), pumpsView.get(0).getCenterY(), pumpsView.get(3).getCenterX(), pumpsView.get(3).getCenterY());
		g2.drawLine( pumpsView.get(0).getCenterX(), pumpsView.get(0).getCenterY(), pumpsView.get(2).getCenterX(), pumpsView.get(2).getCenterY());
		g2.drawLine( pumpsView.get(2).getCenterX(), pumpsView.get(2).getCenterY(), pumpsView.get(6).getCenterX(), pumpsView.get(6).getCenterY());
		g2.drawLine( pumpsView.get(2).getCenterX(), pumpsView.get(2).getCenterY(), pumpsView.get(5).getCenterX(), pumpsView.get(5).getCenterY());
		g2.drawLine( pumpsView.get(3).getCenterX(), pumpsView.get(3).getCenterY(), pumpsView.get(5).getCenterX(), pumpsView.get(5).getCenterY());
		g2.drawLine( pumpsView.get(3).getCenterX(), pumpsView.get(3).getCenterY(), pumpsView.get(4).getCenterX(), pumpsView.get(4).getCenterY());
		g2.drawLine( pumpsView.get(6).getCenterX(), pumpsView.get(6).getCenterY(), pumpsView.get(8).getCenterX(), pumpsView.get(8).getCenterY());
		g2.drawLine( pumpsView.get(5).getCenterX(), pumpsView.get(5).getCenterY(), pumpsView.get(8).getCenterX(), pumpsView.get(8).getCenterY());
		g2.drawLine( pumpsView.get(5).getCenterX(), pumpsView.get(5).getCenterY(), pumpsView.get(7).getCenterX(), pumpsView.get(7).getCenterY());
		g2.drawLine( pumpsView.get(4).getCenterX(), pumpsView.get(4).getCenterY(), pumpsView.get(7).getCenterX(), pumpsView.get(7).getCenterY());
		g2.drawLine( pumpsView.get(8).getCenterX(), pumpsView.get(8).getCenterY(), pumpsView.get(9).getCenterX(), pumpsView.get(9).getCenterY());
		g2.drawLine( pumpsView.get(8).getCenterX(), pumpsView.get(8).getCenterY(), pumpsView.get(10).getCenterX(), pumpsView.get(10).getCenterY());
		g2.drawLine( pumpsView.get(7).getCenterX(), pumpsView.get(7).getCenterY(), cisternsView.get(2).getCenterX(), cisternsView.get(2).getCenterY());
		g2.drawLine( pumpsView.get(9).getCenterX(), pumpsView.get(9).getCenterY(), cisternsView.get(1).getCenterX(), cisternsView.get(1).getCenterY());
		g2.drawLine( pumpsView.get(10).getCenterX(), pumpsView.get(10).getCenterY(), cisternsView.get(0).getCenterX(), cisternsView.get(0).getCenterY());
		
		//g2.drawImage(cisternsView.get(1).getImageOfCistern(), imX +450,imY, 100, 100, null, null );
		//g2.drawLine(cisternsView.get(0).getCenterX(),cisternsView.get(0).getCenterY(),cisternsView.get(1).getCenterX(), cisternsView.get(1).getCenterY());
		//g2.drawOval(imX+460, imY+20, 82, 82);
		g2.dispose();
	}
	//egyelőre csak felhelyezi a modellben inicializált elemeket, de nem adott helyre és nem is adott szomszédokhoz
	public void DrawMap()
	{
		this.setPreferredSize(new Dimension(1000, 900));
		setBackground(color);

		PumpView puV;
		for(int i = 0; i < GameManager.GetPumps().size(); i++)
		{

			puV= new PumpView(220, 175+(i*45),90,90, i);
			pumpsView.add(puV);
			i++;

			puV = new PumpView(470, 175+((i-1)*45), 90, 90, i);
			pumpsView.add(puV);
			if (i == 7) break;
		}

		pumpsView.add(new PumpView(100, 535, 90, 90, 8 ));
		pumpsView.add(new PumpView(350, 535, 90, 90, 9 ));
		pumpsView.add(new PumpView(600, 535, 90, 90, 10 ));


		for(int i = 0; i < GameManager.GetCisterns().size(); i++)
		{
			CisternView cV = new CisternView(200+(i*150), 633, 100, 100,  i);
			cisternsView.add(cV);
		}

		for(int i = 0; i < GameManager.GetWaterSprings().size(); i++)
		{
			WaterSpringView sV = new WaterSpringView(200+(i*250), 30,100, 100, i);
			springsView.add(sV);
		}

		for(int i = 0; i < GameManager.GetMechanics().size(); i++)
		{
			GameManager.GetMechanics().get(i).GetCurrentPosition();
			MechanicView mV = new MechanicView(null, i);
			mechanicsView.add(mV);
		}

		for(int i = 0; i < GameManager.GetSaboteurs().size(); i++)
		{
			GameManager.GetSaboteurs().get(i).GetCurrentPosition();
			SaboteurView sV = new SaboteurView(null, i);
			saboteursView.add(sV);
		}

	}

	private void MouseListener() 
	{
		final PumpView[] pm = new PumpView[1];
		final int[] selectedPump = new int[1];

		this.addMouseListener(new MouseAdapter()
		{
			@Override
	        public void mousePressed(MouseEvent me)
	        {
				for (int i = 0 ; i < pumpsView.size(); i++)
				{
					if (me.getX() >= pumpsView.get(i).getPosX() && me.getX()< pumpsView.get(i).getPosX() + pumpsView.get(i).getWidth()
							&& me.getY()>=pumpsView.get(i).getPosY() && me.getY() < pumpsView.get(i).getPosY()+pumpsView.get(i).getHeight())
					{
						dragging = true;
						pm[0] = pumpsView.get(i);
						selectedPump[0] = i ;
					}
				}
	        }
			 
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				dragging = false;
				pm[0] = null;
				repaint();
			}
			 
			@Override
			public void mouseDragged(MouseEvent e) { }
		}); 

		this.addMouseMotionListener(new MouseMotionListener()
		{
			int nx;
			int ny;
			@Override
			public void mouseDragged(MouseEvent e) 
			{
				if (pm[0] != null)
				{
					nx = e.getX();
					ny = e.getY();

					pumpsView.get(selectedPump[0]).setCenterX(nx);
					pumpsView.get(selectedPump[0]).setCenterY(ny);
					
					update(getGraphics());
				}
				/* if (!dragging ){
					 pumpsView.get(selectedPump[0]).setPosX(nx);
					 pumpsView.get(selectedPump[0]).setPosY(ny);
					 repaint();
					 System.out.println("!dragging");
				 }*/
			}

			@Override
			public void mouseMoved(MouseEvent me) 
			{
	        	 repaint();
			}
		});
	}
}