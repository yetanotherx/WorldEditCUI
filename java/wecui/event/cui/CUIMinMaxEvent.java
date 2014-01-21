package wecui.event.cui;

import wecui.WorldEditCUI;

/**
 * Called when resize event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIMinMaxEvent extends CUIBaseEvent
{
	
	public CUIMinMaxEvent(WorldEditCUI controller, String[] args)
	{
		super(controller, args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.MINMAX;
	}
	
	@Override
	public String run()
	{
		int min = this.getInt(0);
		int max = this.getInt(1);
		this.controller.getSelection().setMinMax(min, max);
		
		this.controller.getDebugger().debug("Expanding/contracting selection.");
		
		return null;
	}
}
