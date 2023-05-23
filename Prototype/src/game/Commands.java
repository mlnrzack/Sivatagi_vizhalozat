package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import game.elements.ActiveElement;
import game.elements.Cistern;
import game.elements.Pipe;
import game.elements.Pump;
import game.elements.WaterSpring;
import game.interfaces.IElement;
import game.players.Mechanic;
import game.players.Player;
import game.players.Saboteur;

public class Commands {
	
	public static void WriteMapStateToFile(String fileName) {
    	try {
        	BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            
            for (IElement e : GameManager.GetMap()) {
            	writer.append(e.toString());
            }
            writer.close();
        } catch(Exception e) {
        	System.out.println(e);
        }
    }
	
	public static long filesCompareByLine(Path path1, Path path2) throws IOException {
        try (BufferedReader bf1 = Files.newBufferedReader(path1);
                BufferedReader bf2 = Files.newBufferedReader(path2)) {
               
               long lineNumber = 1;
               String line1 = "", line2 = "";
               while ((line1 = bf1.readLine()) != null) {
                   line2 = bf2.readLine();
                   if (line2 == null || !line1.equals(line2)) {
                       return lineNumber;
                   }
                   lineNumber++;
               }
               if (bf2.readLine() == null) {
                   return -1;
               }
               else {
                   return lineNumber;
               }
           }
       }

	public static void ExecuteCommand(String command){
    	String[] actionWithParameters = command.split(" ", 0);
    	String action = actionWithParameters[0];
    	String[] parameters = Arrays.copyOfRange(actionWithParameters, 1, actionWithParameters.length);
    	    	
    	switch (action)
       	{
    		// Command: move playerName indexOfTheNeighbour
       		case "move":
       		{
       			Player player = GetPlayer(parameters[0]);
       			
       			int neighbourIdx = Integer.parseInt(parameters[1]);
       			player.Move(neighbourIdx);
                break;
       		}
       		// Command: repair playerName 
            case "repair":{
            	Mechanic player = GetMechanic(parameters[0]);
       			
       			if (player != null)
       				player.Repair();
       			
                break;
            }
            // Command: pickfreepipe playerName
            case "pickfreepipe":{
    			Mechanic player = GetMechanic(parameters[0]);
       			
       			if (player != null)
       				player.PickUpFreePipeEnd();
       			
                break;
            }
         // Command: picknewpump playerName
            case "picknewpump":{
    			Mechanic player = GetMechanic(parameters[0]);
       			
       			if (player != null)
       				player.PickUpPump();
       			
                break;
            }
         // Command: droppump playerName
            case "droppump":{
    			Mechanic player = GetMechanic(parameters[0]);
       			
       			if (player != null)
       				player.BuildPumpIntoPipe();
       			
                break;
            }
         // Command: connectpipe playerName
            case "connectpipe":{
    			Mechanic player = GetMechanic(parameters[0]);
       			
       			if (player != null)
       				player.ConnectPipe();
       			
                break;
            }
            // Command: pickneighbour playerName neighbourIndex
            case "pickneighbour":{
    			Mechanic player = GetMechanic(parameters[0]);
    			int neighbourIdx = Integer.parseInt(parameters[1]);
    			
       			if (player != null)
       				player.DisconnectNeighbourPipe(neighbourIdx);
       			
                break;
            }
            // Command: setpump playerName neighbourIndexOfInput neighbourIndexOfOutput
            case "setpump":{
            	Player player = GetPlayer(parameters[0]);
            
                int neighbourIdxFrom = Integer.parseInt(parameters[1]);
                int neighbourIdxTo = Integer.parseInt(parameters[2]);
                
                if (player != null)
                	player.TrySetPump(neighbourIdxFrom, neighbourIdxTo);
                
                break;
            }
            // Command: leakpipe playerName
            case "leakpipe":{
            	Player player = GetPlayer(parameters[0]);
            	
                if (player != null)
                	player.Damage();
                
                break;
            }
            // Command: stickypipe playerName
            case "stickypipe":{
            	Player player = GetPlayer(parameters[0]);
            	
                if (player != null)
                	player.SetStickyPipe();
                
                break;
            }
         // Command: pass playerName
            case "pass":{
            	Player player = GetPlayer(parameters[0]);
            	
                if (player != null)
                	player.Pass();
                
                break;
            }
         // Command: addpump name
            case "addpump":{
            	if (IsElementNameUnique(parameters[0])) {
            		Pump p = new Pump();
            		p.SetId(parameters[0]);
            		GameManager.AddToMap(p);
            	}
                
                break;
            }
         // Command: addpipe name
            case "addpipe":{
            	if (IsElementNameUnique(parameters[0])) {
            		Pipe p = new Pipe();
            		p.SetId(parameters[0]);
            		GameManager.AddToMap(p);
            	}
                
                break;
            }
         // Command: addcistern name
            case "addcistern":{
            	if (IsElementNameUnique(parameters[0])) {
            		Cistern c = new Cistern();
            		c.SetId(parameters[0]);
            		GameManager.AddToMap(c);
            	}
                
                break;
            }
         // Command: addwaterspring name
            case "addwaterspring":{
            	if (IsElementNameUnique(parameters[0])) {
            		WaterSpring w = new WaterSpring();
            		w.SetId(parameters[0]);
            		GameManager.AddToMap(w);
            	}
                
                break;
            }
            // Command: setneighbours pipeName activeElementName
            case "setneighbours":{
            	Pipe p = GetPipe(parameters[0]);
            	ActiveElement a = GetActiveElement(parameters[1]);
            	
            	if (p != null && a != null) {
            		p.AddNeighbour(a);
            		a.AddPipe(p);
            	}
                
                break;
            }
         // Command: addmechanic playerName elementName
            case "addmechanic":{
            	IElement e = GetElement(parameters[1]); 
            	
            	// Nem foglalt a név.
            	if (GetPlayer(parameters[0]) == null) {
            		Mechanic m = new Mechanic();
            		m.SetName(parameters[0]);
            		if (e.AcceptPlayer(m)) {            			
                		m.SetCurrentPosition(e);
            		}
            		else {
            			GameManager.GetSaboteurs().removeIf(mech -> mech.GetName().equals(parameters[1]));
            		}
            	}

                break;
            }
         // Command: addsaboteur playerName elementName
            case "addsaboteur":{
            	IElement e = GetElement(parameters[1]); 
            	
            	// Nem foglalt a név.
            	if (GetPlayer(parameters[0]) == null) {
            		Saboteur s = new Saboteur();
            		s.SetName(parameters[0]);
            		if (e.AcceptPlayer(s)) {            			
                		s.SetCurrentPosition(e);
            		}
            		else {
            			GameManager.GetSaboteurs().removeIf(saboteur -> saboteur.GetName().equals(parameters[1]));
            		}
            	}

                break;
            }
            default:
                break;
       	}
	}
    
