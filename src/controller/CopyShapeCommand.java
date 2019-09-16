package commands;

import shapes.IShape;
import shapes.Lists;

public class CopyShapeCommand implements ICommand {
	private Lists lists;

	public CopyShapeCommand(Lists lists) {
		this.lists = lists;

	}

	@Override
	public void run() {
		int count = 0;
		if (!lists.isCopiedShapesListEmpty()) {
			lists.clearCopiedShapesList();
		}
		for (IShape shape : lists.getSelectedShapesList()) {
			lists.addtoCopiedShapesList(shape);
			count++;
		}
		System.out.printf("Copied %d shapes\n", count);
	}
}