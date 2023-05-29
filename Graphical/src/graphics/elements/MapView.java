package graphics.elements;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
//import java.util.HashMap;

import javax.swing.*;

import game.*;
import game.IO.DebugLog;
import game.elements.Element;
import game.interfaces.IElement;
import graphics.players.*;

import static game.GameManager.GetMechanics;

/**Ez az osztály felel a játéktér megjelenítéséért.
 */
public class MapView extends JPanel
{
	private BufferedImage mapImage;																	//háttérkép
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();								//a képernyő mérete
	int screenHeight = screenSize.height;															//képernyő magassága
	int screenWidth = screenSize.width;																//képernyő szélessége
	
	private final Color color = Color.decode("#c9a77d");											//a háttérszín
	private final Color circleColor = Color.decode("#94744d");										// a körök színe
	private final Color currentColor = Color.decode("#ffffff");                                     //az aktuális játékost jelölő szín
	
	private ArrayList<ElementView> mapView = new ArrayList<ElementView>();                          //térkép megjelnítéseére szolgáló lista
	private MechanicView currentMechanic;															//az aktuális szerelő megjelenítése
	private SaboteurView currentSaboteur;															//az aktuális szabotőr megjenenítése
	private ArrayList<PipeView> pipesView = new ArrayList<PipeView>();								//a csövek megjelenítésére szolgáló lista
	private ArrayList<ElementView> activesView = new ArrayList<ElementView>();						//
	private ArrayList<CisternView> cisternsView = new ArrayList<CisternView>();						//a ciszternák megjelenítésere szolgáló lista
	private ArrayList<PumpView> pumpsView = new ArrayList<PumpView>();								//a pumpák megjelenítésére szolgáló lista
	private ArrayList<WaterSpringView> springsView = new ArrayList<WaterSpringView>();				//a vízforrások megjelenítésére szolgáló lista
	private ArrayList<MechanicView> mechanicsView = new ArrayList<MechanicView>();					//a szerelők megjelenítésére szolgáló lista
	private ArrayList<SaboteurView> saboteursView = new ArrayList<SaboteurView>();					//a szabotőrök megjelenítésére szolgáló lista

	private int x, y, imX, imY;
	private boolean dragging;

	public MapView()
	{
		//térkép képének létrehozása
		mapImage = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		//háttérszín beállítása
		this.setBackground(Color.decode("#c9a77d"));
		//méret beállítása
		Dimension dimension = new Dimension((int)(screenWidth * 0.7), 1000);
		this.setMaximumSize(dimension);
		this.setMinimumSize(dimension);
		this.setPreferredSize(dimension);
		//térkép felrajzolása
		DrawMap();
		//egértevékenység figyelése
		MouseListener();
	}

