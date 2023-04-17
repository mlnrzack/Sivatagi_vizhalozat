package game.elements;

import java.util.ArrayList;

import game.players.*;

/**
 * ActiveElement
 *
 * Az aktív pályaelemek (pumpa, forrás, ciszterna) ősosztálya.
 * Az őse az Element osztály {@link game.elements.Element}
 */

public abstract class ActiveElement extends Element
{
    protected static ArrayList<Pipe> neighbours = null;

    /**
     * A szomszédsági lista settere, egy lista átadására alkalmas
     * @param neighbour
     */
    public void setNeighbours(ArrayList<Pipe> neighbours)
    {
    	System.out.println("public void setNeighbours(ArrayList<Pipe> neighbours)");
    }

    /**
     * A szomszédsági lista settere
     * @param neighbour
     */
    public static void setNeighbour(Pipe neighbour)
    {
    	System.out.println("public static void setNeighbour(Pipe neighbour)");
    }
    /**
     * A kiválasztott csővet hozzáadja a szomszédok listájából
     * @param p
     * @return
     */
    public static boolean AddPipe(Pipe p)
    {
    	System.out.println("public static boolean AddPipe(Pipe p)");
    	setNeighbour(p);
    	return false;
    }

    /**
     * A kiválasztott csővet eltávolítja a szomszédok listájából
     * @param p
     * @return
     */
    public static boolean RemovePipe(Pipe p)
    {
    	System.out.println("public static boolean RemovePipe(Pipe p)");  	
    	return false;
    }

    /**
     *  Az elemre lépő játékos elfogadását valósítja meg
     * @param player
     * @return
     */
    public boolean AcceptPlayer(Player player)
    {
    	System.out.println("public boolean AcceptPlayer(Player player)");
        return AddPlayer(player);
    }

    /**
     *Az aktív elemtől elválasztja a hozzá kapcsolódó csövet.
     * @param neighbourIdx a leválasztandó szomszéd indexe
     * @return visszatér a leválasztott Cső elemmel
     * */
    public Pipe DisconnectNeighbourPipe(int neighbourIdx)
    {
    	System.out.println("public Pipe DisconnectNeighbourPipe(int neighbourIdx)");
        return null;
    }

    /**
     * Visszatér az elemhez csatlakoztatott szomszédokkal
     * @return Egy <Element> típusú lista
     * */
    public ArrayList<Element> GetNeighbours()
    {
    	System.out.println("public ArrayList<Element> GetNeighbours()");
    	return null;
    }
    /**
     * A játékos felveszi az adott aktív elemből induló szabad végű csövet
     * @return Pipe
     * */
    public Pipe PickUpFreePipeEnd()
    {
    	System.out.println("public IPipe PickUpFreePipeEnd()");
        return null;
    }
    /**
     *A szerelő a ciszternán állva felvesz egy pumpát
     * @return Pump
     */
    public Pump PickUpPump()
    {
    	System.out.println("public IPump PickUpPump()");
        return null;
    }
    /**
     * Beépít egy pumpát egy csőre.
     * @param pump
     * @return boolean
     * */
    public boolean TryBuildPumpInto(Pump pump)
    {
    	System.out.println("public boolean TryBuildPumpInto(IPump pump)");
        return false;
    }

    /**
     * Csatlakoztat egy csövet egy aktív elemhez
     * @param pipeInInventory
     * @return
     */
    public boolean TryConnectPipe(Pipe pipeInInventory)
    {
    	System.out.println("public boolean TryConnectPipe(IPipe pipeInInventory)");
    	pipeInInventory.AddNeighbour(this);

        return false;
    }

    /**
     * Az adott elemen álló szabotőr rongálási kísérletét vizsgáló függvény.
     * @return boolean
     */
    public boolean TryDamage()
    {
    	System.out.println("public boolean TryDamage()");
        return false;
    }

    /**
     * Az adott elemen álló szerelő javítási kísérletét vizsgáló függvény
     * @return boolean
     */
    public boolean TryRepair()
    {
    	System.out.println("public boolean TryRepair()");
        return false;
    }

    /**
     * A pumpa be és kifolyásának irányítása
     * @param inputIdx
     * @param outputIdx
     * @return boolean
     */
    public boolean TrySetInputOutput(int inputIdx, int outputIdx)
    {
    	System.out.println("public boolean TrySetInputOutput(int inputIdx, int outputIdx)");
        return false;
    }
}
