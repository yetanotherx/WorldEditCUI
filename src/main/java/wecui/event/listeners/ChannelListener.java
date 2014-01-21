package wecui.event.listeners;

import wecui.WorldEditCUI;
import wecui.event.CUIEvent;
import wecui.event.cui.CUIBaseEvent;
import wecui.event.cui.CUIEventType;
import wecui.exception.ReflectException;
import wecui.vendor.org.joor.Reflect;

/**
 * Listener class for incoming plugin channel messages
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class ChannelListener
{
	private WorldEditCUI controller;
	
	public ChannelListener(WorldEditCUI controller)
	{
		this.controller = controller;
	}
	
	public void onMessage(String message)
	{
		String[] split = message.split("[|]");
		String type = split[0];
		String args = message.substring(type.length() + 1);
		
		this.controller.getDebugger().debug("Received CUI event from server: " + message);
		
		try
		{
			CUIEvent cuiEvent = new CUIEvent(this.controller, type, args.split("[|]"));
			this.processCUIEvent(cuiEvent);
		}
		catch (Exception ex)
		{
		}
	}

	public void processCUIEvent(CUIEvent event)
	{
		// Get a CUIEventType enum value from the first section of the CUI message
		CUIEventType eventType = CUIEventType.getTypeFromKey(event.getType());
		if (eventType == null || eventType.getEventClass() == null)
		{
			return;
		}
		
		try
		{
			CUIBaseEvent newEvent = Reflect.on(eventType.getEventClass()).create(this.controller, event.getParams()).get();
			
			//Run the event. If doRun returns null, the event was successful. 
			//If it returns a string, it uses that as the error message.
			newEvent.doRun();
		}
		catch (ReflectException e)
		{
			return;
		}
	}
}