	//public ElementView[] GetNeighbourViews()
	{
		
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
		
		g2.drawImage(mapImage, 0, 0, (int)(screenWidth * 0.7), screenHeight, null);
		
		this.setBackground(color);
		imX = 200;
		imY = 633;

		int stringX;
		int stringY;
		g2.setColor(color.BLACK);
		for (int i = 0; i < pipesView.size(); i++) 
		{
			if (pipesView.get(i).GetNeighbours()[0] != null && pipesView.get(i).GetNeighbours()[1] != null) 
			{
				// Draw the line between the two elements
				g2.drawLine(pipesView.get(i).GetNeighbours()[0].getCenterX(), pipesView.get(i).GetNeighbours()[0].getCenterY(),
					pipesView.get(i).GetNeighbours()[1].getCenterX(), pipesView.get(i).GetNeighbours()[1].getCenterY());

			// Set the dimensions of the pipe
			pipesView.get(i).SetDimensions();

			// Determine the angle of the line
			double x = Math.abs(pipesView.get(i).GetNeighbours()[0].posX - pipesView.get(i).GetNeighbours()[1].posX);
			double y = Math.abs(pipesView.get(i).GetNeighbours()[0].posY - pipesView.get(i).GetNeighbours()[1].posY);
			double angle = Math.atan2(pipesView.get(i).GetNeighbours()[1].getCenterY() - pipesView.get(i).GetNeighbours()[0].getCenterY(),
					pipesView.get(i).GetNeighbours()[1].getCenterX() - pipesView.get(i).GetNeighbours()[0].getCenterX());

			// Calculate the center point of the line
			Point center = calculateCenter(pipesView.get(i).GetNeighbours()[0].getCenterX(), pipesView.get(i).GetNeighbours()[0].getCenterY(),
					pipesView.get(i).GetNeighbours()[1].getCenterX(), pipesView.get(i).GetNeighbours()[1].getCenterY());

			// Save the current transform
			AffineTransform oldTransform = g2.getTransform();

			// Apply the rotation transformation
			g2.rotate(angle, center.x, center.y);

			// Calculate the new position for drawing the image
			int imageX = (int) (center.x - pipesView.get(i).getWidth() / 2);
			int imageY = (int) (center.y - pipesView.get(i).getHeight() / 2);

			// Draw the image on top of the line
			g2.drawImage(pipesView.get(i).LoadImage(), imageX, imageY,
					pipesView.get(i).getWidth(), pipesView.get(i).getHeight(), null);

			 stringX = imageX + pipesView.get(i).getWidth() / 2;
			 stringY = imageY + pipesView.get(i).getHeight() / 2;

			// Set the font and color for the string
			g2.setFont(new Font("Arial",Font.PLAIN, 18));

			// Draw the string in the middle of the image
			g2.drawString(pipesView.get(i).GetPipe().GetId(), stringX, stringY+8);

			// Restore the original transform
			g2.setTransform(oldTransform);
		}
	}
		g2.setFont(new Font("Arial",Font.PLAIN, 12));
		for(int i = 0; i < pumpsView.size(); i++)
		{
			g2.setColor(color);
			g2.fillOval(pumpsView.get(i).getPosX() - 7, pumpsView.get(i).getPosY(), 100, 100);
			g2.setColor(circleColor);
			g2.drawOval(pumpsView.get(i).getPosX() - 7, pumpsView.get(i).getPosY(), 100, 100);
			g2.drawImage(pumpsView.get(i).LoadImage(), pumpsView.get(i).getPosX(), pumpsView.get(i).getPosY(),
					pumpsView.get(i).getWidth(), pumpsView.get(i).getHeight(), null, null );
			g2.setColor(Color.BLACK);
			g2.drawString(pumpsView.get(i).GetPump().GetId(), pumpsView.get(i).getPosX() + 22, pumpsView.get(i).getPosY() + 85);

		}

		for (int i = 0 ; i < cisternsView.size(); i++)
		{
			g2.setColor(color);
			g2.fillOval(cisternsView.get(i).getPosX() + 3, cisternsView.get(i).getPosY() + 10, 100, 100);
			g2.setColor(circleColor);
			g2.drawOval(cisternsView.get(i).getPosX() + 3, cisternsView.get(i).getPosY() + 10, 100, 100);
			g2.drawImage(cisternsView.get(i).LoadImage(), cisternsView.get(i).getPosX() ,cisternsView.get(i).getPosY(),
					cisternsView.get(i).getWidth(), cisternsView.get(i).getHeight(), null, null );
			g2.setColor(Color.BLACK);
			g2.drawString(cisternsView.get(i).GetCistern().GetId(), cisternsView.get(i).getPosX() + 30, cisternsView.get(i).getPosY() + 100);
		}
		
		for (int i = 0; i < springsView.size();i++)
		{
			g2.setColor(color);
			g2.fillOval(springsView.get(i).getPosX() + 25, springsView.get(i).getPosY() + 20, 100, 100);
			g2.setColor(circleColor);
			g2.drawOval(springsView.get(i).getPosX() + 25, springsView.get(i).getPosY() + 20, 100, 100);
			g2.drawImage(springsView.get(i).LoadImage(), springsView.get(i).getPosX(), springsView.get(i).getPosY(),
					springsView.get(i).getWidth(), springsView.get(i).getHeight(), null, null);
			g2.setColor(Color.BLACK);
			g2.drawString(springsView.get(i).GetSpring().GetId(), springsView.get(i).getPosX() + 55, springsView.get(i).getPosY() + 115);
		}

		for(int i = 0; i < mechanicsView.size(); i++)
		{
			g2.setColor(circleColor);
			if (mechanicsView.get(i) == currentMechanic)
				g2.setColor(currentColor);

			g2.drawOval(mechanicsView.get(i).getPos().getCenterX() + 3, mechanicsView.get(i).getPos().getCenterY() + 10, 80, 80);

			g2.drawImage(mechanicsView.get(i).LoadImage(), mechanicsView.get(i).getPos().getCenterX() + 20 ,mechanicsView.get(i).getPos().getCenterY() + 20,
					mechanicsView.get(i).getWidth(), mechanicsView.get(i).getHeight(), null, null );
			g2.setColor(Color.BLACK);
			g2.drawString(mechanicsView.get(i).getMechanic().GetName(), mechanicsView.get(i).getPos().getCenterX() + 10, mechanicsView.get(i).getPos().getCenterY()  + 100);

		}

		for(int i = 0; i < saboteursView.size(); i++)
		{
			g2.setColor(circleColor);
			if (saboteursView.get(i) == currentSaboteur)
				g2.setColor(currentColor);
			
			g2.drawOval(saboteursView.get(i).getPos().getCenterX() + 3, saboteursView.get(i).getPos().getCenterY() + 10, 80, 80);

			g2.drawImage(saboteursView.get(i).LoadImage(), saboteursView.get(i).getPos().getCenterX() + 10, saboteursView.get(i).getPos().getCenterY() + 15,
					saboteursView.get(i).getWidth(), saboteursView.get(i).getHeight(), null, null );
			g2.setColor(Color.BLACK);
			g2.drawString(saboteursView.get(i).getSaboteur().GetName(), saboteursView.get(i).getPos().getCenterX() + 5, saboteursView.get(i).getPos().getCenterY()  + 100);

		}

		g2.dispose();
	}

