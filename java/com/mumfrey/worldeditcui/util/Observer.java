package com.mumfrey.worldeditcui.util;

/**
 * Observer for {@link Observable}
 * 
 * @author Adam Mummery-Smith
 */
public interface Observer
{
	public abstract void notifyChanged(Observable<?> source);
}
