package com.pandeagames.www.gutterballredux.Components.interfaces;

import com.pandeagames.www.gutterballredux.gameControllers.RadialCollider;
import com.pandeagames.www.gutterballredux.infoHolders.Radial;

public interface IRadialCollider {
public void radialCollide(RadialCollider other);
public float getX();
public float getY();
}
