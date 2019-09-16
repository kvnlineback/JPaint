package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IApplicationState;
import mouseHandler.Point;
import strategies.IShapeStrategy;
import strategies.SelectedShapeDecorator;
import view.interfaces.PaintCanvasBase;

public class Shape implements IShape {
	private Point startPoint;
	private Point endPoint;
	private Color primaryColor;
	private Color secondaryColor;
	private IShapeStrategy shapeStrategy;
	private IApplicationState as;
	private ShapeShadingType type;
	private ShapeType shapeType;

	public Shape(Point start, Point end, IApplicationState as, IShapeStrategy strategy) {
		setStartPoint(start);
		setEndPoint(end);
		primaryColor = getPrimaryColor(as.getActivePrimaryColor());
		secondaryColor = getSecondaryColor(as.getActiveSecondaryColor());
		this.as = as;
		this.type = as.getActiveShapeShadingType();
		shapeStrategy = strategy;
		shapeType = as.getActiveShapeType();
	}

	@Override
	public Point getStartPoint() {
		return startPoint;
	}

	public void setShapeType(ShapeType type) {
		this.shapeType = type;
	}

	@Override
	public Point getEndPoint() {
		return endPoint;
	}

	@Override
	public void setStartPoint(Point start) {
		startPoint = start;
	}

	@Override
	public void setEndPoint(Point end) {
		endPoint = end;
	}

	public Color getPrimaryColor(ShapeColor color) {
		return switchColor(color);

	}

	public Color getSecondaryColor(ShapeColor color) {
		return switchColor(color);

	}

	@Override
	public void decorate(PaintCanvasBase pc) {
		IShapeStrategy decorator = new SelectedShapeDecorator(this.getStrategy());
		decorator.outlined(this.getStartPoint(), this.getEndPoint(), this.getPrimaryColor(), pc, new BasicStroke(5));
	}

	@Override
	public ArrayList<IShape> ungroup() {
		ArrayList<IShape> list = new ArrayList<IShape>();
		list.add(this);
		return list;
	}

	@Override
	public void move(int deltaX, int deltaY) {
		this.setStartPoint(new Point(this.getStartPoint().getX() + deltaX, this.getStartPoint().getY() + deltaY));
		this.setEndPoint(new Point(this.getEndPoint().getX() + deltaX, this.getEndPoint().getY() + deltaY));
	}

	@Override
	public String toString() {
		return "(" + startPoint + ", " + endPoint + ")";
	}

	private Color switchColor(ShapeColor color) {
		switch (color) {
		case BLACK:
			return Color.BLACK;
		case BLUE:
			return Color.BLUE;
		case CYAN:
			return Color.CYAN;
		case DARK_GRAY:
			return Color.DARK_GRAY;
		case GRAY:
			return Color.GRAY;
		case GREEN:
			return Color.GREEN;
		case LIGHT_GRAY:
			return Color.LIGHT_GRAY;
		case MAGENTA:
			return Color.MAGENTA;
		case ORANGE:
			return Color.ORANGE;
		case PINK:
			return Color.pink;
		case RED:
			return Color.RED;
		case WHITE:
			return Color.white;
		default:
			return Color.yellow;

		}
	}

	public void offsetShape(IShape shape) {
		shape.getStartPoint().setX(shape.getStartPoint().getX() + 150);
		shape.getStartPoint().setY(shape.getStartPoint().getY() + 150);
		shape.getEndPoint().setX(shape.getEndPoint().getX() + 150);
		shape.getEndPoint().setY(shape.getEndPoint().getY() + 150);
	}

	public IShapeStrategy getStrategy() {
		return shapeStrategy;
	}

	public void setShadeType(ShapeShadingType type) {
		this.type = type;

	}

	@Override
	public void draw(PaintCanvasBase pc) {
		if (type.equals(ShapeShadingType.OUTLINE))
			shapeStrategy.outlined(startPoint, endPoint, primaryColor, pc, new BasicStroke(5));
		if (type.equals(ShapeShadingType.FILLED_IN))
			shapeStrategy.filledIn(startPoint, endPoint, primaryColor, pc);
		if (type.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN))
			shapeStrategy.filledInAndOutlined(startPoint, endPoint, primaryColor, secondaryColor, pc,
					new BasicStroke(5));
	}

	public Color getPrimaryColor() {
		return primaryColor;
	}

	public void setPrimaryColor(Color color) {
		this.primaryColor = color;
	}

	public Color getSecondaryColor() {
		return secondaryColor;
	}

	public void setSecondaryColor(Color color) {
		this.secondaryColor = color;
	}

	@Override
	public IShape shapeCopy() {
		Point start = new Point(this.getStartPoint().getX(), this.getStartPoint().getY());
		Point end = new Point(this.getEndPoint().getX(), this.getEndPoint().getY());
		Shape newShape = null;
		if (shapeType == ShapeType.ELLIPSE) {
			newShape = ShapeFactory.createEllipse(start, end, as);
		}
		if (shapeType == ShapeType.RECTANGLE) {
			newShape = ShapeFactory.createRectangle(start, end, as);
		}
		if (shapeType == ShapeType.TRIANGLE) {
			newShape = ShapeFactory.createTriangle(start, end, as);
		}
		newShape.setShapeType(this.shapeType);
		newShape.setPrimaryColor(this.primaryColor);
		newShape.setSecondaryColor(this.secondaryColor);
		newShape.setShadeType(this.type);
		offsetShape(newShape);
		return newShape;
	}
}
