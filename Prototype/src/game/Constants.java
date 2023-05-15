package game;

public class Constants
{
	public static final int PumpErrorProbability = 150;				//a pumpa elromlásának valószínűsége
    public static final int PipeCapacity = 1;						//a cső térfogata
    public static final int PumpWaterCapacity = 1;					//a pumpa kapacitása, mennyi vizet képes továbbítani 
    public static final int MaxNeighboursOfActiveElements = 4;		//az aktív elemekhez csatlakoztatott csövek maximum száma 
    public static final int RoundNumber = 20;						//a játék köreinek a száma.
    public static final int ActionInRoundPerUser = 2;				//egy adott kör alatt a játékos végrehajtott feladatainak a száma
    public static final int AcceptedPlayersInPipe = 1;				//a csövön tartózkodó játékosok száma
    public static final int LeakageTimerBound = 1;					//lyukas cső időzítője
}