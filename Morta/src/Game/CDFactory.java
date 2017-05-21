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
public class CDFactory {
    HashMap<Color,CDObject> plateMap;
    public CDFactory(){
        plateMap=new HashMap<>();
    }
    
    public CDObject getPlate(Color color){
       /* if(plateMap.containsKey(color)){
            CDObject plate= plateMap.get(color);
        
            return plateMap.get(color);
        }
        else return plateMap.put(color,new CDObject(color));*/
       return new CDObject(color);
                
    }
}