	//egyelőre csak felhelyezi a modellben inicializált elemeket, de nem adott helyre és nem is adott szomszédokhoz
	public void DrawMap()
	{
		Graphics2D g2d = (Graphics2D) mapImage.getGraphics();
		g2d.setColor(color);
		g2d.fillRect(0, 0, screenWidth, screenHeight);
		
		for(int i = 0; i < GameManager.GetWaterSprings().size(); i++)
		{
			WaterSpringView sV = new WaterSpringView((int)(screenWidth * 0.05) + (i * (int)(screenWidth * 0.15)),(int)(screenHeight * 0.02), 150, 150, i);
			springsView.add(sV);
			activesView.add(sV);
			mapView.add(sV);
		}
		
		PumpView puV;
		for(int i = 0; i < GameManager.GetPumps().size(); i++)
		{
			puV = new PumpView((int)(screenWidth * 0.1) + (i % 5 * 55), 195 + (i * 70), 90, 90, i);
			pumpsView.add(puV);
			activesView.add(puV);
			mapView.add(puV);
			DebugLog.WriteDebugLog(GameManager.GetPumps().get(i).GetId());
			DebugLog.WriteDebugLog("CenterX  "+Integer.toString(puV.getCenterX()));
			DebugLog.WriteDebugLog("CenterY  "+Integer.toString(puV.getCenterY()));
			DebugLog.WriteDebugLog("PosX "+Integer.toString(puV.getPosX()));
			DebugLog.WriteDebugLog("PosY "+Integer.toString(puV.getPosY()));
			if (i == 10) break;
			i++;

			puV = new PumpView((int)(screenWidth * 0.3) + (i % 4 * 45), 195 + ((i - 1) * 70), 90, 90, i);
			DebugLog.WriteDebugLog(GameManager.GetPumps().get(i).GetId());
			DebugLog.WriteDebugLog("CenterX  "+ puV.getCenterX());
			DebugLog.WriteDebugLog("CenterY  "+Integer.toString(puV.getCenterY()));
			DebugLog.WriteDebugLog("PosX "+Integer.toString(puV.getPosX()));
			DebugLog.WriteDebugLog("PosY "+Integer.toString(puV.getPosY()));
			pumpsView.add(puV);
			activesView.add(puV);
			mapView.add(puV);

		}

		for(int i = GameManager.GetCisterns().size() - 1; i >= 0; i--)
		{
			CisternView cV = new CisternView((int)(screenWidth * 0.35) + (i * (int)(screenWidth * 0.10)),
					(int)(screenHeight * 0.85) - (4 % (i + 1)  * (int)(screenHeight * 0.15)), 100, 100,  i);
			cisternsView.add(cV);
			activesView.add(cV);
			mapView.add(cV);
		}
		
		for(int i = 0; i < GameManager.GetPipes().size(); i++) 
		{
			PipeView piV = new PipeView(i);
			pipesView.add(piV);
			mapView.add(piV);
			
			ElementView[] neighbours = new ElementView[2];
			neighbours[0] = null;
			neighbours[1] = null;
			int l = 0;
			
			for(int j = 0; j < GameManager.GetPipes().get(i).GetNeighbours().size(); j++)
			{
				for(int k = 0; k < activesView.size(); k++)
				{
					if(GameManager.GetPipes().get(i).GetNeighbours().get(j).GetId().equals(activesView.get(k).GetElement().GetId()))
					{
						neighbours[l] = activesView.get(k);
						l++;
					}
					if(l == 2)
						break;
				}
			}
			//szomszédok átadása
			piV.SetNeighbours(neighbours);
		}
		
		for(int i = 0; i < GameManager.GetMechanics().size(); i++)
		{
			String ie = GameManager.GetMechanics().get(i).GetCurrentPosition().GetId();
			for (int k = 0; k < mapView.size(); k++)
			{
				if(ie.equals(mapView.get(k).GetElement().GetId()))
				{
					MechanicView meV = new MechanicView(mapView.get(k),60,60, i);
					mechanicsView.add(meV);
					
					//if(GameManager.GetCurrentMechanic().GetName() == GameManager.GetMechanics().get(i).GetName())
						currentMechanic = meV;
				}
			}
		}

		for(int i = 0; i < GameManager.GetSaboteurs().size(); i++)
		{
			String ie = GameManager.GetSaboteurs().get(i).GetCurrentPosition().GetId();
			for (int k = 0; k < mapView.size(); k++)
			{
				if(ie.equals(mapView.get(k).GetElement().GetId()))
				{
					SaboteurView saV = new SaboteurView(mapView.get(k), 60,60, i);
					saboteursView.add(saV);
					
					//if(GameManager.GetCurrentSaboteur().GetName() == GameManager.GetSaboteurs().get(i).GetName())
						currentSaboteur = saV;
				}
			}
		}
	}
	
	public static Point calculateCenter(int x1, int y1, int x2, int y2) 
	{
		int centerX = (x1 + x2) / 2;
		int centerY = (y1 + y2) / 2;
		return new Point(centerX, centerY);
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
					if (me.getX() >= pumpsView.get(i).getPosX() && me.getX() < pumpsView.get(i).getPosX() + pumpsView.get(i).getWidth()
							&& me.getY()>=pumpsView.get(i).getPosY() && me.getY() < pumpsView.get(i).getPosY()+ pumpsView.get(i).getHeight())
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
					
					repaint();
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