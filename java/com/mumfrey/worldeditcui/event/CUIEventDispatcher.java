package com.mumfrey.worldeditcui.event;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.mumfrey.worldeditcui.InitialisationFactory;
import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.exceptions.InitialisationException;

/**
 * @author Adam Mummery-Smith
 */
public class CUIEventDispatcher implements InitialisationFactory
{
	private WorldEditCUI controller;
	
	private Map<String, Constructor<? extends CUIEvent>> eventConstructors = new HashMap<String, Constructor<? extends CUIEvent>>();

	public CUIEventDispatcher(WorldEditCUI controller)
	{
		this.controller = controller;
	}

	@Override
	public void initialise() throws InitialisationException
	{
		for (CUIEventType eventType : CUIEventType.values())
		{
			try
			{
				Class<? extends CUIEvent> eventClass = eventType.getEventClass();
				Constructor<? extends CUIEvent> ctor = eventClass.getDeclaredConstructor(CUIEventArgs.class);

				this.eventConstructors.put(eventType.getKey(), ctor);
			}
			catch (NoSuchMethodException ex)
			{
				ex.printStackTrace();
				this.controller.getDebugger().debug("Error getting constructor for event " + eventType.getKey());
			}
		}
	}

	public void raiseEvent(CUIEventArgs eventArgs)
	{
		try
		{
			Constructor<? extends CUIEvent> eventCtor = this.eventConstructors.get(eventArgs.getType());
			if (eventCtor == null)
			{
				this.controller.getDebugger().debug("No such event " + eventArgs.getType());
				return;
			}
			
			CUIEvent event = eventCtor.newInstance(eventArgs);
			event.prepare();
			
			String response = event.raise();
			if (response != null)
			{
				this.handleEventResponse(response);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			this.controller.getDebugger().debug("Error raising event " + eventArgs.getType() + ": " + ex.getClass().getSimpleName() + " " + ex.getMessage());
		}
	}

	private void handleEventResponse(String response)
	{
	}
}
