package com.pandeagames.www.gutterballredux.infoHolders;

import com.pandeagames.www.gutterballredux.gameObjects.AppleType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccove on 10/2/2017.
 */

public class StageScore {

    private int _pandas;
    private int _par;
    private int _basePoints;
    private int _totalPoints;
    private int _apples;
    private int _applePointValue;
    private int _parValue;
    private int _underParValue;
    private int _token = 0;
    private ArrayList<ArrayList<Integer>> combos;

    public StageScore()
    {
        combos = new ArrayList<ArrayList<Integer>>();
    }

    public void  setPandas(int value){ _pandas = value; }
    public int getPandas(){ return _pandas; }

    public void  setPar(int value){ _par = value; }
    public int getPar(){ return _par; }

    public int  addApple(int token, AppleType type) {
        combos.get(token).add(type == AppleType.NORMAL ? 0:1);
        return type == AppleType.NORMAL ? _applePointValue:0;
    }

    public void  setApples(int value){ _apples = value; }
    public int getApples(){ return _apples; }

    public void  setApplePointValue(int value){ _applePointValue = value; }
    public int getApplePointValue(){ return _applePointValue; }

    public void  setParValue(int value){ _parValue = value; }
    public int getParValue(){ return _parValue; }

    public void  setUnderParValue(int value){ _underParValue = value; }
    public int getUnderParValue(){ return _underParValue; }

    public int getComboToken() {
        combos.add(new ArrayList());
        return _token++;
    }

    public int getParBonus() {
        int size = combos.size();
        return size > _par ? 0:size == _par ? _parValue:_underParValue;
    }

    public int getTokenCount() {
        return combos.size();
    }

    public int getComboPoints(int token) {
        int combo = 0;

        for (int apple:combos.get(token)) {
            combo = apple == 0 ? combo+1:combo*2;
        }

        return combo * _applePointValue;
    }

    public int getBasePoints() {
        int points = 0;
        int combo = 0;
        for (ArrayList<Integer> comboList:combos) {
            for (int apple:comboList) {
                combo = apple == 0 ? combo+1:combo*2;
            }

            points += combo;
        }

        return points * _applePointValue;
    }

    public int getTotalPoints() {
        return getBasePoints() + getParBonus();
    }
}
