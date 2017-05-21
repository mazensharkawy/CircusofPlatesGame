/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;


import java.awt.Color;

/**
 *
 * @author HP-
 */
public class ColorFactory {
    public static Color getRandomColor(){
        Color[] colorPalette={Color.BLUE,Color.RED,Color.GREEN};
        return colorPalette[(int)(Math.random()*colorPalette.length)];
    }
}
