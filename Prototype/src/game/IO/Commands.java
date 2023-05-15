package game.IO;

import game.GameManager;
import game.elements.*;
import game.interfaces.ISteppable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class Commands
{
/**
 * generate_map
 * addPipe
 * addPump
 * addCistern
 * addSource
 * spawnMechanic
 * spawnSaboteur
 * playerMove
 * playerAction
 * playerSetIO
 * stat
 *
 */

    public Commands()
    {
    	//TODO
    }

    public void GenerateMap(String[] param) throws IOException
    {
        if (param.length != 1)
        {
            System.out.println("Nem adott meg megfelelő elérési útvonalat!");
        }
        else
        {
            File f = new File(param[0]);
            if (f.exists())
            {
                FileReader fileReader = null;
                try
                {
                    fileReader = new FileReader(f);
                }
                catch (FileNotFoundException e)
                {
                    throw new RuntimeException(e);
                }
                BufferedReader br = new BufferedReader(fileReader);
                String neighbourList;
                //Elem Azonosító:Elem_Azonosító,Elem_Azonosító,..,Elem_Azonosító
                while ((neighbourList = br.readLine()) != null)
                {
                    Element temp;
                    String firstSplit[] = neighbourList.split(" ");

                    String [] secondSplit = firstSplit[1].split(":");
                    switch (firstSplit[0])
                    {
                        case "Pipe":       temp = new Pipe(); ProcessParams(secondSplit[1]);
                        case "Pump":        temp = new Pump(); ProcessParams(secondSplit[1]);
                        case "Cistern" :    temp = new Cistern(); ProcessParams(secondSplit[1]);
                        case "Source" :     temp = new WaterSpring(); ProcessParams(secondSplit[1]);
                    }
                }
            }
        }
    }

    void ProcessParams(String params)
    {
        ArrayList<Element> elementNeighbours = new ArrayList<Element>();
        String neighbours[] = params.split(",");
        for (String elementArray: neighbours)
        {
            String[] element = elementArray.split("_");
            //Itt van az a pont, hogy mi a faszomért ilyen komplex a bemenet, de így tűnt a legegyszerűbbnek elválasztani egymástól a dolgokat
            switch (element[0])
            {
                case "Pipe":

                case "Pump":

                case "Cistern" :

                case "Source" :

            }

        }
    }
    
    /**
     * Két féle működés kell neki, vagy csak módosítom a doksiban a bemenetét
     * Régi bemenet: addPipe <neighbour1> <neigbour2>
     * Új bemenet: addPipe <id> <neighbour1> <neigbour2>
     * @param param
     */
    public void AddPipe(String[] param)
    {
        switch (param.length)
        {
            case 0:
            case 1:
            case 2:
                System.out.println("Incorrect parameter");
                return;
            case 3:

                //hozzá kell még adni az ID-t
                //GameManager.AddSteppable(new Pipe(false,0,0,0,pipeNeighbours));
                break;
            default:
                break;
        }
    }

    /**
     * Szintén módosul a bemenet:
     * addPump <id> <neighbour1> <neigbour2> … <neighbourN> <input> <output>
     * @param param
     */
    public void AddPump(String[] param)
    {
        switch (param.length)
        {
            case 0:
            case 1:
                break;
            case 5:

                break;
            default:
                break;
        }
    }
    
    public void AddCistern(String[] param)
    {
        switch (param.length)
        {
            case 0:
                break;
            case 1:
            case 2:
                break;
            default:
                break;
        }
    }
    
    public void AddSouce(String[] param)
    {
        switch (param.length)
        {
            case 0:
                break;
            case 1:
            case 2:
                break;
            default:
                break;
        }
    }

    public void SpawnMechanic(String[] param)
    {
        switch (param.length)
        {
            case 2:
                break;
            default:
                break;
        }
    }
    
    public void SpawnSaboteur(String[] param)
    {
        switch (param.length)
        {
            case 2:
                break;
            default:
                break;
        }
    }

    public void playerMove(String[] param)
    {
        switch (param.length)
        {
            case 2:
                break;
            default:
                break;
        }
    }
    
    public void playerAction(String[] param)
    {
        switch (param.length)
        {
            case 2:
                break;
            default:
                break;
        }
    }
    
    public void playerSetIO(String[] param)
    {
        switch (param.length)
        {
            case 3:
                break;
            default:
                break;
        }
    }
    
    public void stat(String[] param)
    {
        switch (param.length)
        {
            case 1:
                break;
            default:
                break;
        }
    }
}