package com.pandeagames.www.gutterballredux.gameObjects;

import com.pandeagames.www.gutterballredux.Components.BodyComponent;
import com.pandeagames.www.gutterballredux.gameControllers.Game;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.contacts.ContactEdge;

/**
 * Created by ccove on 12/10/2016.
 */

public class Breakable extends BodyComponent {
    protected CollisionGroups collisionLayer = CollisionGroups.LEVEL_GEOM;
    protected int health = 1;
    protected int collisionCount = 0;
    private boolean queueBreak = false, queueCollide = false;
    public Breakable(Game game, Vec2 pos){

        super(game);

        bodyDef.position.set(pos);
    }

    @ Override
    public void update(UpdateInfo updateInfo){
        super.update(updateInfo);

        if(queueBreak){
            onBreak();
            destroy();
            return;
        }

        ContactEdge contact = body.getContactList();
        boolean hasCollide = false;
        while(contact!=null)
        {
            if(((BodyComponent)contact.other.getUserData()).containsCollisionGroup(collisionLayer)){
                hasCollide = true;
                queueCollide = true;
                break;
            }
            contact=contact.next;
        }

        if(queueCollide && !hasCollide){
            onCollide();
            collisionCount++;
            queueCollide = false;
        }

        if(collisionCount >= health){
            queueBreak = true;
        }
    }

    protected void onCollide(){}
    protected void onBreak(){}
}
