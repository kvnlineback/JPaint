package commands;

import mouseHandler.Point;
import shapes.IShape;
import shapes.Lists;
import view.interfaces.PaintCanvasBase;

public class SelectShapeCommand implements ICommand {
	private Point startPoint;
	private Point endPoint;
	private Lists lists;
	private PaintCanvasBase pc;

	public SelectShapeCommand(Point startPoint, Point endPoint, Lists lists, PaintCanvasBase pc) {

		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.lists = lists;
		this.pc = pc;
	}

	@Override
	public void run() {
		int count = 0;
		lists.ClearSelectedShapesList();
		for (IShape shape : lists.getShapesList()) {
			if (detectCollision(shape)) {
				lists.addToSelectedShapesList(shape);
				count++;
			}
			for (IShape selectedShape : lists.getSelectedShapesList()) {
				selectedShape.decorate(pc);
			}
		}
		System.out.printf("Selected %d shapes\n", count);

	}

	private boolean detectCollision(IShape shape) {
		if (startPoint.getX() < shape.getStartPoint().getX() && startPoint.getY() < shape.getStartPoint().getY()
				&& endPoint.getX() > shape.getEndPoint().getX() && endPoint.getY() > shape.getEndPoint().getY()) {
			return true;
		} else if (startPoint.getX() > shape.getStartPoint().getX() && startPoint.getY() < shape.getStartPoint().getY()
				&& endPoint.getX() < shape.getEndPoint().getX() && endPoint.getY() > shape.getEndPoint().getY()) {
			return true;
		} else if (startPoint.getX() < shape.getStartPoint().getX() && startPoint.getY() > shape.getStartPoint().getY()
				&& endPoint.getX() > shape.getEndPoint().getX() && endPoint.getY() < shape.getEndPoint().getY()) {
			return true;
		} else if (startPoint.getX() > shape.getStartPoint().getX() && startPoint.getY() > shape.getStartPoint().getY()
				&& endPoint.getX() < shape.getEndPoint().getX() && endPoint.getY() < shape.getEndPoint().getY()) {
			return true;
		}
		return false;
	}
}