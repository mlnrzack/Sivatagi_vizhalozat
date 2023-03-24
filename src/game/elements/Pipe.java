package game.elements;

import java.util.*;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public class Pipe implements Element extends ISteppable, IPipe
{
    public boolean IsWrong; // { get; set; }

    public ArrayList<ActiveElement> neighbours /*{ get; set; }*/ = new ArrayList<ActiveElement>();

    public override boolean TryBuildPumpInto(IPump pump) => pump.GetBuildedInto(this);

    public override boolean TryRepair()
    {
        if (IsWrong)
        {
            IsWrong = false;

            return true;
        }

        System.out.println("Cső javítása nem sikerül. Nincs elromolva ez az elem.");
        return false;
    }

    public override boolean TryDamage()
    {
        if (!IsWrong)
        {
            IsWrong = true;

            return true;
        }

        System.out.println("Cső rongálása nem sikerül. Már el van rontva ez az elem.");
        return false;
    }

    public boolean Step()
    {
        if (IsWrong && WaterInside > 0)
        {
            WaterToDesert();

            return true;
        }
        
        return false;
    }

    public override IEnumerable<IElement> GetNeighbours() => neighbours;

    public override boolean TryConnectPipe(IPipe pipeInInventory)
    {
        System.out.println("Bocs tesa ez nem fog menni. Jelenleg nem lehet csövet csőhöz csatlakoztatni.");
        return false;
    }

    public void AddNeighbour(ActiveElement newNeighbour)
    {
        neighbours.Add(newNeighbour);
    }

    public override IPipe PickUpFreePipeEnd()
    {
        System.out.println("Bocs tesa ez nem fog menni. Jelenleg nem lehet szabad csővég a csövön.");
        return null;
    }

    public override IPump PickUpPump()
    {
        System.out.println("Bocs tesa ez nem fog menni. Jelenleg nincs felvehető pumpa csövön.");
        return null;
    }

    override public boolean AcceptPlayer(Player player)
    {
        if (Players.Count() < Constants.AcceptedPlayersInPipe)
        {
            Players.Add(player);

            return true;
        }

        System.out.println("Cső nem tud fogadni, mert tele van. Válassz más műveletet.");
        return false;
    }

    public override boolean TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx)
    {
        System.out.println("Nem csinálunk semmit, a cső input/output nem állítható.");
        return false;
    }

    public override IPipe DisconnectNeighbourPipe(int neighbourIdx)
    {
        System.out.println("Nem csinálunk semmit, cső szomszédja nem lecsatlakoztatható.");
        return null;
    }

    public void RemoveNeighbour(ActiveElement neighbour) => neighbours.Remove(neighbour);

    public IEnumerable<ActiveElement> GetNeighboursOfPipe() => neighbours;
}