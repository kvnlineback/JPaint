package strategies;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import mouseHandler.Point;
import view.interfaces.PaintCanvasBase;

public class TriangleStrategy implements IShapeStrategy {

	@Override
	public void filledIn(Point startPoint, Point endPoint, Color PrimaryColor, PaintCanvasBase pc) {
		Graphics2D graphics2d = pc.getGraphics2D();
		graphics2d.setColor(PrimaryColor);
		graphics2d.fillPolygon(new int[]{startPoint.getX(),startPoint.getX(),endPoint.getX()}, new int[]{startPoint.getY(), endPoint.getY(), endPoint.getY()}, 3);
		
	}

	public void outlined(Point startPoint, Point endPoint, Color PrimaryColor, PaintCanvasBase pc, Stroke stroke) {
		Graphics2D graphics2d = pc.getGraphics2D();
		graphics2d.setStroke(stroke);
		graphics2d.setColor(PrimaryColor);
		graphics2d.drawPolygon(new int[]{startPoint.getX(),startPoint.getX(),endPoint.getX()}, new int[]{startPoint.getY(), endPoint.getY(), endPoint.getY()}, 3);
		
	}

	@Override
	public void filledInAndOutlined(Point startPoint, Point endPoint, Color PrimaryColor, Color SecondaryColor, PaintCanvasBase pc, Stroke stroke) {
		filledIn(startPoint, endPoint, PrimaryColor, pc);
		outlined(startPoint, endPoint, SecondaryColor, pc, stroke);	
	}
}
