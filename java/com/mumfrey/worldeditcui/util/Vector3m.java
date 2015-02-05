package com.mumfrey.worldeditcui.util;

/**
 * A version of Vector3 that can be modified.
 * 
 * @author yetanotherx
 */
public class Vector3m extends Vector3
{
	
	public Vector3m(double x, double y, double z)
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
	
	public void setZ(double z)
	{
		this.z = z;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public void setX(double x)
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
	public Vector3 scale(double scale)
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
		this.x = Math.ceil(this.x);
		this.y = Math.ceil(this.y);
		this.z = Math.ceil(this.z);
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
		this.x = Math.floor(this.x);
		this.y = Math.floor(this.y);
		this.z = Math.floor(this.z);
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
		double lengthReciprocal = 1.0 / this.length();
		this.x *= lengthReciprocal;
		this.y *= lengthReciprocal;
		this.z *= lengthReciprocal;
		return this;
	}
}
