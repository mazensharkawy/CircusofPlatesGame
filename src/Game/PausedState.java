/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import eg.edu.alexu.csd.oop.game.GameEngine;

/**
 *
 * @author HP-
 */

public class PausedState implements State{
    public State changeState(GameEngine.GameController gamecontroller){
        gamecontroller.resume();
        return new ResumedState();
    }
}
