package net.soft_systems.integrator;
import java.awt.*;
import java.awt.geom.*;
import java.util.Vector;
public class Sign {
	private Color color = Color.black;
	private Color fillColor = Color.white;
	public void setColor(Color color) { this.color = color; }
	public Color getColor() { return color; }
	public void setFillColor(Color color) { this.fillColor = color; }
	public Color getFillColor() { return fillColor; }
	public void setOpaqueColor() { fillColor = null; }
	/**
	 * @associates Shape
	 */
	private Vector shapes;
	public void addShape(Shape shape) { shapes.add(shape); }
	public void addSign(Sign sign) { this.shapes.addAll(sign.getShapes()); }
	public Sign() { shapes = new Vector(); }
	public void draw(Graphics2D g2d) {
		Color lastColor = g2d.getColor();
		g2d.setColor(color);
		for (int i = 0; i < shapes.size(); i++) {
			if (fillColor != null) {
				g2d.setColor(fillColor);
				g2d.fill((Shape)shapes.elementAt(i));
				g2d.setColor(color);
			}
			g2d.draw((Shape)shapes.elementAt(i));
		}
		g2d.setColor(lastColor);
	}
	public void draw(Graphics2D g2d, double scale) {
		Color lastColor = g2d.getColor();
		g2d.setColor(color);
		AffineTransform transform = AffineTransform.getScaleInstance(scale, scale);
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = (Shape)shapes.elementAt(i);
			Shape scaledShape = transform.createTransformedShape(shape);
			if (fillColor != null) {
				g2d.setColor(fillColor);
				g2d.fill(scaledShape);
				g2d.setColor(color);
			}
			g2d.draw(scaledShape);
		}
		g2d.setColor(lastColor);
	}
	public void draw(Graphics2D g2d, AffineTransform transform) {
		Color lastColor = g2d.getColor();
		g2d.setColor(color);
		for (int i = 0; i < shapes.size(); i++) {
			Shape shape = (Shape)shapes.elementAt(i);
			Shape scaledShape = transform.createTransformedShape(shape);
			if (fillColor != null) {
				g2d.setColor(fillColor);
				g2d.fill(scaledShape);
				g2d.setColor(color);
			}
			g2d.draw(scaledShape);
		}
		g2d.setColor(lastColor);
	}
	public Area getArea() {
		Area area = new Area();
		for (int i = 0; i < shapes.size(); i++) {
			Shape s = (Shape)shapes.elementAt(i);
			Area a = new Area(s);
			area.add(a);
		}
		return area;
	}
	public double getWidth() {
		Rectangle r = getBounds();
		return r.getX() + r.getWidth();
	}
	public double getHeight() {
		Rectangle r = getBounds();
		return r.getY() + r.getHeight();
	}
	public Rectangle getBounds(AffineTransform transform) {
		Rectangle r = getBounds();
		r = transform.createTransformedShape(r).getBounds();
		return r;
	}
	public Shape getSelection() { return getBounds(); }
	public Rectangle getBounds() {
		Rectangle rect = null;
		for (int i = 0; i < shapes.size(); i++) {
			Shape s = (Shape)shapes.elementAt(i);
			Rectangle r = s.getBounds();
			if (rect != null) { rect.add(r); }
			else { rect = r; }
		}
		if (rect != null) { rect.grow(2, 2); }
		return rect;
	}
	public Vector getShapes() { return shapes; }
	public boolean getDrawId() { return true; }
}

