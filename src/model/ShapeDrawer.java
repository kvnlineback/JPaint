package shapes;

import java.awt.Color;
import java.awt.Graphics2D;

import strategies.IShapeStrategy;
import view.interfaces.PaintCanvasBase;

public class ShapeDrawer {

	private PaintCanvasBase pc;
	private Lists lists;
	private IShapeStrategy decorator;

	public ShapeDrawer(PaintCanvasBase pc, Lists lists) {
		this.pc = pc;
		this.lists = lists;
	}

	public void draw() {
		Graphics2D g = pc.getGraphics2D();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, pc.getWidth(), pc.getHeight());
		for (IShape shape : lists.getShapesList()) {
			shape.draw(pc);
			if (lists.getSelectedShapesList().contains(shape)) {
				shape.decorate(pc);
			}
		}
	}
}
