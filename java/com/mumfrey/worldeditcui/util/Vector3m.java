package com.mumfrey.worldeditcui.util;

/**
 * A version of Vector3 that can be modified.
 * 
 * @author yetanotherx
 */
public class Vector3m extends Vector3
{
	
	public Vector3m(float x, float y, float z)
	{
		super(x, y, z);
	}
	
	public Vector3m(Double x, Double y, Double z)
	{
		super(x, y, z);
	}
	
	public Vector3m(Vector3 vector)
	{
		super(vector);
	}
	
	public Vector3m()
	{
	}
	
	public void setZ(float z)
	{
		this.z = z;
	}
	
	public void setY(float y)
	{
		this.y = y;
	}
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	/**
	 * Adds two vectors
	 *
	 * @param that
	 * @return
	 */
	
	@Override
	public Vector3 add(Vector3 that)
	{
		this.x += that.x;
		this.y += that.y;
		this.z += that.z;
		return this;
	}
	
	/**
	 * Subtracts two vectors
	 *
	 * @param that
	 * @return
	 */
	
	@Override
	public Vector3 subtract(Vector3 that)
	{
		this.x -= that.x;
		this.y -= that.y;
		this.z -= that.z;
		return this;
	}
	
	/**
	 * Scales by the scalar value
	 *
	 * @param scale
	 * @return
	 */
	
	@Override
	public Vector3 scale(float scale)
	{
		this.x *= scale;
		this.y *= scale;
		this.z *= scale;
		return this;
	}
	
	/**
	 * Takes the cross product of two vectors
	 *
	 * @param that
	 * @return
	 */
	
	@Override
	public Vector3 cross(Vector3 that)
	{
		this.x = this.getY() * that.getZ() - this.getZ() * that.getY();
		this.y = this.getZ() * that.getX() - this.getX() * that.getZ();
		this.z = this.getX() * that.getY() - this.getY() * that.getX();
		
		return this;
	}
	
	/**
	 * Rounds the X, Y, and Z values of this Vector3 up to 
	 * the nearest integer value. 
	 * 
	 * @return 
	 */
	@Override
	public Vector3 ceil()
	{
		this.x = (float)Math.ceil(this.x);
		this.y = (float)Math.ceil(this.y);
		this.z = (float)Math.ceil(this.z);
		return this;
	}
	
	/**
	 * Rounds the X, Y, and Z values of this Vector3 down to 
	 * the nearest integer value. 
	 * 
	 * @return 
	 */
	@Override
	public Vector3 floor()
	{
		this.x = (float)Math.floor(this.x);
		this.y = (float)Math.floor(this.y);
		this.z = (float)Math.floor(this.z);
		return this;
	}
	
	/**
	 * Rounds the X, Y, and Z values of this Vector3 to 
	 * the nearest integer value. 
	 * 
	 * @return 
	 */
	@Override
	public Vector3 round()
	{
		this.x = Math.round(this.x);
		this.y = Math.round(this.y);
		this.z = Math.round(this.z);
		return this;
	}
	
	/**
	 * Sets the X, Y, and Z values of this Vector3 to their
	 * absolute value.
	 * 
	 * @return 
	 */
	@Override
	public Vector3 abs()
	{
		this.x = Math.abs(this.x);
		this.y = Math.abs(this.y);
		this.z = Math.abs(this.z);
		return this;
	}
	
	/**
	 * returns the vector with a length of 1
	 *
	 * @return
	 */
	
	@Override
	public Vector3 normalize()
	{
		float length = this.length();
		this.x *= 1 / length;
		this.y *= 1 / length;
		this.z *= 1 / length;
		return this;
	}
}
