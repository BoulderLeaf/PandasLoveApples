package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.GeneratedGeom;

/**
 * Created by ccove on 8/11/2017.
 */

public class thomp extends ThrowLevel {
    public thomp(Game game, LevelDef levelDef){
        super(game, new GeneratedGeom(game, levelDef), levelDef);
    }
}
