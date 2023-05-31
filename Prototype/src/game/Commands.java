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
    		// Command: playerMove playerName nameOfTheNeighbour
       		case "playerMove":
       		{
       			Player player = GetPlayer(parameters[0]);
       			IElement currPos = player.GetCurrentPosition();
       			int neighbourIdx = currPos.GetNeighbourIndex(parameters[1]);
       			if (neighbourIdx == -1)
       				System.out.println("Nem létező szomszéd: " + parameters[1]);
       			
       			player.Move(neighbourIdx);
                break;
       		}
       	// Command: playerSetIO playerName neighbourNameOfInput neighbourNameOfOutput
            case "playerSetIO":{
            	Player player = GetPlayer(parameters[0]);
            
            	if (player != null) {
            		int neighbourIndexOfInput = player.GetCurrentPosition().GetNeighbourIndex(parameters[1]);
           			if (neighbourIndexOfInput == -1)
           				System.out.println("Nem létező szomszéd: " + parameters[1]);
           			
           			int neighbourIndexOfOutput = player.GetCurrentPosition().GetNeighbourIndex(parameters[2]);
           			if (neighbourIndexOfOutput == -1)
           				System.out.println("Nem létező szomszéd: " + parameters[2]);
                    
                    if (neighbourIndexOfOutput != -1 && neighbourIndexOfInput != -1)
                    	player.TrySetPump(neighbourIndexOfInput, neighbourIndexOfOutput);
            	} else {
            		System.out.println("Nem létező játékos");
            	}            	
                
                break;
            }
         // Command: addPump name <optionally inputName outputName>
            case "addPump":{
            	if (IsElementNameUnique(parameters[0])) {
            		Pump p = new Pump();
            		p.SetId(parameters[0]);
            		GameManager.AddToMap(p);
            		
            		if (parameters.length == 3 && GetPipe(parameters[1]) != null && GetPipe(parameters[1]) != null){
                		p.SetInput(GetPipe(parameters[1]));
                		p.SetOutput(GetPipe(parameters[2]));
                	}
            	}
            	
                break;
            }
         // Command: addPipe name <optionally broken || sticky || slippery || withWater>
            case "addPipe":{
            	if (IsElementNameUnique(parameters[0])) {
            		Pipe p = new Pipe();
            		p.SetId(parameters[0]);
            		GameManager.AddToMap(p);
            		
            		if (parameters.length > 1){
            			for (int i = 1; i < parameters.length; i++) {
            				if (parameters[i].equals("broken"))
            					p.SetLeaking(true);
            				if (parameters[i].equals("sticky"))
            					p.SetSticky();
            				if (parameters[i].equals("slippery"))
            					p.SetSlippery();
            				if (parameters[i].equals("withWater"))
            					p.SetWaterInside(1);
            			}
            		}
            	}
                
                break;
            }
         // Command: addCistern name
            case "addCistern":{
            	if (IsElementNameUnique(parameters[0])) {
            		Cistern c = new Cistern();
            		c.SetId(parameters[0]);
            		GameManager.AddToMap(c);
            	}
                
                break;
            }
         // Command: addSource name
            case "addSource":{
            	if (IsElementNameUnique(parameters[0])) {
            		WaterSpring w = new WaterSpring();
            		w.SetId(parameters[0]);
            		GameManager.AddToMap(w);
            	}
                
                break;
            }
         // Command: spawnMechanic playerName elementName
            case "spawnMechanic":{
            	IElement e = GetElement(parameters[1]); 
            	
            	// Nem foglalt a név.
            	if (GetPlayer(parameters[0]) == null && e != null) {
            		Mechanic m = new Mechanic();
            		m.SetName(parameters[0]);         			
            		m.SetCurrentPosition(e);
            		e.AcceptPlayer(m);
            		System.out.println(parameters[0] + " szerelő hozzáadva a játékhoz ide: " + e.GetId());
            	} else {
            		System.out.println(parameters[0] + " szerelő hozzáadása sikertelen. Foglalt név vagy nem létező elem.");
            	}

                break;
            }
         // Command: spawnSaboteur playerName elementName
            case "spawnSaboteur":{
            	IElement e = GetElement(parameters[1]); 
            	
            	// Nem foglalt a név.
            	if (GetPlayer(parameters[0]) == null && e != null) {
            		Saboteur s = new Saboteur();
            		s.SetName(parameters[0]);
            		s.SetCurrentPosition(e);
            		e.AcceptPlayer(s);
            		System.out.println(parameters[0] + " szabotőr hozzáadva a játékhoz ide: " + e.GetId());
            	} else {
            		System.out.println(parameters[0] + " szabotőr hozzáadása sikertelen. Foglalt név vagy nem létező elem.");
            	}

                break;
            }
       		// Command: repair playerName
            case "repair":{
            	Mechanic player = GetMechanic(parameters[0]);
       			
       			if (player != null)
       				player.Repair();
       			else
       				System.out.println("Javítás sikertelen. Nincs ilyen nevű szerelő: " + parameters[0]);
       			
                break;
            }
            // Command: pickfreepipe playerName
            case "pickfreepipe":{
    			Mechanic player = GetMechanic(parameters[0]);
       			
       			if (player != null)
       				player.PickUpFreePipeEnd();
       			else
       				System.out.println("Cső felvétele sikertelen. Nincs ilyen nevű szerelő: " + parameters[0]);
       			
                break;
            }
         // Command: picknewpump playerName
            case "picknewpump":{
    			Mechanic player = GetMechanic(parameters[0]);
       			
       			if (player != null)
       				player.PickUpPump();
       			else
       				System.out.println("Pumpa felvétele sikertelen. Nincs ilyen nevű szerelő: " + parameters[0]);
       			
                break;
            }
         // Command: droppump playerName
            case "droppump":{
    			Mechanic player = GetMechanic(parameters[0]);
       			
       			if (player != null && player.BuildPumpIntoPipe())
       				System.out.println("Pumpa csatlakoztatása sikeres neki: " + player.GetName());
       			else
       				System.out.println("Pumpa csatlakoztatása sikertelen neki: " + player.GetName());
       				
                break;
            }
         // Command: connectpipe playerName
            case "connectpipe":{
    			Mechanic player = GetMechanic(parameters[0]);
       			
       			if (player != null && player.ConnectPipe())
       				System.out.println("Cső csatlakoztatása sikeres neki: " + player.GetName());
       			else
       				System.out.println("Cső csatlakoztatása sikertelen neki: " + player.GetName());
       			
                break;
            }
            // Command: pickneighbour playerName neighbourName
            case "pickneighbour":{
    			Mechanic player = GetMechanic(parameters[0]);
    			if (parameters.length != 2)
    				System.out.println("Hibás bemenet");
    				
       			if (player != null) {
       				int neighbourIndex = player.GetCurrentPosition().GetNeighbourIndex(parameters[1]);
       				
       				if (neighbourIndex != -1) {
       					if (player.DisconnectNeighbourPipe(neighbourIndex))
       						System.out.println(parameters[1] + " felvétele sikeres neki: " + player.GetName());
       				}       					
       				else {
       					System.out.println(parameters[1] + " felvétele sikertelen neki: " + parameters[0]);
       					
       				}
       			}       				
       			else {
       				System.out.println(parameters[1] + " felvétele sikertelen neki: " + parameters[0]);
       			}
       			
                break;
            }            
            // Command: leakpipe playerName
            case "leakpipe":{
            	Player player = GetPlayer(parameters[0]);
            	
                if (player != null && player.Damage())
                	System.out.println("Cső lyukasztás sikeres neki: " + player.GetName());
                else
                	System.out.println("Cső lyukasztás sikertelen neki: " + player.GetName());
                
                break;
            }
            // Command: stickypipe playerName
            case "stickypipe":{
            	Player player = GetPlayer(parameters[0]);
            	
                if (player != null && player.SetStickyPipe())
                	System.out.println("Cső ragacsossá tétele sikeres neki: " + player.GetName());
                else
                	System.out.println("Cső ragacsossá tétele sikeres neki: " + parameters[0]);
                
                break;
            }
         // Command: slipperyPipe playerName
            case "slipperyPipe":{
            	Saboteur saboteur = GetSaboteur(parameters[0]);
            	
                if (saboteur != null && saboteur.SetSlipperyPipe())
                	System.out.println("Cső csúszóssá tétele sikeres neki: " + saboteur.GetName());
                else
                	System.out.println("Cső csúszóssá tétele sikertelen neki: " + parameters[0]);
                
                break;
            }
         // Command: pass playerName
            case "pass":{
            	Player player = GetPlayer(parameters[0]);
            	
                if (player != null)
                	player.Pass();
                else
       				System.out.println(parameters[0] + " játékos nem létezik");
                
                break;
            }         
            // Command: setneighbours pipeName activeElementName
            case "setneighbours":{
            	Pipe p = GetPipe(parameters[0]);
            	ActiveElement a = GetActiveElement(parameters[1]);
            	
            	if (p != null && a != null) {
            		p.AddNeighbour(a);
            		a.AddPipe(p);
            	} else {
            		System.out.println(parameters[0] + " cső vagy " +  parameters[1] + " aktív elem nem létezik");
            	}
                
                break;
            }
         // Command: endGame
            case "endGame":{
            	if (GameManager.GetMechanincsPoints() > GameManager.GetSaboteurPoints())
            		System.out.println("Szerelők nyertek");
            	else
            		System.out.println("Szabotőrök nyertek");
                
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
