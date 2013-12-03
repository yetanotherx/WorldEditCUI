package wecui.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import wecui.InitializationFactory;
import wecui.render.LineColor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mumfrey.liteloader.core.LiteLoader;

/**
 * Stores and reads WorldEditCUI settings
 * 
 * @author yetanotherx
 * 
 */
public class CUIConfiguration implements InitializationFactory
{
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	protected boolean debugMode = false;
	protected boolean ignoreUpdates = false;
	
	private Colour cuboidGridColor = new Colour("#CC3333");
	private Colour cuboidEdgeColor = new Colour("#CC4C4C");
	private Colour cuboidFirstPointColor = new Colour("#33CC33");
	private Colour cuboidSecondPointColor = new Colour("#3333CC");
	private Colour polyGridColor = new Colour("#CC3333");
	private Colour polyEdgeColor = new Colour("#CC4C4C");
	private Colour polyPointColor = new Colour("#33CCCC");
	private Colour ellipsoidGridColor = new Colour("#CC4C4C");
	private Colour ellipsoidPointColor = new Colour("#CCCC33");
	private Colour cylinderGridColor = new Colour("#CC3333");
	private Colour cylinderEdgeColor = new Colour("#CC4C4C");
	private Colour cylinderPointColor = new Colour("#CC33CC");
	
	/**
	 * Copies the default config file to the proper directory if it does not
	 * exist. It then reads the file and sets each variable to the proper value.
	 */
	@Override
	public void initialize()
	{
		this.cuboidGridColor        = Colour.setDefault(this.cuboidGridColor,        "#CC3333");
		this.cuboidEdgeColor        = Colour.setDefault(this.cuboidEdgeColor,        "#CC4C4C");
		this.cuboidFirstPointColor  = Colour.setDefault(this.cuboidFirstPointColor,  "#33CC33");
		this.cuboidSecondPointColor = Colour.setDefault(this.cuboidSecondPointColor, "#3333CC");
		this.polyGridColor          = Colour.setDefault(this.polyGridColor,          "#CC3333");
		this.polyEdgeColor          = Colour.setDefault(this.polyEdgeColor,          "#CC4C4C");
		this.polyPointColor         = Colour.setDefault(this.polyPointColor,         "#33CCCC");
		this.ellipsoidGridColor     = Colour.setDefault(this.ellipsoidGridColor,     "#CC4C4C");
		this.ellipsoidPointColor    = Colour.setDefault(this.ellipsoidPointColor,    "#CCCC33");
		this.cylinderGridColor      = Colour.setDefault(this.cylinderGridColor,      "#CC3333");
		this.cylinderEdgeColor      = Colour.setDefault(this.cylinderEdgeColor,      "#CC4C4C");
		this.cylinderPointColor     = Colour.setDefault(this.cylinderPointColor,     "#CC33CC");
		
		LineColor.CUBOIDBOX.setColor(this.cuboidEdgeColor);
		LineColor.CUBOIDGRID.setColor(this.cuboidGridColor);
		LineColor.CUBOIDPOINT1.setColor(this.cuboidFirstPointColor);
		LineColor.CUBOIDPOINT2.setColor(this.cuboidSecondPointColor);
		LineColor.POLYGRID.setColor(this.polyGridColor);
		LineColor.POLYBOX.setColor(this.polyEdgeColor);
		LineColor.POLYPOINT.setColor(this.polyPointColor);
		LineColor.ELLIPSOIDGRID.setColor(this.ellipsoidGridColor);
		LineColor.ELLIPSOIDCENTER.setColor(this.ellipsoidPointColor);
		LineColor.CYLINDERGRID.setColor(this.cylinderGridColor);
		LineColor.CYLINDERBOX.setColor(this.cylinderEdgeColor);
		LineColor.CYLINDERCENTER.setColor(this.cylinderPointColor);
		
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
	
	public static CUIConfiguration create()
	{
		File jsonFile = new File(LiteLoader.getCommonConfigFolder(), "worldeditcui.config.json");
		
		if (jsonFile.exists())
		{
			FileReader fileReader = null;
			
			try
			{
				fileReader = new FileReader(jsonFile);
				CUIConfiguration config = CUIConfiguration.gson.fromJson(fileReader, CUIConfiguration.class);
				return config;
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				try
				{
					if (fileReader != null)
						fileReader.close();
				}
				catch (IOException ex)
				{
				}
			}
		}
		
		return new CUIConfiguration();
	}

	public void save()
	{
		File jsonFile = new File(LiteLoader.getCommonConfigFolder(), "worldeditcui.config.json");
		
		FileWriter fileWriter = null;
		
		try
		{
			fileWriter = new FileWriter(jsonFile);
			CUIConfiguration.gson.toJson(this, fileWriter);
		}
		catch (IOException ex) { ex.printStackTrace(); }
		finally
		{
			try
			{
				if (fileWriter != null) fileWriter.close();
			}
			catch (IOException ex) { ex.printStackTrace(); }
		}
	}
}
