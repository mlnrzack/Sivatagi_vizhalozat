package game.interfaces;

import game.*;
import game.elements.*;
import game.interfaces.*;
import game.players.*;

public interface IElement
{
    boolean TryRepair();
    boolean TryDamage();
    boolean TryBuildPumpInto(IPump pump);
    IPipe DisconnectNeighbourPipe(int neighbourIdx);
    boolean TryConnectPipe(IPipe pipeInInventory);
    IPipe PickUpFreePipeEnd();
    IPump PickUpPump();
    boolean TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx);

    int GetWaterInside();
    void SetWaterInside(int waterInside);
    void DecreaseWater();
    boolean FillWaterTo();
    void WaterToDesert();
    boolean AcceptPlayer(Player player);
    boolean RemovePlayer(Player player);
    ArrayList<IElement> GetNeighbours(); //IEnumerable
}