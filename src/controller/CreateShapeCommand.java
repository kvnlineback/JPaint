package commands;

import model.ShapeType;
import model.interfaces.IApplicationState;
import mouseHandler.Point;
import shapes.IShape;
import shapes.Lists;
import shapes.ShapeFactory;

public class CreateShapeCommand implements ICommand, IUndoRedo {

	private Point startPoint;
	private Point endPoint;
	private IApplicationState as;
	private Lists lists;
	private ShapeType type;
	private IShape shape;

	public CreateShapeCommand(Point startPoint, Point endPoint, IApplicationState as, Lists lists) {

		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.as = as;
		this.lists = lists;
		this.type = as.getActiveShapeType();

	}

	@Override
	public void run() {
		lists.ClearSelectedShapesList();
		IShape shape = null;
		if (type == ShapeType.ELLIPSE) {
			shape = ShapeFactory.createEllipse(startPoint, endPoint, as);
		} else if (type == ShapeType.RECTANGLE) {
			shape = ShapeFactory.createRectangle(startPoint, endPoint, as);
		} else {// triangle
			shape = ShapeFactory.createTriangle(startPoint, endPoint, as);
		}
		lists.addtoShapesList(shape);
		CommandHistory.add(this);
	}

	@Override
	public void undo() {
		lists.removeFromShapesList(shape);

	}

	@Override
	public void redo() {
		lists.addtoShapesList(shape);

	}
}
