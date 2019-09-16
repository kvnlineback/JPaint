package shapes;

import java.util.ArrayList;

import view.interfaces.PaintCanvasBase;

public class Lists {
	private final ArrayList<IShape> shapesList = new ArrayList<IShape>();
	private final ArrayList<IShape> copiedShapesList = new ArrayList<IShape>();
	private final ArrayList<IShape> selectedShapesList = new ArrayList<IShape>();
	private ShapeDrawer shapeDraw;

	public Lists(PaintCanvasBase pc) {
		shapeDraw = new ShapeDrawer(pc, this);
	}

	public void addtoShapesList(IShape Shape) {
		shapesList.add(Shape);
		shapeDraw.draw();
	}

	public void removeFromShapesList(IShape Shape) {
		shapesList.remove(Shape);
		shapeDraw.draw();
	}

	public ArrayList<IShape> getShapesList() {
		return shapesList;
	}

	public void reDraw(ArrayList<IShape> shape) {
		shapeDraw.draw();
	}

	public void addToSelectedShapesList(IShape shape) {
		selectedShapesList.add(shape);
		shapeDraw.draw();
	}

	public ArrayList<IShape> getSelectedShapesList() {
		return selectedShapesList;
	}

	public void removeFromSelectedShapesList(IShape shape) {
		selectedShapesList.remove(shape);
		shapeDraw.draw();
	}

	public void ClearSelectedShapesList() {
		selectedShapesList.clear();
		shapeDraw.draw();
	}

	public boolean isSelectedShapesListEmpty() {
		return selectedShapesList.isEmpty();
	}

	public boolean isCopiedShapesListEmpty() {
		return copiedShapesList.isEmpty();
	}

	public void addtoCopiedShapesList(IShape Shape) {
		copiedShapesList.add(Shape);
	}

	public void removeFromCopiedShapesList(IShape Shape) {
		copiedShapesList.remove(Shape);
	}

	public ArrayList<IShape> getCopiedShapesList() {
		return copiedShapesList;
	}

	public void clearCopiedShapesList() {
		copiedShapesList.clear();
	}
}