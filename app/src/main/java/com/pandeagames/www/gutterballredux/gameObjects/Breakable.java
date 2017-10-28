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
    protected float breakVelocity = 15;
    private boolean queueBreak = false, queueCollide = false;
    private BodyComponent queudBody = null;
    protected Vec2 collideVelocity;
    public Breakable(Game game) {
        this(game, new Vec2(0, 0));
    }
    public Breakable(Game game, Vec2 pos){
        super(game);
        bodyDef.position.set(pos);
    }

    public Breakable(Game game, Vec2 pos, Vec2 linearVelocity){
        this(game, pos);
        bodyDef.linearVelocity.set(linearVelocity);
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
        BodyComponent other = null;
        double velocity;

        while(contact!=null)
        {
            other = (BodyComponent)contact.other.getUserData();
            queudBody = other;
            velocity = other.getVelocity();

            if(other.containsCollisionGroup(collisionLayer) && velocity > breakVelocity && contact.contact.isTouching()){
                if(!queueCollide){
                    collideVelocity = other.getBody().getLinearVelocity().clone();
                }
                hasCollide = true;
                queueCollide = true;

                break;
            }
            contact=contact.next;
        }

        if(queueCollide && !hasCollide){
            onCollide( queudBody );
            collisionCount++;
            queueCollide = false;
        }

        if(collisionCount >= health){
            queueBreak = true;
        }
    }

    protected void onCollide(BodyComponent other){}
    protected void onBreak(){}
}
