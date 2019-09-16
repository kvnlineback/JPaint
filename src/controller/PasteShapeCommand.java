package commands;

import java.util.ArrayList;

import shapes.IShape;
import shapes.Lists;

public class PasteShapeCommand implements ICommand, IUndoRedo {
	private Lists lists;
	private ArrayList<IShape> copyingShapes = new ArrayList<IShape>();

	public PasteShapeCommand(Lists lists) {
		this.lists = lists;

	}

	@Override
	public void run() {
		int count = 0;
		for (IShape shape : lists.getCopiedShapesList()) {
			IShape newShape = shape.shapeCopy();
			lists.addtoShapesList(newShape);
			copyingShapes.add(newShape);
			count++;
		}
		CommandHistory.add(this);
		System.out.printf("Pasted %d shapes\n", count);
	}

	@Override
	public void undo() {
		for (IShape shape : copyingShapes) {
			lists.removeFromShapesList(shape);
		}

	}

	@Override
	public void redo() {
		for (IShape shape : copyingShapes) {
			lists.addtoShapesList(shape);
		}
	}
}
