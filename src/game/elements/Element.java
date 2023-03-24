package game.elements;

import java.util.*;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public abstract class Element implements IElement
{
    private int WaterInside;// { get; set; }
    private ArrayList<Player> Players /*{ get; set; }*/ = new ArrayList<Player>();

    public int GetWaterInside() => WaterInside;

    public void SetWaterInside(int waterInside) => WaterInside = waterInside;

    public virtual void DecreaseWater() => WaterInside--;

    // true ha sikerül vizet fogadnia
    public bool FillWaterTo()
    {
        if (WaterInside < Constants.PipeCapacity)
        {
            WaterInside++;

            return true;
        }

        return false;
    }

    public void WaterToDesert()
    {
        Desert.IncreaseWaterFromPipelineNetwork(WaterInside);
        WaterInside = 0;
    }

    public bool RemovePlayer(Player player) => Players.Remove(player);
    
    public abstract bool AcceptPlayer(Player player);
    public abstract IEnumerable<IElement> GetNeighbours();
    public abstract bool TryRepair();
    public abstract bool TryDamage();
    public abstract bool TryBuildPumpInto(IPump pump);
    public abstract IPipe DisconnectNeighbourPipe(int neighbourIdx);
    public abstract bool TryConnectPipe(IPipe pipeInInventory);
    public abstract IPipe PickUpFreePipeEnd();
    public abstract IPump PickUpPump();
    public abstract bool TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx);
}
