package com.pandeagames.www.gutterballredux.Components.interfaces;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

public interface IContactCallback {
	public void onBeginContact(Contact c);
	public void onEndContact(Contact c);
	public void onPreSolve(Contact c, Manifold manifold);
	public void onPostSolve(Contact c, ContactImpulse cImpulse);
}
