package com.mumfrey.worldeditcui.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Observable object
 * 
 * @param <TObserver> observer type
 * @author Adam Mummery-Smith
 */
public abstract class Observable<TObserver extends Observer>
{
	protected List<TObserver> observers;
	
	public void addObserver(TObserver observer)
	{
		if (this.observers == null)
		{
			this.observers = new ArrayList<TObserver>();
		}
		
		this.observers.add(observer);
	}
	
	protected void notifyObservers()
	{
		if (this.observers != null)
		{
			for (TObserver observer : this.observers)
			{
				observer.notifyChanged(this);
			}
		}
	}
}
