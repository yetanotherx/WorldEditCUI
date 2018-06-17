package com.mumfrey.worldeditcui.render;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.mumfrey.worldeditcui.InitialisationFactory;
import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.exceptions.InitialisationException;
import com.mumfrey.worldeditcui.render.region.Region;
import com.mumfrey.worldeditcui.render.region.RegionType;

/**
 * @author Adam Mummery-Smith
 */
public class CUISelectionProvider implements InitialisationFactory
{
	private Map<String, Constructor<? extends Region>> regionConstructors = new HashMap<String, Constructor<? extends Region>>();

	private WorldEditCUI controller;
	
	public CUISelectionProvider(WorldEditCUI controller)
	{
		this.controller = controller;
	}

	@Override
	public void initialise() throws InitialisationException
	{
		for (RegionType regionType : RegionType.values())
		{
			try
			{
				Class<? extends Region> eventClass = regionType.getRegionClass();
				Constructor<? extends Region> ctor = eventClass.getDeclaredConstructor(WorldEditCUI.class);

				this.regionConstructors.put(regionType.getKey(), ctor);
			}
			catch (NoSuchMethodException ex)
			{
				this.controller.getDebugger().debug("Error getting constructor for region type " + regionType.getKey());
			}
		}
	}
	
	public Region createSelection(String key)
	{
		if ("clear".equals(key))
		{
			return null;
		}
		
		try
		{
			Constructor<? extends Region> regionCtor = this.regionConstructors.get(key);
			Region region = regionCtor.newInstance(this.controller);
			return region;
		}
		catch (NullPointerException ex)
		{
			this.controller.getDebugger().debug("No such selection type " + key);
		}
		catch (Exception ex)
		{
			this.controller.getDebugger().debug("Error creating " + key + " selection: " + ex.getClass().getSimpleName() + " " + ex.getMessage());
		}

		return null;
	}
}
