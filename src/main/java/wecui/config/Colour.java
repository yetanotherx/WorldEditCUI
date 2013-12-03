package wecui.config;

public class Colour
{
	private String hex;
	private transient String defaultColour;

	public Colour(String defaultColour)
	{
		this.hex = defaultColour;
		this.defaultColour = defaultColour;
	}
	
	public Colour()
	{
	}
	
	public static Colour setDefault(Colour colour, String defaultColour)
	{
		if (colour == null)
		{
			return new Colour(defaultColour);
		}
		
		if (colour.hex == null)
		{
			colour.hex = defaultColour;
			colour.defaultColour = defaultColour;
		}
		else
		{
			colour.hex = Colour.parseColour(colour.hex, defaultColour);
		}
		
		return colour;
	}
	
	/**
	 * Validates a user-entered color code. Ensures that color is not null, it
	 * starts with #, that it has all 6 digits, and that each hex code is valid.
	 * 
	 * @param colour
	 * @param def
	 * @return
	 */
	private static String parseColour(String colour, String def)
	{
		if (colour == null)
		{
			return def;
		}
		else if (!colour.startsWith("#"))
		{
			return def;
		}
		else if (colour.length() != 7)
		{
			return def;
		}
		
		return (colour.matches("(?i)^#[0-9a-f]{6}$")) ? colour : def;
	}

	public float red()
	{
		if (this.hex == null) this.hex = this.defaultColour;
        Integer r = Integer.parseInt(this.hex.substring(1, 3), 16);
        return (r.floatValue() / 256.0F);
	}

	public float green()
	{
		if (this.hex == null) this.hex = this.defaultColour;
        Integer g = Integer.parseInt(hex.substring(3, 5), 16);
        return (g.floatValue() / 256.0F);
	}

	public float blue()
	{
		if (this.hex == null) this.hex = this.defaultColour;
        Integer b = Integer.parseInt(hex.substring(5, 7), 16);
        return (b.floatValue() / 256.0F);
	}
}
