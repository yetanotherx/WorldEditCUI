package wecui;

import wecui.config.CUIConfiguration;
import wecui.exception.InitializationException;
import wecui.render.region.BaseRegion;
import wecui.render.region.CuboidRegion;

/**
 * Main controller class. Uses a pseudo-JavaBeans paradigm. The only real
 * logic here is listener registration.
 * 
 * TODO: Preview mode
 * TODO: Command transactions
 * TODO: Add ability to flash selection
 *  
 * @author yetanotherx
 */
public class WorldEditCUI
{
	public static final int protocolVersion = 3;
	private BaseRegion selection;
	private CUIDebug debugger;
	private CUIConfiguration configuration;
	
	public void initialize()
	{
		this.selection = new CuboidRegion(this);
		this.configuration = CUIConfiguration.create();
		this.debugger = new CUIDebug(this);
		
		try
		{
			this.selection.initialize();
			this.configuration.initialize();
			this.debugger.initialize();
		}
		catch (InitializationException e)
		{
			e.printStackTrace();
			return;
		}
	}
	
	public CUIConfiguration getConfiguration()
	{
		return this.configuration;
	}
	
	public void setConfiguration(CUIConfiguration configuration)
	{
		this.configuration = configuration;
	}
	
	public CUIDebug getDebugger()
	{
		return this.debugger;
	}
	
	public void setDebugger(CUIDebug debugger)
	{
		this.debugger = debugger;
	}
	
	public BaseRegion getSelection()
	{
		return this.selection;
	}
	
	public void setSelection(BaseRegion selection)
	{
		this.selection = selection;
	}
}
