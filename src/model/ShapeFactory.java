package shapes;

import model.interfaces.IApplicationState;
import mouseHandler.Point;
import strategies.EllipseStrategy;
import strategies.RectangleStrategy;
import strategies.TriangleStrategy;

public class ShapeFactory {

	public static Shape createTriangle(Point startPoint, Point endPoint, IApplicationState as) {
		return new Shape(startPoint, endPoint, as, new TriangleStrategy());
	}

	public static Shape createRectangle(Point startPoint, Point endPoint, IApplicationState as) {
		return new Shape(startPoint, endPoint, as, new RectangleStrategy());
	}

	public static Shape createEllipse(Point startPoint, Point endPoint, IApplicationState as) {
		return new Shape(startPoint, endPoint, as, new EllipseStrategy());
	}

}
