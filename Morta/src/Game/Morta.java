/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Game.ImageObject;

/**
 *
 * @author HP-
 */
public class Morta extends ImageObject{
    
    public Morta(int posX, int posY, String path) {
        super(posX, posY-50, path);
    }

    @Override
    public void setY(int mY) {
         //To change body of generated methods, choose Tools | Templates.
    }
    
}
