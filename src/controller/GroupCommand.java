package commands;

import shapes.CompositeShape;
import shapes.IShape;
import shapes.Lists;

public class GroupCommand implements ICommand, IUndoRedo {

	private Lists lists;
	private CompositeShape composite;

	public GroupCommand(Lists lists) {
		this.lists = lists;
	}

	@Override
	public void run() {
		composite = new CompositeShape();
		int count = 0;
		for (IShape shape : lists.getSelectedShapesList()) {
			lists.removeFromShapesList(shape);
			composite.addChild(shape);
			count++;
		}
		lists.ClearSelectedShapesList();
		lists.addToSelectedShapesList(composite);
		lists.addtoShapesList(composite);
		CommandHistory.add(this);
		System.out.printf("Grouped %d shapes\n", count);
	}

	@Override
	public void undo() {
		lists.ClearSelectedShapesList();
		for (IShape child : composite.getChildren()) {
			lists.addToSelectedShapesList(child);
			lists.addtoShapesList(child);
		}
		lists.removeFromShapesList(composite);
	}

	@Override
	public void redo() {
		for (IShape child : composite.getChildren()) {
			lists.removeFromShapesList(child);
		}
		lists.addtoShapesList(composite);
		lists.ClearSelectedShapesList();
		lists.addToSelectedShapesList(composite);
	}
}
