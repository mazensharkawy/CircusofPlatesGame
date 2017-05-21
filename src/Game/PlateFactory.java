/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author HP-
 */
public class PlateFactory {
    HashMap<Color,PlateObject> plateMap;
    public PlateFactory(){
        plateMap=new HashMap<>();
    }
    
    public PlateObject getPlate(Color color){
       /* if(plateMap.containsKey(color)){
            PlateObject plate= plateMap.get(color);
        
            return plateMap.get(color);
        }
        else return plateMap.put(color,new PlateObject(color));*/
       return new PlateObject(color);
                
    }
}
