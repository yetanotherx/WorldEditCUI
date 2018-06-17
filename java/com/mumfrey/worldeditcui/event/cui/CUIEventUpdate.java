package com.mumfrey.worldeditcui.event.cui;

import com.mumfrey.worldeditcui.event.CUIEvent;
import com.mumfrey.worldeditcui.event.CUIEventArgs;
import com.mumfrey.worldeditcui.event.CUIEventType;

/**
 * Called when update event is received
 * 
 * @author lahwran
 * @author yetanotherx
 * @author Adam Mummery-Smith
 */
public class CUIEventUpdate extends CUIEvent
{
	public CUIEventUpdate(CUIEventArgs args)
	{
		super(args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.UPDATE;
	}
	
	@Override
	public String raise()
	{
		return null;
	}
}
