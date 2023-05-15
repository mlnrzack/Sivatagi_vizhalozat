package game.elements;

import game.*;

import java.util.ArrayList;

public class WaterSpring extends ActiveElement
{

    private int _id = 0;
    public static ArrayList<Integer> WaterSpringIDs = new ArrayList<>();
    public WaterSpring() 
    {
//        int max = WaterSpringIDs.get(0);
//        for (var item: WaterSpringIDs) {
//            if (item > max) max = item;
//        }
//        _id = max+1;
//        WaterSpringIDs.add(_id);
//        typeString = "WaterSpring";

        GameManager.AddWaterSpring(this);
    }

//    public WaterSpring(int id){
//        _id = id;
//        WaterSpringIDs.add(id);
//        typeString = "WaterSpring";
//
//    }

    public void FillNeighourPipes()
    {    	
    	for(int i = 0; i < this.neighbours.size(); i++)
        {
    		this.neighbours.get(i).FillWaterTo();
        }
    }
}