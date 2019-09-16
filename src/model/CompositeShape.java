package shapes;

import java.util.ArrayList;

import mouseHandler.Point;
import view.interfaces.PaintCanvasBase;

public class CompositeShape implements IShape {
	private Point startPoint;
	private Point endPoint;
	private ArrayList<IShape> children = new ArrayList<IShape>();

	public CompositeShape() {
		startPoint = new Point(0, 0);
		endPoint = new Point(0, 0);
	}

	@Override
	public ArrayList<IShape> ungroup() {
		ArrayList<IShape> list = new ArrayList<>();
		for (IShape child : children) {
			ArrayList<IShape> ungrouped = child.ungroup();
			for (IShape grandchild : ungrouped) {
				list.add(grandchild);
			}
		}
		return list;
	}

	@Override
	public void draw(PaintCanvasBase pcb) {
		for (IShape shape : children) {
			shape.draw(pcb);
		}
	}

	@Override
	public void move(int deltaX, int deltaY) {
		this.setStartPoint(new Point(this.getStartPoint().getX() + deltaX, this.getStartPoint().getY() + deltaY));
		this.setEndPoint(new Point(this.getEndPoint().getX() + deltaX, this.getEndPoint().getY() + deltaY));
		for (IShape child : children) {
			child.move(deltaX, deltaY);
		}
	}

	@Override
	public void decorate(PaintCanvasBase pc) {
		for (IShape child : children) {
			child.decorate(pc);
		}
	}

	public void addChild(IShape shape) {
		if (children.size() == 0) {
			setStartPoint(shape.getStartPoint());
			setEndPoint(shape.getEndPoint());
		} else {
			if (shape.getStartPoint().getX() < getStartPoint().getX()) {
				setStartPoint(new Point(shape.getStartPoint().getX(), getStartPoint().getY()));
			}
			if (shape.getStartPoint().getY() < getStartPoint().getY()) {
				setStartPoint(new Point(getStartPoint().getX(), shape.getStartPoint().getY()));
			}
			if (shape.getEndPoint().getX() > getEndPoint().getX()) {
				setEndPoint(new Point(shape.getEndPoint().getX(), getEndPoint().getY()));
			}
			if (shape.getEndPoint().getY() > getEndPoint().getY()) {
				setEndPoint(new Point(getEndPoint().getX(), shape.getEndPoint().getY()));
			}
		}
		children.add(shape);
	}

	public ArrayList<IShape> getChildren() {
		return children;
	}

	@Override
	public IShape shapeCopy() {
		CompositeShape copy = new CompositeShape();
		for (IShape child : children) {
			copy.addChild(child.shapeCopy());
		}
		return copy;
	}

	@Override
	public Point getStartPoint() {
		return startPoint;
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
}
