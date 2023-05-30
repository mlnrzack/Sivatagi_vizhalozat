package graphics.elements;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

import game.*;
import game.IO.*;
import graphics.*;
import graphics.players.*;


/**Ez az osztály felel a játéktér megjelenítéséért.
 */
public class MapView extends JPanel
{
	private BufferedImage mapImage;                                                                 //háttérkép

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();                             //a képernyő mérete
	int screenHeight = screenSize.height;                                                           //képernyő magassága
	int screenWidth = screenSize.width;                                                             //képernyő szélessége

	private final Color color = Color.decode("#c9a77d");                                            //a háttérszín
	private final Color circleColor = Color.decode("#94744d");                                      //a körök színe
	private final Color currentColor = Color.decode("#ffffff");                                     //az aktuális játékost jelölő szín

	private ArrayList<ElementView> mapView = new ArrayList<ElementView>();                          //térkép megjelnítéseére szolgáló lista
	private MechanicView currentMechanic;                                                           //az aktuális szerelő megjelenítése
	private SaboteurView currentSaboteur;                                                           //az aktuális szabotőr megjenenítése
	private ArrayList<PipeView> pipesView = new ArrayList<PipeView>();                              //a csövek megjelenítésére szolgáló lista
	private ArrayList<ElementView> activesView = new ArrayList<ElementView>();                      //
	private ArrayList<CisternView> cisternsView = new ArrayList<CisternView>();                     //a ciszternák megjelenítésere szolgáló lista
	private ArrayList<PumpView> pumpsView = new ArrayList<PumpView>();                              //a pumpák megjelenítésére szolgáló lista
	private ArrayList<WaterSpringView> springsView = new ArrayList<WaterSpringView>();              //a vízforrások megjelenítésére szolgáló lista
	private ArrayList<MechanicView> mechanicsView = new ArrayList<MechanicView>();                  //a szerelők megjelenítésére szolgáló lista
	private ArrayList<SaboteurView> saboteursView = new ArrayList<SaboteurView>();                  //a szabotőrök megjelenítésére szolgáló lista

	private int x, y, imX, imY;                                                                     //koordináta komponensek
	private boolean dragging;                                                                       //pumpa húzása
	public boolean isPlayerMoving = false;                                                          //játékos lépésének vizsgálására

	/**Osztály konstruktor
	 */
	public MapView()
	{
		//térkép képének létrehozása
		mapImage = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		//háttérszín beállítása
		this.setBackground(color);
		//méret beállítása
		Dimension dimension = new Dimension((int)(screenWidth * 0.7), screenHeight);
		this.setMaximumSize(dimension);
		this.setMinimumSize(dimension);
		this.setPreferredSize(dimension);

		//térkép felrajzolása
		DrawMap();
		//egértevékenység figyelése
		MouseListener();
	}

