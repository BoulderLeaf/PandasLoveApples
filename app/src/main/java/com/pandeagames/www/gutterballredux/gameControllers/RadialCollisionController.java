package com.pandeagames.www.gutterballredux.gameControllers;

import com.pandeagames.www.gutterballredux.threads.BufferedList;
import com.pandeagames.www.gutterballredux.infoHolders.Radial;
import com.pandeagames.www.gutterballredux.infoHolders.UpdateInfo;
import com.pandeagames.www.gutterballredux.Components.AbstractGameComponent;
import com.pandeagames.www.gutterballredux.Components.interfaces.IRadialCollider;

public class RadialCollisionController extends AbstractGameComponent {
	private BufferedList<RadialCollider> list;
	public RadialCollisionController(Game game) {
		super(game);
		list=new BufferedList<RadialCollider>();
		// TODO Auto-generated constructor stub
	}
public void addCollider(RadialCollider collider){
	list.add(collider);
}
public void removeCollider(RadialCollider collider){
	list.remove(collider);
}
	@Override
	public void update(UpdateInfo updateInfo) {
		// TODO Auto-generated method stub
for(int i=0;i<list.size()-1; i++){
	RadialCollider col1=list.get(i);
	if(col1.destroyed())continue;
	Radial r1=col1.getRadial();
	
	for (int j=i+1;j<list.size();j++)
	{
		RadialCollider col2=list.get(j);
		if(col2.destroyed() || col1.destroyed())continue;
		Radial r2=col2.getRadial();
		if(getCircleHit(r1.x,
				r1.y,
				r1.r,
				r2.x,
				r2.y,
				r2.r)){
			col1.getCollider().radialCollide(col2);
			col2.getCollider().radialCollide(col1);
		}
	}
	
}
list.clearBuffer();
	}
	private boolean getCircleHit(float x1, float y1,float r1, float x2, float y2, float r2){
		if(getDist(x1,y1,x2,y2)<r1+r2)return true;
		return false;
	}
	private float getDist(float x1, float y1, float x2, float y2){
		float dx = x2-x1;
		float dy = y2-y1;
		return (float) Math.sqrt(dx*dx+dy*dy);
		
	}

}
