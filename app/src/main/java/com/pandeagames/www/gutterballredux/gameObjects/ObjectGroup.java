package com.pandeagames.www.gutterballredux.gameObjects;

/**
 * Created by ccove on 7/9/2017.
 */

public enum ObjectGroup {
    GEOM(0), OBJECT(1);

    ObjectGroup(int value) { this.value = value; }

    private final int value;
    public int value() { return value; }
}
