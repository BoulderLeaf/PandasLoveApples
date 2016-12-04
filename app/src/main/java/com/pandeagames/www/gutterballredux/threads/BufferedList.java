package com.pandeagames.www.gutterballredux.threads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import android.graphics.Bitmap;
import android.webkit.WebIconDatabase.IconListener;

public class BufferedList<E> extends ArrayList<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ArrayList<E> removeBuffer;
	protected ArrayList<E> addBuffer;
	public BufferedList() {
		// TODO Auto-generated constructor stub
		addBuffer = new ArrayList<E>();
		removeBuffer = new ArrayList<E>();
	}
	@Override
public boolean add(E object)
	{
		addBuffer.add(object);
	return true;
	}
	@Override
	public boolean remove(Object object)
	{
		removeBuffer.add((E) object);
		return true;
	}
	public void clearBuffer(){
		ArrayList<E> testList=this;
		super.addAll(addBuffer);
		super.removeAll(removeBuffer);
		addBuffer.clear();
		removeBuffer.clear();
	}
@Override
public boolean addAll(Collection<? extends E> collection){
	addBuffer.addAll(collection);
	return true;
}
@Override
public boolean removeAll(Collection<? extends Object> collection){
	removeBuffer.addAll((Collection<? extends E>) collection);
	return true;
}
@Override
public boolean addAll(int index,Collection<? extends E> collection){
	return addAll(collection);
}
@Override
public void removeRange(int start, int end){
removeBuffer.addAll(this.subList(start, end));
}


}
