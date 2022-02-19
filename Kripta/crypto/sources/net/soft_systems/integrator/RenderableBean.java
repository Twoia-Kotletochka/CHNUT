package net.soft_systems.integrator;
/**
 * Bean which can be drawn on some surface using object <code>Sign</code>
 * @see Sign
 */
public interface RenderableBean extends Bean {
	/**
	 * @return Sign of bean.
	 */
	public Sign getSign();
	/**
	 * @return X coordinate of bean
	 */
	public double getX();
	/**
	 * @return Y coordinate of bean
	 */
	public double getY();
	/**
	 * Moves bean to new position
	 */
	public void moveTo(double X, double Y);
	/**
	 * Determines x coordinate of point where links are connected
	 * @return Horizontal coordinate relative to X coordinate of sign, which is considered as center of sign
	 */
	double getCenterX();
	/**
	 * Determines y coordinate of point where links are connected
	 * @return Vertical coordinate relative to Y coordinate of sign, which is considered as center of sign
	 */
	double getCenterY();
}

