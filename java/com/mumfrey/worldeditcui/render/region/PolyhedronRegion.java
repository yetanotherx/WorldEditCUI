package com.mumfrey.worldeditcui.render.region;

import java.util.ArrayList;
import java.util.List;

import com.mumfrey.worldeditcui.WorldEditCUI;
import com.mumfrey.worldeditcui.render.LineColour;
import com.mumfrey.worldeditcui.render.points.PointCube;
import com.mumfrey.worldeditcui.render.shapes.Render3DPolygon;
import com.mumfrey.worldeditcui.util.Vector3;

/**
 * Main controller for a polygon-type region
 * 
 * @author TomyLobo
 */
public class PolyhedronRegion extends BaseRegion
{
	
	protected List<PointCube> vertices = new ArrayList<PointCube>();
	protected List<Vector3[]> faces = new ArrayList<Vector3[]>();
	
	public PolyhedronRegion(WorldEditCUI controller)
	{
		super(controller);
	}
	
	@Override
	public void render()
	{
		for (PointCube vertex : this.vertices)
		{
			vertex.render();
		}
		
		for (Vector3[] face : this.faces)
		{
			new Render3DPolygon(LineColour.POLYBOX, face).render();
		}
	}
	
	@Override
	public void setCuboidPoint(int id, double x, double y, double z)
	{
		final PointCube vertex = new PointCube(x, y, z);
		vertex.setColour(id == 0 ? LineColour.CUBOIDPOINT1 : LineColour.POLYPOINT);
		
		if (id < this.vertices.size())
		{
			this.vertices.set(id, vertex);
		}
		else
		{
			for (int i = 0; i < id - this.vertices.size(); i++)
			{
				this.vertices.add(null);
			}
			this.vertices.add(vertex);
		}
	}
	
	private static final Vector3 half = new Vector3(0.5, 0.5, 0.5);
	
	@Override
	public void addPolygon(int[] vertexIds)
	{
		final Vector3[] face = new Vector3[vertexIds.length];
		for (int i = 0; i < vertexIds.length; ++i)
		{
			final PointCube vertex = vertices.get(vertexIds[i]);
			if (vertex == null)
			{
				// This should never happen
				return;
			}
			
			face[i] = vertex.getPoint().add(half);
		}
		faces.add(face);
	}
	
	@Override
	public RegionType getType()
	{
		return RegionType.POLYHEDRON;
	}
}