	/**A térkép felrajzolása a JPanelra, majd annak frissítése
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
				g2.drawLine(pipesView.get(i).GetNeighbours()[0].GetCenterX(), pipesView.get(i).GetNeighbours()[0].GetCenterY(),
						pipesView.get(i).GetNeighbours()[1].GetCenterX(), pipesView.get(i).GetNeighbours()[1].GetCenterY());

				// Set the dimensions of the pipe
				pipesView.get(i).SetDimensions();

				// Determine the angle of the line
				double angle = pipesView.get(i).GetAngle();

				// Calculate the center point of the line
				Point center = calculateCenter(pipesView.get(i).GetNeighbours()[0].GetCenterX(), pipesView.get(i).GetNeighbours()[0].GetCenterY(),
						pipesView.get(i).GetNeighbours()[1].GetCenterX(), pipesView.get(i).GetNeighbours()[1].GetCenterY());

				// Save the current transform
				AffineTransform oldTransform = g2.getTransform();

				// Apply the rotation transformation
				g2.rotate(angle, center.x, center.y);

				// Calculate the new position for drawing the image
				int imageX = (int) (center.x - pipesView.get(i).GetWidth() / 2);
				int imageY = (int) (center.y - pipesView.get(i).GetHeight() / 2);

				// Draw the image on top of the line
				g2.drawImage(pipesView.get(i).LoadImage(), imageX, imageY, pipesView.get(i).GetWidth(), pipesView.get(i).GetHeight(), null);

				stringX = imageX + pipesView.get(i).GetWidth() / 2;
				stringY = imageY + pipesView.get(i).GetHeight() / 2;

				// Set the font and color for the string
				g2.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));

				// Draw the string in the middle of the image
				g2.drawString(pipesView.get(i).GetPipe().GetId(), stringX + 40, stringY + 8);

				// Draw a small circle next to the center
				int circleSize = 45; // Adjust the size as needed
				int circleX = center.x - circleSize / 2;
				int circleY = center.y - circleSize / 2;
				g2.drawOval(circleX, circleY, circleSize, circleSize);

				// Restore the original transform
				g2.setTransform(oldTransform);
			}
		}

		//rajzolási font visszaállítása kisebb méretre
		g2.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));

		for(int i = 0; i < pumpsView.size(); i++)
		{
			g2.setColor(color);
			g2.fillOval(pumpsView.get(i).GetPosX() - 7, pumpsView.get(i).GetPosY(), 100, 100);

			g2.setColor(circleColor);
			g2.drawOval(pumpsView.get(i).GetPosX() - 7, pumpsView.get(i).GetPosY(), 100, 100);

			g2.drawImage(pumpsView.get(i).LoadImage(), pumpsView.get(i).GetPosX(), pumpsView.get(i).GetPosY(),
					pumpsView.get(i).GetWidth(), pumpsView.get(i).GetHeight(), null, null );

			g2.setColor(Color.BLACK);
			g2.drawString(pumpsView.get(i).GetPump().GetId(), pumpsView.get(i).GetPosX() + 22, pumpsView.get(i).GetPosY() + 85);

		}

		for (int i = 0 ; i < cisternsView.size(); i++)
		{
			g2.setColor(color);
			g2.fillOval(cisternsView.get(i).GetPosX() + 3, cisternsView.get(i).GetPosY() + 10, 100, 100);

			g2.setColor(circleColor);
			g2.drawOval(cisternsView.get(i).GetPosX() + 3, cisternsView.get(i).GetPosY() + 10, 100, 100);

			g2.drawImage(cisternsView.get(i).LoadImage(), cisternsView.get(i).GetPosX() ,cisternsView.get(i).GetPosY(),
					cisternsView.get(i).GetWidth(), cisternsView.get(i).GetHeight(), null, null );

			g2.setColor(Color.BLACK);
			g2.drawString(cisternsView.get(i).GetCistern().GetId(), cisternsView.get(i).GetPosX() + 30, cisternsView.get(i).GetPosY() + 100);
		}

		for (int i = 0; i < springsView.size();i++)
		{
			g2.setColor(color);
			g2.fillOval(springsView.get(i).GetPosX() + 25, springsView.get(i).GetPosY() + 20, 100, 100);

			g2.setColor(circleColor);
			g2.drawOval(springsView.get(i).GetPosX() + 25, springsView.get(i).GetPosY() + 20, 100, 100);

			g2.drawImage(springsView.get(i).LoadImage(), springsView.get(i).GetPosX(), springsView.get(i).GetPosY(),
					springsView.get(i).GetWidth(), springsView.get(i).GetHeight(), null, null);

			g2.setColor(Color.BLACK);
			g2.drawString(springsView.get(i).GetSpring().GetId(), springsView.get(i).GetPosX() + 55, springsView.get(i).GetPosY() + 115);
		}

		for(int i = 0; i < mechanicsView.size(); i++)
		{
			//player surrounding circle color set
			g2.setColor(circleColor);
			//current player surrounding circle color set
			if (mechanicsView.get(i) == currentMechanic)
				g2.setColor(currentColor);

			//player surrounding circle draw
			g2.drawOval(mechanicsView.get(i).GetPos().GetCenterX() + ((i % mechanicsView.size()) * 10), mechanicsView.get(i).GetPos().GetCenterY() + 10, 80, 80);

			//load the image of the player
			g2.drawImage(mechanicsView.get(i).LoadImage(), mechanicsView.get(i).GetPos().GetCenterX() + ((i % mechanicsView.size()) * 10) + 20,
					mechanicsView.get(i).GetPos().GetCenterY() + 20, mechanicsView.get(i).GetWidth(), mechanicsView.get(i).GetHeight(), null, null );
			//set the drawing color for the name writing
			g2.setColor(Color.BLACK);
			g2.drawString(mechanicsView.get(i).getMechanic().GetName(), mechanicsView.get(i).GetPos().GetCenterX() + ((i % mechanicsView.size()) * 10) + 10, mechanicsView.get(i).GetPos().GetCenterY()  + 100);

		}

		for(int i = 0; i < saboteursView.size(); i++)
		{
			//player surrounding circle color set
			g2.setColor(circleColor);
			//current player surrounding circle color set
			if (saboteursView.get(i) == currentSaboteur)
				g2.setColor(currentColor);

			//player surrounding circle draw
			g2.drawOval(saboteursView.get(i).GetPos().GetCenterX() - ((i % saboteursView.size()) * 30), saboteursView.get(i).GetPos().GetCenterY() - 70, 80, 80);
			//load the image of the player
			g2.drawImage(saboteursView.get(i).LoadImage(), saboteursView.get(i).GetPos().GetCenterX() - ((i % saboteursView.size()) * 30) + 10, saboteursView.get(i).GetPos().GetCenterY() - 60,
					saboteursView.get(i).GetWidth(), saboteursView.get(i).GetHeight(), null, null );
			//set the drawing color for the name writing
			g2.setColor(Color.BLACK);
			g2.drawString(saboteursView.get(i).getSaboteur().GetName(), saboteursView.get(i).GetPos().GetCenterX() - ((i % saboteursView.size()) * 30) + 10, saboteursView.get(i).GetPos().GetCenterY() - 70);
		}

		for(int i = 0 ; i < mapView.size(); i++)
		{
			if (contains(mapView.get(i), getMousePosition()))
			{
				g.setColor(Color.RED);
				g.drawRect(mapView.get(i).GetPosX(), mapView.get(i).GetPosY(), mapView.get(i).GetWidth(), mapView.get(i).GetHeight());
			}
		}

		for (int k = 0; k < pipesView.size(); k++)
		{
			PipeView pipeView = pipesView.get(k);
			Point pointA = new Point();
			pointA.x = pipeView.GetNeighbours()[0].GetCenterX();
			pointA.y = pipeView.GetNeighbours()[0].GetCenterY();

			Point pointB = new Point();
			pointB.x = pipeView.GetNeighbours()[1].GetCenterX();
			pointB.y = pipeView.GetNeighbours()[1].GetCenterY();

			Point mousePosition = getMousePosition();
			double tolerance = 2.0;

			if (isMouseNearLine(pointA, pointB, mousePosition, tolerance))
			{
				// Mouse is near the line, display the green circle
				int centerX = (pointA.x + pointB.x) / 2;
				int centerY = (pointA.y + pointB.y) / 2;
				int radius = 30;

				g2.setColor(Color.GREEN);
				g2.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
			}
		}

		g2.dispose();
	}

	//egyelőre csak felhelyezi a modellben inicializált elemeket, de nem adott helyre és nem is adott szomszédokhoz
	/**A térkép felrajzolása kezdetben
	 */
	public void DrawMap()
	{
		Graphics2D g2d = (Graphics2D) mapImage.getGraphics();
		g2d.setColor(color);
		g2d.fillRect(0, 0, screenWidth, screenHeight);

		for(int i = 0; i < GameManager.GetWaterSprings().size(); i++)
		{
			WaterSpringView sV = new WaterSpringView((int)(screenWidth * 0.05) + (i * (int)(screenWidth * 0.15)), (int)(screenHeight * 0.02), 150, 150, i);
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
			DebugLog.WriteDebugLog(puV.GetPump().GetId());
			DebugLog.WriteDebugLog("CenterX  " + Integer.toString(puV.GetCenterX()));
			DebugLog.WriteDebugLog("CenterY  " + Integer.toString(puV.GetCenterY()));
			DebugLog.WriteDebugLog("PosX " + Integer.toString(puV.GetPosX()));
			DebugLog.WriteDebugLog("PosY " + Integer.toString(puV.GetPosY()));
			if (i == 10) break;
			i++;

			puV = new PumpView((int)(screenWidth * 0.3) + (i % 4 * 45), 195 + ((i - 1) * 70), 90, 90, i);
			DebugLog.WriteDebugLog(GameManager.GetPumps().get(i).GetId());
			DebugLog.WriteDebugLog(puV.GetPump().GetId());
			DebugLog.WriteDebugLog("CenterY  " +Integer.toString(puV.GetCenterY()));
			DebugLog.WriteDebugLog("PosX " + Integer.toString(puV.GetPosX()));
			DebugLog.WriteDebugLog("PosY " + Integer.toString(puV.GetPosY()));
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
					MechanicView meV = new MechanicView(mapView.get(k), 60, 60, i);
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
					SaboteurView saV = new SaboteurView(mapView.get(k), 60, 60, i);
					saboteursView.add(saV);

					//if(GameManager.GetCurrentSaboteur().GetName() == GameManager.GetSaboteurs().get(i).GetName())
					currentSaboteur = saV;
				}
			}
		}
	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static Point calculateCenter(int x1, int y1, int x2, int y2)
	{
		int centerX = (x1 + x2) / 2;
		int centerY = (y1 + y2) / 2;
		return new Point(centerX, centerY);
	}

	/**
	 */
	private void MouseListener()
	{
		final PumpView[] pm = new PumpView[1];
		final int[] selectedPump = new int[1];
		final ElementView[] mv = new ElementView[1];

		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent me)
			{
				if(!isPlayerMoving)
				{
					for (int i = 0 ; i < pumpsView.size(); i++)
					{
						if (me.getX() >= pumpsView.get(i).GetPosX() && me.getX() < pumpsView.get(i).GetPosX() + pumpsView.get(i).GetWidth()
								&& me.getY() >= pumpsView.get(i).GetPosY() && me.getY() < pumpsView.get(i).GetPosY() + pumpsView.get(i).GetHeight())
						{
							dragging = true;
							pm[0] = pumpsView.get(i);
							selectedPump[0] = i;
						}
					}
				}

				else if (isPlayerMoving)
				{
					JFrame jf = new JFrame();
					for (int k = 0; k < pipesView.size(); k++)
					{
						PipeView pipeView = pipesView.get(k);
						Point pointA = new Point(pipeView.GetNeighbours()[0].GetCenterX(), pipeView.GetNeighbours()[0].GetCenterY());
						Point pointB = new Point(pipeView.GetNeighbours()[1].GetCenterX(), pipeView.GetNeighbours()[1].GetCenterY());
						Point center = calculateCenter(pointA.x, pointA.y, pointB.x, pointB.y);

						if (isMouseClickInsideCircle(pointA,pointB, 50, me.getPoint()))
						{
							mv[0] = pipeView;

							JOptionPane.showMessageDialog(jf, mv[0].GetElement().GetId() + " " + isMouseClickOnLine(pointA, pointB, me.getPoint()));
							System.out.println("1. MapView kiválasztott "+mv[0].GetElement().GetId());
							if (checkNighbour(mv[0])){
								GameFrame.SetElement(mv[0]);
								isPlayerMoving = false;
								}
							else
							{
								JOptionPane.showMessageDialog(jf,"Nem szomszédot sikerült választani, nem történt mozgás");
								isPlayerMoving = false;
							}
							break;
						}
					}

					for (int i = 0; i < activesView.size(); i++)
					{
						if (me.getX() >= activesView.get(i).GetPosX() && me.getX() < activesView.get(i).GetPosX() + activesView.get(i).GetWidth()
								&& me.getY() >= activesView.get(i).GetPosY() && me.getY() < activesView.get(i).GetPosY() + activesView.get(i).GetHeight())
						{
							mv[0] = activesView.get(i);
							//isPlayerMoving= false;
							JOptionPane.showMessageDialog(jf, mv[0].GetElement().GetId());
							if (checkNighbour(mv[0])){
								GameFrame.SetElement(mv[0]);
								isPlayerMoving = false;
								}
							else
							{
								JOptionPane.showMessageDialog(jf,"Nem szomszédot sikerült választani, nem történt mozgás");
								isPlayerMoving = false;
							}

							break;
						}
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

					pumpsView.get(selectedPump[0]).SetCenterX(nx);
					pumpsView.get(selectedPump[0]).SetCenterY(ny);

					repaint();
				}
			}

			@Override
			public void mouseMoved(MouseEvent me)
			{
				repaint();
			}
		});
	}

	/**
	 * @param pipeV
	 * @param point
	 * @return
	 */
	public boolean isOnTheLine(PipeView pipeV, Point point)
	{

		Point p = new Point();
		p = calculateCenter(
				pipeV.GetNeighbours()[0].GetCenterX(), pipeV.GetNeighbours()[0].GetCenterY(),
				pipeV.GetNeighbours()[1].GetCenterX(), pipeV.GetNeighbours()[1].GetCenterY());

		pipeV.GetWidth();
		return false;
	}


	private boolean checkNighbour(ElementView elementView) {
		if (currentMechanic == null)
		{
			System.out.println(currentSaboteur.getSaboteur().GetName());
			for (int i = 0 ; i < currentSaboteur.getSaboteur().GetCurrentPosition().GetNeighbours().size(); i++)
			{
				String ie = currentSaboteur.getSaboteur().GetCurrentPosition().GetNeighbours().get(i).GetId();
				if(ie.equals(elementView.GetElement().GetId()))
					return true;
			}
		}
		else {

			System.out.println(currentMechanic.getMechanic().GetName());
			for (int i = 0;i<currentMechanic.getMechanic().GetCurrentPosition().GetNeighbours().size();i++)
			{
				String ie = currentMechanic.getMechanic().GetCurrentPosition().GetNeighbours().get(i).GetId();
				if(ie.equals(elementView.GetElement().GetId()))
					return true;
			}
		}
		return false;
	}

	/**
	 * @param pointA
	 * @param pointB
	 * @param mouseClick
	 * @return
	 */
	public boolean isMouseClickOnLine(Point pointA, Point pointB, Point mouseClick)
	{
		// Calculate the center point of the line
		Point center = calculateCenter(pointA.x, pointA.y, pointB.x, pointB.y);

		// Calculate the radius of the little circle
		double circleRadius = 50.0; // Adjust the radius as needed

		// Calculate the squared distance between the mouse click and the center of the circle
		double distanceSquared = Math.pow(mouseClick.x - center.x, 2) + Math.pow(mouseClick.y - center.y, 2);

		// Calculate the squared distance threshold for considering a click next to the circle
		double thresholdSquared = Math.pow(circleRadius + 5.0, 2); // Adjust the threshold as needed

		// Check if the squared distance is within the threshold
		return distanceSquared <= thresholdSquared;
	}

	/**
	 * @param startPoint
	 * @param endPoint
	 * @param radius
	 * @param mouseClick
	 * @return
	 */
	public boolean isMouseClickInsideCircle(Point startPoint, Point endPoint, int radius, Point mouseClick)
	{
		// Calculate the center point of the line
		Point centerPoint = new Point((startPoint.x + endPoint.x) / 2, (startPoint.y + endPoint.y) / 2);

		// Calculate the squared distance between the mouse click position and the center of the circle
		double distanceSquared = Math.pow(mouseClick.x - centerPoint.x, 2) + Math.pow(mouseClick.y - centerPoint.y, 2);

		// Calculate the squared radius
		int radiusSquared = radius * radius;

		// Check if the squared distance is less than or equal to the squared radius
		return distanceSquared <= radiusSquared;
	}

	public void UpdateMapDetails() {

		if(GameManager.GetCurrentMechanic() != null) {
			for(ElementView element: mapView) {
				if(element.GetElement() == GameManager.GetCurrentMechanic().GetCurrentPosition()) {
					currentMechanic.UpdatePos(element);
				}
			}
		}
		if(GameManager.GetCurrentSaboteur() != null) {
			for(ElementView element: mapView) {
				if(element.GetElement() == GameManager.GetCurrentSaboteur().GetCurrentPosition()) {
					currentSaboteur.UpdatePos(element);
				}
			}
		}
	}

	/**
	 * @param pointA
	 * @param pointB
	 * @param mousePosition
	 * @param tolerance
	 * @return
	 */
	public boolean isMouseNearLine(Point pointA, Point pointB, Point mousePosition, double tolerance)
	{
		// Calculate the center point of the line
		Point center = calculateCenter(pointA.x, pointA.y, pointB.x, pointB.y);

		// Calculate the distance between the mouse position and the center of the circle
		if (mousePosition!= null)
		{
			double distance = mousePosition.distance(center);

			return distance <= tolerance;
		}else{
			return false;
		}
		// Check if the distance is within the tolerance
	}

	/**
	 * @param mapView
	 * @param point
	 * @return
	 */
	public boolean contains(ElementView mapView, Point point)
	{
		int x1 = mapView.GetPosX();
		int y1 = mapView.GetPosY();
		int x2 = mapView.GetPosX() + mapView.GetWidth();
		int y2 = mapView.GetPosY() + mapView.GetHeight();
		if (point!= null)
			return point.getX() >= x1 && point.getX() <= x2 && point.getY() >= y1 && point.getY() <= y2;
			return false;
	}
}