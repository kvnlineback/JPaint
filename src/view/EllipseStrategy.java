package strategies;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import mouseHandler.Point;
import view.interfaces.PaintCanvasBase;

public class EllipseStrategy implements IShapeStrategy {

	@Override
	public void filledIn(Point startPoint, Point endPoint, Color PrimaryColor, PaintCanvasBase pc) {
		Graphics2D graphics2d = pc.getGraphics2D();
		graphics2d.setColor(PrimaryColor);
		if (startPoint.getX() > endPoint.getX() && startPoint.getY() < endPoint.getY()) { // top right to bottom left
			graphics2d.fillOval(endPoint.getX(), startPoint.getY(), startPoint.getX() - endPoint.getX(), endPoint.getY() - startPoint.getY());
		} else if (startPoint.getY() > endPoint.getY() && startPoint.getX() > endPoint.getX()) { // bottom right to top
			graphics2d.fillOval(endPoint.getX(), endPoint.getY(), startPoint.getX() - endPoint.getX(), startPoint.getY() - endPoint.getY());
		} else if (startPoint.getY() > endPoint.getY()) { // bottom left to top right
			graphics2d.fillOval(startPoint.getX(), endPoint.getY(), endPoint.getX() - startPoint.getX(), startPoint.getY() - endPoint.getY());
		} else { // top left to bottom right
			graphics2d.fillOval(startPoint.getX(), startPoint.getY(), endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY());
		}
	}
	@Override
	public void outlined(Point startPoint, Point endPoint, Color PrimaryColor, PaintCanvasBase pc, Stroke stroke) {
		Graphics2D graphics2d = pc.getGraphics2D();
		graphics2d.setStroke(stroke);
		graphics2d.setColor(PrimaryColor);
		if (startPoint.getX() > endPoint.getX() && startPoint.getY() < endPoint.getY()) { // top right to bottom left
			graphics2d.drawOval(endPoint.getX(), startPoint.getY(), startPoint.getX() - endPoint.getX(), endPoint.getY() - startPoint.getY());
		} else if (startPoint.getY() > endPoint.getY() && startPoint.getX() > endPoint.getX()) { // bottom right to top
			graphics2d.drawOval(endPoint.getX(), endPoint.getY(), startPoint.getX() - endPoint.getX(), startPoint.getY() - endPoint.getY());
		} else if (startPoint.getY() > endPoint.getY()) { // bottom left to top right
			graphics2d.drawOval(startPoint.getX(), endPoint.getY(), endPoint.getX() - startPoint.getX(), startPoint.getY() - endPoint.getY());
		} else { // top left to bottom right
			graphics2d.drawOval(startPoint.getX(), startPoint.getY(), endPoint.getX() - startPoint.getX(), endPoint.getY() - startPoint.getY());
		}
	}
	@Override
	public void filledInAndOutlined(Point startPoint, Point endPoint, Color PrimaryColor, Color SecondaryColor, PaintCanvasBase pc, Stroke stroke) {
		filledIn(startPoint, endPoint, PrimaryColor, pc);
		outlined(startPoint, endPoint, SecondaryColor, pc, stroke);
	}

}
