package com.mumfrey.worldeditcui.render.region;

/**
 * The different types of regions and the classes which we use to display them
 * 
 * @author Adam Mummery-Smith
 */
public enum RegionType
{
	CUBOID    ("cuboid",     "Cuboid",     CuboidRegion.class),
	POLYGON   ("polygon2d",  "2D Polygon", PolygonRegion.class),
	ELLIPSOID ("ellipsoid",  "Ellipsoid",  EllipsoidRegion.class),
	CYLINDER  ("cylinder",   "Cylinder",   CylinderRegion.class),
	POLYHEDRON("polyhedron", "Polyhedron", PolyhedronRegion.class);
	
	private final String key;
	
	private final String name;
	
	private final Class<? extends Region> regionClass;
	
	private RegionType(String key, String name, Class<? extends Region> regionClass)
	{
		this.key = key;
		this.name = name;
		this.regionClass = regionClass;
	}
	
	public String getKey()
	{
		return this.key;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Class<? extends Region> getRegionClass()
	{
		return this.regionClass;
	}
}
