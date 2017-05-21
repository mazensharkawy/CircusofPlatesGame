package Game;

import eg.edu.alexu.csd.oop.game.GameEngine;

public interface State{
    public State changeState(GameEngine.GameController gamecontroller);
}