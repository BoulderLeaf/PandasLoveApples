package com.pandeagames.www.gutterballredux.infoHolders;

import com.pandeagames.www.gutterballredux.gameObjects.AppleType;
import com.pandeagames.www.gutterballredux.gameObjects.launcher.ILauncherListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccove on 10/2/2017.
 */

public class StageScore {


    private ArrayList<IScoreChangeListener> scoreListeners;
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
        scoreListeners=new ArrayList<IScoreChangeListener>();
    }

    public int getPandas(){ return _pandas; }

    public void  setPar(int value){ _par = value; }
    public int getPar(){ return _par; }

    public int  addApple(int token, AppleType type) {
        if(combos.size() <= token)
        {
            return 0;
        }

        combos.get(token).add(type == AppleType.NORMAL ? 0:1);

        _apples++;
        int basePoints = this.getBasePoints();
        int totalPoints = this.getTotalPoints();
        int combo = getCombo(token);

        for (IScoreChangeListener listener:scoreListeners) {
            listener.scoreChange(combos.size(), basePoints, totalPoints,  _apples, combo);
        }

        return type == AppleType.NORMAL ? _applePointValue:0;
    }

    public int getApples(){ return _apples; }

    public void  setApplePointValue(int value){ _applePointValue = value; }
    public int getApplePointValue(){ return _applePointValue; }

    public void  setParValue(int value){ _parValue = value; }
    public int getParValue(){ return _parValue; }

    public void  setUnderParValue(int value){ _underParValue = value; }
    public int getUnderParValue(){ return _underParValue; }

    public int getComboToken() {
        int token = combos.size();
        combos.add(new ArrayList());
        return token;
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

    public int getCombo(int token) {
        int combo = 0;

        for (int apple:combos.get(token)) {
            combo = apple == 0 ? combo:combo+1;
        }

        return combo;
    }

    public int getBasePoints() {
        int points = 0;
        int combo = 0;
        for (ArrayList<Integer> comboList:combos) {
            for (int apple:comboList) {
                combo = apple == 0 ? combo+1:combo*2;
            }

            points += combo;
            combo = 0;
        }

        return points * _applePointValue;
    }

    public int getTotalPoints() {
        return getBasePoints() + getParBonus();
    }

    public void addScoreChangeListener(IScoreChangeListener launcherListener){
        scoreListeners.add(launcherListener);
    }
    public void removeScoreChangeListener(IScoreChangeListener launcherListener){
        scoreListeners.remove(launcherListener);
    }
}
