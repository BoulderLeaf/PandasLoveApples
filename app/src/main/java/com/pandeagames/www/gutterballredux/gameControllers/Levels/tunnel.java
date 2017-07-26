package com.pandeagames.www.gutterballredux.gameControllers.Levels;

import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.gameControllers.Levels.Geom.TunnelGeom;
import com.pandeagames.www.gutterballredux.gameObjects.AppleType;
import com.pandeagames.www.gutterballredux.gameObjects.scripts.BreakableBushMedium;
import com.pandeagames.www.gutterballredux.gameObjects.scripts.BreakableBushSmall;
import com.pandeagames.www.gutterballredux.Components.BodyComponent;

import org.jbox2d.common.Vec2;

public class tunnel extends ThrowLevel {
    public tunnel(Game game, BodyComponent geometry, LevelDef levelDef) {
        super(game, geometry, levelDef);
        // TODO Auto-generated constructor stub

        //accross the top
        createPortal(10.5f, 1.5f);
        createPortal(13.5f, 1.5f);
        createPortal(16.5f, 1.5f);
        createPortal(19.5f, 1.5f);
        createPortal(22.5f, 1.5f);

        //down the right side
        createPortal(22.5f, 4.5f);
        createPortal(22.5f, 7.5f);
        createPortal(22.5f, 10.5f);
        createPortal(22.5f, 13.5f);
        createPortal(22.5f, 16.5f);
        createPortal(22.5f, 19.5f);
        createPortal(22.5f, 22.5f);
        createPortal(22.5f, 25.5f);
        createPortal(22.5f, 28.5f);
        createPortal(22.5f, 31.5f);
        createPortal(22.5f, 34.5f);

        //golds in hard to get spots
        createPortal(19.5f, 25.5f, AppleType.GOLDEN);
        createPortal(16.5f, 25.5f, AppleType.GOLDEN);

        createPortal(19.5f, 13.5f, AppleType.GOLDEN);
        createPortal(16.5f, 13.5f, AppleType.GOLDEN);

        //down the middle
        createPortal(10.5f, 4.5f);
        createPortal(13.5f, 4.5f);
        createPortal(10.5f, 7.5f);
        createPortal(13.5f, 7.5f);
        createPortal(10.5f, 10.5f);
        createPortal(13.5f, 10.5f);
        createPortal(10.5f, 13.5f);
        createPortal(13.5f, 13.5f);
        createPortal(10.5f, 16.5f);
        createPortal(13.5f, 16.5f);
        createPortal(10.5f, 19.5f);
        createPortal(13.5f, 19.5f);
        createPortal(10.5f, 22.5f);
        createPortal(13.5f, 22.5f);
        createPortal(10.5f, 25.5f);
        createPortal(13.5f, 25.5f);
        createPortal(10.5f, 28.5f);
        createPortal(13.5f, 28.5f);
        createPortal(10.5f, 31.5f);
        createPortal(13.5f, 31.5f);
        createPortal(10.5f, 34.5f);
        createPortal(13.5f, 34.5f);

        //on the left side
        createPortal(1.5f, 7.5f);
        createPortal(1.5f, 10.5f);
        createPortal(1.5f, 22.5f);
        createPortal(1.5f, 19.5f);


       // addBreakable(new BreakableBushMedium(game, new Vec2(6.5f, 33f)));
        addBreakable(new BreakableBushMedium(game, new Vec2(3f, 33f)));
        addBreakable(new BreakableBushMedium(game, new Vec2(6f, 21f)));
       // addBreakable(new BreakableBushMedium(game, new Vec2(3f, 21f)));
        addBreakable(new BreakableBushMedium(game, new Vec2(6f, 9f)));

        addBreakable(new BreakableBushSmall(game, new Vec2(7.5f, 34.5f)));
        addBreakable(new BreakableBushSmall(game, new Vec2(7.5f, 31.5f)));
        addBreakable(new BreakableBushSmall(game, new Vec2(7.5f, 28.5f)));
        addBreakable(new BreakableBushSmall(game, new Vec2(7.5f, 25.5f)));

        addBreakable(new BreakableBushSmall(game, new Vec2(7.5f, 13.5f)));
        addBreakable(new BreakableBushSmall(game, new Vec2(7.5f, 16.5f)));
    }

    public tunnel(Game game, LevelDef levelDef) {
        this(game, new TunnelGeom(game), levelDef);
        // TODO Auto-generated constructor stub
    }
    public void destroy(){
        super.destroy();
    }
}

