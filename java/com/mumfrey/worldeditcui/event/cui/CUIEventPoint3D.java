package com.mumfrey.worldeditcui.event.cui;

import com.mumfrey.worldeditcui.event.CUIEvent;
import com.mumfrey.worldeditcui.event.CUIEventArgs;
import com.mumfrey.worldeditcui.event.CUIEventType;
import com.mumfrey.worldeditcui.render.region.Region;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

/**
 * Called when point event is received
 * 
 * @author lahwran
 * @author yetanotherx
 * @author Adam Mummery-Smith
 */
public class CUIEventPoint3D extends CUIEvent
{
	public CUIEventPoint3D(CUIEventArgs args)
	{
		super(args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.POINT;
	}
	
	@Override
	public String raise()
	{
		Region selection = this.controller.getSelection(this.multi);
		if (selection == null)
		{
			this.controller.getDebugger().debug("No active multi selection.");
			return null;
		}

		int id = this.getInt(0);
		
		if (this.multi && "~".equals(this.getString(1)) && "~".equals(this.getString(2)) && "~".equals(this.getString(3)))
		{
			Minecraft mc = Minecraft.getMinecraft();
			Entity entity = mc.getRenderViewEntity();
			double hitDistance = mc.playerController.getBlockReachDistance();
			
			selection.setCuboidVertexLatch(id, entity, Math.min(Math.max(this.getDouble(4), hitDistance), 256.0));
			this.controller.getDebugger().debug("Setting vertex latch #" + id);
			return null;
		}
		
		double x = this.getDouble(1);
		double y = this.getDouble(2);
		double z = this.getDouble(3);
		
		selection.setCuboidPoint(id, x, y, z);
		this.controller.getDebugger().debug("Setting point #" + id);
		
		return null;
	}
}
