package graphics.elements;

import javax.swing.JComponent;

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
public class MapView extends JComponent
{
	private MechanicView currentMechanic = new MechanicView();
	private SaboteurView currentSaboteur = new SaboteurView();
	
	private Map map = new HashMap();
	//valami itt kell csinálni, hogy megjelenjenek az elemek
	
	
	
	public MapView()
	{
		ArrayList<IElement> consoleMap = GameManager.GetMap();
		for(int i = 0; i < consoleMap.size(); i++)
		{
			//map.put(consoleMap.get(i), new( egy ahhoz tartozó )View);
		}
	}
	
	public void ReDraw()
	{
		
	}
}