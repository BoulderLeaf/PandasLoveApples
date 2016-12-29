package com.pandeagames.www.gutterballredux.gameControllers.Levels;

/**
 * Created by ccove on 12/28/2016.
 */

public class LevelDef {

    private String id;
    private int name;

    public LevelDef(String id, int name) {
        this.id = id;
        this.name = name;
    }

    public int getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
