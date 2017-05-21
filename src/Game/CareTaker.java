/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP-
 */
public class CareTaker {
    List<ScoreMemento> scores;
    public CareTaker(){
        scores=new ArrayList<>();
    }

    public List<ScoreMemento> getScores() {
        return scores;
    }

    public void addScore(ScoreMemento score) {
        scores.add(score);
    }
}
