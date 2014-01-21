package wecui.render;

import static org.lwjgl.opengl.GL11.*;

/**
 * Stores data about a line that can be rendered
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
public class LineInfo
{
	
	public float lineWidth;
	public float red;
	public float green;
	public float blue;
	public float alpha;
	public int depthfunc;
	
	public LineInfo(float lineWidth, float r, float g, float b, float a, int depthfunc)
	{
		this.lineWidth = lineWidth;
		this.red = r;
		this.green = g;
		this.blue = b;
		this.alpha = a;
		this.depthfunc = depthfunc;
	}
	
	public LineInfo(float lineWidth, float r, float g, float b)
	{
		this(lineWidth, r, g, b, 1.0f, GL_LEQUAL);
	}
	
	public LineInfo(LineInfo orig)
	{
		this.lineWidth = orig.lineWidth;
		this.red = orig.red;
		this.green = orig.green;
		this.blue = orig.blue;
		this.alpha = orig.alpha;
		this.depthfunc = orig.depthfunc;
	}
	
	/**
	 * Sets the lineWidth and depthFunction based on this color
	 */
	public void prepareRender()
	{
		glLineWidth(this.lineWidth);
		glDepthFunc(this.depthfunc);
	}
	
	public void prepareColor()
	{
		glColor4f(this.red, this.green, this.blue, this.alpha);
	}
}
