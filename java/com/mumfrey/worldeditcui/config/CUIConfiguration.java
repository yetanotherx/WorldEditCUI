package com.mumfrey.worldeditcui.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.worldeditcui.InitialisationFactory;
import com.mumfrey.worldeditcui.render.ConfiguredColour;

/**
 * Stores and reads WorldEditCUI settings
 * 
 * @author yetanotherx
 * @author Adam Mummery-Smith
 */
public final class CUIConfiguration implements InitialisationFactory
{
	private static final String CONFIG_FILE_NAME = "worldeditcui.config.json";

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	
	@Expose private boolean debugMode = false;
	@Expose private boolean ignoreUpdates = false;
	@Expose private boolean promiscuous = false;
	@Expose private boolean alwaysOnTop = false;
	@Expose private boolean clearAllOnKey = false;
	
	@Expose private Colour cuboidGridColor        = ConfiguredColour.CUBOIDBOX.getDefault();
	@Expose private Colour cuboidEdgeColor        = ConfiguredColour.CUBOIDGRID.getDefault();
	@Expose private Colour cuboidFirstPointColor  = ConfiguredColour.CUBOIDPOINT1.getDefault();
	@Expose private Colour cuboidSecondPointColor = ConfiguredColour.CUBOIDPOINT2.getDefault();
	@Expose private Colour polyGridColor          = ConfiguredColour.POLYGRID.getDefault();
	@Expose private Colour polyEdgeColor          = ConfiguredColour.POLYBOX.getDefault();
	@Expose private Colour polyPointColor         = ConfiguredColour.POLYPOINT.getDefault();
	@Expose private Colour ellipsoidGridColor     = ConfiguredColour.ELLIPSOIDGRID.getDefault();
	@Expose private Colour ellipsoidPointColor    = ConfiguredColour.ELLIPSOIDCENTRE.getDefault();
	@Expose private Colour cylinderGridColor      = ConfiguredColour.CYLINDERGRID.getDefault();
	@Expose private Colour cylinderEdgeColor      = ConfiguredColour.CYLINDERBOX.getDefault();
	@Expose private Colour cylinderPointColor     = ConfiguredColour.CYLINDERCENTRE.getDefault();
	@Expose private Colour chunkBoundaryColour    = ConfiguredColour.CHUNKBOUNDARY.getDefault();
	@Expose private Colour chunkGridColour        = ConfiguredColour.CHUNKGRID.getDefault();
	
	/**
	 * Copies the default config file to the proper directory if it does not
	 * exist. It then reads the file and sets each variable to the proper value.
	 */
	@Override
	public void initialise()
	{
		int index = 0;
		try
		{
			for (Field field : this.getClass().getDeclaredFields())
			{
				if (field.getType() == Colour.class)
				{
					ConfiguredColour configuredColour = ConfiguredColour.values()[index++];
					Colour colour = Colour.firstOrDefault((Colour)field.get(this), configuredColour.getColour().getHex());
					field.set(this, colour);
					configuredColour.setColour(colour);
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		this.save();
	}
	
	public boolean isDebugMode()
	{
		return this.debugMode;
	}
	
	public boolean ignoreUpdates()
	{
		return this.ignoreUpdates;
	}
	
	public boolean isPromiscuous()
	{
		return this.promiscuous;
	}
	
	public void setPromiscuous(boolean promiscuous)
	{
		this.promiscuous = promiscuous;
	}
	
	public boolean isAlwaysOnTop()
	{
		return this.alwaysOnTop;
	}
	
	public void setAlwaysOnTop(boolean alwaysOnTop)
	{
		this.alwaysOnTop = alwaysOnTop;
	}
	
	public boolean isClearAllOnKey()
	{
		return this.clearAllOnKey;
	}
	
	public void setClearAllOnKey(boolean clearAllOnKey)
	{
		this.clearAllOnKey = clearAllOnKey;
	}
	
	public static CUIConfiguration create()
	{
		File jsonFile = new File(LiteLoader.getCommonConfigFolder(), CUIConfiguration.CONFIG_FILE_NAME);
		
		if (jsonFile.exists())
		{
			FileReader fileReader = null;
			
			try
			{
				fileReader = new FileReader(jsonFile);
				CUIConfiguration config = CUIConfiguration.GSON.fromJson(fileReader, CUIConfiguration.class);
				return config;
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				try
				{
					if (fileReader != null) fileReader.close();
				}
				catch (IOException ex) {}
			}
		}
		
		return new CUIConfiguration();
	}
	
	public void save()
	{
		File jsonFile = new File(LiteLoader.getCommonConfigFolder(), CUIConfiguration.CONFIG_FILE_NAME);
		
		FileWriter fileWriter = null;
		
		try
		{
			fileWriter = new FileWriter(jsonFile);
			CUIConfiguration.GSON.toJson(this, fileWriter);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if (fileWriter != null) fileWriter.close();
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
}
