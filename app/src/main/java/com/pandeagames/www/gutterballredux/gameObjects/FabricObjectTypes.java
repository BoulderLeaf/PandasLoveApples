package com.pandeagames.www.gutterballredux.gameObjects;

/**
 * Created by ccove on 7/9/2017.
 */

public enum FabricObjectTypes {
    LINE(0), CIRCLE(1), TRIANGLE(2), ELLIPSE(3), RECT(4), POLYLINE(5), POLYGON(6), GROUP(7), TEXT(8), IMAGE(9), PATH(10);

    FabricObjectTypes(int value) { this.value = value; }

    private final int value;
    public int value() { return value; }
}
