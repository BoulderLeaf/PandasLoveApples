package com.pandeagames.www.gutterballredux.gameObjects.launcher;

public interface ILauncherListener {
	public void enableLauncher(Launcher launcher);
	public void disableLauncher(Launcher launcher);
	public void launch(Launcher launcher, float x, float y);
	public void touchLauncher(Launcher launcher, float x, float y);
	public void pullingLauncher(Launcher launcher, float x, float y);
}
