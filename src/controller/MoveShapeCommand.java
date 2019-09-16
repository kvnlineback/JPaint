package commands;

import java.util.ArrayList;

import mouseHandler.Point;
import shapes.IShape;
import shapes.Lists;

public class MoveShapeCommand implements ICommand, IUndoRedo {
	private Point startPoint;
	private Point endPoint;
	private Lists lists;
	private int deltaX;
	private int deltaY;
	private ArrayList<IShape> movingShapes = new ArrayList<IShape>();

	public MoveShapeCommand(Point startPoint, Point endPoint, Lists lists) {

		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.lists = lists;
		this.deltaX = endPoint.getX() - startPoint.getX();
		this.deltaY = endPoint.getY() - startPoint.getY();
	}

	@Override
	public void run() {
		int count = 0;
		for (IShape shape : lists.getSelectedShapesList()) {
			lists.removeFromShapesList(shape);
			shape.move(deltaX, deltaY);
			movingShapes.add(shape);
			lists.addtoShapesList(shape);
			count++;
		}
		CommandHistory.add(this);
		System.out.printf("Moved %d shapes\n", count);
	}

	@Override
	public void undo() {
		for (IShape shape : movingShapes) {
			shape.move(-deltaX, -deltaY);
		}
		lists.reDraw(lists.getShapesList());
	}

	@Override
	public void redo() {
		for (IShape shape : movingShapes) {
			shape.move(deltaX, deltaY);
		}
		lists.reDraw(lists.getShapesList());
	}
}