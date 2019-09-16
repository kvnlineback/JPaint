package strategies;

import java.awt.Color;
import java.awt.Stroke;

import mouseHandler.Point;
import view.interfaces.PaintCanvasBase;

public interface IShapeStrategy {
	public void filledIn(Point startPoint, Point endPoint, Color PrimaryColor, PaintCanvasBase pc);
	
	public void outlined(Point startPoint, Point endPoint, Color PrimaryColor, PaintCanvasBase pc, Stroke stroke);
	
	public void filledInAndOutlined(Point startPoint, Point endPoint, Color PrimaryColor, Color SecondaryColor, PaintCanvasBase pc, Stroke stroke);
}
