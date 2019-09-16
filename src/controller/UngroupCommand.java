package commands;

import java.util.ArrayList;

import shapes.IShape;
import shapes.Lists;

public class UngroupCommand implements ICommand, IUndoRedo {
	private Lists lists;
	private ArrayList<IShape> selected;
	private ArrayList<IShape> composite;

	public UngroupCommand(Lists lists) {
		this.lists = lists;
	}

	@Override
	public void run() {
		redo();
		CommandHistory.add(this);
	}

	@Override
	public void undo() {
		for (IShape shape : composite) {
			lists.addToSelectedShapesList(shape);
			lists.addtoShapesList(shape);
			for (IShape otherShape : shape.ungroup()) {
				lists.removeFromShapesList(otherShape);
				lists.removeFromSelectedShapesList(otherShape);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void redo() {
		selected = (ArrayList<IShape>) lists.getSelectedShapesList().clone();
		composite = new ArrayList<IShape>();
		lists.ClearSelectedShapesList();
		int count = 0;
		for (IShape shape : selected) {
			ArrayList<IShape> list = shape.ungroup();
			lists.removeFromShapesList(shape);
			if (shape.getClass().getName().equals("shapes.CompositeShape")) {
				composite.add(shape);
			}
			for (IShape child : list) {
				lists.addToSelectedShapesList(child);
				lists.addtoShapesList(child);
				count++;
			}
			System.out.printf("Ungrouped %d shapes\n", count);
		}
	}
}
