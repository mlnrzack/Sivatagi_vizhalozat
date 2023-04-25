package game.interfaces;

import java.util.*;

import game.players.*;
import game.elements.*;

public interface IElement
{
    boolean TryRepair();
    boolean TryDamage();
    boolean TryBuildPumpInto(Pump pump);
    Pipe DisconnectNeighbourPipe(int neighbourIdx);
    boolean TryConnectPipe(Pipe pipeInInventory);
    Pipe PickUpFreePipeEnd();
    Pump PickUpPump();
    boolean TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx);

    int GetWaterInside();
    void SetWaterInside(int waterInside);
    void DecreaseWater();
    boolean FillWaterTo();
    void WaterToDesert();
    boolean AcceptPlayer(Player player);
    boolean RemovePlayer(Player player);
    ArrayList<? extends IElement> GetNeighbours(); //IEnumerable
}