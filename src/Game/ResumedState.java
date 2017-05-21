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
public class ResumedState implements State {

    public ResumedState() {
    }

    @Override
    public State changeState(GameEngine.GameController gamecontroller) {
        gamecontroller.pause();
        return new PausedState();
    }
    
}
