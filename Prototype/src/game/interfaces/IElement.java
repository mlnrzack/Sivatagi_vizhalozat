package game.interfaces;

import java.util.*;

import game.players.*;
import game.elements.*;

public interface IElement
{
	boolean AcceptPlayer(Player player);
	Pipe DisconnectNeighbourPipe(int neighbourIdx);
	boolean FillWaterTo();
	ArrayList<? extends IElement> GetNeighbours();
	int GetWaterInside();
	Pipe PickUpFreePipeEnd();
    Pump PickUpPump();
    boolean RemovePlayer(Player player);
    void SetWaterInside(int waterInside);
    boolean TryBuildPumpInto(Pump pump);
    boolean TryConnectPipe(Pipe pipeInInventory);
    boolean TryDamage();
    boolean TryRepair();
    boolean TrySetInputOutput(int neighbourFromIdx, int neighbourToIdx);
    boolean TrySetSlippery();
    boolean TrySetSticky();
    void WaterToDesert();
}