    private static Mechanic GetMechanic(String name) {
    	for (Mechanic mechanic : GameManager.GetMechanics()) {
			if (mechanic.GetName().toUpperCase().equals(name.toUpperCase())) {
				return mechanic;
			}	
		}
    	return null;
    }
    
    private static Saboteur GetSaboteur(String name) {
    	for (Saboteur saboteur : GameManager.GetSaboteurs()) {
			if (saboteur.GetName().toUpperCase().equals(name.toUpperCase())) {
				return saboteur;
			}	
		}
    	return null;
    }
    
    private static Player GetPlayer(String name) {
    	Player player = GetMechanic(name.toUpperCase());
    	
    	if (player != null)
    		return player;
    	else
    		return GetSaboteur(name.toUpperCase());
    }
    
    private static boolean IsElementNameUnique(String id) {
    	for (IElement e : GameManager.GetMap()) {
    		if (id.toUpperCase().equals(e.GetId().toUpperCase()))
    			return false;
    	}
    	
    	return true;
    }
    
    private static Pipe GetPipe(String id) {
    	for (Pipe p : GameManager.GetPipes()) {
    		if (id.toUpperCase().equals(p.GetId().toUpperCase()))
    			return p;
    	}
    	
    	return null;
    }
    
    private static ActiveElement GetActiveElement(String id) {
    	ActiveElement a = null;
    	
    	if (GameManager.GetPipes().stream().anyMatch(pipe -> pipe.GetId().toUpperCase().contains(id.toUpperCase())))
    		return null;
    	
    	for (IElement e : GameManager.GetMap()) {
    		if (id.toUpperCase().equals(e.GetId().toUpperCase()))
    			return (ActiveElement) e;
    	}
    	
    	return null;
    }
    
    private static IElement GetElement(String id) {
    	for (IElement e : GameManager.GetMap()) {
    		if (id.toUpperCase().equals(e.GetId().toUpperCase()))
    			return e;
    	}
    	
    	return null;
    }
}
