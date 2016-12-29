package com.pandeagames.www.gutterballredux.gameControllers.Levels;

/**
 * Created by ccove on 12/28/2016.
 */

public class AppleLevelDef extends LevelDef {

    private int appleLockCount;
    private int bgResId;
    private int miniBgResId;
    private int miniBgResLockedId;
    private int miniBgResUnplayedId;

    public AppleLevelDef(String id, int displayName, int appleLockCount, int bgResId, int miniBgResId, int miniBgResLockedId, int miniBgResUnplayedId){
        super(id, displayName);

        this.appleLockCount = appleLockCount;
        this.bgResId = bgResId;
        this.miniBgResId = miniBgResId;
        this.miniBgResLockedId = miniBgResLockedId;
        this.miniBgResUnplayedId = miniBgResUnplayedId;
    }

    public int getMiniBgResUnplayedId() {
        return miniBgResUnplayedId;
    }

    public int getAppleLockCount() {
        return appleLockCount;
    }

    public int getBgResId() {
        return bgResId;
    }

    public int getMiniBgResId() {
        return miniBgResId;
    }

    public int getMiniBgResLockedId() {
        return miniBgResLockedId;
    }
}
