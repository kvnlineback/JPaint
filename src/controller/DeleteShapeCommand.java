package commands;

import java.util.ArrayList;

import shapes.IShape;
import shapes.Lists;

public class DeleteShapeCommand implements ICommand, IUndoRedo {

	private Lists lists;
	private ArrayList<IShape> deletedShapes = new ArrayList<IShape>();

	public DeleteShapeCommand(Lists lists) {
		this.lists = lists;

	}

	@Override
	public void run() {
		int count = 0;
		for (IShape shape : lists.getSelectedShapesList()) {
			lists.removeFromShapesList(shape);
			count++;
			deletedShapes.add(shape);
		}
		lists.ClearSelectedShapesList();
		CommandHistory.add(this);
		System.out.println("Deleted " + count + " shapes");
	}

	@Override
	public void undo() {
		for (IShape shape : deletedShapes) {
			lists.addtoShapesList(shape);
			lists.addToSelectedShapesList(shape);
		}

	}

	@Override
	public void redo() {
		for (IShape shape : deletedShapes) {
			lists.removeFromShapesList(shape);
		}
	}
}
