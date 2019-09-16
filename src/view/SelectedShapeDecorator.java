package strategies;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import mouseHandler.Point;
import view.interfaces.PaintCanvasBase;

public class SelectedShapeDecorator implements IShapeStrategy {
	
	private IShapeStrategy strategy;
	
	public SelectedShapeDecorator(IShapeStrategy strategy) {
		
		this.strategy = strategy;
	}

	@Override
	public void filledIn(Point startPoint, Point endPoint, Color PrimaryColor, PaintCanvasBase pc) {

	}

	@Override
	public void outlined(Point startPoint, Point endPoint, Color PrimaryColor, PaintCanvasBase pc, Stroke stroke) {
		Point outlineStart = null;
		Point outlineEnd = null;
		Stroke dashedLine = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
		if (startPoint.getX() > endPoint.getX() && startPoint.getY() < endPoint.getY()) { // top right to bottom left
			outlineStart = new Point(startPoint.getX()+5, startPoint.getY()-5);
			outlineEnd = new Point(endPoint.getX()-5, endPoint.getY()+5);
		} else if (startPoint.getY() > endPoint.getY() && startPoint.getX() > endPoint.getX()) { // bottom right to top
			outlineStart = new Point(startPoint.getX()+5, startPoint.getY()+5);
			outlineEnd = new Point(endPoint.getX()-5, endPoint.getY()-5);
		} else if (startPoint.getY() > endPoint.getY()) { // bottom left to top right
			outlineStart = new Point(startPoint.getX()-5, startPoint.getY()+5);
			outlineEnd = new Point(endPoint.getX()+5, endPoint.getY()-5);
		} else { // top left to bottom right
			outlineStart = new Point(startPoint.getX()-5, startPoint.getY()-5);
			outlineEnd = new Point(endPoint.getX()+5, endPoint.getY()+5);
		}
		strategy.outlined(outlineStart, outlineEnd, Color.BLACK, pc, dashedLine);
		
	}

	@Override
	public void filledInAndOutlined(Point startPoint, Point endPoint, Color PrimaryColor, Color SecondaryColor, PaintCanvasBase pc, Stroke stroke) {
		
	}
}