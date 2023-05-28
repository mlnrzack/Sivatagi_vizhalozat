package graphics.elements;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
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
	private BufferedImage mapImage;																	//háttérkép
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();								//a képernyő mérete
	int screenHeight = screenSize.height;															//képernyő magassága
	int screenWidth = screenSize.width;																//képernyő szélessége
	
	private final Color color = Color.decode("#c9a77d");											//a háttérszín
	private final Color circleColor = Color.decode("#94744d");										// a körök színe
	
	private HashMap<Element, ElementView> mapView = new HashMap<Element, ElementView>();			//térkép összekötése a modellel
	
	private MechanicView currentMechanic;															//az aktuális szerelő megjelenítése
	private SaboteurView currentSaboteur;															//az aktuális szabotőr megjenenítése
	private ArrayList<PipeView> pipesView = new ArrayList<PipeView>();								//a csövek megjelenítésére szolgáló lista
	private ArrayList<ElementView> activesView = new ArrayList<ElementView>();						//
	private ArrayList<CisternView> cisternsView = new ArrayList<CisternView>();						//a ciszternák megjelenítésere szolgáló lista
	private ArrayList<PumpView> pumpsView = new ArrayList<PumpView>();								//a pumpák megjelenítésére szolgáló lista
	private ArrayList<WaterSpringView> springsView = new ArrayList<WaterSpringView>();				//a vízforrások megjelenítésére szolgáló lista
	private ArrayList<MechanicView> mechanicsView = new ArrayList<MechanicView>();					//a szerelők megjelenítésére szolgáló lista
	private ArrayList<SaboteurView> saboteursView = new ArrayList<SaboteurView>();					//a szabotőrök megjelenítésére szolgáló lista
	//private Graphics g ;
	private int x, y, imX, imY;
	private boolean dragging;

	public MapView()
	{
		//térkép képének létrehozása
		mapImage = new BufferedImage(1420, 900, BufferedImage.TYPE_INT_ARGB);
		//háttérszín beállítása
		this.setBackground(Color.decode("#c9a77d"));
		//méret beállítása
		Dimension dimension = new Dimension(1420, JFrame.MAXIMIZED_VERT);
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
		
		g2.drawImage(mapImage, 0, 0, 1420, 1100, null);
		
		this.setBackground(color);
		imX = 200;
		imY = 633;
		dragging = false;
		
		g2.setColor(color.BLACK);
		g2.drawLine(springsView.get(0).getCenterX(), springsView.get(0).getCenterY(), pumpsView.get(0).getCenterX(), pumpsView.get(0).getCenterY());
		g2.drawLine(springsView.get(0).getCenterX(), springsView.get(0).getCenterY(), pumpsView.get(1).getCenterX(), pumpsView.get(1).getCenterY());
		g2.drawLine(springsView.get(1).getCenterX(), springsView.get(1).getCenterY(), pumpsView.get(1).getCenterX(), pumpsView.get(1).getCenterY());
		g2.drawLine(pumpsView.get(1).getCenterX(), pumpsView.get(1).getCenterY(), pumpsView.get(2).getCenterX(), pumpsView.get(2).getCenterY());
		g2.drawLine(pumpsView.get(0).getCenterX(), pumpsView.get(0).getCenterY(), pumpsView.get(3).getCenterX(), pumpsView.get(3).getCenterY());
		g2.drawLine(pumpsView.get(0).getCenterX(), pumpsView.get(0).getCenterY(), pumpsView.get(2).getCenterX(), pumpsView.get(2).getCenterY());
		g2.drawLine(pumpsView.get(2).getCenterX(), pumpsView.get(2).getCenterY(), pumpsView.get(6).getCenterX(), pumpsView.get(6).getCenterY());
		g2.drawLine(pumpsView.get(2).getCenterX(), pumpsView.get(2).getCenterY(), pumpsView.get(5).getCenterX(), pumpsView.get(5).getCenterY());
		g2.drawLine(pumpsView.get(3).getCenterX(), pumpsView.get(3).getCenterY(), pumpsView.get(5).getCenterX(), pumpsView.get(5).getCenterY());
		g2.drawLine(pumpsView.get(3).getCenterX(), pumpsView.get(3).getCenterY(), pumpsView.get(4).getCenterX(), pumpsView.get(4).getCenterY());
		g2.drawLine(pumpsView.get(6).getCenterX(), pumpsView.get(6).getCenterY(), pumpsView.get(8).getCenterX(), pumpsView.get(8).getCenterY());
		g2.drawLine(pumpsView.get(5).getCenterX(), pumpsView.get(5).getCenterY(), pumpsView.get(8).getCenterX(), pumpsView.get(8).getCenterY());
		g2.drawLine(pumpsView.get(5).getCenterX(), pumpsView.get(5).getCenterY(), pumpsView.get(7).getCenterX(), pumpsView.get(7).getCenterY());
		g2.drawLine(pumpsView.get(4).getCenterX(), pumpsView.get(4).getCenterY(), pumpsView.get(7).getCenterX(), pumpsView.get(7).getCenterY());
		g2.drawLine(pumpsView.get(8).getCenterX(), pumpsView.get(8).getCenterY(), pumpsView.get(9).getCenterX(), pumpsView.get(9).getCenterY());
		g2.drawLine(pumpsView.get(8).getCenterX(), pumpsView.get(8).getCenterY(), pumpsView.get(10).getCenterX(), pumpsView.get(10).getCenterY());
		g2.drawLine(pumpsView.get(7).getCenterX(), pumpsView.get(7).getCenterY(), cisternsView.get(2).getCenterX(), cisternsView.get(2).getCenterY());
		g2.drawLine(pumpsView.get(9).getCenterX(), pumpsView.get(9).getCenterY(), cisternsView.get(1).getCenterX(), cisternsView.get(1).getCenterY());
		g2.drawLine(pumpsView.get(10).getCenterX(), pumpsView.get(10).getCenterY(), cisternsView.get(0).getCenterX(), cisternsView.get(0).getCenterY());
		
		for (int i = 0 ; i < GameManager.GetCisterns().size(); i++)
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
		
		for (int i = 0; i < GameManager.GetWaterSprings().size();i++)
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

		for(int i = 0; i < GameManager.GetPumps().size(); i++)
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
		/*
		for(int i = 0; i < GameManager.GetPipes().size(); i++)
		{
			g2.drawImage(pipesView.get(i).LoadImage(), pipesView.get(i).GetNeighbours()[0].getCenterX(), pipesView.get(i).GetNeighbours()[0].getCenterY(),
					pipesView.get(i).getWidth(), pipesView.get(i).getHeight(), null, null );
		}
		*/
		g2.dispose();
	}

	//egyelőre csak felhelyezi a modellben inicializált elemeket, de nem adott helyre és nem is adott szomszédokhoz
	public void DrawMap()
	{
		Graphics2D g2d = (Graphics2D) mapImage.getGraphics();
		g2d.setColor(color);
		g2d.fillRect(0, 0, 1420, 1100);
		
		for(int i = 0; i < GameManager.GetWaterSprings().size(); i++)
		{
			WaterSpringView sV = new WaterSpringView(200 + (i * 250), 30, 150, 150, i);
			springsView.add(sV);
			activesView.add(sV);
		}
		
		PumpView puV;
		for(int i = 0; i < GameManager.GetPumps().size(); i++)
		{

			puV = new PumpView(220, 195 + (i * 70), 90, 90, i);
			pumpsView.add(puV);
			activesView.add(puV);
			i++;

			puV = new PumpView(470, 195 + ((i - 1) * 70), 90, 90, i);
			pumpsView.add(puV);
			activesView.add(puV);
			if (i == 7) break;
		}

		pumpsView.add(new PumpView(100, 720, 90, 90, 8));
		pumpsView.add(new PumpView(350, 720, 90, 90, 9));
		pumpsView.add(new PumpView(600, 720, 90, 90, 10 ));

		for(int i = GameManager.GetCisterns().size() - 1; i >= 0; i--)
		{
			CisternView cV = new CisternView(200 + (i * 250), 900, 100, 100,  i);
			cisternsView.add(cV);
			activesView.add(cV);
		}
		
		for(int i = 0; i < GameManager.GetPipes().size(); i++) 
		{
			PipeView piV = new PipeView(i);
			pipesView.add(piV);
			
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