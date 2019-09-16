package shapes;

import java.util.ArrayList;

import mouseHandler.Point;
import view.interfaces.PaintCanvasBase;

public interface IShape {

	ArrayList<IShape> ungroup();

	void draw(PaintCanvasBase pcb);

	void move(int deltaX, int deltaY);

	void decorate(PaintCanvasBase pc);

	IShape shapeCopy();

	Point getStartPoint();

	void setStartPoint(Point point);

	Point getEndPoint();

	void setEndPoint(Point point);

}
