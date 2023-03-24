package game.elements;

import java.util.ArrayList;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public abstract class ActiveElement extends Element
{
    private ArrayList<IPipe> Neighbours /*{ get; set; }*/ = new ArrayList<IPipe>();

    public override bool TryBuildPumpInto(IPump pump)
    {
        // override-olni leszármazottakban, ott megvalósítani ha be lehet építeni pumpát.
        System.out.println("Nem csinálunk semmit, a pumpa nem beépíthető.");
        return false;
    }

    public override bool TryDamage()
    {
        // override-olni leszármazottakban, ott megvalósítani ha tönkretehető.
        System.out.println("Nem csinálunk semmit, a pályaelem nem tönkretehető.");
        return false;
    }

    public override bool TryRepair()
    {
        // override-olni leszármazottakban, ott megvalósítani ha javatható.
        System.out.println("Nem csinálunk semmit, a pályaelem nem javítható.");
        return false;
    }

    public override bool TrySetInputOutput(int inputIdx, int outputIdx)
    {
        // override-olni leszármazottakban, ott megvalósítani ha beállítható.
        System.out.println("Nem csinálunk semmit, a pályaelem nem állítható.");
        return false;
    }

    public override IPipe DisconnectNeighbourPipe(int neighbourIdx)
    {
        // override-olni leszármazottakban, ott megvalósítani ha beállítható.
        System.out.println("Nem csinálunk semmit, az elem nem lecsatlakoztatható.");
        return null;
    }

    public override bool TryConnectPipe(IPipe pipeInInventory)
    {
        if (Neighbours.Count() < Constants.MaxNeighboursOfActiveElements)
        {
            pipeInInventory.AddNeighbour(this);

            return true;
        }

        return false;
    }

    public override IPipe PickUpFreePipeEnd()
    {
        // override-olni leszármazottakban, ott megvalósítani ha felvehető az adott típuson szabad csővég.
        System.out.println("Nem csinálunk semmit, szabad csővég nem felvehető.");
        return null;
    }

    public override IPump PickUpPump()
    {
        // override-olni leszármazottakban, ott megvalósítani ha felvehető az adott típuson pumpa.
        System.out.println("Nem csinálunk semmit, szabad csővég nem felvehető.");
        return null;
    }

    public override IEnumerable<IElement> GetNeighbours() => Neighbours;

    public override bool AcceptPlayer(Player player)
    {
        Players.ToList().Add(player);

        return true;
    }
}
